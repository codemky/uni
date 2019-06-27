package edu.uni.userBaseInfo1.controller;

import edu.uni.auth.service.AuthService;
import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.userBaseInfo1.bean.*;
import edu.uni.userBaseInfo1.service.*;
import edu.uni.userBaseInfo1.utils.AddressUtil;
import edu.uni.utils.RedisCache;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author laizhouhao
 * @Description 关于地址信息模块的Controller层（Http URL请求）的具体实现方法
 * @Date 16:15 2019/4/29
 **/
//填写description内容可以在测试模块显示相应的文字和模块
@Api(description = "地址信息模块")
//Controller类（或者说Http）的请求路径
//如果添加了路径，则在需要调用该类的方法时需要在方法请求mapping路径前加上类的mapping路径
@RequestMapping("json/address")
//标志这个类是一个controller类，用于被Spring扫描然后配置添加和配置相应的bean
@RestController
public class AddressController {
    //把Address的Service接口层的所有方法自动装配到该对象中
    @Autowired
    private AddressService addressService;
    @Autowired
    private ApprovalMainService approvalMainService;
    @Autowired
    private UserinfoApplyService userinfoApplyService;
    @Autowired
    private UserinfoApplyApprovalService userinfoApplyApprovalService;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;
    @Autowired
    private StudentService studentService;

    //把缓存工具类RedisCache相应的方法自动装配到该对象
    @Autowired
    private RedisCache cache;

    //内部类，专门用来管理每个get方法所对应缓存的名称。
    static class CacheNameHelper{
        // ub1_a_address_{地址记录id}
        public static final String Receive_CacheNamePrefix = "ub1_a_address_";
        // ub1_a_address_listAll
        public static final String ListAll_CacheName = "ub1_a_address_listAll";
    }

//    @ApiOperation( value = "测试地址工具类",notes = "未测试" )
//    @GetMapping("/test")
//    @ResponseBody
//    //@ApiImplicitParam(name = "id", value = "Address表的一个id", required = false, dataType = "Long" , paramType = "path")
//    public void test() throws IOException {
//        ReadTest readTest = new ReadTest();
//        readTest.printExecl();
//        AddressUtil addressUtil = new AddressUtil();
//        //System.out.println(new UserinfoApplyApprovalController().isDepartmentSame(727,0));
//        System.out.println("所有国家："+addressUtil.SelectCountries().toString());
//        System.out.println("根据国家编码查询所有省份："+addressUtil.SelectStates(1).toString());
//        System.out.println("根据省份编码查询所有城市："+addressUtil.SelectCities(13).toString());
//        System.out.println("根据城市编码查询所有县/区："+addressUtil.SelectAreas(1101).toString());
//        System.out.println("根据县/区编码查询所有街道："+addressUtil.SelectStreets(110101).toString());
//
//    }




    /**
     * Author: mokuanyuan 16:05 2019/6/11
     * @param cityName
     * @return Result
     * @apiNote: 根据城市城市名称模糊搜索城市
     */
    @GetMapping("getCityByName")
    @ResponseBody
    public Result getCityByName(@RequestParam("cityName") String cityName) {
        edu.uni.auth.bean.User loginUser = authService.getUser();
        if ( cityName == null)
            return Result.build(ResultType.ParamError);
        if ( loginUser == null )
            return Result.build(ResultType.Failed, "你沒有登錄");

        List<AddrCity> addrCities = addressService.selectCityByName(cityName);

        return Result.build(ResultType.Success).appendData("cities",addrCities);

    }

