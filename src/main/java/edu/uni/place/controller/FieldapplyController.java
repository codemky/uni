package edu.uni.place.controller;

import com.github.pagehelper.PageInfo;
import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.place.bean.Fieldapply;
import edu.uni.place.service.FieldapplyService;
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
 * @Author 潘绍豪
 * @Create 2019/5/7
 * @Description
 * @Since 1.0.0
 */
@Api(description = "场地使用申请")
@Controller
@RequestMapping("json/place/fieldapply")
public class FieldapplyController {
    @Autowired
    private FieldapplyService fieldapplyService;

    @Autowired
    private RedisCache cache;

    /**
     * 内部类，专门用来管理每个方法所对应缓存的名称。
     */
    static class CacheNameHelper{
        // {场地使用申请id}
        private static final String Receive_CacheNamePrefix = "e_fieldapply_";
        // {页码}
        private static final String List_CacheNamePrefix = "e_fieldapply_list_";
        // {学校id}_{场地id}_{用户id}_{校历id}_{页码}
        private static final String ListByUIdFIdUIdCId_CacheNamePrefix = "e_field_listByUIdFIdUIdCId_list";
    }

    /**
     * 新增场地使用申请
     * @param fieldapply
     * @return
     */
    @ApiOperation(value="新增场地使用申请", notes = "")
    @ApiImplicitParam(name= "fieldapply",value = "场地使用申请实体类", required = true, dataType = "Fieldapply")
    @PostMapping("fieldapply")
    @ResponseBody
    public Result create(@RequestBody(required = false) Fieldapply fieldapply){
        fieldapply.setCanlendarId((long) 1);
        if(fieldapply != null){
            boolean success = fieldapplyService.insert(fieldapply);
            if(success){
                cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListByUIdFIdUIdCId_CacheNamePrefix + "*");
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }

    /**
     * 删除场地使用申请
     * @param id
     * @return
     */
    @ApiOperation(value = "根据场地使用申请id删除场地使用申请", notes = "")
    @ApiImplicitParam(name = "id", value = "场地使用申请id", required = true, dataType = "Long", paramType = "path")
    @DeleteMapping("fieldapply/{id}")
    @ResponseBody
    public Result destroy(@PathVariable Long id){
        boolean success = fieldapplyService.delete(id);
        if(success){
            cache.delete(CacheNameHelper.Receive_CacheNamePrefix + id);
            cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.ListByUIdFIdUIdCId_CacheNamePrefix + "*");
            return Result.build(ResultType.Success);
        }else{
            return Result.build(ResultType.Failed);
        }
    }

    /**
     * 修改场地使用申请
     * @param fieldapply
     * @return
     */
    @ApiOperation(value="根据场地使用申请id修改场地使用申请")
    @ApiImplicitParam(name="fieldapply", value = "场地使用申请实体类", required = true, dataType = "Fieldapply")
    @PutMapping("fieldapply")
    @ResponseBody
    public Result update(@RequestBody(required = false) Fieldapply fieldapply){
        fieldapply.setCanlendarId((long) 1);
        if(fieldapply != null && fieldapply.getId() != null){
            boolean success = fieldapplyService.update(fieldapply);
            if(success){
                cache.delete(CacheNameHelper.Receive_CacheNamePrefix + fieldapply.getId());
                cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListByUIdFIdUIdCId_CacheNamePrefix + "*");
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }

    /**
     * 获取场地使用申请详情
     * @param id
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据场地使用申请id获取场地使用申请详情", notes = "")
    @ApiImplicitParam(name = "id", value = "场地使用申请id", required = true, dataType = "Long", paramType = "path")
    @GetMapping("fieldapply/{id}")
    public void receive(@PathVariable Long id,
                        HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.Receive_CacheNamePrefix + id;
        String json = cache.get(cacheName);
        if(json == null){
            Fieldapply fieldapply = fieldapplyService.select(id);
            json = Result.build(ResultType.Success).appendData("fieldapply",fieldapply ).convertIntoJSON();
            if(fieldapply != null){
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    /**
     * 根据学校id，场地id，用户id，校历id和页码来分页查询场地使用申请
     * @param universityId
     * @param fieldId
     * @param userId
     * @param canlendarId
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据学校id，场地id，用户id，校历id和页码来分页查询场地使用申请", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name="universityId", value = "学校id", required = true, dataType = "Long", paramType = "path"),
            @ApiImplicitParam(name="fieldId", value = "场地id", required = true, dataType = "Long", paramType = "path"),
            @ApiImplicitParam(name="userId", value = "用户id", required = true, dataType = "Long", paramType = "path"),
            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/products/listByUIdFIdUIdCId/{universityId}/{fieldId}/{userId}/{pageNum}")
    public void listByCid(@PathVariable Long universityId,
                          @PathVariable Long fieldId,
                          @PathVariable Long userId,
                          @PathVariable Integer pageNum,
                          HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByUIdFIdUIdCId_CacheNamePrefix + universityId + "_" + fieldId + "_" + userId + "_"+ pageNum;
        String json = cache.get(cacheName);
        if (json == null) {
            PageInfo<Fieldapply> pageInfo = fieldapplyService.selectPageByUIdFIdUIdCId(pageNum, universityId, fieldId,userId);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if (pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }


}
