package edu.uni.userBaseInfo1.controller;

import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.userBaseInfo1.bean.*;
import edu.uni.userBaseInfo1.service.*;
import edu.uni.userBaseInfo1.utils.UserInfo;
import edu.uni.utils.RedisCache;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author laizhouhao
 * @Description 关于查看个人信息的控制层
 * @Date 16:01 2019/5/7
 **/
//填写description内容可以在测试模块显示相应的文字和模块
@Api(description = "查看用户个人信息")
//Controller类（或者说Http）的请求路径
//如果添加了路径，则在需要调用该类的方法时需要在方法请求mapping路径前加上类的mapping路径
@RequestMapping("json/studentInfo")
//标志这个类是一个controller类，用于被Spring扫描然后配置添加和配置相应的bean
@Controller
public class StudentInfoController {
    //用户sevice层对象
    @Autowired
    private UserService userService;
    @Autowired
    private PictureService pictureService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private PoliticalAffiliationService politicalAffiliationService;
    @Autowired
    private AddrCountryService addrCountryService;
    @Autowired
    private AddrStateService addrStateService;
    @Autowired
    private AddrCityService addrCityService;
    @Autowired
    private AddrAreaService addrAreaService;
    @Autowired
    private AddrStreetService addrStreetService;
    @Autowired
    private StudentRelationService studentRelationService;

    @Autowired  //把缓存工具类RedisCache相应的方法自动装配到该对象
    private RedisCache cache;

    //内部类，专门用来管理每个get方法所对应缓存的名称。
    static class CacheNameHelper{
        // ub1_s_studentInfo_{用户记录id}
        public static final String Receive_CacheNamePrefix = "ub1_s_studentInfo_";
        // ub1_s_studentInfo_listAll
        public static final String ListAll_CacheName = "ub1_s_studentInfo_listAll";
    }

    /**
     * Author: laizhouhao 16:08 2019/5/7
     * @param id
     * @return response
     * @apiNote: 根据学生的用户id查找学生个人信息
     */
    @ApiOperation( value = "根据学生的用户id查找学生个人信息",notes = "2019年5月7日 21:45:45 已通过测试" )
    @GetMapping("info/student/All/{id}")
    @ApiImplicitParam(name = "id", value = "用户id", required = false, dataType = "Long" , paramType = "path")
    @ResponseBody
    public void receive(@PathVariable Long id, HttpServletResponse response) throws IOException {
        //检验页面传来的id是否存在
        if(id != null){
            //将查询出来的学生个人信息放入工具类UserInfo的对象中
            UserInfo userInfo = new UserInfo();
            List<User> users = new ArrayList<>();
            List<Picture> pictures = new ArrayList<>();
            List<Address> addresses = new ArrayList<>();
            List<Student> students = new ArrayList<>();
            List<PoliticalAffiliation> politicalAffiliations = new ArrayList<>();
            List<AddrCountry> addrCountries = new ArrayList<>();
            List<AddrState> addrStates = new ArrayList<>();
            List<AddrCity> addrCities = new ArrayList<>();
            List<AddrArea>addrAreas = new ArrayList<>();
            List<AddrStreet>addrStreets = new ArrayList<>();

            //根据id查询用户信息
            users.add(userService.selectUserById(id));

            //根据该id查询有效的照片信息
            PictureExample pictureExample = new PictureExample();
            pictureExample.createCriteria().andUserIdEqualTo(id).andDeletedEqualTo(false);
            pictures = pictureService.selectByExample(pictureExample);

            //根据id查询有效的学生信息
            StudentExample studentExample = new StudentExample();
            studentExample.createCriteria().andUserIdEqualTo(id).andDeletedEqualTo(false);
            students = studentService.selectByExample(studentExample);

            //根据查找出来的政治面貌id获取用户的政治面貌
            PoliticalAffiliation politicalAffiliation =
                    politicalAffiliationService.selectPoliticalAffiliationById(students.get(0).getPoliticalId());
            politicalAffiliations.add(politicalAffiliation);

            //根据id查询有效的当前住址信息
            AddressExample addressExamplePre = new AddressExample();
            AddressExample addressExampleContact = new AddressExample();
            addressExamplePre.createCriteria().andUserIdEqualTo(id).andFlagEqualTo(0).andDeletedEqualTo(false);
            addresses.add(addressService.selectByExample(addressExamplePre).get(0));
            //获取当前住址的国家、市、区、街道信息
            //获取国家
            addrCountries.add(addrCountryService.selectAddrCountryById(addresses.get(0).getCountry()));
            //获取省
            addrStates.add(addrStateService.selectAddrStateById(addresses.get(0).getState()));
            //获取城市
            addrCities.add(addrCityService.selectAddrCityById(addresses.get(0).getCity()));
            //获取区
            addrAreas.add(addrAreaService.selectAddrAreaById(addresses.get(0).getArea()));
            //获取街道
            addrStreets.add(addrStreetService.selectAddrStreetById(addresses.get(0).getStreet()));

            //根据id查询有效的通信地址信息
            addressExampleContact.createCriteria().andUserIdEqualTo(id).andFlagEqualTo(3).andDeletedEqualTo(false);
            addresses.add(addressService.selectByExample(addressExampleContact).get(0));
            //获取当前住址的国家、市、区、街道信息
            //获取国家
            addrCountries.add(addrCountryService.selectAddrCountryById(addresses.get(1).getCountry()));
            //获取省
            addrStates.add(addrStateService.selectAddrStateById(addresses.get(1).getState()));
            //获取城市
            addrCities.add(addrCityService.selectAddrCityById(addresses.get(1).getCity()));
            //获取区
            addrAreas.add(addrAreaService.selectAddrAreaById(addresses.get(1).getArea()));
            //获取街道
            addrStreets.add(addrStreetService.selectAddrStreetById(addresses.get(1).getStreet()));

            //将查询出来的各类信息放入用户信息对象中
            userInfo.setUsers(users);
            userInfo.setPictures(pictures);
            userInfo.setAddresses(addresses);
            userInfo.setStudents(students);
            userInfo.setPoliticalAffiliations(politicalAffiliations);
            userInfo.setAddrCountries(addrCountries);
            userInfo.setAddrStates(addrStates);
            userInfo.setAddrCities(addrCities);
            userInfo.setAddrAreas(addrAreas);
            userInfo.setAddrStreets(addrStreets);

            //设置返回的数据格式
            response.setContentType("application/json;charset=utf-8");
            //拼接缓存键名（字符串）
            String cacheName = StudentInfoController.CacheNameHelper.Receive_CacheNamePrefix + id +"ccc";
            //尝试在缓存中通过键名获取相应的键值
            //因为在Redis中，数据是以”“” "键-值"对 的形式储存的
            String json = cache.get(cacheName);
            //如果在缓存中找不到，那就从数据库里找
            if(json == null){
                json = Result.build(ResultType.Success)
                        .appendData("userInfo",userInfo).convertIntoJSON();
                cache.set(cacheName,json);
            }
            //到最后通过response对象返回json格式字符串的数据
            response.getWriter().write(json);
        }
    }

}