    /**
     * Author: mokuanyuan 16:05 2019/6/11
     * @param response
     * @return Result
     * @apiNote: 根据用户id获取地址信息 ，但user_id为-1时为根据登录状态的用户信息获取相应的地址信息（只查询自己的内容）
     */
    @GetMapping("getAddressInformation/{userId}")
    @ResponseBody
    public Result getAddressInformation(@PathVariable Long userId , HttpServletResponse response){
        edu.uni.auth.bean.User loginUser = authService.getUser();
        if(userId == null)
            return Result.build(ResultType.ParamError);
        Long tempUserId = userId;
        if(userId == -1 ){
            if(loginUser == null){
                return Result.build(ResultType.Failed, "你沒有登錄");
            }else
                userId = loginUser.getId();
        }

        User user = userService.selectUserById(userId);
//        List<Integer> roles = otherEmployPositionService.selectEmployeeRoleByUserId();
        if( tempUserId != -1 )
            switch (user.getUserType()){
                case 1:
                    if( !studentService.whetherSeeStudent(tempUserId,loginUser.getId()))
                        return Result.build(ResultType.Disallow,"登录用户无权查看该学生用户的信息");
                    break;
                case 2:
                    if( !studentService.whetherSeeEmployee(tempUserId,loginUser.getId()))
                        return Result.build(ResultType.Disallow,"登录用户无权查看该职员用户的信息");
                    break;
                case 3:
                    if( !studentService.whetherSeeRelation(tempUserId,loginUser.getId()))
                        return Result.build(ResultType.Disallow,"登录用户无权查看该学生亲属用户的信息");
                    break;
            }


        List<Address> addresses = addressService.selectByUserId(userId);
        if(addresses.size() == 0)
            return Result.build(ResultType.Failed,"该用户的地址信息“为空");
        List<Address> addressOfFilter = addressService.filterAddress(addresses);
        List<List> addressList = new ArrayList<>();
        addressService.selectAllInfoToList(addressList,addressOfFilter);
        return Result.build(ResultType.Success).appendData("address",addressList)
                .appendData("addressBase",addresses);


    }


    /**
     * Author: chenenru 18:50 2019/5/10
     * @param countryCode
     * @return Response
     * @apiNote: 根据国家编码查询所有省份
     */
    //根据国家编码查询所有省份
    @ApiOperation( value = "根据国家编码查询所有省份",notes = "2019年5月10日 18:50:00 已通过测试" )
    @GetMapping("address/addrState/{countryCode}")
    @ApiImplicitParam(name = "countryCode", value = "国家的编码", required = false, dataType = "Long" , paramType = "path")
    @ResponseBody
    public Result selectByCountryCode(@PathVariable Long countryCode,HttpServletResponse response) throws IOException{
        //如果在缓存中找不到，那就从数据库里找
        AddressUtil addressUtil = new AddressUtil();
        List<AddrState> addrStates = addressUtil.SelectStates(countryCode);
        System.out.println("-----"+addrStates.toString());
        return Result.build(ResultType.Success).appendData("addrStates",addrStates);

    }

    /**
     * Author: chenenru 18:50 2019/5/10
     * @param stateCode
     * @return Response
     * @apiNote: 根据省份编码查询所有城市
     */
    //根据省份编码查询所有城市
    @ApiOperation( value = "根据省份编码查询所有城市",notes = "2019年5月10日 18:51:43 已通过测试" )
    @GetMapping("address/addrCity/{stateCode}")
    @ApiImplicitParam(name = "stateCode", value = "省份的编码", required = false, dataType = "Long" , paramType = "path")
    @ResponseBody
    public Result selectByStateCode(@PathVariable Long stateCode,HttpServletResponse response) throws IOException{
        //如果在缓存中找不到，那就从数据库里找
        AddressUtil addressUtil = new AddressUtil();
        List<AddrCity> addrCities = addressUtil.SelectCities(stateCode);


        return Result.build(ResultType.Success).appendData("addrCities",addrCities);
    }

    /**
     * Author: chenenru 18:50 2019/5/10
     * @param cityCode
     * @return Response
     * @apiNote: 根据城市编码查询所有县
     */
    //根据城市编码查询所有县/区
    @ApiOperation( value = "根据城市编码查询所有县/区",notes = "2019年5月10日 18:51:51 已通过测试" )
    @GetMapping("address/addrArea/{cityCode}")
    @ApiImplicitParam(name = "cityCode", value = "cityCode", required = false, dataType = "Long" , paramType = "path")
    @ResponseBody
    public Result selectByCityCode(@PathVariable Long cityCode,HttpServletResponse response) throws IOException{

        AddressUtil addressUtil = new AddressUtil();
        List<AddrArea> addrAreas = addressUtil.SelectAreas(cityCode);
        return Result.build(ResultType.Success).appendData("addrAreas",addrAreas);
    }

