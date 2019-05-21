/*
author:  邓凯丰
create:  2019.5.10
modified:  2019.5.11
*/
package edu.uni.professionalcourses.controller;

import com.github.pagehelper.PageInfo;
import edu.uni.professionalcourses.bean.CourseSyllabus;
import edu.uni.professionalcourses.service.CourseSyllabusService;
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

public class CourseSyllabusController {
    @Autowired
    private CourseSyllabusService courseSyllabusService;

    @Autowired
    private RedisCache cache;

    /**
     * 内部类，专门用来管理每个方法所对应缓存的名称。
     */
    static class CacheNameHelper{
        // as_d_department_{部门id}
        private static final String Receive_CacheNamePrefix = "pc_courseSyllabus_";
        // as_d_department_list_{页码}
        private static final String List_CacheNamePrefix = "pc_courseSyllabus_list_";
        // as_d_departments_listByUniversityID_{学校id}_{页码}
        private static final String ListByUniversityID_CacheNamePrefix = "pc_courseSyllabus_listByUniversityID_";
        // as_d_departments_listByUniversityID_{部门id}_{页码}
        private static final String ListByCourseID_CacheNamePrefix = "pc_courseSyllabus_listByCourseID_";
        //as_d_department_selectAll
        public static final String ListAll_CacheName = "pc_courseSyllabus_selectAll";
    }

    /**
     * 新增理论教学大纲
     * @param
     * @return
     */
    @ApiOperation(value="新增理论教学大纲", notes = "已测试")
    @ApiImplicitParam(name= "courseSyllabus",value = "理论教学大纲实体类", required = true, dataType = "CourseSyllabus")
    @PostMapping("courseSyllabus")
    @ResponseBody
    public Result create(@RequestBody(required = false) CourseSyllabus courseSyllabus){
        if(courseSyllabus != null){
            boolean success = courseSyllabusService.insert(courseSyllabus);
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
     * 删除理论教学大纲
     * @param id
     * @return
     */
    @ApiOperation(value = "根据理论教学大纲id删除理论教学大纲", notes = "已测试")
    @ApiImplicitParam(name = "id", value = "课程id", required = true, dataType = "bigint", paramType = "path")
    @DeleteMapping("courseSyllabus/{id}")
    @ResponseBody
    public Result destroy(@PathVariable long id){
        boolean success = courseSyllabusService.delete(id);
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
     * 修改理论教学大纲
     * @param
     * @return
     */
    @ApiOperation(value="根据理论教学大纲id修改理论教学大纲", notes = "已测试")
    @ApiImplicitParam(name="courseSyllabus", value = "理论教学大纲实体类", required = true, dataType = "CourseSyllabus")
    @PutMapping("courseSyllabus")
    @ResponseBody
    public Result update(@RequestBody(required = false) CourseSyllabus courseSyllabus){
        if(courseSyllabus != null && courseSyllabus.getId() != null){
            boolean success = courseSyllabusService.update(courseSyllabus);
            if(success){
                cache.delete(CacheNameHelper.Receive_CacheNamePrefix + courseSyllabus.getId());
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
     * 获取理论教学大纲
     * @param id
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据理论教学大纲id获取理论教学大纲详情", notes = "已测试")
    @ApiImplicitParam(name = "id", value = "理论教学大纲id", required = true, dataType = "bigint", paramType = "path")
    @GetMapping("courseSyllabus/{id}")
    public void receive(@PathVariable long id,
                        HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.Receive_CacheNamePrefix + id;
        String json = cache.get(cacheName);
        if(json == null){
            CourseSyllabus courseSyllabus= courseSyllabusService.select(id);
            json = Result.build(ResultType.Success).appendData("course", courseSyllabus).convertIntoJSON();
            if(courseSyllabus != null){
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    /**
     * 根据页码分页查询所有理论教学大纲
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据页码分页查询所有理论教学大纲", notes = "已测试")
    @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    @GetMapping(value = "/courseSyllabuss/list/{pageNum}")
    public void list(
            @PathVariable Integer pageNum ,
            HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.List_CacheNamePrefix + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<CourseSyllabus> pageInfo = courseSyllabusService.selectPage(pageNum);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    /**
     * 根据学校id和页码来分页查询理论教学大纲
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
    @GetMapping(value = "/courseSyllabuss/listByUniversityId/{university_id}/{pageNum}")
    public void listByUniversityID(@PathVariable long university_id,
                                   @PathVariable Integer pageNum,
                                   HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByUniversityID_CacheNamePrefix + university_id + "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<CourseSyllabus> pageInfo = courseSyllabusService.selectPageByUniversity(pageNum, university_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }
    /**
     * 根据课程id和页码来分页查询理论教学大纲
     * @param course_id
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据课程id和页码来分页查询课程", notes = "已测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name="course_id", value = "课程id", required = true, dataType = "bigint", paramType = "path"),
            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/courseSyllabuss/listByCourseId/{course_id}/{pageNum}")
    public void listByCourseID(@PathVariable long course_id,
                                   @PathVariable Integer pageNum,
                                   HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByUniversityID_CacheNamePrefix + course_id + "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<CourseSyllabus> pageInfo = courseSyllabusService.selectPageByCourse(pageNum, course_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }
    /**
     * 列举所有理论教学大纲
     * @param response
     * @throws IOException
     */
    @ApiOperation(value="列举所有理论教学大纲", notes="已测试")
    @GetMapping(value = "/courseSyllabuss/listAll")
    public void list(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListAll_CacheName;
        String json = cache.get(cacheName);
        if(json == null){
            json = Result.build(ResultType.Success).appendData("courseSyllabuss", courseSyllabusService.selectAll()).convertIntoJSON();
            cache.set(cacheName, json);
        }
        response.getWriter().write(json);
    }
}
