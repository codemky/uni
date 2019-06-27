package edu.uni.userBaseInfo1.controller;

import edu.uni.administrativestructure.service.DepartmentService;
import edu.uni.administrativestructure.service.PositionService;
import edu.uni.administrativestructure.service.SubdepartmentService;
import edu.uni.auth.mapper.RoleMapper;
import edu.uni.auth.service.AuthService;
import edu.uni.auth.service.RoleService;
import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.place.service.FieldService;
import edu.uni.professionalcourses.service.SpecialtyService;
import edu.uni.userBaseInfo1.alibaba.easyexcel.test.StudentUpload;
import edu.uni.userBaseInfo1.bean.*;
import edu.uni.userBaseInfo1.config.userBaseInfo1Config;
import edu.uni.userBaseInfo1.mapper.UserinfoApplyApprovalMapper;
import edu.uni.userBaseInfo1.service.*;
import edu.uni.userBaseInfo1.utils.StaticInformation;
import edu.uni.userBaseInfo1.utils.UserInfoFileUtil;
import edu.uni.userBaseInfo1.utils.userinfoTransMapBean;
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

import javax.lang.model.element.TypeElement;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author chenenru
 * @ClassName UserinfoApplyController
 * @Description
 * @Date 2019/4/30 15:52
 * @Version 1.0
 **/
//填写description内容可以在测试模块显示相应的文字和模块
@Api(description = "用户信息申请信息模块")
//Controller类（或者说Http）的请求路径
//如果添加了路径，则在需要调用该类的方法时需要在方法请求mapping路径前加上类的mapping路径
@RequestMapping("json/userinfoApply")
//标志这个类是一个controller类，用于被Spring扫描然后配置添加和配置相应的bean
@Controller
public class UserinfoApplyController {

    @Autowired
    private userBaseInfo1Config userBaseInfo1Config;
    @Autowired
    private UserinfoApplyApprovalMapper userinfoApplyApprovalMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    UserinfoApplyService userinfoApplyService;
    @Autowired
    UserService userService;
    @Autowired
    UserinfoApplyApprovalController userinfoApplyApprovalController;
    @Autowired
    AuthService authService;
    @Autowired
    UserinfoApplyApprovalService userinfoApplyApprovalService;
    @Autowired
    private ApprovalStepInchargeService approvalStepInchargeService;
    @Autowired
    private OtherClassService otherClassService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    OtherEmployService otherEmployService;
    @Autowired  //把Student的Service层接口所有的方法自动装配到该对象中
    private StudentService studentService;
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
    @Autowired
    private AddrCountryService addrCountryService;
    @Autowired
    private AddrStateService addrStateService;
    @Autowired
    private AddrCityService addrCityService;
    @Autowired
    private AddrAreaService addrAreaService;
    @Autowired
    private AddrStreetService addrStreetService;
    @Autowired
    private OtherUniversityService otherUniversityService;
    @Autowired
    private MyAcademicService myAcademicService;
    @Autowired
    private MyAcademicDegreeService myAcademicDegreeService;
    @Autowired
    private ApprovalMainService  approvalMainService;
    @Autowired
    private SpecialtyService specialtyService;
    @Autowired
    private FieldService fieldService;
    @Autowired
    private PoliticalAffiliationService politicalAffiliationService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private SubdepartmentService subdepartmentService;
    @Autowired
    private MySecondLevelDisciplineService mySecondLevelDisciplineService;
    @Autowired
    private OtherEmployPositionService otherEmployPositionService;
    @Autowired
    private PositionService positionService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private OtherRoleService otherRoleService;
    @Autowired
    private ExcelController excelController;
    @Autowired
    private StudentUpload studentUpload;


    @Autowired  //把缓存工具类RedisCache相应的方法自动装配到该对象
    private RedisCache cache;

    //内部类，专门用来管理每个get方法所对应缓存的名称。
    static class CacheNameHelper{
        // ub1_e_UserinfoApply_{用户信息申请记录id}
        public static final String Receive_CacheNamePrefix = "ub1_u_userinfoApply_";
        // ub1_e_UserinfoApplys_listAll
        public static final String ListAll_CacheName = "ub1_u_userinfoApply_listAll";
    }

