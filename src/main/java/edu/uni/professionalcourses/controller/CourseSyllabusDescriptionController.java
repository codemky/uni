/*
author:  邓凯丰
create:  2019.5.10
modified:  2019.5.11
*/
package edu.uni.professionalcourses.controller;

import com.github.pagehelper.PageInfo;
import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.professionalcourses.bean.CourseSyllabusDescription;
import edu.uni.professionalcourses.service.CourseSyllabusDescriptionService;
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

public class CourseSyllabusDescriptionController {
    @Autowired
    private CourseSyllabusDescriptionService courseSyllabusDescriptionService;

    @Autowired
    private RedisCache cache;

    /**
     * 内部类，专门用来管理每个方法所对应缓存的名称。
     */
    static class CacheNameHelper{
        // as_d_department_{部门id}
        private static final String Receive_CacheNamePrefix = "pc_courseSyllabusDescription_";
        // as_d_department_list_{页码}
        private static final String List_CacheNamePrefix = "pc_courseSyllabusDescription_list_";
        // as_d_departments_listByUniversityID_{学校id}_{页码}
        private static final String ListByUniversityID_CacheNamePrefix = "pc_courseSyllabusDescription_listByUniversityID_";

        // as_d_departments_listByCourseID_{部门id}_{页码}
        private static final String ListByCoursesyllabusID_CacheNamePrefix = "pc_courseSyllabusDescription_listByCourseexperimentID_";
        // as_d_departments_listByCourseID_{部门id}_{页码}
        private static final String ListByBookID_CacheNamePrefix = "pc_courseSyllabusDescription_listByBookID_";
        //as_d_department_selectAll
        public static final String ListAll_CacheName = "pc_courseSyllabusDescription_selectAll";
    }


    @ApiOperation(value="新增理论教学内容", notes = "已测试")
    @ApiImplicitParam(name= "courseSyllabusDescription",value = "理论教学内容实体类", required = true, dataType = "CourseSyllabusDescription")
    @PostMapping("courseSyllabusDescription")
    @ResponseBody
    public Result create(@RequestBody(required = false) CourseSyllabusDescription courseSyllabusDescription){
        if(courseSyllabusDescription != null){
            boolean success = courseSyllabusDescriptionService.insert(courseSyllabusDescription);
            if(success){
                cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName );
                cache.deleteByPaterm(CacheNameHelper.ListByUniversityID_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.Receive_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListByBookID_CacheNamePrefix+"*");
                cache.deleteByPaterm(CacheNameHelper.ListByCoursesyllabusID_CacheNamePrefix+"*");
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }

    /**
     * 删除理论教学内容
     * @param id
     * @return
     */
    @ApiOperation(value = "根据理论教学内容id删除实验教学内容", notes = "已测试")
    @ApiImplicitParam(name = "id", value = "理论教学内容id", required = true, dataType = "bigint", paramType = "path")
    @DeleteMapping("courseSyllabusDescription/{id}")
    @ResponseBody
    public Result destroy(@PathVariable long id){
        boolean success = courseSyllabusDescriptionService.delete(id);
        if(success){
            cache.delete(CacheNameHelper.Receive_CacheNamePrefix + id);
            cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.ListByUniversityID_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName );
            cache.deleteByPaterm(CacheNameHelper.ListByBookID_CacheNamePrefix+"*");
            cache.deleteByPaterm(CacheNameHelper.ListByCoursesyllabusID_CacheNamePrefix+"*");
            return Result.build(ResultType.Success);
        }else{
            return Result.build(ResultType.Failed);
        }
    }

