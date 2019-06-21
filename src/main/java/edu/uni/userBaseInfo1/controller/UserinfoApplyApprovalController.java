package edu.uni.userBaseInfo1.controller;

import edu.uni.administrativestructure.bean.*;
import edu.uni.administrativestructure.bean.Class;
import edu.uni.administrativestructure.service.DepartmentService;
import edu.uni.administrativestructure.service.EmployPositionService;
import edu.uni.administrativestructure.service.PositionService;
import edu.uni.administrativestructure.service.SubdepartmentService;
import edu.uni.auth.bean.Role;
import edu.uni.auth.service.AuthService;
import edu.uni.auth.service.RoleService;
import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.place.service.FieldService;
import edu.uni.professionalcourses.service.SpecialtyService;
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
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private OtherClassService otherClassService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    OtherEmployService otherEmployService;
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
    private AuthService authService;
    @Autowired
    private RoleService roleService;


    @Autowired  //把缓存工具类RedisCache相应的方法自动装配到该对象
    private RedisCache cache;

    //内部类，专门用来管理每个get方法所对应缓存的名称。
    static class CacheNameHelper{
        // ub1_e_UserinfoApplyApproval_{用户信息审批流程记录id}
        public static final String Receive_CacheNamePrefix = "ub1_u_userinfoApplyApproval_";
        // ub1_e_UserinfoApplyApprovals_listAll
        public static final String ListAll_CacheName = "ub1_u_userinfoApplyApproval_listAll";
    }


    /**
     * Author: mokuanyuan 19:13 2019/5/16
     * @param userinfoApplyApproval
     * @param response
     * @apiNote: 根据审核结果和信息类型和用户角色查询所有的审批记录
     */
    @ApiOperation( value = "根据审核结果和信息类型和用户角色查询所有的审批记录",notes = "未测试" )
    @PostMapping("/getByRoleName")
    @ApiImplicitParam(name = "userinfoApplyApproval", value = "用户信息审批流程详情实体", required = true, dataType = "UserinfoApplyApproval")
    @ResponseBody
    public Result selectByRoleName(@RequestBody UserinfoApplyApproval userinfoApplyApproval,
                                 HttpServletResponse response) throws IOException {

        response.setContentType("application/json;charset=utf-8");
        edu.uni.auth.bean.User loginUser = authService.getUser();
        if(loginUser == null){
            return Result.build(ResultType.Failed, "你沒有登錄");
        }

        System.out.println(userinfoApplyApproval.toString());

        Long schoolId = loginUser.getUniversityId() ;
//        Long user_id = (long) 1720 ;
        Long user_id = loginUser.getId();

        List<Role> rolesBean = roleService.selectByUidAndUniversityId(schoolId, user_id);
        List<String > roles = new ArrayList<>();
        rolesBean.forEach( item -> { roles.add(item.getName()); } );

        List<UserinfoApplyApproval> userinfoApplyApprovalList =
                userinfoApplyApprovalService.selectAllByApprovalAndRole(userinfoApplyApproval,roles);

        //筛选查询出来的审批记录，符合条件才放入List里
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

        //搜索申请人姓名
        List<String> apply_name = new ArrayList<>();
        show_apply.forEach( item -> {
            apply_name.add(userService.selectUserById(item.getApplyUserId()).getUserName());
        } );

//        //把审核记录和申请人姓名放入json串
//        String json = Result.build(ResultType.Success).appendData("apply_list",show_apply).
//                appendData("Name",apply_name).convertIntoJSON();
//
//        response.getWriter().write(json);

        return Result.build(ResultType.Success).appendData("apply_list",show_apply).
                appendData("Name",apply_name);

    }


    /**
     * Author: mokuanyuan 18:33 2019/5/14
     * @param applyId
     * @param response
     * @return 旧纪录（如果有的话） 和 新纪录
     * @apiNote: 根据信息类型和新旧记录的id查询出 旧纪录（如果有的话） 和 新纪录
     */
    @ApiOperation( value = "据信息类型和新旧记录的id查询出 旧纪录（如果有的话） 和 新纪录",notes = "未测试" )
    @GetMapping("/getOldInfoAndNewInfoByApply/{applyId}")
    @ApiImplicitParam(name = "applyId", value = "userinfoApply表的一个id", required = false, dataType = "Long" , paramType = "path")
    @ResponseBody
    public void getOldInfoAndNewInfoByApply(@PathVariable Long applyId ,
                                            HttpServletResponse response ) throws IOException{
        response.setContentType("application/json;charset=utf-8");
        //获取申请记录详情
        UserinfoApply userinfoApply = userinfoApplyService.selectUserinfoApplyById(applyId);
        //通过申请记录找到新旧记录的id，但还不知道要去查哪个表，后面根据申请记录的信息类型再去查相应的表记录
        Long old_id = userinfoApply.getOldInfoId();
        Long new_id = userinfoApply.getNewInfoId();

        Result result = Result.build(ResultType.Success);

        //根据信息类型调用相应的service层的查询方法
        switch ( userinfoApply.getInfoType() ) {
            case 0: //0为联系方式
                if( old_id != null){
                    Ecomm ecomm = ecommService.selectById(old_id);
                    if(ecomm != null)
                        result.appendData("old_info",ecomm);
                }
                Ecomm ecomm = ecommService.selectById(new_id);
                if( ecomm != null)
                    result.appendData("new_info",ecomm);
                break;
            case 1: //1为地址
                if( old_id != null){
                   Address address = addressService.selectById(old_id);
                   if(address != null){
                       HashMap<String,Object> map = new HashMap<>();
                       addressService.selectAllInfoToMap(map,address);
                       result.appendData("old_info", map );
                   }
                }
                Address address = addressService.selectById(new_id);
                if(address !=null){
                    HashMap<String,Object> new_map = new HashMap<>();
                    addressService.selectAllInfoToMap(new_map,address);
                    result.appendData("new_info",new_map);
                }
                break;

            case 2: //2为照片
                if( old_id != null){
                    Picture picture = pictureService.selectById(old_id);
                    if(picture !=null)
                        result.appendData("old_info",picture);
                }
                Picture picture = pictureService.selectById(new_id);
                if(picture !=null)
                    result.appendData("new_info",picture);
                break;
            case 3: //3为学生亲属
                if( old_id != null){
                    StudentRelation studentRelation = studentRelationService.selectById(old_id);
                    result.appendData("relation",studentRelation);
                    if(studentRelation !=null){
                        User user = userService.selectUserById(studentRelation.getRelaId());
                        if(user !=null)
                            result.appendData("old_info",user);
                    }
                }

                StudentRelation studentRelation = studentRelationService.selectById(new_id);
                result.appendData("relation",studentRelation);
                if(studentRelation!=null){
                    User user = userService.selectUserById(studentRelation.getRelaId());
                    if(user!=null)
                        result.appendData("new_info",user);
                }
                break;
            case 4: //4为学历
                if( old_id != null ){
                    LearningDegree learningDegree = learningDegreeSerevice.selectLearningDegreeById(old_id);
                    if(learningDegree!=null){
                        HashMap<String, Object> map = new HashMap<>();
                        learningDegreeSerevice.selectAllInfoToMap(map,learningDegree);
                        result.appendData("old_info",map);
                    }
                }
                LearningDegree learningDegree = learningDegreeSerevice.selectLearningDegreeById(new_id);
                if(learningDegree!=null){
                    HashMap<String, Object> new_degree = new HashMap<>();
                    learningDegreeSerevice.selectAllInfoToMap(new_degree,learningDegree);
                    result.appendData("new_info",new_degree);
                }
                break;
            case 5: //5为简历
                if( old_id != null){
                    EmployeeHistory employeeHistory = employeeHistoryService.selectById(old_id);
                    if(employeeHistory!=null)
                        result.appendData("old_info",employeeHistory);
                }
                EmployeeHistory employeeHistory = employeeHistoryService.selectById(new_id);
                if(employeeHistory!=null)
                    result.appendData("new_info",employeeHistory);
                break;
            case 6: //6为学生信息
                if( old_id != null){ //旧信息
                    HashMap<String, Object> map = new HashMap<>();
                    Student student = studentService.selectById(old_id);
                    map.put("user",userService.selectUserById(student.getUserId()));
                    map.put("student_no",student.getStuNo());
//                    System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(student.getBeginLearnDate()));
                    map.put("begin_learn_date",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(student.getBeginLearnDate()));
                    map.put("grade",student.getGrade());
                    map.put("specialty",specialtyService.select(student.getSpecialtyId()).getName());
                    map.put("class",otherClassService.select(student.getClassId()).getName());
                    map.put("political",politicalAffiliationService.selectPoliticalAffiliationById(student.getPoliticalId()).getPolitical());
                    map.put("live_room",fieldService.select(student.getLiveRoom()).getName());
//                    Address home_address = addressService.selectById(student.getHomeAddressId());
//                    HashMap<String, Object> address_map = new HashMap<>();
//                    addressService.selectAllInfoToMap(address_map,home_address);
//                    map.put("home_address",address_map);
//                    map.put("ecomm",student.getPhoneEcommId());
                    result.appendData("old_info",map);
                }
                //放入新信息
                HashMap<String, Object> new_map = new HashMap<>();
                Student student = studentService.selectById(new_id);
                new_map.put("user",userService.selectUserById(student.getUserId()));
                new_map.put("student_no",student.getStuNo());
                new_map.put("begin_learn_date",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(student.getBeginLearnDate()));
                new_map.put("grade",student.getGrade());
                new_map.put("specialty",specialtyService.select(student.getSpecialtyId()).getName());
                new_map.put("class",otherClassService.select(student.getClassId()).getName());
                new_map.put("political",politicalAffiliationService.selectPoliticalAffiliationById(student.getPoliticalId()).getPolitical());
                new_map.put("live_room",fieldService.select(student.getLiveRoom()).getName());
//                Address home_address = addressService.selectById(student.getHomeAddressId());
//                HashMap<String, Object> address_new_map = new HashMap<>();
//                addressService.selectAllInfoToMap(address_new_map,home_address);
//                new_map.put("home_address",address_new_map);
//                new_map.put("ecomm",student.getPhoneEcommId());
                result.appendData("new_info",new_map);
                break;
            case 7: //7为教职工信息
                if( old_id != null){
                    Employee employee = employeeService.selectEmployeeById(old_id);
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("user",userService.selectUserById(employee.getUserId()));
                    map.put("employee_no",employee.getEmpNo());
                    map.put("department", departmentService.select(employee.getDepartmentId()).getName());
                    map.put("subdepartment", subdepartmentService.select(employee.getSubdepartmentId()).getName());
                    map.put("employ_history",employeeHistoryService.selectById(employee.getEmployHistoryId()));
                    map.put("discipline",mySecondLevelDisciplineService.
                            selectSecondLevelDisciplineById(employee.getDisciplineId()).getName());
                    map.put("political",politicalAffiliationService.
                            selectPoliticalAffiliationById(employee.getPositionId()).getPolitical());
                    List<EmployPosition> employPositions =
                            otherEmployPositionService.selectByEmployeeId(employee);
                    List<String> positions = new ArrayList<>();
                    for(EmployPosition employPosition : employPositions)
                        positions.add(positionService.select(employPosition.getPositionId()).getName());
                    map.put("positions",positions);
                    result.appendData("old_info",map);
                }
                Employee employee = employeeService.selectEmployeeById(new_id);
                HashMap<String, Object> new_employee = new HashMap<>();
                new_employee.put("user",userService.selectUserById(employee.getUserId()));
                new_employee.put("employee_no",employee.getEmpNo());
                new_employee.put("department", departmentService.select(employee.getDepartmentId()).getName());
                new_employee.put("subdepartment", subdepartmentService.select(employee.getSubdepartmentId()).getName());
                new_employee.put("employ_history",employeeHistoryService.selectById(employee.getEmployHistoryId()));
                new_employee.put("discipline",mySecondLevelDisciplineService.
                        selectSecondLevelDisciplineById(employee.getDisciplineId()).getName());
                new_employee.put("political",politicalAffiliationService.
                        selectPoliticalAffiliationById(employee.getPositionId()).getPolitical());
                List<EmployPosition> employPositions =
                        otherEmployPositionService.selectByEmployeeId(employee);
                List<String> positions = new ArrayList<>();
                for(EmployPosition employPosition : employPositions)
                    positions.add(positionService.select(employPosition.getPositionId()).getName());
                new_employee.put("positions",positions);
                result.appendData("new_info",new_employee);
                break;
            case 8: //8为用户个人信息
                if( old_id != null)
                    result.appendData("old_info",userService.selectUserById(old_id));
                result.appendData("new_info",userService.selectUserById(new_id));
                break;
            case 9: //9为学生信息excel表
//                if( old_id != null)
//                    result.appendData("old_info",userUploadFileService.selectUserUploadFileById(old_id));
                result.appendData("new_info",userUploadFileService.selectUserUploadFileById(new_id));
                break;
            case 10:  //10为教职工信息excel表
//                if( old_id != null)
//                    result.appendData("old_info",userUploadFileService.selectUserUploadFileById(old_id));
                result.appendData("new_info",userUploadFileService.selectUserUploadFileById(new_id));
                break;
            case 11: //11为学生信息excel表
//                if( old_id != null)
//                    result.appendData("old_info",userUploadFileService.selectUserUploadFileById(old_id).getFileName());
                result.appendData("new_info",userUploadFileService.selectUserUploadFileById(new_id));
                break;
            case 12:  //12为教职工信息excel表
//                if( old_id != null)
//                    result.appendData("old_info",userUploadFileService.selectUserUploadFileById(old_id).getFileName());
                result.appendData("new_info",userUploadFileService.selectUserUploadFileById(new_id));
                break;

        }

        //获取申请人信息
        result.appendData("user_info",userService.selectUserById(userinfoApply.getByWho()));
        //申请理由
        result.appendData("reason",userinfoApply.getApplyReason());
        //如果是第n步审核(n>1)，则搜索出之前的所有审核流程记录
        //根据申请表id查询所有的审批流程
        List<UserinfoApplyApproval> userinfoApplyApprovals =
                userinfoApplyApprovalService.selectByApplyId(userinfoApply.getId());
        result.appendData("apply_approval",userinfoApplyApprovals);
        //再把每一步审批人的姓名也查出来
//        List<User> approvers = new ArrayList<>();
        List<String> approversName = new ArrayList<>();
        userinfoApplyApprovals.forEach(item -> {
            User user = userService.selectUserById(item.getCheckWho());
            if( user != null )
                approversName.add(user.getUserName());
        });
        result.appendData("approversName",approversName);

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
     * @param studentUserId
     * @param userId
     * @return boolean
     * @apiNote: 根据学生用户的用户id和另一个教职工用户的用户id ， 判断这两个用户是否是同一个二级学院
     */
    public boolean isDepartmentSame(long studentUserId,long userId){
        //审批人
        System.out.println("-->"+employeeService.selectByUserId(userId));
        Employee employee = employeeService.selectByUserId(userId).get(0);
        Employ employ = otherEmployService.selectEmployByEmployeeId(employee.getId(),employee.getUniversityId());
        System.out.println(employ.getDepartmentId());
        //申请人为学生才行
        Student student = studentService.selectByUserId(studentUserId).get(0);
        Class aClass = otherClassService.selectClassByClassId( student.getClassId() );
        System.out.println(aClass.getDepartmentId()+"--->");
        if( employ.getDepartmentId().equals(aClass.getDepartmentId()) ) {
//            System.out.println("是同一个学院");
            return true;
        }else{
//            System.out.println("不是同一个学院");
            return false;
        }
    }

}
