package edu.uni.place.controller;

import com.github.pagehelper.PageInfo;
import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.place.bean.School;
import edu.uni.place.service.SchoolService;
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

@Api(description = "场地基础信息")
@Controller
@RequestMapping("json/place")
public class SchoolController {
    @Autowired
    private SchoolService schoolService;

    @Autowired
    private RedisCache cache;

    /**
     * 内部类，专门用来管理每个方法所对应缓存的名称。
     */
    static class CacheNameHelper{
        // {校区id}
        private static final String Receive_CacheNamePrefix = "e_school_";
        // {页码}
        private static final String List_CacheNamePrefix = "e_school_list_";
        // {学校id}_{页码}
        private static final String ListByUId_CacheNamePrefix = "e_school_listByUId_";



    }

    /**
     * 新增校区
     * @param school
     * @return
     */
    @ApiOperation(value="新增校区", notes = "")
    @ApiImplicitParam(name= "school",value = "校区实体类", required = true, dataType = "School")
    @PostMapping("create")
    @ResponseBody
    public Result create(@RequestBody(required = false) School school){
        //初始化校区
        school.setDatetime(Calendar.getInstance().getTime());
        if(school != null){
            boolean success = schoolService.insert(school);
            if(success){
                cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
//                cache.deleteByPaterm(CacheNameHelper.ListByCid_CacheNamePrefix + example.getCid() + "*");
                cache.deleteByPaterm(CacheNameHelper.ListByUId_CacheNamePrefix + "*"); // 保险点。不知道本类别商品的插入会不会影响其他类别商品查询的次序
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }

    /**
     * 删除校区
     * @param id
     * @return
     */
    @ApiOperation(value = "根据校区id删除校区", notes = "")
    @ApiImplicitParam(name = "id", value = "校区id", required = true, dataType = "Long", paramType = "path")
    @DeleteMapping("school/destroy/{id}")
    @ResponseBody
    public Result destroy(@PathVariable Long id){
        boolean success = schoolService.delete(id);
        if(success){
            cache.delete(CacheNameHelper.Receive_CacheNamePrefix + id);
            cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.ListByUId_CacheNamePrefix + "*");
            return Result.build(ResultType.Success);
        }else{
            return Result.build(ResultType.Failed);
        }
    }

    /**
     * 修改校区
     * @param school
     * @return
     */
    @ApiOperation(value="根据校区id修改校区信息")
    @ApiImplicitParam(name="school", value = "校区实体类", required = true, dataType = "School")
    @PutMapping("school/update")
    @ResponseBody
    public Result update(@RequestBody(required = false) School school){
        //初始化校区
        school.setDatetime(Calendar.getInstance().getTime());

        if(school != null && school.getId() != null){
            boolean success = schoolService.update(school);
            if(success){
                cache.delete(CacheNameHelper.Receive_CacheNamePrefix + school.getId());
                cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListByUId_CacheNamePrefix + "*");
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }

    /**
     * 获取校区详情
     * @param id
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据校区id获取校区详情", notes = "")
    @ApiImplicitParam(name = "id", value = "校区id", required = true, dataType = "Long", paramType = "path")
    @GetMapping("school/receive/{id}")
    public void receive(@PathVariable Long id,
                        HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.Receive_CacheNamePrefix + id;
        String json = cache.get(cacheName);
        if(json == null){
            School school = schoolService.select(id);
            json = Result.build(ResultType.Success).appendData("school",school ).convertIntoJSON();
            if(school != null){
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    /**
     * @description: 根据校区名称模糊查询校区
     * @author: 潘绍豪
     * @date: {2019/5/9} {19:27}
     */
    @ApiOperation(value = "根据校区名称模糊查询", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "校区名", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping("school/listLikename/{name}/{pageNum}")
    public void selectLikeName(@PathVariable String name,@PathVariable Integer pageNum,
                        HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.Receive_CacheNamePrefix + name + "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<School> pageInfo = schoolService.selectPageLikeName(pageNum,name);
            json = Result.build(ResultType.Success).appendData("pageInfo",pageInfo ).convertIntoJSON();
            if(pageInfo != null){
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }
    /**
     * 根据学校id和页码来分页查询校区
     * @param universityId
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据学校id和页码来分页查询校区", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name="universityId", value = "学校id", required = true, dataType = "Long", paramType = "path"),
            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/school/listByUId_page/{universityId}/{pageNum}")
    public void listByUid(@PathVariable Long universityId,
                          @PathVariable Integer pageNum,
                          HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByUId_CacheNamePrefix + universityId + "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<School> pageInfo = schoolService.selectPageByUId(pageNum, universityId);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    /**
     * 根据学校id查询校区，返回校区
     * @param universityId
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据学校id查询校区，返回校区", notes = "")
    @ApiImplicitParam(name = "universityId", value = "学校id", dataType = "Long", required = true, paramType = "path")
    @GetMapping(value = "/school/listbyUId/{universityId}")
    public void list(@PathVariable Long universityId,
                     HttpServletResponse response) throws IOException{
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByUId_CacheNamePrefix + universityId;
        String json = cache.get(cacheName);
        if(json == null) {
            List<School> list = schoolService.selectByUId(universityId);
            json = Result.build(ResultType.Success).appendData("list", list).convertIntoJSON();
            if (list != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    /**
     * 清除所有缓存
     * @return
     */
    @ApiOperation(value = "清除所有缓存", notes = "")
    @DeleteMapping("schools/cache")
    @ResponseBody
    public Result destroyCache(){
        cache.deleteByPaterm(CacheNameHelper.Receive_CacheNamePrefix + "*");
        cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
        cache.deleteByPaterm(CacheNameHelper.ListByUId_CacheNamePrefix + "*");
        return Result.build(ResultType.Success);
    }



}
