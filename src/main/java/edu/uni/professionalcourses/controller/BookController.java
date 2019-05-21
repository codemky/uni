package edu.uni.professionalcourses.controller;

import com.github.pagehelper.PageInfo;
import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.professionalcourses.bean.Book;
import edu.uni.professionalcourses.bean.Course;
import edu.uni.professionalcourses.service.BookService;
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
 功能： Book的增删查改
 */
@Api(description = "专业课程模块")
@Controller
@RequestMapping("json/professionalcourses")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private RedisCache cache;
    /**
     * 内部类，专门用来管理每个get方法所对应缓存的名称。
     */
    static class CacheNameHelper{
        //pc_Book_listAll
        public static final String ListAll_CacheName = "pc_Book_listAll";
        // pc_Book_{类别id}
        public static final String Receive_CacheNamePrefix = "pc_Book_";
        // pc_Book_list_{页码}
        private static final String List_CacheNamePrefix = "pc_Book_list_";
        // pc_Book_listByUniversityID_{书籍、教材、参考书表学校id}_{页码}
        private static final String ListByUniversityID_CacheNamePrefix = "pc_Book_listByUniversityID_";
    }
    //显示所有数据
    @ApiOperation(value = "显示Book中所有信息",notes = "已测试")
    @GetMapping("book/listAll")
    public void listAll(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String json =cache.get(CacheNameHelper.ListAll_CacheName);
        if(json==null){
            // System.out.println("查询数据库");
            List<Book> books= bookService.selectAll();
            json= Result.build(ResultType.Success).appendData("books",books).convertIntoJSON();
            cache.set(CacheNameHelper.ListAll_CacheName,json);
        }
        response.getWriter().write(json);
    }
    /**
     * 新增Book信息
     * @param book
     * @return
     */
    /*因为新增book信息后，更新course_book表，即book与course的关系表需要course，需要前端传递Course信息进来才能获取到course_id，
    因为在测试中并没有实现从前端接受到Course信息，所以以写死Course信息进行测试insert方法是否正常*/
    @ApiOperation(value="新增Book信息", notes="未测试")
    @ApiImplicitParam(name = "book", value = "Book详情实体", required = true, dataType = "Book")
    @PostMapping("/book")
    @ResponseBody
    public Result create(@RequestBody(required = false) Book book){
        if(book!= null){
            Course course=new Course();
            course.setId(10L);
            boolean success = bookService.insert(book,course);
            if(success){
                // 清空相关缓存
                cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
                cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName );
                cache.deleteByPaterm(CacheNameHelper.ListByUniversityID_CacheNamePrefix + "*");
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
     * 根据id删除BookId
     * @param id
     * @return
     */
    @ApiOperation(value="根据id删除Book信息", notes="已测试")
    @ApiImplicitParam(name = "id", value = "Book主id", required = true, dataType = "bigint", paramType = "path")
    @DeleteMapping("/book/{id}")
    @ResponseBody
    public Result destroy(@PathVariable Long id){
        boolean success = bookService.delete(id);
        if(success){
            // 清空相关缓存
            cache.deleteByPaterm(CacheNameHelper.List_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.ListAll_CacheName );
            cache.deleteByPaterm(CacheNameHelper.ListByUniversityID_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.Receive_CacheNamePrefix + "*");
            return Result.build(ResultType.Success);
        }else{
            return Result.build(ResultType.Failed);
        }
    }
    /**
     * 根据id更新Book信息
     * @param book
     * @return
     */
    @ApiOperation(value="根据id更新Book信息", notes="未测试")
    @ApiImplicitParam(name = "book", value = "Book实体", required = true, dataType = "Book")
    @PutMapping("/book")
    @ResponseBody
    public Result update(@RequestBody(required = false) Book book){
        if(book != null && book.getId() != null){
            boolean success = bookService.update(book);
            if(success){
                cache.delete(CacheNameHelper.Receive_CacheNamePrefix + book.getId());
                cache.delete(CacheNameHelper.ListAll_CacheName);
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }
    /**
     * 根据id查询Book信息
     * @param id
     * @param response
     * @throws IOException
     */
    @ApiOperation(value="根据id查询Book信息", notes="已测试")
    @ApiImplicitParam(name = "id", value = "Book主id", required = false, dataType = "bigint", paramType = "path")
    @GetMapping("/book/{id}")
    public void receive(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.Receive_CacheNamePrefix + id;
        String json = cache.get(cacheName);
        if(json == null){
            json = Result.build(ResultType.Success).appendData("book",  bookService.select(id)).convertIntoJSON();
            cache.set(cacheName, json);
        }
        response.getWriter().write(json);
    }

    /**
     * 根据页码分页查询所有该页码所有Book信息
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据页码分页查询该页码所有Book信息", notes = "已测试")
    @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    @GetMapping(value = "/book/list/{pageNum}")
    public void list(
            @PathVariable Integer pageNum ,
            HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.List_CacheNamePrefix + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<Book> pageInfo = bookService.selectPage(pageNum);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }
    /**
     * 根据Book表的学校id和页码来分页查询
     * @param university_id
     * @param pageNum
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "根据Book表的学校id和页码来分页查询", notes = "测试中")
    @ApiImplicitParams({

            @ApiImplicitParam(name="pageNum", value = "页码", required = true, dataType = "Integer", paramType = "path")
    })
    @GetMapping(value = "/book/listByUniversityId/{university_id}/{pageNum}")
    public void listByUniversityID(@PathVariable long university_id,
                                   @PathVariable Integer pageNum,
                                   HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListByUniversityID_CacheNamePrefix + university_id + "_" + pageNum;
        String json = cache.get(cacheName);
        if(json == null){
            PageInfo<Book> pageInfo = bookService.selectPageByUniversityID(pageNum, university_id);
            json = Result.build(ResultType.Success).appendData("pageInfo", pageInfo).convertIntoJSON();
            if(pageInfo != null) {
                cache.set(cacheName, json);
            }
        }
        response.getWriter().write(json);
    }
}
