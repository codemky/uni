package edu.uni.auth.controller;

import edu.uni.auth.bean.Role;
import edu.uni.auth.bean.UserRole;
import edu.uni.auth.exception.RoleException;
import edu.uni.auth.exception.UnivInfoSUPException;
import edu.uni.auth.service.AuthService;
import edu.uni.auth.service.RoleService;
import edu.uni.auth.service.UnivInfoSUPService;
import edu.uni.auth.service.UserRoleService;
import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.auth.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author 何亮
 */
@Api(description = "权限管理模块")
@Controller
@RequestMapping("json/auth")
public class UserRoleController {
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private AuthService authService;
    @Autowired
    private UnivInfoSUPService univInfoSUPService;
    @Autowired
    private RoleService roleService;

    /**
     * <p>
     *     返回用户已有角色集和全部角色集
     * </p>
     * @return
     */
    @ApiOperation(value="返回用户已有角色集和全部角色集", notes="")
    @ApiImplicitParam(name="userId", value = "用户id", required = true, dataType = "Long")
    @GetMapping("userRoleSet/{userId}")
    @ResponseBody
    public Result getUserRoleSet(@PathVariable Long userId) throws UnivInfoSUPException {
        if(userId > 0){
            // 获取用户的角色集
            List<Role> havaRoles = roleService.selectByUidAndUniversityId(userId, getUniversityOfSup());
            // 获取学校的角色集
            List<Role> roles = roleService.selectAllByUniversityId(getUniversityOfSup());

            return Result.build(ResultType.Success).appendData("havaRoles",havaRoles).appendData("allRoles", roles);
        }
        return  Result.build(ResultType.ParamError);
    }

    /**
     * <p>
     *     更新用户的角色集
     * </p>
     * @return
     */
    @ApiOperation(value="更新用户的角色集", notes="")
    @PostMapping("userRoleSet")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId", value = "用户id", required = true, dataType = "Long"),
            @ApiImplicitParam(name="roleIdStr", value = "新角色id集合", required = true, dataType = "String")
    })
    @ResponseBody
    public Result updateUserRoleSet(Long userId, String roleIdStr) throws UnivInfoSUPException, SQLException {
        System.out.println(userId);
        System.out.println(roleIdStr);
        Long[] roleIds = null;
        if(roleIdStr != null){
            String[] split = roleIdStr.split(",");
            roleIds = new Long[split.length];
            for (int i=0 ; i<split.length ; ++i){
                roleIds[i] = Long.parseLong(split[i]);
            }
        }

        if(userId != null && roleIds != null){
            // 更新用户的角色集合
            roleService.updateUserRoleSet(getUniversityOfSup(), userId, CollectionUtils.asList(roleIds));
            return Result.build(ResultType.Success);
        }
        return  Result.build(ResultType.ParamError);
    }







    /**
     * <p>
     *     新增用户角色
     * </p>
     * @return
     */
    @ApiOperation(value="新增用户角色", notes="")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId", value = "用户id", required = true, dataType = "Long"),
            @ApiImplicitParam(name="roleId", value = "角色id", required = true, dataType = "Long"),
            @ApiImplicitParam(name="status", value = "状态", required = true, dataType = "Integer"),
    })
    @PostMapping("userRole")
    @ResponseBody
    public Result create(Long userId, Long roleId, Integer status) throws UnivInfoSUPException, RoleException {
        if(userId != null && roleId != null && status != null){
            checkRoleId(roleId);

            UserRole userRole = new UserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);
            userRole.setStatus(status);
            userRole.setUniversityId(getUniversityOfSup());

            boolean success = userRoleService.insert(userRole);
            return success ? Result.build(ResultType.Success) : Result.build(ResultType.Failed);
        }
        return  Result.build(ResultType.ParamError);
    }

    /**
     * 删除角色用户
     * @param id
     * @return
     */
    @ApiOperation(value="删除角色用户", notes="")
    @ApiImplicitParam(name = "id", value = "用户角色id", required = true, dataType = "Long")
    @PostMapping("userRoleDelete")
    @ResponseBody
    public Result destroy(Long id) throws UnivInfoSUPException {
        boolean deleted = userRoleService.deleteByIdAndUniversityId(id, getUniversityOfSup());
        return deleted ? Result.build(ResultType.Success) : Result.build(ResultType.Failed);
    }

    /**
     * 修改角色用户状态
     * @param userRole
     * @return
     */
    @ApiOperation(value="修改角色用户状态")
    @ApiImplicitParam(name="userRole", value = "用户角色实体类", required = true, dataType = "UserRole")
    @PutMapping("userRole")
    @ResponseBody
    public Result update(@RequestBody UserRole userRole) throws UnivInfoSUPException {
        if(userRole.getId() != null && userRole.getStatus() != null){
            boolean update = userRoleService.updateStatusByIdAndUniversityId(userRole.getId(), getUniversityOfSup(), userRole.getStatus());
            return update ? Result.build(ResultType.Success) : Result.build(ResultType.Failed);
        }
        return Result.build(ResultType.ParamError);
    }




    private Long getUniversityOfSup() throws UnivInfoSUPException {
        User user = authService.getUser();
        Long universityId = univInfoSUPService.selectUniversityIdBySupId(user.getId());
        return universityId;
    }

    private void checkRoleId(long roleId) throws RoleException {
        if(Role.OperatorId == roleId || Role.UnivInfoSUPId == roleId){
            throw new RoleException("非法操作");
        }
    }
}
