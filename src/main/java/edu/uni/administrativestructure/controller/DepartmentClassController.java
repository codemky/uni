package edu.uni.administrativestructure.controller;

import com.github.pagehelper.PageInfo;
import edu.uni.administrativestructure.bean.DepartmentClass;
import edu.uni.administrativestructure.service.DepartmentClassService;
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
 * create: 2019.4.26
 * modified:2019.5.7
 */

@Api(description = "行政架构模块")
@Controller
@RequestMapping("json/administrativestructure")
public class DepartmentClassController {
    @Autowired
    private DepartmentClassService departmentClassService;

    @Autowired
    private RedisCache cache;

    /**
     * 内部类，专门用来管理每个方法所对应缓存的名称。
     */
    static class CacheNameHelper{
        // as_dc_departmentClass_{部门班级关系id}
        private static final String Receive_CacheNamePrefix = "as_departmentClass_";
        // as_dc_departmentClass_list_{页码}
        private static final String List_CacheNamePrefix = "as_departmentClass_list_";
        // as_ud_departmentClasses_listByDepartmentID_{部门班级关系id}_{页码}
        private static final String ListByDepartmentID_CacheNamePrefix = "as_departmentClass_listByDepartmentID_";
        private static final String ListByUniversityID_CacheNamePrefix = "as_departmentClass_listByUniversityID_";
        private static final String ListByClassID_CacheNamePrefix = "as_departmentClass_listByClassID_";
        public static final String ListAll_CacheName = "as_departmentClass_listAll";
    }

    /**
     * 新增部门班级关系
     * @param departmentClass
     * @return
     */
    @ApiOperation(value="新增部门班级关系", notes = "已测试")
    @ApiImplicitParam(name= "departmentClass",value = "部门班级关系实体类", required = true, dataType = "DepartmentClass")
    @PostMapping("departmentClass")
    @ResponseBody
    public Result create(@RequestBody(required = false) DepartmentClass departmentClass){
        if(departmentClass != null){
            boolean success = departmentClassService.insert(departmentClass);
            if(success){
                cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName);
                cache.deleteByPaterm(CacheNameHelper.ListByDepartmentID_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListByUniversityID_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListByClassID_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.Receive_CacheNamePrefix + "*");
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }

    /**
     * 删除部门班级关系
     * @param id
     * @return
     */
    @ApiOperation(value = "根据部门班级关系id删除部门班级关系", notes = "已测试")
    @ApiImplicitParam(name = "id", value = "部门班级关系id", required = true, dataType = "bigint", paramType = "path")
    @DeleteMapping("departmentClass/{id}")
    @ResponseBody
    public Result destroy(@PathVariable long id){
        boolean success = departmentClassService.delete(id);
        if(success){
            cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName);
            cache.deleteByPaterm(CacheNameHelper.ListByDepartmentID_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.ListByUniversityID_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.ListByClassID_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.Receive_CacheNamePrefix + "*");
            return Result.build(ResultType.Success);
        }else{
            return Result.build(ResultType.Failed);
        }
    }
    /**
     * 修改部门班级关系
     * @param departmentClass
     * @return
     */
    @ApiOperation(value="根据部门班级关系id修改部门班级关系信息", notes = "已测试")
    @ApiImplicitParam(name="departmentClass", value = "部门班级关系实体类", required = true, dataType = "DepartmentClass")
    @PutMapping("departmentClass")
    @ResponseBody
    public Result update(@RequestBody(required = false) DepartmentClass departmentClass){
        if(departmentClass != null && departmentClass.getId() != null){
            boolean success = departmentClassService.update(departmentClass);
            if(success){
                cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName);
                cache.deleteByPaterm(CacheNameHelper.ListByDepartmentID_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListByUniversityID_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListByClassID_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.Receive_CacheNamePrefix + "*");
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }

    /**
     * 获取部门班级关系详情
     * @param id
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据部门班级关系id获取部门班级关系详情", notes = "已测试")
    @ApiImplicitParam(name = "id", value = "部门班级关系id", required = true, dataType = "bigint", paramType = "path")
    @GetMapping("departmentClass/{id}")
    public void receive(@PathVariable long id,
                        HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.Receive_CacheNamePrefix + id;
        String json = cache.get(cacheName);
        if(json == null){
            DepartmentClass departmentClass = departmentClassService.select(id);
            json = Result.build(ResultType.Success).appendData("departmentClass", departmentClass).convertIntoJSON();
            if(departmentClass != null){
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    /**
     * 根据页码分页查询所有部门班级关系
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据页码分页查询所有部门班级关系", notes = "已测试")
    @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    @GetMapping(value = "/departmentClasses/list/{pageNum}")
    public void list(
            @PathVariable Integer pageNum ,
            HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.List_CacheNamePrefix + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<DepartmentClass> pageInfo = departmentClassService.selectPage(pageNum);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    /**
     * 根据部门id和页码来分页查询部门班级关系
     * @param department_id
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据部门id和页码来分页查询部门班级关系", notes = "已测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name="department_id", value = "部门id", required = true, dataType = "bigint", paramType = "path"),
            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/departmentClasses/listByDepartmentID/{department_id}/{pageNum}")
    public void listByDepartmentID(@PathVariable long department_id,
                                   @PathVariable Integer pageNum,
                                   HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByDepartmentID_CacheNamePrefix + department_id + "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<DepartmentClass> pageInfo = departmentClassService.selectPageByDepartment(pageNum, department_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    /**
     * 根据学校id和页码来分页查询部门班级关系
     * @param university_id
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据学校id和页码来分页查询部门班级关系", notes = "已测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name="university_id", value = "学校id", required = true, dataType = "bigint", paramType = "path"),
            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/departmentClasses/listByUniversityID/{university_id}/{pageNum}")
    public void listByUniversityID(@PathVariable long university_id,
                                   @PathVariable Integer pageNum,
                                   HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByUniversityID_CacheNamePrefix + university_id + "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<DepartmentClass> pageInfo = departmentClassService.selectPageByUniversity(pageNum, university_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    /**
     * 根据班级id和页码来分页查询部门班级关系
     * @param class_id
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据班级id和页码来分页查询部门班级关系", notes = "已测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name="class_id", value = "班级id", required = true, dataType = "bigint", paramType = "path"),
            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/departmentClasses/listByClassID/{class_id}/{pageNum}")
    public void listByClassID(@PathVariable long class_id,
                                   @PathVariable Integer pageNum,
                                   HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByClassID_CacheNamePrefix + class_id + "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<DepartmentClass> pageInfo = departmentClassService.selectPageByClass(pageNum, class_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    /**
     * 列举所有部门班级关系
     * @param response
     * @throws IOException
     */
    @ApiOperation(value="列举所有部门班级关系", notes="已测试")
    @GetMapping(value = "/departmentClasses/listAll")
    public void list(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListAll_CacheName;
        String json = cache.get(cacheName);
        if(json == null){
            json = Result.build(ResultType.Success).appendData("departmentClasses", departmentClassService.selectAll()).convertIntoJSON();
            cache.set(cacheName, json);
        }
        response.getWriter().write(json);
    }
}
