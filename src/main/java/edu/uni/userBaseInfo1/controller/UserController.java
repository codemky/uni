package edu.uni.userBaseInfo1.controller;

import edu.uni.administrativestructure.mapper.PositionMapper;
import edu.uni.administrativestructure.service.DepartmentService;
import edu.uni.administrativestructure.service.PositionService;
import edu.uni.administrativestructure.service.SubdepartmentService;
import edu.uni.administrativestructure.service.UniversityService;
import edu.uni.auth.mapper.RoleMapper;
import edu.uni.auth.service.AuthService;
import edu.uni.auth.service.RoleService;
import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.place.service.FieldService;
import edu.uni.professionalcourses.service.SpecialtyService;
import edu.uni.userBaseInfo1.bean.*;
import edu.uni.userBaseInfo1.mapper.EmployeeMapper;
import edu.uni.userBaseInfo1.mapper.UserinfoApplyApprovalMapper;
import edu.uni.userBaseInfo1.mapper.UserinfoApplyMapper;
import edu.uni.userBaseInfo1.service.*;
import edu.uni.userBaseInfo1.utils.UserInfo;
import edu.uni.utils.LogUtils;
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
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

/**
 * @Author chenenru
 * @ClassName UserController
 * @Description
 * @Date 2019/4/29 23:37
 * @Version 1.0
 **/
//填写description内容可以在测试模块显示相应的文字和模块
@Api(description = "用户信息模块")
//Controller类（或者说Http）的请求路径
//如果添加了路径，则在需要调用该类的方法时需要在方法请求mapping路径前加上类的mapping路径
@RequestMapping("json/user")
//标志这个类是一个controller类，用于被Spring扫描然后配置添加和配置相应的bean
@Controller
public class UserController {
    private LogUtils logUilts = new LogUtils(this.getClass());

    @Autowired
    private AuthService authService;
    @Autowired
    private PositionMapper positionMapper;
    @Autowired
    private UserinfoApplyMapper userinfoApplyMapper;
    @Autowired
    private ApprovalMainService approvalMainService;
    @Autowired
    private UserinfoApplyApprovalMapper userinfoApplyApprovalMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private EcommService ecommService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentRelationService studentRelationService;
    @Autowired
    private PoliticalAffiliationService politicalAffiliationService;
    @Autowired
    private PictureService pictureService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private MyAcademicService myAcademicService;
    @Autowired
    private MyAcademicDegreeService myAcademicDegreeService;
    @Autowired
    private EmployeeHistoryService employeeHistoryService;
    @Autowired
    private LearningDegreeSerevice learningDegreeSerevice;
    @Autowired
    UserinfoApplyService userinfoApplyService;
    @Autowired
    UserinfoApplyApprovalController userinfoApplyApprovalController;
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
    private SpecialtyService specialtyService;
    @Autowired
    private FieldService fieldService;
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
    private OtherDepartmentService otherDepartmentService;
    @Autowired
    private OtherSubdepartmentService otherSubdepartmentService;



    @Autowired  //把缓存工具类RedisCache相应的方法自动装配到该对象
    private RedisCache cache;

    //内部类，专门用来管理每个get方法所对应缓存的名称。
    static class CacheNameHelper{
        // ub1_e_User_{用户记录id}
        public static final String Receive_CacheNamePrefix = "ub1_e_user_";
        // ub1_e_Users_listAll
        public static final String ListAll_CacheName = "ub1_e_user_listAll";
    }

    @ApiOperation( value = "获取登录信息",notes = "未测试" )
    @GetMapping("getLoginInfo")
    @ResponseBody
    public Result getLoginInfo(){
        edu.uni.auth.bean.User user = authService.getUser();
        if(user == null){
            return Result.build(ResultType.Failed, "你沒有登錄");
        }
        return Result.build(ResultType.Success).appendData("user", user);
    }



