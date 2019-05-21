package edu.uni.educateAffair.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.educateAffair.bean.Canlendar;
import edu.uni.educateAffair.service.CanlendarService;
import edu.uni.utils.RedisCache;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
    *@Author:梁俊杰
    *@Description:校历表
    *@Date:Created in {16:45} {2019/4/29}
*/
@Api(description = "校历模块")
@Controller
@RequestMapping("json/educateAffair")
public class CanlendarController {
    @Autowired
    private RedisCache cache;
    @Autowired
    private CanlendarService canlendarService;

    static class CacheNameHelper{
        // e_canlendar_{类别id}
        public static final String Receive_CacheNamePrefix = "e_canlendar_";
        // e_canlendar_listAll
        public static final String ListAll_CacheName = "e_canlendar_listAll";
        //e_canlendar_listByCid_{类别id}_{页码}
        private static final String ListByCid_CacheNamePrefix = "e_canlendar_listByCid_";
    }

    /**
        *@Author:梁俊杰
        *@Description:修改校历
        *@Date:Created in {16:44} {2019/4/29}
    */
    @ApiOperation(value = "修改校历" , notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "canlendar", value = "校历实体类", required = true, dataType = "Canlendar"),
            @ApiImplicitParam(name = "sid", value = "学期表id", required = true, dataType = "Long")
    })
    @PostMapping("/canlendar")
    @ResponseBody
    public Result updateCanlendar(@RequestBody List<Canlendar> ListCanlendar, @RequestParam(value = "sid") Long sid) throws SQLException {
        /*        for(Canlendar canlendar : ListCanlendar){
            //初始化校历信息
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(canlendar.getTheDate());
            canlendar.setUniversityId((long)1);
            canlendar.setSemesterId(sid);
            canlendar.setWeek(canlendarService.inputWeek(sid,canlendar.getTheDate()));
            canlendar.setDay(canlendarService.inputDate(canlendar.getTheDate()));
            canlendar.setDatetime(Calendar.getInstance().getTime());
            canlendar.setByWho((long)1);
            canlendar.setDeleted(0);
        }*/
        //执行修改操作
        boolean success;
        success = canlendarService.updateCanlendar(ListCanlendar);
        if (success){
            cache.deleteByPaterm(CacheNameHelper.Receive_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.ListByCid_CacheNamePrefix + "*");
            cache.delete(CacheNameHelper.ListAll_CacheName);
            return Result.build(ResultType.Success);
            //return Result.build(ResultType.Success);
        }else{
            return Result.build(ResultType.Failed);
        }
    }

    /**
        *@Author:梁俊杰
        *@Description:查看所有校历
        *@Date:Created in {14:09} {2019/5/4}
    */
    @ApiOperation(value = "查看所有校历" , notes = "")
    @GetMapping(value = "/canlendar/listAll")
    public void selectAllCanlendar(HttpServletResponse response) throws Exception {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListAll_CacheName;
        String json = cache.get(cacheName);
        if (json==null){
            System.out.println(123);
            json = Result.build(ResultType.Success).appendData("canlendar",canlendarService.selectAll()).convertIntoJSON();
            cache.set(cacheName,json);
        }
        response.getWriter().write(json);
    }

    /**
        *@Author:梁俊杰
        *@Description:根据学期ID获取校历
        *@Date:Created in {16:06} {2019/5/11}
    */


    /**
        *@Author:梁俊杰
        *@Description:根据ID删除校历
        *@Date:Created in {18:46} {2019/5/4}
    */
    @ApiOperation(value = "根据ID删除校历" , notes = "")
    @ApiImplicitParam(name = "id", value = "校历ID", required = true, dataType = "Long")
    @DeleteMapping("/canlendar/did={did}")
    @ResponseBody
    public Result deleteCanlendarById(@PathVariable(value = "did") Long did){
        //删除校历
        boolean success = canlendarService.delete(did);
        //返回值
        if (success){
            cache.deleteByPaterm(CacheNameHelper.Receive_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.ListByCid_CacheNamePrefix + "*");
            cache.delete(CacheNameHelper.ListAll_CacheName);
            return Result.build(ResultType.Success);
        }else{
            return Result.build(ResultType.Failed);
        }
    }

    /**
     *@Author:梁俊杰
     *@Description:删除所有校历缓存
     *@Date:Created in {21:54} {2019/4/30}
     */
    @ApiOperation(value = "删除所有学期缓存" , notes = "")
    @DeleteMapping("/canlendar/deletecache")
    @ResponseBody
    public Result deleteCache() throws Exception{
        cache.delete(CacheNameHelper.ListAll_CacheName);
        cache.deleteByPaterm(CacheNameHelper.Receive_CacheNamePrefix + "*");
        cache.deleteByPaterm(CacheNameHelper.ListByCid_CacheNamePrefix + "*");
        return Result.build(ResultType.Success);
    }

}

