package edu.uni.administrativestructure.controller;

import com.github.pagehelper.PageInfo;
import edu.uni.administrativestructure.bean.UniversityDepartment;
import edu.uni.administrativestructure.service.UniversityDepartmentService;
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
public class UniversityDepartmentController {
    @Autowired
    private UniversityDepartmentService universityDepartmentService;

    @Autowired
    private RedisCache cache;

    /**
     * 内部类，专门用来管理每个方法所对应缓存的名称。
     */
    static class CacheNameHelper{
        // as_ud_universityDepartment_{学校部门关系id}
        private static final String Receive_CacheNamePrefix = "as_universityDepartment_";
        // as_ud_universityDepartment_list_{页码}
        private static final String List_CacheNamePrefix = "as_universityDepartment_list_";
        // as_ud_universityDepartments_listByDepartmentID_{学校部门关系id}_{页码}
        private static final String ListByDepartmentID_CacheNamePrefix = "as_universityDepartment_listByDepartmentID_";
        private static final String ListByUniversityID_CacheNamePrefix = "as_universityDepartment_listByUniversityID_";
        public static final String ListAll_CacheName = "as_universityDepartment_listAll";
    }

    /**
     * 新增学校部门关系
     * @param universityDepartment
     * @return
     */
    @ApiOperation(value="新增学校部门关系", notes = "已测试")
    @ApiImplicitParam(name= "universityDepartment",value = "学校部门关系实体类", required = true, dataType = "UniversityDepartment")
    @PostMapping("universityDepartment")
    @ResponseBody
    public Result create(@RequestBody(required = false) UniversityDepartment universityDepartment){
        if(universityDepartment != null){
            boolean success = universityDepartmentService.insert(universityDepartment);
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
     * 删除学校部门关系
     * @param id
     * @return
     */
    @ApiOperation(value = "根据学校部门关系id删除学校部门关系", notes = "已测试")
    @ApiImplicitParam(name = "id", value = "学校部门关系id", required = true, dataType = "bigint", paramType = "path")
    @DeleteMapping("universityDepartment/{id}")
    @ResponseBody
    public Result destroy(@PathVariable long id){
        boolean success = universityDepartmentService.delete(id);
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
     * 修改学校部门关系
     * @param universityDepartment
     * @return
     */
    @ApiOperation(value="根据学校部门关系id修改学校部门关系信息", notes = "已测试")
    @ApiImplicitParam(name="universityDepartment", value = "学校部门关系实体类", required = true, dataType = "UniversityDepartment")
    @PutMapping("universityDepartment")
    @ResponseBody
    public Result update(@RequestBody(required = false) UniversityDepartment universityDepartment){
        if(universityDepartment != null && universityDepartment.getId() != null){
            boolean success = universityDepartmentService.update(universityDepartment);
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
     * 获取学校部门关系详情
     * @param id
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据学校部门关系id获取学校部门关系详情", notes = "已测试")
    @ApiImplicitParam(name = "id", value = "学校部门关系id", required = true, dataType = "bigint", paramType = "path")
    @GetMapping("universityDepartment/{id}")
    public void receive(@PathVariable long id,
                        HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.Receive_CacheNamePrefix + id;
        String json = cache.get(cacheName);
        if(json == null){
            UniversityDepartment universityDepartment = universityDepartmentService.select(id);
            json = Result.build(ResultType.Success).appendData("universityDepartment", universityDepartment).convertIntoJSON();
            if(universityDepartment != null){
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    /**
     * 根据页码分页查询所有学校部门关系
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据页码分页查询所有学校部门关系", notes = "已测试")
    @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    @GetMapping(value = "/universityDepartments/list/{pageNum}")
    public void list(
            @PathVariable Integer pageNum ,
            HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.List_CacheNamePrefix + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<UniversityDepartment> pageInfo = universityDepartmentService.selectPage(pageNum);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    /**
     * 根据部门id和页码来分页查询学校部门关系
     * @param department_id
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据部门id和页码来分页查询学校部门关系", notes = "已测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name="department_id", value = "部门id", required = true, dataType = "bigint", paramType = "path"),
            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/universityDepartments/listByDepartmentID/{department_id}/{pageNum}")
    public void listByDepartmentID(@PathVariable long department_id,
                                   @PathVariable Integer pageNum,
                                   HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByDepartmentID_CacheNamePrefix + department_id + "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<UniversityDepartment> pageInfo = universityDepartmentService.selectPageByDepartment(pageNum, department_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    /**
     * 根据学校id和页码来分页查询学校部门关系
     * @param university_id
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据学校id和页码来分页查询学校部门关系", notes = "已测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name="university_id", value = "学校id", required = true, dataType = "bigint", paramType = "path"),
            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/universityDepartments/listByUniversityID/{university_id}/{pageNum}")
    public void listByUniversityID(@PathVariable long university_id,
                                   @PathVariable Integer pageNum,
                                   HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByUniversityID_CacheNamePrefix + university_id + "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<UniversityDepartment> pageInfo = universityDepartmentService.selectPageByUniversity(pageNum, university_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    /**
     * 列举所有学校部门关系
     * @param response
     * @throws IOException
     */
    @ApiOperation(value="列举所有学校部门关系", notes="已测试")
    @GetMapping(value = "/universityDepartments/listAll")
    public void list(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListAll_CacheName;
        String json = cache.get(cacheName);
        if(json == null){
            json = Result.build(ResultType.Success).appendData("universityDepartments", universityDepartmentService.selectAll()).convertIntoJSON();
            cache.set(cacheName, json);
        }
        response.getWriter().write(json);
    }
}