    /**
     * Author: chenenru 18:50 2019/5/10
     * @param areaCode
     * @return Response
     * @apiNote: 根据县/区编码查询所有街道
     */
    //根据县/区编码查询所有街道
    @ApiOperation( value = "根据县/区编码查询所有街道",notes = "2019年5月10日 18:52:28 已通过测试" )
    @GetMapping("address/addrStreet/{areaCode}")
    @ApiImplicitParam(name = "areaCode", value = "县/区的编码", required = false, dataType = "Long" , paramType = "path")
    @ResponseBody
    public Result selectByAreaCode(@PathVariable Long areaCode,HttpServletResponse response) throws IOException{

        AddressUtil addressUtil = new AddressUtil();
        List<AddrStreet> addrStreets = addressUtil.SelectStreets(areaCode);

        return Result.build(ResultType.Success).appendData("addrStreets",addrStreets);
    }

//    /**
//     * Author: laizhouhao 9:55 2019/4/30
//     * @param id
//     * @return response
//     * @apiNote: 获取地址信息详细
//     */
//    @ApiOperation( value = "以一个id获取一条地址记录详情",notes = "2019-5-5 15:53:53已通过测试" )
//    @GetMapping("address/{id}")
//    @ApiImplicitParam(name = "id", value = "Address表的一个id", required = false, dataType = "Long" , paramType = "path")
//    @ResponseBody
//    public void receive(@PathVariable Long id, HttpServletResponse response) throws IOException {
//        //设置返回的数据格式
//        response.setContentType("application/json;charset=utf-8");
//        //拼接缓存键名（字符串）
//        String cacheName = AddressController.CacheNameHelper.Receive_CacheNamePrefix + id;
//        //尝试在缓存中通过键名获取相应的键值
//        //因为在Redis中，数据是以”“” "键-值"对 的形式储存的
//        String json = cache.get(cacheName);
//        //如果在缓存中找不到，那就从数据库里找
//        if(json == null){
//            Address address = addressService.selectById(id);
//            //把查询到的结果用Result工具类转换成json格式的字符串
//            json = Result.build(ResultType.Success).appendData("address",address).convertIntoJSON();
//            //如果有查询到数据，就把在数据库查到的数据放到缓存中
//            if(address != null){
//                cache.set(cacheName,json);
//            }
//        }
//        //到最后通过response对象返回json格式字符串的数据
//        response.getWriter().write(json);
//
//    }

//    /**
//     * Author: laizhouhao 16:26 2019/4/29
//     * @return response
//     * @apiNote: 查询地址的所有记录
//     */
//    @ApiOperation( value = "获取所有地址记录的内容",notes = "2019-5-5 15:53:53已通过测试" )
//    @GetMapping("addresses/listAll")
//    @ResponseBody
//    public void selectAll(HttpServletResponse response)throws Exception{
//        response.setContentType("application/json;charset=utf-8");
//        String cacheName = AddressController.CacheNameHelper.ListAll_CacheName;
//        String json = cache.get(cacheName);
//        if(json==null){
//            json = Result.build(ResultType.Success).appendData("addresses", addressService.selectAll()).convertIntoJSON();
//            cache.set(json, cacheName);
//        }
//        response.getWriter().write(json);
//    }

//    /**
//     * Author: chenenru 0:49 2019/5/5
//     * @apiNote: 根据用户的id查询对应的地址记录
//     */
//    @ApiOperation( value = "根据用户的id查询对应的地址的内容",notes = "2019-5-5 15:53:53已通过测试" )
//    @GetMapping("addressByUId/{userId}")
//    @ResponseBody
//    public void selectByUserId(@PathVariable Long userId,HttpServletResponse response) throws IOException{
//        response.setContentType("application/json;charset=utf-8");
//        String cacheName = AddressController.CacheNameHelper.ListAll_CacheName+userId;
//        String json = cache.get(cacheName);
//        if(json == null){
//            json = Result.build(ResultType.Success)
//                    .appendData("addresses",addressService.selectByUserId(userId)).convertIntoJSON();
//            cache.set(cacheName,json);
//        }
//        response.getWriter().write(json);
//    }

