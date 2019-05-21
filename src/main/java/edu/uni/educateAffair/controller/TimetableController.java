package edu.uni.educateAffair.controller;

import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.educateAffair.bean.Timetable;
import edu.uni.educateAffair.service.TimetableService;
import edu.uni.utils.RedisCache;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author:梁俊杰
 * @Description:作息表
 * @Date:Created in 22:17 2019/5/10
 */
@Api(description = "作息表模块")
@Controller
@RequestMapping("/json/educateAffair")
public class TimetableController {

    @Autowired
    private TimetableService timetableService;
    @Autowired
    private RedisCache cache;

    /**
     * 内部类，专门用来管理每个get方法所对应缓存的名称。
     */
    static class CacheNameHelper{
        // e_timetable_{类别id}
        private static final String Receive_CacheNamePrefix = "e_timetable_";
        // e_timetables_listAll
        private static final String ListAll_CacheName = "e_timetables_listAll";
        // e_timetable_listByCid_{类别id}_{页码}
        private static final String ListBySid_CacheNamePrefix = "e_timetable_listBySid_";
    }


    @ApiOperation(value = "查找所有作息表信息" , notes = "")
    @GetMapping(value = "/timetable/listAll")
    public void selectAll(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cacheName = CacheNameHelper.ListAll_CacheName;
        String json = cache.get(cacheName);
        if(json == null){
            json = Result.build(ResultType.Success).appendData("timetable",timetableService.selectAll()).convertIntoJSON();
            cache.set(cacheName,json);
        }
        response.getWriter().write(json);
    }
}
