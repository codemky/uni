package edu.uni.place.controller;

import com.github.pagehelper.PageInfo;
import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.place.bean.Field;
import edu.uni.place.dto.Fielddto;
import edu.uni.place.service.FieldService;
import edu.uni.place.service.FielddtoService;
import edu.uni.utils.RedisCache;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

/**
 * @Author 潘绍豪
 * @Create 2019/4/30
 * @Description
 * @Since 1.0.0
 */
@Api(description = "场地基础信息")
@Controller
@RequestMapping("json/place")
public class FieldController {
    @Autowired
    private FieldService fieldService;
    @Autowired
    private FielddtoService fielddtoService;

    @Autowired
    private RedisCache cache;

    /**
     * 内部类，专门用来管理每个方法所对应缓存的名称。
     */
    static class CacheNameHelper{
        // {场地信息id}
        private static final String Receive_CacheNamePrefix = "e_field_";
        // {页码}
        private static final String List_CacheNamePrefix = "e_field_list_";
        // {学校id}_{校区id}_{功能区id}_{页码}
        private static final String ListByUIdSIdAId_CacheNamePrefix = "e_field_listByUIdSIdAId_list";
    }

    /**
     * 新增场地信息
     * @param field
     * @return
     */
    @ApiOperation(value="新增场地信息", notes = "")
    @ApiImplicitParam(name= "field",value = "场地信息实体类", required = true, dataType = "Field")
    @PostMapping("field/create")
    @ResponseBody
    public Result create(@RequestBody(required = false) Field field){
        //初始化场地
        field.setDatetime(Calendar.getInstance().getTime());
        if(field != null){
            boolean success = fieldService.insert(field);
            if(success){
                cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");

                cache.deleteByPaterm(CacheNameHelper.ListByUIdSIdAId_CacheNamePrefix + "*");
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }


    /**
     * 删除场地信息
     * @param id
     * @return
     */
    @ApiOperation(value = "根据场地信息id删除场地信息", notes = "")
    @ApiImplicitParam(name = "id", value = "场地信息id", required = true, dataType = "Long", paramType = "path")
    @DeleteMapping("field/destroy/{id}")
    @ResponseBody
    public Result destroy(@PathVariable Long id){
        boolean success = fieldService.delete(id);
        if(success){
            cache.delete(CacheNameHelper.Receive_CacheNamePrefix + id);
            cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.ListByUIdSIdAId_CacheNamePrefix + "*");
            return Result.build(ResultType.Success);
        }else{
            return Result.build(ResultType.Failed);
        }
    }

    /**
     * 修改场地信息
     * @param field
     * @return
     */
    @ApiOperation(value="根据场地信息id修改场地信息信息")
    @ApiImplicitParam(name="field", value = "场地信息实体类", required = true, dataType = "Field")
    @PutMapping("field/update")
    @ResponseBody
    public Result update(@RequestBody(required = false) Field field){
        //初始化场地
        field.setDatetime(Calendar.getInstance().getTime());
        if(field != null && field.getId() != null){
            boolean success = fieldService.update(field);
            if(success){
                cache.delete(CacheNameHelper.Receive_CacheNamePrefix + field.getId());
                cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListByUIdSIdAId_CacheNamePrefix + "*");
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }

    /**
     * 获取场地信息详情
     * @param id
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据场地信息id获取场地信息详情", notes = "")
    @ApiImplicitParam(name = "id", value = "场地信息id", required = true, dataType = "Long", paramType = "path")
    @GetMapping("field/receive/{id}")
    public void receive(@PathVariable Long id,
                        HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.Receive_CacheNamePrefix + id;
        String json = cache.get(cacheName);
        if(json == null){
            Field field = fieldService.select(id);
            json = Result.build(ResultType.Success).appendData("field",field ).convertIntoJSON();
            if(field != null){
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }


    /**
     * @description: 根据功能区id和场地名称模糊查询
     * @author: 潘绍豪
     * @date: {2019/5/9} {20:21}
     */
    @ApiOperation(value = "根据功能区id和场地名称模糊查询", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name="name", value = "名称", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name="areaId", value = "功能区id", required = true, dataType = "Long", paramType = "path"),
            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping("field/listLikename/{name}/{areaId}/{pageNum}")
    public void listByCid(@PathVariable String name,
                          @PathVariable Long areaId,
                          @PathVariable Integer pageNum,
                          HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByUIdSIdAId_CacheNamePrefix + name + "_" + areaId + "_"+ pageNum;
        String json = cache.get(cacheName);
        if (json == null) {
            PageInfo<Field> pageInfo = fieldService.selectPageLikeName(pageNum, name,areaId);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if (pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    /**
     * 根据功能区id和页码来分页查询场地信息
     * @param areaId
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据功能区id和页码来分页查询场地信息", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name="universityId", value = "学校id", required = true, dataType = "Long", paramType = "path"),
            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/field/listByUIdSIdAId_page/{areaId}/{pageNum}")
    public void listByUIdSIdAId_page(@PathVariable Long areaId,
                          @PathVariable Integer pageNum,
                          HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByUIdSIdAId_CacheNamePrefix +  areaId + "_"+ pageNum;
        String json = cache.get(cacheName);
        if (json == null) {
            PageInfo<Field> pageInfo = fieldService.selectPageByUIdSIdAId(pageNum, areaId);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if (pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }
    /**
     * @description: 学校id和页码来分页查询场地信息
     * @author: 潘绍豪
     * @date: {2019/5/10} {17:58}
     */
    @ApiOperation(value = "学校id和页码来分页查询场地信息", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name="universityId", value = "学校id", required = true, dataType = "Long", paramType = "path"),
            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/fields/listByUId_page/{universityId}/{pageNum}")
    public void listByCid(@PathVariable Long universityId,
                          @PathVariable Integer pageNum,
                          HttpServletResponse response) throws IOException{
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByUIdSIdAId_CacheNamePrefix + universityId + "_" + pageNum;
        String json = cache.get(cacheName);
        if (json == null) {
            PageInfo<Field> pageInfo = fieldService.selectPageByUId(pageNum, universityId);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if (pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    /**
     * @description: 校区id和页码来分页查询场地信息
     * @author: 潘绍豪
     * @date: {2019/5/10} {18:24}
     */
    @ApiOperation(value = "根据校区id和页码来分页查询场地信息", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name="schoolId", value = "校区id", required = true, dataType = "Long", paramType = "path"),
            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/fields/listByUIdSId_page/{schoolId}/{pageNum}")
    public void listByUidSid(@PathVariable Long schoolId,
                          @PathVariable Integer pageNum,
                          HttpServletResponse response) throws IOException{
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByUIdSIdAId_CacheNamePrefix  + schoolId + "_"  + pageNum;
        String json = cache.get(cacheName);
        if (json == null) {
            PageInfo<Field> pageInfo = fieldService.selectPageByUIdSId(pageNum, schoolId);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if (pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);

    }


    /**
     * 根据功能区id来查询场地信息
     * @param areaId
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据功能区id来查询场地信息", notes = "")
    @ApiImplicitParams({
           @ApiImplicitParam(name="areaId", value = "功能区id", required = true, dataType = "Long", paramType = "path"),
    })
    @GetMapping(value = "/field/listByUIdSIdAId/{{areaId}")
    public void listByUid(@PathVariable Long areaId,
                          HttpServletResponse response) throws IOException{
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByUIdSIdAId_CacheNamePrefix  + areaId;
        String json = cache.get(cacheName);
        if(json == null){
            List<Field> list = fieldService.seleByUIdSIdAId(areaId);
            json = Result.build(ResultType.Success).appendData("list", list).convertIntoJSON();
            if(list != null){
                cache.set(cacheName,json);
            }
        }
        response.getWriter().write(json);

    }

    /**
     * @description: 根据校区id查校区名、功能区查功能区名、场地名模糊查询
     * @author: 潘绍豪
     * @date: {2019/5/11} {15:21}
     */
    @ApiOperation(value = "根据校区id查校区名、功能区查功能区名、场地名模糊查询", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name="universityId", value = "学校id", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name="schoolId", value = "校区id",  dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name="areaId", value = "功能区id",dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name="name", value = "场地名称",dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "int", paramType = "query")
    })
    @GetMapping(value = "/fields/selectFileddtosPageByIds")
    public void listwithIds(@RequestParam(name = "universityId") Long universityId,
                            @RequestParam(name = "schoolId",required = false) Long schoolId,
                            @RequestParam(name = "areaId",required = false) Long areaId,
                            @RequestParam(name = "name",required = false) String name,
                            @RequestParam(name = "pageNum") int pageNum,
                            HttpServletResponse response) throws IOException{
        response.setContentType("application/json;charset=utf-8");
        //System.out.println(universityId);
        //System.out.println(pageNum);
        String cachename;
        cachename = CacheNameHelper.ListByUIdSIdAId_CacheNamePrefix + universityId;
        cachename += "_";
        if (schoolId != null) {
            cachename += schoolId;
        }
        cachename += "_";
        if (areaId != null) {
            cachename +=  areaId;
        }
        cachename += "_";
        if(name != null){
            cachename +=  name;
        }
        cachename = cachename + "_" + pageNum;
        String json = cache.get(cachename);
        //String json = null;
        if(json == null){
            PageInfo<Fielddto> pageInfo = fielddtoService.selectFileddtosPageByIds(universityId,  schoolId,  areaId,name, pageNum);

            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null){
                cache.set(cachename,json);
            }
        }
        response.getWriter().write(json);
    }
    
    /**
     * 清除所有缓存
     * @return
     */
    @ApiOperation(value = "清除所有缓存", notes = "")
    @DeleteMapping("fields/cache")
    @ResponseBody
    public Result destroyCache(){
        cache.deleteByPaterm(CacheNameHelper.Receive_CacheNamePrefix + "*");
        cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
        cache.deleteByPaterm(CacheNameHelper.ListByUIdSIdAId_CacheNamePrefix + "*");
        return Result.build(ResultType.Success);
    }
}
