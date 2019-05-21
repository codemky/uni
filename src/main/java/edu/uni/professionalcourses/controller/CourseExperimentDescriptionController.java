/*
author:  邓凯丰
create:  2019.5.10
modified:  2019.5.11
*/
package edu.uni.professionalcourses.controller;

import com.github.pagehelper.PageInfo;
import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.professionalcourses.bean.CourseExperimentDescription;
import edu.uni.professionalcourses.service.CourseExperimentDescriptionService;
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

public class CourseExperimentDescriptionController {
    @Autowired
    private CourseExperimentDescriptionService courseExperimentDescriptionService;

    @Autowired
    private RedisCache cache;

    /**
     * 内部类，专门用来管理每个方法所对应缓存的名称。
     */
    static class CacheNameHelper{
        // as_d_department_{部门id}
        private static final String Receive_CacheNamePrefix = "pc_courseExperimentDescription_";
        // as_d_department_list_{页码}
        private static final String List_CacheNamePrefix = "pc_courseExperimentDescription_list_";
        // as_d_departments_listByUniversityID_{学校id}_{页码}
        private static final String ListByUniversityID_CacheNamePrefix = "pc_courseExperimentDescription_listByUniversityID_";

        // as_d_departments_listByCourseID_{部门id}_{页码}
        private static final String ListByCourseexperimentID_CacheNamePrefix = "pc_courseExperimentDescription_listByCourseexperimentID_";
        // as_d_departments_listByCourseID_{部门id}_{页码}
        private static final String ListByCoursebookID_CacheNamePrefix = "pc_courseExperimentDescription_listByCoursebookID_";
        //as_d_department_selectAll
        public static final String ListAll_CacheName = "pc_courseExperimentDescription_selectAll";
    }


    @ApiOperation(value="新增实验教学内容", notes = "已测试")
    @ApiImplicitParam(name= "courseExperimentDescription",value = "实验教学内容实体类", required = true, dataType = "CourseExperimentDescription")
    @PostMapping("courseExperimentDescription")
    @ResponseBody
    public Result create(@RequestBody(required = false) CourseExperimentDescription courseExperimentDescription){
        if(courseExperimentDescription != null){
            boolean success = courseExperimentDescriptionService.insert(courseExperimentDescription);
            if(success){
                cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName );
                cache.deleteByPaterm(CacheNameHelper.ListByUniversityID_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.Receive_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListByCoursebookID_CacheNamePrefix+"*");
                cache.deleteByPaterm(CacheNameHelper.ListByCourseexperimentID_CacheNamePrefix+"*");
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }

    /**
     * 删除实验教学内容
     * @param id
     * @return
     */
    @ApiOperation(value = "根据实验教学内容id删除实验教学内容", notes = "已测试")
    @ApiImplicitParam(name = "id", value = "实验教学内容id", required = true, dataType = "bigint", paramType = "path")
    @DeleteMapping("courseExperimentDescription/{id}")
    @ResponseBody
    public Result destroy(@PathVariable long id){
        boolean success = courseExperimentDescriptionService.delete(id);
        if(success){
            cache.delete(CacheNameHelper.Receive_CacheNamePrefix + id);
            cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.ListByUniversityID_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName );
            cache.deleteByPaterm(CacheNameHelper.ListByCoursebookID_CacheNamePrefix+"*");
            cache.deleteByPaterm(CacheNameHelper.ListByCourseexperimentID_CacheNamePrefix+"*");
            return Result.build(ResultType.Success);
        }else{
            return Result.build(ResultType.Failed);
        }
    }

