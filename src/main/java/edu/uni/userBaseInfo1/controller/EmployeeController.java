package edu.uni.userBaseInfo1.controller;

import edu.uni.administrativestructure.bean.*;
import edu.uni.administrativestructure.bean.Class;
import edu.uni.administrativestructure.service.PositionService;
import edu.uni.educateAffair.bean.Curriculum;
import edu.uni.educateAffair.service.CurriculumService;
import edu.uni.professionalcourses.bean.Specialty;
import edu.uni.professionalcourses.service.SpecialtyService;
import edu.uni.userBaseInfo1.PageBean.ClassmateBean;
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
import io.swagger.annotations.ApiOperation;
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

    @Autowired  //把缓存工具类RedisCache相应的方法自动装配到该对象
    private RedisCache cache;

    //内部类，专门用来管理每个get方法所对应缓存的名称。
    static class CacheNameHelper{
        // ub1_e_Employee_{职员记录id}
        public static final String Receive_CacheNamePrefix = "ub1_e_employee_";
        // ub1_e_Employees_listAll
        public static final String ListAll_CacheName = "ub1_e_employee_listAll";
    }

    /**
     * Author: chenenru 23:41 2019/4/29
     * @param id response
     * @return response
     * @apiNote: 获取职员详情
     */
    //以下说明为本类中所有方法的注解的解释，仅在本处注释（因为都几乎是一个模版）
    //@ApiOperation：用于在swagger2页面显示方法的提示信息
    //@GetMapping：规定方法的请求路径和方法的请求方式（Get方法）
    //@ApiImplicitParam：用于在swagger2页面测试时用于测试的变量，详细解释可以看Swagger2注解说明
    //@ResponseBody：指明该方法效果等同于通过response对象输出指定格式的数据（JSON）
    @ApiOperation( value = "以一个id获取一条职员记录详情",notes = "2019-5-2 11:05:35已通过测试" )
    @GetMapping("employee/{id}")
    @ApiImplicitParam(name = "id", value = "Employee表的一个id", required = false, dataType = "Long" , paramType = "path")
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
        if(json == null){
            Employee employee = employeeService.selectEmployeeById(id);
            //把查询到的结果用Result工具类转换成json格式的字符串
            json = Result.build(ResultType.Success).appendData("employee",employee).convertIntoJSON();
            //如果有查询到数据，就把在数据库查到的数据放到缓存中
            if(employee != null){
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
     * @apiNote: 获取所有职员记录的内容
     */
    @ApiOperation( value = "获取所有职员记录的内容",notes = "2019-5-2 11:05:41已通过测试" )
    @GetMapping("employees/listAll")
    @ResponseBody
    public void selectAll(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = EmployeeController.CacheNameHelper.ListAll_CacheName;
        String json = cache.get(cacheName);
        if(json == null){
            json = Result.build(ResultType.Success)
                    .appendData("employees",employeeService.selectAllEmployees()).convertIntoJSON();
            cache.set(cacheName,json);
        }
        response.getWriter().write(json);
    }
    /**
     * Author: chenenru 23:47 2019/4/29
     * @param  employee
     * @return Result
     * @apiNote: 新增职员信息
     */
    @ApiOperation(value="新增职员信息", notes="2019-5-2 11:05:46已通过测试")
    @ApiImplicitParam(name = "employee", value = "职员详情实体", required = true, dataType = "Employee")
    @PostMapping("/employee")  //post请求方式
    @ResponseBody
    public Result create(@RequestBody(required = false) Employee employee){
        //检验页面传来的对象是否存在
        if(employee != null){
            boolean success = employeeService.insertEmployee(employee);
            if(success){
                // 清空相关缓存
                cache.delete(EmployeeController.CacheNameHelper.ListAll_CacheName);
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
     * @apiNote: 删除职员
     */
    @ApiOperation(value="删除职员", notes="2019-5-2 11:05:51已通过测试")
    @ApiImplicitParam(name = "id", value = "职员的id", required = true, dataType = "Long", paramType = "path")
    @DeleteMapping("/employee/{id}")   //delete请求
    @ResponseBody
    public Result destroy(@PathVariable Long id){
        boolean success = employeeService.deleteEmployee(id);
        if(success){
            // 清空相关缓存
            cache.delete(EmployeeController.CacheNameHelper.ListAll_CacheName);
            return Result.build(ResultType.Success);
        }else{
            return Result.build(ResultType.Failed);
        }
    }
    /**
     * Author: chenenru 23:52 2019/4/29
     * @param employee
     * @return Result
     * @apiNote: 更新职员详情
     */
    @ApiOperation(value="更新职员详情", notes="2019-5-2 11:05:55已通过测试")
    @ApiImplicitParam(name = "employee", value = "职员详情实体", required = true, dataType = "employee")
    @PutMapping("/employee")   //Put请求
    @ResponseBody
    public Result update(@RequestBody(required = false) Employee employee){
        if(employee != null && employee.getId() != null){
            boolean success = employeeService.updateEmployee(employee);
            if(success){
                //清除相应的缓存
                cache.delete(EmployeeController.CacheNameHelper.Receive_CacheNamePrefix + employee.getId());
                cache.delete(EmployeeController.CacheNameHelper.ListAll_CacheName);
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }

    /**
     * Author: laizhouhao 18:52 2019/5/6
     * @param user_id
     * @return response
     * @apiNote: 根据用户的id查询对应的职员主要信息
     */
    @ApiOperation( value = "根据用户的id查询对应的职员主要信息",notes = "2019年5月6日 18:53:21 已通过测试" )
    @GetMapping("employeeByUserId/{user_id}")
    @ResponseBody
    public void selectByUserId(@PathVariable Long user_id,HttpServletResponse response) throws IOException{
        response.setContentType("application/json;charset=utf-8");
        String cacheName = EmployeeController.CacheNameHelper.ListAll_CacheName+user_id;
        String json = cache.get(cacheName);
        if(json == null){
            json = Result.build(ResultType.Success)
                    .appendData("employee",employeeService.selectByUserId(user_id)).convertIntoJSON();
            cache.set(cacheName,json);
        }
        response.getWriter().write(json);
    }

    /**
     * Author: laizhouhao 8:21 2019/5/9
     * @param emp_no
     * @return response
     * @apiNote: 根据员工编号查询未离职员工的主要信息
     */
    @ApiOperation( value = "根据员工编号查询未离职员工的主要信息",notes = "未测试" )
    @GetMapping("info/employeeWorkedMainInfo/All/{emp_no}")
    @ApiImplicitParam(name = "emp_no", value = "员工号emp_no", required = false, dataType = "String" , paramType = "path")
    @ResponseBody
    public void receiveRelations(@PathVariable String emp_no, HttpServletResponse response) throws IOException {
        //检验页面传来的id是否存在
        if(emp_no != null){
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
            if(json == null){
                json = Result.build(ResultType.Success)
                        .appendData("userInfo",userInfo).convertIntoJSON();
                cache.set(cacheName,json);
            }
            //到最后通过response对象返回json格式字符串的数据
            response.getWriter().write(json);
        }
    }

    @ApiOperation( value = "根据职员的的id查找职员对应的行政岗位和学院",notes = "未测试" )
    @GetMapping("info/employeeDetailInfo/department/{userId}")
    @ApiImplicitParam(name = "userId", value = "userId", required = false, dataType = "Long" , paramType = "path")
    @ResponseBody
    public void selectDepartmentIdByUserId(@PathVariable Long userId,HttpServletResponse response) throws IOException{
        Employee employee = employeeService.selectByUserId(userId).get(0);
        Employ employ = otherEmployService.selectEmployByEmployeeId(employee.getUserId() , employee.getUniversityId());
        System.out.println(employ.getDepartmentId());
        //设置返回的数据格式
        response.setContentType("application/json;charset=utf-8");
        //拼接缓存键名（字符串）
        String cacheName = StudentController.CacheNameHelper.Receive_CacheNamePrefix +"selectDepartmentIdByUserId"+ userId;
        //尝试在缓存中通过键名获取相应的键值
        //因为在Redis中，数据是以”“” "键-值"对 的形式储存的
        String json = cache.get(cacheName);
        //如果在缓存中找不到，那就从数据库里找
        if(json == null){
            json = Result.build(ResultType.Success)
                    .appendData("employ",employ).convertIntoJSON();
            cache.set(cacheName,json);
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
    @ApiOperation(value="申请修改职员主要信息", notes="2019年5月11日 14:33:14 已通过测试")
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

    /**
     * Author: chenenru 11:24 2019/5/16
     * @param userId
     * @return
     * @apiNote: 根据userId查询employee表获取到employeeId,
     * 根据employeeId对class表查询employeeId==headteacher的所有班级，
     * 根据year进行排序，选择某一年级，得到这一年级的所带的所有班级名，选择班级名得到具体的班级的id,
     * 根据classmate表就可以查询某一个班级的所有学生
     */
    @ApiOperation(value="查看所带班级（主修专业）所有学生的所有信息", notes="未测试")
    @ApiImplicitParam(name = "userId", value = "userId", required = false, dataType = "Long" , paramType = "path")
    @GetMapping("employee/allClassmates/{userId}")
    @ResponseBody
    public void selectAllClassmatesByUserId(@PathVariable Long userId,Integer year,String className,HttpServletResponse response) throws  IOException{
        String cacheName = CacheNameHelper.ListAll_CacheName+"employee"+userId+year+className;
        String json = cache.get(cacheName);
        if(json ==null){
            if(userId!=null){
                UserInfo userInfo = new UserInfo();
                Employee employee = employeeService.selectByUserId(userId).get(0);
                //userInfo.setEmployees(employee);
                System.out.println(" ."+year+". ."+className+".");
                List<Class> classes = otherClassService.selectClassesByEmployeeId(employee.getId(),year,className);
                List<Student> students = new ArrayList<>();
                List<Classmate> classmates = new ArrayList<>();
                List<User> users = new ArrayList<>();
                for (Class cclass:classes) {
                        classmates = otherClassmateService.selectByClassId(cclass.getId());
                        for (Classmate classmate : classmates) {
                            Student student = studentService.selectValidStudentByStuId(classmate.getStudentId());
                            User user = userService.selectUserById(student.getUserId());
                            students.add(student);
                            users.add(user);
                        }
                        System.out.println("class:" + cclass.getId());
                        System.out.println("classmates:"+classmates.iterator().toString());
                        System.out.println("students:"+students.iterator().toString());
                }
               userInfo.setClasses(classes);
               userInfo.setClassmates(classmates);
               userInfo.setStudents(students);
               userInfo.setUsers(users);
               json = Result.build(ResultType.Success).appendData("userInfo", userInfo).convertIntoJSON();
               cache.set(cacheName,json);
            }
        }
        response.getWriter().write(json);

    }

    @ApiOperation(value="查询所授课班级学生信息", notes="未测试")
    @ApiImplicitParam(name = "userId", value = "userId", required = false, dataType = "Long" , paramType = "path")
    @GetMapping("employee/class/allClassmates/{userId}")
    @ResponseBody
    public  void selectStudentsByUserId(@PathVariable Long userId,HttpServletResponse response) throws IOException {

        String cacheName = CacheNameHelper.ListAll_CacheName+"class"+"employee"+userId;
        //String json = cache.get(cacheName);
        String json = null;
        if(json ==null){
            if (userId!=null){
                Employee employee = employeeService.selectByUserId(userId).get(0);
                List<Long> longs = new ArrayList<>();
                UserInfo userInfo = new UserInfo();
                List<Class> classes = new ArrayList<>();
                longs.add(employee.getId());
                List<Curriculum> curricula = curriculumService.selectCurriculumByCondition(null, longs, null, null);
                System.out.println("curricula---->"+curricula);
                List<Student> students = new ArrayList<>();
                List<Classmate> classmates = new ArrayList<>();
                List<User> users = new ArrayList<>();
                for (Curriculum c:curricula) {
                    Class aClass = otherClassService.selectClassByClassId(c.getClassId());
                    classes.add(aClass);
                    List<Classmate> classmates1 = otherClassmateService.selectByClassId(aClass.getId());
                    System.out.println("classmate---->"+classmates1);
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
                json = Result.build(ResultType.Success).appendData("userInfo", userInfo).convertIntoJSON();
                cache.set(cacheName,json);
            }
        }
        response.getWriter().write(json);
    }

    /**
     * Author: laizhouhao 18:52 2019/5/17
     * @return response
     * @apiNote: 人事处职员获取其所在学校的所有学院的所有职员的信息
     */
    @ApiOperation( value = "人事处职员获取其所在学校的所有学院的所有职员的信息",notes = "2019年5月18日 15:11:40 已通过测试" )
    @GetMapping("selectAllEmployees/listAll")
    @ResponseBody
    public void selectAllEmployees(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //获取session
        HttpSession session = request.getSession();
        //获取该职员的用户id
        Long user_id = (Long) session.getAttribute("user_id");
        List<Employee> employeeList = employeeService.selectValidEmployeeByUserId(Long.valueOf(0));
        response.setContentType("application/json;charset=utf-8");
        String cacheName = EmployeeController.CacheNameHelper.ListAll_CacheName;
        String json = cache.get(cacheName);
        //判断是否存在时这个用户id的职员
        if(employeeList.size()>=1){
            //判断该职员是否是人事处职员
            boolean isWorker = employeeService.checkEmployee(employeeList.get(0).getPositionId(), "人事处");
            if(isWorker){
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
     * @return response
     * @apiNote: 根据学院名、科室名、姓名/教工号查询职员信息
     */
    @ApiOperation( value = "根据学院名、科室名、姓名、教工号查询职员信息",notes = "未测试" )
    @GetMapping("selectEmployeesByThreePosition/listAll")
    @ResponseBody
    public void selectEmployeeByThreePositions(String depart_name, String subdepart_name,String emp_name, String emp_no,HttpServletResponse response) throws IOException {
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

    //年级
    //所有专业
    //所有班级
    //所有岗位
    //政治面貌
    @ApiOperation(value = "进行高级搜索的前戏", notes = "未测试")
    @GetMapping("/filter/all")
    @ResponseBody
    public void selectAllToFilter(Long userId, HttpServletResponse response) throws IOException {
        //System.out.println(new Date());
        response.setContentType("application/json;charset=utf-8");
        String json = null;
        HashMap<String,Object> filtermessage = new HashMap<>();
        List<Employee> employees = employeeService.selectByUserId(userId);
        if (employees != null) {
            Employ employ = otherEmployService.selectEmployByEmployeeId(employees.get(0).getId(), employees.get(0).getUniversityId());
            List<Department> departments = otherDepartmentService.selectValidById(employ.getDepartmentId());
            if (departments != null) {
                List<String> className = new ArrayList<>();
                Set<String> specialtyName = new HashSet<>();
                Set<String> cyaer = new HashSet<>();
                Set<String> political = new HashSet<>();
                Set<String> position = new HashSet<>();
                //查询所有的班级class、专业Specialty、年级student、岗位Position 和 政治面貌politicalAffiliation
                List<Class> classes = otherClassService.selectAllClassByDepartmentId(departments.get(0).getId());
                for (Class c:classes) {
                    //所有班级
                    className.add(c.getName());
                    //所有专业
                    Specialty select = specialtyService.select(c.getSpecialtyId());
                    specialtyName.add(select.getName());
                    //所有年级
                    cyaer.add(String.valueOf(c.getCyear()));
                    //所有岗位
                    List<Classmate> classmates = otherClassmateService.selectByClassId(c.getId());
                    for (Classmate cm : classmates) {
                        Student student = studentService.selectValidStudentByStuId(cm.getStudentId());
                        List<ClassmatePosition> classmatePositions = otherClassmatePositionService.selectclassmatePositionByClassmateId(cm.getId());
                        if (classmatePositions != null) {
                            //List<Position> positions = positionService.selectAll();
                            //for (Position p : positions) {
                                for (ClassmatePosition cp : classmatePositions) {
                                    //if (p.getId().equals(cp.getPositionId())) {
                                        Position position1 = positionService.select(cp.getPositionId());
                                        if (position1!=null){
                                            position.add(position1.getName());
                                        }
                                    //}
                                }
                            //}
                        }
                        //所有政治面貌
                        PoliticalAffiliation politicalAffiliation = politicalAffiliationService.selectPoliticalAffiliationById(student.getPoliticalId());
                        political.add(politicalAffiliation.getPolitical());
                    }
                }
                filtermessage.put("className", className);
                filtermessage.put("specialtyName", specialtyName);
                filtermessage.put("gradeName",cyaer);
                filtermessage.put("politicalName", political);
                filtermessage.put("positionName", position);

            }
        }
        //System.out.println(new Date());
        //System.out.println(filtermessage);
        json = Result.build(ResultType.Success).appendData("filterMessage", filtermessage).convertIntoJSON();
        response.getWriter().write(json);

    }

    //所有班级名称class，所有年级student,所有主修专业Specialty，性别user，姓名user，学号student，所有政治面貌politicalAffiliation，所有岗位Position
    @ApiOperation(value = "领导查询某班的所有学生，可进行高级搜索(代码垃圾，不要看了)", notes = "未测试")
    @GetMapping("student/allClassmates/{userId}")
    @ResponseBody
    /*public void selectClassesByClassId(@PathVariable Long userId,@RequestParam(value ="className[]") List<String> className,
                                       @RequestParam(value ="cyear[]") List<String> cyear, @RequestParam(value ="specialty[]") List<String> specialty, String user_sex, String studentName,String studentNo,
                                       @RequestParam(value ="political[]") List<String> political, @RequestParam(value ="position[]") List<String> position, HttpServletResponse response) throws IOException {*/
    public void selectClassesByClassId(@PathVariable Long userId,String[] classNames,
                                       String[] cyears, String[] specialtys, String user_sex, String studentName,String studentNo,
                                       String[] politicals, String[] positions, HttpServletResponse response) throws IOException {
        //System.out.println(new Date());
        response.setContentType("application/json;charset=utf-8");
        /*System.out.println(userId+" "+classNames+" "+cyears+" "+specialtys+" "+user_sex
        +" "+studentName+" "+studentNo+" "+politicals+" "+positions);*/
        int flag = 0;
        String json = null;
        List<ClassmateBean> classmateBeans = new ArrayList<>();
        //该领导为自己学院的领导
        List<Employee> employees = employeeService.selectByUserId(userId);
        if (employees != null) {

            Employ employ = otherEmployService.selectEmployByEmployeeId(employees.get(0).getId(), employees.get(0).getUniversityId());
            List<Department> departments = otherDepartmentService.selectValidById(employ.getDepartmentId());
            if (departments != null) {
                List<Class> classes = otherClassService.selectAllClassByDepartmentId(departments.get(0).getId());
                if (classes != null) {
                    flag = 0;
                    for (Class c : classes) {
                        if (classNames != null && !classNames.equals("")) {
                            for (int i=0;i<classNames.length;i++){
                                if (!classNames[i].equals(c.getName())) {
                                    continue;
                                }else{
                                    flag = 1;
                                    break;
                                }
                            }
                            if (flag!=1){
                                continue;
                            }
                        }
                        List<Classmate> classmates = otherClassmateService.selectByClassId(c.getId());
                        if (classmates != null && classmates.size() > 0) {
                            for (Classmate cm : classmates) {
                                Student student = studentService.selectValidStudentByStuId(cm.getStudentId());
                                User user = userService.selectUserById(student.getUserId());
                                List<ClassmatePosition> classmatePositions = otherClassmatePositionService.selectclassmatePositionByClassmateId(cm.getId());
                                ClassmateBean classmateBean = new ClassmateBean();
                                //学生的用户id
                                classmateBean.setUserId(user.getId());
                                //学生id
                                classmateBean.setStudentId(student.getId());
                                //学号
                                classmateBean.setStudentNo(student.getStuNo());
                                //姓名
                                classmateBean.setStudentName(user.getUserName());
                                //入学日期
                                classmateBean.setBeginLearnDate(student.getBeginLearnDate());
                                //主修专业
                                Specialty select = specialtyService.select(student.getSpecialtyId());
                                classmateBean.setSpecialty(select.getName());
                                //所在年级
                                classmateBean.setGrade(student.getGrade());
                                //所在的班级
                                classmateBean.setClassName(c.getName());
                                //性别  0:女 1:男 2：不详
                                if (user.getUserSex().equals(0)) {
                                    classmateBean.setSex("女");
                                } else {
                                    classmateBean.setSex("男");
                                }
                                //}
                                //联系方式
                                Ecomm ecomm = ecommService.selectById(student.getPhoneEcommId());
                                classmateBean.setPhone(ecomm.getContent());
                                //政治面貌
                                PoliticalAffiliation politicalAffiliation = politicalAffiliationService.selectPoliticalAffiliationById(student.getPoliticalId());
                                classmateBean.setPolitical(politicalAffiliation.getPolitical());
                                //}
                                //岗位
                                if (classmatePositions != null) {
                                    //List<Position> positions = positionService.selectAll();
                                    StringBuffer positionName = new StringBuffer();
                                    //for (Position p : positions) {
                                        for (ClassmatePosition cp : classmatePositions) {
                                            Position position = positionService.select(cp.getPositionId());
                                            /*if (p.getId().equals(cp.getPositionId())) {
                                                positionName.append(p.getName());
                                            }*/
                                            if (position!=null){
                                                positionName.append(position.getName());
                                            }
                                        }
                                    //}
                                    classmateBean.setPosition(String.valueOf(positionName));
                                }
                                //开启判断
                                //String className, String cyear,
                                // String specialty, String user_sex,
                                // String studentName, String studentNo,
                                // String political, String position,
                                //班级判断在前面
                                //所有年级
                                //所有专业
                                //所有班级
                                //所有岗位
                                //所有政治面貌
                                /*if (classNames!=null){
                                    for (int i=0;i<classNames.length;i++){
                                        System.out.println("班级："+classNames[i]+" "+classmateBean.getClass().getName());
                                        if (!classmateBean.getClass().getName().equals(classNames[i])){
                                            continue;
                                        }else{
                                            flag = 1;
                                            break;
                                        }
                                    }
                                    if (flag!=1){
                                        continue;
                                    }
                                }*/
                                flag=0;
                                if (cyears!=null&&!cyears.equals("")){
                                    for (int i=0;i<cyears.length;i++){
                                        System.out.println("年级："+cyears[i]+" "+classmateBean.getGrade());
                                        if (!classmateBean.getGrade().equals(cyears[i])){
                                            continue;
                                        }else{
                                            flag = 1;
                                            break;
                                        }
                                    }
                                    if (flag!=1){
                                        continue;
                                    }
                                }
                                flag=0;
                                if (specialtys!=null&&!specialtys.equals("")){
                                    for (int i=0;i<specialtys.length;i++){
                                        System.out.println("专业："+specialtys[i]+" "+classmateBean.getSpecialty());
                                        if (!specialtys[i].equals(classmateBean.getSpecialty())){
                                            continue;
                                        }else{
                                            flag = 1;
                                            break;
                                        }
                                    }
                                    if (flag!=1){
                                        continue;
                                    }
                                }
                                if (user_sex!=null&&!user_sex.equals("")){
                                    if (!user_sex.equals(classmateBean.getSex())){
                                        continue;
                                    }
                                }
                                if (studentName!=null&&!studentName.equals("")){
                                    if (!studentName.equals(classmateBean.getStudentName())){
                                        continue;
                                    }
                                }
                                if (studentNo!=null&&!studentNo.equals("")){
                                    if (!studentNo.equals(classmateBean.getStudentNo())){
                                        continue;
                                    }
                                }
                                flag=0;
                                if (politicals!=null&&!politicals.equals("")){
                                    for (int i=0;i<politicals.length;i++){
                                        System.out.println("政治面貌："+politicals[i]+" "+classmateBean.getPosition());
                                        if (!politicals[i].equals(classmateBean.getPolitical())){
                                            continue;
                                        }else{
                                            flag = 1;
                                            break;
                                        }
                                    }
                                    if (flag!=1){
                                        continue;
                                    }
                                }
                                flag=0;
                                //System.out.println(positions.length);
                                if (positions!=null&&!positions.equals("")){
                                    if (classmateBean.getPosition()!=null){
                                        for (int i=0;i<positions.length;i++){
                                            System.out.println("岗位："+positions[i]+" "+classmateBean.getPosition());
                                            int indexOf = classmateBean.getPosition().indexOf(positions[i]);
                                            System.out.println("indexof："+indexOf);
                                            /*if (!(indexOf<=2&&indexOf>=0)){
                                                continue;
                                            }else {
                                                flag=1;
                                                break;
                                            }*/
                                            if (indexOf<=2&&indexOf>=0){
                                                flag=1;
                                                break;
                                            }
                                        }
                                    }else{
                                        continue;
                                    }
                                }
                                if (flag!=1&&positions!=null){
                                    continue;
                                }
                                //System.out.println("flag不为1时的indexof不为2时：不应该运行到这里");
                                classmateBeans.add(classmateBean);
                            }
                        }
                    }
                }
            }
        }
        //System.out.println(new Date());
        //System.out.println(classmateBeans);
        json = Result.build(ResultType.Success).appendData("classmateBeans", classmateBeans).convertIntoJSON();
        response.getWriter().write(json);
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
