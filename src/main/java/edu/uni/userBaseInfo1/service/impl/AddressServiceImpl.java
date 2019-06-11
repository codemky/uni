package edu.uni.userBaseInfo1.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.auth.bean.Role;
import edu.uni.auth.mapper.RoleMapper;
import edu.uni.example.config.ExampleConfig;
import edu.uni.userBaseInfo1.bean.*;
import edu.uni.userBaseInfo1.mapper.AddressMapper;
import edu.uni.userBaseInfo1.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @Author laizhouhao
 * @Description Address实体类的service层接口的实现类
 * @Date 15:49 2019/4/29
 **/
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
//Service类的注解，标志这是一个服务层接口类，这样才能被Spring”“”“”“”"扫描"到
@SuppressWarnings("ALL")
@Service
public class AddressServiceImpl implements AddressService {
    //持久层接口的对象
    @Autowired
    private AddressMapper addressMapper;//爆红的话编译器的原因，不影响运行
    @Autowired
    private ApprovalMainService approvalMainService;
    @Autowired
    private UserinfoApplyService userinfoApplyService;
    @Autowired
    private ApprovalStepInchargeService approvalStepInchargeService;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserinfoApplyApprovalService userinfoApplyApprovalService;
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


    //配置类，规定了上传文件的路径和分页查询每一页的记录数
    @Autowired
    private ExampleConfig config;

    /**
     * Author: laizhouhao 15:17 2019/4/29
     * @return List<Address>
     * @apiNote: 查询所有的地址
     */
    @Override
    public List<Address> selectAll() {
        return addressMapper.selectByExample(null);
    }

    /**
     * Author: laizhouhao 15:39 2019/4/29
     * @param id
     * @return Address
     * @apiNote: 根据id查询出一条地址信息
     */
    @Override
    public Address selectById(Long id) {
        return addressMapper.selectByPrimaryKey(id);
    }

//    /**
//     * Author: chenenru 1:24 2019/5/5
//     * @param userId
//     * @return Address
//     * @apiNote: 根据用户的id查询出地址信息
//     */
//    @Override
//    public List<Address> selectByUserId(Long userId) {
//        return addressMapper.selectByUserId(userId);
//    }

    /**
     * Author: mokuanyuan 16:21 2019/6/7
     * @param userId
     * @return Address
     * @apiNote: 根据用户的id查询出地址信息且信息是有效的
     */
    @Override
    public List<Address> selectByUserId(Long userId) {
        AddressExample addressExample = new AddressExample();
        AddressExample.Criteria criteria = addressExample.createCriteria();
        criteria.andDeletedEqualTo(false);
        criteria.andUserIdEqualTo(userId);
        return addressMapper.selectByExample(addressExample);
    }

    /**
     * Author: laizhouhao 15:41 2019/4/29
     * @param pageNum
     * @return List<Address>
     * @apiNote: 分页查询出所有地址信息
     */
    @Override
    public PageInfo<Address> selectPage(Integer pageNum) {
        //设置查询的是哪一页和每页的记录条数
        PageHelper.offsetPage(pageNum, config.getPageSize());

        //无条件查询,查询所有
        List<Address>addresss =addressMapper.selectByExample(null);

        if (addresss!=null){
            return new PageInfo<>(addresss);
        }
        return null;
    }

    /**
     * Author: laizhouhao 15:44 2019/4/29
     * @param address
     * @return boolean
     * @apiNote: 增加Address表的一个记录
     */
    @Override
    public boolean insert(Address address) {
        return addressMapper.insert(address)>0 ? true : false;
    }

    /**
     * Author: laizhouhao 15:46 2019/4/29
     * @param address
     * @return boolean
     * @apiNote: 用一个新的对象更新Address表的一个记录
     */
    @Override
    public boolean update(Address address) {
        return addressMapper.updateByPrimaryKey(address) > 0 ? true : false;
    }


    /**
     * Author: laizhouhao 15:47 2019/4/29
     * @param id
     * @return boolean
     * @apiNote: 根据id删除Address表的一个记录
     */
    @Override
    public boolean delete(Long id) {
        return addressMapper.deleteByPrimaryKey(id) > 0 ? true : false;
    }