    @ApiOperation(value="根据实验教学内容id修改实验教学内容信息", notes = "已测试")
    @ApiImplicitParam(name="courseExperimentDescription", value = "实验教学内容实体类", required = true, dataType = "CourseExperimentDescription")
    @PutMapping("courseExperimentDescription")
    @ResponseBody
    public Result update(@RequestBody(required = false) CourseExperimentDescription courseExperimentDescription){
        if(courseExperimentDescription != null && courseExperimentDescription.getId() != null){
            boolean success = courseExperimentDescriptionService.update(courseExperimentDescription);
            if(success){
                cache.delete(CacheNameHelper.Receive_CacheNamePrefix + courseExperimentDescription.getId());
                cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListByUniversityID_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName );
                cache.deleteByPaterm(CacheNameHelper.ListByCoursebookID_CacheNamePrefix+"*");
                cache.deleteByPaterm(CacheNameHelper.ListByCourseexperimentID_CacheNamePrefix+"*");
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }

    /**
     * 获取实验教学内容详情
     * @param id
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据实验教学内容id获取实验教学内容详情", notes = "已测试")
    @ApiImplicitParam(name = "id", value = "实验教学内容id", required = true, dataType = "bigint", paramType = "path")
    @GetMapping("courseExperimentDescription/{id}")
    public void receive(@PathVariable long id,
                        HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.Receive_CacheNamePrefix + id;
        String json = cache.get(cacheName);
        if(json == null){
            CourseExperimentDescription courseExperimentDescription= courseExperimentDescriptionService.select(id);
            json = Result.build(ResultType.Success).appendData("courseExperimentDescription", courseExperimentDescription).convertIntoJSON();
            if(courseExperimentDescription != null){
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
    @GetMapping(value = "/courseExperimentDescriptions/list/{pageNum}")
    public void list(
            @PathVariable Integer pageNum ,
            HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.List_CacheNamePrefix + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<CourseExperimentDescription> pageInfo = courseExperimentDescriptionService.selectPage(pageNum);
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
    @GetMapping(value = "/courseExperimentDescriptions/listByUniversityId/{university_id}/{pageNum}")
    public void listByUniversityID(@PathVariable long university_id,
                                   @PathVariable Integer pageNum,
                                   HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByUniversityID_CacheNamePrefix + university_id + "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<CourseExperimentDescription> pageInfo = courseExperimentDescriptionService.selectPageByUniversity(pageNum, university_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    @ApiOperation(value = "根据试验大纲id和页码来分页查询实验教学内容", notes = "已测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name="courseexperiment_id", value = "实验大纲id", required = true, dataType = "bigint", paramType = "path"),
            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/courseExperimentDescriptions/listByCourseexperimentId/{courseexperiment_id}/{pageNum}")
    public void listByCourseexperimentID(@PathVariable long courseexperiment_id,
                               @PathVariable Integer pageNum,
                               HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByCourseexperimentID_CacheNamePrefix + courseexperiment_id + "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<CourseExperimentDescription> pageInfo = courseExperimentDescriptionService.selectPageByCourseexperiment(pageNum, courseexperiment_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    @ApiOperation(value = "根据课程书籍id和页码来分页查询实验教学内容", notes = "已测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name="coursebook_id", value = "课程书籍id", required = true, dataType = "bigint", paramType = "path"),
            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/courseExperimentDescriptions/listByCoursebookId/{coursebook_id}/{pageNum}")
    public void listByCoursebookID(@PathVariable long coursebook_id,
                               @PathVariable Integer pageNum,
                               HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByCoursebookID_CacheNamePrefix + coursebook_id + "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<CourseExperimentDescription> pageInfo = courseExperimentDescriptionService.selectPageByCoursebook(pageNum, coursebook_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }
    /**
     * 列举所有实验教学内容
     * @param response
     * @throws IOException
     */
    @ApiOperation(value="列举所有实验教学内容", notes="已测试")
    @GetMapping(value = "/courseExperimentDescriptions/listAll")
    public void list(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListAll_CacheName;
        String json = cache.get(cacheName);
        if(json == null){
            json = Result.build(ResultType.Success).appendData("courseExperimentDescriptions", courseExperimentDescriptionService.selectAll()).convertIntoJSON();
            cache.set(cacheName, json);
        }
        response.getWriter().write(json);
    }
}

