/**
 * Author: mokuanyuan 10:06 2019/4/25
 * @apiNote: 关于电子通信信息模块的Controller层（Http URL请求）的具体实现方法
 */

package edu.uni.userBaseInfo1.controller;

import edu.uni.auth.service.AuthService;
import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.config.GlobalConfig;
import edu.uni.userBaseInfo1.bean.*;
import edu.uni.userBaseInfo1.service.*;
import edu.uni.utils.RedisCache;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

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
    private OtherRoleService otherRoleService;
    @Autowired
    private UserService userService;
    @Autowired
    private ApprovalStepInchargeService approvalStepInchargeService;
    @Autowired
    private AuthService authService;
    @Autowired
    private OtherEmployPositionService otherEmployPositionService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private StudentService studentService;

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
     * Author: mokuanyuan 21:26 2019/6/18
     * @param userId
     * @apiNote: 根据用户id获取其相应的电子通信方式，如果userId为-1时表示查询登录者的信息
     */
    @ApiOperation(value="根据用户id获取其相应的电子通信方式，如果userId为-1时表示查询登录者的信息", notes="未测试")
    @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "Path")
    @GetMapping("getEcommInformation/{userId}")
    @ResponseBody
    public Result getEcommInformation(@PathVariable Long userId){
        if(userId == null)
            return Result.build(ResultType.ParamError,"获取的用户id为空");

        Long tempUserId = userId;
        edu.uni.auth.bean.User loginUser = authService.getUser();
        if (loginUser == null)
            return Result.build(ResultType.Failed, "你沒有登錄");
        if (userId == -1)   // -1 代表的是查询自己的信息
            userId = loginUser.getId();

        // 1.判断被查询者的用户类型 是学生还是职员还是学生亲属  2.判断被查询者的角色是不是领导角色  3.判断被查询者和查询者是不是从属于同一个单位
        // ①如果被查询者类型为学生时，判断是否是自己的班主任或者是学院领导即可
        // ②如果被查询者类型为教职工时，判断查询者是否是和被查询者时同一个学校并且是该学校的人事处工作人员
        // ③如果被查询者类型为学生亲属时，判断是不是自己的孩子，或者判断是否是自己孩子的学校的班主任或者学院领导
        User user = userService.selectUserById(userId);
//        List<Integer> roles = otherEmployPositionService.selectEmployeeRoleByUserId();
        if( tempUserId != -1 )
            switch (user.getUserType()){
                case 1:
                    if( !studentService.whetherSeeStudent(tempUserId,loginUser.getId()))
                        return Result.build(ResultType.Disallow,"登录用户无权查看该学生用户的信息");
                    break;
                case 2:
                    if( !studentService.whetherSeeEmployee(tempUserId,loginUser.getId()))
                        return Result.build(ResultType.Disallow,"登录用户无权查看该职员用户的信息");
                    break;
                case 3:
                    if( !studentService.whetherSeeRelation(tempUserId,loginUser.getId()))
                        return Result.build(ResultType.Disallow,"登录用户无权查看该学生亲属用户的信息");
                    break;
            }


        List<Ecomm> ecomms = ecommService.selectValidEcomByUserId(userId);
        if(ecomms.size() == 0)
            return Result.build(ResultType.Failed,"该用户的电信通信方式信息“为空");
        List<Ecomm> ecommList = ecommService.filterEcomm(ecomms);

//        addressService.selectAllInfoToList(addressList,addresses);
        return Result.build(ResultType.Success).appendData("ecomm",ecommList);

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
            String path = "E://" ;
            //进行路径转义
//            path=path.replace(":", "%3A").replace("/", "%2F");
            System.out.println(path.replace(":", "%3A").replace("/", "%2F"));
            //文件名（都用UUID命名吧）
            String fileName = UUID.randomUUID() + suffix;
            //传入路径和文件名这两个参数
            file.transferTo(new File(path, fileName));
            response.getWriter().write(Result.build(ResultType.Success).
                    appendData("path", "E://" + fileName).
                    appendData("fileName", fileName).
                    convertIntoJSON());
            }

    }

    /**
     * Author: laizhouhao 20:40 2019/6/9
     * @param user_id
     * @return 用户通信方式
     * @apiNote: 根据用户id获取用户的通信方式
     */
    @ApiOperation( value = "根据用户id获取用户的通信方式",notes = "2019年6月9日 21:05:01 已通过测试" )
    @GetMapping("/getUserEcomm/{user_id}")
    @ApiImplicitParam(name = "user_id", value = "用户user_id", required = false, dataType = "Long" , paramType = "path")
    @ResponseBody
    public void receiveUserPictureAddr(@PathVariable Long user_id, HttpServletResponse response) throws IOException {
        //检验页面传来的id是否存在
        if(user_id != null){
            //根据用户id查找该用户有多少个通信方式
            List<Ecomm> ecommList = ecommService.selectValidEcomByUserId(user_id);
            //用户的通信方式详情
            HashMap<String, Object> map = new LinkedHashMap<>();
            ecommService.getUserEcomm(map, ecommList);
            //设置返回的数据格式
            response.setContentType("application/json;charset=utf-8");
            //拼接缓存键名（字符串）
            String cacheName = UserController.CacheNameHelper.Receive_CacheNamePrefix +"-------"+ user_id;
            //尝试在缓存中通过键名获取相应的键值
            //因为在Redis中，数据是以”“” "键-值"对 的形式储存的
            String json = cache.get(cacheName);
            //如果在缓存中找不到，那就从数据库里找
            if(json == null){
                json = Result.build(ResultType.Success)
                        .appendData("userEcomm",map).convertIntoJSON();
                cache.set(cacheName,json);
            }
            //到最后通过response对象返回json格式字符串的数据
            response.getWriter().write(json);
        }
    }


}
