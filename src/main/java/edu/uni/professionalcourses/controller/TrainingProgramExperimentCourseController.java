package edu.uni.professionalcourses.controller;

import com.github.pagehelper.PageInfo;
import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.professionalcourses.bean.TrainingProgramExperimentCourse;
import edu.uni.professionalcourses.service.TrainingProgramExperimentCourseService;
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
 create：2019.5.1
 modified：2019.5.1
 功能： TrainingProgramExperimentCourseController的增删查改
 */
@Api(description = "专业课程模块")
@Controller
@RequestMapping("json/professionalcourses")
public class TrainingProgramExperimentCourseController {
    @Autowired
    private TrainingProgramExperimentCourseService trainingProgramExperimentCourseService;
    @Autowired
    private RedisCache cache;
    /**
     * 内部类，专门用来管理每个get方法所对应缓存的名称。
     */
    static class CacheNameHelper{
        //pc_TrainingProgramExperimentCourse_listAll
        public static final String ListAll_CacheName = "pc_TrainingProgramExperimentCourse_listAll";
        // pc_TrainingProgramExperimentCourse_{类别id}
        public static final String Receive_CacheNamePrefix = "pc_TrainingProgramExperimentCourse_";
        // pc_TrainingProgramExperimentCourse_list_{页码}
        private static final String List_CacheNamePrefix = "pc_TrainingProgramExperimentCourse_list_";
        // pc_TrainingProgramExperimentCourse_listByTrainingProgramID_{培养方案内容id}_{页码}
        private static final String ListByTrainingProgramID_CacheNamePrefix = "pc_TrainingProgramExperimentCourse_listByTrainingProgramID_";
        // pc_TrainingProgramExperimentCourse_listByUniversityID_{学校id}_{页码}
        private static final String ListByUniversityID_CacheNamePrefix = "pc_TrainingProgramExperimentCourse_listByUniversityID_";
        // pc_TrainingProgramExperimentCourse_listByCourseID_{课程id}_{页码}
        private static final String ListByCourseID_CacheNamePrefix = "pc_TrainingProgramExperimentCourse_listByCourseID_";

    }
    //显示所有数据
    @ApiOperation(value = "显示TrainingProgramExperimentCourse中所有信息",notes = "未测试")
    @GetMapping("trainingProgramExperimentCourse/listAll")
    public void listAll(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String json =cache.get(CacheNameHelper.ListAll_CacheName);
        if(json==null){
            // System.out.println("查询数据库");
            List<TrainingProgramExperimentCourse> trainingProgramExperimentCourse= trainingProgramExperimentCourseService.selectAll();
            json= Result.build(ResultType.Success).appendData("trainingProgramExperimentCourse",trainingProgramExperimentCourse).convertIntoJSON();
            cache.set(CacheNameHelper.ListAll_CacheName,json);
        }
        response.getWriter().write(json);
    }
    /**
     * 新增TrainingProgramExperimentCourse信息
     * @param trainingProgramExperimentCourse
     * @return
     */
    @ApiOperation(value="新增专业实验课程培养方案内容信息", notes = "未测试")
    @ApiImplicitParam(name="trainingProgramExperimentCourse",value ="专业培养实验课程方案内容实体类", required = true, dataType ="TrainingProgramExperimentCourse")
    @PostMapping("trainingProgramExperimentCourse")
    @ResponseBody
    public Result create(@RequestBody(required = false) TrainingProgramExperimentCourse trainingProgramExperimentCourse){
        if(trainingProgramExperimentCourse!= null){
            boolean success = trainingProgramExperimentCourseService.insert(trainingProgramExperimentCourse);
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
     * 根据id删除TrainingProgramsExperimentCourse信息
     * @param id
     * @return
     */
    @ApiOperation(value="根据id删除TrainingProgramsExperimentCourse信息", notes="未测试")
    @ApiImplicitParam(name = "id", value = "TrainingProgramsExperimentCourse主id", required = true, dataType = "bigint", paramType = "path")
    @DeleteMapping("/trainingProgramsExperimentCourse/{id}")
    @ResponseBody
    public Result destroy(@PathVariable Long id){
        boolean success = trainingProgramExperimentCourseService.delete(id);
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
     * 根据id更新TrainingProgramExperimentCourse信息
     * @param trainingProgramExperimentCourse
     * @return
     */
    @ApiOperation(value="根据id更新TrainingProgramsExperimentCourse信息", notes="已测试")
    @ApiImplicitParam(name = "trainingProgramExperimentCourse", value = "TrainingProgramsExperimentCourse实体",
            required = true, dataType = "TrainingProgramExperimentCourse")
    @PutMapping("/trainingProgramExperimentCourse")
    @ResponseBody
    public Result update(@RequestBody(required = false) TrainingProgramExperimentCourse trainingProgramExperimentCourse){
        if(trainingProgramExperimentCourse != null && trainingProgramExperimentCourse.getId() != null){
            boolean success = trainingProgramExperimentCourseService.update(trainingProgramExperimentCourse);
            if(success){
                cache.delete(CacheNameHelper.Receive_CacheNamePrefix + trainingProgramExperimentCourse.getId());
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
     * 根据id查询TrainingProgramExperimentCourse信息
     * @param id
     * @param response
     * @throws IOException
     */
    @ApiOperation(value="根据id查询TrainingProgramExperimentCourse信息", notes="未测试")
    @ApiImplicitParam(name = "id", value = "TrainingProgramExperimentCourse主id", required = false, dataType = "bigint", paramType = "path")
    @GetMapping("/trainingProgramExperimentCourse{id}")
    public void receive(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.Receive_CacheNamePrefix + id;
        String json = cache.get(cacheName);
        if(json == null){
            json = Result.build(ResultType.Success).appendData("trainingProgramExperimentCourse",
                    trainingProgramExperimentCourseService.select(id)).convertIntoJSON();
            cache.set(cacheName, json);
        }
        response.getWriter().write(json);
    }

    /**
     * 根据页码分页查询所有该页码所有TrainingProgramExperimentCourse信息
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据页码分页查询该页码所有TrainingProgramExperimentCourse信息", notes = "未测试")
    @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    @GetMapping(value = "/trainingProgramExperimentCourse/list/{pageNum}")
    public void list(
            @PathVariable Integer pageNum ,
            HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.List_CacheNamePrefix + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<TrainingProgramExperimentCourse> pageInfo = trainingProgramExperimentCourseService.selectPage(pageNum);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }
    /**
     * 根据TrainingProgramExperimentCourse表的学校id和页码来分页查询
     * @param university_id
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据TrainingProgramExperimentCourse表的学校id和页码来分页查询", notes = "测试中")
    @ApiImplicitParams({

            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/trainingProgramExperimentCourse/listByUniversityId/{university_id}/{pageNum}")
    public void listByUniversityID(@PathVariable long university_id,
                                   @PathVariable Integer pageNum,
                                   HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByUniversityID_CacheNamePrefix + university_id + "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<TrainingProgramExperimentCourse> pageInfo = trainingProgramExperimentCourseService.selectPageByUniversityID(pageNum, university_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }
    /**
     * 根据TrainingProgramExperimentCourse表的培养方案内容id和页码来分页查询
     * @param training_program_id
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据TrainingProgramExperimentCourse表的培养方案内容id和页码来分页查询", notes = "测试中")
    @ApiImplicitParams({

            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/trainingProgramExperimentCourse/listByTrainingProgramID/{training_program_id}/{pageNum}")
    public void listByTrainingProgramID(@PathVariable long training_program_id,
                                  @PathVariable Integer pageNum,
                                  HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByTrainingProgramID_CacheNamePrefix + training_program_id+ "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<TrainingProgramExperimentCourse> pageInfo = trainingProgramExperimentCourseService.selectPageByTrainingProgramID(pageNum,
                    training_program_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }
    /**
     * 根据TrainingProgramExperimentCourse表的课程id和页码来分页查询
     * @param course_id
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据TrainingProgramExperimentCourse表的课程id和页码来分页查询", notes = "测试中")
    @ApiImplicitParams({

            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/trainingProgramExperimentCourse/listByCourseID/{course_id}/{pageNum}")
    public void listByCourseID(@PathVariable long course_id,
                                  @PathVariable Integer pageNum,
                                  HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByCourseID_CacheNamePrefix + course_id+ "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<TrainingProgramExperimentCourse> pageInfo = trainingProgramExperimentCourseService.selectPageByCourseID(pageNum, course_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }
}
