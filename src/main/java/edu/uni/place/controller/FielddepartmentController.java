package edu.uni.place.controller;

import com.github.pagehelper.PageInfo;
import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.place.bean.Fielddepartment;
import edu.uni.place.service.FielddepartmentService;
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
import java.util.Calendar;

/**
 * @Author 潘绍豪
 * @Create 2019/4/30
 * @Description
 * @Since 1.0.0
 */
@Api(description = "场地基础信息")
@Controller
@RequestMapping("json/place")
public class FielddepartmentController {
    @Autowired
    private FielddepartmentService fielddepartmentService;

    @Autowired
    private RedisCache cache;

    /**
     * 内部类，专门用来管理每个方法所对应缓存的名称。
     */
    static class CacheNameHelper{
        // {场地主管部门id}
        private static final String Receive_CacheNamePrefix = "e_fielddepartment_";
        // {页码}
        private static final String List_CacheNamePrefix = "e_fielddepartment_list_";
        // {学校id}_{场地id}_{部门id}_{页码}
        private static final String ListByUIdFIdDId_CacheNamePrefix = "e_field_listByUIdFIdDId_list";
    }

    /**
     * 新增场地主管部门
     * @param fielddepartment
     * @return
     */
    @ApiOperation(value="新增场地主管部门", notes = "")
    @ApiImplicitParam(name= "fielddepartment",value = "场地主管部门实体类", required = true, dataType = "Fielddepartment")
    @PostMapping("fielddepartment")
    @ResponseBody
    public Result create(@RequestBody(required = false) Fielddepartment fielddepartment){
        //初始化场地主管部门
        fielddepartment.setDatetime(Calendar.getInstance().getTime());
        if(fielddepartment != null){
            boolean success = fielddepartmentService.insert(fielddepartment);
            if(success){
                cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");

                cache.deleteByPaterm(CacheNameHelper.ListByUIdFIdDId_CacheNamePrefix + "*");
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }

    /**
     * 删除场地主管部门
     * @param id
     * @return
     */
    @ApiOperation(value = "根据场地主管部门id删除场地主管部门", notes = "")
    @ApiImplicitParam(name = "id", value = "场地主管部门id", required = true, dataType = "Long", paramType = "path")
    @DeleteMapping("fielddepartment/{id}")
    @ResponseBody
    public Result destroy(@PathVariable Long id){
        boolean success = fielddepartmentService.delete(id);
        if(success){
            cache.delete(CacheNameHelper.Receive_CacheNamePrefix + id);
            cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.ListByUIdFIdDId_CacheNamePrefix + "*");
            return Result.build(ResultType.Success);
        }else{
            return Result.build(ResultType.Failed);
        }
    }

    /**
     * 修改场地主管部门
     * @param fielddepartment
     * @return
     */
    @ApiOperation(value="根据场地主管部门id修改场地主管部门")
    @ApiImplicitParam(name="fielddepartment", value = "场地主管部门实体类", required = true, dataType = "Fielddepartment")
    @PutMapping("fielddepartment")
    @ResponseBody
    public Result update(@RequestBody(required = false) Fielddepartment fielddepartment){
        System.out.println(fielddepartment.toString());
        //初始化场地主管部门
        fielddepartment.setDatetime(Calendar.getInstance().getTime());
        if(fielddepartment != null && fielddepartment.getId() != null){
            boolean success = fielddepartmentService.update(fielddepartment);
            if(success){
                cache.delete(CacheNameHelper.Receive_CacheNamePrefix + fielddepartment.getId());
                cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListByUIdFIdDId_CacheNamePrefix + "*");
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }

    /**
     * 获取场地主管部门详情
     * @param id
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据场地主管部门id获取场地主管部门详情", notes = "")
    @ApiImplicitParam(name = "id", value = "场地主管部门id", required = true, dataType = "Long", paramType = "path")
    @GetMapping("fielddepartment/{id}")
    public void receive(@PathVariable Long id,
                        HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.Receive_CacheNamePrefix + id;
        String json = cache.get(cacheName);
        if(json == null){
            Fielddepartment fielddepartment = fielddepartmentService.select(id);
            json = Result.build(ResultType.Success).appendData("fielddepartment",fielddepartment ).convertIntoJSON();
            if(fielddepartment != null){
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    /**
     * 根据学校id，场地id，部门id和页码来分页查询场地主管部门
     * @param universityId
     * @param fieldId
     * @param departmentId
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据学校id，场地id，部门id和页码来分页查询场地主管部门", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name="universityId", value = "学校id", required = true, dataType = "Long", paramType = "path"),
            @ApiImplicitParam(name="fieldId", value = "场地id", required = true, dataType = "Long", paramType = "path"),
            @ApiImplicitParam(name="departmentId", value = "部门id", required = true, dataType = "Long", paramType = "path"),
            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/fielddepartment/listByUIdFIdDId/{universityId}/{fieldId}/{departmentId}/{pageNum}")
    public void listByCid(@PathVariable Long universityId,
                          @PathVariable Long fieldId,
                          @PathVariable Long departmentId,
                          @PathVariable Integer pageNum,
                          HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByUIdFIdDId_CacheNamePrefix + universityId + "_" + fieldId + "_" + departmentId + "_"+ pageNum;
        String json = cache.get(cacheName);
        if (json == null) {
            PageInfo<Fielddepartment> pageInfo = fielddepartmentService.selectPageByUIdFIdDId(pageNum, universityId, fieldId,departmentId);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if (pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    /**
     * 清除所有缓存
     * @return
     */
    @ApiOperation(value = "清除所有缓存", notes = "")
    @DeleteMapping("fielddepartment/cache")
    @ResponseBody
    public Result destroyCache(){
        cache.deleteByPaterm(CacheNameHelper.Receive_CacheNamePrefix + "*");
        cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
        cache.deleteByPaterm(CacheNameHelper.ListByUIdFIdDId_CacheNamePrefix + "*");
        return Result.build(ResultType.Success);
    }

}
