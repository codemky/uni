package edu.uni.administrativestructure.controller;

import com.github.pagehelper.PageInfo;
import edu.uni.administrativestructure.bean.DepartmentSubdepartment;
import edu.uni.administrativestructure.service.DepartmentSubdepartmentService;
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

@Api(description = "行政架构模块")
@Controller
@RequestMapping("json/administrativestructure")
/**
 * author : 黄永佳
 * create : 2019/4/23 0023 15:34
 * modified :
 * 功能 :控制对一级部门二级部门表的请求
 **/
public class DepartmentSubdepartmentController {

    @Autowired
    private DepartmentSubdepartmentService departmentSubdepartmentService;

    @Autowired
    private RedisCache cache;

    /**
     * 内部类，专门用来管理每个方法所对应缓存的名称。
     */
    static class CacheNameHelper{
        // as_d_departmentsubdepartment_{主id}
        private static final String Receive_CacheNamePrefix = "as_departmentsubdepartment_";
        // as_d_departmentsubdepartment_list_{页码}
        private static final String List_CacheNamePrefix = "as_departmentsubdepartment_list_";
        // as_d_departmentsubdepartment_listByUniversityid_{学校id}_{页码}
        private static final String ListByUniversityId_CacheNamePrefix = "as_departmentsubdepartment_listByUniversityId_";
        //as_d_departmentsubdepartment_list
        public static final String ListAll_CacheName = "as_departmentsubdepartment_listAll";
        // as_d_departmentsubdepartment_listByDepartmentid_{一级部门id}_{页码}
        private static final String ListByDepartmentId_CacheNamePrefix = "as_departmentsubdepartment_listByDepartmentId_";
        // as_d_departmentsubdepartment_listBySubdepartmentid_{二级部门id}_{页码}
        private static final String ListBySubdepartmentId_CacheNamePrefix = "as_departmentsubdepartment_listBySubdepartmentId_";
    }

    /**
     * 新增一级二级部门关系
     * @param departmentSubdepartment
     * @return
     */
    @ApiOperation(value="新增一级二级部门关系", notes = "已测试")
    @ApiImplicitParam(name= "departmentSubdepartment",value = "一级二级部门关系实体类", required = true, dataType = "DepartmentSubdepartment")
    @PostMapping("departmentSubdepartment")
    @ResponseBody
    public Result create(@RequestBody(required = false) DepartmentSubdepartment departmentSubdepartment){
        if(departmentSubdepartment != null){
            boolean success = departmentSubdepartmentService.insert(departmentSubdepartment);
            if(success){
                cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName );
                cache.deleteByPaterm(CacheNameHelper.ListByUniversityId_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.Receive_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListByDepartmentId_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListBySubdepartmentId_CacheNamePrefix + "*");
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }

    /**
     * 删除一级二级部门关系
     * @param id
     * @return
     */
    @ApiOperation(value = "根据一级二级部门关系id删除部门", notes = "已测试")
    @ApiImplicitParam(name = "id", value = "一级二级部门关系id", required = true, dataType = "bigint", paramType = "path")
    @DeleteMapping("departmentSubdepartment/{id}")
    @ResponseBody
    public Result destroy(@PathVariable long id){
        boolean success = departmentSubdepartmentService.delete(id);
        if(success){
            cache.delete(CacheNameHelper.Receive_CacheNamePrefix + id);
            cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.ListByUniversityId_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName );
            cache.deleteByPaterm(CacheNameHelper.ListByDepartmentId_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.ListBySubdepartmentId_CacheNamePrefix + "*");
            return Result.build(ResultType.Success);
        }else{
            return Result.build(ResultType.Failed);
        }
    }

