package edu.uni.professionalcourses.controller;

import com.github.pagehelper.PageInfo;
import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.professionalcourses.bean.SyllabusTeachingContent;
import edu.uni.professionalcourses.service.SyllabusTeachingContentService;
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
 功能： SyllabusTeachingContent的增删查改
 */
@Api(description = "专业课程模块")
@Controller
@RequestMapping("json/professionalcourses")
public class SyllabusTeachingContentController {
    @Autowired
    private SyllabusTeachingContentService syllabusTeachingContentService;
    @Autowired
    private RedisCache cache;
    /**
     * 内部类，专门用来管理每个get方法所对应缓存的名称。
     */
    static class CacheNameHelper{
        //pc_SyllabusTeachingContent_listAll
        public static final String ListAll_CacheName = "pc_SyllabusTeachingContent_listAll";
        // pc_SyllabusTeachingContent_{类别id}
        public static final String Receive_CacheNamePrefix = "pc_SyllabusTeachingContent_";
        // pc_SyllabusTeachingContent_list_{页码}
        private static final String List_CacheNamePrefix = "pc_SyllabusTeachingContent_list_";
        // pc_SyllabusTeachingContent_listByCourseSyllabusDescriptionID_{教学内容表理论教学内容id}_{页码}
        private static final String ListByCourseSyllabusDescriptionID_CacheNamePrefix = "pc_SyllabusTeachingContent_listByCourseSyllabusDescriptionID_";
        // pc_SyllabusTeachingContent_listByUniversityID_{教学内容表学校id}_{页码}
        private static final String ListByUniversityID_CacheNamePrefix = "pc_SyllabusTeachingContent_listByUniversityID_";

    }
    //显示所有数据
    @ApiOperation(value = "显示SyllabusTeachingContent中所有信息",notes = "未测试")
    @GetMapping("syllabusTeachingContent/listAll")
    public void listAll(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String json =cache.get(CacheNameHelper.ListAll_CacheName);
        if(json==null){
            // System.out.println("查询数据库");
            List<SyllabusTeachingContent> syllabusTeachingContents= syllabusTeachingContentService.selectAll();
            json= Result.build(ResultType.Success).appendData("syllabusTeachingContents",syllabusTeachingContents).convertIntoJSON();
            cache.set(CacheNameHelper.ListAll_CacheName,json);
        }
        response.getWriter().write(json);
    }
    /**
     * 新增SyllabusTeachingContent信息
     * @param syllabusTeachingContent
     * @return
     */
    @ApiOperation(value="新增教学内容信息", notes = "未测试")
    @ApiImplicitParam(name="syllabusTeachingContents",value ="教学内容实体类", required = true, dataType ="SyllabusTeachingContent")
    @PostMapping("syllabusTeachingContents")
    @ResponseBody
    public Result create(@RequestBody(required = false) SyllabusTeachingContent syllabusTeachingContent){
        if(syllabusTeachingContent != null){
            boolean success = syllabusTeachingContentService.insert(syllabusTeachingContent);
            if(success){
                cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName );
                cache.deleteByPaterm(CacheNameHelper.ListByUniversityID_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListByCourseSyllabusDescriptionID_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.Receive_CacheNamePrefix + "*");
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }
    /**
     * 根据id删除SyllabusTeachingContent信息
     * @param id
     * @return
     */
    @ApiOperation(value="根据id删除SyllabusTeachingContent信息", notes="未测试")
    @ApiImplicitParam(name = "id", value = "SyllabusTeachingContent主id", required = true, dataType = "bigint", paramType = "path")
    @DeleteMapping("/syllabusTeachingContent/{id}")
    @ResponseBody
    public Result destroy(@PathVariable Long id){
        boolean success = syllabusTeachingContentService.delete(id);
        if(success){
            // 清空相关缓存
            cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName );
            cache.deleteByPaterm(CacheNameHelper.ListByUniversityID_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.ListByCourseSyllabusDescriptionID_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.Receive_CacheNamePrefix + id);
            return Result.build(ResultType.Success);
        }else{
            return Result.build(ResultType.Failed);
        }
    }
    /**
     * 根据id更新SyllabusTeachingContent信息
     * @param syllabusTeachingContent
     * @return
     */
    @ApiOperation(value="根据id更新SyllabusTeachingContent信息", notes="已测试")
    @ApiImplicitParam(name = "syllabusTeachingContent", value = "SyllabusTeachingContent实体", required = true, dataType = "SyllabusTeachingContent")
    @PutMapping("/syllabusTeachingContent")
    @ResponseBody
    public Result update(@RequestBody(required = false) SyllabusTeachingContent syllabusTeachingContent){
        if(syllabusTeachingContent != null && syllabusTeachingContent.getId() != null){
            boolean success = syllabusTeachingContentService.update(syllabusTeachingContent);
            if(success){
                cache.delete(CacheNameHelper.Receive_CacheNamePrefix + syllabusTeachingContent.getId());
                cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName );
                cache.deleteByPaterm(CacheNameHelper.ListByUniversityID_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListByCourseSyllabusDescriptionID_CacheNamePrefix + "*");
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }
    /**
     * 根据id查询SyllabusTeachingContent信息
     * @param id
     * @param response
     * @throws IOException
     */
    @ApiOperation(value="根据id查询SyllabusTeachingContent信息", notes="未测试")
    @ApiImplicitParam(name = "id", value = "SyllabusTeachingContent主id", required = false, dataType = "bigint", paramType = "path")
    @GetMapping("/syllabusTeachingContent{id}")
    public void receive(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.Receive_CacheNamePrefix + id;
        String json = cache.get(cacheName);
        if(json == null){
            json = Result.build(ResultType.Success).appendData("syllabusTeachingContent",  syllabusTeachingContentService.select(id)).convertIntoJSON();
            cache.set(cacheName, json);
        }
        response.getWriter().write(json);
    }

    /**
     * 根据页码分页查询所有该页码所有SyllabusTeachingContent信息
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据页码分页查询该页码所有SyllabusTeachingContent信息", notes = "未测试")
    @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    @GetMapping(value = "/syllabusTeachingContent/list/{pageNum}")
    public void list(
            @PathVariable Integer pageNum ,
            HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.List_CacheNamePrefix + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<SyllabusTeachingContent> pageInfo = syllabusTeachingContentService.selectPage(pageNum);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }
    /**
     * 根据SyllabusTeachingContent表的学校id和页码来分页查询
     * @param university_id
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据SyllabusTeachingContent表的学校id和页码来分页查询", notes = "测试中")
    @ApiImplicitParams({

            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/syllabusTeachingContent/listByUniversityId/{university_id}/{pageNum}")
    public void listByUniversityID(@PathVariable long university_id,
                                   @PathVariable Integer pageNum,
                                   HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByUniversityID_CacheNamePrefix + university_id + "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<SyllabusTeachingContent> pageInfo = syllabusTeachingContentService.selectPageByUniversityID(pageNum, university_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }
    /**
     * 根据SyllabusTeachingContent表的理论教学内容id和页码来分页查询
     * @param course_syllabus_description_id
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据SyllabusTeachingContent表的理论教学内容id和页码来分页查询", notes = "测试中")
    @ApiImplicitParams({

            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/syllabusTeachingContent/listByCourseSyllabusDescriptionID/{course_syllabus_description_id}/{pageNum}")
    public void listByCourseSyllabusDescriptionID(@PathVariable long course_syllabus_description_id,
                                   @PathVariable Integer pageNum,
                                   HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByCourseSyllabusDescriptionID_CacheNamePrefix + course_syllabus_description_id+ "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<SyllabusTeachingContent> pageInfo = syllabusTeachingContentService.selectPageByCourseSyllabusDescriptionID(pageNum, course_syllabus_description_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }
}
