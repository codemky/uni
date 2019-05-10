package edu.uni.administrativestructure.controller;

import com.github.pagehelper.PageInfo;
import edu.uni.administrativestructure.bean.Department;
import edu.uni.administrativestructure.service.DepartmentService;
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
 * create : 2019/4/19 0023 15:34
 * modified :
 * 功能 :控制对部门表的请求
 **/
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private RedisCache cache;

    /**
     * 内部类，专门用来管理每个方法所对应缓存的名称。
     */
    static class CacheNameHelper{
        // as_d_department_{部门id}
        private static final String Receive_CacheNamePrefix = "as_department_";
        // as_d_department_list_{页码}
        private static final String List_CacheNamePrefix = "as_department_list_";
        // as_d_departments_listByUniversityID_{学校id}_{页码}
        private static final String ListByUniversityID_CacheNamePrefix = "as_department_listByUniversityID_";
        //as_d_department_selectAll
        public static final String ListAll_CacheName = "as_department_selectAll";
    }

    /**
     * 新增部门
     * @param department
     * @return
     */
    @ApiOperation(value="新增部门", notes = "已测试")
    @ApiImplicitParam(name= "department",value = "部门实体类", required = true, dataType = "Department")
    @PostMapping("department")
    @ResponseBody
    public Result create(@RequestBody(required = false) Department department){
        if(department != null){
            boolean success = departmentService.insert(department);
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
     * 删除部门
     * @param id
     * @return
     */
    @ApiOperation(value = "根据部门id删除部门", notes = "已测试")
    @ApiImplicitParam(name = "id", value = "部门id", required = true, dataType = "bigint", paramType = "path")
    @DeleteMapping("department/{id}")
    @ResponseBody
    public Result destroy(@PathVariable long id){
        boolean success = departmentService.delete(id);
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
     * 修改部门
     * @param department
     * @return
     */
    @ApiOperation(value="根据部门id修改部门信息", notes = "已测试")
    @ApiImplicitParam(name="department", value = "部门实体类", required = true, dataType = "Department")
    @PutMapping("department")
    @ResponseBody
    public Result update(@RequestBody(required = false) Department department){
        if(department != null && department.getId() != null){
            boolean success = departmentService.update(department);
            if(success){
                cache.delete(CacheNameHelper.Receive_CacheNamePrefix + department.getId());
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
     * 获取部门详情
     * @param id
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据部门id获取部门详情", notes = "已测试")
    @ApiImplicitParam(name = "id", value = "部门id", required = true, dataType = "bigint", paramType = "path")
    @GetMapping("department/{id}")
    public void receive(@PathVariable long id,
                        HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.Receive_CacheNamePrefix + id;
        String json = cache.get(cacheName);
        if(json == null){
            Department department= departmentService.select(id);
            json = Result.build(ResultType.Success).appendData("department", department).convertIntoJSON();
            if(department != null){
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    /**
     * 根据页码分页查询所有部门
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据页码分页查询所有部门", notes = "已测试")
    @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    @GetMapping(value = "/departments/list/{pageNum}")
    public void list(
            @PathVariable Integer pageNum ,
            HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.List_CacheNamePrefix + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<Department> pageInfo = departmentService.selectPage(pageNum);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    /**
     * 根据学校id和页码来分页查询部门
     * @param university_id
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据学校id和页码来分页查询部门", notes = "已测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name="university_id", value = "学校id", required = true, dataType = "bigint", paramType = "path"),
            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/departments/listByUniversityId/{university_id}/{pageNum}")
    public void listByUniversityID(@PathVariable long university_id,
                          @PathVariable Integer pageNum,
                          HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByUniversityID_CacheNamePrefix + university_id + "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<Department> pageInfo = departmentService.selectPageByUniversity(pageNum, university_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }
    /**
     * 列举所有部门
     * @param response
     * @throws IOException
     */
    @ApiOperation(value="列举所有部门", notes="已测试")
    @GetMapping(value = "/departments/listAll")
    public void list(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListAll_CacheName;
        String json = cache.get(cacheName);
        if(json == null){
            json = Result.build(ResultType.Success).appendData("departments", departmentService.selectAll()).convertIntoJSON();
            cache.set(cacheName, json);
        }
        response.getWriter().write(json);
    }
}
