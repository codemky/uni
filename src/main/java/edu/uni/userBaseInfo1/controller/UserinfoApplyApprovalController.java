package edu.uni.userBaseInfo1.controller;

import edu.uni.administrativestructure.bean.Class;
import edu.uni.administrativestructure.bean.Employ;
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

        List<String> apply_name = new ArrayList<>();
        show_apply.forEach( item -> {
            apply_name.add(userService.selectUserById(item.getApplyUserId()).getUserName());
        } );



        String json = Result.build(ResultType.Success).appendData("apply_list",show_apply).
                appendData("Name",apply_name).convertIntoJSON();

        response.getWriter().write(json);

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
        UserinfoApply userinfoApply = userinfoApplyService.selectUserinfoApplyById(applyId);
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
                       map.put("country", addrCountryService.selectAddrCountryById(address.getCountry()).getCountryZh() );
                       map.put("state", addrStateService.selectAddrStateById( address.getState()).getStateZh() );
                       map.put("city", addrCityService.selectAddrCityById(address.getCity()).getCityZh() );
                       map.put("area", addrAreaService.selectAddrAreaById(address.getArea()).getAreaZh() );
                       map.put("street", addrStreetService.selectAddrStreetById(address.getStreet()).getStreetZh() );
                       map.put("detail", address.getDetail() );
                       map.put("zip_code",address.getZipCode());
                       map.put("telephone",address.getTelephone());
                       map.put("flag",address.getFlag());
                       result.appendData("old_info", map );
                   }
                }
                Address address = addressService.selectById(new_id);
                if(address !=null){
                    HashMap<String,Object> new_map = new HashMap<>();
                    new_map.put("country", addrCountryService.selectAddrCountryById(address.getCountry()).getCountryZh() );
                    new_map.put("state", addrStateService.selectAddrStateById( address.getState()).getStateZh() );
                    new_map.put("city", addrCityService.selectAddrCityById(address.getCity()).getCityZh() );
                    new_map.put("area", addrAreaService.selectAddrAreaById(address.getArea()).getAreaZh() );
                    new_map.put("street", addrStreetService.selectAddrStreetById(address.getStreet()).getStreetZh() );
                    new_map.put("detail", address.getDetail() );
                    new_map.put("zip_code",address.getZipCode());
                    new_map.put("telephone",address.getTelephone());
                    new_map.put("flag",address.getFlag());
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
                    if(studentRelation !=null){
                        User user = userService.selectUserById(studentRelation.getRelaId());
                        if(user !=null)
                            result.appendData("old_info",user);
                    }
                }
                StudentRelation studentRelation = studentRelationService.selectById(new_id);
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
                        map.put("country",addrCountryService.selectAddrCountryById(learningDegree.getCountryId()));
                        map.put("city",addrCityService.selectAddrCityById(learningDegree.getCityId()));
                        map.put("school", otherUniversityService.selectValidById(learningDegree.getSchoolId()));
                        map.put("academic", myAcademicService.selectById(learningDegree.getAcademicId()));
                        map.put("degree", myAcademicDegreeService.selectById(learningDegree.getDegreeId()));
                        result.appendData("old_info",map);
                    }
                }
                LearningDegree learningDegree = learningDegreeSerevice.selectLearningDegreeById(new_id);
                if(learningDegree!=null){
                    HashMap<String, Object> new_degree = new HashMap<>();
                    new_degree.put("country",addrCountryService.selectAddrCountryById(learningDegree.getCountryId()));
                    new_degree.put("city",addrCityService.selectAddrCityById(learningDegree.getCityId()));
                    new_degree.put("school", otherUniversityService.selectValidById(learningDegree.getSchoolId()));
                    new_degree.put("academic", myAcademicService.selectById(learningDegree.getAcademicId()));
                    new_degree.put("degree", myAcademicDegreeService.selectById(learningDegree.getDegreeId()));
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
                if( old_id != null)
                    result.appendData("old_info",studentService.selectById(old_id));
                result.appendData("new_info",studentService.selectById(new_id));
                break;
            case 7: //7为教职工信息
                if( old_id != null)
                    result.appendData("old_info",employeeService.selectEmployeeById(old_id));
                result.appendData("new_info",employeeService.selectEmployeeById(new_id));
                break;
            case 8: //8为用户个人信息
                if( old_id != null)
                    result.appendData("old_info",userService.selectUserById(old_id));
                result.appendData("new_info",userService.selectUserById(new_id));
                break;
            case 9: //9为学生信息excel表
                if( old_id != null)
                    result.appendData("old_info",userUploadFileService.selectUserUploadFileById(old_id));
                result.appendData("new_info",userUploadFileService.selectUserUploadFileById(new_id));
                break;
            case 10:  //10为教职工信息excel表
                if( old_id != null)
                    result.appendData("old_info",userUploadFileService.selectUserUploadFileById(old_id));
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
        //再把每一步审批人的信息也查出来
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
     * @param studentId
     * @param userId
     * @return boolean
     * @apiNote: 根据申请人的用户id(第一个参数)和登录到审批中心人的用户id(第二个参数)判断是否处于同一个学院
     */
    public boolean isDepartmentSame(long studentId,long userId){
        //审批人
        System.out.println("-->"+employeeService.selectByUserId(userId));
        Employee employee = employeeService.selectByUserId(userId).get(0);
        Employ employ = otherEmployService.selectEmployByEmployeeId(employee.getId(),employee.getUniversityId());
        System.out.println(employ.getDepartmentId());
        //申请人为学生才行
        Student student = studentService.selectByUserId(studentId).get(0);
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
