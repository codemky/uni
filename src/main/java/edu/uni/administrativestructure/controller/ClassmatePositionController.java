package edu.uni.administrativestructure.controller;

import com.github.pagehelper.PageInfo;
import edu.uni.administrativestructure.bean.ClassmatePosition;
import edu.uni.administrativestructure.service.ClassmatePositionService;
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
 * create : 2019/4/26 0026 18:59
 * modified :
 * 功能 :控制对ClassmatePosition表的请求
 **/
@Api(description = "行政架构模块")
@Controller
@RequestMapping("json/administrativestructure")
public class ClassmatePositionController {
    @Autowired
    private ClassmatePositionService classmatePositionService;
    @Autowired
    private RedisCache cache;

    /**
     * 内部类，专门用来管理每个方法所对应缓存的名称。
     */
    static class CacheNameHelper{
        // as_c_classmateposition_{班级信息id}
        private static final String Receive_CacheNamePrefix = "as_classmateposition_";
        // as_c_classmateposition_{页码}
        private static final String List_CacheNamePrefix = "as_classmateposition_list_";
        // as_c_classmateposition_listByUniversityId_{学校id}_{页码}
        private static final String ListByUniversityId_CacheNamePrefix = "as_classmateposition_listByUniversityId_";
        // as_c_classmateposition_listByDepartmentId_{部门id}_{页码}
        private static final String ListByClassmateId_CacheNamePrefix = "as_classmateposition_listByClassmateId_";
        // as_c_classmateposition_listBySpecialtyId_{专业id}_{页码}
        private static final String ListByPositionId_CacheNamePrefix = "as_classmateposition_listByPositionId_";
    }

    /**
     * 新增班级人员岗位信息
     * @param classmatePosition
     * @return
     */
    @ApiOperation(value="新增班级人员岗位信息表", notes = "已测试")
    @ApiImplicitParam(name= "classmatePosition",value = "班级人员岗位信息实体类", required = true, dataType = "ClassmatePosition")
    @PostMapping("classmateposition")
    @ResponseBody
    public Result create(@RequestBody(required = false) ClassmatePosition classmatePosition){
        if(classmatePosition != null){
            boolean success = classmatePositionService.insert(classmatePosition);
            if(success){
                cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListByUniversityId_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.Receive_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListByClassmateId_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListByPositionId_CacheNamePrefix + "*");
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }
    /**
     * 删除班级人员岗位信息
     * @param id
     * @return
     */
    @ApiOperation(value = "根据班级人员岗位id删除信息", notes = "已测试")
    @ApiImplicitParam(name = "id", value = "班级人员岗位id", required = true, dataType = "bigint", paramType = "path")
    @DeleteMapping("classmateposition/{id}")
    @ResponseBody
    public Result destroy(@PathVariable long id){
        boolean success = classmatePositionService.delete(id);
        if(success){
            cache.delete(CacheNameHelper.Receive_CacheNamePrefix + id);
            cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.ListByUniversityId_CacheNamePrefix + "*");
//            cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName );
            cache.deleteByPaterm(CacheNameHelper.ListByClassmateId_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.ListByPositionId_CacheNamePrefix + "*");
            return Result.build(ResultType.Success);
        }else{
            return Result.build(ResultType.Failed);
        }
    }
    /**
     * 修改班级人员岗位信息
     * @param classmatePosition
     * @return
     */
    @ApiOperation(value="根据班级人员岗位id修改班级人员岗位信息", notes = "已测试")
    @ApiImplicitParam(name="classmatePosition", value = "班级人员岗位信息实体类", required = true, dataType = "ClassmatePosition")
    @PutMapping("classmateposition")
    @ResponseBody
    public Result update(@RequestBody(required = false) ClassmatePosition classmatePosition){
        if(classmatePosition != null && classmatePosition.getId() != null){
            boolean success = classmatePositionService.update(classmatePosition);
            if(success){
                cache.delete(CacheNameHelper.Receive_CacheNamePrefix + classmatePosition.getId());
                cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListByUniversityId_CacheNamePrefix + "*");
//                cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName );
                cache.deleteByPaterm(CacheNameHelper.ListByClassmateId_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListByPositionId_CacheNamePrefix + "*");
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }
    /**
     * 获取班级人员岗位信息详情
     * @param id
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据班级人员岗位id获取详情", notes = "已测试")
    @ApiImplicitParam(name = "id", value = "班级人员岗位id", required = true, dataType = "bigint", paramType = "path")
    @GetMapping("classmateposition/{id}")
    public void receive(@PathVariable long id,
                        HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.Receive_CacheNamePrefix + id;
        String json = cache.get(cacheName);
        if(json == null){
            ClassmatePosition classmatePosition= classmatePositionService.select(id);
            json = Result.build(ResultType.Success).appendData("classmateposition", classmatePosition).convertIntoJSON();
            if(classmatePosition != null){
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }
    /**
     * 根据页码分页查询所有班级人员岗位信息
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据页码分页查询所有班级人员岗位信息", notes = "已测试")
    @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    @GetMapping(value = "/classmatepositions/list/{pageNum}")
    public void list(
            @PathVariable Integer pageNum ,
            HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.List_CacheNamePrefix + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<ClassmatePosition> pageInfo = classmatePositionService.selectPage(pageNum);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }
    /**
     * 根据学校id和页码来分页查询班级人员岗位信息
     * @param university_id
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据学校id和页码来分页查询班级人员岗位信息", notes = "已测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name="university_id", value = "学校id", required = true, dataType = "bigint", paramType = "path"),
            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/classmateposition/listByUniversityId/{university_id}/{pageNum}")
    public void listByUniversityID(@PathVariable long university_id,
                                   @PathVariable Integer pageNum,
                                   HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByUniversityId_CacheNamePrefix + university_id + "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<ClassmatePosition> pageInfo = classmatePositionService.selectPageByUniversity(pageNum, university_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }
    /**
     * 根据班级人员id和页码来分页查询班级人员岗位信息
     * @param classmate_id
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据班级人员id和页码来分页查询班级人员岗位信息", notes = "已测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name="classmate_id", value = "班级人员id", required = true, dataType = "bigint", paramType = "path"),
            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/classmatepositions/listByClassmateId/{classmate_id}/{pageNum}")
    public void listByDepartmentID(@PathVariable long classmate_id,
                                   @PathVariable Integer pageNum,
                                   HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByClassmateId_CacheNamePrefix + classmate_id + "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<ClassmatePosition> pageInfo = classmatePositionService.selectPageByClassmate(pageNum, classmate_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }
    /**
     * 根据岗位id和页码来分页查询班级人员岗位信息
     * @param position_id
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据岗位id和页码来分页查询班级人员岗位信息", notes = "已测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name="position_id", value = "岗位id", required = true, dataType = "bigint", paramType = "path"),
            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/classmatepositions/listByPositionId/{position_id}/{pageNum}")
    public void listBySubdepartmentID(@PathVariable long position_id,
                                      @PathVariable Integer pageNum,
                                      HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByPositionId_CacheNamePrefix + position_id + "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<ClassmatePosition> pageInfo = classmatePositionService.selectPageByPosition(pageNum, position_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }
}
