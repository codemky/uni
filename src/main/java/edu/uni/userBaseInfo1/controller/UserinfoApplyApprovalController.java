package edu.uni.userBaseInfo1.controller;

import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.userBaseInfo1.bean.*;
import edu.uni.userBaseInfo1.bean.Class;
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
import java.util.List;

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
    private UserinfoApplyApprovalService userinfoApplyApprovalService;
    @Autowired
    private UserinfoApplyService userinfoApplyService;
    @Autowired
    private ClassService classService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    EmployService employService;
    @Autowired  //把Student的Service层接口所有的方法自动装配到该对象中
    private StudentService studentService;
    @Autowired
    private UserService userService;
    @Autowired
    private StudentRelationService studentRelationService;
    @Autowired
    private EcommService ecommService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private PictureService pictureService;
    @Autowired
    private LearningDegreeSerevice learningDegreeSerevice;
    @Autowired
    private EmployeeHistoryService employeeHistoryService;
    @Autowired
    private UserUploadFileService userUploadFileService;

    @Autowired  //把缓存工具类RedisCache相应的方法自动装配到该对象
    private RedisCache cache;

    //内部类，专门用来管理每个get方法所对应缓存的名称。
    static class CacheNameHelper{
        // ub1_e_UserinfoApplyApproval_{用户信息审批流程记录id}
        public static final String Receive_CacheNamePrefix = "ub1_u_userinfoApplyApproval_";
        // ub1_e_UserinfoApplyApprovals_listAll
        public static final String ListAll_CacheName = "ub1_u_userinfoApplyApproval_listAll";
    }


    @ApiOperation( value = "根据审核结果和信息类型和用户角色查询所有的审批记录",notes = "未测试" )
    @PostMapping("/getByRoleName")
    @ApiImplicitParam(name = "userinfoApplyApproval", value = "用户信息审批流程详情实体", required = true, dataType = "UserinfoApplyApproval")
    @ResponseBody
    public void selectByRoleName(@RequestBody UserinfoApplyApproval userinfoApplyApproval,
                                 HttpServletResponse response) throws IOException {

        response.setContentType("application/json;charset=utf-8");

        Long schoolId = (long) 1 ;
        Long user_id = (long) 1720 ;

        List<String > roles = new ArrayList<>();
        roles.add("辅导员");
        roles.add("院长");

        List<UserinfoApplyApproval> userinfoApplyApprovalList =
                userinfoApplyApprovalService.selectAllByApprovalAndRole(userinfoApplyApproval,roles);

        List<UserinfoApplyApproval> show_apply = new ArrayList<>();

        userinfoApplyApprovalList.forEach( item -> {
            //先检验是否是同一个学校
            if( item.getUniversityId().equals(schoolId) ) {
                User user = userService.selectUserById(item.getApplyUserId());
                //当申请人用户类型是学生时
                if( user.getUserType() == 1 ){
                    //当职员的学院和申请人学生的学院一致时才显示
                    if( isDepartmentSame(user.getId(),user_id) )
                        show_apply.add(item);
                }else{
                    //如果是学生的亲属，则需要找到该亲属的孩子的同一个学院职员才能审批
                    if( user.getUserType() == 4 ){
                        StudentRelation studentRelation = studentRelationService.selectUserIdByRelaId(user.getId());
                        if( isDepartmentSame(studentRelation.getUserId(),user_id)  )
                            show_apply.add(item);
                    }
                    else
                        show_apply.add(item);
                }
            }
        } ) ;

        String json = Result.build(ResultType.Success).appendData("apply_list",show_apply).convertIntoJSON();

        response.getWriter().write(json);

    }


    /**
     * Author: mokuanyuan 18:33 2019/5/14
     * @param userinfoApplyApproval
     * @param response
     * @return 旧纪录（如果有的话） 和 新纪录
     * @apiNote: 根据信息类型和新旧记录的id查询出 旧纪录（如果有的话） 和 新纪录
     */
    @ApiOperation( value = "据信息类型和新旧记录的id查询出 旧纪录（如果有的话） 和 新纪录",notes = "未测试" )
    @PostMapping("/getOldInfoAndNewInfoByApply")
    @ApiImplicitParam(name = "userinfoApplyApproval", value = "用户信息审批流程详情实体", required = true, dataType = "UserinfoApplyApproval")
    @ResponseBody
    public void getOldInfoAndNewInfoByApply(@RequestBody UserinfoApplyApproval userinfoApplyApproval ,
                                            HttpServletResponse response ) throws IOException{
        response.setContentType("application/json;charset=utf-8");
        UserinfoApply userinfoApply = userinfoApplyService.selectUserinfoApplyById(userinfoApplyApproval.getUserinfoApplyId());
        Long old_id = userinfoApply.getOldInfoId();
        Long new_id = userinfoApply.getNewInfoId();

        Result result = Result.build(ResultType.Success);

        //根据信息类型调用相应的service层的查询方法
        switch ( userinfoApply.getInfoType() ) {
            case 0: //0为联系方式
                if( old_id != null)
                    result.appendData("old_info",ecommService.selectById(old_id));
                result.appendData("new_info",ecommService.selectById(new_id));
            case 1: //1为地址
                if( old_id != null)
                    result.appendData("old_info",addressService.selectById(old_id));
                result.appendData("new_info",addressService.selectById(new_id));
            case 2: //2为照片
                if( old_id != null)
                    result.appendData("old_info",pictureService.selectById(old_id));
                result.appendData("new_info",pictureService.selectById(new_id));
            case 3: //3为学生亲属
                if( old_id != null)
                    result.appendData("old_info",studentRelationService.selectById(old_id));
                result.appendData("new_info",studentRelationService.selectById(new_id));
            case 4: //4为学历
                if( old_id != null)
                    result.appendData("old_info",learningDegreeSerevice.selectLearningDegreeById(old_id));
                result.appendData("new_info",learningDegreeSerevice.selectLearningDegreeById(new_id));
            case 5: //5为简历
                if( old_id != null)
                    result.appendData("old_info",learningDegreeSerevice.selectLearningDegreeById(old_id));
                result.appendData("new_info",learningDegreeSerevice.selectLearningDegreeById(new_id));
            case 6: //6为学生信息
                if( old_id != null)
                    result.appendData("old_info",studentService.selectById(old_id));
                result.appendData("new_info",studentService.selectById(new_id));
            case 7: //7为教职工信息
                if( old_id != null)
                    result.appendData("old_info",employeeService.selectEmployeeById(old_id));
                result.appendData("new_info",employeeService.selectEmployeeById(new_id));
            case 8: //8为用户个人信息
                if( old_id != null)
                    result.appendData("old_info",userService.selectUserById(old_id));
                result.appendData("new_info",userService.selectUserById(new_id));
            case 9: //9为学生excel表
                if( old_id != null)
                    result.appendData("old_info",userUploadFileService.selectUserUploadFileById(old_id));
                result.appendData("new_info",userUploadFileService.selectUserUploadFileById(new_id));
            case 10:  //10为教职工信息
                if( old_id != null)
                    result.appendData("old_info",userUploadFileService.selectUserUploadFileById(old_id));
                result.appendData("new_info",userUploadFileService.selectUserUploadFileById(new_id));

        }

        String json = result.convertIntoJSON();

        response.getWriter().write(json);


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
    @ApiImplicitParam(name = "id", value = "userinfoApplyApproval表的一个id", required = false, dataType = "Long" , paramType = "path")
    @ResponseBody
    public void receive(@PathVariable Long id, HttpServletResponse response) throws IOException {
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
    @GetMapping("userinfoApplyApprovals/listAll")
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
    @ApiImplicitParam(name = "id", value = "用户信息审批流程的id", required = true, dataType = "Long", paramType = "path")
    @DeleteMapping("/userinfoApplyApproval/{id}")   //delete请求
    @ResponseBody
    public Result destroy(@PathVariable Long id){
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
     * Author: mokuanyuan 19:08 2019/5/13
     * @param studentId
     * @param userId
     * @return boolean
     * @apiNote: 根据申请人的用户id(第一个参数)和登录到审批中心人的用户id(第二个参数)判断是否处于同一个学院
     */
    public boolean isDepartmentSame(long studentId,long userId){
        //审批人
        System.out.println("-->"+employeeService.selectByUserId(userId));
        Employee employee = employeeService.selectByUserId(userId).get(0);
        Employ employ = employService.selectEmployByEmployeeId(employee.getId(),employee.getUniversityId());
        System.out.println(employ.getDepartmentId());
        //申请人为小学生才行
        Student student = studentService.selectByUserId(studentId).get(0);
        Class aClass = classService.selectClassByClassId( student.getClassId() );
        System.out.println(aClass.getDepartmentId()+"--->");
        if( employ.getDepartmentId().equals(aClass.getDepartmentId()) ) {
            System.out.println("是同一个学院");
            return true;
        }else{
            System.out.println("不是同一个学院");
            return false;
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
