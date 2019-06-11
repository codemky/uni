package edu.uni.auth.controller;

import com.github.pagehelper.PageInfo;
import edu.uni.auth.bean.UnivInfoSUP;
import edu.uni.auth.exception.UnivInfoSUPException;
import edu.uni.auth.service.UnivInfoSUPService;
import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * @Author 何亮
 * <p>
 *     该Controller的所有接口均是用于对学校信息管理员而设置，只允许运维者使用
 * </p>
 *
 */
@Api(description = "权限管理模块")
@Controller
@RequestMapping("json/auth")
public class UnivInfoSUPController {
//    @Autowired
//    private UserRoleService userRoleService;
    @Autowired
    private UnivInfoSUPService univInfoSUPService;

    /**
     * <p>
     *      新增学校信息管理者
     * </p>
     * @param userId
     * @param universityId
     * @return
     */
    @ApiOperation(value="新增学校信息管理者", notes="")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId", value = "用户id", required = true, dataType = "Long"),
            @ApiImplicitParam(name="universityId", value = "学校id", required = true, dataType = "Long"),
            @ApiImplicitParam(name="status", value = "学校信息管理员状态", required = true, dataType = "Integer")
    })
    @PostMapping("uisup")
    @ResponseBody
    public Result create(Long userId,Long universityId,Integer status) throws UnivInfoSUPException {
        if(userId != null && universityId!= null && status != null){
            boolean success = univInfoSUPService.insert(userId, universityId, status);
            return success ? Result.build(ResultType.Success) : Result.build(ResultType.Failed);
        }
        return Result.build(ResultType.ParamError);
    }

    /**
     * <p>
     *     删除学校信息管理者
     * </p>
     * @param universityId
     * @return
     */
    @ApiOperation(value="删除学校信息管理者", notes="")
    @ApiImplicitParam(name = "universityId", value = "學校id", required = true, dataType = "Long")
    @PostMapping("/unisupDelete")
    @ResponseBody
    public Result destroy(Long universityId){
        boolean delete = univInfoSUPService.delete(universityId);
        return delete ? Result.build(ResultType.Success) : Result.build(ResultType.Failed);
    }


    /**
     *<p>
     *    修改学校信息管理者状态
     *</p>
     * @param universityId
     * @param status
     * @return
     * @throws UnivInfoSUPException
     */
    @ApiOperation(value="修改学校信息管理者状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name="universityId", value = "学校id", required = true, dataType = "Long"),
            @ApiImplicitParam(name="status", value = "学校信息管理员状态", required = true, dataType = "Integer")
    })
    @PostMapping("uisupStatus")
    @ResponseBody
    public Result update(Long universityId, Integer status) throws UnivInfoSUPException {
        if(universityId != null &&  status != null){
            boolean success = univInfoSUPService.updateStatusByUniversityId(universityId, status);
            return success ? Result.build(ResultType.Success) : Result.build(ResultType.Failed);
        }
        return Result.build(ResultType.ParamError);
    }

    /**
     * <p>
     *     分頁查詢学校信息管理者
     * </p>
     * @throws IOException
     */
    @ApiOperation(value="分頁查詢学校信息管理者", notes="")
    @GetMapping(value = "uisups/listPage/{pageNum}")
    @ResponseBody
    public Result list(@PathVariable Integer pageNum) {
        List<UnivInfoSUP> sups = univInfoSUPService.selectPage(pageNum, 10);
        return Result.build(ResultType.Success).appendData("sups", sups);
    }

    /**
     * <p>
     *     根据学校的中英文名，模糊搜索符合条件的前十条学校信息管理者信息
     * </p>
     * @throws IOException
     */
    @ApiOperation(value="根据学校的中英文名，模糊搜索符合条件的前十条学校信息管理者信息", notes="")
    @GetMapping(value = "uisups/listLikeUnivNameEname/{name}_{ename}/{pageNum}")
    @ResponseBody
    public Result listLikeUnivNameEname(@PathVariable String name, @PathVariable String ename, @PathVariable Integer pageNum) {
        PageInfo pageInfo = univInfoSUPService.selectLikeUnivNameEname(pageNum, 10, name, ename);
        return Result.build(ResultType.Success).appendData("pageInfo", pageInfo);
    }

}
