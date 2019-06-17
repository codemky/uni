package edu.uni.userBaseInfo1.controller;

import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.config.GlobalConfig;
import edu.uni.example.config.ExampleConfig;
import edu.uni.userBaseInfo1.utils.UserInfoFileUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @Author deschen
 * @Create 2019/5/21
 * @Description
 * @Since 1.0.0
 */
@Api
@RestController
@RequestMapping("/example/file")
@Slf4j
public class FileExampleController {

    @Autowired
    private GlobalConfig globalConfig;

    @Autowired
    private ExampleConfig exampleConfig;

    @Autowired
    private ExcelController excelController;

    @ApiOperation(value="上传职员文件并校验，校验通过就写入数据库", notes = "")
    @PostMapping("/upload/employee")
    public Result uploadEmployeeFile(MultipartFile file, HttpServletRequest request) {
        StringBuffer stringBuffer = new StringBuffer();
        if (file == null || file.isEmpty()) {
            return Result.build(ResultType.ParamError);
        }
        // 全局上传路径
//        UserInfoFileUtil userInfoFileUtil = new UserInfoFileUtil(globalConfig.getUploadRootDir());
        // excel上传路径
        UserInfoFileUtil userInfoFileUtil = new UserInfoFileUtil(exampleConfig.getAbsoluteExcelDir());
        String filePath;
        try {
            filePath = userInfoFileUtil.uploadFile(file, request);
            //校验职员
            stringBuffer = excelController.checkoutEmployeeExcel(file.getInputStream(), request);
            if (stringBuffer.equals("校验通过")){
                stringBuffer = excelController.insertEmployeeExcel(file.getInputStream(), request);
            }
        } catch (IOException e) {
            return Result.build(ResultType.Failed);
        }
        //log.info("成功");
        System.out.println("成功"+filePath);
        // service层方法把文件路径存储在数据库中

        return Result.build(ResultType.Success).appendData("message", stringBuffer);
    }
    @ApiOperation(value="上传学生文件并校验，校验通过就写入数据库", notes = "")
    @PostMapping("/upload/student")
    public Result uploadStudentFile(MultipartFile file, HttpServletRequest request) {
        StringBuffer stringBuffer = new StringBuffer();
        if (file == null || file.isEmpty()) {
            return Result.build(ResultType.ParamError);
        }
        // 全局上传路径
//        UserInfoFileUtil userInfoFileUtil = new UserInfoFileUtil(globalConfig.getUploadRootDir());
        // excel上传路径
        UserInfoFileUtil userInfoFileUtil = new UserInfoFileUtil(exampleConfig.getAbsoluteExcelDir());
        String filePath;
        try {
            filePath = userInfoFileUtil.uploadFile(file, request);
            //校验学生
            stringBuffer = excelController.checkoutStudentExcel(file.getInputStream(),request);
            if (stringBuffer.equals("校验通过")) {
                stringBuffer = excelController.insertStudentExcel(file.getInputStream(),request);
            }
        } catch (IOException e) {
            return Result.build(ResultType.Failed);
        }
        //log.info("成功");
        System.out.println("成功"+filePath);
        // service层方法把文件路径存储在数据库中
        return Result.build(ResultType.Success).appendData("message", stringBuffer);
    }


    @ApiOperation(value = "文件下载", notes = "")
    @GetMapping("/download")
    public void download(
            @ApiParam(name = "fileName")
            @RequestParam String fileName,
            HttpServletResponse response) {
        UserInfoFileUtil userInfoFileUtil = new UserInfoFileUtil(exampleConfig.getAbsoluteExcelDir());
        try {
            // filePath 可以从数据库获取，这里为了方便直接输入路径
            userInfoFileUtil.downloadFile(fileName, response);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @ApiOperation(value = "文件删除", notes = "")
    @DeleteMapping("")
    public Result deleteFile(
            @ApiParam(name = "filePath")
            @RequestParam String filePath) {
        UserInfoFileUtil userInfoFileUtil = new UserInfoFileUtil();
        // filePath 可以从数据库获取，这里为了方便直接输入路径
        boolean success = userInfoFileUtil.deleteFile(filePath);
        if (success) {
            return Result.build(ResultType.Success);
        }
        return Result.build(ResultType.Failed);
    }

}
