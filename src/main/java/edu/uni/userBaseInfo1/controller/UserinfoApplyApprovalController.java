package edu.uni.userBaseInfo1.controller;

import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.userBaseInfo1.bean.UserinfoApplyApproval;
import edu.uni.userBaseInfo1.service.UserinfoApplyApprovalService;
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
 * @ClassName UserinfoApplyApprovalController
 * @Description
 * @Date 2019/4/30 15:52
 * @Version 1.0
 **/
//填写description内容可以在测试模块显示相应的文字和模块
@Api(description = "用户信息审批流程信息模块")
//Controller类（或者说Http）的请求路径
//如果添加了路径，则在需要调用该类的方法时需要在方法请求mapping路径前加上类的mapping路径
@RequestMapping("json/userinfoApplyApproval")
//标志这个类是一个controller类，用于被Spring扫描然后配置添加和配置相应的bean
@Controller
public class UserinfoApplyApprovalController {

    //把UserinfoApplyApproval的Service层接口所有的方法自动装配到该对象中
    @Autowired
    UserinfoApplyApprovalService userinfoApplyApprovalService;
    @Autowired  //把缓存工具类RedisCache相应的方法自动装配到该对象
    private RedisCache cache;

    //内部类，专门用来管理每个get方法所对应缓存的名称。
    static class CacheNameHelper{
        // ub1_e_UserinfoApplyApproval_{用户信息审批流程记录id}
        public static final String Receive_CacheNamePrefix = "ub1_e_UserinfoApplyApproval_";
        // ub1_e_UserinfoApplyApprovals_listAll
        public static final String ListAll_CacheName = "ub1_e_UserinfoApplyApprovals_listAll";
    }

    /**
     * Author: chenenru 23:41 2019/4/29
     * @param id response
     * @return response
     * @apiNote: 获取用户信息审批流程详情
     */
    //以下说明为本类中所有方法的注解的解释，仅在本处注释（因为都几乎是一个模版）
    //@ApiOperation：用于在swagger2页面显示方法的提示信息
    //@GetMapping：规定方法的请求路径和方法的请求方式（Get方法）
    //@ApiImplicitParam：用于在swagger2页面测试时用于测试的变量，详细解释可以看Swagger2注解说明
    //@ResponseBody：指明该方法效果等同于通过response对象输出指定格式的数据（JSON）
    @ApiOperation( value = "以一个id获取一条用户信息审批流程记录详情",notes = "2019-5-2 11:09:11已通过测试" )
    @GetMapping("userinfoApplyApproval/{id}")
    @ApiImplicitParam(name = "id", value = "userinfoApplyApproval表的一个id", required = false, dataType = "Integer" , paramType = "path")
    @ResponseBody
    public void receive(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        //设置返回的数据格式
        response.setContentType("application/json;charset=utf-8");
        //拼接缓存键名（字符串）
        String cacheName = UserinfoApplyApprovalController.CacheNameHelper.Receive_CacheNamePrefix + id;
        //尝试在缓存中通过键名获取相应的键值
        //因为在Redis中，数据是以”“” "键-值"对 的形式储存的
        String json = cache.get(cacheName);
        //如果在缓存中找不到，那就从数据库里找
        if(json == null){
            UserinfoApplyApproval userinfoApplyApproval = userinfoApplyApprovalService.selectUserinfoApplyApprovalById(id);
            //把查询到的结果用Result工具类转换成json格式的字符串
            json = Result.build(ResultType.Success).appendData("userinfoApplyApproval",userinfoApplyApproval).convertIntoJSON();
            //如果有查询到数据，就把在数据库查到的数据放到缓存中
            if(userinfoApplyApproval != null){
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
     * @apiNote: 获取所有用户信息审批流程记录的内容
     */
    @ApiOperation( value = "获取所有用户信息审批流程记录的内容",notes = "2019-5-2 11:09:21已通过测试" )
    @GetMapping("userinfoApplyApprovals/listUserinfoApplyApprovalAll")
    @ResponseBody
    public void selectAll(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = UserinfoApplyApprovalController.CacheNameHelper.ListAll_CacheName;
        String json = cache.get(cacheName);
        if(json == null){
            json = Result.build(ResultType.Success)
                    .appendData("userinfoApplyApprovals",userinfoApplyApprovalService.selectAllUserinfoApplyApprovals()).convertIntoJSON();
            cache.set(cacheName,json);
        }
        response.getWriter().write(json);
    }
    /**
     * Author: chenenru 23:47 2019/4/29
     * @param  userinfoApplyApproval
     * @return Result
     * @apiNote: 新增用户信息审批流程信息
     */
    @ApiOperation(value="新增用户信息审批流程信息", notes="2019-5-2 11:09:25已通过测试")
    @ApiImplicitParam(name = "userinfoApplyApproval", value = "用户信息审批流程详情实体", required = true, dataType = "UserinfoApplyApproval")
    @PostMapping("/userinfoApplyApproval")  //post请求方式
    @ResponseBody
    public Result create(@RequestBody(required = false) UserinfoApplyApproval userinfoApplyApproval){
        //检验页面传来的对象是否存在
        if(userinfoApplyApproval != null){
            boolean success = userinfoApplyApprovalService.insertUserinfoApplyApproval(userinfoApplyApproval);
            if(success){
                // 清空相关缓存
                cache.delete(UserinfoApplyApprovalController.CacheNameHelper.ListAll_CacheName);
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
     * @apiNote: 删除用户信息审批流程
     */
    @ApiOperation(value="删除用户信息审批流程", notes="2019-5-2 11:09:29已通过测试")
    @ApiImplicitParam(name = "id", value = "用户信息审批流程的id", required = true, dataType = "Integer", paramType = "path")
    @DeleteMapping("/userinfoApplyApproval/{id}")   //delete请求
    @ResponseBody
    public Result destroy(@PathVariable Integer id){
        boolean success = userinfoApplyApprovalService.deleteUserinfoApplyApproval(id);
        if(success){
            // 清空相关缓存
            cache.delete(UserinfoApplyApprovalController.CacheNameHelper.ListAll_CacheName);
            return Result.build(ResultType.Success);
        }else{
            return Result.build(ResultType.Failed);
        }
    }
    /**
     * Author: chenenru 23:52 2019/4/29
     * @param userinfoApplyApproval
     * @return Result
     * @apiNote: 更新用户信息审批流程详情
     */
    @ApiOperation(value="更新用户信息审批流程详情", notes="2019-5-2 11:09:34已通过测试")
    @ApiImplicitParam(name = "userinfoApplyApproval", value = "用户信息审批流程详情实体", required = true, dataType = "UserinfoApplyApproval")
    @PutMapping("/userinfoApplyApproval")   //Put请求
    @ResponseBody
    public Result update(@RequestBody(required = false) UserinfoApplyApproval userinfoApplyApproval){
        if(userinfoApplyApproval != null && userinfoApplyApproval.getId() != null){
            boolean success = userinfoApplyApprovalService.updateUserinfoApplyApproval(userinfoApplyApproval);
            if(success){
                //清除相应的缓存
                cache.delete(UserinfoApplyApprovalController.CacheNameHelper.Receive_CacheNamePrefix + userinfoApplyApproval.getId());
                cache.delete(UserinfoApplyApprovalController.CacheNameHelper.ListAll_CacheName);
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