    /**
     * Author: laizhouhao 19:00 2019/5/7
     * @param addressExample
     * @return List<Address>
     * @apiNote: 根据自定义条件查询用户的地址信息
     */
    @Override
    public List<Address> selectByExample(AddressExample addressExample) {
        return addressMapper.selectByExample(addressExample);
    }

    /**
     * Author: laizhouhao 15:18 2019/5/14
     * @param requestMessage
     * @return boolean
     * @apiNote: 用户点击申请修改地址信息
     */
    @Override
    public boolean clickApplyAddress(RequestMessage requestMessage) {
        Address address = requestMessage.getAddress();
        Long byWho = requestMessage.getByWho();
        UserinfoApply userInfo_apply = requestMessage.getUserinfoApply();
        //获取被修改的用户id
        Long user_id = address.getUserId();
        //旧记录id
        Long oldId = address.getId();
//            System.out.println("oldId = "+oldId);
        //将要插入的记录设置为无效
        address.setDeleted(true);
        //将新纪录插入Address表
        addressMapper.insert(address);
        //新纪录的id
        Long newId = address.getId();
        //向userinfoApply增加审批业务id
        userInfo_apply.setApprovalMainId(approvalMainService.
                selectIdByName(userInfo_apply.getUniversityId(), "审批学生申请修改地址"));
        //设置用户信息申请种类
        userInfo_apply.setInfoType(1);
        //设置用户信息申请旧信息记录id
        userInfo_apply.setOldInfoId(oldId);
        //设置用户信息申请新信息记录id
        userInfo_apply.setNewInfoId(newId);
        //设置用户信息申请开始时间
        userInfo_apply.setStartTime(address.getDatetime());
        //设置用户信息创建时间
        userInfo_apply.setDatetime(address.getDatetime());
        //设置用户信息申请写入者
        userInfo_apply.setByWho(byWho);
        //设置用户信息申请为有效
        userInfo_apply.setDeleted(false);
        //插入新的userinfoApply记录
        boolean successInfoApply = userinfoApplyService.insertUserinfoApply(userInfo_apply);
        //向审批流程表插入一条数据
        UserinfoApplyApproval applyApproval = new UserinfoApplyApproval();
        //设置学校id
        applyApproval.setUniversityId(userInfo_apply.getUniversityId());
        //设置申请表id
        applyApproval.setUserinfoApplyId(userInfo_apply.getId());
        //设置步骤，初始化为1
        applyApproval.setStep(1);
        //设置时间
        applyApproval.setDatetime(userInfo_apply.getStartTime());
        //设置写入者
        applyApproval.setByWho(byWho);
        //设置为有效
        applyApproval.setDeleted(false);
        //设置申请信息的种类
        applyApproval.setInfoType(userInfo_apply.getInfoType());
        //设置审批的角色名
        int st = applyApproval.getStep();
        Long mainId = userInfo_apply.getApprovalMainId();
        Long roleId = approvalStepInchargeService
                .selectRoleIdByStepAppovalId(st,mainId);
        Role role = roleMapper.selectByPrimaryKey(roleId);
        //设置申请人的用户id
        applyApproval.setApplyUserId(byWho);
        boolean successApplyApproval = userinfoApplyApprovalService.insertUserinfoApplyApproval(applyApproval);
        System.out.println("aaa="+applyApproval);
        return successInfoApply && successApplyApproval;
    }

    /**
     * Author: laizhouhao 20:06 2019/5/15
     * @param user_id
     * @return Address
     * @apiNote: 根据用户id获取有效的用户地址信息
     */
    @Override
    public List<Address> selectValidAddressByUserId(Long user_id) {
        AddressExample addressExample = new AddressExample();
        addressExample.createCriteria().andUserIdEqualTo(user_id).andDeletedEqualTo(false);
        return addressMapper.selectByExample(addressExample);
    }

    /**
     * Author: laizhouhao 20:22 2019/5/15
     * @param id
     * @return Address
     * @apiNote: 根据id获取有效的地址信息
     */
    @Override
    public Address selectValidAddressById(Long id) {
        AddressExample addressExample = new AddressExample();
        addressExample.createCriteria().andIdEqualTo(id).andDeletedEqualTo(false);
        List<Address> addressList = new ArrayList<>();
        addressList = addressMapper.selectByExample(addressExample);
        return addressList==null?null:addressList.get(0);
    }

