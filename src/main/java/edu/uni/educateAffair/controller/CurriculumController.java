package edu.uni.educateAffair.controller;

import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.educateAffair.VO.CurriculumWithCondition;
import edu.uni.educateAffair.bean.Curriculum;
import edu.uni.educateAffair.VO.CurriculumVO;
import edu.uni.educateAffair.service.CurriculumService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @Author:梁俊杰
 * @Description:课程表模块
 * @Date:Created in 17:12 2019/5/4
 */
@Api(description = "课程表模块")
@Controller
@RequestMapping("json/educateAffair")
public class CurriculumController {


    @Autowired
    private CurriculumService curriculumService;

    @ApiOperation(value = "新增授课安排表" , notes = "")
    @ApiImplicitParam(name = "curriculum", value = "授课安排表实体类", required = true, dataType = "Curriculum")
    @PostMapping(value = "/curriculum")
    @ResponseBody
    public Result insertCurriculum(@RequestBody Curriculum curriculum){
        //初始化授课安排表值
        curriculum.setByWho((long)1);
        curriculum.setDatetime(Calendar.getInstance().getTime());
        curriculum.setDeleted(0);
        //插入授课安排表
        boolean success = curriculumService.insertCurriculum(curriculum);
        //返回值
        if (success){
            /*cache.deleteByPaterm(CacheNameHelper.Receive_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.ListByCid_CacheNamePrefix + "*"); // 保险点。不知道本类别商品的插入会不会影响其他类别商品查询的次序*/
            return Result.build(ResultType.Success).appendData("id",curriculum.getId());
        }else{
            return Result.build(ResultType.Failed);
        }
    }
    /**
        *@Author:梁俊杰
        *@Description:修改授课安排表
        *@Date:Created in {23:02} {2019/5/4}
    */
    @ApiOperation(value = "修改授课安排表" , notes = "")
    @ApiImplicitParam(name = "curriculum", value = "授课安排表实体类", required = true, dataType = "Curriculum")
    @PutMapping(value = "/curriculum")
    @ResponseBody
    public Result updateCurriculum(@RequestBody Curriculum curriculum){
        boolean success = curriculumService.updateCurriculum(curriculum);
        //返回值
        if (success){
            /*cache.deleteByPaterm(CacheNameHelper.Receive_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.ListByCid_CacheNamePrefix + "*"); // 保险点。不知道本类别商品的插入会不会影响其他类别商品查询的次序*/
            return Result.build(ResultType.Success).appendData("id",curriculum.getId());
        }else{
            return Result.build(ResultType.Failed);
        }
    }
    /**
     *@Author:梁俊杰
     *@Description:查看授课安排表
     *@Date:Created in {23:37} {2019/5/4}
     */
    @ApiOperation(value = "查看所有授课安排表" , notes = "")
    @GetMapping(value = "/curriculum/listAll")
    public void selectAllCurriculum(HttpServletResponse response) throws Exception {
        response.setContentType("application/json;charset=utf-8");
        String json = Result.build(ResultType.Success).appendData("curriculum",curriculumService.selectAll()).convertIntoJSON();
        response.getWriter().write(json);
    }
    /**
        *@Author:梁俊杰
        *@Description:删除授课安排表
        *@Date:Created in {23:45} {2019/5/4}
    */
    @ApiOperation(value = "根据ID删除授课安排表" , notes = "")
    @ApiImplicitParam(name = "id", value = "授课安排表id", required = true, dataType = "Long")
    @DeleteMapping(value = "/curriculum/did={did}")
    @ResponseBody
    public Result deleteCurriculum(@PathVariable(value = "did") Long did){
        boolean success = curriculumService.delete(did);
        //返回值
        if (success){
            /*cache.deleteByPaterm(CacheNameHelper.Receive_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.ListByCid_CacheNamePrefix + "*"); // 保险点。不知道本类别商品的插入会不会影响其他类别商品查询的次序*/
            return Result.build(ResultType.Success);
        }else{
            return Result.build(ResultType.Failed);
        }
    }

    @ApiOperation(value = "课程表获取name" , notes = "")
    @GetMapping(value = "/curriculum/getCurriculumName")
    @ResponseBody
    public void tttt(@RequestBody CurriculumWithCondition curriculumWithCondition, HttpServletResponse response) throws Exception {
        response.setContentType("application/json;charset=utf-8");
        List<CurriculumVO> curriculumVOS = curriculumService.Transform(curriculumWithCondition);
        String json = Result.build(ResultType.Success).appendData("curriculumVOS",curriculumVOS).convertIntoJSON();
       // System.out.println(vo.toString());
        response.getWriter().write(json);
        //return Result.build(ResultType.Success);
    }


    @ApiOperation(value = "通过学期集合+教师id集合+课程id集合+班级id集合获取课程表信息" , notes = "")
    @GetMapping(value = "/curriculum/getCurriculumMsg")
    @ResponseBody
    public void getCurriculumMsg(@RequestParam(required = false) ArrayList<Long> semesterId,
                                 @RequestParam(required = false) ArrayList<Long> employeeId,
                                 @RequestParam(required = false) ArrayList<Long> courseId,
                                 @RequestParam(required = false) ArrayList<Long> classId,
                                 HttpServletResponse response) throws Exception {
        response.setContentType("application/json;charset=utf-8");
/*        employeeId.add((long) 229);
        employeeId.add((long) 203);*/
        /*courseId.add((long) 1);
        classId.add((long) 1);*/
        // System.out.println(vo.toString());
        String json = Result.build(ResultType.Success).appendData("curriculumMsg",curriculumService.selectCurriculumByCondition(semesterId,employeeId,courseId,classId)).convertIntoJSON();
        response.getWriter().write(json);
        //return Result.build(ResultType.Success);
    }

}
