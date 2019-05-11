/**
 * Author: mokuanyuan 10:06 2019/4/25
 * @apiNote: 关于电子通信信息模块的Controller层（Http URL请求）的具体实现方法
 */

package edu.uni.userBaseInfo1.controller;

import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.userBaseInfo1.bean.*;
import edu.uni.userBaseInfo1.service.*;
import edu.uni.utils.RedisCache;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
//        System.out.println("o = "+ requestMessage.getEcomm());
        Ecomm ecomm = requestMessage.getEcomm();
        Long byWho = requestMessage.getByWho();
        UserinfoApply userInfo_apply = requestMessage.getUserinfoApply();
        //判断前端传过来的值是否为空
        if(requestMessage.getEcomm()!=null && requestMessage.getByWho()!=null && requestMessage.getUserinfoApply()!=null){
            //获取被修改的用户id
            Long user_id = ecomm.getUserId();
            //旧记录id
            Long oldId = ecomm.getId();
//            System.out.println("oldId = "+oldId);
            //将要插入的记录设置为无效
            ecomm.setDeleted(true);
            //将新纪录插入Ecomm表
            ecommService.insert(ecomm);
            //新纪录的id
            Long newId = ecomm.getId();
            //向userinfoApply增加审批业务id
            userInfo_apply.setApprovalMainId(approvalMainService.
                    selectIdByName(userInfo_apply.getUniversityId(), "审批学生申请修改照片"));
            //设置用户信息申请种类
            userInfo_apply.setInfoType(0);
            //设置用户信息申请旧信息记录id
            userInfo_apply.setOldInfoId(oldId);
            //设置用户信息申请新信息记录id
            userInfo_apply.setNewInfoId(newId);
            //设置用户信息申请开始时间
            userInfo_apply.setStartTime(ecomm.getDatetime());
            //设置用户信息创建时间
            userInfo_apply.setDatetime(ecomm.getDatetime());
            //设置用户信息申请写入者
            userInfo_apply.setByWho(byWho);
            //设置用户信息申请为有效
            userInfo_apply.setDeleted(false);
            //插入新的userinfoApply记录
            boolean successInfoApply = userinfoApplyService.insertUserinfoApply(userInfo_apply);
            //向审批流程表插入一条数据
            UserinfoApplyApproval applyApproval = new UserinfoApplyApproval();
            //设置学校id
            applyApproval.setUniversityId(userInfo_apply.getUniversityId());
            //设置申请表id
            applyApproval.setUserinfoApplyId(userInfo_apply.getId());
            //设置步骤，初始化为1
            applyApproval.setStep(1);
            //设置时间
            applyApproval.setDatetime(userInfo_apply.getStartTime());
            //设置写入者
            applyApproval.setByWho(byWho);
            //设置为有效
            applyApproval.setDeleted(false);
            //设置申请信息的种类
            applyApproval.setInfoType(userInfo_apply.getInfoType());
            //设置审批的角色名
            int st = applyApproval.getStep();
            Long mainId = userInfo_apply.getApprovalMainId();
            Long roleId = approvalStepInchargeService
                    .selectRoleIdByStepAppovalId(st,mainId);
            Role role = roleService.selectById(roleId);
            //设置申请人的用户id
            applyApproval.setApplyUserId(byWho);
            boolean successApplyApproval = userinfoApplyApprovalService.insertUserinfoApplyApproval(applyApproval);
            System.out.println("aaa="+applyApproval);
            if(successInfoApply && successApplyApproval){
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
     * Author: laizhouhao 20:50 2019/5/9
     * @param userinfoApplyApproval, user_id
     * @return Result
     * @apiNote: 审批修改通信方式的申请, 点击通过时
     */
    @ApiOperation(value="审批修改通信方式的申请, 点击通过时", notes="未测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userinfoApplyApproval", value = "用户申请审批流程表实体", required = true, dataType = "UserinfoApplyApproval"),
            @ApiImplicitParam(name = "user_id", value = "审批人id", required = true, dataType = "Long", paramType = "path")
    })
    @PostMapping("commituserinfoApply/{user_id}")
    @ResponseBody
    public Result ApplyModifyEcomm(@RequestBody UserinfoApplyApproval userinfoApplyApproval,@PathVariable Long user_id){
        if(userinfoApplyApproval != null){
            //比较当前步骤是否是最后一步
            boolean isLast = userService.isLastStep(userinfoApplyApproval.getStep(),userinfoApplyApproval.getUserinfoApplyId());
            //该步骤是最后一步
            if(isLast){
                //更新
                boolean firstSuccess = userService.endForPass(userinfoApplyApproval, user_id);
                //判断两个更新是否都成功
                if(firstSuccess) {
                    //清除相应的缓存
                    cache.delete(CacheNameHelper.Receive_CacheNamePrefix + "applyModifydEcomm");
                    cache.delete(CacheNameHelper.ListAll_CacheName);
                    return Result.build(ResultType.Success);
                }else{
                    return Result.build(ResultType.Failed);
                }
            }else{ //该审批不是最后一步
                boolean secondSuccess = userService.createForPass(userinfoApplyApproval, user_id);
                //操作成功
                if(secondSuccess){
                    //清除相应的缓存
                    cache.delete(CacheNameHelper.Receive_CacheNamePrefix + "applyModifydEcomm");
                    cache.delete(CacheNameHelper.ListAll_CacheName);
                    return Result.build(ResultType.Success);
                }else{
                    return Result.build(ResultType.Failed);
                }
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
