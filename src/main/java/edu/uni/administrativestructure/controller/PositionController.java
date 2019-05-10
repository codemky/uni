package edu.uni.administrativestructure.controller;

import com.github.pagehelper.PageInfo;
import edu.uni.administrativestructure.bean.Position;
import edu.uni.administrativestructure.service.PositionService;
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
import java.util.LinkedList;
import java.util.List;

/**
 * author：黄育林
 * create: 2019.4.23
 * modified: 2019.5.7
 */

@Api(description = "行政架构模块")
@Controller
@RequestMapping("json/administrativestructure")
public class PositionController {
    @Autowired
    private PositionService positionService;

    @Autowired
    private RedisCache cache;

    /**
     * 内部类，专门用来管理每个方法所对应缓存的名称。
     */
    static class CacheNameHelper{
        // as_p_position_{岗位信息id}
        private static final String Receive_CacheNamePrefix = "as_position_";
        // as_p_position_list_{页码}
        private static final String List_CacheNamePrefix = "as_position_list_";
        public static final String ListAll_CacheName = "as_position_listAll";
        private static final String ListByUniversityID_CacheNamePrefix = "as_position_listByUniversityID_";
    }

    /**
     * 新增岗位信息
     * @param position
     * @return
     */
    @ApiOperation(value="新增岗位信息", notes = "已测试")
    @ApiImplicitParam(name= "position",value = "岗位信息实体类", required = true, dataType = "Position")
    @PostMapping("position")
    @ResponseBody
    public Result create(@RequestBody(required = false) Position position){
        if(position != null){
            boolean success = positionService.insert(position);
            if(success){
                cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName);
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
     * 删除岗位信息
     * @param id
     * @return
     */
    @ApiOperation(value = "根据岗位信息id删除岗位信息", notes = "已测试")
    @ApiImplicitParam(name = "id", value = "岗位信息id", required = true, dataType = "bigint", paramType = "path")
    @DeleteMapping("position/{id}")
    @ResponseBody
    public Result destroy(@PathVariable long id){
        boolean success = positionService.delete(id);
        if(success){
            cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName);
            cache.deleteByPaterm(CacheNameHelper.ListByUniversityID_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.Receive_CacheNamePrefix + "*");
            return Result.build(ResultType.Success);
        }else{
            return Result.build(ResultType.Failed);
        }
    }

    /**
     * 修改岗位信息
     * @param position
     * @return
     */
    @ApiOperation(value="根据岗位信息id修改岗位信息", notes = "已测试")
    @ApiImplicitParam(name="position", value = "岗位信息实体类", required = true, dataType = "Position")
    @PutMapping("position")
    @ResponseBody
    public Result update(@RequestBody(required = false) Position position){
        if(position != null && position.getId() != null){
            boolean success = positionService.update(position);
            if(success){
                cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName);
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
     * 获取岗位信息详情
     * @param id
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据岗位信息id获取岗位信息详情", notes = "已测试")
    @ApiImplicitParam(name = "id", value = "岗位信息id", required = true, dataType = "bigint", paramType = "path")
    @GetMapping("position/{id}")
    public void receive(@PathVariable long id,
                        HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.Receive_CacheNamePrefix + id;
        String json = cache.get(cacheName);
        if(json == null){
            Position position= positionService.select(id);
            json = Result.build(ResultType.Success).appendData("position", position).convertIntoJSON();
            if(position != null){
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    /**
     * 根据页码分页查询所有岗位信息
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据页码分页查询所有岗位信息", notes = "已测试")
    @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    @GetMapping(value = "/positions/list/{pageNum}")
    public void list(
            @PathVariable Integer pageNum ,
            HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.List_CacheNamePrefix + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<Position> pageInfo = positionService.selectPage(pageNum);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    /**
     * 列举所有岗位信息
     * @param response
     * @throws IOException
     */
    @ApiOperation(value="列举所有岗位信息", notes="已测试")
    @GetMapping(value = "/positions/listAll")
    public void list(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListAll_CacheName;
        String json = cache.get(cacheName);
        if(json == null){
            json = Result.build(ResultType.Success).appendData("positions", positionService.selectAll()).convertIntoJSON();
            cache.set(cacheName, json);
        }
        response.getWriter().write(json);
    }

    /**
     * 根据学校id和页码来分页查询岗位信息
     * @param university_id
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据学校id和页码来分页查询岗位信息", notes = "已测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name="university_id", value = "学校id", required = true, dataType = "bigint", paramType = "path"),
            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/positions/listByUniversityID/{university_id}/{pageNum}")
    public void listByUniversityID(@PathVariable long university_id,
                                   @PathVariable Integer pageNum,
                                   HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByUniversityID_CacheNamePrefix + university_id + "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<Position> pageInfo = positionService.selectPageByUniversity(pageNum, university_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }


}
