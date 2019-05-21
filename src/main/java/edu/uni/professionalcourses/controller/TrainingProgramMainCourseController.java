package edu.uni.professionalcourses.controller;

import com.github.pagehelper.PageInfo;
import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.professionalcourses.bean.TrainingProgramMainCourse;
import edu.uni.professionalcourses.service.TrainingProgramMainCourseService;
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
 modified：2019.5.5
 功能： TrainingProgramMainCourseController的增删查改
 */
@Api(description = "专业课程模块")
@Controller
@RequestMapping("json/professionalcourses")
public class TrainingProgramMainCourseController {
    @Autowired
    private TrainingProgramMainCourseService trainingProgramMainCourseService;
    @Autowired
    private RedisCache cache;
    /**
     * 内部类，专门用来管理每个get方法所对应缓存的名称。
     */
    static class CacheNameHelper{
        //pc_TrainingProgramMainCourse_listAll
        public static final String ListAll_CacheName = "pc_TrainingProgramMainCourse_listAll";
        // pc_TrainingProgramMainCourse_{类别id}
        public static final String Receive_CacheNamePrefix = "pc_TrainingProgramMainCourse_";
        // pc_TrainingProgramMainCourse_list_{页码}
        private static final String List_CacheNamePrefix = "pc_TrainingProgramMainCourse_list_";
        // pc_TrainingProgramMainCourse_listByTrainingProgramID_{培养方案内容id}_{页码}
        private static final String ListByTrainingProgramID_CacheNamePrefix = "pc_TrainingProgramMainCourse_listByTrainingProgramID_";
        // pc_TrainingProgramMainCourse_listByUniversityID_{学校id}_{页码}
        private static final String ListByUniversityID_CacheNamePrefix = "pc_TrainingProgramMainCourse_listByUniversityID_";
        // pc_TrainingProgramMainCourse_listByCourseID_{课程id}_{页码}
        private static final String ListByCourseID_CacheNamePrefix = "pc_TrainingProgramMainCourse_listByCourseID_";

    }
    //显示所有数据
    @ApiOperation(value = "显示TrainingProgramMainCourse中所有信息",notes = "未测试")
    @GetMapping("trainingProgramMainCourse/listAll")
    public void listAll(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String json =cache.get(CacheNameHelper.ListAll_CacheName);
        if(json==null){
            // System.out.println("查询数据库");
            List<TrainingProgramMainCourse> trainingProgramMainCourse= trainingProgramMainCourseService.selectAll();
            json= Result.build(ResultType.Success).appendData("trainingProgramMainCourse",trainingProgramMainCourse).convertIntoJSON();
            cache.set(CacheNameHelper.ListAll_CacheName,json);
        }
        response.getWriter().write(json);
    }
    /**
     * 新增TrainingProgramMainCourse信息
     * @param trainingProgramMainCourse
     * @return
     */
    @ApiOperation(value="新增专业理论课程培养方案内容信息", notes = "未测试")
    @ApiImplicitParam(name="trainingProgramMainCourse",value ="专业培养理论课程方案内容实体类", required = true, dataType ="TrainingProgramMainCourse")
    @PostMapping("trainingProgramMainCourse")
    @ResponseBody
    public Result create(@RequestBody(required = false) TrainingProgramMainCourse trainingProgramMainCourse){
        if(trainingProgramMainCourse!= null){
            boolean success = trainingProgramMainCourseService.insert(trainingProgramMainCourse);
            if(success){
                cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName );
                cache.deleteByPaterm(CacheNameHelper.ListByUniversityID_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListByCourseID_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListByTrainingProgramID_CacheNamePrefix+"*");
                cache.deleteByPaterm(CacheNameHelper.Receive_CacheNamePrefix + "*");
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }
    /**
     * 根据id删除TrainingProgramMainCourse信息
     * @param id
     * @return
     */
    @ApiOperation(value="根据id删除TrainingProgramMainCourse信息", notes="未测试")
    @ApiImplicitParam(name = "id", value = "TrainingProgramMainCourse主id", required = true, dataType = "bigint", paramType = "path")
    @DeleteMapping("/trainingProgramMainCourse/{id}")
    @ResponseBody
    public Result destroy(@PathVariable Long id){
        boolean success = trainingProgramMainCourseService.delete(id);
        if(success){
            // 清空相关缓存
            cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName );
            cache.deleteByPaterm(CacheNameHelper.ListByUniversityID_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.ListByCourseID_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.ListByTrainingProgramID_CacheNamePrefix+"*");
            cache.deleteByPaterm(CacheNameHelper.Receive_CacheNamePrefix + id);
            return Result.build(ResultType.Success);
        }else{
            return Result.build(ResultType.Failed);
        }
    }
    /**
     * 根据id更新TrainingProgramMainCourse信息
     * @param trainingProgramMainCourse
     * @return
     */
    @ApiOperation(value="根据id更新TrainingProgramMainCourse信息", notes="已测试")
    @ApiImplicitParam(name = "trainingProgramMainCourse", value = "TrainingProgramMainCourse实体",
            required = true, dataType = "TrainingProgramMainCourse")
    @PutMapping("/trainingProgramMainCourse")
    @ResponseBody
    public Result update(@RequestBody(required = false) TrainingProgramMainCourse trainingProgramMainCourse){
        if(trainingProgramMainCourse != null && trainingProgramMainCourse.getId() != null){
            boolean success = trainingProgramMainCourseService.update(trainingProgramMainCourse);
            if(success){
                cache.delete(CacheNameHelper.Receive_CacheNamePrefix + trainingProgramMainCourse.getId());
                cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName );
                cache.deleteByPaterm(CacheNameHelper.ListByUniversityID_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListByCourseID_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListByTrainingProgramID_CacheNamePrefix+"*");
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }
    /**
     * 根据id查询TrainingProgramMainCourse信息
     * @param id
     * @param response
     * @throws IOException
     */
    @ApiOperation(value="根据id查询TrainingProgramMainCourse信息", notes="未测试")
    @ApiImplicitParam(name = "id", value = "TrainingProgramMainCourse主id", required = false, dataType = "bigint", paramType = "path")
    @GetMapping("/trainingProgramMainCourse{id}")
    public void receive(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.Receive_CacheNamePrefix + id;
        String json = cache.get(cacheName);
        if(json == null){
            json = Result.build(ResultType.Success).appendData("trainingProgramMainCourse",
                    trainingProgramMainCourseService.select(id)).convertIntoJSON();
            cache.set(cacheName, json);
        }
        response.getWriter().write(json);
    }

    /**
     * 根据页码分页查询所有该页码所有TrainingProgramMainCourse信息
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据页码分页查询该页码所有TrainingProgramMainCourse信息", notes = "未测试")
    @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    @GetMapping(value = "/trainingProgramMainCourse/list/{pageNum}")
    public void list(
            @PathVariable Integer pageNum ,
            HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.List_CacheNamePrefix + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<TrainingProgramMainCourse> pageInfo = trainingProgramMainCourseService.selectPage(pageNum);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }
    /**
     * 根据TrainingProgramMainCourse表的学校id和页码来分页查询
     * @param university_id
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据TrainingProgramMainCourse表的学校id和页码来分页查询", notes = "测试中")
    @ApiImplicitParams({

            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/trainingProgramMainCourse/listByUniversityId/{university_id}/{pageNum}")
    public void listByUniversityID(@PathVariable long university_id,
                                   @PathVariable Integer pageNum,
                                   HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByUniversityID_CacheNamePrefix + university_id + "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<TrainingProgramMainCourse> pageInfo = trainingProgramMainCourseService.selectPageByUniversityID(pageNum, university_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }
    /**
     * 根据TrainingProgramMainCourse表的培养方案内容id和页码来分页查询
     * @param training_program_id
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据TrainingProgramMainCourse表的培养方案内容id和页码来分页查询", notes = "测试中")
    @ApiImplicitParams({

            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/trainingProgramMainCourse/listByTrainingProgramID/{training_program_id}/{pageNum}")
    public void listByTrainingProgramID(@PathVariable long training_program_id,
                                        @PathVariable Integer pageNum,
                                        HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByTrainingProgramID_CacheNamePrefix + training_program_id+ "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<TrainingProgramMainCourse> pageInfo = trainingProgramMainCourseService.selectPageByTrainingProgramID(pageNum,
                    training_program_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }
    /**
     * 根据TrainingProgramMainCourse表的课程id和页码来分页查询
     * @param course_id
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据TrainingProgramMainCourse表的课程id和页码来分页查询", notes = "测试中")
    @ApiImplicitParams({

            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/trainingProgramMainCourse/listByCourseID/{course_id}/{pageNum}")
    public void listByCourseID(@PathVariable long course_id,
                               @PathVariable Integer pageNum,
                               HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByCourseID_CacheNamePrefix + course_id+ "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<TrainingProgramMainCourse> pageInfo = trainingProgramMainCourseService.selectPageByCourseID(pageNum, course_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }
}
