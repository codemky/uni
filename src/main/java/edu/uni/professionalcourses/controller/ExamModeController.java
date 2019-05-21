/*
author:  邓凯丰
create:  2019.5.10
modified:  2019.5.11
*/
package edu.uni.professionalcourses.controller;

import com.github.pagehelper.PageInfo;
import edu.uni.professionalcourses.bean.ExamMode;
import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.professionalcourses.service.ExamModeService;
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

public class ExamModeController {
    @Autowired
    private ExamModeService examModeService;

    @Autowired
    private RedisCache cache;

    /**
     * 内部类，专门用来管理每个方法所对应缓存的名称。
     */
    static class CacheNameHelper{
        private static final String Receive_CacheNamePrefix = "pc_examMode_";
        private static final String List_CacheNamePrefix = "pc_examMode_list_";
        private static final String ListByUniversityID_CacheNamePrefix = "pc_examMode_listByUniversityID_";
        public static final String ListAll_CacheName = "pc_examMode_selectAll";
    }

    /**
     * 新增课程种类
     * @param
     * @return
     */
    @ApiOperation(value="新增考试类型", notes = "已测试")
    @ApiImplicitParam(name= "examMode",value = "考试类型实体类", required = true, dataType = "examMode")
    @PostMapping("examMode")
    @ResponseBody
    public Result create(@RequestBody(required = false) ExamMode examMode){
        if(examMode != null){
            boolean success = examModeService.insert(examMode);
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
     * 删除课程种类
     * @param id
     * @return
     */
    @ApiOperation(value = "根据考试类型id删除考试类型", notes = "已测试")
    @ApiImplicitParam(name = "id", value = "考试类型id", required = true, dataType = "bigint", paramType = "path")
    @DeleteMapping("examMode/{id}")
    @ResponseBody
    public Result destroy(@PathVariable long id){
        boolean success = examModeService.delete(id);
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

    @ApiOperation(value="根据考试类型id修改考试类型信息", notes = "已测试")
    @ApiImplicitParam(name="examMode", value = "考试类型实体类", required = true, dataType = "ExamMode")
    @PutMapping("ExamMode")
    @ResponseBody
    public Result update(@RequestBody(required = false) ExamMode examMode){
        if(examMode != null && examMode.getId() != null){
            boolean success = examModeService.update(examMode);
            if(success){
                cache.delete(CacheNameHelper.Receive_CacheNamePrefix + examMode.getId());
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
     * 获取考试类型详情
     * @param id
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据考试类型id获取考试类型详情", notes = "已测试")
    @ApiImplicitParam(name = "id", value = "考试类型id", required = true, dataType = "bigint", paramType = "path")
    @GetMapping("examMode/{id}")
    public void receive(@PathVariable long id,
                        HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.Receive_CacheNamePrefix + id;
        String json = cache.get(cacheName);
        if(json == null){
            ExamMode examMode= examModeService.select(id);
            json = Result.build(ResultType.Success).appendData("examMode", examMode).convertIntoJSON();
            if(examMode != null){
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    /**
     * 根据页码分页查询所有考试类型
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据页码分页查询所有考试类型", notes = "已测试")
    @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    @GetMapping(value = "/examModes/list/{pageNum}")
    public void list(
            @PathVariable Integer pageNum ,
            HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.List_CacheNamePrefix + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<ExamMode> pageInfo = examModeService.selectPage(pageNum);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    /**
     * 根据学校id和页码来分页查询考试类型
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
    @GetMapping(value = "/examModes/listByUniversityId/{university_id}/{pageNum}")
    public void listByUniversityID(@PathVariable long university_id,
                                   @PathVariable Integer pageNum,
                                   HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByUniversityID_CacheNamePrefix + university_id + "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<ExamMode> pageInfo = examModeService.selectPageByUniversity(pageNum, university_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }
    /**
     * 列举所有考试类型
     * @param response
     * @throws IOException
     */
    @ApiOperation(value="列举所有课程种类", notes="已测试")
    @GetMapping(value = "/examModes/listAll")
    public void list(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListAll_CacheName;
        String json = cache.get(cacheName);
        if(json == null){
            json = Result.build(ResultType.Success).appendData("courseSpeciess", examModeService.selectAll()).convertIntoJSON();
            cache.set(cacheName, json);
        }
        response.getWriter().write(json);
    }
}

