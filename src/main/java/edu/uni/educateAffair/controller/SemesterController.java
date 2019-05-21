package edu.uni.educateAffair.controller;

import com.alibaba.druid.support.json.JSONParser;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.util.JSONPObject;
import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.educateAffair.bean.Canlendar;
import edu.uni.educateAffair.bean.Semester;
import edu.uni.educateAffair.service.CanlendarService;
import edu.uni.educateAffair.service.SemesterService;
import edu.uni.utils.JsonUtils;
import edu.uni.utils.RedisCache;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Author:梁俊杰
 * @Description:学期表
 * @Date:Created in 8:28 2019/4/29
 */

@Api(description = "学期表模块")
@Controller
@RequestMapping("/json/educateAffair")
public class SemesterController {
    @Autowired
    private SemesterService semesterService;
    @Autowired
    private CanlendarService canlendarService;
    @Autowired
    private RedisCache cache;


    /**
     * 内部类，专门用来管理每个get方法所对应缓存的名称。
     */
    static class CacheNameHelper{
        // e_semester_{类别id}
        private static final String Receive_CacheNamePrefix = "e_semester_";
        // e_semesters_listAll
        private static final String ListAll_CacheName = "e_semesters_listAll";
        // e_p_products_listByCid_{类别id}_{页码}
        private static final String ListBySid_CacheNamePrefix = "e_semester_listBySid_";
    }

    @ApiOperation(value = "新增学期表" , notes = "")
    @ApiImplicitParam(name = "semester" , value = "学期实体类" , required = true ,dataType = "Semester")
    @PostMapping("/semester")
    @ResponseBody
    public Result insertSemester(@RequestBody Semester semester) throws SQLException {
        //初始化学期信息
        semester.setByWho((long) 0);
        semester.setDatetime(Calendar.getInstance().getTime());
        semester.setUniversityId((long) 1);
        semester.setDeleted(0);
        //初始化校历
        List<Canlendar> canlendarList = new ArrayList<Canlendar>();
        boolean success = semesterService.insert(semester,canlendarList);

        //返回校历ID+日期体
/*        List<Canlendar> canlendar = new ArrayList<Canlendar>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for(int i = 0 ; i < canlendarList.size(); i++){
            canlendar.add(canlendarList.get(i));
        }*/
        if (success){
            cache.delete(CacheNameHelper.ListBySid_CacheNamePrefix + "*");
            cache.delete(CacheNameHelper.ListAll_CacheName);
            return Result.build(ResultType.Success).appendData("id",semester.getId());
        }else{
            return Result.build(ResultType.Failed);
        }
    }
    /**
        *@Author:梁俊杰
        *@Description:查找所有学期信息
        *@Date:Created in {14:35} {2019/4/30}
    */
    @ApiOperation(value = "查找所有学期信息" , notes = "")
    @GetMapping(value = "/semester/listAll")
    public void selectAll(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Calendar.getInstance().getTime());
        String cacheName = CacheNameHelper.ListAll_CacheName;
        String json = cache.get(cacheName);
        Long sid = null;
        List<Semester> semesterList = semesterService.selectAll();
        for (Semester semester : semesterList){
            if(calendar.getTime().after(semester.getStart()) && calendar.getTime().before(semester.getEnd())){
                sid = semester.getId();
            }
        }
        if(json == null){
            json = Result.build(ResultType.Success).appendData("semesters",semesterList).appendData("NowSemester",sid).convertIntoJSON();
            cache.set(cacheName,json);
        }

        response.getWriter().write(json);
    }
/**
    *@Author:梁俊杰
    *@Description:按ID查找学期信息
    *@Date:Created in {14:48} {2019/4/30}
*/
    @ApiOperation(value = "按ID查找学期信息" , notes = "")
    @ApiImplicitParam(name = "id" , value = "学期ID" , required = true ,dataType = "Long")
    @GetMapping("/semester/id={id}")
    public void selectSemesterById(@PathVariable(value = "id") Long id , HttpServletResponse response) throws IOException{
        response.setContentType("application/json;charset=utf-8");
/*        String cacheName = CacheNameHelper.ListBySid_CacheNamePrefix + id;
        String json = cache.get(cacheName);*/
            String json = Result.build(ResultType.Success).appendData("semester",semesterService.selectById(id)).appendData("canlendar",canlendarService.selectBySemesterId(id)).convertIntoJSON();
            //cache.set(cacheName,json);
        response.getWriter().write(json);
    }
