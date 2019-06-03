package edu.uni.place.controller;

import com.github.pagehelper.PageInfo;
import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.place.bean.Fieldincharge;
import edu.uni.place.service.FieldinchargeService;
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
public class FieldinchargeController {
    @Autowired
    private FieldinchargeService fieldinchargeService;

    @Autowired
    private RedisCache cache;

    /**
     * 内部类，专门用来管理每个方法所对应缓存的名称。
     */
    static class CacheNameHelper{
        // {场地管理员id}
        private static final String Receive_CacheNamePrefix = "e_fieldincharge_";
        // {页码}
        private static final String List_CacheNamePrefix = "e_fieldincharge_list_";
        // {学校id}_{场地id}_{用户id}_{页码}
        private static final String ListByUIdFIdUId_CacheNamePrefix = "e_field_listByUIdFIdUId_list";
    }

    /**
     * 新增场地管理员
     * @param fieldincharge
     * @return
     */
    @ApiOperation(value="新增场地管理员", notes = "")
    @ApiImplicitParam(name= "fieldincharge",value = "场地管理员实体类", required = true, dataType = "Fieldincharge")
    @PostMapping("fieldincharget")
    @ResponseBody
    public Result create(@RequestBody(required = false) Fieldincharge fieldincharge){
        //初始场地管理员
        fieldincharge.setDatetime(Calendar.getInstance().getTime());
        if(fieldincharge != null){
            boolean success = fieldinchargeService.insert(fieldincharge);
            if(success){
                cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");

                cache.deleteByPaterm(CacheNameHelper.ListByUIdFIdUId_CacheNamePrefix + "*");
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }

    /**
     * 删除场地管理员
     * @param id
     * @return
     */
    @ApiOperation(value = "根据场地管理员id删除场地管理员", notes = "")
    @ApiImplicitParam(name = "id", value = "场地主管部门id", required = true, dataType = "Long", paramType = "path")
    @DeleteMapping("fieldincharge/{id}")
    @ResponseBody
    public Result destroy(@PathVariable Long id){
        boolean success = fieldinchargeService.delete(id);
        if(success){
            cache.delete(CacheNameHelper.Receive_CacheNamePrefix + id);
            cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.ListByUIdFIdUId_CacheNamePrefix + "*");
            return Result.build(ResultType.Success);
        }else{
            return Result.build(ResultType.Failed);
        }
    }

    /**
     * 修改场地管理员
     * @param fieldincharge
     * @return
     */
    @ApiOperation(value="根据场地管理员id修改场地管理员")
    @ApiImplicitParam(name="fieldincharge", value = "场地管理员实体类", required = true, dataType = "Fieldincharge")
    @PutMapping("fieldincharge")
    @ResponseBody
    public Result update(@RequestBody(required = false) Fieldincharge fieldincharge){
        //初始场地管理员
        fieldincharge.setDatetime(Calendar.getInstance().getTime());
        if(fieldincharge != null && fieldincharge.getId() != null){
            boolean success = fieldinchargeService.update(fieldincharge);
            if(success){
                cache.delete(CacheNameHelper.Receive_CacheNamePrefix + fieldincharge.getId());
                cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListByUIdFIdUId_CacheNamePrefix + "*");
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }

    /**
     * 获取场地管理员详情
     * @param id
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据场地管理员id获取场地管理员详情", notes = "")
    @ApiImplicitParam(name = "id", value = "场地管理员id", required = true, dataType = "Long", paramType = "path")
    @GetMapping("fieldincharge/{id}")
    public void receive(@PathVariable Long id,
                        HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.Receive_CacheNamePrefix + id;
        String json = cache.get(cacheName);
        if(json == null){
            Fieldincharge fieldincharge = fieldinchargeService.select(id);
            json = Result.build(ResultType.Success).appendData("fieldincharge",fieldincharge ).convertIntoJSON();
            if(fieldincharge != null){
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    /**
     * 根据学校id，场地id，用户id和页码来分页查询场地管理员
     * @param universityId
     * @param fieldId
     * @param userId
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据学校id，场地id，用户id和页码来分页查询场地管理员", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name="universityId", value = "学校id", required = true, dataType = "Long", paramType = "path"),
            @ApiImplicitParam(name="fieldId", value = "场地id", required = true, dataType = "Long", paramType = "path"),
            @ApiImplicitParam(name="userId", value = "用户id", required = true, dataType = "Long", paramType = "path"),
            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/products/listByUIdFIdUId/{universityId}/{fieldId}/{userId}/{pageNum}")
    public void listByCid(@PathVariable Long universityId,
                          @PathVariable Long fieldId,
                          @PathVariable Long userId,
                          @PathVariable Integer pageNum,
                          HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByUIdFIdUId_CacheNamePrefix + universityId + "_" + fieldId + "_" + userId + "_"+ pageNum;
        String json = cache.get(cacheName);
        if (json == null) {
            PageInfo<Fieldincharge> pageInfo = fieldinchargeService.selectPageByUIdFIdUId(pageNum, universityId, fieldId,userId);
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
    @DeleteMapping("fieldincharge/cache")
    @ResponseBody
    public Result destroyCache(){
        cache.deleteByPaterm(CacheNameHelper.Receive_CacheNamePrefix + "*");
        cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
        cache.deleteByPaterm(CacheNameHelper.ListByUIdFIdUId_CacheNamePrefix + "*");
        return Result.build(ResultType.Success);
    }


}