    @ApiOperation(value="在任何申请页面点击确认申请时，带文件上传", notes="2019年5月11日 14:33:14 已通过测试")
    @ApiImplicitParam( name = "map"  )
    @PostMapping("/applyModifyWithFile")
    @ResponseBody
    public Result applyModifyWithFile (@RequestParam("file") MultipartFile file, @RequestParam("reason") String reason,
                                       @RequestParam(name = "flag",required = false) String flag, @RequestParam("type") String type,
                                       @RequestParam(name = "fileInfo",required = false) String fileInfo,@RequestParam("id") String id ,
                                       HttpServletRequest request) throws ParseException {
        edu.uni.auth.bean.User loginUser = authService.getUser();
        if(loginUser == null){
            return Result.build(ResultType.Failed, "你沒有登錄");
        }

        if (file == null || file.isEmpty() || file.getOriginalFilename() == null )
            return Result.build(ResultType.ParamError,"文件为空");
        if( reason == null )
            return Result.build(ResultType.ParamError,"申请理由为空");
        //1.判断传过来的申请是什么类型的  2.根据不同的类型判断不同的参数  3.把相应的数据信息放进map中调用本身接口
        if( !type.equals("2") && !type.equals("9") && !type.equals("10") && !type.equals("11") && !type.equals("12")  )
            return Result.build(ResultType.ParamError,"申请的信息类型不合法");

        //根据flag（说明是修改还是增加还是批量增加）、登录用户的用户类型、申请的信息类型获取审批业务的名称
        String approvalName = StaticInformation.getApprovalString(loginUser.getUserType(), Integer.valueOf(type));
        System.out.println("生成的审批业务名称为:" +approvalName);

        //查询是否存在这样的审批规定
        Long approvalId = approvalMainService.selectIdByName(loginUser.getUniversityId(), approvalName);
        if( approvalId == -1 || approvalId == -2 )
            return Result.build(ResultType.Disallow,"该申请所对应的规定不存在");

        //判断审批规定的合法性
        if( !(approvalMainService.selectStepCntById(approvalId) > 0) )
            return Result.build(ResultType.Disallow,"审批规定的步骤数异常");
        if( approvalStepInchargeService.selectByMainId(approvalId).size() <= 0 )
            return Result.build(ResultType.Disallow,"审批规定对应的步骤记录数异常");
        if( approvalStepInchargeService.selectRoleIdByStepAppovalId(1,approvalId) == null )
            return Result.build(ResultType.Disallow,"找不到第一步审批的角色id");


        //根据信息类型设置不同的文件路径
        UserInfoFileUtil userInfoFileUtil =null;
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        if(type.equals("2")){  //图片类型文件
            if(!flag.equals("0") && !flag.equals("1") )
                return Result.build(ResultType.ParamError,"图片种类参数不合法,既不是0也不是1");
            if( !suffix.equals(".jpg") && !suffix.equals(".jpeg") && !suffix.equals(".png") )
                return Result.build(ResultType.ParamError,"文件不是图片类型");
            userInfoFileUtil = new UserInfoFileUtil(userBaseInfo1Config.getAbsoluteImageDir());
        }
        else{ //excel类型文件
            if( !suffix.equals(".xlsx") && !suffix.equals(".xls") )
                return Result.build(ResultType.ParamError,"文件不是excel文件类型");
            if( fileInfo == null )
                return Result.build(ResultType.ParamError,"文件的说明信息为空");
            userInfoFileUtil = new UserInfoFileUtil(userBaseInfo1Config.getAbsoluteExcelDir());
        }
        StringBuffer stringBuffer = new StringBuffer();

        //根据信息类型校验权限
        List<Employee> employees = employeeService.selectByUserId(loginUser.getId());
        if(!type.equals("2")){
            if(loginUser.getUserType() != 2)
                return Result.build(ResultType.Disallow,"登录用户不是职员类型用户！");
            if(employees.size() == 0)
                return Result.build(ResultType.Disallow,"登录用户职员信息为空！");

            if(type.equals("9") || type.equals("11")){
                if( !otherRoleService.isPlayOneRole(loginUser.getId(),"辅导员") ||
                        !otherEmployPositionService.whetherPositionByEmployeeId(employees.get(0),"辅导员") )
                    return Result.build(ResultType.Disallow,"登录用户没有辅导员权限！");
            }

            if(type.equals("10") || type.equals("12")){
                if( !otherRoleService.isPlayOneRole(loginUser.getId(),"人事处工作人员") ||
                        !otherEmployPositionService.whetherPositionByEmployeeId(employees.get(0),"辅导员") )
                    return Result.build(ResultType.Disallow,"登录用户没有人事处工作人员权限！");
            }
        }

        try {
            switch (Integer.parseInt(type)){
                case 9: stringBuffer = studentUpload.checkUpdateStudent(file.getInputStream());  break;
                case 10: stringBuffer = studentUpload.checkUpdateEmployee(file);  break;
                case 11: stringBuffer = excelController.checkoutStudentExcel(file.getInputStream(), request);  break;
                case 12: stringBuffer = excelController.checkoutEmployeeExcel(file.getInputStream(), request);  break;
            }

            System.out.println("StringBuffer######:" + stringBuffer.toString());

            if( stringBuffer.toString().contains("报错信息") && !type.equals("2") ){
                userInfoFileUtil.deleteFile(userInfoFileUtil.getUploadRootDir() + userInfoFileUtil.getFilePrefix());
                return Result.build(ResultType.Failed,"校验不通过").appendData("message",stringBuffer);
            }

            //检验权限和校验通过后才上传
            userInfoFileUtil.uploadFile(file, request);

        } catch (IOException e) {
            return Result.build(ResultType.Failed,"上传失败");
        }


        HashMap<String, Object> map = new HashMap<>();
        map.put("type",Integer.parseInt(type));
        map.put("reason",reason);

        switch (Integer.parseInt(type)){
            case 2:
                HashMap<String, Object> imageMap = new HashMap<>();
                imageMap.put("id",Long.parseLong(id));
                imageMap.put("pictureName",userInfoFileUtil.getFilePrefix() + suffix);
                imageMap.put("flag",Integer.parseInt(flag) );
                map.put("applyPicture",imageMap);
                map.put("fileName",userInfoFileUtil.getFilePrefix() + suffix);
                break;
            case 9: case 10: case 11: case 12:
                HashMap<String, Object> excelMap = new HashMap<>();
                excelMap.put("id",Long.parseLong(id));
                excelMap.put("fileInfo",fileInfo);
                excelMap.put("fileName",userInfoFileUtil.getFilePrefix() + suffix);
                map.put("applyExcel",excelMap);
                map.put("fileName",userInfoFileUtil.getFilePrefix() + suffix);
        }

        return applyModifyNoneFile(map);
    }