    @ApiOperation(value="根据理论教学内容id修改理论教学内容信息", notes = "已测试")
    @ApiImplicitParam(name="courseSyllabusDescription", value = "理论教学内容实体类", required = true, dataType = "CourseSyllabusDescription")
    @PutMapping("courseSyllabusDescription")
    @ResponseBody
    public Result update(@RequestBody(required = false) CourseSyllabusDescription courseSyllabusDescription){
        if(courseSyllabusDescription != null && courseSyllabusDescription.getId() != null){
            boolean success = courseSyllabusDescriptionService.update(courseSyllabusDescription);
            if(success){
                cache.delete(CacheNameHelper.Receive_CacheNamePrefix + courseSyllabusDescription.getId());
                cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListByUniversityID_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName );
                cache.deleteByPaterm(CacheNameHelper.ListByBookID_CacheNamePrefix+"*");
                cache.deleteByPaterm(CacheNameHelper.ListByCoursesyllabusID_CacheNamePrefix+"*");
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }

    /**
     * 获取理论教学内容详情
     * @param id
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据理论教学内容id获取理论教学内容详情", notes = "已测试")
    @ApiImplicitParam(name = "id", value = "理论教学内容id", required = true, dataType = "bigint", paramType = "path")
    @GetMapping("courseSyllabusDescription/{id}")
    public void receive(@PathVariable long id,
                        HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.Receive_CacheNamePrefix + id;
        String json = cache.get(cacheName);
        if(json == null){
            CourseSyllabusDescription courseSyllabusDescription= courseSyllabusDescriptionService.select(id);
            json = Result.build(ResultType.Success).appendData("courseSyllabusDescription", courseSyllabusDescription).convertIntoJSON();
            if(courseSyllabusDescription != null){
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    /**
     * 根据页码分页查询所有实验教学内容
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据页码分页查询所有实验教学内容", notes = "已测试")
    @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    @GetMapping(value = "/courseSyllabusDescriptions/list/{pageNum}")
    public void list(
            @PathVariable Integer pageNum ,
            HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.List_CacheNamePrefix + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<CourseSyllabusDescription> pageInfo = courseSyllabusDescriptionService.selectPage(pageNum);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    @ApiOperation(value = "根据学校id和页码来分页查询实验教学内容", notes = "已测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name="university_id", value = "学校id", required = true, dataType = "bigint", paramType = "path"),
            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/courseSyllabusDescriptions/listByUniversityId/{university_id}/{pageNum}")
    public void listByUniversityID(@PathVariable long university_id,
                                   @PathVariable Integer pageNum,
                                   HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByUniversityID_CacheNamePrefix + university_id + "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<CourseSyllabusDescription> pageInfo = courseSyllabusDescriptionService.selectPageByUniversity(pageNum, university_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    @ApiOperation(value = "根据试验大纲id和页码来分页查询实验教学内容", notes = "已测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name="coursesyllabus_id", value = "实验大纲id", required = true, dataType = "bigint", paramType = "path"),
            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/courseSyllabusDescriptions/listByCoursesyllabusId/{coursesyllabus_id}/{pageNum}")
    public void listByCourseexperimentID(@PathVariable long coursesyllabus_id,
                                         @PathVariable Integer pageNum,
                                         HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByCoursesyllabusID_CacheNamePrefix + coursesyllabus_id + "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<CourseSyllabusDescription> pageInfo = courseSyllabusDescriptionService.selectPageByCoursesyllabus(pageNum, coursesyllabus_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    @ApiOperation(value = "根据书籍id和页码来分页查询实验教学内容", notes = "已测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name="book_id", value = "书籍id", required = true, dataType = "bigint", paramType = "path"),
            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/courseSyllabusDescriptions/listByCoursebookId/{book_id}/{pageNum}")
    public void listByCoursebookID(@PathVariable long book_id,
                                   @PathVariable Integer pageNum,
                                   HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByBookID_CacheNamePrefix + book_id + "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<CourseSyllabusDescription> pageInfo = courseSyllabusDescriptionService.selectPageByBook(pageNum, book_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }
    /**
     * 列举所有理论教学内容
     * @param response
     * @throws IOException
     */
    @ApiOperation(value="列举所有理论教学内容", notes="已测试")
    @GetMapping(value = "/courseSyllabusDescriptions/listAll")
    public void list(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListAll_CacheName;
        String json = cache.get(cacheName);
        if(json == null){
            json = Result.build(ResultType.Success).appendData("courseSyllabusDescriptions", courseSyllabusDescriptionService.selectAll()).convertIntoJSON();
            cache.set(cacheName, json);
        }
        response.getWriter().write(json);
    }
}


