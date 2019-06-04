package edu.uni.userBaseInfo1.controller;

import edu.uni.administrativestructure.bean.Class;
import edu.uni.administrativestructure.bean.Classmate;
import edu.uni.administrativestructure.bean.ClassmatePosition;
import edu.uni.administrativestructure.service.ClassmatePositionService;
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


    @ApiOperation(value="查询所授课班级学生信息之班级信息", notes="未测试")
    @ApiImplicitParam(name = "userId", value = "userId", required = false, dataType = "Long" , paramType = "path")
    @GetMapping("employee/class/allClassmates/{userId}")
    @ResponseBody
    public  void selectClassesByUserId(@PathVariable Long userId, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        Employee employee = employeeService.selectByUserId(userId).get(0);
        List<Long> longs = new ArrayList<>();
        longs.add(employee.getId());
        List<Curriculum> curricula = curriculumService.selectCurriculumByCondition(null, longs, null, null);
        ClassBean classBean = new ClassBean();
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
        }
        System.out.println(classBean);
        json = Result.build(ResultType.Success).appendData("classBean", classBean).convertIntoJSON();
        response.getWriter().write(json);
    }
}
