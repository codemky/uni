package edu.uni.professionalcourses.controller;

import com.github.pagehelper.PageInfo;
import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.professionalcourses.bean.Specialty;
import edu.uni.professionalcourses.service.SpecialtyService;
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
 create：2019.5.5
 modified：2019.5.11
 功能： SpecialtyController的增删查改
 */
@Api(description = "专业课程模块")
@Controller
@RequestMapping("json/professionalcourses")
public class SpecialtyController {
    @Autowired
    private SpecialtyService specialtyService;
    @Autowired
    private RedisCache cache;
    /**
     * 内部类，专门用来管理每个get方法所对应缓存的名称。
     */
    static class CacheNameHelper{
        //pc_Specialty_listAll
        public static final String ListAll_CacheName = "pc_Specialty_listAll";
        // pc_Specialty_{类别id}
        public static final String Receive_CacheNamePrefix = "pc_Specialty_";
        // pc_Specialty_list_{页码}
        private static final String List_CacheNamePrefix = "pc_Specialty_list_";
        // pc_Specialty_listByDepartmentID_{部门id}_{页码}
        private static final String ListByDepartmentID_CacheNamePrefix = "pc_Specialty_listByDepartmentID_";
        // pc_Specialty_listByUniversityID_{学校id}_{页码}
        private static final String ListByUniversityID_CacheNamePrefix = "pc_SimilarSpecialty_listByUniversityID_";


    }
    //显示所有数据
    @ApiOperation(value = "显示Specialty中所有信息",notes = "未测试")
    @GetMapping("specialty/listAll")
    public void listAll(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String json =cache.get(CacheNameHelper.ListAll_CacheName);
        if(json==null){
            // System.out.println("查询数据库");
            List<Specialty> specialties= specialtyService.selectAll();
            json= Result.build(ResultType.Success).appendData("specialty",specialties).convertIntoJSON();
            cache.set(CacheNameHelper.ListAll_CacheName,json);
        }
        response.getWriter().write(json);
    }
    /**
     * 新增Specialty信息
     * @param specialty
     * @return
     */
    @ApiOperation(value="新增专业信息", notes = "未测试")
    @ApiImplicitParam(name="specialty",value ="专业实体类", required = true, dataType ="Specialty")
    @PostMapping("specialty")
    @ResponseBody
    public Result create(@RequestBody(required = false) Specialty specialty){
        if(specialty!= null){
            boolean success = specialtyService.insert(specialty);
            if(success){
                cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName );
                cache.deleteByPaterm(CacheNameHelper.ListByUniversityID_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListByDepartmentID_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.Receive_CacheNamePrefix + "*");
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }
    /**
     * 根据id删除Specialty信息
     * @param id
     * @return
     */
    @ApiOperation(value="根据id删除Specialty信息", notes="未测试")
    @ApiImplicitParam(name = "id", value = "Specialty主id", required = true, dataType = "bigint", paramType = "path")
    @DeleteMapping("/specialty/{id}")
    @ResponseBody
    public Result destroy(@PathVariable Long id){
        boolean success =specialtyService.delete(id);
        if(success){
            // 清空相关缓存
            cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName );
            cache.deleteByPaterm(CacheNameHelper.ListByUniversityID_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.ListByDepartmentID_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.Receive_CacheNamePrefix + id);
            return Result.build(ResultType.Success);
        }else{
            return Result.build(ResultType.Failed);
        }
    }
    /**
     * 根据id更新Specialty信息
     * @param specialty
     * @return
     */
    @ApiOperation(value="根据id更新Specialty信息", notes="已测试")
    @ApiImplicitParam(name = "specialty", value = "Specialty实体",
            required = true, dataType = "Specialty")
    @PutMapping("/specialty")
    @ResponseBody
    public Result update(@RequestBody(required = false)Specialty specialty){
        if(specialty != null && specialty.getId() != null){
            boolean success = specialtyService.update(specialty);
            if(success){
                cache.delete(CacheNameHelper.Receive_CacheNamePrefix + specialty.getId());
                cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName );
                cache.deleteByPaterm(CacheNameHelper.ListByUniversityID_CacheNamePrefix + "*");

                cache.deleteByPaterm(CacheNameHelper.ListByDepartmentID_CacheNamePrefix + "*");
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }
    /**
     * 根据id查询Specialty信息
     * @param id
     * @param response
     * @throws IOException
     */
    @ApiOperation(value="根据id查询Specialty信息", notes="未测试")
    @ApiImplicitParam(name = "id", value = "Specialty主id", required = false, dataType = "bigint", paramType = "path")
    @GetMapping("/specialty{id}")
    public void receive(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.Receive_CacheNamePrefix + id;
        String json = cache.get(cacheName);
        if(json == null){
            json = Result.build(ResultType.Success).appendData("specialty",
                    specialtyService.select(id)).convertIntoJSON();
            cache.set(cacheName, json);
        }
        response.getWriter().write(json);
    }

    /**
     * 根据页码分页查询所有该页码所有Specialty信息
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据页码分页查询该页码所有Specialty信息", notes = "未测试")
    @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    @GetMapping(value = "/specialty/list/{pageNum}")
    public void list(
            @PathVariable Integer pageNum ,
            HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.List_CacheNamePrefix + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<Specialty> pageInfo = specialtyService.selectPage(pageNum);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }
    /**
     * 根据Specialty表的学校id和页码来分页查询
     * @param university_id
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据Specialty表的学校id和页码来分页查询", notes = "测试中")
    @ApiImplicitParams({

            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/specialty/listByUniversityId/{university_id}/{pageNum}")
    public void listByUniversityID(@PathVariable long university_id,
                                   @PathVariable Integer pageNum,
                                   HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByUniversityID_CacheNamePrefix + university_id + "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<Specialty> pageInfo = specialtyService.selectPageByUniversityID(pageNum, university_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }
    /**
     * 根据Specialty表的部门id和页码来分页查询
     * @param department_id
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据Specialty表的部门id和页码来分页查询", notes = "测试中")
    @ApiImplicitParams({

            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/Specialty/listByCourseID/{department_id}/{pageNum}")
    public void listByDepartmentID(@PathVariable long department_id,
                                         @PathVariable Integer pageNum,
                                         HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByDepartmentID_CacheNamePrefix + department_id+ "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<Specialty> pageInfo = specialtyService.selectPageByDepartmentID(pageNum, department_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }
}
