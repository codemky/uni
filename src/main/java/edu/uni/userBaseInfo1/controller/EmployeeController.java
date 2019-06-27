package edu.uni.userBaseInfo1.controller;

import edu.uni.administrativestructure.bean.*;
import edu.uni.administrativestructure.bean.Class;
import edu.uni.administrativestructure.service.EmployPositionService;
import edu.uni.administrativestructure.service.PositionService;
import edu.uni.administrativestructure.service.SubdepartmentService;
import edu.uni.administrativestructure.service.UniversityService;
import edu.uni.auth.service.AuthService;
import edu.uni.educateAffair.bean.Curriculum;
import edu.uni.educateAffair.service.CurriculumService;
import edu.uni.place.bean.Field;
import edu.uni.professionalcourses.bean.Specialty;
import edu.uni.professionalcourses.service.SpecialtyService;
import edu.uni.userBaseInfo1.PageBean.ClassmateBean;
import edu.uni.userBaseInfo1.PageBean.EmployeeBean;
import edu.uni.userBaseInfo1.service.OtherClassService;
import edu.uni.userBaseInfo1.service.OtherClassmateService;
import edu.uni.userBaseInfo1.service.OtherEmployService;
import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.userBaseInfo1.bean.*;
import edu.uni.userBaseInfo1.service.*;
import edu.uni.userBaseInfo1.utils.UserInfo;
import edu.uni.utils.RedisCache;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.web.session.HttpServletSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @Author chenenru
 * @ClassName EmployeeController
 * @Description
 * @Date 2019/4/30 0:14
 * @Version 1.0
 **/
//填写description内容可以在测试模块显示相应的文字和模块
@Api(description = "职员信息模块")
//Controller类（或者说Http）的请求路径
//如果添加了路径，则在需要调用该类的方法时需要在方法请求mapping路径前加上类的mapping路径
@RequestMapping("json/employee")
//标志这个类是一个controller类，用于被Spring扫描然后配置添加和配置相应的bean
@Controller
public class EmployeeController {
    //把employee的Service层接口所有的方法自动装配到该对象中
    @Autowired
    EmployeeService employeeService;
    @Autowired
    UserService userService;
    @Autowired
    OtherEmployService otherEmployService;
    @Autowired
    ApprovalMainService approvalMainService;
    @Autowired
    UserinfoApplyService userinfoApplyService;
    @Autowired
    UserinfoApplyApprovalService userinfoApplyApprovalService;
    @Autowired
    private OtherRoleService otherRoleService;
    @Autowired
    ApprovalStepInchargeService approvalStepInchargeService;
    @Autowired
    OtherClassService otherClassService;
    @Autowired
    private OtherClassmateService otherClassmateService;
    @Autowired
    StudentService studentService;
    @Autowired
    CurriculumService curriculumService;
    @Autowired
    OtherDepartmentService otherDepartmentService;
    @Autowired
    private OtherClassmatePositionService otherClassmatePositionService;
    @Autowired
    private SpecialtyService specialtyService;
    @Autowired
    private EcommService ecommService;
    @Autowired
    private PoliticalAffiliationService politicalAffiliationService;
    @Autowired
    private PositionService positionService;
    @Autowired
    private SubdepartmentService subdepartmentService;
    @Autowired
    private PictureService pictureService;
    @Autowired
    private OtherSubdepartmentService otherSubdepartmentService;
    @Autowired
    private MySecondLevelDisciplineService mySecondLevelDisciplineService;
    @Autowired
    private AuthService authService;
    @Autowired
    private OtherEmployPositionService otherEmployPositionService;
    @Autowired
    private OtherUniversityService otherUniversityService;


    @Autowired  //把缓存工具类RedisCache相应的方法自动装配到该对象
    private RedisCache cache;

    //内部类，专门用来管理每个get方法所对应缓存的名称。
    static class CacheNameHelper {
        // ub1_e_Employee_{职员记录id}
        public static final String Receive_CacheNamePrefix = "ub1_e_employee_";
        // ub1_e_Employees_listAll
        public static final String ListAll_CacheName = "ub1_e_employee_listAll";
    }

    @ApiOperation(value = "获取职员这部分的信息", notes = "未测试")
    @ApiImplicitParam(name = "userId", value = "用户ID", required = false, dataType = "Long", paramType = "path")
    @GetMapping("/getEmployeeInformation/{userId}")
    @ResponseBody
    public Result getEmployeeInformation(@PathVariable Long userId) throws IOException {
        edu.uni.auth.bean.User loginUser = authService.getUser();
        if (userId == null)
            return Result.build(ResultType.ParamError);
        if (userId == -1) { // -1 代表的是查询自己的信息
            if (loginUser == null) {
                return Result.build(ResultType.Failed, "你沒有登錄");
            } else {
                userId = loginUser.getId();
            }
        }

        if( !otherRoleService.isPlaySchoolLeader(loginUser.getId()) )
            return Result.build(ResultType.Failed, "你没有人事处的权限无法查看职员详情");

        List<Employee> employees = employeeService.selectByUserId(userId);
        if (employees.size() == 0)
            return Result.build(ResultType.ParamError);

        HashMap<String, Object> map = new HashMap<>();
        employeeService.selectByUserIdToMap(map, employees.get(0));


        return Result.build(ResultType.Success).appendData("employee", map).appendData("employeeBase",employees.get(0));
    }


