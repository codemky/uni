/*
author:  邓凯丰
create:  2019.5.10
modified:  2019.5.11
*/
package edu.uni.professionalcourses.controller;

import com.github.pagehelper.PageInfo;
import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.professionalcourses.bean.CourseClassification;
import edu.uni.professionalcourses.service.CourseClassificationService;
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

public class CourseClassificationController {
    @Autowired
    private CourseClassificationService courseClassificationService;

    @Autowired
    private RedisCache cache;

    /**
     * 内部类，专门用来管理每个方法所对应缓存的名称。
     */
    static class CacheNameHelper{
        // as_d_department_{部门id}
        private static final String Receive_CacheNamePrefix = "pc_courseClassification_";
        // as_d_department_list_{页码}
        private static final String List_CacheNamePrefix = "pc_courseClassification_list_";
        // as_d_departments_listByUniversityID_{学校id}_{页码}
        private static final String ListByUniversityID_CacheNamePrefix = "pc_courseClassification_listByUniversityID_";

        // as_d_departments_listByCourseID_{部门id}_{页码}
        private static final String ListByCourseID_CacheNamePrefix = "pc_courseClassification_listByCourseID_";
        //as_d_department_selectAll
        public static final String ListAll_CacheName = "pc_courseClassification_selectAll";
    }

    /**
     * 新增课程分类
     * @param
     * @return
     */
    @ApiOperation(value="新增分类课程", notes = "已测试")
    @ApiImplicitParam(name= "courseClassification",value = "课程分类实体类", required = true, dataType = "CourseClassification")
    @PostMapping("courseClassification")
    @ResponseBody
    public Result create(@RequestBody(required = false) CourseClassification courseClassification){
        if(courseClassification != null){
            boolean success = courseClassificationService.insert(courseClassification);
            if(success){
                cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName );
                cache.deleteByPaterm(CacheNameHelper.ListByUniversityID_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.Receive_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListByCourseID_CacheNamePrefix+"*");
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }

    /**
     * 删除课程分类
     * @param id
     * @return
     */
    @ApiOperation(value = "根据课程分类id删除课程分类", notes = "已测试")
    @ApiImplicitParam(name = "id", value = "课程分类id", required = true, dataType = "bigint", paramType = "path")
    @DeleteMapping("courseClassification/{id}")
    @ResponseBody
    public Result destroy(@PathVariable long id){
        boolean success = courseClassificationService.delete(id);
        if(success){
            cache.delete(CacheNameHelper.Receive_CacheNamePrefix + id);
            cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.ListByUniversityID_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName );
            cache.deleteByPaterm(CacheNameHelper.ListByCourseID_CacheNamePrefix+"*");
            return Result.build(ResultType.Success);
        }else{
            return Result.build(ResultType.Failed);
        }
    }
    /**
     * 修改课程分类
     * @param
     * @return
     */
    @ApiOperation(value="根据课程分类id修改课程分类信息", notes = "已测试")
    @ApiImplicitParam(name="courseClassification", value = "课程分类实体类", required = true, dataType = "CourseClassification")
    @PutMapping("courseClassification")
    @ResponseBody
    public Result update(@RequestBody(required = false) CourseClassification courseClassification){
        if(courseClassification != null && courseClassification.getId() != null){
            boolean success = courseClassificationService.update(courseClassification);
            if(success){
                cache.delete(CacheNameHelper.Receive_CacheNamePrefix + courseClassification.getId());
                cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListByUniversityID_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName );
                cache.deleteByPaterm(CacheNameHelper.ListByCourseID_CacheNamePrefix+"*");
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }

    /**
     * 获取课程分类详情
     * @param id
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据课程分类id获取课程分类详情", notes = "已测试")
    @ApiImplicitParam(name = "id", value = "课程分类id", required = true, dataType = "bigint", paramType = "path")
    @GetMapping("courseClassification/{id}")
    public void receive(@PathVariable long id,
                        HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.Receive_CacheNamePrefix + id;
        String json = cache.get(cacheName);
        if(json == null){
            CourseClassification courseClassification= courseClassificationService.select(id);
            json = Result.build(ResultType.Success).appendData("courseClassification", courseClassification).convertIntoJSON();
            if(courseClassification != null){
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    /**
     * 根据页码分页查询所有课程分类
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据页码分页查询所有课程分类", notes = "已测试")
    @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    @GetMapping(value = "/courseClassifications/list/{pageNum}")
    public void list(
            @PathVariable Integer pageNum ,
            HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.List_CacheNamePrefix + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<CourseClassification> pageInfo = courseClassificationService.selectPage(pageNum);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    /**
     * 根据学校id和页码来分页查询课程分类
     * @param university_id
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据学校id和页码来分页查询课程分类", notes = "已测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name="university_id", value = "学校id", required = true, dataType = "bigint", paramType = "path"),
            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/courseClassifications/listByUniversityId/{university_id}/{pageNum}")
    public void listByUniversityID(@PathVariable long university_id,
                                   @PathVariable Integer pageNum,
                                   HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByUniversityID_CacheNamePrefix + university_id + "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<CourseClassification> pageInfo = courseClassificationService.selectPageByUniversity(pageNum, university_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }


    /**
     * 列举所有课程
     * @param response
     * @throws IOException
     */
    @ApiOperation(value="列举所有课程分类", notes="已测试")
    @GetMapping(value = "/courseClassifications/listAll")
    public void list(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListAll_CacheName;
        String json = cache.get(cacheName);
        if(json == null){
            json = Result.build(ResultType.Success).appendData("courseClassifications", courseClassificationService.selectAll()).convertIntoJSON();
            cache.set(cacheName, json);
        }
        response.getWriter().write(json);
    }
}