/**
    *@Author:梁俊杰
    *@Description:修改学期信息
    *@Date:Created in {21:54} {2019/4/30}
*/
    @ApiOperation(value = "按ID修改学期信息" , notes = "")
    @ApiImplicitParam(name = "semester" , value = "学期实体类" , required = true , dataType = "Semester")
    @PutMapping("/semester")
    @ResponseBody
    public Result updateSemesterById(@RequestBody Semester semester) throws Exception{
        semester.setByWho((long) 0);
        semester.setDatetime(Calendar.getInstance().getTime());
        semester.setUniversityId((long) 1);
        semester.setDeleted(1);

        Long id = semester.getId();

        boolean success = semesterService.update(semester);
        if (success){
            cache.delete(CacheNameHelper.Receive_CacheNamePrefix + id);
            cache.delete(CacheNameHelper.ListAll_CacheName);
            return Result.build(ResultType.Success);
        }else{
            return Result.build(ResultType.Failed);
        }
    }
    /**
     *@Author:梁俊杰
     *@Description:删除学期信息
     *@Date:Created in {21:54} {2019/4/30}
     */
    @ApiOperation(value = "按ID删除学期信息" , notes = "")
    @ApiImplicitParam(name = "id" , value = "学期id" , required = true , dataType = "Long")
    @DeleteMapping("/semester/did={did}")
    @ResponseBody
    public Result deleteSemesterById(@PathVariable(value = "did") Long did) throws Exception{
        boolean success = semesterService.delete(did);
        if (success){
            cache.delete(CacheNameHelper.ListAll_CacheName);
            cache.delete(CacheNameHelper.Receive_CacheNamePrefix + did);
            return Result.build(ResultType.Success);
        }else{
            return Result.build(ResultType.Failed);
        }
    }

    /**
     *@Author:梁俊杰
     *@Description:删除所有学期缓存
     *@Date:Created in {21:54} {2019/4/30}
     */
    @ApiOperation(value = "删除所有学期缓存" , notes = "")
    @DeleteMapping("/semester/deletecache")
    @ResponseBody
    public Result deleteCache() throws Exception{
            cache.delete(CacheNameHelper.ListAll_CacheName);
            cache.deleteByPaterm(CacheNameHelper.Receive_CacheNamePrefix + "*");
            cache.deleteByPaterm(CacheNameHelper.ListBySid_CacheNamePrefix + "*");
            return Result.build(ResultType.Success);
    }

    /**
        *@Author:梁俊杰
        *@Description:返回当前时间所属学期
        *@Date:Created in {13:02} {2019/5/16}
    */
    @ApiOperation(value = "按ID删除学期信息" , notes = "")
    @GetMapping("/semester/getNowSemester")
    public void selectNowSemester(HttpServletResponse response) throws Exception{
        response.setContentType("application/json;charset=utf-8");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Calendar.getInstance().getTime());
        System.out.println(calendar.getTime());
        List<Semester> semesterList = semesterService.selectAll();
        Long sid = null;
        for (Semester semester : semesterList){
            if(calendar.getTime().after(semester.getStart()) && calendar.getTime().before(semester.getEnd())){
                sid = semester.getId();
            }
        }
        String json = Result.build(ResultType.Success).appendData("sid",sid).convertIntoJSON();
        response.getWriter().write(json);
    }

    /**
     *@Author:梁俊杰
     *@Description:测试
     *@Date:Created in {21:54} {2019/4/30}
     */
    @ApiOperation(value = "测试" , notes = "")
    @GetMapping("/semester/ttttt")
    @ResponseBody
    public Result ttt() throws Exception{
        List<Long> id = semesterService.selectByfromtoEnd((long)64,(long)67);
        //semesterService.selectBySemesterIdAndCount((long)50 ,2);
        for(Long i : id){
            System.out.println(i);
        }
        return Result.build(ResultType.Success);
    }

}