    /**
     * Author: mokuanyuan 15:19 2019/6/12
     * @param map
     * @return Result
     * @apiNote: 在任何申请页面点击确认申请时
     */
    @ApiOperation(value="在任何申请页面点击确认申请时，不带文件", notes="2019年5月11日 14:33:14 已通过测试")
    @ApiImplicitParam( name = "map"  )
    @PostMapping("/applyModifyNoneFile")
    @ResponseBody
    public Result applyModifyNoneFile(@RequestBody Map<String,Object> map) throws ParseException {

        User user = null;
        edu.uni.auth.bean.User loginUser = authService.getUser();
        if(loginUser == null){
            return Result.build(ResultType.Failed, "你沒有登錄");
        }

        System.out.println(map.toString());
        //获取前台参数
        Integer type = (Integer) map.get("type");
        String reason = (String) map.get("reason");
        Integer modifiedUserId = (Integer) map.get("modifiedUserId");
        if(type == null || reason == null)
            return Result.build(ResultType.ParamError,"申请信息的信息类型或者申请的理由为空！");
        if( !(type >= 0 && type <= 12) )
            return Result.build(ResultType.ParamError,"申请信息的信息类型不合法！");

        //查询是否存在这样的审批规定
        String approvalName = StaticInformation.getApprovalString(loginUser.getUserType(), type);
        System.out.println("生成的审批业务名称为:" +approvalName);
        long approvalId = approvalMainService.selectIdByName(loginUser.getUniversityId(), approvalName);
        if( approvalId == -1 || approvalId == -2 )
            return Result.build(ResultType.Disallow,"该申请所对应的规定出错");

        //判断审批规定的合法性
        if( !(approvalMainService.selectStepCntById(approvalId) > 0) )
            return Result.build(ResultType.Disallow,"审批规定的步骤数异常");
        if( approvalStepInchargeService.selectByMainId(approvalId).size() <= 0 )
            return Result.build(ResultType.Disallow,"审批规定对应的步骤记录数异常");
        if( approvalStepInchargeService.selectRoleIdByStepAppovalId(1,approvalId) == null )
            return Result.build(ResultType.Disallow,"找不到第一步审批的角色id");

        if(modifiedUserId != null) {
            user = userService.selectUserById(modifiedUserId);
            //只能同一个学校单位的用户才能做出操作
            if(!user.getUniversityId().equals(loginUser.getUniversityId()))
                return Result.build(ResultType.Disallow,"被修改者和修改者的所属学校不一致！");
        }

        if(type == 6 || type == 9 || type == 11){
            // 6和代表的是学生信息，无论是修改还是增加，而且9代表的是批量更新学生，11代表批量增加学生
            // 操作学生信息的发起者只能是辅导员,而且学生（被修改者）和辅导员（做出修改者）要在同一个学院
            // 如果不符合以上的条件，那么操作则被服务器拒绝或提示没有权限
            if( type == 6 || type == 9 )
                if( modifiedUserId == null ) //必须提供被修改的学生用户的user_id
                    return Result.build(ResultType.ParamError,"被修改的学生用户的user_id为空！");
            //判断被修改这和修改者是否在同一个学院
            if( type == 6 || type == 9 )
                if( ! userinfoApplyApprovalController.isDepartmentSame(user.getId(),loginUser.getId()))
                    return Result.build(ResultType.Disallow,"修改者和被修改者不是一个学院！");
            //再判断修改者是否有扮演辅导员这个角色
            if( ! otherRoleService.isPlayOneRole(loginUser.getId(),"辅导员") )
                return Result.build(ResultType.Disallow,"修改者没有辅导员权限！");
        }

        if( type == 4 || type == 5 || type == 7 || type == 10 || type == 12 ){
            // 4 和 5 分别代表职员的学历和简历
            // 7和代表的是职员信息，无论是修改还是增加，而且10代表的是批量更新职员，12代表批量增加职员
            // 操作职员信息的发起者只能是人事处工作人员,而且职员（被修改者）和辅导员（做出修改者）要在同一个学校
            // 如果不符合以上的条件，那么操作则被服务器拒绝或提示没有权限
            if( ! otherRoleService.isPlayOneRole(loginUser.getId(),"人事处工作人员"))
                return Result.build(ResultType.Disallow,"该职员没有人事处工作人员权限");
            if( modifiedUserId == null && ( type == 4 || type == 5 || type == 7 )  ) //必须提供被修改的学生用户的user_id
                return Result.build(ResultType.ParamError,"被修改的职员用户的user_id为空！");

        }

        boolean applyResult = userinfoApplyService.clickApply((HashMap<String, Object>) map,type,loginUser,user);
        if( !applyResult && (type == 2 || type == 9 ||  type == 10 ||  type == 11 ||  type == 12) ){
            UserInfoFileUtil userInfoFileUtil = null;
            String fileName = (String)map.get("fileName");
            if( type == 2 )
                userInfoFileUtil = new UserInfoFileUtil(userBaseInfo1Config.getAbsoluteImageDir());
            else
                userInfoFileUtil = new UserInfoFileUtil(userBaseInfo1Config.getAbsoluteExcelDir());
            userInfoFileUtil.deleteFile(userInfoFileUtil.getUploadRootDir() + fileName );
        }



        return applyResult ? Result.build(ResultType.Success,"申请操作成功！")
                : Result.build(ResultType.Failed,"申请操作失败！");

    }