    /**
     * Author: mokuanyuan 20:49 2019/6/18
     *
     * @param schoolId
     * @apiNote: 根据学校id获取该学校的所有部门
     */
    @ApiOperation(value = "以一个学校id获取该学校所有的有效部门", notes = "2019-5-2 11:05:35已通过测试")
    @GetMapping("getDepartments/{schoolId}")
    @ApiImplicitParam(name = "schoolId", value = "学校id", required = false, dataType = "Long", paramType = "path")
    @ResponseBody
    public Result getDepartment(Long schoolId) {
        if (schoolId == null)
            return Result.build(ResultType.ParamError, "学校id为空");

//        List<Department> departments = otherDepartmentService.selectAllValidDepartment(schoolId);

        return Result.build(ResultType.Success).appendData("Departments", otherDepartmentService.selectAllValidDepartment(schoolId));

    }

    /**
     * Author: mokuanyuan 20:49 2019/6/18
     * @param schoolId
     * @param departmentId
     * @apiNote 以一个学校id和部门id获取所有的科室
     */
    @ApiOperation(value = "以一个学校id和部门id获取所有的科室", notes = "2019-5-2 11:05:35已通过测试")
    @GetMapping("employee/getSubDepartments")
    @ResponseBody
    public Result getDepartment(@RequestParam("schoolId") Long schoolId ,
                                @RequestParam("departmentId") Long departmentId ) {

        if (schoolId == null || departmentId == null)
            return Result.build(ResultType.ParamError, "学校id或者部门id为空");

        return Result.build(ResultType.Success).appendData("Subdepartments",
                otherSubdepartmentService.selectBySchoolIdAndDepartmentId(schoolId, departmentId));

    }

    /**
     * Author: mokuanyuan 17:24 2019/6/7
     *
     * @apiNote: 根据schoolId查询该学校的所有，该方法用于点击申请时先把部分信息发给前端
     */
    @ApiOperation(value = "当职员点击申请时交付给前端的部分信息", notes = "未测试")
    @GetMapping("/getSometimeInfoForApply")
    @ResponseBody
    public Result getSometimeInfoForApply() throws IOException {

        Result result = Result.build(ResultType.Success);

        edu.uni.auth.bean.User loginUser = authService.getUser();
        if (loginUser == null) {
            return Result.build(ResultType.Failed, "你沒有登錄");
        }
        result.appendData("schoolId",loginUser.getUniversityId());
//        University university = otherUniversityService.selectValidById(loginUser.getUniversityId());
//        result.appendData("schoolName",university.getName());
        List<Department> departments = otherDepartmentService.selectAllValidDepartment(loginUser.getUniversityId());

        result.appendData("departments",departments);

        //所有的政治面貌
        result.appendData("political", politicalAffiliationService.selectAllPoliticalAffiliations());


        return result;

    }


    /**
     * Author: mokuanyuan 17:24 2019/6/7
     *
     * @apiNote: 根据schoolId查询该学校的所有，该方法用于点击申请时先把部分信息发给前端
     */
    @ApiOperation(value = "根据专业名称搜索二级学科", notes = "未测试")
    @GetMapping("/getDisciplinesByName")
    @ResponseBody
    public Result getDisciplinesByName(@RequestParam("name") String name) throws IOException {

        return Result.build(ResultType.Success).appendData("disciplines", mySecondLevelDisciplineService.selectByName(name));

    }


    /**
     * Author: chenenru 23:41 2019/4/29
     *
     * @param id response
     * @return response
     * @apiNote: 获取职员详情
     */
    //以下说明为本类中所有方法的注解的解释，仅在本处注释（因为都几乎是一个模版）
    //@ApiOperation：用于在swagger2页面显示方法的提示信息
    //@GetMapping：规定方法的请求路径和方法的请求方式（Get方法）
    //@ApiImplicitParam：用于在swagger2页面测试时用于测试的变量，详细解释可以看Swagger2注解说明
    //@ResponseBody：指明该方法效果等同于通过response对象输出指定格式的数据（JSON）
    @ApiOperation(value = "以一个id获取一条职员记录详情", notes = "2019-5-2 11:05:35已通过测试")
    @GetMapping("employee/{id}")
    @ApiImplicitParam(name = "id", value = "Employee表的一个id", required = false, dataType = "Long", paramType = "path")
    @ResponseBody
    public void receive(@PathVariable Long id, HttpServletResponse response) throws IOException {
        //设置返回的数据格式
        response.setContentType("application/json;charset=utf-8");
        //拼接缓存键名（字符串）
        String cacheName = EmployeeController.CacheNameHelper.Receive_CacheNamePrefix + id;
        //尝试在缓存中通过键名获取相应的键值
        //因为在Redis中，数据是以”“” "键-值"对 的形式储存的
        String json = cache.get(cacheName);
        //如果在缓存中找不到，那就从数据库里找
        if (json == null) {
            Employee employee = employeeService.selectEmployeeById(id);
            //把查询到的结果用Result工具类转换成json格式的字符串
            json = Result.build(ResultType.Success).appendData("employee", employee).convertIntoJSON();
            //如果有查询到数据，就把在数据库查到的数据放到缓存中
            if (employee != null) {
                cache.set(cacheName, json);
            }
        }
        //到最后通过response对象返回json格式字符串的数据
        response.getWriter().write(json);

    }

