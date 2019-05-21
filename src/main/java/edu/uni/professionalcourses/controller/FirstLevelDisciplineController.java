package edu.uni.professionalcourses.controller;

import com.github.pagehelper.PageInfo;
import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.professionalcourses.bean.FirstLevelDiscipline;
import edu.uni.professionalcourses.service.FirstLevelDisciplineService;
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
 功能： FirstLevelDiscipline的增删查改
 */
@Api(description = "专业课程模块")
@Controller
@RequestMapping("json/professionalcourses")
public class FirstLevelDisciplineController {

    @Autowired
    private FirstLevelDisciplineService firstLevelDisciplineService;
    @Autowired
    private RedisCache cache;
    /**
     * 内部类，专门用来管理每个get方法所对应缓存的名称。
     */
    static class CacheNameHelper{
        //pc_FirstLevelDiscipline_listAll
        public static final String ListAll_CacheName = "pc_FirstLevelDiscipline_listAll";
        // pc_FirstLevelDiscipline_{类别id}
        public static final String Receive_CacheNamePrefix = "pc_FirstLevelDiscipline_";
        // pc_FirstLevelDiscipline_list_{页码}
        private static final String List_CacheNamePrefix = "pc_FirstLevelDiscipline_list_";
        // pc_FirstLevelDiscipline_listByDisciplineCategoryID_{一级学科表学科门类ID}_{页码}
        private static final String ListByDisciplineCategoryID_CacheNamePrefix = "pc_FirstLevelDiscipline_listByDisciplineCategoryID_";
    }
    //显示所有数据
    @ApiOperation(value = "显示FirstLevelDiscipline中所有信息",notes = "未测试")
    @GetMapping("firstLevelDiscipline/listAll")
    public void listAll(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String json =cache.get(CacheNameHelper.ListAll_CacheName);
        if(json==null){
            // System.out.println("查询数据库");
            List<FirstLevelDiscipline> firstLevelDisciplines= firstLevelDisciplineService.selectAll();
            json= Result.build(ResultType.Success).appendData("firstLevelDiscipline",firstLevelDisciplines).convertIntoJSON();
            cache.set(CacheNameHelper.ListAll_CacheName,json);
        }
        response.getWriter().write(json);
    }
    /**
     * 新增FirstLevelDiscipline信息
     * @param firstLevelDiscipline
     * @return
     */

    @ApiOperation(value="新增FirstLevelDiscipline信息", notes="未测试")
    @ApiImplicitParam(name = "firstLevelDiscipline", value = "FirstLevelDiscipline详情实体", required = true, dataType = "FirstLevelDiscipline")
    @PostMapping("/firstLevelDiscipline")
    @ResponseBody
    public Result create(@RequestBody(required = false) FirstLevelDiscipline firstLevelDiscipline){
        if(firstLevelDiscipline!= null){
            boolean success = firstLevelDisciplineService.insert(firstLevelDiscipline);
            if(success){
                // 清空相关缓存
                cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName );
                cache.deleteByPaterm(CacheNameHelper.ListByDisciplineCategoryID_CacheNamePrefix + "*");
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
     * 根据id删除FirstLevelDiscipline
     * @param id
     * @return
     */
    @ApiOperation(value="根据id删除FirstLevelDiscipline信息", notes="未测试")
    @ApiImplicitParam(name = "id", value = "FirstLevelDiscipline主id", required = true, dataType = "bigint", paramType = "path")
    @DeleteMapping("firstLevelDiscipline/{id}")
    @ResponseBody
    public Result destroy(@PathVariable Long id){
        boolean success = firstLevelDisciplineService.delete(id);
        if(success){
            // 清空相关缓存
            cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName );
            cache.deleteByPaterm(CacheNameHelper.ListByDisciplineCategoryID_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.Receive_CacheNamePrefix + "*");
            return Result.build(ResultType.Success);
        }else{
            return Result.build(ResultType.Failed);
        }
    }
    /**
     * 根据id更新FirstLevelDiscipline信息
     * @param firstLevelDiscipline
     * @return
     */
    @ApiOperation(value="根据id更新FirstLevelDiscipline信息", notes="未测试")
    @ApiImplicitParam(name = "firstLevelDiscipline", value = "FirstLevelDiscipline实体", required = true, dataType = "FirstLevelDiscipline")
    @PutMapping("/firstLevelDiscipline")
    @ResponseBody
    public Result update(@RequestBody(required = false) FirstLevelDiscipline firstLevelDiscipline){
        if(firstLevelDiscipline != null && firstLevelDiscipline.getId() != null){
            boolean success = firstLevelDisciplineService.update(firstLevelDiscipline);
            if(success){
                cache.delete(CacheNameHelper.Receive_CacheNamePrefix + firstLevelDiscipline.getId());
                cache.delete(CacheNameHelper.ListAll_CacheName);
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }
    /**
     * 根据id查询FirstLevelDiscipline信息
     * @param id
     * @param response
     * @throws IOException
     */
    @ApiOperation(value="根据id查询FirstLevelDiscipline信息", notes="未测试")
    @ApiImplicitParam(name = "id", value = "FirstLevelDiscipline主id", required = false, dataType = "bigint", paramType = "path")
    @GetMapping("/firstLevelDiscipline/{id}")
    public void receive(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.Receive_CacheNamePrefix + id;
        String json = cache.get(cacheName);
        if(json == null){
            json = Result.build(ResultType.Success).appendData("firstLevelDiscipline",  firstLevelDisciplineService.select(id)).convertIntoJSON();
            cache.set(cacheName, json);
        }
        response.getWriter().write(json);
    }

    /**
     * 根据页码分页查询所有该页码所有FirstLevelDiscipline信息
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据页码分页查询该页码所有FirstLevelDiscipline信息", notes = "未测试")
    @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    @GetMapping(value = "/firstLevelDiscipline/list/{pageNum}")
    public void list(
            @PathVariable Integer pageNum ,
            HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.List_CacheNamePrefix + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<FirstLevelDiscipline> pageInfo = firstLevelDisciplineService.selectPage(pageNum);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }
    /**
     * 根据FirstLevelDiscipline表的学科门类id和页码来分页查询
     * @param discipline_category_id
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据FirstLevelDiscipline表的学校id和页码来分页查询", notes = "测试中")
    @ApiImplicitParams({

            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/firstLevelDiscipline/listByDisciplineCategoryId/{discipline_category_id}/{pageNum}")
    public void listByDisciplineCategoryID(@PathVariable long discipline_category_id,
                                   @PathVariable Integer pageNum,
                                   HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByDisciplineCategoryID_CacheNamePrefix + discipline_category_id + "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<FirstLevelDiscipline> pageInfo = firstLevelDisciplineService.selectPageByDisciplineCategoryID(pageNum, discipline_category_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }

}
