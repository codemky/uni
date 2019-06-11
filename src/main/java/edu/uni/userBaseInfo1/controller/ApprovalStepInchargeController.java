package edu.uni.userBaseInfo1.controller;

import edu.uni.auth.bean.Role;
import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.userBaseInfo1.bean.ApprovalMain;
import edu.uni.userBaseInfo1.bean.ApprovalStepIncharge;
import edu.uni.userBaseInfo1.service.ApprovalMainService;
import edu.uni.userBaseInfo1.service.ApprovalStepInchargeService;
import edu.uni.userBaseInfo1.service.OtherRoleService;
import edu.uni.utils.RedisCache;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @Author laizhouhao
 * @Description 关于某审批某步骤详情信息模块的Controller层（Http URL请求）的具体实现方法
 * @Date 16:15 2019/4/29
 **/
//填写description内容可以在测试模块显示相应的文字和模块
@Api(description = "某审批某步骤详情信息模块")
//Controller类（或者说Http）的请求路径
//如果添加了路径，则在需要调用该类的方法时需要在方法请求mapping路径前加上类的mapping路径
@RequestMapping("json/approvalStepIncharge")
//标志这个类是一个controller类，用于被Spring扫描然后配置添加和配置相应的bean
@Controller
public class ApprovalStepInchargeController {
    //把ApprovalStepIncharge的Service接口层的所有方法自动装配到该对象中
    @Autowired
    private ApprovalStepInchargeService approvalStepInchargeService;
    @Autowired
    private ApprovalMainService approvalMainService;
    @Autowired
    private OtherRoleService otherRoleService;

    //把缓存工具类RedisCache相应的方法自动装配到该对象
    @Autowired
    private RedisCache cache;

    //内部类，专门用来管理每个get方法所对应缓存的名称。
    static class CacheNameHelper{
        // ub1_a_approvalStepIncharge_{某审批某步骤详情记录id}
        public static final String Receive_CacheNamePrefix = "ub1_a_approvalStepIncharge_";
        // ub1_a_approvalStepIncharge_listAll
        public static final String ListAll_CacheName = "ub1_a_approvalStepIncharge_listAll";
    }


    /**
     * Author: mokuanyuan 15:37 2019/6/4
     * @param mainId
     * @param response
     * @apiNote: 以一个审批规定id获取所有关于该审批规定的步骤详情
     */
    @ApiOperation( value = "以一个审批规定id获取所有关于该审批规定的步骤详情",notes = "未测试" )
    @GetMapping("approvalStepIncharge/list/{mainId}")
    @ApiImplicitParam(name = "mainId", value = "ApprovalMain表的id", required = true, dataType = "Long" , paramType = "path")
    @ResponseBody
    public void selectByMainId(@PathVariable Long mainId, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");

        List<ApprovalStepIncharge> approvalStepIncharges = approvalStepInchargeService.selectByMainId(mainId);
        List<Role> roles = otherRoleService.selectAll();
        String json = Result.build(ResultType.Success).appendData("roles",roles)
                .appendData("approvalStepIncharges",approvalStepIncharges).convertIntoJSON();
        response.getWriter().write(json);

    }


