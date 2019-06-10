package edu.uni.userBaseInfo1.controller;

import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.userBaseInfo1.bean.*;
import edu.uni.userBaseInfo1.service.*;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author chenenru
 * @ClassName LearningDegreeController
 * @Description
 * @Date 2019/4/30 15:50
 * @Version 1.0
 **/
//填写description内容可以在测试模块显示相应的文字和模块
@Api(description = "学历信息模块")
//Controller类（或者说Http）的请求路径
//如果添加了路径，则在需要调用该类的方法时需要在方法请求mapping路径前加上类的mapping路径
@RequestMapping("json/learningDegree")
//标志这个类是一个controller类，用于被Spring扫描然后配置添加和配置相应的bean
@Controller
public class LearningDegreeController {

    //把LearningDegree的Service层接口所有的方法自动装配到该对象中
    @Autowired
    LearningDegreeSerevice learningDegreeService;
    @Autowired
    MyAcademicDegreeService myAcademicDegreeService;
    @Autowired
    MyAcademicService myAcademicService;
    @Autowired
    private ApprovalMainService approvalMainService;
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
        // ub1_e_LearningDegree_{学历记录id}
        public static final String Receive_CacheNamePrefix = "ub1_l_learningDegree_";
        // ub1_e_LearningDegrees_listAll
        public static final String ListAll_CacheName = "ub1_l_learningDegree_listAll";
    }

    /**
     * Author: chenenru 23:41 2019/4/29
     * @param id response
     * @return response
     * @apiNote: 获取学历详情
     */
    //以下说明为本类中所有方法的注解的解释，仅在本处注释（因为都几乎是一个模版）
    //@ApiOperation：用于在swagger2页面显示方法的提示信息
    //@GetMapping：规定方法的请求路径和方法的请求方式（Get方法）
    //@ApiImplicitParam：用于在swagger2页面测试时用于测试的变量，详细解释可以看Swagger2注解说明
    //@ResponseBody：指明该方法效果等同于通过response对象输出指定格式的数据（JSON）
    @ApiOperation( value = "以一个id获取一条学历记录详情",notes = "2019-5-2 11:07:06已通过测试" )
    @GetMapping("learningDegree/{id}")
    @ApiImplicitParam(name = "id", value = "learningDegree表的一个id", required = false, dataType = "Long" , paramType = "path")
    @ResponseBody
    public void receive(@PathVariable Long id, HttpServletResponse response) throws IOException {
        //设置返回的数据格式
        response.setContentType("application/json;charset=utf-8");
        //拼接缓存键名（字符串）
        String cacheName = LearningDegreeController.CacheNameHelper.Receive_CacheNamePrefix + id;
        //尝试在缓存中通过键名获取相应的键值
        //因为在Redis中，数据是以”“” "键-值"对 的形式储存的
        String json = cache.get(cacheName);
        //如果在缓存中找不到，那就从数据库里找
        if(json == null){
            LearningDegree learningDegree = learningDegreeService.selectLearningDegreeById(id);
            //把查询到的结果用Result工具类转换成json格式的字符串
            json = Result.build(ResultType.Success).appendData("learningDegree",learningDegree).convertIntoJSON();
            //如果有查询到数据，就把在数据库查到的数据放到缓存中
            if(learningDegree != null){
                cache.set(cacheName,json);
            }
        }
        //到最后通过response对象返回json格式字符串的数据
        response.getWriter().write(json);
    }

    /**
     * Author: laizhouhao 8:34 2019/5/6
     * @param user_id
     * @return response
     * @apiNote: 以user_id获取学历详情
     */
    @ApiOperation( value = "根据用户的id查询对应的学历信息",notes = "未测试" )
    @GetMapping("selectLearningDegreeByUserId/{user_id}")
    @ResponseBody
    public void selectByUserId(@PathVariable Long user_id,HttpServletResponse response) throws IOException{
        response.setContentType("application/json;charset=utf-8");
        String cacheName = LearningDegreeController.CacheNameHelper.ListAll_CacheName;
        String json = cache.get(cacheName);
        if(json == null){
            json = Result.build(ResultType.Success)
                    .appendData("learningDegree",learningDegreeService.selectByUserId(user_id)).convertIntoJSON();
            cache.set(cacheName,json);
        }
        response.getWriter().write(json);
    }

    /**
     * Author: chenenru 23:44 2019/4/29
     * @param response
     * @return
     * @apiNote: 获取所有学历记录的内容
     */
    @ApiOperation( value = "获取所有学历记录的内容",notes = "2019-5-2 11:07:10已通过测试" )
    @GetMapping("learningDegrees/listAll")
    @ResponseBody
    public void selectAll(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = LearningDegreeController.CacheNameHelper.ListAll_CacheName;
        String json = cache.get(cacheName);
        if(json == null){
            json = Result.build(ResultType.Success)
                    .appendData("learningDegrees",learningDegreeService.selectAllLearningDegrees()).convertIntoJSON();
            cache.set(cacheName,json);
        }
        response.getWriter().write(json);
    }
    /**
     * Author: chenenru 23:47 2019/4/29
     * @param  learningDegree
     * @return Result
     * @apiNote: 新增学历信息
     */
    @ApiOperation(value="新增学历信息", notes="2019-5-2 11:07:14已通过测试")
    @ApiImplicitParam(name = "LearningDegree", value = "学历详情实体", required = true, dataType = "LearningDegree")
    @PostMapping("/learningDegree")  //post请求方式
    @ResponseBody
    public Result create(@RequestBody(required = false) LearningDegree learningDegree){
        //检验页面传来的对象是否存在
        if(learningDegree != null){
            boolean success = learningDegreeService.insertLearningDegree(learningDegree);
            if(success){
                // 清空相关缓存
                cache.delete(LearningDegreeController.CacheNameHelper.ListAll_CacheName);
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
     * @apiNote: 删除学历
     */
    @ApiOperation(value="删除学历", notes="2019-5-2 11:07:19已通过测试")
    @ApiImplicitParam(name = "id", value = "学历的id", required = true, dataType = "Long", paramType = "path")
    @DeleteMapping("/learningDegree/{id}")   //delete请求
    @ResponseBody
    public Result destroy(@PathVariable Long id){
        boolean success = learningDegreeService.deleteLearningDegree(id);
        if(success){
            // 清空相关缓存
            cache.delete(LearningDegreeController.CacheNameHelper.ListAll_CacheName);
            return Result.build(ResultType.Success);
        }else{
            return Result.build(ResultType.Failed);
        }
    }
    /**
     * Author: chenenru 23:52 2019/4/29
     * @param learningDegree
     * @return Result
     * @apiNote: 更新学历详情
     */
    @ApiOperation(value="更新学历详情", notes="2019-5-2 11:07:24已通过测试")
    @ApiImplicitParam(name = "learningDegree", value = "学历详情实体", required = true, dataType = "LearningDegree")
    @PutMapping("/learningDegree")   //Put请求
    @ResponseBody
    public Result update(@RequestBody(required = false) LearningDegree learningDegree){
        if(learningDegree != null && learningDegree.getId() != null){
            boolean success = learningDegreeService.updateLearningDegree(learningDegree);
            if(success){
                //清除相应的缓存
                cache.delete(LearningDegreeController.CacheNameHelper.Receive_CacheNamePrefix + learningDegree.getId());
                cache.delete(LearningDegreeController.CacheNameHelper.ListAll_CacheName);
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }

    /**
     * Author: chenenru 2019-5-11 08:48:28
     * @param userId
     * @param response
     * @throws IOException
     */
    /*@ApiOperation(value = "根据职员的id查询有效的学历记录，同时查询所有的受教育程度和学位表",notes="正在测试")
    @GetMapping("info/learningDegree/employee/{userId}")   //GET请求
    @ResponseBody
    private void AddToUserApplay(@PathVariable Long userId,HttpServletResponse response) throws IOException {
        System.out.println("-----"+userId);
        if(userId != null){
            UserInfo userInfo = new UserInfo();

            //获取职员的有效的学历
            List<LearningDegree> learningDegrees = learningDegreeService.selectByUserId(userId);

            //查询受教育表的所有记录
            List<Academic> academics = myAcademicService.selectAll();

            //查询学位表
            List<AcademicDegree> academicDegrees = myAcademicDegreeService.selectAll();

            //放入到工具类里面
            userInfo.setLearningDegrees(learningDegrees);
            userInfo.setAcademics(academics);
            userInfo.setAcademicDegrees(academicDegrees);

            System.out.println("^-^ --->:"+userInfo.toString());

            response.setContentType("application/json;charset=utf-8");
            String cacheName = LearningDegreeController.CacheNameHelper.ListAll_CacheName+"learningDegree"+userId;
            String json = cache.get(cacheName);
            if(json == null){
                json = Result.build(ResultType.Success)
                        .appendData("userInfo",userInfo).convertIntoJSON();
                cache.set(cacheName,json);
            }
            response.getWriter().write(json);
        }
    }*/

    /**
     * Author: laizhouhao 20:50 2019/5/9
     * @param requestMessage
     * @return Result
     * @apiNote: 申请修改学历, 点击申请时
     */
    @ApiOperation(value="申请修改学历", notes="2019年5月11日 14:33:14 已通过测试")
    @ApiImplicitParam(name = "requestMessage", value = "请求参数实体", required = true, dataType = "RequestMessage")
    @PostMapping("applyModifyLearningDegree/")
    @ResponseBody
    public Result ApplyModifyLearningDegree(@RequestBody RequestMessage requestMessage){
        //判断前端传过来的值是否为空
        if(requestMessage.getLearningDegree()!=null && requestMessage.getByWho()!=null && requestMessage.getUserinfoApply()!=null){
            boolean success = learningDegreeService.clickApplyLearningDegree(requestMessage);
            if(success){
                //清除相应的缓存
                cache.delete(CacheNameHelper.Receive_CacheNamePrefix + "applyModifydLearningDegree");
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
     * @apiNote: 审批修改学历的申请, 点击通过时
     *//*
    @ApiOperation(value="审批修改学历的申请, 点击通过时", notes="未测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userinfoApplyApproval", value = "用户申请审批流程表实体", required = true, dataType = "UserinfoApplyApproval"),
            @ApiImplicitParam(name = "user_id", value = "审批人id", required = true, dataType = "Long", paramType = "path")
    })
    @PostMapping("commituserinfoApply/{user_id}")
    @ResponseBody
    public Result commitApplyModifyLearningDegree(@RequestBody UserinfoApplyApproval userinfoApplyApproval,@PathVariable Long user_id){
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
                    cache.delete(CacheNameHelper.Receive_CacheNamePrefix + "applyModifydLearningDegree");
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
                    cache.delete(CacheNameHelper.Receive_CacheNamePrefix + "applyModifydLearningDegree");
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
     * Author: laizhouhao 20:50 2019/5/9
     * @param userinfoApplyApproval,user_id
     * @return Result
     * @apiNote: 审批修改学历的申请, 点击不通过时
     *//*
    @ApiOperation(value="审批修改学历的申请, 点击不通过时", notes="未测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userinfoApplyApproval", value = "用户申请审批流程表实体", required = true, dataType = "UserinfoApplyApproval"),
            @ApiImplicitParam(name = "user_id", value = "审批人id", required = true, dataType = "Long", paramType = "path")
    })
    @PostMapping(value = "refuseuserinfoApply/{user_id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Result refuseApplyModifyLearningDegree(@RequestBody UserinfoApplyApproval userinfoApplyApproval,@PathVariable Long user_id) throws IOException {
        System.out.println("小莫是头猪！！！---");
        if(userinfoApplyApproval != null && user_id != null){
            boolean success = userService.endForRefuse(userinfoApplyApproval, user_id);
            if(success) {
                //清除相应的缓存
                cache.delete(CacheNameHelper.Receive_CacheNamePrefix + "applyModifydLearningDegree");
                cache.delete(CacheNameHelper.ListAll_CacheName);
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }*/

    /**
     * Author: laizhouhao 16:39 2019/6/10
     * @param user_id
     * @return 用户的学历信息详情
     * @apiNote: 根据用户id获取用户的所有学历信息详情
     */
    @ApiOperation( value = "根据用户id获取用户的所有学历信息详情",notes = "2019年6月10日 18:32:10 已通过测试" )
    @GetMapping("/getUserDegree/{user_id}")
    @ApiImplicitParam(name = "user_id", value = "用户user_id", required = false, dataType = "Long" , paramType = "path")
    @ResponseBody
    public void receiveUserPictureAddr(@PathVariable Long user_id, HttpServletResponse response) throws IOException {
        //检验页面传来的id是否存在
        if(user_id != null){
            //获取该用户的所有学历信息
            List<LearningDegree> learningDegreeList = new ArrayList<>();
            learningDegreeList = learningDegreeService.selectByUserId(user_id);
            //获取该用户所有学历的详情信息
            HashMap<String,Object> map = new HashMap<>();
            learningDegreeService.getLearningDegree(map, learningDegreeList);
            //设置返回的数据格式
            response.setContentType("application/json;charset=utf-8");
            //拼接缓存键名（字符串）
            String cacheName = UserController.CacheNameHelper.Receive_CacheNamePrefix +"learningDegrees"+ user_id;
            //尝试在缓存中通过键名获取相应的键值
            //因为在Redis中，数据是以”“” "键-值"对 的形式储存的
            String json = cache.get(cacheName);
            //如果在缓存中找不到，那就从数据库里找
            if(json == null){
                json = Result.build(ResultType.Success)
                        .appendData("userLearningDegree",map).convertIntoJSON();
                cache.set(cacheName,json);
            }
            //到最后通过response对象返回json格式字符串的数据
            response.getWriter().write(json);
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
