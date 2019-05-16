/**
 * Author: mokuanyuan 10:06 2019/4/25
 * @apiNote: 关于电子通信信息模块的Controller层（Http URL请求）的具体实现方法
 */

package edu.uni.userBaseInfo1.controller;

import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.config.GlobalConfig;
import edu.uni.userBaseInfo1.bean.*;
import edu.uni.userBaseInfo1.service.*;
import edu.uni.utils.RedisCache;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

//填写description内容可以在测试模块显示相应的文字和模块
@Api(description = "电子通信模块")
//Controller类（或者说Http）的请求路径
//如果添加了路径，则在需要调用该类的方法时需要在方法请求mapping路径前加上类的mapping路径
@RequestMapping("json/ecomm")
//标志这个类是一个controller类，用于被Spring扫描然后配置添加和配置相应的bean
@Controller
public class EcommController {



    @Autowired  //把Ecomm的Service层接口所有的方法自动装配到该对象中
    EcommService ecommService;
    @Autowired
    ApprovalMainService approvalMainService;
    @Autowired
    UserinfoApplyService userinfoApplyService;
    @Autowired
    UserinfoApplyApprovalService userinfoApplyApprovalService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;
    @Autowired
    ApprovalStepInchargeService approvalStepInchargeService;

    @Autowired  //把缓存工具类RedisCache相应的方法自动装配到该对象
    private RedisCache cache;

    @Autowired
    private GlobalConfig config;



    //内部类，专门用来管理每个get方法所对应缓存的名称。
    static class CacheNameHelper{
        // ub1_e_ecomm_{电子通信记录id}
        public static final String Receive_CacheNamePrefix = "ub1_e_ecomm_";
        // ub1_e_ecomms_listAll
        public static final String ListAll_CacheName = "ub1_e_ecomm_listAll";
    }


    /**
     * Author: mokuanyuan 10:58 2019/4/26
     * @param id
     * @return response
     * @apiNote: 获取电子通信记录详情
     */
    //以下说明为本类中所有方法的注解的解释，仅在本处注释（因为都几乎是一个模版）
    //@ApiOperation：用于在swagger2页面显示方法的提示信息
    //@GetMapping：规定方法的请求路径和方法的请求方式（Get方法）
    //@ApiImplicitParam：用于在swagger2页面测试时用于测试的变量，详细解释可以看Swagger2注解说明
    //@ResponseBody：指明该方法效果等同于通过response对象输出指定格式的数据（JSON）
    @ApiOperation( value = "以一个id获取一条电子通信记录详情",notes = "2019-5-5 15:53:53已通过测试" )
    @GetMapping("ecomm/{id}")
    @ApiImplicitParam(name = "id", value = "Ecomm表的一个id", required = false, dataType = "Long" , paramType = "path")
    @ResponseBody
    public void receive(@PathVariable Long id, HttpServletResponse response) throws IOException {
        //设置返回的数据格式
        response.setContentType("application/json;charset=utf-8");
        //拼接缓存键名（字符串）
        String cacheName = CacheNameHelper.Receive_CacheNamePrefix + id;
        //尝试在缓存中通过键名获取相应的键值
        //因为在Redis中，数据是以”“” "键-值"对 的形式储存的
        String json = cache.get(cacheName);
        //如果在缓存中找不到，那就从数据库里找
        if(json == null){
            Ecomm ecomm = ecommService.selectById(id);
            //把查询到的结果用Result工具类转换成json格式的字符串
            json = Result.build(ResultType.Success).appendData("ecomm",ecomm).convertIntoJSON();
            //如果有查询到数据，就把在数据库查到的数据放到缓存中
            if(ecomm != null){
                cache.set(cacheName,json);
            }
        }
        //到最后通过response对象返回json格式字符串的数据
        response.getWriter().write(json);

    }


    /**
     * Author: mokuanyuan 11:02 2019/4/26
     * @apiNote: 查询所有电子通信记录
     */
    @ApiOperation( value = "获取所有通信记录的内容",notes = "2019-5-5 15:53:53已通过测试" )
    @GetMapping("ecomms/listAll")
    @ResponseBody
    public void selectAll(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListAll_CacheName;
        String json = cache.get(cacheName);
        if(json == null){
            json = Result.build(ResultType.Success)
                    .appendData("ecomms",ecommService.selectAll()).convertIntoJSON();
            cache.set(cacheName,json);
        }
        response.getWriter().write(json);
    }

