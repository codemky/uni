package edu.uni.auth.controller;

import edu.uni.auth.bean.Func;
import edu.uni.auth.bean.FuncModule;
import edu.uni.auth.bean.Node;
import edu.uni.auth.bean.User;
import edu.uni.auth.service.AuthService;
import edu.uni.auth.service.FuncService;
import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
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
@Api(description = "功能点模块")
@Controller
@RequestMapping("json/auth")
public class FuncController {
    @Autowired
    private AuthService authService;
    @Autowired
    private FuncService funcService;

    @ApiOperation(value="获取角色功能点菜单", notes="")
    @ApiImplicitParam(name="roleId", value = "角色id", required = true, dataType = "Long")
    @GetMapping("roleFuncMenu/{roleId}")
    @ResponseBody
    public Result funcList(@PathVariable Long roleId){
        List<Node> menu = funcService.getRoleFuncMenu(roleId);
        return Result.build(ResultType.Success).appendData("menu", menu);
    }

    /**
     * <p>
     *     返回用户功能菜单
     * </p>
     * @return
     */
    @ApiOperation(value="返回用户菜单", notes="")
    @GetMapping("/menu")
    @ResponseBody
    public Result menu(){
        User user = authService.getUser();
        List<Node> userMenu = funcService.getUserMenu(user);
        return Result.build(ResultType.Success).appendData("menu", userMenu);
    }


}