    /**
     * Author: mokuanyuan 14:56 2019/6/12
     * @param map
     * @return Result
     * @apiNote: 审批所有申请, 点击通过或者不通过时
     */
    @ApiOperation(value="审批所有申请, 点击通过或者不通过时", notes="已测试 2019/6/5 21点43分")
    @ApiImplicitParam( name = "map"  )
    @PutMapping("judgeApply")
    @ResponseBody
    public Result commitApplyModifyStudent(@RequestBody Map<String,Object> map) throws IOException {
        edu.uni.auth.bean.User loginUser = authService.getUser();
        if(loginUser == null){
            return Result.build(ResultType.Failed, "你沒有登錄");
        }
        Long userId = loginUser.getId();
        Integer approvalId = (Integer) map.get("approval_id");
        Integer flag = (Integer) map.get("flag");
        String judgeReason = (String) map.get("Reason");
        boolean isFlag = flag == -1 || flag == 0;

        System.out.println(userId + "###" + approvalId + "$$" + flag + "@@" + judgeReason);
//        List<UserinfoApplyApproval> userinfoApplyApprovals = userinfoApplyApprovalService.selectByApplyId((long)approvalId);
        UserinfoApplyApproval userinfoApplyApproval = userinfoApplyApprovalService.selectUserinfoApplyApprovalById((long) approvalId);
        if(userId != null && userinfoApplyApproval != null && isFlag && judgeReason != null ){
            userinfoApplyApproval.setReason(judgeReason);
            if(flag.equals(0)){  // flag为0 时表示通过
                //比较当前步骤是否是最后一步
                if(userService.isLastStep(userinfoApplyApproval.getStep(),userinfoApplyApproval.getUserinfoApplyId())) //该步骤是最后一步
                    return userService.endForPass(userinfoApplyApproval, userId) ?
                            Result.build(ResultType.Success) : Result.build(ResultType.Failed);
                else //该审批不是最后一步
                    return userService.createForPass(userinfoApplyApproval, userId) ?
                            Result.build(ResultType.Success) : Result.build(ResultType.Failed);
            }
            else{
                if(flag.equals(-1)) // flag为-1 时表示不通过
                    return userService.endForRefuse(userinfoApplyApproval, userId) ?
                            Result.build(ResultType.Success) : Result.build(ResultType.Failed);
                else
                    return Result.build(ResultType.ParamError);
            }

        }
        return Result.build(ResultType.ParamError);
    }


