package edu.uni.administrativestructure.controller;

import com.github.pagehelper.PageInfo;
import edu.uni.administrativestructure.bean.Subdepartment;
import edu.uni.administrativestructure.service.SubdepartmentService;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * author：黄育林
 * create: 2019.4.20
 * modified: 2019.5.7
 */

@Api(description = "行政架构模块")
@Controller
@RequestMapping("json/administrativestructure")
public class SubdepartmentController {
    @Autowired
    private SubdepartmentService subdepartmentService;

    @Autowired
    private RedisCache cache;

    /**
     * 内部类，专门用来管理每个方法所对应缓存的名称。
     */
    static class CacheNameHelper{
        // as_sub_subdepartment_{三级部门id}
        private static final String Receive_CacheNamePrefix = "as_subdepartment_";
        // as_sub_subdepartment_list_{页码}
        private static final String List_CacheNamePrefix = "as_subdepartment_list_";
        // as_sub_subdepartments_listByDepartmentID_{部门id}_{页码}
        private static final String ListByDepartmentID_CacheNamePrefix = "as_subdepartment_listByDepartmentID_";
        private static final String ListByUniversityID_CacheNamePrefix = "as_subdepartment_listByUniversityID_";
        public static final String ListAll_CacheName = "as_subdepartment_listAll";
    }

    /**
     * 新增三级部门
     * @param subdepartment
     * @return
     */
    @ApiOperation(value="新增三级部门", notes = "已测试")
    @ApiImplicitParam(name= "subdepartment",value = "三级部门实体类", required = true, dataType = "Subdepartment")
    @PostMapping("subdepartment")
    @ResponseBody
    public Result create(@RequestBody(required = false) Subdepartment subdepartment){
        if(subdepartment != null){
            boolean success = subdepartmentService.insert(subdepartment);
            if(success){
                cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName);
                cache.deleteByPaterm(CacheNameHelper.ListByDepartmentID_CacheNamePrefix + "*");
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
     * 删除三级部门
     * @param id
     * @return
     */
    @ApiOperation(value = "根据三级部门id删除部门", notes = "已测试")
    @ApiImplicitParam(name = "id", value = "三级部门id", required = true, dataType = "bigint", paramType = "path")
    @DeleteMapping("subdepartment/{id}")
    @ResponseBody
    public Result destroy(@PathVariable long id){
        boolean success = subdepartmentService.delete(id);
        if(success){
            cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName);
            cache.deleteByPaterm(CacheNameHelper.ListByDepartmentID_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.ListByUniversityID_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.Receive_CacheNamePrefix + "*");
            return Result.build(ResultType.Success);
        }else{
            return Result.build(ResultType.Failed);
        }
    }
    /**
     * 修改三级部门
     * @param subdepartment
     * @return
     */
    @ApiOperation(value="根据三级部门id修改三级部门信息", notes = "已测试")
    @ApiImplicitParam(name="subdepartment", value = "三级部门实体类", required = true, dataType = "Subdepartment")
    @PutMapping("subdepartment")
    @ResponseBody
    public Result update(@RequestBody(required = false) Subdepartment subdepartment){
        if(subdepartment != null && subdepartment.getId() != null){
            boolean success = subdepartmentService.update(subdepartment);
            if(success){
                cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName);
                cache.deleteByPaterm(CacheNameHelper.ListByDepartmentID_CacheNamePrefix + "*");
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
     * 获取三级部门详情
     * @param id
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据三级部门id获取部门详情", notes = "已测试")
    @ApiImplicitParam(name = "id", value = "三级部门id", required = true, dataType = "bigint", paramType = "path")
    @GetMapping("subdepartment/{id}")
    public void receive(@PathVariable long id,
                        HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.Receive_CacheNamePrefix + id;
        String json = cache.get(cacheName);
        if(json == null){
            Subdepartment subdepartment= subdepartmentService.select(id);
            json = Result.build(ResultType.Success).appendData("subdepartment", subdepartment).convertIntoJSON();
            if(subdepartment != null){
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    /**
     * 根据页码分页查询所有三级部门
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据页码分页查询所有三级部门", notes = "已测试")
    @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    @GetMapping(value = "/subdepartments/list/{pageNum}")
    public void list(
            @PathVariable Integer pageNum ,
            HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.List_CacheNamePrefix + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<Subdepartment> pageInfo = subdepartmentService.selectPage(pageNum);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    /**
     * 根据部门id和页码来分页查询三级部门
     * @param department_id
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据部门id和页码来分页查询三级部门", notes = "已测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name="department_id", value = "部门id", required = true, dataType = "bigint", paramType = "path"),
            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/subdepartments/listByDepartmentID/{department_id}/{pageNum}")
    public void listByDepartmentID(@PathVariable long department_id,
                                   @PathVariable Integer pageNum,
                                   HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByDepartmentID_CacheNamePrefix + department_id + "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<Subdepartment> pageInfo = subdepartmentService.selectPageByDepartment(pageNum, department_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    /**
     * 根据学校id和页码来分页查询三级部门
     * @param university_id
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据学校id和页码来分页查询三级部门", notes = "已测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name="university_id", value = "学校id", required = true, dataType = "bigint", paramType = "path"),
            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/subdepartments/listByUniversityID/{university_id}/{pageNum}")
    public void listByUniversityID(@PathVariable long university_id,
                                   @PathVariable Integer pageNum,
                                   HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByUniversityID_CacheNamePrefix + university_id + "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<Subdepartment> pageInfo = subdepartmentService.selectPageByUniversity(pageNum, university_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    /**
     * 列举所有三级部门
     * @param response
     * @throws IOException
     */
    @ApiOperation(value="列举所有三级部门", notes="已测试")
    @GetMapping(value = "/subdepartments/listAll")
    public void list(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListAll_CacheName;
        String json = cache.get(cacheName);
        if(json == null){
            json = Result.build(ResultType.Success).appendData("subdepartments", subdepartmentService.selectAll()).convertIntoJSON();
            cache.set(cacheName, json);
        }
        response.getWriter().write(json);
    }


}