    /**
     * Author: chenenru 0:49 2019/5/5
     * @apiNote: 根据用户id获取用户的通信方式
     */
    @ApiOperation( value = "根据用户id获取用户的通信方式",notes = "2019-5-5 15:53:53已通过测试" )
    @GetMapping("ecommByUId/{userId}")
    @ResponseBody
    public void selectByUserId(@PathVariable Long userId,HttpServletResponse response) throws IOException{
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListAll_CacheName+userId;
        String json = cache.get(cacheName);
        if(json == null){
            json = Result.build(ResultType.Success)
                    .appendData("ecomms",ecommService.selectByUserId(userId)).convertIntoJSON();
            cache.set(cacheName,json);
        }
        response.getWriter().write(json);
    }

    /**
     * 新增电子通信方式
     * @param ecomm
     * @return  新增通信结果
     */
    @ApiOperation(value="新增电子通信方式", notes="2019-5-5 15:53:53已通过测试")
    @ApiImplicitParam(name = "ecomm", value = "电子通信方式详情实体", required = true, dataType = "Ecomm")
    @PostMapping("/ecomm")  //post请求方式
    @ResponseBody
    public Result create(@RequestBody(required = false) Ecomm ecomm){
        //检验页面传来的对象是否存在

        if(ecomm != null){
            int success = ecommService.insert(ecomm);
//            System.out.println("succ=="+ecomm.getId());
            if(success==1){
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
     * 删除电子通信方式
     * @param id
     * @return 删除操作结果
     */
    @ApiOperation(value="删除电子通信方式", notes="2019-5-5 15:53:53已通过测试")
    @ApiImplicitParam(name = "id", value = "电子通信方式id", required = true, dataType = "Long", paramType = "path")
    @DeleteMapping("/ecomm/{id}")   //delete请求
    @ResponseBody
    public Result destroy(@PathVariable Long id){
        boolean success = ecommService.delete(id);

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
     * @param ecomm
     * @return 更新操作结果
     */
    @ApiOperation(value="更新电子通信方式详情", notes="2019-5-5 15:53:53已通过测试")
    @ApiImplicitParam(name = "ecomm", value = "电子通信方式详情实体", required = true, dataType = "Ecomm")
    @PutMapping("/ecomm")   //Put请求
    @ResponseBody
    public Result update(@RequestBody(required = false) Ecomm ecomm){

        if(ecomm != null && ecomm.getId() != null){
            boolean success =ecommService.update(ecomm);
            if(success){
                //清除相应的缓存
                cache.delete(CacheNameHelper.Receive_CacheNamePrefix + ecomm.getId());
                cache.delete(CacheNameHelper.ListAll_CacheName);
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }

    /**
     * Author: laizhouhao 20:50 2019/5/9
     * @param requestMessage
     * @return Result
     * @apiNote: 申请修改通信方式, 点击申请时
     */
    @ApiOperation(value="申请修改通信方式", notes="2019年5月11日 14:33:14 已通过测试")
    @ApiImplicitParam(name = "requestMessage", value = "请求参数实体", required = true, dataType = "RequestMessage")
    @PostMapping("applyModifyEcomm/")
    @ResponseBody
    public Result ApplyModifyEcomm(@RequestBody RequestMessage requestMessage){
        //判断前端传过来的值是否为空
        if(requestMessage.getEcomm()!=null && requestMessage.getByWho()!=null && requestMessage.getUserinfoApply()!=null){
            boolean success = ecommService.clickApplyEcomm(requestMessage);
            if(success){
                //清除相应的缓存
                cache.delete(CacheNameHelper.Receive_CacheNamePrefix + "applyModifydEcomm");
                cache.delete(CacheNameHelper.ListAll_CacheName);
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }


    /**
     * Author: mokuanyuan 20:18 2019/5/14
     * @param file
     * @param response
     * @return 文件上传后的路径和文件名
     * @apiNote: 实现上传文件功能
     */
    @ApiOperation(value = "上传文件")
    @PostMapping("/uploadPicture")
    @ResponseBody
    public void upLoadFile(
            @ApiParam(value = "上传的文件", required = true)MultipartFile file,
            HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        if (file != null ){
            //文件后缀名带上"."的  比如 ".txt" ".sql"
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
//              String path = ResourceUtils.getURL("classpath:").getPath() ;
            //文件上传路径
            String path = "/E:/" ;
            //文件名（都用UUID命名吧）
            String fileName = UUID.randomUUID() + suffix;
            //传入路径和文件名这两个参数
            file.transferTo(new File(path, fileName));
            response.getWriter().write(Result.build(ResultType.Success).
                    appendData("path", "/E:/" + fileName).
                    appendData("fileName", fileName).
                    convertIntoJSON());
            }

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
