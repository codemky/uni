package edu.uni.userBaseInfo1.controller;

import edu.uni.administrativestructure.bean.Employ;
import edu.uni.administrativestructure.service.EmployService;
import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.userBaseInfo1.bean.Employee;
import edu.uni.userBaseInfo1.bean.StudentRelation;
import edu.uni.userBaseInfo1.bean.User;
import edu.uni.userBaseInfo1.service.EmployeeService;
import edu.uni.userBaseInfo1.service.UserService;
import edu.uni.userBaseInfo1.utils.UserInfo;
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
    EmployService employService;
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
        Employ employ = employService.selectEmployByEmployeeId(employee.getUserId());
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
