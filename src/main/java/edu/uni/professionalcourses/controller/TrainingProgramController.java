package edu.uni.professionalcourses.controller;

import com.github.pagehelper.PageInfo;
import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.professionalcourses.bean.TrainingProgram;
import edu.uni.professionalcourses.service.TrainingProgramService;
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
 create：2019.4.26
 modified：2019.5.11
 功能： TrainingProgramController的增删查改
 */
@Api(description = "专业课程模块")
@Controller
@RequestMapping("json/professionalcourses")
public class TrainingProgramController {
    @Autowired
    private TrainingProgramService trainingProgramService;
    @Autowired
    private RedisCache cache;
    /**
     * 内部类，专门用来管理每个get方法所对应缓存的名称。
     */
    static class CacheNameHelper{
        //pc_TrainingProgram_listAll
        public static final String ListAll_CacheName = "pc_TrainingProgram_listAll";
        // pc_TrainingProgram_{类别id}
        public static final String Receive_CacheNamePrefix = "pc_TrainingProgram_";
        // pc_TrainingProgram_list_{页码}
        private static final String List_CacheNamePrefix = "pc_TrainingProgram_list_";
        // pc_TrainingProgram_listBySpecialtyID_{专业培养方案内容表专业id}_{页码}
        private static final String ListBySpecialtyID_CacheNamePrefix = "pc_TrainingProgram_listBySpecialtyID_";
        // pc_TrainingProgram_listByUniversityID_{学校id}_{页码}
        private static final String ListByUniversityID_CacheNamePrefix = "pc_TrainingProgram_listByUniversityID_";

    }
    //显示所有数据
    @ApiOperation(value = "显示TrainingProgram中所有信息",notes = "未测试")
    @GetMapping("trainingProgram/listAll")
    public void listAll(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String json =cache.get(CacheNameHelper.ListAll_CacheName);
        if(json==null){
            // System.out.println("查询数据库");
            List<TrainingProgram> trainingProgram= trainingProgramService.selectAll();
            json= Result.build(ResultType.Success).appendData("trainingProgram",trainingProgram).convertIntoJSON();
            cache.set(CacheNameHelper.ListAll_CacheName,json);
        }
        response.getWriter().write(json);
    }
    /**
     * 新增TrainingProgram信息
     * @param trainingProgram
     * @return
     */
    @ApiOperation(value="新增专业培养方案内容信息", notes = "未测试")
    @ApiImplicitParam(name="trainingProgram",value ="专业培养方案内容实体类", required = true, dataType ="TrainingProgram")
    @PostMapping("trainingProgram")
    @ResponseBody
    public Result create(@RequestBody(required = false) TrainingProgram trainingProgram){
        if(trainingProgram!= null){
            boolean success = trainingProgramService.insert(trainingProgram);
            if(success){
                cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName );
                cache.deleteByPaterm(CacheNameHelper.ListByUniversityID_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListBySpecialtyID_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.Receive_CacheNamePrefix + "*");
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }
    /**
     * 根据id删除TrainingPrograms信息
     * @param id
     * @return
     */
    @ApiOperation(value="根据id删除TrainingPrograms信息", notes="未测试")
    @ApiImplicitParam(name = "id", value = "TrainingPrograms主id", required = true, dataType = "bigint", paramType = "path")
    @DeleteMapping("/trainingPrograms/{id}")
    @ResponseBody
    public Result destroy(@PathVariable Long id){
        boolean success = trainingProgramService.delete(id);
        if(success){
            // 清空相关缓存
            cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName );
            cache.deleteByPaterm(CacheNameHelper.ListByUniversityID_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.ListBySpecialtyID_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.Receive_CacheNamePrefix + id);
            return Result.build(ResultType.Success);
        }else{
            return Result.build(ResultType.Failed);
        }
    }
    /**
     * 根据id更新TrainingProgram信息
     * @param trainingProgram
     * @return
     */
    @ApiOperation(value="根据id更新TrainingPrograms信息", notes="已测试")
    @ApiImplicitParam(name = "trainingProgram", value = "TrainingPrograms实体", required = true, dataType = "TrainingProgram")
    @PutMapping("/trainingProgram")
    @ResponseBody
    public Result update(@RequestBody(required = false) TrainingProgram trainingProgram){
        if(trainingProgram != null && trainingProgram.getId() != null){
            boolean success = trainingProgramService.update(trainingProgram);
            if(success){
                cache.delete(CacheNameHelper.Receive_CacheNamePrefix + trainingProgram.getId());
                cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName );
                cache.deleteByPaterm(CacheNameHelper.ListByUniversityID_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListBySpecialtyID_CacheNamePrefix + "*");
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }
    /**
     * 根据id查询TrainingProgram信息
     * @param id
     * @param response
     * @throws IOException
     */
    @ApiOperation(value="根据id查询TrainingProgram信息", notes="未测试")
    @ApiImplicitParam(name = "id", value = "TrainingProgram主id", required = false, dataType = "bigint", paramType = "path")
    @GetMapping("/trainingProgram{id}")
    public void receive(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.Receive_CacheNamePrefix + id;
        String json = cache.get(cacheName);
        if(json == null){
            json = Result.build(ResultType.Success).appendData("trainingProgram",  trainingProgramService.select(id)).convertIntoJSON();
            cache.set(cacheName, json);
        }
        response.getWriter().write(json);
    }

    /**
     * 根据页码分页查询所有该页码所有TrainingProgram信息
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据页码分页查询该页码所有TrainingProgram信息", notes = "未测试")
    @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    @GetMapping(value = "/trainingProgram/list/{pageNum}")
    public void list(
            @PathVariable Integer pageNum ,
            HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.List_CacheNamePrefix + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<TrainingProgram> pageInfo = trainingProgramService.selectPage(pageNum);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }
    /**
     * 根据TrainingProgram表的学校id和页码来分页查询
     * @param university_id
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据TrainingProgram表的学校id和页码来分页查询", notes = "测试中")
    @ApiImplicitParams({

            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/trainingProgram/listByUniversityId/{university_id}/{pageNum}")
    public void listByUniversityID(@PathVariable long university_id,
                                   @PathVariable Integer pageNum,
                                   HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByUniversityID_CacheNamePrefix + university_id + "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<TrainingProgram> pageInfo = trainingProgramService.selectPageByUniversityID(pageNum, university_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }
    /**
     * 根据TrainingProgram表的专业id和页码来分页查询
     * @param specialty_id
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据TrainingProgram表的专业id和页码来分页查询", notes = "测试中")
    @ApiImplicitParams({

            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/trainingProgram/listBySpecialtyID/{specialty_id}/{pageNum}")
    public void listBySpecialtyID(@PathVariable long specialty_id,
                                                  @PathVariable Integer pageNum,
                                                  HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListBySpecialtyID_CacheNamePrefix + specialty_id+ "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<TrainingProgram> pageInfo = trainingProgramService.selectPageBySpecialtyID(pageNum, specialty_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }
}
