package edu.uni.userBaseInfo1.controller;

import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.userBaseInfo1.bean.SecondLevelDiscipline;
import edu.uni.userBaseInfo1.service.SecondLevelDisciplineService;
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
 * @Author chenenru
 * @ClassName SecondLevelDisciplineController
 * @Description
 * @Date 2019/4/30 15:51
 * @Version 1.0
 **/
//填写description内容可以在测试模块显示相应的文字和模块
@Api(description = "二级学科信息模块")
//Controller类（或者说Http）的请求路径
//如果添加了路径，则在需要调用该类的方法时需要在方法请求mapping路径前加上类的mapping路径
@RequestMapping("json/secondLevelDiscipline")
//标志这个类是一个controller类，用于被Spring扫描然后配置添加和配置相应的bean
@Controller
public class SecondLevelDisciplineController {

    //把SecondLevelDiscipline的Service层接口所有的方法自动装配到该对象中
    @Autowired
    SecondLevelDisciplineService secondLevelDisciplineService;
    @Autowired  //把缓存工具类RedisCache相应的方法自动装配到该对象
    private RedisCache cache;

    //内部类，专门用来管理每个get方法所对应缓存的名称。
    static class CacheNameHelper{
        // ub1_e_SecondLevelDiscipline_{二级学科记录id}
        public static final String Receive_CacheNamePrefix = "ub1_e_SecondLevelDiscipline_";
        // ub1_e_SecondLevelDisciplines_listAll
        public static final String ListAll_CacheName = "ub1_e_SecondLevelDisciplines_listAll";
    }

    /**
     * Author: chenenru 23:41 2019/4/29
     * @param id response
     * @return response
     * @apiNote: 获取二级学科详情
     */
    //以下说明为本类中所有方法的注解的解释，仅在本处注释（因为都几乎是一个模版）
    //@ApiOperation：用于在swagger2页面显示方法的提示信息
    //@GetMapping：规定方法的请求路径和方法的请求方式（Get方法）
    //@ApiImplicitParam：用于在swagger2页面测试时用于测试的变量，详细解释可以看Swagger2注解说明
    //@ResponseBody：指明该方法效果等同于通过response对象输出指定格式的数据（JSON）
    @ApiOperation( value = "以一个id获取一条二级学科记录详情",notes = "2019-5-2 11:08:06已通过测试" )
    @GetMapping("secondLevelDiscipline/{id}")
    @ApiImplicitParam(name = "id", value = "secondLevelDiscipline表的一个id", required = false, dataType = "Long" , paramType = "path")
    @ResponseBody
    public void receive(@PathVariable Long id, HttpServletResponse response) throws IOException {
        //设置返回的数据格式
        response.setContentType("application/json;charset=utf-8");
        //拼接缓存键名（字符串）
        String cacheName = SecondLevelDisciplineController.CacheNameHelper.Receive_CacheNamePrefix + id;
        //尝试在缓存中通过键名获取相应的键值
        //因为在Redis中，数据是以”“” "键-值"对 的形式储存的
        String json = cache.get(cacheName);
        //如果在缓存中找不到，那就从数据库里找
        if(json == null){
            SecondLevelDiscipline secondLevelDiscipline = secondLevelDisciplineService.selectSecondLevelDisciplineById(id);
            //把查询到的结果用Result工具类转换成json格式的字符串
            json = Result.build(ResultType.Success).appendData("secondLevelDiscipline",secondLevelDiscipline).convertIntoJSON();
            //如果有查询到数据，就把在数据库查到的数据放到缓存中
            if(secondLevelDiscipline != null){
                cache.set(cacheName,json);
            }
        }
        //到最后通过response对象返回json格式字符串的数据
        response.getWriter().write(json);

    }
    /**
     * Author: chenenru 23:44 2019/4/29
     * @param response
     * @return
     * @apiNote: 获取所有二级学科记录的内容
     */
    @ApiOperation( value = "获取所有二级学科记录的内容",notes = "2019-5-2 11:08:10已通过测试" )
    @GetMapping("secondLevelDisciplines/listAll")
    @ResponseBody
    public void selectAll(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = SecondLevelDisciplineController.CacheNameHelper.ListAll_CacheName;
        String json = cache.get(cacheName);
        if(json == null){
            json = Result.build(ResultType.Success)
                    .appendData("secondLevelDisciplines",secondLevelDisciplineService.selectAllSecondLevelDisciplines()).convertIntoJSON();
            cache.set(cacheName,json);
        }
        response.getWriter().write(json);
    }
    /**
     * Author: chenenru 23:47 2019/4/29
     * @param  SecondLevelDiscipline
     * @return Result
     * @apiNote: 新增二级学科信息
     */
    @ApiOperation(value="新增二级学科信息", notes="2019-5-2 11:08:15已通过测试")
    @ApiImplicitParam(name = "secondLevelDiscipline", value = "二级学科详情实体", required = true, dataType = "SecondLevelDiscipline")
    @PostMapping("/secondLevelDiscipline")  //post请求方式
    @ResponseBody
    public Result create(@RequestBody(required = false) SecondLevelDiscipline SecondLevelDiscipline){
        //检验页面传来的对象是否存在
        if(SecondLevelDiscipline != null){
            boolean success = secondLevelDisciplineService.insertSecondLevelDiscipline(SecondLevelDiscipline);
            if(success){
                // 清空相关缓存
                cache.delete(SecondLevelDisciplineController.CacheNameHelper.ListAll_CacheName);
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }
    /**
     * Author: chenenru 23:50 2019/4/29
     * @param id
     * @return Result
     * @apiNote: 删除二级学科
     */
    @ApiOperation(value="删除二级学科", notes="2019-5-2 11:08:21已通过测试")
    @ApiImplicitParam(name = "id", value = "二级学科的id", required = true, dataType = "Long", paramType = "path")
    @DeleteMapping("/secondLevelDiscipline/{id}")   //delete请求
    @ResponseBody
    public Result destroy(@PathVariable Long id){
        boolean success = secondLevelDisciplineService.deleteSecondLevelDiscipline(id);
        if(success){
            // 清空相关缓存
            cache.delete(SecondLevelDisciplineController.CacheNameHelper.ListAll_CacheName);
            return Result.build(ResultType.Success);
        }else{
            return Result.build(ResultType.Failed);
        }
    }
    /**
     * Author: chenenru 23:52 2019/4/29
     * @param SecondLevelDiscipline
     * @return Result
     * @apiNote: 更新二级学科详情
     */
    @ApiOperation(value="更新二级学科详情", notes="2019-5-2 11:08:26已通过测试")
    @ApiImplicitParam(name = "SecondLevelDiscipline", value = "二级学科详情实体", required = true, dataType = "SecondLevelDiscipline")
    @PutMapping("/secondLevelDiscipline")   //Put请求
    @ResponseBody
    public Result update(@RequestBody(required = false) SecondLevelDiscipline SecondLevelDiscipline){
        if(SecondLevelDiscipline != null && SecondLevelDiscipline.getId() != null){
            boolean success = secondLevelDisciplineService.updateSecondLevelDiscipline(SecondLevelDiscipline);
            if(success){
                //清除相应的缓存
                cache.delete(SecondLevelDisciplineController.CacheNameHelper.Receive_CacheNamePrefix + SecondLevelDiscipline.getId());
                cache.delete(SecondLevelDisciplineController.CacheNameHelper.ListAll_CacheName);
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
