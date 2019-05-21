/*
author:  邓凯丰
create:  2019.5.10
modified:  2019.5.11
*/
package edu.uni.professionalcourses.controller;

import com.github.pagehelper.PageInfo;
import edu.uni.professionalcourses.bean.CourseCategory;
import edu.uni.professionalcourses.service.CourseCategoryService;
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

public class CourseCategoryController {
    @Autowired
    private CourseCategoryService courseCategoryService;

    @Autowired
    private RedisCache cache;

    /**
     * 内部类，专门用来管理每个方法所对应缓存的名称。
     */
    static class CacheNameHelper{
        // as_d_department_{部门id}
        private static final String Receive_CacheNamePrefix = "pc_courseCategory_";
        // as_d_department_list_{页码}
        private static final String List_CacheNamePrefix = "pc_courseCategory_list_";
        // as_d_departments_listByUniversityID_{学校id}_{页码}
        private static final String ListByUniversityID_CacheNamePrefix = "pc_courseCategory_listByUniversityID_";
        //as_d_department_selectAll
        public static final String ListAll_CacheName = "pc_courseCategory_selectAll";
    }

    /**
     * 新增课程类别
     * @param department
     * @return
     */
    @ApiOperation(value="新增类别课程", notes = "已测试")
    @ApiImplicitParam(name= "courseCategory",value = "课程类别实体类", required = true, dataType = "CourseCategory")
    @PostMapping("courseCategory")
    @ResponseBody
    public Result create(@RequestBody(required = false) CourseCategory courseCategory){
        if(courseCategory != null){
            boolean success = courseCategoryService.insert(courseCategory);
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
    @ApiOperation(value = "根据课程类别id删除课程类别", notes = "已测试")
    @ApiImplicitParam(name = "id", value = "课程类别id", required = true, dataType = "bigint", paramType = "path")
    @DeleteMapping("courseCategory/{id}")
    @ResponseBody
    public Result destroy(@PathVariable long id){
        boolean success = courseCategoryService.delete(id);
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
    @ApiOperation(value="根据课程类别id修改课程类别信息", notes = "已测试")
    @ApiImplicitParam(name="courseCategory", value = "课程类别实体类", required = true, dataType = "CourseCategory")
    @PutMapping("courseCategory")
    @ResponseBody
    public Result update(@RequestBody(required = false) CourseCategory courseCategory){
        if(courseCategory != null && courseCategory.getId() != null){
            boolean success = courseCategoryService.update(courseCategory);
            if(success){
                cache.delete(CacheNameHelper.Receive_CacheNamePrefix + courseCategory.getId());
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
    @ApiOperation(value = "根据课程类别id获取课程类别详情", notes = "已测试")
    @ApiImplicitParam(name = "id", value = "课程类别id", required = true, dataType = "bigint", paramType = "path")
    @GetMapping("courseCategory/{id}")
    public void receive(@PathVariable long id,
                        HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.Receive_CacheNamePrefix + id;
        String json = cache.get(cacheName);
        if(json == null){
            CourseCategory courseCategory= courseCategoryService.select(id);
            json = Result.build(ResultType.Success).appendData("courseCategory", courseCategory).convertIntoJSON();
            if(courseCategory != null){
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
    @ApiOperation(value = "根据页码分页查询所有课程类别", notes = "已测试")
    @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    @GetMapping(value = "/courseCategorys/list/{pageNum}")
    public void list(
            @PathVariable Integer pageNum ,
            HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.List_CacheNamePrefix + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<CourseCategory> pageInfo = courseCategoryService.selectPage(pageNum);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    /**
     * 根据学校id和页码来分页查询课程类别
     * @param university_id
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据学校id和页码来分页查询课程类别", notes = "已测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name="university_id", value = "学校id", required = true, dataType = "bigint", paramType = "path"),
            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/courseCategorys/listByUniversityId/{university_id}/{pageNum}")
    public void listByUniversityID(@PathVariable long university_id,
                                   @PathVariable Integer pageNum,
                                   HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByUniversityID_CacheNamePrefix + university_id + "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<CourseCategory> pageInfo = courseCategoryService.selectPageByUniversity(pageNum, university_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

}