/*
author:  邓凯丰
create:  2019.5.10
modified:  2019.5.11
*/
package edu.uni.professionalcourses.controller;

import com.github.pagehelper.PageInfo;
import edu.uni.professionalcourses.bean.Course;
import edu.uni.professionalcourses.service.CourseService;
import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
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

public class CourseController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private RedisCache cache;

    /**
     * 内部类，专门用来管理每个方法所对应缓存的名称。
     */
    static class CacheNameHelper{
        // as_d_department_{部门id}
        private static final String Receive_CacheNamePrefix = "pc_course_";
        // as_d_department_list_{页码}
        private static final String List_CacheNamePrefix = "pc_course_list_";
        // as_d_departments_listByUniversityID_{学校id}_{页码}
        private static final String ListByUniversityID_CacheNamePrefix = "pc_course_listByUniversityID_";
        //as_d_department_selectAll
        public static final String ListAll_CacheName = "pc_course_selectAll";
    }

    /**
     * 新增课程
     * @param department
     * @return
     */
    @ApiOperation(value="新增课程", notes = "已测试")
    @ApiImplicitParam(name= "course",value = "课程实体类", required = true, dataType = "Course")
    @PostMapping("course")
    @ResponseBody
    public Result create(@RequestBody(required = false) Course course){
        if(course != null){
            boolean success = courseService.insert(course);
            if(success){
                cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName );
                cache.deleteByPaterm(CacheNameHelper.ListByUniversityID_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.Receive_CacheNamePrefix + "*");
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }

    /**
     * 删除课程
     * @param id
     * @return
     */
    @ApiOperation(value = "根据课程id删除课程", notes = "已测试")
    @ApiImplicitParam(name = "id", value = "课程id", required = true, dataType = "bigint", paramType = "path")
    @DeleteMapping("course/{id}")
    @ResponseBody
    public Result destroy(@PathVariable long id){
        boolean success = courseService.delete(id);
        if(success){
            cache.delete(CacheNameHelper.Receive_CacheNamePrefix + id);
            cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.ListByUniversityID_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName );
            return Result.build(ResultType.Success);
        }else{
            return Result.build(ResultType.Failed);
        }
    }
    /**
     * 修改课程
     * @param department
     * @return
     */
    @ApiOperation(value="根据课程id修改课程信息", notes = "已测试")
    @ApiImplicitParam(name="course", value = "课程实体类", required = true, dataType = "Course")
    @PutMapping("course")
    @ResponseBody
    public Result update(@RequestBody(required = false) Course course){
        if(course != null && course.getId() != null){
            boolean success = courseService.update(course);
            if(success){
                cache.delete(CacheNameHelper.Receive_CacheNamePrefix + course.getId());
                cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListByUniversityID_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName );
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }

    /**
     * 获取课程详情
     * @param id
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据课程id获取课程详情", notes = "已测试")
    @ApiImplicitParam(name = "id", value = "课程id", required = true, dataType = "bigint", paramType = "path")
    @GetMapping("course/{id}")
    public void receive(@PathVariable long id,
                        HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.Receive_CacheNamePrefix + id;
        String json = cache.get(cacheName);
        if(json == null){
            Course course= courseService.select(id);
            json = Result.build(ResultType.Success).appendData("course", course).convertIntoJSON();
            if(course != null){
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
    @ApiOperation(value = "根据页码分页查询所有课程", notes = "已测试")
    @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    @GetMapping(value = "/courses/list/{pageNum}")
    public void list(
            @PathVariable Integer pageNum ,
            HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.List_CacheNamePrefix + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<Course> pageInfo = courseService.selectPage(pageNum);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    /**
     * 根据学校id和页码来分页查询课程
     * @param university_id
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据学校id和页码来分页查询课程", notes = "已测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name="university_id", value = "学校id", required = true, dataType = "bigint", paramType = "path"),
            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/courses/listByUniversityId/{university_id}/{pageNum}")
    public void listByUniversityID(@PathVariable long university_id,
                                   @PathVariable Integer pageNum,
                                   HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByUniversityID_CacheNamePrefix + university_id + "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<Course> pageInfo = courseService.selectPageByUniversity(pageNum, university_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    @ApiOperation(value = "根据number获取课程详情", notes = "已测试")
    @ApiImplicitParam(name = "number", value = "课程number", required = true, dataType = "String", paramType = "path")
    @GetMapping("courseBynumber/{number}")
    public void receive(@PathVariable String number,
                        HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.Receive_CacheNamePrefix + number;
        String json = cache.get(cacheName);
        if(json == null){
            Course course= courseService.selectBynumber(number);
            json = Result.build(ResultType.Success).appendData("course", course).convertIntoJSON();
            if(course != null){
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

}