    /**
     * Author: laizhouhao 16:40 2019/4/29
     * @param address
     * @return 新增地址信息结果
     */
    @ApiOperation(value="新增地址信息记录", notes="2019-5-5 15:53:53已通过测试")
    @ApiImplicitParam(name = "address", value = "地址详情实体", required = true, dataType = "Address")
    @PostMapping("/address")  //post请求方式
    @ResponseBody
    public Result create(@RequestBody(required = false)Address address){
        if(address!=null){
            boolean success = addressService.insert(address);
            if(success){
                cache.delete(AddressController.CacheNameHelper.ListAll_CacheName);
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }

    /**
     * Author: laizhouhao 16:47 2019/4/29
     * @param id
     * @return 删除地址信息结果
     * @apiNote 根据id删除地址信息
     */
    @ApiOperation(value="根据id删除地址信息", notes="2019-5-5 15:53:53已通过测试")
    @ApiImplicitParam(name = "id", value = "地址id", required = true, dataType = "Long", paramType = "path")
    @DeleteMapping("/address/{id}")   //delete请求
    @ResponseBody
    public Result destroy(@PathVariable long id){
        boolean success = addressService.delete(id);
        if(success){
            // 清空相关缓存
            cache.delete(AddressController.CacheNameHelper.ListAll_CacheName);
            return Result.build(ResultType.Success);
        }else{
            return Result.build(ResultType.Failed);
        }
    }

    /**
     * Author: laizhouhao 11:01 2019/4/30
     * @param address
     * @return 更新操作结果
     * @apiNote 更新地址信息
     */
    @ApiOperation(value="更新地址信息", notes="2019-5-5 15:53:53已通过测试")
    @ApiImplicitParam(name = "address", value = "地址信息详情实体", required = true, dataType = "Address")
    @PutMapping("/address")   //Put请求
    @ResponseBody
    public Result update(@RequestBody(required = false)Address address){
        if(address != null && address.getId() != null){
            boolean success = addressService.update(address);
            if(success){
                //清除相应的缓存
                cache.delete(AddressController.CacheNameHelper.Receive_CacheNamePrefix + address.getId());
                cache.delete(AddressController.CacheNameHelper.ListAll_CacheName);
                return Result.build(ResultType.Success);
            }else{
                return Result.build(ResultType.Failed);
            }
        }
        return Result.build(ResultType.ParamError);
    }

//    /**
//     * Author: laizhouhao 20:11 2019/5/13
//     * @param requestMessage
//     * @return Result
//     * @apiNote: 申请修改地址信息, 点击申请时
//     */
//    @ApiOperation(value="申请修改地址信息, 点击申请时", notes="未测试")
//    @ApiImplicitParam(name = "requestMessage", value = "请求参数实体", required = true, dataType = "RequestMessage")
//    @PostMapping("applyModifyAddress/")
//    @ResponseBody
//    public Result ApplyModifyAddress(@RequestBody RequestMessage requestMessage){
//        //判断前端传过来的值是否为空
//        if(requestMessage.getAddress()!=null && requestMessage.getByWho()!=null && requestMessage.getUserinfoApply()!=null){
//            boolean success = addressService.clickApplyAddress(requestMessage);
//            if(success){
//                //清除相应的缓存
//                cache.delete(AddressController.CacheNameHelper.Receive_CacheNamePrefix + "applyModifyAddress111");
//                cache.delete(AddressController.CacheNameHelper.ListAll_CacheName);
//                return Result.build(ResultType.Success);
//            }else{
//                return Result.build(ResultType.Failed);
//            }
//        }
//        return Result.build(ResultType.ParamError);
//    }
//
}
