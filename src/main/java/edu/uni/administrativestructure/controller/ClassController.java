package edu.uni.administrativestructure.controller;

import com.github.pagehelper.PageInfo;
import edu.uni.administrativestructure.bean.Class;
import edu.uni.administrativestructure.service.ClassService;
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
 * author : 黄永佳
 * create : 2019/4/26 0026 14:42
 * modified :
 * 功能 :控制对班级信息表的请求
 **/
@Api(description = "行政架构模块")
@Controller
@RequestMapping("json/administrativestructure")
public class ClassController {
    @Autowired
    private ClassService classService;

    @Autowired
    private RedisCache cache;

    /**
     * 内部类，专门用来管理每个方法所对应缓存的名称。
     */
    static class CacheNameHelper{
        // as_c_class_{班级信息id}
        private static final String Receive_CacheNamePrefix = "as_class_";
        // as_c_class_{页码}
        private static final String List_CacheNamePrefix = "as_class_list_";
        // as_c_class_listByUniversityId_{学校id}_{页码}
        private static final String ListByUniversityId_CacheNamePrefix = "as_class_listByUniversityId_";
        // as_c_class_listByDepartmentId_{部门id}_{页码}
        private static final String ListByDepartmentId_CacheNamePrefix = "as_class_listByDepartmentId_";
        // as_c_class_listBySpecialtyId_{专业id}_{页码}
        private static final String ListBySpecialtyid_CacheNamePrefix = "as_class_listBySpecialtyId_";
    }
    /**
     * 新增班级信息
     * @param class_1
     * @return
     */
    @ApiOperation(value="新增班级信息表", notes = "已测试")
    @ApiImplicitParam(name= "class_1",value = "班级信息实体类", required = true, dataType = "Class")
    @PostMapping("class")
    @ResponseBody
    public Result create(@RequestBody(required = false) Class class_1){
        if(class_1 != null){
            boolean success = classService.insert(class_1);
            if(success){
                cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListByUniversityId_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.Receive_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListByDepartmentId_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListBySpecialtyid_CacheNamePrefix + "*");
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }
    /**
     * 删除班级
     * @param id
     * @return
     */
    @ApiOperation(value = "根据班级id删除班级", notes = "已测试")
    @ApiImplicitParam(name = "id", value = "班级id", required = true, dataType = "bigint", paramType = "path")
    @DeleteMapping("class/{id}")
    @ResponseBody
    public Result destroy(@PathVariable long id){
        boolean success = classService.delete(id);
        if(success){
            cache.delete(CacheNameHelper.Receive_CacheNamePrefix + id);
            cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.ListByUniversityId_CacheNamePrefix + "*");
//            cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName );
            cache.deleteByPaterm(CacheNameHelper.ListByDepartmentId_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.ListBySpecialtyid_CacheNamePrefix + "*");
            return Result.build(ResultType.Success);
        }else{
            return Result.build(ResultType.Failed);
        }
    }
    /**
     * 修改班级信息
     * @param class_1
     * @return
     */
    @ApiOperation(value="根据班级id修改班级信息", notes = "已测试")
    @ApiImplicitParam(name="class_1", value = "一级二级部门关系实体类", required = true, dataType = "Class")
    @PutMapping("class")
    @ResponseBody
    public Result update(@RequestBody(required = false) Class class_1){
        if(class_1 != null && class_1.getId() != null){
            boolean success = classService.update(class_1);
            if(success){
                cache.delete(CacheNameHelper.Receive_CacheNamePrefix + class_1.getId());
                cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListByUniversityId_CacheNamePrefix + "*");
//                cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName );
                cache.deleteByPaterm(CacheNameHelper.ListByDepartmentId_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListBySpecialtyid_CacheNamePrefix + "*");

                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }
    /**
     * 获取班级信息详情
     * @param id
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据班级id获取班级详情", notes = "已测试")
    @ApiImplicitParam(name = "id", value = "班级id", required = true, dataType = "bigint", paramType = "path")
    @GetMapping("class/{id}")
    public void receive(@PathVariable long id,
                        HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.Receive_CacheNamePrefix + id;
        String json = cache.get(cacheName);
        if(json == null){
            Class class_1= classService.select(id);
            json = Result.build(ResultType.Success).appendData("class", class_1).convertIntoJSON();
            if(class_1 != null){
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
    @ApiOperation(value = "根据页码分页查询所有班级", notes = "已测试")
    @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    @GetMapping(value = "/classes/list/{pageNum}")
    public void list(
            @PathVariable Integer pageNum ,
            HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.List_CacheNamePrefix + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<Class> pageInfo = classService.selectPage(pageNum);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }
    /**
     * 根据学校id和页码来分页查询班级信息
     * @param university_id
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据学校id和页码来分页查询班级信息", notes = "已测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name="university_id", value = "学校id", required = true, dataType = "bigint", paramType = "path"),
            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/classes/listByUniversityId/{university_id}/{pageNum}")
    public void listByUniversityID(@PathVariable long university_id,
                                   @PathVariable Integer pageNum,
                                   HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByUniversityId_CacheNamePrefix + university_id + "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<Class> pageInfo = classService.selectPageByUniversity(pageNum, university_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }
    /**
     * 根据一级部门id和页码来分页查询班级信息
     * @param department_id
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据一级部门id和页码来分页查询班级信息", notes = "已测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name="department_id", value = "一级部门id", required = true, dataType = "bigint", paramType = "path"),
            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/classes/listByDepartmentId/{department_id}/{pageNum}")
    public void listByDepartmentID(@PathVariable long department_id,
                                   @PathVariable Integer pageNum,
                                   HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByDepartmentId_CacheNamePrefix + department_id + "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<Class> pageInfo = classService.selectPageByDepartment(pageNum, department_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }
    /**
     * 根据专业id和页码来分页查询一二级部门关系
     * @param specialty_id
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据专业id和页码来分页查询班级信息", notes = "已测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name="specialty_id", value = "二级部门id", required = true, dataType = "bigint", paramType = "path"),
            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/classes/listBySubdepartmentId/{specialty_id}/{pageNum}")
    public void listBySubdepartmentID(@PathVariable long specialty_id,
                                      @PathVariable Integer pageNum,
                                      HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListBySpecialtyid_CacheNamePrefix + specialty_id + "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<Class> pageInfo = classService.selectPageBySpecialty(pageNum, specialty_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }
}
