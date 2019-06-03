package edu.uni.place.controller;

import com.github.pagehelper.PageInfo;
import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.place.bean.Schoolarea;
import edu.uni.place.service.SchoolareaService;
import edu.uni.utils.RedisCache;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

/**
 * @Author 潘绍豪
 * @Create 2019/4/29
 * @Description
 * @Since 1.0.0
 */

@Api(description = "场地基础信息")
@Controller
@RequestMapping("json/place")
public class SchoolareaController {
    @Autowired
    private SchoolareaService schoolareaService;

    @Autowired
    private RedisCache cache;

    /**
     * 内部类，专门用来管理每个方法所对应缓存的名称。
     */
    static class CacheNameHelper{
        // {学校功能区id}
        private static final String Receive_CacheNamePrefix = "e_schoolarea_";
        // {页码}
        private static final String List_CacheNamePrefix = "e_schoolarea_list_";
        // {学校id}_{校区id}_{页码}
        private static final String ListByUIdSId_CacheNamePrefix = "e_schoolarea_listByUIdSId_";
    }

    /**
     * 新增学校功能区
     * @param schoolarea
     * @return
     */
    @ApiOperation(value="新增学校功能区", notes = "")
    @ApiImplicitParam(name= "schoolarea",value = "学校功能区实体类", required = true, dataType = "Schoolarea")
    @PostMapping("schoolarea/create")
    @ResponseBody
    public Result create(@RequestBody(required = false) Schoolarea schoolarea){
        //初始化功能区
        schoolarea.setDatetime(Calendar.getInstance().getTime());
        if(schoolarea != null){
            boolean success = schoolareaService.insert(schoolarea);
            if(success){
                cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");

                cache.deleteByPaterm(CacheNameHelper.ListByUIdSId_CacheNamePrefix + "*");
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }

    /**
     * 删除学校功能区
     * @param id
     * @return
     */
    @ApiOperation(value = "根据学校功能区id删除学校功能区", notes = "")
    @ApiImplicitParam(name = "id", value = "学校功能区id", required = true, dataType = "Long", paramType = "path")
    @DeleteMapping("schoolarea/destroy/{id}")
    @ResponseBody
    public Result destroy(@PathVariable Long id){
        boolean success = schoolareaService.delete(id);
        if(success){
            cache.delete(CacheNameHelper.Receive_CacheNamePrefix + id);
            cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.ListByUIdSId_CacheNamePrefix + "*");
            return Result.build(ResultType.Success);
        }else{
            return Result.build(ResultType.Failed);
        }
    }

    /**
     * 修改学校功能区
     * @param schoolarea
     * @return
     */
    @ApiOperation(value="根据学校功能区id修改学校功能区信息")
    @ApiImplicitParam(name="schoolarea", value = "学校功能区实体类", required = true, dataType = "Schoolarea")
    @PutMapping("schoolarea/update")
    @ResponseBody
    public Result update(@RequestBody(required = false) Schoolarea schoolarea){
        //初始化功能区
        schoolarea.setDatetime(Calendar.getInstance().getTime());
        if(schoolarea != null && schoolarea.getId() != null){
            boolean success = schoolareaService.update(schoolarea);
            if(success){
                cache.delete(CacheNameHelper.Receive_CacheNamePrefix + schoolarea.getId());
                cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListByUIdSId_CacheNamePrefix + "*");
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }
    /**
     * 获取学校功能区详情
     * @param id
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据学校功能区id获取学校功能区详情", notes = "")
    @ApiImplicitParam(name = "id", value = "学校功能区id", required = true, dataType = "Long", paramType = "path")
    @GetMapping("schoolarea/receive/{id}")
    public void receive(@PathVariable Long id,
                        HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.Receive_CacheNamePrefix + id;
        String json = cache.get(cacheName);
        if(json == null){
            Schoolarea schoolarea = schoolareaService.select(id);
            json = Result.build(ResultType.Success).appendData("schoolarea",schoolarea ).convertIntoJSON();
            if(schoolarea != null){
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    /**
     * @description: 根据功能区名称模糊查询
     * @author: 潘绍豪
     * @date: {2019/5/9} {19:55}
     */
    @ApiOperation(value = "根据功能区名称模糊查询", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name="name", value = "校区名", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping("schoolarea/listLikename/{name}/{pageNum}")
    public void listByCid(@PathVariable String name,
                          @PathVariable Integer pageNum,
                          HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByUIdSId_CacheNamePrefix + name + "_"+pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<Schoolarea> pageInfo = schoolareaService.selectPageLikeName(pageNum, name);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    /**
     * 根据校区id和页码来分页查询学校功能区
     * @param schoolId
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据校区id和页码来分页查询学校功能区", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name="schoolId", value = "校区id", required = true, dataType = "Long", paramType = "path"),
            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/schoolarea/listByUIdSId_page/{schoolId}/{pageNum}")
    public void listByCid(@PathVariable Long schoolId,
                          @PathVariable Integer pageNum,
                          HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByUIdSId_CacheNamePrefix +  + schoolId + "_"+pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<Schoolarea> pageInfo = schoolareaService.selectPageByUIdSId(pageNum,schoolId);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    /**
     * 根据校区id查询功能区，返回功能区
     * @param schoolId
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据校区id查询功能区", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name="schoolId", value = "校区id", required = true, dataType = "Long", paramType = "path")
    })
    @GetMapping(value = "/schoolarea/listBySId/{schoolId}")
    public void listBySId(@PathVariable Long schoolId,
                     HttpServletResponse response) throws IOException{
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByUIdSId_CacheNamePrefix + schoolId;
        String json = cache.get(cacheName);
        if(json == null){
            List<Schoolarea> list = schoolareaService.selectByUIdSId(schoolId);
            json = Result.build(ResultType.Success).appendData("list",list).convertIntoJSON();
            if(list != null){
                cache.set(cacheName, json);
            }
        }
        //System.out.println("system: " + json);
        response.getWriter().write(json);
    }

    /**
     * @description: 根据学校id查询功能区，返回功能区
     * @author: 潘绍豪
     * @date: {2019/5/12} {1:00}
     */
    @ApiOperation(value = "根据学校id查询功能区", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name="universityId", value = "学校id", required = true, dataType = "Long", paramType = "path")
    })
    @GetMapping(value = "/schoolarea/listByUId/{universityId}")
    public void listByUId(@PathVariable Long universityId,
                          HttpServletResponse response) throws IOException{
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByUIdSId_CacheNamePrefix + universityId;
        String json = cache.get(cacheName);
        if(json == null){
            List<Schoolarea> list = schoolareaService.selectByUId(universityId);
            json = Result.build(ResultType.Success).appendData("list",list).convertIntoJSON();
            if(list != null){
                cache.set(cacheName, json);
            }
        }
        //System.out.println("system: " + json);
        response.getWriter().write(json);
    }

    /**
     * @description: 根据学校id或者校区id来查询功能区
     * @author: 潘绍豪
     * @date: {2019/5/16} {16:51}
     */
    @ApiOperation(value = "根据学校id或者校区id来查询功能区", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name="universityId", value = "学校id", dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name="schoolId", value = "校区id",  dataType = "Long", paramType = "query"),
    })
    @GetMapping(value = "/schoolarea/selectByUIdorSId")
    public void listwithIds(@RequestParam(name = "universityId",required = false) Long universityId,
                            @RequestParam(name = "schoolId",required = false) Long schoolId,
                            HttpServletResponse response) throws IOException{
        response.setContentType("application/json;charset=utf-8");
        String cachename = CacheNameHelper.List_CacheNamePrefix;
        cachename += "_";
        if (universityId != null){
            cachename += universityId;
        }
        cachename += "_";
        if(schoolId != null){
            cachename += schoolId;
        }
        String json = cache.get(cachename);
        if(json == null){
            List<Schoolarea> list = schoolareaService.selectByUIdorSId(universityId,schoolId);
            json = Result.build(ResultType.Success).appendData("list",list).convertIntoJSON();
            if (list != null){
                cache.set(cachename , json);
            }
        }
        response.getWriter().write(json);
    }


    /**
     * 清除所有缓存
     * @return
     */
    @ApiOperation(value = "清除所有缓存", notes = "")
    @DeleteMapping("schoolarea/cache")
    @ResponseBody
    public Result destroyCache(){
        cache.deleteByPaterm(CacheNameHelper.Receive_CacheNamePrefix + "*");
        cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
        cache.deleteByPaterm(CacheNameHelper.ListByUIdSId_CacheNamePrefix + "*");
        return Result.build(ResultType.Success);
    }

}
