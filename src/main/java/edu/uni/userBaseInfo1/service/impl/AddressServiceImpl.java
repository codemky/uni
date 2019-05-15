package edu.uni.userBaseInfo1.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.example.config.ExampleConfig;
import edu.uni.userBaseInfo1.bean.*;
import edu.uni.userBaseInfo1.mapper.AddressMapper;
import edu.uni.userBaseInfo1.mapper.RoleMapper;
import edu.uni.userBaseInfo1.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @Author laizhouhao
 * @Description Address实体类的service层接口的实现类
 * @Date 15:49 2019/4/29
 **/
import java.util.ArrayList;
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

    /**
     * Author: chenenru 1:24 2019/5/5
     * @param userId
     * @return Address
     * @apiNote: 根据用户的id查询出地址信息
     */
    @Override
    public List<Address> selectByUserId(Long userId) {
        return addressMapper.selectByUserId(userId);
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
        //将新纪录插入Ecomm表
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
     * @apiNote: 根据用户id获取有效的地址信息
     */
    @Override
    public Address selectValidAddressById(Long id) {
        AddressExample addressExample = new AddressExample();
        addressExample.createCriteria().andIdEqualTo(id).andDeletedEqualTo(false);
        List<Address> addressList = new ArrayList<>();
        addressList = addressMapper.selectByExample(addressExample);
        return addressList==null?null:addressList.get(0);
    }


}
