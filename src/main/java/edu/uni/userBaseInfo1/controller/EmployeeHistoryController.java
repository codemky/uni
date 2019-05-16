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
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @Author laizhouhao
 * @Description 关于雇员简历信息模块的Controller层（Http URL请求）的具体实现方法
 * @Date 16:15 2019/4/29
 **/
//填写description内容可以在测试模块显示相应的文字和模块
@Api(description = "雇员简历信息模块")
//Controller类（或者说Http）的请求路径
//如果添加了路径，则在需要调用该类的方法时需要在方法请求mapping路径前加上类的mapping路径
@RequestMapping("json/employeeHistory")
//标志这个类是一个controller类，用于被Spring扫描然后配置添加和配置相应的bean
@Controller
public class EmployeeHistoryController {
    //把EmployeeHistory的Service接口层的所有方法自动装配到该对象中
    @Autowired
    private EmployeeHistoryService employeeHistoryService;
    @Autowired
    private ApprovalMainService approvalMainService;
    @Autowired
    UserinfoApplyService userinfoApplyService;
    //把缓存工具类RedisCache相应的方法自动装配到该对象
    @Autowired
    UserinfoApplyApprovalService userinfoApplyApprovalService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;
    @Autowired
    ApprovalStepInchargeService approvalStepInchargeService;
    @Autowired
    private RedisCache cache;

    //内部类，专门用来管理每个get方法所对应缓存的名称。
    static class CacheNameHelper{
        // ub1_e_employeeHistory_{雇员简历信息记录id}
        public static final String Receive_CacheNamePrefix = "ub1_e_employeeHistory_";
        // ub1_e_employeeHistory_listAll
        public static final String ListAll_CacheName = "ub1_e_employeeHistory_listAll";
    }

    /**
     * Author: laizhouhao 9:55 2019/4/30
     * @param id
     * @return response
     * @apiNote: 获取雇员简历信息
     */
    //以下说明为本类中所有方法的注解的解释，仅在本处注释（因为都几乎是一个模版）
    //@ApiOperation：用于在swagger2页面显示方法的提示信息
    //@GetMapping：规定方法的请求路径和方法的请求方式（Get方法）
    //@ApiImplicitParam：用于在swagger2页面测试时用于测试的变量，详细解释可以看Swagger2注解说明
    //@ResponseBody：指明该方法效果等同于通过response对象输出指定格式的数据（JSON）
    @ApiOperation( value = "以一个id获取一条雇员简历信息",notes = "2019-5-2 11:06:25已通过测试" )
    @GetMapping("employeeHistory/{id}")
    @ApiImplicitParam(name = "id", value = "EmployeeHistory表的一个id", required = false, dataType = "Long" , paramType = "path")
    @ResponseBody
    public void receive(@PathVariable Long id, HttpServletResponse response) throws IOException {
        //设置返回的数据格式
        response.setContentType("application/json;charset=utf-8");
        //拼接缓存键名（字符串）
        String cacheName = EmployeeHistoryController.CacheNameHelper.Receive_CacheNamePrefix + id;
        //尝试在缓存中通过键名获取相应的键值
        //因为在Redis中，数据是以”“” "键-值"对 的形式储存的
        String json = cache.get(cacheName);
        //如果在缓存中找不到，那就从数据库里找
        if(json == null){
            EmployeeHistory employeeHistory = employeeHistoryService.selectById(id);
            //把查询到的结果用Result工具类转换成json格式的字符串
            json = Result.build(ResultType.Success).appendData("employeeHistory",employeeHistory).convertIntoJSON();
            //如果有查询到数据，就把在数据库查到的数据放到缓存中
            if(employeeHistory != null){
                cache.set(cacheName,json);
            }
        }
        //到最后通过response对象返回json格式字符串的数据
        response.getWriter().write(json);

    }

    /**
     * Author: laizhouhao 18:23 2019/5/6
     * @param user_id
     * @return response
     * @apiNote: 根据用户的id查询对应的雇员简历信息
     */
    @ApiOperation( value = "根据用户的id查询对应的雇员简历信息",notes = "2019年5月6日 18:25:13 已通过测试" )
    @GetMapping("employeeHistoryByUserId/{user_id}")
    @ResponseBody
    public void selectByUserId(@PathVariable Long user_id,HttpServletResponse response) throws IOException{
        response.setContentType("application/json;charset=utf-8");
        String cacheName = EmployeeHistoryController.CacheNameHelper.ListAll_CacheName+user_id;
        String json = cache.get(cacheName);
        if(json == null){
            json = Result.build(ResultType.Success)
                    .appendData("employeeHistorys",employeeHistoryService.selectByUserId(user_id)).convertIntoJSON();
            cache.set(cacheName,json);
        }
        response.getWriter().write(json);
    }