    /**
     * 修改一级二级部门关系
     * @param departmentSubdepartment
     * @return
     */
    @ApiOperation(value="根据一二级部门关系id修改一二级部门关系信息", notes = "已测试")
    @ApiImplicitParam(name="departmentSubdepartment", value = "一级二级部门关系实体类", required = true, dataType = "DepartmentSubdepartment")
    @PutMapping("departmentSubdepartment")
    @ResponseBody
    public Result update(@RequestBody(required = false) DepartmentSubdepartment departmentSubdepartment){
        if(departmentSubdepartment != null && departmentSubdepartment.getId() != null){
            boolean success = departmentSubdepartmentService.update(departmentSubdepartment);
            if(success){
                cache.delete(CacheNameHelper.Receive_CacheNamePrefix + departmentSubdepartment.getId());
                cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListByUniversityId_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName );
                cache.deleteByPaterm(CacheNameHelper.ListByDepartmentId_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListBySubdepartmentId_CacheNamePrefix + "*");

                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }

    /**
     * 获取一二级部门关系详情
     * @param id
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据一级二级部门关系id获取一级二级部门关系详情", notes = "已测试")
    @ApiImplicitParam(name = "id", value = "一级二级部门关系id", required = true, dataType = "bigint", paramType = "path")
    @GetMapping("departmentSubdepartment/{id}")
    public void receive(@PathVariable long id,
                        HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.Receive_CacheNamePrefix + id;
        String json = cache.get(cacheName);
        if(json == null){
            DepartmentSubdepartment departmentSubdepartment= departmentSubdepartmentService.select(id);
            json = Result.build(ResultType.Success).appendData("departmentSubdepartment", departmentSubdepartment).convertIntoJSON();
            if(departmentSubdepartment != null){
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    /**
     * 根据页码分页查询所有一二级部门关系
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据页码分页查询所有部门", notes = "已测试")
    @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    @GetMapping(value = "/departmentSubdepartments/list/{pageNum}")
    public void list(
            @PathVariable Integer pageNum ,
            HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.List_CacheNamePrefix + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<DepartmentSubdepartment> pageInfo = departmentSubdepartmentService.selectPage(pageNum);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    /**
     * 根据学校id和页码来分页查询一二级部门关系
     * @param university_id
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据学校id和页码来分页查询一二级部门关系", notes = "已测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name="university_id", value = "学校id", required = true, dataType = "bigint", paramType = "path"),
            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/departmentSubdepartments/listByUniversityId/{university_id}/{pageNum}")
    public void listByUniversityID(@PathVariable long university_id,
                                   @PathVariable Integer pageNum,
                                   HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByUniversityId_CacheNamePrefix + university_id + "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<DepartmentSubdepartment> pageInfo = departmentSubdepartmentService.selectPageByUniversity(pageNum, university_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    /**
     * 根据一级部门id和页码来分页查询一二级部门关系
     * @param department_id
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据一级部门id和页码来分页查询一二级部门关系", notes = "已测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name="department_id", value = "一级部门id", required = true, dataType = "bigint", paramType = "path"),
            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/departmentSubdepartments/listByDepartmentId/{department_id}/{pageNum}")
    public void listByDepartmentID(@PathVariable long department_id,
                                   @PathVariable Integer pageNum,
                                   HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByDepartmentId_CacheNamePrefix + department_id + "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<DepartmentSubdepartment> pageInfo = departmentSubdepartmentService.selectPageByDepartment(pageNum, department_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    /**
     * 根据二级部门id和页码来分页查询一二级部门关系
     * @param subdepartment_id
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据二级部门id和页码来分页查询一二级部门关系", notes = "已测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name="subdepartment_id", value = "二级部门id", required = true, dataType = "bigint", paramType = "path"),
            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/departmentSubdepartments/listBySubdepartmentId/{subdepartment_id}/{pageNum}")
    public void listBySubdepartmentID(@PathVariable long subdepartment_id,
                                   @PathVariable Integer pageNum,
                                   HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListBySubdepartmentId_CacheNamePrefix + subdepartment_id + "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<DepartmentSubdepartment> pageInfo = departmentSubdepartmentService.selectPageBySubdepartment(pageNum, subdepartment_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    /**
     * 列举所有一二级部门关系
     * @param response
     * @throws IOException
     */
    @ApiOperation(value="列举所有一二级部门关系", notes="已测试")
    @GetMapping(value = "/departmentSubdepartments/list")
    public void list(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListAll_CacheName;
        String json = cache.get(cacheName);
        if(json == null){
            json = Result.build(ResultType.Success).appendData("departmentSubdepartments", departmentSubdepartmentService.selectAll()).convertIntoJSON();
            cache.set(cacheName, json);
        }
        response.getWriter().write(json);
    }
}
