package edu.uni.userBaseInfo1.controller;

import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.professionalcourses.bean.Academic;
import edu.uni.userBaseInfo1.service.MyAcademicService;
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

/**
 * @Author laizhouhao
 * @Description 关于受教育程度信息模块的Controller层（Http URL请求）的具体实现方法
 * @Date 15:12 2019/4/30
 **/

//填写description内容可以在测试模块显示相应的文字和模块
@Api(description = "受教育程度信息模块")
//Controller类（或者说Http）的请求路径
//如果添加了路径，则在需要调用该类的方法时需要在方法请求mapping路径前加上类的mapping路径
@RequestMapping("json/academic")
//标志这个类是一个controller类，用于被Spring扫描然后配置添加和配置相应的bean
@Controller
public class MyAcademicController {

    @Autowired  //把Academic的Service层接口所有的方法自动装配到该对象中
    private MyAcademicService myAcademicService;

    @Autowired  //把缓存工具类RedisCache相应的方法自动装配到该对象
    private RedisCache cache;

    //内部类，专门用来管理每个get方法所对应缓存的名称。
    static class CacheNameHelper{
        // ub1_a_academic_{学生信息记录id}
        public static final String Receive_CacheNamePrefix = "ub1_a_academic_";
        // ub1_a_academic_listAll
        public static final String ListAll_CacheName = "ub1_a_academic_listAll";
    }

    /**
     * Author: laizhouhao 15:17 2019/4/30
     * @param id
     * @return response
     * @apiNote: 获取受教育程度信息详情
     */
    //以下说明为本类中所有方法的注解的解释，仅在本处注释（因为都几乎是一个模版）
    //@ApiOperation：用于在swagger2页面显示方法的提示信息
    //@GetMapping：规定方法的请求路径和方法的请求方式（Get方法）
    //@ApiImplicitParam：用于在swagger2页面测试时用于测试的变量，详细解释可以看Swagger2注解说明
    //@ResponseBody：指明该方法效果等同于通过response对象输出指定格式的数据（JSON）
    @ApiOperation( value = "根据id获取受教育程度信息",notes = "2019年5月6日 10:57:28已通过测试" )
    @GetMapping("academic/{id}")
    @ApiImplicitParam(name = "id", value = "Academic表的一个id", required = false, dataType = "Long" , paramType = "path")
    @ResponseBody
    public void receive(@PathVariable Long id, HttpServletResponse response) throws IOException {
        //设置返回的数据格式
        response.setContentType("application/json;charset=utf-8");
        //拼接缓存键名（字符串）
        String cacheName = edu.uni.userBaseInfo1.controller.MyAcademicController.CacheNameHelper.Receive_CacheNamePrefix + id;
        //尝试在缓存中通过键名获取相应的键值
        //因为在Redis中，数据是以”“” "键-值"对 的形式储存的
        String json = cache.get(cacheName);
        //如果在缓存中找不到，那就从数据库里找
        if(json == null){
            Academic academic = myAcademicService.selectById(id);
            //把查询到的结果用Result工具类转换成json格式的字符串
            json = Result.build(ResultType.Success).appendData("academic",academic).convertIntoJSON();
            //如果有查询到数据，就把在数据库查到的数据放到缓存中
            if(academic != null){
                cache.set(cacheName,json);
            }
        }
        //到最后通过response对象返回json格式字符串的数据
        response.getWriter().write(json);

    }

    /**
     * Author: laizhouhao 15:19 2019/4/30
     * @return: response
     * @apiNote: 查询受教育程度表所有受教育程度信息
     */
    @ApiOperation( value = "查询受教育程度表所有受教育程度信息",notes = "2019年5月6日 10:59:37 已通过测试" )
    @GetMapping("academic/listAll")
    @ResponseBody
    public void selectAll(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = edu.uni.userBaseInfo1.controller.MyAcademicController.CacheNameHelper.ListAll_CacheName;
        String json = cache.get(cacheName);
        if(json == null){
            json = Result.build(ResultType.Success)
                    .appendData("academics", myAcademicService.selectAll()).convertIntoJSON();
            cache.set(cacheName,json);
        }
        response.getWriter().write(json);
    }

    /**
     * Author: laizhouhao 15:21 2019/4/30
     * @param: academic
     * @return: Result
     * @apiNote: 新增受教育程度信息
     */
    @ApiOperation(value="新增受教育程度信息", notes="2019年5月6日 11:00:30 已通过测试")
    @ApiImplicitParam(name = "academic", value = "受教育程度信息实体", required = true, dataType = "Academic")
    @PostMapping("/academic")  //post请求方式
    @ResponseBody
    public Result create(@RequestBody(required = false) Academic academic){
        //检验页面传来的对象是否存在
        if(academic != null){
            boolean success = myAcademicService.insert(academic);
            if(success){
                // 清空相关缓存
                cache.delete(edu.uni.userBaseInfo1.controller.MyAcademicController.CacheNameHelper.ListAll_CacheName);
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }

    /**
     * Author: laizhouhao 15:23 2019/4/30
     * @param id
     * @return 删除操作结果
     * @apiNote: 删除受教育程度信息
     */
    @ApiOperation(value="删除受教育程度信息", notes="2019年5月6日 11:04:16 已通过测试")
    @ApiImplicitParam(name = "id", value = "受教育程度id", required = true, dataType = "Long", paramType = "path")
    @DeleteMapping("/academic/{id}")   //delete请求
    @ResponseBody
    public Result destroy(@PathVariable Long id){
        boolean success = myAcademicService.delete(id);
        if(success){
            // 清空相关缓存
            cache.delete(edu.uni.userBaseInfo1.controller.MyAcademicController.CacheNameHelper.ListAll_CacheName);
            return Result.build(ResultType.Success);
        }else{
            return Result.build(ResultType.Failed);
        }
    }

    /**
     * Author: laizhouhao 15:25 2019/4/30
     * @param academic
     * @return 更新操作结果
     * @apiNote: 更新受教育程度信息
     */
    /**
    @ApiOperation(value="更新受教育程度信息详情", notes="2019年5月6日 11:06:04 已通过测试")
    @ApiImplicitParam(name = "academic", value = "受教育程度信息实体", required = true, dataType = "Academic")
    @PutMapping("/academic")   //Put请求
    @ResponseBody
    public Result update(@RequestBody(required = false) Academic academic){
        if(academic != null && academic.getId() != null){
            boolean success = myAcademicService.update(academic);
            if(success){
                //清除相应的缓存
                cache.delete(edu.uni.userBaseInfo1.controller.AcademicController.CacheNameHelper.Receive_CacheNamePrefix + academic.getId());
                cache.delete(edu.uni.userBaseInfo1.controller.AcademicController.CacheNameHelper.ListAll_CacheName);
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
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
        //String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        //上传文件名
        //String filename = CommonUtils.generateUUID() + suffix;
        String filename = LocalDateTime.now() + "-" + file.getOriginalFilename();
        //服务端保存的文件对象
        File serverFile = new File(uploadDir + filename);
        //将上传的文件写入服务器端文件内
        file.transferTo(serverFile);
        return filename;
    }


}