    /**
     * Author: mokuanyuan 20:14 2019/6/2
     * @param map
     * @param address
     * @apiNote: 传入一个HashMap和Address对象，把Address里的id字段对应的信息内容放入到map里
     */
    @Override
    public void selectAllInfoToMap(HashMap map, Address address) {
        map.put("country", addrCountryService.selectAddrCountryById(address.getCountry()).getCountryZh() );
        map.put("state", addrStateService.selectAddrStateById( address.getState()).getStateZh() );
        map.put("city", addrCityService.selectAddrCityById(address.getCity()).getCityZh() );
        map.put("area", addrAreaService.selectAddrAreaById(address.getArea()).getAreaZh() );
        map.put("street", addrStreetService.selectAddrStreetById(address.getStreet()).getStreetZh() );
        map.put("detail", address.getDetail() );
        map.put("zip_code",address.getZipCode());
        map.put("telephone",address.getTelephone());
        map.put("flag",address.getFlag());

    }

    /**
     * Author: mokuanyuan 16:12 2019/6/11
     * @param addressMap
     * @param addressList
     * @apiNote: 传入一个Address对象和一个List集合对象，其集合元素为HashMap<String, Object>，把Address对象中的id和name包装成map放到List集合中
     */
    @Override
    public void selectAllInfoToList(HashMap<String, Object> addressMap, List<Address> address) {

        if(address.size() > 0 ){
            List<HashMap<String, Object>> addressList = new ArrayList<HashMap<String, Object>>();
            for(int i=0 ; i < address.size() ; i++){

                //国家
                HashMap<String, Object> map = new HashMap<>();
                map.put("id",addrCountryService.selectAddrCountryById(address.get(i).getCountry()).getId());
                map.put("name", addrCountryService.selectAddrCountryById(address.get(i).getCountry()).getCountryZh() );
                addressList.add(map);

                //省份
                map = new HashMap<>();
                map.put("id",addrStateService.selectAddrStateById( address.get(i).getState()).getId());
                map.put("name", addrStateService.selectAddrStateById( address.get(i).getState()).getStateZh() );
                addressList.add(map);

                //城市
                map = new HashMap<>();
                map.put("id", addrCityService.selectAddrCityById(address.get(i).getCity()).getId() );
                map.put("name", addrCityService.selectAddrCityById(address.get(i).getCity()).getCityZh() );
                addressList.add(map);

                //县或区
                map = new HashMap<>();
                map.put("id", addrAreaService.selectAddrAreaById(address.get(i).getArea()).getId());
                map.put("name", addrAreaService.selectAddrAreaById(address.get(i).getArea()).getAreaZh() );
                addressList.add(map);

                //街道或村
                map = new HashMap<>();
                map.put("id", addrStreetService.selectAddrStreetById(address.get(i).getStreet()).getId() );
                map.put("name", addrStreetService.selectAddrStreetById(address.get(i).getStreet()).getStreetZh() );
                addressList.add(map);

                map = new HashMap<>();
                map.put("detail", address.get(i).getDetail() );
                addressList.add(map);

                map = new HashMap<>();
                map.put("zip_code",address.get(i).getZipCode());
                addressList.add(map);

                map = new HashMap<>();
                map.put("telephone",address.get(i).getTelephone());
                addressList.add(map);

                map = new HashMap<>();
                map.put("flag",address.get(i).getFlag());
                addressList.add(map);

            }
            addressMap.put("address",addressList);

        }

    }

