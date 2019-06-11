package edu.uni.auth.controller;

import com.github.pagehelper.PageInfo;
import edu.uni.auth.service.AuthService;
import edu.uni.auth.service.UserServiceOfAuth;
import edu.uni.auth.shrio.CustomCredentialMatcher;
import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.auth.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @Author 何亮
 */
@Api(description = "权限模块")
@Controller
@RequestMapping("json/auth")
public class UserControllerOfAuth {
    @Autowired
    private UserServiceOfAuth userService;
    @Autowired
    private AuthService authService;


    @ApiOperation(value="修改密码")
    @ApiImplicitParam(name="newPwd", value = "新密码", required = true, dataType = "String")
    @PostMapping("/changePwd")
    @ResponseBody
    public Result changePwd(String newPwd){
        if(!StringUtils.isEmpty(newPwd)){
            User user = authService.getUser();
            // 生成新的salt
            String salt = getRandomString(35);
            // 加密
            String pwd = CustomCredentialMatcher.encodePassword(newPwd, salt);
            // 更新到数据库
            boolean update = userService.updatePwdAndSalt(user.getId(), pwd, salt);
            return update ?
                    Result.build(ResultType.Success, "密码修改成功") :
                    Result.build(ResultType.Failed, "密码修改失败");
        }
        return Result.build(ResultType.ParamError);
    }

