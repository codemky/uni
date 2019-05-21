package edu.uni.professionalcourses.controller;

import com.github.pagehelper.PageInfo;
import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.professionalcourses.bean.Academic;
import edu.uni.professionalcourses.service.AcademicService;
import edu.uni.utils.RedisCache;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
/**author：曹中耀
 create：2019.5.1
 modified：2019.5.11
 功能： AcademicController的增删查改
 */
@Api(description = "专业课程模块")
@Controller
@RequestMapping("json/professionalcourses")
public class AcademicController {
    @Autowired
    private AcademicService academicService;
    @Autowired
    private RedisCache cache;
    /**
     * 内部类，专门用来管理每个get方法所对应缓存的名称。
     */
    static class CacheNameHelper{
        //pc_Academic_listAll
        public static final String ListAll_CacheName = "pc_Academic_listAll";
        // pc_Academic_{类别id}
        public static final String Receive_CacheNamePrefix = "pc_Academic_";
        // pc_Academic_list_{页码}
        private static final String List_CacheNamePrefix = "pc_Academic_list_";


    }
    //显示所有数据
    @ApiOperation(value = "显示Academic中所有信息",notes = "未测试")
    @GetMapping("academic/listAll")
    public void listAll(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String json =cache.get(CacheNameHelper.ListAll_CacheName);
        if(json==null){
            // System.out.println("查询数据库");
            List<Academic> academics= academicService.selectAll();
            json= Result.build(ResultType.Success).appendData("academic",academics).convertIntoJSON();
            cache.set(CacheNameHelper.ListAll_CacheName,json);
        }
        response.getWriter().write(json);
    }
    /**
     * 新增Academic信息
     * @param academic
     * @return
     */
    @ApiOperation(value="新增先修课程信息", notes = "未测试")
    @ApiImplicitParam(name="academic",value ="学位实体类", required = true, dataType ="Academic")
    @PostMapping("academic")
    @ResponseBody
    public Result create(@RequestBody(required = false) Academic academic){
        if(academic!= null){
            boolean success = academicService.insert(academic);
            if(success){
                cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName );

                cache.deleteByPaterm(CacheNameHelper.Receive_CacheNamePrefix + "*");
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }
    /**
     * 根据id删除Academic信息
     * @param id
     * @return
     */
    @ApiOperation(value="根据id删除Academic信息", notes="未测试")
    @ApiImplicitParam(name = "id", value = "Academic主id", required = true, dataType = "bigint", paramType = "path")
    @DeleteMapping("/academic/{id}")
    @ResponseBody
    public Result destroy(@PathVariable Long id){
        boolean success = academicService.delete(id);
        if(success){
            // 清空相关缓存
            cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName );
            cache.deleteByPaterm(CacheNameHelper.Receive_CacheNamePrefix + id);
            return Result.build(ResultType.Success);
        }else{
            return Result.build(ResultType.Failed);
        }
    }
    /**
     * 根据id更新Academic信息
     * @param academic
     * @return
     */
    @ApiOperation(value="根据id更新Academic信息", notes="已测试")
    @ApiImplicitParam(name = "academic", value = "Academic实体",
            required = true, dataType = "Academic")
    @PutMapping("/academic")
    @ResponseBody
    public Result update(@RequestBody(required = false) Academic academic){
        if(academic != null && academic.getId() != null){
            boolean success = academicService.update(academic);
            if(success){
                cache.delete(CacheNameHelper.Receive_CacheNamePrefix + academic.getId());
                cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName );

                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }
    /**
     * 根据id查询Academic信息
     * @param id
     * @param response
     * @throws IOException
     */
    @ApiOperation(value="根据id查询Academic信息", notes="未测试")
    @ApiImplicitParam(name = "id", value = "Academic主id", required = false, dataType = "bigint", paramType = "path")
    @GetMapping("/academic{id}")
    public void receive(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.Receive_CacheNamePrefix + id;
        String json = cache.get(cacheName);
        if(json == null){
            json = Result.build(ResultType.Success).appendData("academic",
                    academicService.select(id)).convertIntoJSON();
            cache.set(cacheName, json);
        }
        response.getWriter().write(json);
    }

    /**
     * 根据页码分页查询所有该页码所有Academic信息
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据页码分页查询该页码所有Academic信息", notes = "未测试")
    @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    @GetMapping(value = "/academic/list/{pageNum}")
    public void list(
            @PathVariable Integer pageNum ,
            HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.List_CacheNamePrefix + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<Academic> pageInfo = academicService.selectPage(pageNum);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

}