    /**
     * Author: laizhouhao 21:09 2019/6/9
     * @param
     * @return 用户的所有地址的详细信息
     * @apiNote: 根据用户的地址主要信息获取所有地址的详细信息
     */
    @Override
    public void getAddress(HashMap<String, Object> map, List<Address> addressList) {
        HashMap<String,Object> eachMap = new HashMap<>();
        //获取用户的各种地址信息
        for (int i=0; i<addressList.size(); i++){
            //判断该地址信息是否有效，有效则加入
            if(addressList.get(i).getDeleted() == false){
                int addressType = addressList.get(i).getFlag();
                switch (addressType){
                    //查找当前住址
                    case 0:
                        eachMap.put("Country", addrCountryService.selectAddrCountryById(addressList.get(i).getCountry()).getCountryZh());
                        eachMap.put("State",addrStateService.selectAddrStateById(addressList.get(i).getState()).getStateZh());
                        eachMap.put("City",addrCityService.selectAddrCityById(addressList.get(i).getCity()).getCityZh());
                        eachMap.put("Area", addrAreaService.selectAddrAreaById(addressList.get(i).getArea()).getAreaZh());
                        eachMap.put("Street", addressList.get(i).getStreet());
                        eachMap.put("Detail",addressList.get(i).getDetail());
                        eachMap.put("ZipCode", addressList.get(i).getZipCode());
                        eachMap.put("Phone", addressList.get(i).getTelephone());
                        map.put("CurAddr", eachMap);
                        break;
                    //查找收货地址
                    case 1:
                        eachMap.put("Country", addrCountryService.selectAddrCountryById(addressList.get(i).getCountry()).getCountryZh());
                        eachMap.put("State",addrStateService.selectAddrStateById(addressList.get(i).getState()).getStateZh());
                        eachMap.put("City",addrCityService.selectAddrCityById(addressList.get(i).getCity()).getCityZh());
                        eachMap.put("Area", addrAreaService.selectAddrAreaById(addressList.get(i).getArea()).getAreaZh());
                        eachMap.put("Street", addressList.get(i).getStreet());
                        eachMap.put("Detail",addressList.get(i).getDetail());
                        eachMap.put("ZipCode", addressList.get(i).getZipCode());
                        eachMap.put("Phone", addressList.get(i).getTelephone());
                        map.put("ReciveAddr", eachMap);
                        break;
                    //查找曾今住址
                    case 2:
                        eachMap.put("Country", addrCountryService.selectAddrCountryById(addressList.get(i).getCountry()).getCountryZh());
                        eachMap.put("State",addrStateService.selectAddrStateById(addressList.get(i).getState()).getStateZh());
                        eachMap.put("City",addrCityService.selectAddrCityById(addressList.get(i).getCity()).getCityZh());
                        eachMap.put("Area", addrAreaService.selectAddrAreaById(addressList.get(i).getArea()).getAreaZh());
                        eachMap.put("Street", addressList.get(i).getStreet());
                        eachMap.put("Detail",addressList.get(i).getDetail());
                        eachMap.put("ZipCode", addressList.get(i).getZipCode());
                        eachMap.put("Phone", addressList.get(i).getTelephone());
                        map.put("PreAddr", eachMap);
                        break;
                    //查找通信地址
                    case 3:
                        eachMap.put("Country", addrCountryService.selectAddrCountryById(addressList.get(i).getCountry()).getCountryZh());
                        eachMap.put("State",addrStateService.selectAddrStateById(addressList.get(i).getState()).getStateZh());
                        eachMap.put("City",addrCityService.selectAddrCityById(addressList.get(i).getCity()).getCityZh());
                        eachMap.put("Area", addrAreaService.selectAddrAreaById(addressList.get(i).getArea()).getAreaZh());
                        eachMap.put("Street", addressList.get(i).getStreet());
                        eachMap.put("Detail",addressList.get(i).getDetail());
                        eachMap.put("ZipCode", addressList.get(i).getZipCode());
                        eachMap.put("Phone", addressList.get(i).getTelephone());
                        map.put("EcommAddr", eachMap);
                        break;
                    //查找办公地址
                    case 4:
                        eachMap.put("Country", addrCountryService.selectAddrCountryById(addressList.get(i).getCountry()).getCountryZh());
                        eachMap.put("State",addrStateService.selectAddrStateById(addressList.get(i).getState()).getStateZh());
                        eachMap.put("City",addrCityService.selectAddrCityById(addressList.get(i).getCity()).getCityZh());
                        eachMap.put("Area", addrAreaService.selectAddrAreaById(addressList.get(i).getArea()).getAreaZh());
                        eachMap.put("Street", addressList.get(i).getStreet());
                        eachMap.put("Detail",addressList.get(i).getDetail());
                        eachMap.put("ZipCode", addressList.get(i).getZipCode());
                        eachMap.put("Phone", addressList.get(i).getTelephone());
                        map.put("OfficeAddr", eachMap);
                        break;
                }
            }
        }
    }
}
