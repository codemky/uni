package edu.uni.userBaseInfo1.controller;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;
import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.userBaseInfo1.alibaba.easyexcel.test.StudentUpload;
import edu.uni.userBaseInfo1.alibaba.easyexcel.test.listen.ExcelListener;
import edu.uni.userBaseInfo1.alibaba.easyexcel.test.model.EmployeeModel;
import edu.uni.userBaseInfo1.alibaba.easyexcel.test.model.StudentModel;
import edu.uni.userBaseInfo1.alibaba.easyexcel.test.util.FileUtil;
import edu.uni.userBaseInfo1.bean.Employee;
import edu.uni.userBaseInfo1.service.*;
import edu.uni.userBaseInfo1.utils.WDWUtil;
import edu.uni.utils.RedisCache;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author chenenru
 * @ClassName StudentUploadAndInsertController
 * @Description
 * @Date 2019/5/21 21:07
 * @Version 1.0
 **/
//填写description内容可以在测试模块显示相应的文字和模块
@Api(description = "excel信息模块")
//Controller类（或者说Http）的请求路径
//如果添加了路径，则在需要调用该类的方法时需要在方法请求mapping路径前加上类的mapping路径
@RequestMapping("json/excel")
//标志这个类是一个controller类，用于被Spring扫描然后配置添加和配置相应的bean
@RestController
public class ExcelController {

    //把缓存工具类RedisCache相应的方法自动装配到该对象
    @Autowired
    private RedisCache cache;

    //内部类，专门用来管理每个get方法所对应缓存的名称。
    static class CacheNameHelper {
        // ub1_a_address_{地址记录id}
        public static final String Receive_CacheNamePrefix = "ub1_e_excel_";
        // ub1_a_address_listAll
        public static final String ListAll_CacheName = "ub1_e_excel_listAll";
    }

    @ApiOperation(value = "测试student的excel校验", notes = "2019-5-21 22:02:31测试通过")
    @GetMapping("/student/checkoutexcel")
    @ResponseBody
    public StringBuffer checkoutStudentExcel(InputStream inputStream, HttpServletRequest request) throws IOException {
        request.setCharacterEncoding("utf-8");
        StringBuffer stringBuffer = new StringBuffer();
        StudentUpload studentUpload = new StudentUpload();
        List<Object> data = EasyExcelFactory.read(inputStream, new Sheet(1, 1, StudentModel.class));
        StudentUpload.setDouStuNo(new HashSet<>());
        StudentUpload.setDouIden(new HashSet<>());
        for (Object o : data) {
            stringBuffer.append(studentUpload.checkoutStudent(o));
        }
        if (stringBuffer != null && !stringBuffer.equals("") && stringBuffer.length() > 0) {
            System.out.println("报错信息：");
            System.out.println(stringBuffer);
        } else {
            stringBuffer.append("校验通过");
            System.out.println("校验通过");
        }
        inputStream.close();
        return stringBuffer;
    }

    @ApiOperation(value = "测试student的excel写入数据库", notes = "2019-5-21 23:25:01测试通过")
    @GetMapping("/student/inserttexcel")
    @ResponseBody
    public StringBuffer insertStudentExcel(InputStream inputStream, HttpServletRequest request) throws IOException {
        request.setCharacterEncoding("utf-8");
        StringBuffer stringBuffer = new StringBuffer();
        StudentUpload studentUpload = new StudentUpload();
        List<Object> data = EasyExcelFactory.read(inputStream, new Sheet(1, 1, StudentModel.class));
        for (Object o : data) {
            stringBuffer.append(studentUpload.insertStudent(o));
        }
        System.out.println(stringBuffer);
        inputStream.close();
        return stringBuffer;
    }

    @ApiOperation(value = "测试employee的excel校验", notes = "未测试")
    @GetMapping("/employee/checkoutexcel")
    @ResponseBody
    public StringBuffer checkoutEmployeeExcel(InputStream inputStream, HttpServletRequest request) throws IOException {
        request.setCharacterEncoding("utf-8");
        StringBuffer stringBuffer = new StringBuffer();
        StudentUpload studentUpload = new StudentUpload();
        List<Object> data = EasyExcelFactory.read(inputStream, new Sheet(1, 1, EmployeeModel.class));
        StudentUpload.setDouStuNo(new HashSet<>());
        StudentUpload.setDouIden(new HashSet<>());
        for (Object o : data) {
            stringBuffer.append(studentUpload.checkoutEmployee(o));
        }
        if (stringBuffer != null && !stringBuffer.equals("") && stringBuffer.length() > 0) {
            System.out.println("报错信息：");
            System.out.println(stringBuffer);
        } else {
            stringBuffer.append("校验通过");
            System.out.println("校验通过");
        }
        inputStream.close();
        return stringBuffer;
    }

    @ApiOperation(value = "测试employee的excel写入数据库", notes = "未测试")
    @GetMapping("/employee/insertexcel")
    @ResponseBody
    public StringBuffer insertEmployeeExcel(InputStream inputStream, HttpServletRequest request) throws IOException {
        request.setCharacterEncoding("utf-8");
        StringBuffer stringBuffer = new StringBuffer();
        StudentUpload studentUpload = new StudentUpload();
        List<Object> data = EasyExcelFactory.read(inputStream, new Sheet(1, 1, EmployeeModel.class));
        for (Object o : data) {
            stringBuffer.append(studentUpload.insertEmployee(o));
        }
        System.out.println(stringBuffer);
        inputStream.close();
        return stringBuffer;
    }

}