    /**
     * Author: mokuanyuan 15:23 2019/6/12
     * @param map
     * @return List<UserinfoApply>
     * @apiNote: 根据信息类型，申请结果和用户id查询该用户的所有申请信息
     */
    @ApiOperation( value = "根据信息类型，申请结果和用户id查询该用户的所有申请信息",notes = "未测试" )
    @PostMapping("userinfoApplys/listAll")
    @ApiImplicitParam(name = "map")
    @ResponseBody
    public Result selectAllByUserId(@RequestBody(required = false) Map<String,Object> map) throws IOException {

        System.out.println(map.toString());

        //获取前端参数
        UserinfoApply userinfoApply = new UserinfoApply();
        userinfoApply.setInfoType( (Integer) map.get("type"));
        userinfoApply.setApplyResult( (Boolean) map.get("result") );
        //获取登录状态
        edu.uni.auth.bean.User loginUser = authService.getUser();
        if(loginUser == null){
            return Result.build(ResultType.Failed, "你沒有登錄");
        }
        userinfoApply.setUniversityId(loginUser.getUniversityId());
        //检验参数合法性
        if( loginUser.getId() == null || userinfoApply.getUniversityId() == null )
            return Result.build(ResultType.ParamError);

        return Result.build(ResultType.Success).appendData("userinfoApplys",userinfoApplyService.
                            selectByTypeAndResultAndUserId(userinfoApply,loginUser.getId()));

    }




