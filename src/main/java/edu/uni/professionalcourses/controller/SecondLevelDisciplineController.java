package edu.uni.professionalcourses.controller;

import com.github.pagehelper.PageInfo;
import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.professionalcourses.bean.SecondLevelDiscipline;
import edu.uni.professionalcourses.service.SecondLevelDisciplineService;
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
import java.util.List;

/**author：曹中耀
 create：2019.5.11
 modified：2019.5.11
 功能：SecondLevelDiscipline的增删查改
 */
@Api(description = "专业课程模块")
@Controller
@RequestMapping("json/professionalcourses")
public class SecondLevelDisciplineController {
    @Autowired
    private SecondLevelDisciplineService secondLevelDisciplineService;
    @Autowired
    private RedisCache cache;
    /**
     * 内部类，专门用来管理每个get方法所对应缓存的名称。
     */
    static class CacheNameHelper{
        //pc_SecondLevelDiscipline_listAll
        public static final String ListAll_CacheName = "pc_SecondLevelDiscipline_listAll";
        // pc_SecondLevelDiscipline_{类别id}
        public static final String Receive_CacheNamePrefix = "pc_SecondLevelDiscipline_";
        // pc_SecondLevelDiscipline_list_{页码}
        private static final String List_CacheNamePrefix = "pc_SecondLevelDiscipline_list_";
        // pc_SecondLevelDiscipline_listByFirstLevelDisciplineID_{一级学科表一级学科ID}_{页码}
        private static final String ListByFirstLevelDiscipline_CacheNamePrefix = "pc_SecondLevelDiscipline_listByFirstLevelDisciplineID_";
    }
    //显示所有数据
    @ApiOperation(value = "显示SecondLevelDiscipline中所有信息",notes = "未测试")
    @GetMapping("secondLevelDiscipline/listAll")
    public void listAll(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String json =cache.get(CacheNameHelper.ListAll_CacheName);
        if(json==null){
            // System.out.println("查询数据库");
            List<SecondLevelDiscipline> secondLevelDisciplines= secondLevelDisciplineService.selectAll();
            json= Result.build(ResultType.Success).appendData("secondLevelDiscipline",secondLevelDisciplines).convertIntoJSON();
            cache.set(CacheNameHelper.ListAll_CacheName,json);
        }
        response.getWriter().write(json);
    }
    /**
     * 新增SecondLevelDiscipline信息
     * @param secondLevelDiscipline
     * @return
     */

    @ApiOperation(value="新增SecondLevelDiscipline信息", notes="未测试")
    @ApiImplicitParam(name = "secondLevelDiscipline", value = "SecondLevelDiscipline详情实体", required = true, dataType = "SecondLevelDiscipline")
    @PostMapping("/secondLevelDiscipline")
    @ResponseBody
    public Result create(@RequestBody(required = false) SecondLevelDiscipline secondLevelDiscipline){
        if(secondLevelDiscipline!= null){
            boolean success = secondLevelDisciplineService.insert(secondLevelDiscipline);
            if(success){
                // 清空相关缓存
                cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName );
                cache.deleteByPaterm(CacheNameHelper.ListByFirstLevelDiscipline_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.Receive_CacheNamePrefix + "*");
                return Result.build(ResultType.Success);
            }
            else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }
    /**
     * 根据id删除SecondLevelDiscipline
     * @param id
     * @return
     */
    @ApiOperation(value="根据id删除SecondLevelDiscipline信息", notes="未测试")
    @ApiImplicitParam(name = "id", value = "SecondLevelDiscipline主id", required = true, dataType = "bigint", paramType = "path")
    @DeleteMapping("secondLevelDiscipline/{id}")
    @ResponseBody
    public Result destroy(@PathVariable Long id){
        boolean success = secondLevelDisciplineService.delete(id);
        if(success){
            // 清空相关缓存
            cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName );
            cache.deleteByPaterm(CacheNameHelper.ListByFirstLevelDiscipline_CacheNamePrefix+ "*");
            cache.deleteByPaterm(CacheNameHelper.Receive_CacheNamePrefix + "*");
            return Result.build(ResultType.Success);
        }else{
            return Result.build(ResultType.Failed);
        }
    }
    /**
     * 根据id更新SecondLevelDiscipline信息
     * @param secondLevelDiscipline
     * @return
     */
    @ApiOperation(value="根据id更新SecondLevelDiscipline信息", notes="未测试")
    @ApiImplicitParam(name = "secondLevelDiscipline", value = "SecondLevelDiscipline实体", required = true, dataType = "SecondLevelDiscipline")
    @PutMapping("/secondLevelDiscipline")
    @ResponseBody
    public Result update(@RequestBody(required = false) SecondLevelDiscipline secondLevelDiscipline){
        if(secondLevelDiscipline != null && secondLevelDiscipline.getId() != null){
            boolean success = secondLevelDisciplineService.update(secondLevelDiscipline);
            if(success){
                cache.delete(CacheNameHelper.Receive_CacheNamePrefix + secondLevelDiscipline.getId());
                cache.delete(CacheNameHelper.ListAll_CacheName);
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }
    /**
     * 根据id查询SecondLevelDiscipline信息
     * @param id
     * @param response
     * @throws IOException
     */
    @ApiOperation(value="根据id查询SecondLevelDiscipline信息", notes="未测试")
    @ApiImplicitParam(name = "id", value = "SecondLevelDiscipline主id", required = false, dataType = "bigint", paramType = "path")
    @GetMapping("/secondLevelDiscipline/{id}")
    public void receive(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.Receive_CacheNamePrefix + id;
        String json = cache.get(cacheName);
        if(json == null){
            json = Result.build(ResultType.Success).appendData("secondLevelDiscipline",  secondLevelDisciplineService.select(id)).convertIntoJSON();
            cache.set(cacheName, json);
        }
        response.getWriter().write(json);
    }

    /**
     * 根据页码分页查询所有该页码所有SecondLevelDiscipline信息
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据页码分页查询该页码所有SecondLevelDiscipline信息", notes = "未测试")
    @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    @GetMapping(value = "/secondLevelDiscipline/list/{pageNum}")
    public void list(
            @PathVariable Integer pageNum ,
            HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.List_CacheNamePrefix + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<SecondLevelDiscipline> pageInfo = secondLevelDisciplineService.selectPage(pageNum);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }
    /**
     * 根据SecondLevelDiscipline表的一级学科id和页码来分页查询
     * @param first_level_discipline_id
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据SecondLevelDiscipline表的学校id和页码来分页查询", notes = "测试中")
    @ApiImplicitParams({

            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/secondLevelDiscipline/listByFirstLevelDisciplineId/{first_level_discipline_id}/{pageNum}")
    public void listByDisciplineCategoryID(@PathVariable long first_level_discipline_id,
                                           @PathVariable Integer pageNum,
                                           HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByFirstLevelDiscipline_CacheNamePrefix + first_level_discipline_id + "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<SecondLevelDiscipline> pageInfo = secondLevelDisciplineService.selectPageByFirstLevelDisciplineID(pageNum, first_level_discipline_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }
}
