package edu.uni.userBaseInfo1.controller;

import com.github.pagehelper.PageInfo;
import com.sun.org.apache.regexp.internal.RE;
import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.config.GlobalConfig;
import edu.uni.example.config.ExampleConfig;
import edu.uni.utils.FileUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javafx.application.Application;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
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

    @ApiOperation(value="文件上传", notes = "")
    @PostMapping("/upload")
    public Result uploadFile(MultipartFile file, HttpServletRequest request) {
        if (file == null || file.isEmpty()) {
            return Result.build(ResultType.ParamError);
        }
        // 全局上传路径
//        FileUtil fileUtil = new FileUtil(globalConfig.getUploadRootDir());
        // excel上传路径
        FileUtil fileUtil = new FileUtil(exampleConfig.getAbsoluteExcelDir());
        String filePath;
        try {
            filePath = fileUtil.uploadFile(file, request);
            file.getInputStream();
            //校验职员
            excelController.checkoutEmployeeExcel(file.getInputStream(), request);
            excelController.insertEmployeeExcel(file.getInputStream(), request);
            //校验学生
            excelController.checkoutStudentExcel(file.getInputStream(),request);
            excelController.insertStudentExcel(file.getInputStream(),request);
        } catch (IOException e) {
            return Result.build(ResultType.Failed);
        }
        //log.info("成功");
        System.out.println("成功"+filePath);
        // service层方法把文件路径存储在数据库中

        return Result.build(ResultType.Success);
    }


    @ApiOperation(value = "文件下载", notes = "")
    @GetMapping("/download")
    public void download(
            @ApiParam(name = "filePath")
            @RequestParam String filePath,
            HttpServletResponse response) {
        FileUtil fileUtil = new FileUtil();
        try {
            // filePath 可以从数据库获取，这里为了方便直接输入路径
            fileUtil.downloadFile(filePath, response);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @ApiOperation(value = "文件删除", notes = "")
    @DeleteMapping("")
    public Result deleteFile(
            @ApiParam(name = "filePath")
            @RequestParam String filePath) {
        FileUtil fileUtil = new FileUtil();
        // filePath 可以从数据库获取，这里为了方便直接输入路径
        boolean success = fileUtil.deleteFile(filePath);
        if (success) {
            return Result.build(ResultType.Success);
        }
        return Result.build(ResultType.Failed);
    }

}