    @ApiOperation(value="注册用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name="name", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(name="pwd", value = "密码", required = true, dataType = "String"),
            @ApiImplicitParam(name="universityId", value = "学校id", required = true, dataType = "Long"),
            @ApiImplicitParam(name="userType", value = "用户类型", required = true, dataType = "Integer"),
            @ApiImplicitParam(name="checkCode", value = "验证码", required = true, dataType = "String")
    })
    @PostMapping("/register")
    @ResponseBody
    public Result register(String name, String pwd, Long universityId, Integer userType, String checkCode, HttpServletRequest request){
        if(!StringUtils.isEmpty(name) || !StringUtils.isEmpty(pwd) || universityId != null || userType != null){
            String code = (String) request.getSession().getAttribute("checkCode");
            LocalDateTime codeTime = (LocalDateTime) request.getSession().getAttribute("checkCodeTime");
            // 没有获取过验证码
            if(code == null || codeTime == null){
                return Result.build(ResultType.Failed, "你没有获取过验证码");
            }

            // 验证码过期时间为2min
            LocalDateTime now = LocalDateTime.now();
            Duration duration = Duration.between(codeTime, now);
            long days = duration.toDays(); //相差的天数
            long hours = duration.toHours();//相差的小时数
            long minutes = duration.toMinutes();//相差的分钟数
            if(days > 0 || hours > 0 || minutes >= 2){
                return Result.build(ResultType.Failed, "验证码过期");
            }

            // 验证码错误
            if (!code.equals(checkCode)){
                return Result.build(ResultType.Failed, "验证码错误");
            }

            // 新增用户
            User user = new User();
            user.setName(name);
            String salt = getRandomString(35);
            user.setPwd(CustomCredentialMatcher.encodePassword(pwd, salt));
            user.setStatus(0);
            user.setSalt(salt);
            user.setUniversityId(universityId);
            user.setUserType(userType);
            user.setRegist(new Date());

            boolean insert = userService.insert(user);

            // 删除验证码
            request.getSession().removeAttribute("checkCode");
            request.getSession().removeAttribute("checkCodeTime");

            return insert ? Result.build(ResultType.Success, "注册成功") : Result.build(ResultType.Failed, " 注册失败");
        }
        return Result.build(ResultType.ParamError);
    }

    /**
     * 检测用户名是否已被注册
     * @param name
     * @return
     */
    @GetMapping("user/checkNameExist")
    @ApiImplicitParam(name="name", value = "用户名", required = true, dataType = "String")
    @ResponseBody
    public Result checkNameExist(String name){
        if(!StringUtils.isEmpty(name)){
            User user = userService.selectByName(name);
            return user == null ?
                    Result.build(ResultType.Success,"用户名可注册") :
                    Result.build(ResultType.Failed,"用户名已被注册");
        }
        return Result.build(ResultType.ParamError);
    }

    /**
     * 返回随机验证码
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("checkImg")
    public void checkImg(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        int width = 120;
        int height = 30;

        // 步骤一 绘制一张内存中图片
        BufferedImage bufferedImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);

        // 步骤二 图片绘制背景颜色 ---通过绘图对象
        Graphics graphics = bufferedImage.getGraphics();// 得到画图对象 --- 画笔
        // 绘制任何图形之前 都必须指定一个颜色
        graphics.setColor(getRandColor(200, 250));
        graphics.fillRect(0, 0, width, height);

        // 步骤三 绘制边框
        graphics.setColor(Color.WHITE);
        graphics.drawRect(0, 0, width - 1, height - 1);

        // 步骤四 四个随机数字
        Graphics2D graphics2d = (Graphics2D) graphics;
        // 设置输出字体
        graphics2d.setFont(new Font("宋体", Font.BOLD, 25));

        Random random = new Random();// 生成随机数
        String checkCode = getRandomString(5);

        // 定义x坐标
        int x = 10;
        for (int i = 0; i < checkCode.length(); i++) {
            // 随机颜色
            graphics2d.setColor(new Color(20 + random.nextInt(110), 20 + random
                    .nextInt(110), 20 + random.nextInt(110)));
            // 旋转 -30 --- 30度
            int jiaodu = random.nextInt(60) - 30;
            // 换算弧度
            double theta = jiaodu * Math.PI / 180;

            // 获得字母数字
            char c = checkCode.charAt(i);

            // 将c 输出到图片
            graphics2d.rotate(theta, x, 20);
            graphics2d.drawString(String.valueOf(c), x, 20);
            graphics2d.rotate(-theta, x, 20);
            x += 30;
        }

        // 将验证码内容保存session
        request.getSession().removeAttribute("checkCode");
        request.getSession().removeAttribute("checkCodeTime");
        request.getSession().setAttribute("checkCode", checkCode);
        request.getSession().setAttribute("checkCodeTime", LocalDateTime.now());

        // 步骤五 绘制干扰线
        graphics.setColor(getRandColor(160, 200));
        int x1;
        int x2;
        int y1;
        int y2;
        for (int i = 0; i < 30; i++) {
            x1 = random.nextInt(width);
            x2 = random.nextInt(12);
            y1 = random.nextInt(height);
            y2 = random.nextInt(12);
            graphics.drawLine(x1, y1, x1 + x2, x2 + y2);
        }

        // 将上面图片输出到浏览器 ImageIO
        graphics.dispose();// 释放资源
        ImageIO.write(bufferedImage, "jpg", response.getOutputStream());

    }

    /**
     * 取其某一范围的color
     */
    private Color getRandColor(int fc, int bc) {
        // 取其随机颜色
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    /**
     * 生成长度为length的随机字符串
     * @param length
     * @return
     */
    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(35);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }




    /**
     * <p>
     *     根据用户名和用户姓名模糊查询用户列表
     * </p>
     * @throws IOException
     */
    @ApiOperation(value="根据用户名和用户姓名查询用户列表", notes="")
    @GetMapping(value = "user/listLikeUnivIdNameEname/{universityId}/{name}_{userName}/{pageNum}")
    @ApiImplicitParams({
            @ApiImplicitParam(name="name", value = "账户名", dataType = "String", paramType = "path"),
            @ApiImplicitParam(name="userName", value = "用户姓名", dataType = "String", paramType = "path"),
            @ApiImplicitParam(name="pageNum", value = "页码", dataType = "Integer", paramType = "path")
    })
    @ResponseBody
    public Result listLikeUnivIdNameEname(@PathVariable Long universityId,@PathVariable String name, @PathVariable String userName
            , @PathVariable Integer pageNum) {
        PageInfo pageInfo = userService.selectPageByUniversityIdLikeByNameAndUserName(universityId, name, userName, pageNum);
        return Result.build(ResultType.Success).appendData("pageInfo", pageInfo);
    }

    /**
     * <p>
     *     根据用户名和用户姓名模糊查询用户列表
     * </p>
     * @throws IOException
     */
    @ApiOperation(value="根据用户名和用户姓名查询用户列表", notes="")
    @GetMapping(value = "user/listLikeUnivNameEname/{name}_{userName}/{pageNum}")
    @ApiImplicitParams({
            @ApiImplicitParam(name="name", value = "账户名", dataType = "String", paramType = "path"),
            @ApiImplicitParam(name="userName", value = "用户姓名", dataType = "String", paramType = "path"),
            @ApiImplicitParam(name="pageNum", value = "页码", dataType = "Integer", paramType = "path")
    })
    @ResponseBody
    public Result listLikeUnivNameEname(@PathVariable String name, @PathVariable String userName
            , @PathVariable Integer pageNum) {
        PageInfo pageInfo = userService.selectPageByUniversityIdLikeByNameAndUserName(authService.getUser().getUniversityId(), name, userName, pageNum);
        return Result.build(ResultType.Success).appendData("pageInfo", pageInfo);
    }
}
