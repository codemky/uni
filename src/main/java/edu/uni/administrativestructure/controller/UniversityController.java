package edu.uni.administrativestructure.controller;

import com.github.pagehelper.PageInfo;
import edu.uni.administrativestructure.bean.University;
import edu.uni.administrativestructure.service.UniversityService;
import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.utils.RedisCache;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * author：黄育林
 * create: 2019.4.20
 * mofified：2019.4.28
 */

@Api(description = "行政架构模块")
@Controller
@RequestMapping("json/administrativestructure")
public class UniversityController {
    @Autowired
    private UniversityService universityService;

    @Autowired
    private RedisCache cache;

    /**
     * 内部类，专门用来管理每个get方法所对应缓存的名称。
     */
    static class CacheNameHelper{
        // as_u_university_{学校id}
        public static final String Receive_CacheNamePrefix = "as_university_";
        private static final String List_CacheNamePrefix = "as_university_list_";
        // as_u_university_listAll
        public static final String ListAll_CacheName = "as_university_listAll";
    }

    /**
     * 新增学校
     * @param university
     * @return
     */
    @ApiOperation(value="新增学校", notes="已测试")
    @ApiImplicitParam(name = "university", value = "学校详情实体", required = true, dataType = "University")
    @PostMapping("/university")
    @ResponseBody
    public Result create(@RequestBody(required = false) University university){
        if(university != null){
            boolean success = universityService.insert(university);
            if(success){
                // 清空相关缓存
                cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName + "*");
                cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.Receive_CacheNamePrefix + "*");
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }

    /**
     * 删除学校
     * @param id
     * @return
     */
    @ApiOperation(value="删除学校", notes="已测试")
    @ApiImplicitParam(name = "id", value = "学校id", required = true, dataType = "bigint", paramType = "path")
    @DeleteMapping("/university/{id}")
    @ResponseBody
    public Result destroy(@PathVariable long id){
        boolean success = universityService.delete(id);
        if(success){
            // 清空相关缓存
            cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName + "*");
            cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.Receive_CacheNamePrefix + "*");
            return Result.build(ResultType.Success);
        }else{
            return Result.build(ResultType.Failed);
        }
    }

    /**
     * 更新学校
     * @param university
     * @return
     */
    @ApiOperation(value="根据学校id更新学校信息", notes="已测试")
    @ApiImplicitParam(name = "university", value = "学校实体", required = true, dataType = "University")
    @PutMapping("/university")
    @ResponseBody
    public Result update(@RequestBody(required = false) University university){
        if(university != null && university.getId() != null){
            boolean success = universityService.update(university);
            if(success){
                cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName + "*");
                cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.Receive_CacheNamePrefix + "*");
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }

    /**
     * 获取学校详情
     * @param id
     * @param response
     * @throws IOException
     */
    @ApiOperation(value="根据学校id获取学校详情", notes="已测试")
    @ApiImplicitParam(name = "id", value = "学校id", required = false, dataType = "bigint", paramType = "path")
    @GetMapping("/university/{id}")
    public void receive(@PathVariable long id, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.Receive_CacheNamePrefix + id;
        String json = cache.get(cacheName);
        if(json == null){
            json = Result.build(ResultType.Success).appendData("university",  universityService.select(id)).convertIntoJSON();
            cache.set(cacheName, json);
        }
        response.getWriter().write(json);
    }

    /**
     * 列举所有学校
     * @param response
     * @throws IOException
     */
    @ApiOperation(value="列举所有学校", notes="已测试")
    @GetMapping(value = "/universitys/listAll")
    public void list(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListAll_CacheName;
        String json = cache.get(cacheName);
        if(json == null){
            json = Result.build(ResultType.Success).appendData("universitys", universityService.selectAll()).convertIntoJSON();
            cache.set(cacheName, json);
        }
        response.getWriter().write(json);
    }

    /**
     * 根据页码分页查询所有学校
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据页码分页查询所有学校", notes = "已测试")
    @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    @GetMapping(value = "/universities/list/{pageNum}")
    public void list(
            @PathVariable Integer pageNum ,
            HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.List_CacheNamePrefix + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<University> pageInfo = universityService.selectPage(pageNum);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

}
