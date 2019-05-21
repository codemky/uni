package edu.uni.professionalcourses.controller;
/**author：曹中耀
create：2019.4.23
modified：2019.5.11
功能： CourseBook的增删查改
 */

import com.github.pagehelper.PageInfo;
import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.professionalcourses.bean.CourseBook;
import edu.uni.professionalcourses.service.CourseBookService;
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

@Api(description = "专业课程模块")
@Controller
@RequestMapping("json/professionalcourses")
public class CourseBookController {
    @Autowired
    private CourseBookService courseBookService;
    @Autowired
    private RedisCache cache;
    /**
     * 内部类，专门用来管理每个get方法所对应缓存的名称。
     */
    static class CacheNameHelper{
        //pc_courseBook_listAll
        public static final String ListAll_CacheName = "pc_courseBook_listAll";
        // pc_courseBook_{类别id}
        public static final String Receive_CacheNamePrefix = "pc_courseBook_";
        // pc_courseBook_list_{页码}
        private static final String List_CacheNamePrefix = "pc_courseBook_list_";
        // pc_courseBook_listByCourseBookID_{课程参考书表id}_{页码}
        private static final String ListByCourseBookID_CacheNamePrefix = "pc_courseBook_listByCourseBookID_";
        // pc_courseBook_listByUniversityID_{课程参考书表学校id}_{页码}
        private static final String ListByUniversityID_CacheNamePrefix = "pc_courseBook_listByUniversityID_";
        // pc_courseBook_listByCourseID_{课程参考书表课程id}_{页码}
        private static final String ListByCourseID_CacheNamePrefix = "pc_courseBook_listByCourseID_";
        // pc_courseBook_listByBookID_{课程参考书表课本id}_{页码}
        private static final String ListByBookID_CacheNamePrefix = "pc_courseBook_listByBookID_";
    }
    //显示所有数据
    @ApiOperation(value = "显示CourseBook中所有信息",notes = "已测试")
    @GetMapping("coursebook/listAll")
    public void listAll(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String json =cache.get(CacheNameHelper.ListAll_CacheName);
        if(json==null){
           // System.out.println("查询数据库");
             List<CourseBook> courseBooks= courseBookService.selectAll();
            json=Result.build(ResultType.Success).appendData("courseBooks",courseBooks).convertIntoJSON();
            cache.set(CacheNameHelper.ListAll_CacheName,json);
        }
        response.getWriter().write(json);
    }
    /**
     * 新增CourseBook信息
     * @param courseBook
     * @return
     */
    @ApiOperation(value="新增CourseBook信息", notes="已测试")
    @ApiImplicitParam(name = "courseBook", value = "CourseBook详情实体", required = true, dataType = "CourseBook")
    @PostMapping("/courseBook")
    @ResponseBody
    public Result create(@RequestBody(required = false) CourseBook courseBook){
        if(courseBook != null){
            boolean success = courseBookService.insert(courseBook);
            if(success){
                // 清空相关缓存
                cache.delete(CacheNameHelper.ListAll_CacheName);
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }
    /**
     * 根据id删除CourseBookId
     * @param id
     * @return
     */
    @ApiOperation(value="根据id删除CourseBook信息", notes="已测试")
    @ApiImplicitParam(name = "id", value = "CourseBook主id", required = true, dataType = "bigint", paramType = "path")
    @DeleteMapping("/coursebook/{id}")
    @ResponseBody
    public Result destroy(@PathVariable Long id){
        boolean success = courseBookService.delete(id);
        if(success){
            // 清空相关缓存
            cache.delete(CacheNameHelper.ListAll_CacheName);
            return Result.build(ResultType.Success);
        }else{
            return Result.build(ResultType.Failed);
        }
    }
    /**
     * 根据id更新CourseBook信息
     * @param courseBook
     * @return
     */
    @ApiOperation(value="根据id更新CourseBook信息", notes="已测试")
    @ApiImplicitParam(name = "courseBook", value = "CourseBook实体", required = true, dataType = "CourseBook")
    @PutMapping("/courseBook")
    @ResponseBody
    public Result update(@RequestBody(required = false) CourseBook courseBook){
        if(courseBook != null && courseBook.getId() != null){
            boolean success = courseBookService.update(courseBook);
            if(success){
                cache.delete(CacheNameHelper.Receive_CacheNamePrefix + courseBook.getId());
                cache.delete(CacheNameHelper.ListAll_CacheName);
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }
    /**
     * 根据id查询CourseBook信息
     * @param id
     * @param response
     * @throws IOException
     */
    @ApiOperation(value="根据id查询CourseBook信息", notes="已测试")
    @ApiImplicitParam(name = "id", value = "CourseBook主id", required = false, dataType = "bigint", paramType = "path")
    @GetMapping("/courseBook/{id}")
    public void receive(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.Receive_CacheNamePrefix + id;
        String json = cache.get(cacheName);
        if(json == null){
            json = Result.build(ResultType.Success).appendData("courseBook",  courseBookService.select(id)).convertIntoJSON();
            cache.set(cacheName, json);
        }
        response.getWriter().write(json);
    }

    /**
     * 根据页码分页查询所有该页码所有CourseBook信息
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据页码分页查询该页码所有CourseBook信息", notes = "已测试")
    @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    @GetMapping(value = "/CourseBook/list/{pageNum}")
    public void list(
            @PathVariable Integer pageNum ,
            HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.List_CacheNamePrefix + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<CourseBook> pageInfo = courseBookService.selectPage(pageNum);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }


   /**
     * 根据CourseBook表的学校id和页码来分页查询
     * @param university_id
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据CourseBook表的学校id和页码来分页查询", notes = "测试中")
    @ApiImplicitParams({
            // @ApiImplicitParam(name="CourseBook主id", value = "CourseBook主id", required = true, dataType = "bigint", paramType = "path"),
            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/CourseBook/listByUniversityId/{university_id}/{pageNum}")
    public void listByUniversityID(@PathVariable long university_id,
                          @PathVariable Integer pageNum,
                          HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByUniversityID_CacheNamePrefix + university_id + "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<CourseBook> pageInfo = courseBookService.selectPageByUniversityID(pageNum, university_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }
    /**
     * 根据CourseBook表的课程id和页码来分页查询
     * @param course_id
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据CourseBook表的课程id和页码来分页查询", notes = "测试中")
    @ApiImplicitParams({
            // @ApiImplicitParam(name="CourseBook主id", value = "CourseBook主id", required = true, dataType = "bigint", paramType = "path"),
            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/CourseBook/listByCourseId/{course_id}/{pageNum}")
    public void listByCourseID(@PathVariable long course_id,
                                   @PathVariable Integer pageNum,
                                   HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByCourseID_CacheNamePrefix + course_id + "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<CourseBook> pageInfo = courseBookService.selectPageByCourseID(pageNum, course_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }
    /**
     * 根据CourseBook表的课本id和页码来分页查询
     * @param book_id
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据CourseBook表的课本id和页码来分页查询", notes = "测试中")
    @ApiImplicitParams({
            // @ApiImplicitParam(name="CourseBook主id", value = "CourseBook主id", required = true, dataType = "bigint", paramType = "path"),
            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/CourseBook/listByBookId/{book_id}/{pageNum}")
    public void listByBookID(@PathVariable long book_id,
                               @PathVariable Integer pageNum,
                               HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByBookID_CacheNamePrefix + book_id + "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<CourseBook> pageInfo = courseBookService.selectPageByBookID(pageNum, book_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }
}

