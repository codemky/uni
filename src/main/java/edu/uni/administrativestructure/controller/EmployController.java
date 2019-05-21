package edu.uni.administrativestructure.controller;

import com.github.pagehelper.PageInfo;
import edu.uni.administrativestructure.bean.Employ;
import edu.uni.administrativestructure.service.EmployService;
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
 * create: 2019.4.25
 * modified:2019.5.7
 */

@Api(description = "行政架构模块")
@Controller
@RequestMapping("json/administrativestructure")
public class EmployController {
    @Autowired
    private EmployService employService;

    @Autowired
    private RedisCache cache;

    /**
     * 内部类，专门用来管理每个方法所对应缓存的名称。
     */
    static class CacheNameHelper{
        // as_e_employ_{部门人员记录id}
        private static final String Receive_CacheNamePrefix = "as_employ_";
        // as_e_employ_list_{页码}
        private static final String List_CacheNamePrefix = "as_employ_list_";
        // as_sub_subdepartments_listByDepartment_{部门id}_{页码}
        private static final String ListByDepartmentID_CacheNamePrefix = "as_employ_listByDepartmentID_";
        private static final String ListByUniversityID_CacheNamePrefix = "as_employ_listByUniversityID_";
        private static final String ListByWorkerID_CacheNamePrefix = "as_employ_listByWorkerID_";
        public static final String ListAll_CacheName = "as_employ_listAll";
    }

    /**
     * 新增部门人员记录
     * @param employ
     * @return
     */
    @ApiOperation(value="新增部门人员记录", notes = "已测试")
    @ApiImplicitParam(name= "employ",value = "部门人员记录实体类", required = true, dataType = "Employ")
    @PostMapping("employ")
    @ResponseBody
    public Result create(@RequestBody(required = false) Employ employ){
        if(employ != null){
            boolean success = employService.insert(employ);
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
     * 删除部门人员记录
     * @param id
     * @return
     */
    @ApiOperation(value = "根据部门人员记录id删除部门人员记录", notes = "已测试")
    @ApiImplicitParam(name = "id", value = "部门人员记录id", required = true, dataType = "bigint", paramType = "path")
    @DeleteMapping("employ/{id}")
    @ResponseBody
    public Result destroy(@PathVariable long id){
        boolean success = employService.delete(id);
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
     * 修改部门人员记录
     * @param employ
     * @return
     */
    @ApiOperation(value="根据部门人员记录id修改部门人员记录信息", notes = "已测试")
    @ApiImplicitParam(name="employ", value = "部门人员记录实体类", required = true, dataType = "Employ")
    @PutMapping("employ")
    @ResponseBody
    public Result update(@RequestBody(required = false) Employ employ){
        if(employ != null && employ.getId() != null){
            boolean success = employService.update(employ);
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
     * 获取部门人员记录详情
     * @param id
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据部门人员记录id获取部门人员记录详情", notes = "已测试")
    @ApiImplicitParam(name = "id", value = "部门人员记录id", required = true, dataType = "bigint", paramType = "path")
    @GetMapping("employ/{id}")
    public void receive(@PathVariable long id,
                        HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.Receive_CacheNamePrefix + id;
        String json = cache.get(cacheName);
        if(json == null){
            Employ employ= employService.select(id);
            json = Result.build(ResultType.Success).appendData("employ", employ).convertIntoJSON();
            if(employ != null){
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    /**
     * 根据页码分页查询所有部门人员记录
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据页码分页查询所有部门人员记录", notes = "已测试")
    @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    @GetMapping(value = "/employs/list/{pageNum}")
    public void list(
            @PathVariable Integer pageNum ,
            HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.List_CacheNamePrefix + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<Employ> pageInfo = employService.selectPage(pageNum);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    /**
     * 根据部门id和页码来分页查询部门人员记录
     * @param department_id
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据部门id和页码来分页查询部门人员记录", notes = "已测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name="department_id", value = "部门id", required = true, dataType = "bigint", paramType = "path"),
            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/employs/listByDepartmentID/{department_id}/{pageNum}")
    public void listByDepartmentID(@PathVariable long department_id,
                                   @PathVariable Integer pageNum,
                                   HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByDepartmentID_CacheNamePrefix + department_id + "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<Employ> pageInfo = employService.selectPageByDepartment(pageNum, department_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    /**
     * 根据学校id和页码来分页查询部门人员记录
     * @param university_id
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据学校id和页码来分页查询部门人员记录", notes = "已测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name="university_id", value = "学校id", required = true, dataType = "bigint", paramType = "path"),
            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/employs/listByUniversityID/{university_id}/{pageNum}")
    public void listByUniversityID(@PathVariable long university_id,
                                   @PathVariable Integer pageNum,
                                   HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByUniversityID_CacheNamePrefix + university_id + "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<Employ> pageInfo = employService.selectPageByUniversity(pageNum, university_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    /**
     * 根据雇员id和页码来分页查询部门人员记录
     * @param worker_id
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据雇员id和页码来分页查询部门人员记录", notes = "已测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name="worker_id", value = "雇员id", required = true, dataType = "bigint", paramType = "path"),
            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/employs/listByWorkerId/{worker_id}/{pageNum}")
    public void listByWorkerId(@PathVariable long worker_id,
                                   @PathVariable Integer pageNum,
                                   HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByWorkerID_CacheNamePrefix + worker_id + "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<Employ> pageInfo = employService.selectPageByEmployee(pageNum, worker_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

    /**
     * 列举所有部门人员记录
     * @param response
     * @throws IOException
     */
    @ApiOperation(value="列举所有部门人员记录", notes="已测试")
    @GetMapping(value = "/employs/listAll")
    public void list(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListAll_CacheName;
        String json = cache.get(cacheName);
        if(json == null){
            json = Result.build(ResultType.Success).appendData("employs", employService.selectAll()).convertIntoJSON();
            cache.set(cacheName, json);
        }
        response.getWriter().write(json);
    }
}
