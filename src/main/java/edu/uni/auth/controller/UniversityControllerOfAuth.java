package edu.uni.auth.controller;

import edu.uni.auth.bean.University;
import edu.uni.auth.service.UniversityServiceOfAuth;
import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author 何亮
 */
@Api(description = "权限管理模块")
@Controller
@RequestMapping("json/auth")
public class UniversityControllerOfAuth {
    @Autowired
    private UniversityServiceOfAuth universityService;

    /**
     * 分页查询学校
     */
    @ApiOperation(value="分页查询学校", notes="")
    @GetMapping(value = "/universitys/{name}_{ename}/{pageNum}")
    @ResponseBody
    public Result universitys(@PathVariable String name, @PathVariable String ename, @PathVariable Integer pageNum){
        List<University> universities = universityService.selectPageByNameAndEname(12, pageNum, name, ename);
        return Result.build(ResultType.Success).appendData("universities", universities);
    }

    @ApiOperation(value="列舉學校", notes="")
    @GetMapping(value = "/universitys/listAll")
    @ResponseBody
    public Result listAll(){
        return Result.build(ResultType.Success).appendData("universitys", universityService.selectAll());
    }
}
