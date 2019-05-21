/*
author:  邓凯丰
create:  2019.5.10
modified:  2019.5.11
*/
package edu.uni.professionalcourses.controller;

import com.github.pagehelper.PageInfo;
import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.professionalcourses.bean.CourseHasResource;
import edu.uni.professionalcourses.service.CourseHasResourceService;
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

@Api(description = "专业课程模块")
@Controller
@RequestMapping("json/professionalCourses")
/**
 * author : 邓凯丰
 * create : 2019/4/19 0023 15:34
 * modified :
 * 功能 :控制对课程表的请求
 **/
public class CourseHasResourceController {
    @Autowired
    private CourseHasResourceService courseHasResourceService;

    @Autowired
    private RedisCache cache;

    /**
     * 内部类，专门用来管理每个方法所对应缓存的名称。
     */
    static class CacheNameHelper{
        // as_d_department_{部门id}
        private static final String Receive_CacheNamePrefix = "pc_courseHasResource_";
        // as_d_department_list_{页码}
        private static final String List_CacheNamePrefix = "pc_courseHasResource_list_";
        // as_d_departments_listByUniversityID_{学校id}_{页码}
        private static final String ListByUniversityID_CacheNamePrefix = "pc_courseHasResource_listByUniversityID_";
        // as_d_departments_listByCourseID_{部门id}_{页码}
        private static final String ListByCourseID_CacheNamePrefix = "pc_courseHasResource_listByCourseID_";
        // as_d_departments_listByCourseID_{部门id}_{页码}
        private static final String ListByCourseresourceID_CacheNamePrefix = "pc_courseHasResource_listByCourseresourceID_";
        //as_d_department_selectAll
        public static final String ListAll_CacheName = "pc_courseHasResource_selectAll";
    }


    @ApiOperation(value="新增课程资源对应表", notes = "已测试")
    @ApiImplicitParam(name= "courseHasResource",value = "课程资源对应表实体类", required = true, dataType = "CourseHasResource")
    @PostMapping("courseHasResource")
    @ResponseBody
    public Result create(@RequestBody(required = false) CourseHasResource courseHasResource){
        if(courseHasResource != null){
            boolean success = courseHasResourceService.insert(courseHasResource);
            if(success){
                cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName );
                cache.deleteByPaterm(CacheNameHelper.ListByUniversityID_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.Receive_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListByCourseID_CacheNamePrefix+"*");
                cache.deleteByPaterm(CacheNameHelper.ListByCourseresourceID_CacheNamePrefix+"*");
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }

    /**
     * 删除课程资源对应表
     * @param id
     * @return
     */
    @ApiOperation(value = "根据课程资源对应表id删除课程资源对应表", notes = "已测试")
    @ApiImplicitParam(name = "id", value = "课程资源对应表id", required = true, dataType = "bigint", paramType = "path")
    @DeleteMapping("courseHasResource/{id}")
    @ResponseBody
    public Result destroy(@PathVariable long id){
        boolean success = courseHasResourceService.delete(id);
        if(success){
            cache.delete(CacheNameHelper.Receive_CacheNamePrefix + id);
            cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.ListByUniversityID_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName );
            cache.deleteByPaterm(CacheNameHelper.ListByCourseID_CacheNamePrefix+"*");
            cache.deleteByPaterm(CacheNameHelper.ListByCourseresourceID_CacheNamePrefix+"*");
            return Result.build(ResultType.Success);
        }else{
            return Result.build(ResultType.Failed);
        }
    }

    @ApiOperation(value="根据课程资源对应表id修改课程资源对应表信息", notes = "已测试")
    @ApiImplicitParam(name="courseHasResource", value = "课程资源对应表实体类", required = true, dataType = "CourseHasResource")
    @PutMapping("courseHasResource")
    @ResponseBody
    public Result update(@RequestBody(required = false) CourseHasResource courseHasResource){
        if(courseHasResource != null && courseHasResource.getId() != null){
            boolean success = courseHasResourceService.update(courseHasResource);
            if(success){
                cache.delete(CacheNameHelper.Receive_CacheNamePrefix + courseHasResource.getId());
                cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListByUniversityID_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName );
                cache.deleteByPaterm(CacheNameHelper.ListByCourseID_CacheNamePrefix+"*");
                cache.deleteByPaterm(CacheNameHelper.ListByCourseresourceID_CacheNamePrefix+"*");
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }

    /**
     * 获取课程资源对应表详情
     * @param id
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据课程资源对应表id获取课程详情", notes = "已测试")
    @ApiImplicitParam(name = "id", value = "课程资源对应表id", required = true, dataType = "bigint", paramType = "path")
    @GetMapping("courseHasResource/{id}")
    public void receive(@PathVariable long id,
                        HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.Receive_CacheNamePrefix + id;
        String json = cache.get(cacheName);
        if(json == null){
            CourseHasResource courseHasResource= courseHasResourceService.select(id);
            json = Result.build(ResultType.Success).appendData("coursenHasResource", courseHasResource).convertIntoJSON();
            if(courseHasResource != null){
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    /**
     * 根据页码分页查询所有课程
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据页码分页查询所有课程资源对应表", notes = "已测试")
    @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    @GetMapping(value = "/courseHasResources/list/{pageNum}")
    public void list(
            @PathVariable Integer pageNum ,
            HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.List_CacheNamePrefix + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<CourseHasResource> pageInfo = courseHasResourceService.selectPage(pageNum);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    @ApiOperation(value = "根据学校id和页码来分页查询课程资源对应表", notes = "已测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name="university_id", value = "学校id", required = true, dataType = "bigint", paramType = "path"),
            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/courseHasResources/listByUniversityId/{university_id}/{pageNum}")
    public void listByUniversityID(@PathVariable long university_id,
                                   @PathVariable Integer pageNum,
                                   HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByUniversityID_CacheNamePrefix + university_id + "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<CourseHasResource> pageInfo = courseHasResourceService.selectPageByUniversity(pageNum, university_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    @ApiOperation(value = "根据课程id和页码来分页查询课程资源对应表", notes = "已测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name="course_id", value = "课程id", required = true, dataType = "bigint", paramType = "path"),
            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/courseHasResources/listByCourseId/{course_id}/{pageNum}")
    public void listByCourseexperimentID(@PathVariable long course_id,
                                         @PathVariable Integer pageNum,
                                         HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByCourseID_CacheNamePrefix + course_id + "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<CourseHasResource> pageInfo = courseHasResourceService.selectPageByCourse(pageNum, course_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    @ApiOperation(value = "根据课程资源id和页码来分页查询课程资源对应表", notes = "已测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name="courseresource_id", value = "课程资源id", required = true, dataType = "bigint", paramType = "path"),
            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/courseHasResources/listByCourseresourceId/{courseresource_id}/{pageNum}")
    public void listByCoursebookID(@PathVariable long courseresource_id,
                                   @PathVariable Integer pageNum,
                                   HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByCourseresourceID_CacheNamePrefix + courseresource_id + "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<CourseHasResource> pageInfo = courseHasResourceService.selectPageByCourseresource(pageNum, courseresource_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }
    /**
     * 列举所有课程资源对应表
     * @param response
     * @throws IOException
     */
    @ApiOperation(value="列举所有课程资源对应表", notes="已测试")
    @GetMapping(value = "/courseHasResources/listAll")
    public void list(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListAll_CacheName;
        String json = cache.get(cacheName);
        if(json == null){
            json = Result.build(ResultType.Success).appendData("courseHasResources", courseHasResourceService.selectAll()).convertIntoJSON();
            cache.set(cacheName, json);
        }
        response.getWriter().write(json);
    }
}

