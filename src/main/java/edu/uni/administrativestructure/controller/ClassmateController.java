package edu.uni.administrativestructure.controller;

import com.github.pagehelper.PageInfo;
import edu.uni.administrativestructure.bean.Classmate;
import edu.uni.administrativestructure.service.ClassmateService;
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

/**
 * author：黄育林
 * create: 2019.4.25
 * modified:2019.5.9
 */

@Api(description = "行政架构模块")
@Controller
@RequestMapping("json/administrativestructure")
public class ClassmateController {

    @Autowired
    private ClassmateService classmateService;

    @Autowired
    private RedisCache cache;

    /**
     * 内部类，专门用来管理每个方法所对应缓存的名称。
     */
    static class CacheNameHelper{
        private static final String Receive_CacheNamePrefix = "as_classmate_";
        private static final String List_CacheNamePrefix = "as_classmate_list_";
        private static final String ListByClassID_CacheNamePrefix = "as_classmate_listByClassID_";
        private static final String ListByUniversityID_CacheNamePrefix = "as_classmate_listByUniversityID_";
        private static final String ListByStudentID_CacheNamePrefix = "as_classmate_listByStudentID_";
        public static final String ListAll_CacheName = "as_classmate_listAll";
    }

    /**
     * 新增班级人员记录
     * @param classmate
     * @return
     */
    @ApiOperation(value="新增班级人员记录", notes = "已测试")
    @ApiImplicitParam(name= "classmate",value = "班级人员记录实体类", required = true, dataType = "Classmate")
    @PostMapping("classmate")
    @ResponseBody
    public Result create(@RequestBody(required = false) Classmate classmate){
        if(classmate != null){
            boolean success = classmateService.insert(classmate);
            if(success){
                cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName + "*");
                cache.deleteByPaterm(CacheNameHelper.ListByClassID_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListByUniversityID_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListByStudentID_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.Receive_CacheNamePrefix + "*");
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }

    /**
     * 删除班级人员记录
     * @param id
     * @return
     */
    @ApiOperation(value = "根据班级人员记录id删除班级人员记录", notes = "已测试")
    @ApiImplicitParam(name = "id", value = "班级人员记录id", required = true, dataType = "bigint", paramType = "path")
    @DeleteMapping("classmate/{id}")
    @ResponseBody
    public Result destroy(@PathVariable long id){
        boolean success = classmateService.delete(id);
        if(success){
            cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName + "*");
            cache.deleteByPaterm(CacheNameHelper.ListByClassID_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.ListByUniversityID_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.ListByStudentID_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.Receive_CacheNamePrefix + "*");
            return Result.build(ResultType.Success);
        }else{
            return Result.build(ResultType.Failed);
        }
    }
    /**
     * 修改班级人员记录
     * @param classmate
     * @return
     */
    @ApiOperation(value="根据班级人员记录id修改班级人员记录信息", notes = "已测试")
    @ApiImplicitParam(name="classmate", value = "班级人员记录实体类", required = true, dataType = "Classmate")
    @PutMapping("classmate")
    @ResponseBody
    public Result update(@RequestBody(required = false) Classmate classmate){
        if(classmate != null && classmate.getId() != null){
            boolean success = classmateService.update(classmate);
            if(success){
                cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName + "*");
                cache.deleteByPaterm(CacheNameHelper.ListByClassID_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListByUniversityID_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListByStudentID_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.Receive_CacheNamePrefix + "*");
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }

    /**
     * 获取班级人员记录详情
     * @param id
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据班级人员记录id获取班级人员记录详情", notes = "已测试")
    @ApiImplicitParam(name = "id", value = "班级人员记录id", required = true, dataType = "bigint", paramType = "path")
    @GetMapping("classmate/{id}")
    public void receive(@PathVariable long id,
                        HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.Receive_CacheNamePrefix + id;
        String json = cache.get(cacheName);
        if(json == null){
            Classmate classmate= classmateService.select(id);
            json = Result.build(ResultType.Success).appendData("classmate", classmate).convertIntoJSON();
            if(classmate != null){
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    /**
     * 根据页码分页查询所有班级人员记录
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据页码分页查询所有班级人员记录", notes = "已测试")
    @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    @GetMapping(value = "/classmates/list/{pageNum}")
    public void list(
            @PathVariable Integer pageNum ,
            HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.List_CacheNamePrefix + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<Classmate> pageInfo = classmateService.selectPage(pageNum);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    /**
     * 根据班级id和页码来分页查询班级人员记录
     * @param class_id
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据班级id和页码来分页查询啊班级人员记录", notes = "已测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name="class_id", value = "班级id", required = true, dataType = "bigint", paramType = "path"),
            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/classmates/listByClassID/{class_id}/{pageNum}")
    public void listByClassID(@PathVariable long class_id,
                                   @PathVariable Integer pageNum,
                                   HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByClassID_CacheNamePrefix + class_id + "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<Classmate> pageInfo = classmateService.selectPageByClass(pageNum, class_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    /**
     * 根据学生id和页码来分页查询班级人员记录
     * @param student_id
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据学生id和页码来分页查询班级人员记录", notes = "已测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name="student_id", value = "学生id", required = true, dataType = "bigint", paramType = "path"),
            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/classmates/listByStudentID/{student_id}/{pageNum}")
    public void listByStudentID(@PathVariable long student_id,
                                   @PathVariable Integer pageNum,
                                   HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByStudentID_CacheNamePrefix + student_id + "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<Classmate> pageInfo = classmateService.selectPageByStudent(pageNum, student_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    /**
     * 根据学校id和页码来分页查询班级人员记录
     * @param university_id
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据学校id和页码来分页查询班级人员记录", notes = "已测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name="university_id", value = "学校id", required = true, dataType = "bigint", paramType = "path"),
            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/classmates/listByUniversityID/{university_id}/{pageNum}")
    public void listByUniversityID(@PathVariable long university_id,
                                   @PathVariable Integer pageNum,
                                   HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByUniversityID_CacheNamePrefix + university_id + "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<Classmate> pageInfo = classmateService.selectPageByUniversity(pageNum, university_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    /**
     * 列举所有班级人员记录
     * @param response
     * @throws IOException
     */
    @ApiOperation(value="列举所有班级人员记录", notes="已测试")
    @GetMapping(value = "/classmates/listAll")
    public void list(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListAll_CacheName;
        String json = cache.get(cacheName);
        if(json == null){
            json = Result.build(ResultType.Success).appendData("employs", classmateService.selectAll()).convertIntoJSON();
            cache.set(cacheName, json);
        }
        response.getWriter().write(json);
    }

}
