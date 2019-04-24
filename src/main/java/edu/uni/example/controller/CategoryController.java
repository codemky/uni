package edu.uni.example.controller;

import edu.uni.bean.ResultType;
import edu.uni.example.bean.Category;
import edu.uni.bean.Result;
import edu.uni.example.service.CategoryService;
import edu.uni.utils.CommonUtils;
import edu.uni.utils.RedisCache;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Api(description = "示例模块")
@Controller
@RequestMapping("json/example")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private RedisCache cache;

    /**
     * 内部类，专门用来管理每个get方法所对应缓存的名称。
     */
    static class CacheNameHelper{
        // e_c_category_{类别id}
        public static final String Receive_CacheNamePrefix = "e_category_";
        // e_c_categories_listAll
        public static final String ListAll_CacheName = "e_categories_listAll";
    }

    /**
     * 新增类别
     * @param category
     * @return
     */
    @ApiOperation(value="新增类别", notes="已测试")
    @ApiImplicitParam(name = "category", value = "类别详情实体", required = true, dataType = "Category")
    @PostMapping("/category")
    @ResponseBody
    public Result create(@RequestBody(required = false) Category category){
        if(category != null){
            boolean success = categoryService.insert(category);
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
     * 删除类别
     * @param id
     * @return
     */
    @ApiOperation(value="删除类别", notes="已测试")
    @ApiImplicitParam(name = "id", value = "类别id", required = true, dataType = "Integer", paramType = "path")
    @DeleteMapping("/category/{id}")
    @ResponseBody
    public Result destroy(@PathVariable Integer id){
        boolean success = categoryService.delete(id);
        if(success){
            // 清空相关缓存
            cache.delete(CacheNameHelper.ListAll_CacheName);
            return Result.build(ResultType.Success);
        }else{
            return Result.build(ResultType.Failed);
        }
    }

    /**
     * 更新类别
     * @param category
     * @return
     */
    @ApiOperation(value="更新类别id更新类别信息", notes="已测试")
    @ApiImplicitParam(name = "category", value = "类别实体", required = true, dataType = "Category")
    @PutMapping("/category")
    @ResponseBody
    public Result update(@RequestBody(required = false) Category category){
        if(category != null && category.getId() != null){
            boolean success = categoryService.update(category);
            if(success){
                cache.delete(CacheNameHelper.Receive_CacheNamePrefix + category.getId());
                cache.delete(CacheNameHelper.ListAll_CacheName);
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }


    /**
     * 获取类别详情
     * @param id
     * @param response
     * @throws IOException
     */
    @ApiOperation(value="根据类别id获取类别详情", notes="已测试")
    @ApiImplicitParam(name = "id", value = "类别id", required = false, dataType = "Integer", paramType = "path")
    @GetMapping("/category/{id}")
    public void receive(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.Receive_CacheNamePrefix + id;
        String json = cache.get(cacheName);
        if(json == null){
            json = Result.build(ResultType.Success).appendData("category",  categoryService.select(id)).convertIntoJSON();
            cache.set(cacheName, json);
        }
        response.getWriter().write(json);
    }

    /**
     * 列举所有类别
     * @param response
     * @throws IOException
     */
    @ApiOperation(value="列举所有类别", notes="已测试")
    @GetMapping(value = "/categories/listAll")
    public void list(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListAll_CacheName;
        String json = cache.get(cacheName);
        if(json == null){
            json = Result.build(ResultType.Success).appendData("categories", categoryService.selectAll()).convertIntoJSON();
            cache.set(cacheName, json);
        }
        response.getWriter().write(json);
    }


    /**
     * <p>
     *     上传文件方法
     * </p>
     * @param uploadDir 上传文件目录，如 F:\\file\\ , /home/file/
     * @param file
     * @return 文件名
     * @throws Exception
     */
    private String executeUpload(String uploadDir, MultipartFile file) throws Exception{
        //获取文件后缀名
//        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        //上传文件名
//        String filename = CommonUtils.generateUUID() + suffix;
        String filename = LocalDateTime.now() + "-" + file.getOriginalFilename();
        //服务端保存的文件对象
        File serverFile = new File(uploadDir + filename);
        //将上传的文件写入服务器端文件内
        file.transferTo(serverFile);
        return filename;
    }
}
