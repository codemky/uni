package edu.uni.auth.controller;

import edu.uni.auth.bean.Node;
import edu.uni.auth.bean.Role;
import edu.uni.auth.exception.RoleException;
import edu.uni.auth.exception.UnivInfoSUPException;
import edu.uni.auth.service.FuncService;
import edu.uni.auth.service.UnivInfoSUPService;
import edu.uni.auth.bean.User;
import edu.uni.auth.service.AuthService;
import edu.uni.auth.service.RoleService;
import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * @Author 何亮
 */
@Api(description = "权限管理模块")
@Controller
@RequestMapping("json/auth")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private AuthService authService;
    @Autowired
    private UnivInfoSUPService univInfoSUPService;
    @Autowired
    private FuncService funcService;
    @Autowired
    private ShiroFilterFactoryBean shiroFilter;



    /**
     * 更新角色功能点集合
     * @param roleId
     * @param funcIdStr
     * @return
     * @throws UnivInfoSUPException
     * @throws SQLException
     */
    @ApiOperation(value="更新角色功能点集合", notes="")
    @ApiImplicitParams({
            @ApiImplicitParam(name="roleId", value = "角色id", required = true, dataType = "Long"),
            @ApiImplicitParam(name="funcIds", value = "功能点id数组", required = true, dataType = "String")
    })
    @PostMapping("roleFuncs")
    @ResponseBody
    public Result roleFuncs(Long roleId, String funcIdStr) throws UnivInfoSUPException, SQLException {
        System.out.println(roleId);
        System.out.println(funcIdStr);

        Long[] funcIds = null;
        if(funcIdStr != null){
            String[] split = funcIdStr.split(",");
            funcIds = new Long[split.length];
            for(int i=0 ; i<split.length ; ++i){
                funcIds[i] = Long.parseLong(split[i]);
            }
        }

        if(roleId != null && funcIdStr != null){
            roleService.updateRoleFuncSet(getUniversityOfSup() ,roleId, CollectionUtils.asList(funcIds));
            // 動態權限更新
            authService.updatePermission(shiroFilter, null);
            return Result.build(ResultType.Success);
        }
        return Result.build(ResultType.ParamError);
    }


    /**
     * <p>
     *     学校新增角色
     * </p>
     * @param name
     * @param status
     * @param description
     * @return
     * @throws RoleException
     * @throws UnivInfoSUPException
     */
    @ApiOperation(value="学校新增角色", notes="")
    @ApiImplicitParams({
            @ApiImplicitParam(name="name", value = "角色名", required = true, dataType = "String"),
            @ApiImplicitParam(name="status", value = "角色状态", required = true, dataType = "Integer"),
            @ApiImplicitParam(name="description", value = "角色描述", required = false, dataType = "String")
    })
    @PostMapping("role")
    @ResponseBody
    public Result create(String name, Integer status, String description) throws RoleException, UnivInfoSUPException {
        checkRoleName(name);

        if(!StringUtils.isEmpty(name) && status != null){
            Role role = new Role();
            role.setName(name);
            role.setStatus(status);
            role.setDescription(description);
            role.setUniversityId(getUniversityOfSup());
            boolean success = roleService.insert(role);
            return success ? Result.build(ResultType.Success) : Result.build(ResultType.Failed);
        }
        return Result.build(ResultType.ParamError);
    }

    /**
     * 学校删除角色
     * @param id
     * @return
     */
    @ApiOperation(value="学校删除角色", notes="")
    @ApiImplicitParam(name = "id", value = "角色id", required = true, dataType = "Long", paramType = "path")
    @PostMapping("roleDelete")
    @ResponseBody
    public Result destroy(Long id) throws UnivInfoSUPException, SQLException {
        roleService.deleteByIdAndUniversityId(id, getUniversityOfSup());
        return Result.build(ResultType.Success);
    }

    /**
     * 学校删除角色2
     * @param id
     * @return
     */
    @ApiOperation(value="学校删除角色2", notes="")
    @ApiImplicitParam(name = "id", value = "角色id", required = true, dataType = "Long", paramType = "path")
    @DeleteMapping("roleDelete/{id}")
    @ResponseBody
    public Result destroy2(@PathVariable Long id) throws UnivInfoSUPException, SQLException {
        roleService.deleteByIdAndUniversityId(id, getUniversityOfSup());
        return Result.build(ResultType.Success);
    }


    /**
     * 学校修改角色
     * @param role
     * @return
     */
    @ApiOperation(value="根据角色id修改角色信息2")
    @ApiImplicitParam(name="role", value = "角色实体类", required = true, dataType = "Role")
    @PostMapping("roleStatus")
    @ResponseBody
    public Result update2(Role role) throws RoleException, UnivInfoSUPException {
        checkRoleName(role.getName());

        if(role.getId() != null){
            role.setUniversityId(getUniversityOfSup());
            boolean updated = roleService.updateSelective(role);
            if(updated == true){
                authService.updatePermission(shiroFilter, null);
                return Result.build(ResultType.Success);
            }else {
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }

    /**
     * 列举某学校角色
     */
    @ApiOperation(value="列举某学校角色", notes="")
    @GetMapping(value = "/roles/listAll")
    @ResponseBody
    public Result listAll() throws UnivInfoSUPException {
        List<Role> roles = roleService.selectAllByUniversityId(getUniversityOfSup());
        return Result.build(ResultType.Success).appendData("roles", roles);
    }


    /**
     * 列举某用户在本学校中所包含的角色
     */
    @ApiOperation(value="列举某用户在本学校中所包含的角色", notes="")
    @GetMapping(value = "/roles/listByUid/{uid}")
    @ResponseBody
    public Result listByUid(@PathVariable Long uid) throws UnivInfoSUPException {

        List<Role> roles = roleService.selectByUidAndUniversityId(uid, getUniversityOfSup());
        return Result.build(ResultType.Success).appendData("roles", roles);
    }

    private Long getUniversityOfSup() throws UnivInfoSUPException {
        User user = authService.getUser();
        Long universityId = univInfoSUPService.selectUniversityIdBySupId(user.getId());
        return universityId;
    }

    private void checkRoleName(String name) throws RoleException {
        if(Role.Operator.equals(name) || Role.UnivInfoSUP.equals(name))
            throw new RoleException("角色名非法");
    }
}
