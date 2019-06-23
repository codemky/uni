package edu.uni.userBaseInfo1.controller;

import edu.uni.administrativestructure.bean.Class;
import edu.uni.administrativestructure.bean.Classmate;
import edu.uni.administrativestructure.bean.ClassmatePosition;
import edu.uni.administrativestructure.service.ClassmatePositionService;
import edu.uni.auth.service.AuthService;
import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.educateAffair.bean.Curriculum;
import edu.uni.educateAffair.service.CurriculumService;
import edu.uni.userBaseInfo1.PageBean.ClassBean;
import edu.uni.userBaseInfo1.bean.Employee;
import edu.uni.userBaseInfo1.bean.Student;
import edu.uni.userBaseInfo1.bean.User;
import edu.uni.userBaseInfo1.service.*;
import edu.uni.userBaseInfo1.utils.UserInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @Author chenenru
 * @ClassName OtherClassController
 * @Description
 * @Date 2019/6/3 18:47
 * @Version 1.0
 **/
//填写description内容可以在测试模块显示相应的文字和模块
@Api(description = "莫宽元组的班级模块")
//Controller类（或者说Http）的请求路径
//如果添加了路径，则在需要调用该类的方法时需要在方法请求mapping路径前加上类的mapping路径
@RequestMapping("json/class")
//标志这个类是一个controller类，用于被Spring扫描然后配置添加和配置相应的bean
@Controller
public class OtherClassController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private CurriculumService curriculumService;
    @Autowired
    private OtherClassService otherClassService;
    @Autowired
    private OtherClassmateService otherClassmateService;
    @Autowired
    private UserService userService;
    @Autowired
    private OtherClassmatePositionService otherClassmatePositionService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private AuthService authService;
    @Autowired
    private OtherEmployPositionService otherEmployPositionService;


    @ApiOperation(value="教师查询所授课班级学生信息之班级信息", notes="未测试")
    @GetMapping("employee/class/allClassmates")
    @ResponseBody
    public  Result selectClassesByUserId(HttpServletResponse response) throws IOException {

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
        longs.add(employees.get(0).getId());
        List<Curriculum> curricula = curriculumService.selectCurriculumByCondition(null, longs, null, null);
        ClassBean classBean = new ClassBean();
        Set<ClassBean> classBeans = new HashSet<>();
        int i=0;
        String json = null;
        for (Curriculum c:curricula) {
            Class aClass = otherClassService.selectClassByClassId(c.getClassId());
            Employee employee1 = employeeService.selectEmployeeById(aClass.getHeadteacher());
            User user = userService.selectUserById(employee1.getUserId());
            List<Classmate> classmates = otherClassmateService.selectByClassId(aClass.getId());
            for (Classmate classmate:classmates) {
                ClassmatePosition classmatePosition = otherClassmatePositionService.selectclassmatePositionByClassmateIdAndPositionId(classmate.getId(), Long.valueOf(25));
                if (classmatePosition!=null){
                    Student student = studentService.selectValidStudentByStuId(classmate.getStudentId());
                    User user1 = userService.selectUserById(student.getUserId());
                    classBean.setClassId(c.getClassId());
                    classBean.setMoniter(user1.getUserName());
                    classBean.setCode(aClass.getCode());
                    classBean.setName(aClass.getName());
                    classBean.setNumber(classmates.size());
                    classBean.setHeadteacher(user.getUserName());
                    i++;
                }else{
                    continue;
                }
            }
            classBeans.add(classBean);
        }
        //System.out.println(classBean);
        return Result.build(ResultType.Success).appendData("classBeans", classBeans);
    }
}