    @ApiOperation( value = "获取职员用户的所有权限",notes = "未测试" )
    @GetMapping("getEmployeePositions")
    @ResponseBody
    public Result getEmployeePositions(){
        edu.uni.auth.bean.User loginUser = authService.getUser();
        if(loginUser == null)
            return Result.build(ResultType.Failed, "你沒有登錄");
        if(loginUser.getUserType() != 2)
            return Result.build(ResultType.Disallow,"登陆用户不是教职工类型用户“");

        List<Employee> employees = employeeService.selectByUserId(loginUser.getId());
        if( employees.size() == 0 )
            return Result.build(ResultType.Failed,"该教职工用户的教职工信息“为空");

        List<Integer> roles = otherEmployPositionService.selectEmployeeRoleByUserId(employees.get(0));

        return roles.size() > 0 ? Result.build(ResultType.Success).appendData("employeeRoles",roles) :
                Result.build(ResultType.Failed,"该职员没有任何权限");

    }


    @ApiOperation(value="获取用户这部分的信息", notes="未测试")
    @ApiImplicitParam(name = "userId", value = "用户ID", required = false, dataType = "Long" , paramType = "path")
    @GetMapping("/getUserInformation/{userId}")
    @ResponseBody
    public Result getUserInformation(@PathVariable Long userId) throws IOException{
        edu.uni.auth.bean.User loginUser = authService.getUser();
        if(userId == null)
            return Result.build(ResultType.ParamError);
        if(userId == -1 ) { // -1 代表的是查询自己的信息
            if (loginUser == null) {
                return Result.build(ResultType.Failed, "你沒有登錄");
            } else {
                userId = loginUser.getId();
            }
        }
//        }else{  //否则表示某个登录后的用户查看某个用户信息，此时需要检验这个登录用户的角色是否有权限查看这个用户信息
//            User user = userService.selectUserById(userId);
//            if( user == null )
//                return Result.build(ResultType.ParamError,"该学生信息不存在或已过时");
//            if( user.getUserType() != 1 ) //用户Id需要时学生类型，不然查了也是白查
//                return Result.build(ResultType.ParamError,"所查看的用户不是学生");
//            //判断该学生与该登录用户的二级学院关系
//            if( !userinfoApplyApprovalController.isDepartmentSame(user.getId(), loginUser.getId()))
//                return Result.build(ResultType.Disallow,"登录用户和所查学生不在同一个二级学院“”");
//            //判断该登录用户是否包含某几个角色
//            if( !otherRoleService.isPlayDepartmentLeader(loginUser.getId()) )
//                return Result.build(ResultType.Disallow,"登录用户的操作权限不允许");
//
//        }
        User user = userService.selectUserById(userId);


//        boolean isOperate = false;
//        HashMap<String , Object> studentMap = new HashMap<>();
//
//        if(user.getUserType() == 1) {
//            List<Student> students = studentService.selectByUserId(userId);
//            if (students.size() > 0) {
//                studentService.selectByUserIdToMap(studentMap, students.get(0));
//                isOperate = true;
//            }
//        }
//

        HashMap<String , Object> map = new HashMap<>();
        map.put("userName",user.getUserName());
        map.put("identification",user.getIdentification());
        map.put("userSex",user.getUserSex());
        map.put("university", otherUniversityService.selectValidById(user.getUniversityId()).getName());
        map.put("userType",user.getUserType());
        map.put("regist",user.getRegist());

//        if(isOperate)
            return Result.build(ResultType.Success).appendData("user",map);
//        else
//            return Result.build(ResultType.ParamError);
    }




