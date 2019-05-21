package edu.uni.professionalcourses.controller;

import com.github.pagehelper.PageInfo;
import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.professionalcourses.bean.PreCourse;
import edu.uni.professionalcourses.service.PreCourseService;
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
 功能： PreCourseController的增删查改
 */
@Api(description = "专业课程模块")
@Controller
@RequestMapping("json/professionalcourses")
public class PreCourseController {
    @Autowired
    private PreCourseService preCourseService;
    @Autowired
    private RedisCache cache;
    /**
     * 内部类，专门用来管理每个get方法所对应缓存的名称。
     */
    static class CacheNameHelper{
        //pc_PreCourse_listAll
        public static final String ListAll_CacheName = "pc_PreCourse_listAll";
        // pc_PreCourse_{类别id}
        public static final String Receive_CacheNamePrefix = "pc_PreCourse_";
        // pc_PreCourse_list_{页码}
        private static final String List_CacheNamePrefix = "pc_PreCourse_list_";
        // pc_PreCourse_listByPreCourseID_{先修课程id}_{页码}
        private static final String ListByPreCourseID_CacheNamePrefix = "pc_PreCourse_listByPreCourseID_";
        // pc_PreCourse_listByUniversityID_{学校id}_{页码}
        private static final String ListByUniversityID_CacheNamePrefix = "pc_PreCourse_listByUniversityID_";
        // pc_PreCourse_listByCourseID_{课程id}_{页码}
        private static final String ListByCourseID_CacheNamePrefix = "pc_PreCourse_listByCourseID_";

    }
    //显示所有数据
    @ApiOperation(value = "显示PreCourse中所有信息",notes = "未测试")
    @GetMapping("preCourse/listAll")
    public void listAll(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String json =cache.get(CacheNameHelper.ListAll_CacheName);
        if(json==null){
            // System.out.println("查询数据库");
            List<PreCourse> preCourses= preCourseService.selectAll();
            json= Result.build(ResultType.Success).appendData("preCourse",preCourses).convertIntoJSON();
            cache.set(CacheNameHelper.ListAll_CacheName,json);
        }
        response.getWriter().write(json);
    }
    /**
     * 新增PreCourse信息
     * @param preCourse
     * @return
     */
    @ApiOperation(value="新增先修课程信息", notes = "未测试")
    @ApiImplicitParam(name="preCourse",value ="先修课程实体类", required = true, dataType ="PreCourse")
    @PostMapping("preCourse")
    @ResponseBody
    public Result create(@RequestBody(required = false) PreCourse preCourse){
        if(preCourse!= null){
            boolean success = preCourseService.insert(preCourse);
            if(success){
                cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName );
                cache.deleteByPaterm(CacheNameHelper.ListByUniversityID_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListByCourseID_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListByPreCourseID_CacheNamePrefix+"*");
                cache.deleteByPaterm(CacheNameHelper.Receive_CacheNamePrefix + "*");
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }
    /**
     * 根据id删除PreCourse信息
     * @param id
     * @return
     */
    @ApiOperation(value="根据id删除PreCourse信息", notes="未测试")
    @ApiImplicitParam(name = "id", value = "PreCourse主id", required = true, dataType = "bigint", paramType = "path")
    @DeleteMapping("/preCourse/{id}")
    @ResponseBody
    public Result destroy(@PathVariable Long id){
        boolean success = preCourseService.delete(id);
        if(success){
            // 清空相关缓存
            cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName );
            cache.deleteByPaterm(CacheNameHelper.ListByUniversityID_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.ListByCourseID_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.ListByPreCourseID_CacheNamePrefix+"*");
            cache.deleteByPaterm(CacheNameHelper.Receive_CacheNamePrefix + id);
            return Result.build(ResultType.Success);
        }else{
            return Result.build(ResultType.Failed);
        }
    }
    /**
     * 根据id更新PreCourse信息
     * @param preCourse
     * @return
     */
    @ApiOperation(value="根据id更新PreCourse信息", notes="已测试")
    @ApiImplicitParam(name = "preCourse", value = "PreCourse实体",
            required = true, dataType = "PreCourse")
    @PutMapping("/preCourse")
    @ResponseBody
    public Result update(@RequestBody(required = false) PreCourse preCourse){
        if(preCourse != null && preCourse.getId() != null){
            boolean success = preCourseService.update(preCourse);
            if(success){
                cache.delete(CacheNameHelper.Receive_CacheNamePrefix + preCourse.getId());
                cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName );
                cache.deleteByPaterm(CacheNameHelper.ListByUniversityID_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListByCourseID_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListByPreCourseID_CacheNamePrefix+"*");
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }
    /**
     * 根据id查询PreCourse信息
     * @param id
     * @param response
     * @throws IOException
     */
    @ApiOperation(value="根据id查询PreCourse信息", notes="未测试")
    @ApiImplicitParam(name = "id", value = "PreCourse主id", required = false, dataType = "bigint", paramType = "path")
    @GetMapping("/preCourse{id}")
    public void receive(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.Receive_CacheNamePrefix + id;
        String json = cache.get(cacheName);
        if(json == null){
            json = Result.build(ResultType.Success).appendData("preCourse",
                    preCourseService.select(id)).convertIntoJSON();
            cache.set(cacheName, json);
        }
        response.getWriter().write(json);
    }

    /**
     * 根据页码分页查询所有该页码所有PreCourse信息
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据页码分页查询该页码所有PreCourse信息", notes = "未测试")
    @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    @GetMapping(value = "/preCourse/list/{pageNum}")
    public void list(
            @PathVariable Integer pageNum ,
            HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.List_CacheNamePrefix + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<PreCourse> pageInfo = preCourseService.selectPage(pageNum);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }
    /**
     * 根据PreCourse表的学校id和页码来分页查询
     * @param university_id
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据PreCourse表的学校id和页码来分页查询", notes = "测试中")
    @ApiImplicitParams({

            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/preCourse/listByUniversityId/{university_id}/{pageNum}")
    public void listByUniversityID(@PathVariable long university_id,
                                   @PathVariable Integer pageNum,
                                   HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByUniversityID_CacheNamePrefix + university_id + "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<PreCourse> pageInfo = preCourseService.selectPageByUniversityID(pageNum, university_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }
    /**
     * 根据PreCourse表的培养方案内容id和页码来分页查询
     * @param pre_course_id
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据PreCourse表的先修课程id和页码来分页查询", notes = "测试中")
    @ApiImplicitParams({

            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/preCourse/listByPreCourseID/{pre_course_id}/{pageNum}")
    public void listByPreCourseID(@PathVariable long pre_course_id,
                                        @PathVariable Integer pageNum,
                                        HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByPreCourseID_CacheNamePrefix + pre_course_id+ "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<PreCourse> pageInfo = preCourseService.selectPageByPreCourseID(pageNum,
                    pre_course_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }
    /**
     * 根据PreCourse表的课程id和页码来分页查询
     * @param course_id
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据PreCourse表的课程id和页码来分页查询", notes = "测试中")
    @ApiImplicitParams({

            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/PreCourse/listByCourseID/{course_id}/{pageNum}")
    public void listByCourseID(@PathVariable long course_id,
                               @PathVariable Integer pageNum,
                               HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByCourseID_CacheNamePrefix + course_id+ "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<PreCourse> pageInfo = preCourseService.selectPageByCourseID(pageNum, course_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }
}
