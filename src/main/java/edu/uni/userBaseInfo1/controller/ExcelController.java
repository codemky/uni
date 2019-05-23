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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

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
    @Autowired
    private OtherDepartmentService departmentservice;
    @Autowired
    private OtherClassService cclassservice;
    @Autowired
    private PoliticalAffiliationService politicalAffiliationservice;
    @Autowired
    private AddrCountryService addrCountryservice;
    @Autowired
    private AddrStateService addrStateservice;
    @Autowired
    private AddrCityService addrCityservice;
    @Autowired
    private AddrAreaService addrAreaservice;
    @Autowired
    private AddrStreetService addrStreetservice;
    @Autowired
    private OtherSpecialtyService specialtyservice;
    @Autowired
    private UserService userservice;

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

    @ApiOperation(value = "测试student的excel上传", notes = "2019-5-21 22:02:31测试通过")
    //@ApiImplicitParam(name = "filePath", value = "文件的路径或文件名", required = false, dataType = "String" )
    @GetMapping("/student/checkoutexcel")
    @ResponseBody
    public void checkoutStudentExcel(String filePath, HttpServletResponse response) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        response.setContentType("application/json;charset=utf-8");
        String cacheName = AddressController.CacheNameHelper.Receive_CacheNamePrefix +"student"+ "checkout";
        String json = cache.get(cacheName);
        if (json == null) {
            if (WDWUtil.isExcel2007(filePath)) {
                InputStream inputStream = FileUtil.getResourcesFileInputStream(filePath);
                ExcelListener excelListener = new ExcelListener();
                StudentUpload studentUpload = new StudentUpload();
                //EasyExcelFactory.readBySax(inputStream, new Sheet(1, 1), excelListener);
//            List<Object> data = EasyExcelFactory.read(inputStream, new Sheet(1, 0));
                List<Object> data = EasyExcelFactory.read(inputStream, new Sheet(1, 1, StudentModel.class));
                //EasyExcelFactory.readBySax(inputStream, new Sheet(1, 1, StudentModel.class), excelListener);
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
                //print(data);
            }
            json = Result.build(ResultType.Success).appendData("message",stringBuffer).convertIntoJSON();
            cache.set(json, cacheName);
        }
        response.getWriter().write(json);

    }
    @ApiOperation(value = "测试student的excel写入数据库", notes = "2019-5-21 23:25:01测试通过")
    //@ApiImplicitParam(name = "filePath", value = "文件的路径或文件名", required = false, dataType = "String" )
    @GetMapping("/student/inserttexcel")
    @ResponseBody
    public void insertStudentExcel(String filePath,HttpServletResponse response) throws IOException {
        StringBuffer stringBuffer= new StringBuffer();
        response.setContentType("application/json;charset=utf-8");
        String cacheName = AddressController.CacheNameHelper.Receive_CacheNamePrefix + "student"+ "insert";
        String json = cache.get(cacheName);
        if (json == null) {
            if (WDWUtil.isExcel2007(filePath)) {
                InputStream inputStream = FileUtil.getResourcesFileInputStream(filePath);
                StudentUpload studentUpload = new StudentUpload();
                //EasyExcelFactory.readBySax(inputStream, new Sheet(1, 1), excelListener);
//            List<Object> data = EasyExcelFactory.read(inputStream, new Sheet(1, 0));
                List<Object> data = EasyExcelFactory.read(inputStream, new Sheet(1, 1, StudentModel.class));
                //EasyExcelFactory.readBySax(inputStream, new Sheet(1, 1, StudentModel.class), excelListener);
                for (Object o : data) {
                    stringBuffer.append(studentUpload.insertStudent(o));
                }
                /*if (stringBuffer != null && !stringBuffer.equals("") && stringBuffer.length() > 0) {
                    if (stringBuffer.toString().contains("插入失败")){
                        //stringBuffer.delete(0,stringBuffer.length());
                        int roe = stringBuffer.indexOf("失败");
                        stringBuffer.append("第"+stringBuffer.indexOf("插入")+"条记录插入不成功");
                    }
                    stringBuffer.delete(0,stringBuffer.length());
                    stringBuffer.append("插入成功");
                }*/
                System.out.println(stringBuffer);
                inputStream.close();
            }
            json = Result.build(ResultType.Success).appendData("message",stringBuffer).convertIntoJSON();
            cache.set(json, cacheName);
        }
        response.getWriter().write(json);
    }
    @ApiOperation(value = "测试employee的excel上传", notes = "未测试")
    @GetMapping("/employee/checkoutexcel")
    @ResponseBody
    public void checkoutEmployeeExcel(String filePath, HttpServletResponse response) throws IOException{
        StringBuffer stringBuffer = new StringBuffer();
        response.setContentType("application/json;charset=utf-8");
        String cacheName = AddressController.CacheNameHelper.Receive_CacheNamePrefix + "employee" +"checkout";
        String json = cache.get(cacheName);
        if (json == null) {
            if (WDWUtil.isExcel2007(filePath)) {
                InputStream inputStream = FileUtil.getResourcesFileInputStream(filePath);
                ExcelListener excelListener = new ExcelListener();
                StudentUpload studentUpload = new StudentUpload();
                //EasyExcelFactory.readBySax(inputStream, new Sheet(1, 1), excelListener);
//            List<Object> data = EasyExcelFactory.read(inputStream, new Sheet(1, 0));
                List<Object> data = EasyExcelFactory.read(inputStream, new Sheet(1, 1, EmployeeModel.class));
                //EasyExcelFactory.readBySax(inputStream, new Sheet(1, 1, StudentModel.class), excelListener);
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
                //print(data);
            }
            json = Result.build(ResultType.Success).appendData("message",stringBuffer).convertIntoJSON();
            cache.set(json, cacheName);
        }
        response.getWriter().write(json);
    }
    @ApiOperation(value = "测试employee的excel写入数据库", notes = "未测试")
    //@ApiImplicitParam(name = "filePath", value = "文件的路径或文件名", required = false, dataType = "String" )
    @GetMapping("/employee/insertexcel")
    @ResponseBody
    public void insertEmployeeExcel(String filePath,HttpServletResponse response) throws  IOException{
        StringBuffer stringBuffer= new StringBuffer();
        response.setContentType("application/json;charset=utf-8");
        String cacheName = AddressController.CacheNameHelper.Receive_CacheNamePrefix + "employee"+ "insert";
        String json = cache.get(cacheName);
        if (json == null) {
            if (WDWUtil.isExcel2007(filePath)) {
                InputStream inputStream = FileUtil.getResourcesFileInputStream(filePath);
                StudentUpload studentUpload = new StudentUpload();
                //EasyExcelFactory.readBySax(inputStream, new Sheet(1, 1), excelListener);
//            List<Object> data = EasyExcelFactory.read(inputStream, new Sheet(1, 0));
                List<Object> data = EasyExcelFactory.read(inputStream, new Sheet(1, 1, EmployeeModel.class));
                //EasyExcelFactory.readBySax(inputStream, new Sheet(1, 1, StudentModel.class), excelListener);
                for (Object o : data) {
                    stringBuffer.append(studentUpload.insertEmployee(o));
                }
                /*if (stringBuffer != null && !stringBuffer.equals("") && stringBuffer.length() > 0) {
                    if (stringBuffer.toString().contains("插入失败")){
                        //stringBuffer.delete(0,stringBuffer.length());
                        int roe = stringBuffer.indexOf("失败");
                        stringBuffer.append("第"+stringBuffer.indexOf("插入")+"条记录插入不成功");
                    }
                    stringBuffer.delete(0,stringBuffer.length());
                    stringBuffer.append("插入成功");
                }*/
                System.out.println(stringBuffer);
                inputStream.close();
            }
            json = Result.build(ResultType.Success).appendData("message",stringBuffer).convertIntoJSON();
            cache.set(json, cacheName);
        }
        response.getWriter().write(json);
    }
}