    /**
     * Author: laizhouhao 16:40 2019/4/29
     * @param approvalStepIncharge
     * @return 为某种审批新增一个步骤详情
     */
    @ApiOperation(value="新增某审批某步骤详情信息记录", notes="未测试")
    @ApiImplicitParam(name = "approvalStepIncharge", value = "某审批某步骤详情详情实体", required = true, dataType = "ApprovalStepIncharge")
    @PostMapping("/approvalStepIncharge")  //post请求方式
    @ResponseBody
    public Result create(@RequestBody(required = false)ApprovalStepIncharge approvalStepIncharge){

        if(approvalStepIncharge != null && approvalStepIncharge.getApprovalMainId() != null ){
            ApprovalMain approvalMain = approvalMainService.selectById(approvalStepIncharge.getApprovalMainId());
            int step = approvalMain.getStepCnt();
            approvalStepIncharge.setStep(step+1);
            approvalMain.setStepCnt(step+1);
            boolean main_success = approvalMainService.updateForStepIncharge(approvalMain);

            approvalStepIncharge.setByWho((long) 1);
            approvalStepIncharge.setDatetime(new Date());
            approvalStepIncharge.setDeleted(false);
            approvalStepIncharge.setUniversityId((long) 1);
            boolean success = approvalStepInchargeService.insert(approvalStepIncharge);

            if(success || main_success){
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
     * @return 删除某审批某步骤详情信息结果
     */
    @ApiOperation(value="逻辑删除删除某审批某步骤详情信息", notes="未测试")
    @ApiImplicitParam(name = "id", value = "某审批某步骤详情id", required = true, dataType = "Long", paramType = "path")
    @DeleteMapping("/approvalStepIncharge/{id}")   //delete请求
    @ResponseBody
    public Result destroy(@PathVariable long id){
        boolean success = approvalStepInchargeService.updateToInvalidById(id);
        if(success){
            // 清空相关缓存

            return Result.build(ResultType.Success);
        }else{
            return Result.build(ResultType.Failed);
        }
    }

    /**
     * Author: laizhouhao 11:01 2019/4/30
     * @param approvalStepIncharge
     * @return 更新操作结果
     */
    @ApiOperation(value="更新某审批某步骤详情信息", notes="2019年5月6日 18:08:42 已通过测试")
    @ApiImplicitParam(name = "approvalStepIncharge", value = "某审批某步骤详情信息详情实体", required = true, dataType = "ApprovalStepIncharge")
    @PutMapping("/approvalStepIncharge")   //Put请求
    @ResponseBody
    public Result update(@RequestBody(required = false) ApprovalStepIncharge approvalStepIncharge){
        if(approvalStepIncharge != null && approvalStepIncharge.getId() != null){
            ApprovalStepIncharge new_approvalStepIncharge = approvalStepInchargeService.selectById(approvalStepIncharge.getId());
            new_approvalStepIncharge.setName(approvalStepIncharge.getName());
            new_approvalStepIncharge.setRoleId(approvalStepIncharge.getRoleId());

            boolean success = approvalStepInchargeService.update(new_approvalStepIncharge);
            if(success){
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }




//    /**
//     * Author: laizhouhao 9:55 2019/4/30
//     * @param id
//     * @return response
//     * @apiNote: 获取某审批某步骤详情信息详细
//     */
//    //以下说明为本类中所有方法的注解的解释，仅在本处注释（因为都几乎是一个模版）
//    //@ApiOperation：用于在swagger2页面显示方法的提示信息
//    //@GetMapping：规定方法的请求路径和方法的请求方式（Get方法）
//    //@ApiImplicitParam：用于在swagger2页面测试时用于测试的变量，详细解释可以看Swagger2注解说明
//    //@ResponseBody：指明该方法效果等同于通过response对象输出指定格式的数据（JSON）
//    @ApiOperation( value = "以一个id获取一条某审批某步骤详情记录详情",notes = "2019年5月6日 18:07:20 已通过测试" )
//    @GetMapping("approvalStepIncharge/{id}")
//    @ApiImplicitParam(name = "id", value = "ApprovalStepIncharge表的一个id", required = false, dataType = "Long" , paramType = "path")
//    @ResponseBody
//    public void receive(@PathVariable Long id, HttpServletResponse response) throws IOException {
//        //设置返回的数据格式
//        response.setContentType("application/json;charset=utf-8");
//        //拼接缓存键名（字符串）
//        String cacheName = ApprovalStepInchargeController.CacheNameHelper.Receive_CacheNamePrefix + id;
//        //尝试在缓存中通过键名获取相应的键值
//        //因为在Redis中，数据是以”“” "键-值"对 的形式储存的
//        String json = cache.get(cacheName);
//        //如果在缓存中找不到，那就从数据库里找
//        if(json == null){
//            ApprovalStepIncharge approvalStepIncharge = approvalStepInchargeService.selectById(id);
//            //把查询到的结果用Result工具类转换成json格式的字符串
//            json = Result.build(ResultType.Success).appendData("approvalStepIncharge",approvalStepIncharge).convertIntoJSON();
//            //如果有查询到数据，就把在数据库查到的数据放到缓存中
//            if(approvalStepIncharge != null){
//                cache.set(cacheName,json);
//            }
//        }
//        //到最后通过response对象返回json格式字符串的数据
//        response.getWriter().write(json);
//
//    }


//    /**
//     * Author: laizhouhao 16:26 2019/4/29
//     * @apiNote: 查询某审批某步骤详情的所有记录
//     */
//    @ApiOperation( value = "获取所有某审批某步骤详情记录的内容",notes = "2019年5月6日 18:07:47 已通过测试" )
//    @GetMapping("approvalStepIncharges/listAll")
//    @ResponseBody
//    public void selectAll(HttpServletResponse response)throws Exception{
//        response.setContentType("application/json;charset=utf-8");
//        String cacheName = CacheNameHelper.ListAll_CacheName;
//        String json = cache.get(cacheName);
//        if(json==null){
//            json = Result.build(ResultType.Success).appendData("approvalStepIncharges", approvalStepInchargeService.selectAll()).convertIntoJSON();
//            cache.set(json, cacheName);
//        }
//        response.getWriter().write(json);
//    }



}
