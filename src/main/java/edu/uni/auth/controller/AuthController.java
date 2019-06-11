package edu.uni.auth.controller;

import edu.uni.auth.mapper.RoleMapper;
import edu.uni.auth.service.AuthService;
import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author 何亮
 */
@RestController
@RequestMapping("json/auth")
@Api(description = "login模块")
public class AuthController {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private AuthService authService;
    @Autowired
    private ShiroFilterFactoryBean shiroFilter;

    @GetMapping("index")
    public Result index(){
        return Result.build(ResultType.Success, "index");
    }

    @GetMapping("index2")
    public Result index2(){
        return Result.build(ResultType.Success, "index2");
    }

    @GetMapping("update")
    public Result update(){
        Map<String, String> init = new LinkedHashMap<>();
        init.put("/json/auth/index2", "anon");
        authService.updatePermission(shiroFilter,init);
        return Result.build(ResultType.Success, "update");
    }

    @GetMapping("logout")
    public Result logout(){
        Subject subject = SecurityUtils.getSubject();
        if(subject != null){
            subject.logout();
        }
        return Result.build(ResultType.Success, "退出操作成功");
    }

    @RequestMapping("success")
    public Result success(){
        return Result.build(ResultType.Success, "success");
    }

    @RequestMapping("403")
    public Result unauthorized(){
        return Result.build(ResultType.Unauthorized);
    }

    @RequestMapping("login")
    public Result login(String uname, String pwd){
        if(!StringUtils.isEmpty(uname) && !StringUtils.isEmpty(pwd)){
            UsernamePasswordToken token = new UsernamePasswordToken(uname, pwd);
            Subject currentUser = SecurityUtils.getSubject();
            try {
                currentUser.login( token );
                //验证是否登录成功
                if (currentUser.isAuthenticated()) {
                    return Result.build(ResultType.Success, "身份认证登陆成功");
                } else if(currentUser.isRemembered()){
                    return Result.build(ResultType.Success, "Remember Me登陆成功");
                } else {
                    token.clear();
                    return Result.build(ResultType.Failed);
                }
            } catch ( UnknownAccountException uae ) {
                return Result.build(ResultType.Failed, "账户不存在");
            } catch ( IncorrectCredentialsException ice ) {
                return Result.build(ResultType.Failed, "密码错误");
            } catch ( LockedAccountException lae ) {
                return Result.build(ResultType.Failed, "账号已被锁定");
            } catch ( AuthenticationException ae ) {
                ae.printStackTrace();
                return Result.build(ResultType.Failed, ae.getMessage());
            }
        }
        return Result.build(ResultType.ParamError, "用户名和密码不能为空");
    }
}