    /**
     * Author: chenenru 23:44 2019/4/29
     * @param response
     * @return
     * @apiNote: 获取所有用户信息申请记录的内容
     */
    @ApiOperation( value = "获取所有用户信息申请记录的内容",notes = "2019-5-2 11:09:47已通过测试" )
    @GetMapping("userinfoApplys/listAll")
    @ResponseBody
    public void selectAll(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = UserinfoApplyController.CacheNameHelper.ListAll_CacheName;
        String json = cache.get(cacheName);
        if(json == null){
            json = Result.build(ResultType.Success)
                    .appendData("userinfoApplys",userinfoApplyService.selectAllUserinfoApplys()).convertIntoJSON();
            cache.set(cacheName,json);
        }
        response.getWriter().write(json);
    }
    /**
     * Author: chenenru 23:47 2019/4/29
     * @param  userinfoApply
     * @return Result
     * @apiNote: 新增用户信息申请信息
     */
    @ApiOperation(value="新增用户信息申请信息", notes="2019-5-2 11:09:51已通过测试")
    @ApiImplicitParam(name = "userinfoApply", value = "用户信息申请详情实体", required = true, dataType = "UserinfoApply")
    @PostMapping("/userinfoApply")  //post请求方式
    @ResponseBody
    public Result create(@RequestBody(required = false) UserinfoApply userinfoApply){
        //检验页面传来的对象是否存在
        if(userinfoApply != null){
            boolean success = userinfoApplyService.insertUserinfoApply(userinfoApply);
            if(success){
                // 清空相关缓存
                cache.delete(UserinfoApplyController.CacheNameHelper.ListAll_CacheName);
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
     * @apiNote: 删除用户信息申请
     */
    @ApiOperation(value="删除用户信息申请", notes="2019-5-2 11:09:56已通过测试")
    @ApiImplicitParam(name = "id", value = "用户信息申请的id", required = true, dataType = "Long", paramType = "path")
    @DeleteMapping("/userinfoApply/{id}")   //delete请求
    @ResponseBody
    public Result destroy(@PathVariable Long id){
        boolean success = userinfoApplyService.deleteUserinfoApply(id);
        if(success){
            // 清空相关缓存
            cache.delete(UserinfoApplyController.CacheNameHelper.ListAll_CacheName);
            return Result.build(ResultType.Success);
        }else{
            return Result.build(ResultType.Failed);
        }
    }
    /**
     * Author: chenenru 23:52 2019/4/29
     * @param userinfoApply
     * @return Result
     * @apiNote: 更新用户信息申请详情
     */
    @ApiOperation(value="更新用户信息申请详情", notes="2019-5-2 11:10:01已通过测试")
    @ApiImplicitParam(name = "userinfoApply", value = "用户信息申请详情实体", required = true, dataType = "UserinfoApply")
    @PutMapping("/userinfoApply")   //Put请求
    @ResponseBody
    public Result update(@RequestBody(required = false) UserinfoApply userinfoApply){
        if(userinfoApply != null && userinfoApply.getId() != null){
            boolean success = userinfoApplyService.updateUserinfoApply(userinfoApply);
            if(success){
                //清除相应的缓存
                cache.delete(UserinfoApplyController.CacheNameHelper.Receive_CacheNamePrefix + userinfoApply.getId());
                cache.delete(UserinfoApplyController.CacheNameHelper.ListAll_CacheName);
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }

    //    /**
//     * Author: chenenru 23:41 2019/4/29
//     * @param id response
//     * @return response
//     * @apiNote: 获取用户信息申请详情
//     */
//    //以下说明为本类中所有方法的注解的解释，仅在本处注释（因为都几乎是一个模版）
//    //@ApiOperation：用于在swagger2页面显示方法的提示信息
//    //@GetMapping：规定方法的请求路径和方法的请求方式（Get方法）
//    //@ApiImplicitParam：用于在swagger2页面测试时用于测试的变量，详细解释可以看Swagger2注解说明
//    //@ResponseBody：指明该方法效果等同于通过response对象输出指定格式的数据（JSON）
//    @ApiOperation( value = "以一个id获取一条用户信息申请记录详情",notes = "2019-5-2 11:09:42已通过测试" )
//    @GetMapping("userinfoApply/{id}")
//    @ApiImplicitParam(name = "id", value = "userinfoApply表的一个id", required = false, dataType = "Long" , paramType = "path")
//    @ResponseBody
//    public void receive(@PathVariable Long id, HttpServletResponse response) throws IOException {
//        //设置返回的数据格式
//        response.setContentType("application/json;charset=utf-8");
//
//        userinfoApplyApprovalController.getOldInfoAndNewInfoByApply(id,response);
//    }

//    /**
//     * Author: laizhouhao 20:50 2019/5/9
//     * @param userinfoApplyApproval, user_id
//     * @return Result
//     * @apiNote: 审批申请信息, 点击通过时
//     */
//    @ApiOperation(value="审批申请信息, 点击通过时", notes="2019年5月14日 15:11:29 已通过测试")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "userinfoApplyApproval", value = "用户申请审批流程表实体", required = true, dataType = "UserinfoApplyApproval"),
//            @ApiImplicitParam(name = "user_id", value = "审批人id", required = true, dataType = "Long", paramType = "path")
//    })
//    @PostMapping("commituserinfoApply/{user_id}")
//    @ResponseBody
//    public Result commitUserinfoApply(@RequestBody UserinfoApplyApproval userinfoApplyApproval, @PathVariable Long user_id){
//        if(userinfoApplyApproval != null){
//            //比较当前步骤是否是最后一步
//            boolean isLast = userService.isLastStep(userinfoApplyApproval.getStep(),userinfoApplyApproval.getUserinfoApplyId());
//            //该步骤是最后一步
//            if(isLast){
//                //更新申请表和用户信息审批流程表
//                boolean firstSuccess = userService.endForPass(userinfoApplyApproval, user_id);
//                //更新用户申请修改的信息的新旧记录
//                boolean thirdSuccess = userService.updateNewAndOldMessage(userinfoApplyApproval);
//                //判断两个更新是否都成功
//                if(firstSuccess) {
//                    //清除相应的缓存
//                    cache.delete(UserinfoApplyController.CacheNameHelper.Receive_CacheNamePrefix + "commitUserinfoApply");
//                    cache.delete(UserinfoApplyController.CacheNameHelper.ListAll_CacheName);
//                    return Result.build(ResultType.Success);
//                }else{
//                    return Result.build(ResultType.Failed);
//                }
//            }else{ //该审批不是最后一步
//                boolean secondSuccess = userService.createForPass(userinfoApplyApproval, user_id);
//                //操作成功
//                if(secondSuccess){
//                    //清除相应的缓存
//                    cache.delete(UserinfoApplyController.CacheNameHelper.Receive_CacheNamePrefix + "commitUserinfoApply");
//                    cache.delete(UserinfoApplyController.CacheNameHelper.ListAll_CacheName);
//                    return Result.build(ResultType.Success);
//                }else{
//                    return Result.build(ResultType.Failed);
//                }
//            }
//        }
//        return Result.build(ResultType.ParamError);
//    }
//
//    /**
//     * Author: laizhouhao 20:50 2019/5/9
//     * @param userinfoApplyApproval,user_id
//     * @return Result
//     * @apiNote: 审批申请信息, 点击不通过时
//     */
//    @ApiOperation(value="审批申请信息, 点击不通过时", notes="2019年5月14日 15:11:21 已通过测试")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "userinfoApplyApproval", value = "用户申请审批流程表实体", required = true, dataType = "UserinfoApplyApproval"),
//            @ApiImplicitParam(name = "user_id", value = "审批人id", required = true, dataType = "Long", paramType = "path")
//    })
//    @PostMapping(value = "refuseuserinfoApply/{user_id}",consumes = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public Result refuseUserinfoApply(@RequestBody UserinfoApplyApproval userinfoApplyApproval,@PathVariable Long user_id) throws IOException {
//        System.out.println("小莫是头猪！！！---");
//        if(userinfoApplyApproval != null && user_id != null){
//            boolean success = userService.endForRefuse(userinfoApplyApproval, user_id);
//            if(success) {
//                //清除相应的缓存
//                cache.delete(UserinfoApplyController.CacheNameHelper.Receive_CacheNamePrefix + "refuseUserinfoApply");
//                cache.delete(UserinfoApplyController.CacheNameHelper.ListAll_CacheName);
//                return Result.build(ResultType.Success);
//            }else{
//                return Result.build(ResultType.Failed);
//            }
//        }
//        return Result.build(ResultType.ParamError);
//    }

}