    /**
     * Author: laizhouhao 16:26 2019/4/29
     * @return response
     * @apiNote: 查询雇员简历信息表的所有记录
     */
    @ApiOperation( value = "查询雇员简历信息表的所有记录",notes = "2019-5-2 11:06:16已通过测试" )
    @GetMapping("employeeHistorys/listAll")
    @ResponseBody
    public void selectAll(HttpServletResponse response)throws Exception{
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListAll_CacheName;
        String json = cache.get(cacheName);
        if(json==null){
            json = Result.build(ResultType.Success).appendData("employeeHistorys", employeeHistoryService.selectAll()).convertIntoJSON();
            cache.set(json, cacheName);
        }
        response.getWriter().write(json);
    }

    /**
     * Author: laizhouhao 16:40 2019/4/29
     * @param employeeHistory
     * @return 新增雇员简历信息结果
     * @apiNote 新增雇员简历信息
     */
    @ApiOperation(value="新增雇员简历信息", notes="2019-5-2 11:06:11已通过测试")
    @ApiImplicitParam(name = "employeeHistory", value = "雇员简历信息实体", required = true, dataType = "EmployeeHistory")
    @PostMapping("/employeeHistory")  //post请求方式
    @ResponseBody
    public Result create(@RequestBody(required = false)EmployeeHistory employeeHistory){
        if(employeeHistory!=null){
            boolean success = employeeHistoryService.insert(employeeHistory);
            if(success){
                //清除对应的缓存
                cache.delete(EmployeeHistoryController.CacheNameHelper.ListAll_CacheName);
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }

    /**
     * Author: laizhouhao 16:47 2019/4/29
     * @param id
     * @return 删除雇员简历信息结果
     * @apiNote 根据id删除雇员简历信息
     */
    @ApiOperation(value="根据id删除雇员简历信息", notes="2019-5-2 11:06:07已通过测试")
    @ApiImplicitParam(name = "id", value = "雇员简历信息id", required = true, dataType = "Long", paramType = "path")
    @DeleteMapping("/employeeHistory/{id}")   //delete请求
    @ResponseBody
    public Result destroy(@PathVariable long id){
        boolean success = employeeHistoryService.delete(id);
        if(success){
            // 清空相关缓存
            cache.delete(EmployeeHistoryController.CacheNameHelper.ListAll_CacheName);
            return Result.build(ResultType.Success);
        }else{
            return Result.build(ResultType.Failed);
        }
    }

    /**
     * Author: laizhouhao 11:01 2019/4/30
     * @param employeeHistory
     * @return 更新操作结果
     * @apiNote 更新雇员简历信息
     */
    @ApiOperation(value="更新雇员简历信息", notes="2019-5-2 11:06:03已通过测试")
    @ApiImplicitParam(name = "employeeHistory", value = "雇员简历信息详情实体", required = true, dataType = "EmployeeHistory")
    @PutMapping("/employeeHistory")   //Put请求
    @ResponseBody
    public Result update(@RequestBody(required = false) EmployeeHistory employeeHistory){
        if(employeeHistory != null && employeeHistory.getId() != null){
            boolean success = employeeHistoryService.update(employeeHistory);
            if(success){
                //清除相应的缓存
                cache.delete(EmployeeHistoryController.CacheNameHelper.Receive_CacheNamePrefix + employeeHistory.getId());
                cache.delete(EmployeeHistoryController.CacheNameHelper.ListAll_CacheName);
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }

    /**
     * Author: chenenru 2019-5-11 09:19:51
     * @param requestMessage
     * @return Result
     * @apiNote: 申请修改雇员的简历方式
     */
    /*@ApiOperation(value="申请修改雇员的简历方式", notes="未测试")
    @ApiImplicitParam(name = "requestMessage", value = "请求参数实体", required = true, dataType = "RequestMessage")
    @PostMapping("applyModifydEmployeeHistory/")
    @ResponseBody
    public Result ApplyModifyEmployeeHistory(@RequestBody RequestMessage requestMessage){
//        System.out.println("o = "+ requestMessage.getEmployeeHistory());
        EmployeeHistory employeeHistory = requestMessage.getEmployeeHistory();
        Long byWho = requestMessage.getByWho();
        UserinfoApply userInfo_apply = requestMessage.getUserinfoApply();
        //判断前端传过来的值是否为空
        if(requestMessage.getEmployeeHistory()!=null && requestMessage.getByWho()!=null && requestMessage.getUserinfoApply()!=null){
            //获取被修改的用户id
            Long user_id = employeeHistory.getUserId();
            //旧记录id
            Long oldId = employeeHistory.getId();
//            System.out.println("oldId = "+oldId);
            //将要插入的记录设置为无效
            employeeHistory.setDeleted(true);
            //将新纪录插入EmployeeHistory表
            employeeHistoryService.insert(employeeHistory);
            //新纪录的id
            Long newId = employeeHistory.getId();

            //向userinfoApply增加审批业务id
            userInfo_apply.setApprovalMainId(approvalMainService.
                    selectIdByName(userInfo_apply.getUniversityId(), "审批职员申请修改简历"));
            //设置用户信息申请种类
            userInfo_apply.setInfoType(0);
            //设置用户信息申请旧信息记录id
            userInfo_apply.setOldInfoId(oldId);
            //设置用户信息申请新信息记录id
            userInfo_apply.setNewInfoId(newId);
            //设置用户信息申请开始时间
            userInfo_apply.setStartTime(employeeHistory.getDatetime());
            //设置用户信息创建时间
            userInfo_apply.setDatetime(employeeHistory.getDatetime());
            //设置用户信息申请写入者
            userInfo_apply.setByWho(byWho);
            //设置用户信息申请为有效
            userInfo_apply.setDeleted(false);
            //插入新的userinfoApply记录
            boolean success = userinfoApplyService.insertUserinfoApply(userInfo_apply);
            if(success){
                //清除相应的缓存
                cache.delete(CacheNameHelper.Receive_CacheNamePrefix + "applyModifydEmployeeHistory");
                cache.delete(CacheNameHelper.ListAll_CacheName);
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }*/
    /**
     * Author: chenenru 20:50 2019/5/9
     * @param requestMessage
     * @return Result
     * @apiNote: 申请修改简历, 点击申请时
     */
    @ApiOperation(value="申请修改简历", notes="2019年5月11日 14:33:14 已通过测试")
    @ApiImplicitParam(name = "requestMessage", value = "请求参数实体", required = true, dataType = "RequestMessage")
    @PostMapping("applyModifyEmployeeHistory/")
    @ResponseBody
    public Result ApplyModifyEmployeeHistory(@RequestBody RequestMessage requestMessage){
        //判断前端传过来的值是否为空
        if(requestMessage.getEmployeeHistory()!=null && requestMessage.getByWho()!=null && requestMessage.getUserinfoApply()!=null){
            boolean success = employeeHistoryService.clickApplyEmployeeHistory(requestMessage);
            if(success){
                //清除相应的缓存
                cache.delete(CacheNameHelper.Receive_CacheNamePrefix + "applyModifydEmployeeHistory");
                cache.delete(CacheNameHelper.ListAll_CacheName);
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }

    /**
     * Author: chenenru 20:50 2019/5/9
     * @param userinfoApplyApproval, user_id
     * @return Result
     * @apiNote: 审批修改简历的申请, 点击通过时
     *//*
    @ApiOperation(value="审批修改简历的申请, 点击通过时", notes="未测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userinfoApplyApproval", value = "用户申请审批流程表实体", required = true, dataType = "UserinfoApplyApproval"),
            @ApiImplicitParam(name = "user_id", value = "审批人id", required = true, dataType = "Long", paramType = "path")
    })
    @PostMapping("commituserinfoApply/{user_id}")
    @ResponseBody
    public Result commitApplyModifyEmployeeHistory(@RequestBody UserinfoApplyApproval userinfoApplyApproval,@PathVariable Long user_id){
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
                    cache.delete(CacheNameHelper.Receive_CacheNamePrefix + "applyModifydEmployeeHistory");
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
                    cache.delete(CacheNameHelper.Receive_CacheNamePrefix + "applyModifydEmployeeHistory");
                    cache.delete(CacheNameHelper.ListAll_CacheName);
                    return Result.build(ResultType.Success);
                }else{
                    return Result.build(ResultType.Failed);
                }
            }
        }
        return Result.build(ResultType.ParamError);
    }

    *//**
     * Author: chenenru 20:50 2019/5/9
     * @param userinfoApplyApproval,user_id
     * @return Result
     * @apiNote: 审批修改简历的申请, 点击不通过时
     *//*
    @ApiOperation(value="审批修改简历的申请, 点击不通过时", notes="未测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userinfoApplyApproval", value = "用户申请审批流程表实体", required = true, dataType = "UserinfoApplyApproval"),
            @ApiImplicitParam(name = "user_id", value = "审批人id", required = true, dataType = "Long", paramType = "path")
    })
    @PostMapping(value = "refuseuserinfoApply/{user_id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Result refuseApplyModifyEmployeeHistory(@RequestBody UserinfoApplyApproval userinfoApplyApproval,@PathVariable Long user_id) throws IOException {
        System.out.println("小莫是头猪！周浩也是！！---");
        if(userinfoApplyApproval != null && user_id != null){
            boolean success = userService.endForRefuse(userinfoApplyApproval, user_id);
            if(success) {
                //清除相应的缓存
                cache.delete(CacheNameHelper.Receive_CacheNamePrefix + "applyModifydEmployeeHistory");
                cache.delete(CacheNameHelper.ListAll_CacheName);
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }*/

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
