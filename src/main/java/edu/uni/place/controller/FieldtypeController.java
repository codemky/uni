package edu.uni.place.controller;

import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.place.bean.Fieldtype;
import edu.uni.place.service.FieldtypeService;
import edu.uni.utils.RedisCache;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;

/**
 * @Author 潘绍豪
 * @Create 2019/5/5
 * @Description
 * @Since 1.0.0
 */
@Api(description = "场地基础信息")
@Controller
@RequestMapping("json/place")
public class FieldtypeController {
    @Autowired
    private FieldtypeService fieldtypeService;

    @Autowired
    private RedisCache cache;

    /**
     * 内部类，专门用来管理每个方法所对应缓存的名称。
     */
    static class CacheNameHelper{
        // {场地类型id}
        private static final String Receive_CacheNamePrefix = "e_fieldtype_";

        public static final String ListAll_CacheName = "e_fieldtypes_listAll";
    }


    /**
     * 新增场地类型
     * @param fieldtype
     * @return
     */
    @ApiOperation(value="新增场地类型", notes = "")
    @ApiImplicitParam(name= "fieldtype",value = "场地类型实体类", required = true, dataType = "Fieldtype")
    @PostMapping("fieldtype")
    @ResponseBody
    public Result create(@RequestBody(required = false) Fieldtype fieldtype){
        //初始化场地类型
        fieldtype.setDatetime(Calendar.getInstance().getTime());
        if(fieldtype != null){
            boolean success = fieldtypeService.insert(fieldtype);
            if(success){
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }

    /**
     * 删除场地类型
     * @param id
     * @return
     */
    @ApiOperation(value = "根据场地类型id删除场地类型", notes = "")
    @ApiImplicitParam(name = "id", value = "场地类型id", required = true, dataType = "Long", paramType = "path")
    @DeleteMapping("fieldtype/{id}")
    @ResponseBody
    public Result destroy(@PathVariable Long id){
        boolean success = fieldtypeService.delete(id);
        if(success){
            cache.delete(CacheNameHelper.Receive_CacheNamePrefix + id);
            return Result.build(ResultType.Success);
        }else{
            return Result.build(ResultType.Failed);
        }
    }

    /**
     * 修改场地类型
     * @param fieldtype
     * @return
     */
    @ApiOperation(value="根据场地类型id修改场地类型信息")
    @ApiImplicitParam(name="fieldtype", value = "场地类型实体类", required = true, dataType = "Fieldtype")
    @PutMapping("fieldtype")
    @ResponseBody
    public Result update(@RequestBody(required = false) Fieldtype fieldtype){
        //初始化场地类型
        fieldtype.setDatetime(Calendar.getInstance().getTime());
        if(fieldtype != null && fieldtype.getId() != null){
            boolean success = fieldtypeService.update(fieldtype);
            if(success){
                cache.delete(CacheNameHelper.Receive_CacheNamePrefix + fieldtype.getId());
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }

    /**
     * 列举所有场地类型
     * @param response
     * @throws IOException
     */
    @ApiOperation(value="列举所有场地类型", notes="")
    @GetMapping(value = "fieldtypes/listAll")
    public void list(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListAll_CacheName;
        String json = cache.get(cacheName);
        if(json == null){
            json = Result.build(ResultType.Success).appendData("fieldtypes", fieldtypeService.selectAll()).convertIntoJSON();
            cache.set(cacheName, json);
        }
        response.getWriter().write(json);
    }

    /**
     * 清除所有缓存
     * @return
     */
    @ApiOperation(value = "清除所有缓存", notes = "")
    @DeleteMapping("fieldtype/cache")
    @ResponseBody
    public Result destroyCache(){
        cache.deleteByPaterm(CacheNameHelper.Receive_CacheNamePrefix + "*");
        cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName + "*");
        return Result.build(ResultType.Success);
    }
}
