package edu.uni.gradeManagement1.controller;

import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
/**
 * @author 陈少鑫
 * <p>@description :文件下载工具类</p>
 * @date 19:40 2019-05-19
 * @modified 19:40 2019-05-19
 */

@Api(description = "成绩模块：文件下载工具类")
@Controller
@RequestMapping(value = "/jsons/gradeManagement1")
public class DownloadController {

    @ApiOperation(value = "处理文件下载的接口")
    @GetMapping(value = "download")
    public void download(
            HttpServletResponse response,
            @ApiParam(value = "绝对地址")
            @RequestParam(value = "path") String path) throws IOException {
        System.out.println(path);
        File file = new File(path);
        if (!file.exists()) {
            String json = Result.build(ResultType.Failed).appendData("data","文件不存在").convertIntoJSON();
            response.getWriter().write(json);
            return;
        }
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
        response.setContentType("multipart/form-data");
        //2.设置文件头：最后一个参数是设置下载文件名
        response.setHeader("Content-Disposition", "attachment;fileName="+path.substring(path.lastIndexOf("//")));
        byte[] bytes = new byte[2048];
        int n;
        try(
                InputStream is = new FileInputStream(file);
                OutputStream os = new BufferedOutputStream(response.getOutputStream());
        ){
            while ((n = is.read(bytes)) != -1){
                os.write(bytes,0,n);
            }
            os.flush();
        }
    }

}