    /**
     * Author: chenenru 23:44 2019/4/29
     *
     * @param response
     * @return
     * @apiNote: 获取所有职员记录的内容
     */
    @ApiOperation(value = "获取所有职员记录的内容", notes = "2019-5-2 11:05:41已通过测试")
    @GetMapping("employees/listAll")
    @ResponseBody
    public void selectAll(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = EmployeeController.CacheNameHelper.ListAll_CacheName;
        String json = cache.get(cacheName);
        if (json == null) {
            json = Result.build(ResultType.Success)
                    .appendData("employees", employeeService.selectAllEmployees()).convertIntoJSON();
            cache.set(cacheName, json);
        }
        response.getWriter().write(json);
    }

    /**
     * Author: chenenru 23:47 2019/4/29
     *
     * @param employee
     * @return Result
     * @apiNote: 新增职员信息
     */
    @ApiOperation(value = "新增职员信息", notes = "2019-5-2 11:05:46已通过测试")
    @ApiImplicitParam(name = "employee", value = "职员详情实体", required = true, dataType = "Employee")
    @PostMapping("/employee")  //post请求方式
    @ResponseBody
    public Result create(@RequestBody(required = false) Employee employee) {
        //检验页面传来的对象是否存在
        if (employee != null) {
            boolean success = employeeService.insertEmployee(employee);
            if (success) {
                // 清空相关缓存
                cache.delete(EmployeeController.CacheNameHelper.ListAll_CacheName);
                return Result.build(ResultType.Success);
            } else {
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }

    /**
     * Author: chenenru 23:50 2019/4/29
     *
     * @param id
     * @return Result
     * @apiNote: 删除职员
     */
    @ApiOperation(value = "删除职员", notes = "2019-5-2 11:05:51已通过测试")
    @ApiImplicitParam(name = "id", value = "职员的id", required = true, dataType = "Long", paramType = "path")
    @DeleteMapping("/employee/{id}")   //delete请求
    @ResponseBody
    public Result destroy(@PathVariable Long id) {
        boolean success = employeeService.deleteEmployee(id);
        if (success) {
            // 清空相关缓存
            cache.delete(EmployeeController.CacheNameHelper.ListAll_CacheName);
            return Result.build(ResultType.Success);
        } else {
            return Result.build(ResultType.Failed);
        }
    }

    /**
     * Author: chenenru 23:52 2019/4/29
     *
     * @param employee
     * @return Result
     * @apiNote: 更新职员详情
     */
    @ApiOperation(value = "更新职员详情", notes = "2019-5-2 11:05:55已通过测试")
    @ApiImplicitParam(name = "employee", value = "职员详情实体", required = true, dataType = "employee")
    @PutMapping("/employee")   //Put请求
    @ResponseBody
    public Result update(@RequestBody(required = false) Employee employee) {
        if (employee != null && employee.getId() != null) {
            boolean success = employeeService.updateEmployee(employee);
            if (success) {
                //清除相应的缓存
                cache.delete(EmployeeController.CacheNameHelper.Receive_CacheNamePrefix + employee.getId());
                cache.delete(EmployeeController.CacheNameHelper.ListAll_CacheName);
                return Result.build(ResultType.Success);
            } else {
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }

    /**
     * Author: laizhouhao 18:52 2019/5/6
     *
     * @param user_id
     * @return response
     * @apiNote: 根据用户的id查询对应的职员主要信息
     */
    @ApiOperation(value = "根据用户的id查询对应的职员主要信息", notes = "2019年5月6日 18:53:21 已通过测试")
    @GetMapping("employeeByUserId/{user_id}")
    @ResponseBody
    public void selectByUserId(@PathVariable Long user_id, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = EmployeeController.CacheNameHelper.ListAll_CacheName + user_id;
        String json = cache.get(cacheName);
        if (json == null) {
            json = Result.build(ResultType.Success)
                    .appendData("employee", employeeService.selectByUserId(user_id)).convertIntoJSON();
            cache.set(cacheName, json);
        }
        response.getWriter().write(json);
    }

    /**
     * Author: laizhouhao 8:21 2019/5/9
     *
     * @param emp_no
     * @return response
     * @apiNote: 根据员工编号查询未离职员工的主要信息
     */
    @ApiOperation(value = "根据员工编号查询未离职员工的主要信息", notes = "未测试")
    @GetMapping("info/employeeWorkedMainInfo/All/{emp_no}")
    @ApiImplicitParam(name = "emp_no", value = "员工号emp_no", required = false, dataType = "String", paramType = "path")
    @ResponseBody
    public void receiveRelations(@PathVariable String emp_no, HttpServletResponse response) throws IOException {
        //检验页面传来的id是否存在
        if (emp_no != null) {
            UserInfo userInfo = new UserInfo();

            //根据员工编号查询user_id
            Long user_id = employeeService.selectEmployeeByEmpNo(emp_no).getUserId();
            //根据user_id查询员工主要信息
            List<User> us = new ArrayList<>();
            us.add(userService.selectUserById(user_id));

            //将查询出来的用户加入工具类对象储存
            userInfo.setUsers(us);

            //设置返回的数据格式
            response.setContentType("application/json;charset=utf-8");
            //拼接缓存键名（字符串）
            String cacheName = EmployeeController.CacheNameHelper.Receive_CacheNamePrefix + emp_no;
            //尝试在缓存中通过键名获取相应的键值
            //因为在Redis中，数据是以”“” "键-值"对 的形式储存的
            String json = cache.get(cacheName);
            //如果在缓存中找不到，那就从数据库里找
            if (json == null) {
                json = Result.build(ResultType.Success)
                        .appendData("userInfo", userInfo).convertIntoJSON();
                cache.set(cacheName, json);
            }
            //到最后通过response对象返回json格式字符串的数据
            response.getWriter().write(json);
        }
    }

    @ApiOperation(value = "根据职员的的id查找职员对应的行政岗位和学院", notes = "未测试")
    @GetMapping("info/employeeDetailInfo/department/{userId}")
    @ApiImplicitParam(name = "userId", value = "userId", required = false, dataType = "Long", paramType = "path")
    @ResponseBody
    public void selectDepartmentIdByUserId(@PathVariable Long userId, HttpServletResponse response) throws IOException {
        Employee employee = employeeService.selectByUserId(userId).get(0);
        Employ employ = otherEmployService.selectEmployByEmployeeId(employee.getUserId(), employee.getUniversityId());
        System.out.println(employ.getDepartmentId());
        //设置返回的数据格式
        response.setContentType("application/json;charset=utf-8");
        //拼接缓存键名（字符串）
        String cacheName = StudentController.CacheNameHelper.Receive_CacheNamePrefix + "selectDepartmentIdByUserId" + userId;
        //尝试在缓存中通过键名获取相应的键值
        //因为在Redis中，数据是以”“” "键-值"对 的形式储存的
        String json = cache.get(cacheName);
        //如果在缓存中找不到，那就从数据库里找
        if (json == null) {
            json = Result.build(ResultType.Success)
                    .appendData("employ", employ).convertIntoJSON();
            cache.set(cacheName, json);
        }
        //到最后通过response对象返回json格式字符串的数据
        response.getWriter().write(json);
    }

    /**
     * Author: chenenru 20:50 2019/5/9
     * @param requestMessage
     * @return Result
     * @apiNote: 申请修改职员主要信息, 点击申请时
     */
    /*@ApiOperation(value="申请修改职员主要信息", notes="2019年5月11日 14:33:14 已通过测试")
    @ApiImplicitParam(name = "requestMessage", value = "请求参数实体", required = true, dataType = "RequestMessage")
    @PostMapping("applyModifyEmployee/")
    @ResponseBody
    public Result ApplyModifyEmployee(@RequestBody RequestMessage requestMessage){
        //判断前端传过来的值是否为空
        if(requestMessage.getEmployee()!=null && requestMessage.getByWho()!=null && requestMessage.getUserinfoApply()!=null){
            boolean success = employeeService.clickApplyEmployee(requestMessage);
            if(success){
                //清除相应的缓存
                cache.delete(CacheNameHelper.Receive_CacheNamePrefix + "applyModifydEmployee");
                cache.delete(CacheNameHelper.ListAll_CacheName);
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }*/

    /**
     * Author: chenenru 11:24 2019/5/16
     *
     * @return
     * @apiNote: 根据userId查询employee表获取到employeeId,
     * 根据employeeId对class表查询employeeId==headteacher的所有班级，
     * 根据year进行排序，选择某一年级，得到这一年级的所带的所有班级名，选择班级名得到具体的班级的id,
     * 根据classmate表就可以查询某一个班级的所有学生
     */
    @ApiOperation(value = "班主任查看所带班级（主修专业）所有学生的所有信息", notes = "未测试")
    @GetMapping("employee/allClassmates")
    @ResponseBody
    public Result selectAllClassmatesByUserId(Integer year, String className, HttpServletResponse response) throws IOException {

        edu.uni.auth.bean.User loginUSer = authService.getUser();
        if (loginUSer == null)
            return Result.build(ResultType.Failed, "你还没登录");

        if (loginUSer.getUserType() != 2)
            return Result.build(ResultType.Failed, "你不是教职工用户");

        List<Employee> employees = employeeService.selectByUserId(loginUSer.getId());
        if (employees.size() == 0)
            return Result.build(ResultType.Failed, "你的教职工信息为空");

        List<Integer> roles = otherEmployPositionService.selectEmployeeRoleByUserId(employees.get(0));
        if (!roles.contains(1))
            return Result.build(ResultType.Failed, "你没有班主任的权限");

        UserInfo userInfo = new UserInfo();

        List<Class> classes = otherClassService.selectClassesByEmployeeId(employees.get(0).getId(), year, className);
        List<Student> students = new ArrayList<>();
        List<Classmate> classmates = new ArrayList<>();
        List<User> users = new ArrayList<>();
        for (Class cclass : classes) {
            classmates = otherClassmateService.selectByClassId(cclass.getId());
            for (Classmate classmate : classmates) {
                Student student = studentService.selectValidStudentByStuId(classmate.getStudentId());
                User user = userService.selectUserById(student.getUserId());
                students.add(student);
                users.add(user);
            }
        }
        userInfo.setClasses(classes);
        userInfo.setClassmates(classmates);
        userInfo.setStudents(students);
        userInfo.setUsers(users);
        return Result.build(ResultType.Success).appendData("userInfo", userInfo);
    }

    /*@ApiOperation(value = "这不是教师查询所授课班级学生信息(这个接口已经被抛弃)", notes = "未测试")
    @GetMapping("employee/class/allClassmates")
    @ResponseBody
    public Result selectStudentsByUserId(HttpServletResponse response) throws IOException {

        edu.uni.auth.bean.User loginUSer = authService.getUser();
        if (loginUSer == null)
            return Result.build(ResultType.Failed, "你还没登录");

        if (loginUSer.getUserType() != 2)
            return Result.build(ResultType.Failed, "你不是教职工用户");

        List<Employee> employees = employeeService.selectByUserId(loginUSer.getId());
        if (employees.size() == 0)
            return Result.build(ResultType.Failed, "你的教职工信息为空");

        List<Integer> roles = otherEmployPositionService.selectEmployeeRoleByUserId(employees.get(0));
        if (!roles.contains(0))
            return Result.build(ResultType.Failed, "你没有教师的权限");
        List<Long> longs = new ArrayList<>();
        UserInfo userInfo = new UserInfo();
        List<Class> classes = new ArrayList<>();
        longs.add(employees.get(0).getId());
        List<Curriculum> curricula = curriculumService.selectCurriculumByCondition(null, longs, null, null);
        List<Student> students = new ArrayList<>();
        List<Classmate> classmates = new ArrayList<>();
        List<User> users = new ArrayList<>();
        for (Curriculum c : curricula) {
            Class aClass = otherClassService.selectClassByClassId(c.getClassId());
            classes.add(aClass);
            List<Classmate> classmates1 = otherClassmateService.selectByClassId(aClass.getId());
            for (Classmate classmate : classmates1) {
                Student student = studentService.selectValidStudentByStuId(classmate.getStudentId());
                User user = userService.selectUserById(student.getUserId());
                students.add(student);
                users.add(user);
                classmates.add(classmate);
            }
        }
        userInfo.setClasses(classes);
        userInfo.setClassmates(classmates);
        userInfo.setStudents(students);
        userInfo.setUsers(users);
        return Result.build(ResultType.Success).appendData("userInfo", userInfo);
    }*/

    /**
     * Author: laizhouhao 18:52 2019/5/17
     *
     * @return response
     * @apiNote: 人事处职员获取其所在学校的所有学院的所有职员的信息
     */
    @ApiOperation(value = "人事处职员获取其所在学校的所有学院的所有职员的信息", notes = "2019年5月18日 15:11:40 已通过测试")
    @GetMapping("selectAllEmployees/listAll")
    @ResponseBody
    public void selectAllEmployees(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取session
        HttpSession session = request.getSession();
        //获取该职员的用户id
        Long user_id = (Long) session.getAttribute("user_id");
        List<Employee> employeeList = employeeService.selectValidEmployeeByUserId(Long.valueOf(0));
        response.setContentType("application/json;charset=utf-8");
        String cacheName = EmployeeController.CacheNameHelper.ListAll_CacheName;
        String json = cache.get(cacheName);
        //判断是否存在时这个用户id的职员
        if (employeeList.size() >= 1) {
            //判断该职员是否是人事处职员
            boolean isWorker = employeeService.checkEmployee(employeeList.get(0).getPositionId(), "人事处");
            if (isWorker) {
                //获取该职员所在的学校id
                Long university_id = employeeList.get(0).getUniversityId();
                //获取所在学校的所有职员信息
                List<Employee> employees = employeeService.selectValidEmployeeByUniId(university_id);
                System.out.println(employees);
                if (json == null) {
                    json = Result.build(ResultType.Success)
                            .appendData("employees", employees).convertIntoJSON();
                    cache.set(cacheName, json);
                }
                response.getWriter().write(json);
            }
        }
    }

    /**
     * Author: laizhouhao 18:52 2019/5/17
     *
     * @return response
     * @apiNote: 根据学院名、科室名、姓名/教工号查询职员信息
     */
    @ApiOperation(value = "根据学院名、科室名、姓名、教工号查询职员信息", notes = "未测试")
    @GetMapping("selectEmployeesByThreePosition/listAll")
    @ResponseBody
    public void selectEmployeeByThreePositions(String depart_name, String subdepart_name, String emp_name, String emp_no, HttpServletResponse response) throws IOException {
        UserInfo userInfo = new UserInfo();
        userInfo = employeeService.selectEmployeeByFourPosition(depart_name, subdepart_name, emp_name, emp_no);
        response.setContentType("application/json;charset=utf-8");
        String cacheName = EmployeeController.CacheNameHelper.ListAll_CacheName;
        String json = cache.get(cacheName);
        if (json == null) {
            json = Result.build(ResultType.Success)
                    .appendData("userInfo", userInfo).convertIntoJSON();
            cache.set(cacheName, json);
        }
        response.getWriter().write(json);
    }

    /**
     * Author: chenenru 12:51 2019/6/17
     *
     * @param
     * @return
     * @apiNote: //年级//所有专业//所有班级//所有岗位//政治面貌
     */
    @ApiOperation(value = "领导进行高级搜索班级学生的前戏", notes = "未测试")
    @GetMapping("filter/classmates/all")
    @ResponseBody
    public Result selectAllClassmatesToFilter(HttpServletResponse response) throws IOException {
        edu.uni.auth.bean.User loginUSer = authService.getUser();
        if (loginUSer == null)
            return Result.build(ResultType.Failed, "你还没登录");

        if (loginUSer.getUserType() != 2)
            return Result.build(ResultType.Failed, "你不是教职工用户");

        List<Employee> employees = employeeService.selectByUserId(loginUSer.getId());
        if (employees.size() == 0)
            return Result.build(ResultType.Failed, "你的教职工信息为空");

        List<Integer> roles = otherEmployPositionService.selectEmployeeRoleByUserId(employees.get(0));
        if (!roles.contains(2))
            return Result.build(ResultType.Failed, "你没有学院领导的权限");

        HashMap<String, Object> filtermessage = new HashMap<>();
        Set<String> className = new HashSet<>();
        Set<String> specialtyName = new HashSet<>();
        Set<String> cyaer = new HashSet<>();
        Set<String> political = new HashSet<>();
        Set<String> position = new HashSet<>();

        if (employees != null) {
            List<ClassmateBean> classmateBeans = employeeService.selectClassMateBeantByUserId(employees.get(0).getUserId());
            for (ClassmateBean c : classmateBeans) {
                if (c.getClassName() != null) {
                    className.add(c.getClassName());
                }
                if (c.getSpecialty() != null) {
                    specialtyName.add(c.getSpecialty());
                }
                if (c.getGrade() != null) {
                    cyaer.add(c.getGrade());
                }
                if (c.getPolitical() != null) {
                    political.add(c.getPolitical());
                }
                if (c.getPosition() != null) {
                    position.add(c.getPosition());
                }
            }
        }
        filtermessage.put("className", className);
        filtermessage.put("specialtyName", specialtyName);
        filtermessage.put("gradeName", cyaer);
        filtermessage.put("politicalName", political);
        filtermessage.put("positionName", position);
        return Result.build(ResultType.Success).appendData("filterMessage", filtermessage);


    }

    /**
     * Author: chenenru 12:50 2019/6/17
     *
     * @param
     * @return
     * @apiNote: 年级，专业，班级，学号，姓名（可以模糊）进行搜索 性别 政治面貌 岗位
     * //所有班级名称class，所有年级student,所有主修专业Specialty，性别user，姓名user，学号student，所有政治面貌politicalAffiliation，所有岗位Position
     */
    @ApiOperation(value = "领导查询某班的所有学生，进行高级搜索", notes = "未测试")
    @PostMapping("filter/allClassmates")
    @ResponseBody
    public Result selectAllClassmatesByFilter(@RequestParam(value = "classNames",required = false) List<String> classNames,
                                              @RequestParam(value = "cyears",required = false) List<String> cyears,
                                              @RequestParam(value = "specialtys",required = false) List<String> specialtys,
                                              String user_sex, String studentName, String studentNo,
                                              @RequestParam(value = "politicals",required = false) List<String> politicals,
                                              @RequestParam(value = "positions",required = false) List<String> positions, HttpServletResponse response) throws IOException {
        edu.uni.auth.bean.User loginUSer = authService.getUser();
        if (loginUSer == null)
            return Result.build(ResultType.Failed, "你还没登录");

        if (loginUSer.getUserType() != 2)
            return Result.build(ResultType.Failed, "你不是教职工用户");

        List<Employee> employees = employeeService.selectByUserId(loginUSer.getId());
        if (employees.size() == 0)
            return Result.build(ResultType.Failed, "你的教职工信息为空");

        List<Integer> roles = otherEmployPositionService.selectEmployeeRoleByUserId(employees.get(0));
        if (!roles.contains(2))
            return Result.build(ResultType.Failed, "你没有学院领导的权限");

        System.out.println("controller层：");
        System.out.println("传过来的classNames："+classNames);
        System.out.println("传过来的cyears："+cyears);
        System.out.println("传过来的specialtys："+specialtys);
        System.out.println("传过来的user_sex："+user_sex);
        System.out.println("传过来的studentName："+studentName);
        System.out.println("传过来的studentNo："+studentNo);
        System.out.println("传过来的politicals："+politicals);
        System.out.println("传过来的positions："+positions);

        int flag = 0;
        Integer sex = null;
        List<ClassmateBean> classmateBeans = new ArrayList<>();
        if (employees != null) {
            if (studentName!=null)
                studentName = "%"+studentName+"%";
            if (studentNo!=null)
                studentNo = "%"+studentNo+"%";
            if (user_sex!=null){
                if (user_sex.equals("男")){
                    sex = 1;
                }
                if (user_sex.equals("女")){
                    sex = 0;
                }
            }
            classmateBeans = employeeService.selectClassmateBeanByFilter(employees.get(0).getUserId(),classNames,cyears,
                    specialtys,sex,studentName, studentNo, politicals, positions);
            for (int i=0;i<classmateBeans.size();i++){
                if (classmateBeans.get(i).getSex()!=null){
                    if (classmateBeans.get(i).getSex().equals(String.valueOf(1))){
                        classmateBeans.get(i).setSex("男");
                    }
                    if (classmateBeans.get(i).getSex().equals(String.valueOf(0))){
                        classmateBeans.get(i).setSex("女");
                    }
                }
            }
        }
        return Result.build(ResultType.Success).appendData("classmateBeans", classmateBeans);
    }

    /**
     * Author: chenenru 12:49 2019/6/17
     *
     * @param
     * @return
     * @apiNote: 还有关于搜索进行筛选的内容：可以通过学院，科室，岗位，姓名（可以模糊）进行搜索
     */
    @ApiOperation(value = "人事处进行高级搜索校内所有职员的信息的前戏", notes = "未测试")
    @GetMapping("filter/employees/all")
    @ResponseBody
    public Result selectAllEmployeesToFilter(HttpServletResponse response) throws IOException {

        edu.uni.auth.bean.User loginUSer = authService.getUser();
        if (loginUSer == null)
            return Result.build(ResultType.Failed, "你还没登录");

        if (loginUSer.getUserType() != 2)
            return Result.build(ResultType.Failed, "你不是教职工用户");

        List<Employee> employees = employeeService.selectByUserId(loginUSer.getId());
        if (employees.size() == 0)
            return Result.build(ResultType.Failed, "你的教职工信息为空");

        List<Integer> roles = otherEmployPositionService.selectEmployeeRoleByUserId(employees.get(0));
        if (!roles.contains(3))
            return Result.build(ResultType.Failed, "你没有人事处的权限");

        HashMap<String, Object> filtermessage = new HashMap<>();
        Set<String> departmentName = new HashSet<>();
        Set<String> subDepartmentName = new HashSet<>();
        Set<String> positionName = new HashSet<>();
        List<EmployeeBean> employeeBeans = new ArrayList<>();
        if (employees.size() > 0) {
            employeeBeans = employeeService.selectEmployeeBeanByUniId(employees.get(0).getUniversityId());
            for (EmployeeBean e : employeeBeans) {
                if (e.getDepartmentName() != null) {
                    departmentName.add(e.getDepartmentName());
                }
                if (e.getSubDepartmentName() != null) {
                    subDepartmentName.add(e.getSubDepartmentName());
                }
                if (e.getPositionName() != null) {
                    positionName.add(e.getPositionName());
                }
            }
        }
        filtermessage.put("departmentName", departmentName);
        filtermessage.put("subDepartmentName", subDepartmentName);
        filtermessage.put("positionName", positionName);
        return Result.build(ResultType.Success).appendData("filtermessage", filtermessage);
    }

    /**
     * Author: chenenru 16:45 2019/6/17
     *
     * @param
     * @return
     * @apiNote: 列名从左到右 : 员工编号，照片，姓名，学校，当前所属学院，当前所在科室，行政岗位，按钮（详细信息）
     * 还有关于搜索进行筛选的内容：可以通过学院，科室，岗位，姓名（可以模糊）进行搜索 @RequestParam("departmentNames[]")
     */
    @ApiOperation(value = "人事处查询校内所有职员的信息，进行高级搜索", notes = "未测试")
    /*@ApiImplicitParams({
            @ApiImplicitParam(name = "departmentNames", required = false, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "subDepartmentNames", required = false, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "positionNames", required = false, dataType = "String", paramType = "path")
    }
    )*/
    @PostMapping("filter/allemployees")
    @ResponseBody
    public Result selectAllClassmatesByFilter(String employeeName, @RequestParam(value = "departmentNames",required = false) List<String> departmentNames,
                                              @RequestParam(value = "subDepartmentNames",required = false) List<String> subDepartmentNames,
                                              @RequestParam(value = "positionNames",required = false) List<String> positionNames, HttpServletResponse response) throws IOException {

        edu.uni.auth.bean.User loginUSer = authService.getUser();
        if (loginUSer == null)
            return Result.build(ResultType.Failed, "你还没登录");

        if (loginUSer.getUserType() != 2)
            return Result.build(ResultType.Failed, "你不是教职工用户");

        List<Employee> employees = employeeService.selectByUserId(loginUSer.getId());
        if (employees.size() == 0)
            return Result.build(ResultType.Failed, "你的教职工信息为空");

        List<Integer> roles = otherEmployPositionService.selectEmployeeRoleByUserId(employees.get(0));
        if (!roles.contains(3))
            return Result.build(ResultType.Failed, "你没有人事处的权限");
        System.out.println("controller层：");
        System.out.println("传过来的employeeName："+employeeName);
        System.out.println("传过来的学院名："+departmentNames);
        System.out.println("传过来的科室名："+subDepartmentNames);
        System.out.println("传过来的岗位名："+positionNames);

        int flag = 0;
        List<EmployeeBean> employeeBeans = new ArrayList<>();
        if (employees.size() > 0) {
            System.out.println("学校的id：" + employees.get(0).getUniversityId());
            if (employeeName!=null){
                employeeName = "%"+employeeName+"%";
            }
            employeeBeans = employeeService.selectEmployeeBeanByAllFilter(employees.get(0).getUniversityId(), employeeName,
                    departmentNames, subDepartmentNames, positionNames);
            System.out.println("教职员的数量："+employeeBeans.size());
        }
        return Result.build(ResultType.Success).appendData("employeeBean", employeeBeans);
    }

    /**
     * Author: chenenru 20:50 2019/5/9
     * @param userinfoApplyApproval, user_id
     * @return Result
     * @apiNote: 审批修改职员主要信息的申请, 点击通过时
     */
   /* @ApiOperation(value="审批修改职员主要信息的申请, 点击通过时", notes="未测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userinfoApplyApproval", value = "用户申请审批流程表实体", required = true, dataType = "UserinfoApplyApproval"),
            @ApiImplicitParam(name = "user_id", value = "审批人id", required = true, dataType = "Long", paramType = "path")
    })
    @PostMapping("commituserinfoApply/{user_id}")
    @ResponseBody
    public Result commitApplyModifyEmployee(@RequestBody UserinfoApplyApproval userinfoApplyApproval,@PathVariable Long user_id){
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
                    cache.delete(CacheNameHelper.Receive_CacheNamePrefix + "applyModifydEmployee");
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
                    cache.delete(CacheNameHelper.Receive_CacheNamePrefix + "applyModifydEmployee");
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
     * @apiNote: 审批修改职员主要信息的申请, 点击不通过时
     *//*
    @ApiOperation(value="审批修改职员主要信息的申请, 点击不通过时", notes="未测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userinfoApplyApproval", value = "用户申请审批流程表实体", required = true, dataType = "UserinfoApplyApproval"),
            @ApiImplicitParam(name = "user_id", value = "审批人id", required = true, dataType = "Long", paramType = "path")
    })
    @PostMapping(value = "refuseuserinfoApply/{user_id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Result refuseApplyModifyEmployee(@RequestBody UserinfoApplyApproval userinfoApplyApproval, @PathVariable Long user_id) throws IOException {
        System.out.println("小莫是头猪！！！---");
        if(userinfoApplyApproval != null && user_id != null){
            boolean success = userService.endForRefuse(userinfoApplyApproval, user_id);
            if(success) {
                //清除相应的缓存
                cache.delete(CacheNameHelper.Receive_CacheNamePrefix + "applyModifydEmployee");
                cache.delete(CacheNameHelper.ListAll_CacheName);
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }*/


}
