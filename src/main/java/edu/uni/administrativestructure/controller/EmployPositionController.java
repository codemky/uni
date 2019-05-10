package edu.uni.administrativestructure.controller;

import com.github.pagehelper.PageInfo;
import edu.uni.administrativestructure.bean.EmployPosition;
import edu.uni.administrativestructure.service.EmployPositionService;
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
 * create: 2019.4.27
 * modified:2019.5.7
 */

@Api(description = "行政架构模块")
@Controller
@RequestMapping("json/administrativestructure")
public class EmployPositionController {
    @Autowired
    private EmployPositionService employPositionService;

    @Autowired
    private RedisCache cache;

    /**
     * 内部类，专门用来管理每个方法所对应缓存的名称。
     */
    static class CacheNameHelper{
        // as_ep_employPosition_{雇员岗位关系id}
        private static final String Receive_CacheNamePrefix = "as_employPosition_";
        // as_ep_employPosition_list_{页码}
        private static final String List_CacheNamePrefix = "as_employPosition_list_";
        // as_ep_employPositions_listByEmployID_{雇员岗位关系id}_{页码}
        private static final String ListByEmployID_CacheNamePrefix = "as_emplyPosition_listByEmployID_";
        private static final String ListByPositionID_CacheNamePrefix = "as_emplyPosition_listByPositionID_";
        private static final String ListByUniversityID_CacheNamePrefix = "as_employPosition_listByUniversityID_";
        public static final String ListAll_CacheName = "as_employPosition_listAll";
    }

    /**
     * 新增部门班级关系
     * @param employPosition
     * @return
     */
    @ApiOperation(value="新增雇员岗位关系", notes = "已测试")
    @ApiImplicitParam(name= "employPosition",value = "雇员岗位关系实体类", required = true, dataType = "EmployPosition")
    @PostMapping("employPosition")
    @ResponseBody
    public Result create(@RequestBody(required = false) EmployPosition employPosition){
        if(employPosition != null){
            boolean success = employPositionService.insert(employPosition);
            if(success){
                cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName);
                cache.deleteByPaterm(CacheNameHelper.ListByEmployID_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListByPositionID_CacheNamePrefix + "*");
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
     * 删除雇员岗位关系
     * @param id
     * @return
     */
    @ApiOperation(value = "根据雇员岗位关系id删除雇员岗位关系", notes = "已测试")
    @ApiImplicitParam(name = "id", value = "雇员岗位关系id", required = true, dataType = "bigint", paramType = "path")
    @DeleteMapping("employPosition/{id}")
    @ResponseBody
    public Result destroy(@PathVariable long id){
        boolean success = employPositionService.delete(id);
        if(success){
            cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName);
            cache.deleteByPaterm(CacheNameHelper.ListByEmployID_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.ListByPositionID_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.ListByUniversityID_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.Receive_CacheNamePrefix + "*");
            return Result.build(ResultType.Success);
        }else{
            return Result.build(ResultType.Failed);
        }
    }
    /**
     * 修改雇员岗位关系
     * @param employPosition
     * @return
     */
    @ApiOperation(value="根据雇员岗位关系id修改雇员岗位关系信息", notes = "已测试")
    @ApiImplicitParam(name="employPosition", value = "雇员岗位关系实体类", required = true, dataType = "EmployPosition")
    @PutMapping("employPosition")
    @ResponseBody
    public Result update(@RequestBody(required = false) EmployPosition employPosition){
        if(employPosition != null && employPosition.getId() != null){
            boolean success = employPositionService.update(employPosition);
            if(success){
                cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName);
                cache.deleteByPaterm(CacheNameHelper.ListByEmployID_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListByPositionID_CacheNamePrefix + "*");
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
     * 获取雇员岗位关系详情
     * @param id
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据雇员岗位关系id获取雇员岗位关系详情", notes = "已测试")
    @ApiImplicitParam(name = "id", value = "雇员岗位关系id", required = true, dataType = "bigint", paramType = "path")
    @GetMapping("employPosition/{id}")
    public void receive(@PathVariable long id,
                        HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.Receive_CacheNamePrefix + id;
        String json = cache.get(cacheName);
        if(json == null){
            EmployPosition employPosition = employPositionService.select(id);
            json = Result.build(ResultType.Success).appendData("employPosition", employPosition).convertIntoJSON();
            if(employPosition != null){
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    /**
     * 根据页码分页查询所有雇员岗位关系
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据页码分页查询所有雇员岗位关系", notes = "已测试")
    @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    @GetMapping(value = "/employPositions/list/{pageNum}")
    public void list(
            @PathVariable Integer pageNum ,
            HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.List_CacheNamePrefix + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<EmployPosition> pageInfo = employPositionService.selectPage(pageNum);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    /**
     * 根据雇员id和页码来分页查询部门班级关系
     * @param employ_id
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据雇员岗位id和页码来分页查询雇员岗位关系", notes = "已测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name="employ_id", value = "雇员id", required = true, dataType = "bigint", paramType = "path"),
            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/employPositios/listByEmployID/{employ_id}/{pageNum}")
    public void listByDepartmentID(@PathVariable long employ_id,
                                   @PathVariable Integer pageNum,
                                   HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByEmployID_CacheNamePrefix + employ_id + "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<EmployPosition> pageInfo = employPositionService.selectPageByEmploy(pageNum, employ_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    /**
     * 根据学校id和页码来分页查询雇员岗位关系
     * @param university_id
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据学校id和页码来分页查询雇员岗位关系", notes = "已测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name="university_id", value = "学校id", required = true, dataType = "bigint", paramType = "path"),
            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/employPositions/listByUniversityID/{university_id}/{pageNum}")
    public void listByUniversityID(@PathVariable long university_id,
                                   @PathVariable Integer pageNum,
                                   HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByUniversityID_CacheNamePrefix + university_id + "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<EmployPosition> pageInfo = employPositionService.selectPageByUniversity(pageNum, university_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    /**
     * 根据岗位id和页码来分页查询雇员岗位关系
     * @param position_id
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据岗位id和页码来分页查询雇员岗位关系", notes = "已测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name="position_id", value = "岗位id", required = true, dataType = "bigint", paramType = "path"),
            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/employPositions/listByPositionID/{position_id}/{pageNum}")
    public void listByPositionID(@PathVariable long position_id,
                                   @PathVariable Integer pageNum,
                                   HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByPositionID_CacheNamePrefix + position_id + "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<EmployPosition> pageInfo = employPositionService.selectPageByPosition(pageNum, position_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    /**
     * 列举所有雇员岗位关系
     * @param response
     * @throws IOException
     */
    @ApiOperation(value="列举所有雇员岗位关系", notes="已测试")
    @GetMapping(value = "/employPositions/listAll")
    public void list(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListAll_CacheName;
        String json = cache.get(cacheName);
        if(json == null){
            json = Result.build(ResultType.Success).appendData("employPositions", employPositionService.selectAll()).convertIntoJSON();
            cache.set(cacheName, json);
        }
        response.getWriter().write(json);
    }
}