    /**
     * Author: chenenru 23:41 2019/4/29
     * @param id
     * @return response
     * @apiNote: 获取用户详情
     */
    //以下说明为本类中所有方法的注解的解释，仅在本处注释（因为都几乎是一个模版）
    //@ApiOperation：用于在swagger2页面显示方法的提示信息
    //@GetMapping：规定方法的请求路径和方法的请求方式（Get方法）
    //@ApiImplicitParam：用于在swagger2页面测试时用于测试的变量，详细解释可以看Swagger2注解说明
    //@ResponseBody：指明该方法效果等同于通过response对象输出指定格式的数据（JSON）
    @ApiOperation( value = "以一个id获取一条用户记录详情",notes = "2019-5-2 11:08:41已通过测试" )
    @GetMapping("user/{id}")
    @ApiImplicitParam(name = "id", value = "User表的一个id", required = false, dataType = "Long" , paramType = "path")
    @ResponseBody
    public void receive(@PathVariable Long id, HttpServletResponse response) throws IOException {
        //设置返回的数据格式
        response.setContentType("application/json;charset=utf-8");
        //拼接缓存键名（字符串）
        String cacheName = UserController.CacheNameHelper.Receive_CacheNamePrefix +"id"+id;
        //尝试在缓存中通过键名获取相应的键值
        //因为在Redis中，数据是以”“” "键-值"对 的形式储存的
        String json = cache.get(cacheName);
        //如果在缓存中找不到，那就从数据库里找
        if(json == null){
            User user = userService.selectUserById(id);
            //把查询到的结果用Result工具类转换成json格式的字符串
            json = Result.build(ResultType.Success).appendData("user",user).convertIntoJSON();
            //如果有查询到数据，就把在数据库查到的数据放到缓存中
            if(user != null){
                cache.set(cacheName,json);
            }
        }
        //到最后通过response对象返回json格式字符串的数据
        response.getWriter().write(json);

    }
    /**
     * Author: chenenru 23:44 2019/4/29
     * @param
     * @return
     * @apiNote: 获取所有用户记录的内容
     */
    @ApiOperation( value = "获取所有用户记录的内容",notes = "2019-5-2 11:08:46已通过测试" )
    @GetMapping("users/listAll")
    @ResponseBody
    public void selectAll(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = UserController.CacheNameHelper.ListAll_CacheName;
        String json = cache.get(cacheName);
        if(json == null){
            json = Result.build(ResultType.Success)
                    .appendData("users",userService.selectAllUsers()).convertIntoJSON();
            cache.set(cacheName,json);
        }
        response.getWriter().write(json);
    }
    /**
     * Author: chenenru 23:47 2019/4/29
     * @param  user
     * @return Result
     * @apiNote: 新增用户信息
     */
    @ApiOperation(value="新增用户信息", notes="2019-5-2 11:08:50已通过测试")
    @ApiImplicitParam(name = "user", value = "用户详情实体", required = true, dataType = "User")
    @PostMapping("/user")  //post请求方式
    @ResponseBody
    public Result create(@RequestBody(required = false) User user){
        //检验页面传来的对象是否存在
        if(user != null){
            boolean success = userService.insertUser(user);
            if(success){
                // 清空相关缓存
                cache.delete(UserController.CacheNameHelper.ListAll_CacheName);
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
     * @apiNote: 删除用户
     */
    @ApiOperation(value="删除用户", notes="2019-5-2 11:08:55已通过测试")
    @ApiImplicitParam(name = "id", value = "用户的id", required = true, dataType = "Long", paramType = "path")
    @DeleteMapping("/user/{id}")   //delete请求
    @ResponseBody
    public Result destroy(@PathVariable Long id){
        boolean success = userService.deleteUser(id);
        if(success){
            // 清空相关缓存
            cache.delete(UserController.CacheNameHelper.ListAll_CacheName);
            return Result.build(ResultType.Success);
        }else{
            return Result.build(ResultType.Failed);
        }
    }
    /**
     * Author: chenenru 23:52 2019/4/29
     * @param user
     * @return Result
     * @apiNote: 更新用户详情
     */
    @ApiOperation(value="更新用户详情", notes="2019-5-2 11:08:59已通过测试")
    @ApiImplicitParam(name = "user", value = "用户详情实体", required = true, dataType = "User")
    @PutMapping("/user")   //Put请求
    @ResponseBody
    public Result update(@RequestBody(required = false) User user){
        if(user != null && user.getId() != null){
            boolean success = userService.updateUser(user);
            if(success){
                //清除相应的缓存
                cache.delete(UserController.CacheNameHelper.Receive_CacheNamePrefix + user.getId());
                cache.delete(UserController.CacheNameHelper.ListAll_CacheName);
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }

    /**
     * Author: laizhouhao 21:40 2019/5/7
     * @param user_id
     * @return response
     * @apiNote: 根据用户表的id查找该用户的照片、地址信息
     */
    @ApiOperation( value = "根据用户表的id查找该用户的照片、地址信息",notes = "2019年5月8日 20:40:35 已测试通过" )
    @GetMapping("info/userPictureAddr/{user_id}")
    @ApiImplicitParam(name = "user_id", value = "用户user_id", required = false, dataType = "Long" , paramType = "path")
    @ResponseBody
    public void receiveUserPictureAddr(@PathVariable Long user_id, HttpServletResponse response) throws IOException {
        //检验页面传来的id是否存在
        if(user_id != null){
            UserInfo userInfo = new UserInfo();
            //获取用户照片、地址信息
            userInfo = userService.selectPictureAddrByUserId(user_id);
            //设置返回的数据格式
            response.setContentType("application/json;charset=utf-8");
            //拼接缓存键名（字符串）
            String cacheName = UserController.CacheNameHelper.Receive_CacheNamePrefix +"pictureUserId"+ user_id;
            //尝试在缓存中通过键名获取相应的键值
            //因为在Redis中，数据是以”“” "键-值"对 的形式储存的
            String json = cache.get(cacheName);
            //如果在缓存中找不到，那就从数据库里找
            if(json == null){
                json = Result.build(ResultType.Success)
                        .appendData("userInfo",userInfo).convertIntoJSON();
                cache.set(cacheName,json);
            }
            //到最后通过response对象返回json格式字符串的数据
            response.getWriter().write(json);
        }
    }

    /**
     * Author: laizhouhao 18:31 2019/5/9
     * @param user_id,oldPwd,newPwd
     * @return Result
     * @apiNote: 验证旧密码后修改密码
     */
    @ApiOperation( value = "验证旧密码后修改密码",notes = "2019-5-9 19:32:36 已通过测试" )
    @GetMapping("modifyPassword/{user_id}/{oldPwd}/{newPwd}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_id", value = "用户id", required = false, dataType = "Long", paramType = "path"),
            @ApiImplicitParam(name = "oldPwd", value = "旧密码", required = false, dataType = "String", paramType = "path" ),
            @ApiImplicitParam(name = "newPwd", value = "新密码", required = false, dataType = "String", paramType = "path" )
    })
    @ResponseBody
    public Result updatePassword(@PathVariable Long user_id,@PathVariable String oldPwd,@PathVariable String newPwd, HttpServletResponse response) throws IOException {
        //检验页面传来的id、旧密码、新密码是否存在
        if(user_id!=null && oldPwd != null && newPwd!=null){
            User user = new User();
            user.setPwd(newPwd);
            //构造修改条件，即当用户名和旧密码都正确时
            UserExample userExample = new UserExample();
            userExample.createCriteria().andIdEqualTo(user_id).andPwdEqualTo(oldPwd);
            int success = userService.updateByExample(user,userExample);
            if(success==1){
                //清除相应的缓存
                cache.delete(UserController.CacheNameHelper.Receive_CacheNamePrefix +"midifyPwd"+ user_id);
                cache.delete(UserController.CacheNameHelper.ListAll_CacheName);
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }

    /**
     * Author: laizhouhao 19:53 2019/6/9
     * @param user_id
     * @return userType
     * @apiNote: 根据用户id获取用户类型
     */
    @ApiOperation( value = "根据用户id获取用户类型",notes = "2019年6月9日 20:09:47 已通过测试" )
    @GetMapping("/getUserType/{user_id}")
    @ApiImplicitParam(name = "user_id", value = "用户user_id", required = false, dataType = "Long" , paramType = "path")
    @ResponseBody
    public void receiveUserInfoListAll(@PathVariable Long user_id, HttpServletResponse response) throws IOException {
        //检验页面传来的user_id是否存在
        if(user_id != null){
            //查找用户类型
            String userType = userService.getUserType(user_id);
            //设置返回的数据格式
            response.setContentType("application/json;charset=utf-8");
            //拼接缓存键名（字符串）
            String cacheName = UserController.CacheNameHelper.Receive_CacheNamePrefix +"userInfoListAll"+ user_id;
            //尝试在缓存中通过键名获取相应的键值
            //因为在Redis中，数据是以”“” "键-值"对 的形式储存的
            String json = cache.get(cacheName);
            //如果在缓存中找不到，那就从数据库里找
            if(json == null){
                json = Result.build(ResultType.Success)
                        .appendData("userType",userType).convertIntoJSON();
                cache.set(cacheName,json);
            }
            //到最后通过response对象返回json格式字符串的数据
            response.getWriter().write(json);
        }
    }

}
