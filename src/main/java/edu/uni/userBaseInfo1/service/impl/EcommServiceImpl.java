/**
 * @Author Mr.k
 * @Description // Ecomm实体类的service层接口的实现类
 * @Date 17:16 2019/4/24
 **/

package edu.uni.userBaseInfo1.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.auth.bean.Role;
import edu.uni.auth.mapper.RoleMapper;
import edu.uni.example.config.ExampleConfig;
import edu.uni.userBaseInfo1.bean.*;
import edu.uni.userBaseInfo1.mapper.*;
import edu.uni.userBaseInfo1.service.*;
import edu.uni.userBaseInfo1.bean.Ecomm;
import edu.uni.userBaseInfo1.bean.EcommExample;
import edu.uni.userBaseInfo1.mapper.EcommMapper;
import edu.uni.userBaseInfo1.service.EcommService;
import edu.uni.userBaseInfo1.utils.UserInfo;
import edu.uni.userBaseInfo1.utils.userinfoTransMapBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

//Service类的注解，标志这是一个服务层接口类，这样才能被Spring”“”“”“”"扫描"到
@SuppressWarnings("ALL")
@Service
public class EcommServiceImpl implements EcommService {

    //持久层接口的对象
    @Autowired
    private EcommMapper ecommMapper;  //爆红不用管
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private ApprovalStepInchargeService approvalStepInchargeService;
    @Autowired
    private UserinfoApplyApprovalService userinfoApplyApprovalService;
    @Autowired
    private ApprovalMainService approvalMainService;
    @Autowired
    private UserinfoApplyService userinfoApplyService;

    //配置类，规定了上传文件的路径和分页查询每一页的记录数
    @Autowired
    private ExampleConfig config;

    /**
     * Author: mokuanyuan 14:52 2019/6/12
     * @param oldId
     * @param newId
     * @return boolean 操作结果
     * @apiNote: 当审批的最后一步都通过后进行的操作，把相应的信息记录进行更新操作
     */
    public boolean updateForApply(Long oldId,Long newId){
        boolean result = false;
        Ecomm newEcomm = selectById(newId);
        if(oldId != null){
            Ecomm oldEcomm = selectById(oldId);
            oldEcomm.setId(newId);
            newEcomm.setId(oldId);
            oldEcomm.setDeleted(true);
            newEcomm.setDeleted(false);
            if( update(oldEcomm) && update(newEcomm) )
                result = true;
        }else{
            newEcomm.setDeleted(false);
            if( update(newEcomm) )
                result = true;
        }
        return result;
    }

    public boolean readyForApply(HashMap<String, Object> map, Ecomm ecomm, long[] idList ,
                                 edu.uni.auth.bean.User loginUser, edu.uni.userBaseInfo1.bean.User modifiedUser) {
        //通过工具类获取在map包装好的对象属性
        userinfoTransMapBean.transMap2Bean((Map) map.get("applyEcomm"),ecomm);
        //检验是否把该获取的信息都获取到了
        if(Ecomm.isValidForApply(ecomm) == false)
            return false;
        boolean result = false;
        if(ecomm.getId() != -1){  //不是-1代表原本有旧数据
            Ecomm oldEcomm = selectById(ecomm.getId());
            Ecomm.copyPropertiesForApply(ecomm,oldEcomm);
            ecomm.setByWho(loginUser.getId());
            idList[0] = oldEcomm.getId();
            result = insert(ecomm) > 0 ? true : false;
            idList[1] = ecomm.getId();
        }
        else{
            ecomm.setUserId(modifiedUser.getId());
            ecomm.setDatetime(new Date());
            ecomm.setByWho(loginUser.getId());
            ecomm.setDeleted(true);
            result = insert(ecomm) > 0 ? true : false;
            idList[1] = ecomm.getId();
        }
        return result;
    }

    /**
     * Author: mokuanyuan 10:44 2019/4/26
     * @return List<Ecomm>
     * @apiNote: 查询所有通信记录，不分页
     */
    public List<Ecomm> selectAll() {
        return ecommMapper.selectByExample(null);
    }

    /**
     * Author: mokuanyuan 10:45 2019/4/26
     * @param id
     * @return Ecomm
     * @apiNote: 通过id查询一个通信记录
     */
    public Ecomm selectById(long id) {
        return ecommMapper.selectByPrimaryKey(id);
    }

    /**
     * Author: mokuanyuan 10:39 2019/4/26
     * @param pageNum
     * @return pageInfo<Ecomm>
     * @apiNote: 分页查询所有通信记录
     */
    public PageInfo<Ecomm> selectPage(int pageNum) {
        //设置查询的是哪一页和每一页有多少个记录
        PageHelper.offsetPage(pageNum,config.getPageSize());

        //无条件查询（条件对象为null，即无条件），查询所有
        List<Ecomm> ecomms = ecommMapper.selectByExample(null);

        //检验查询的结果
        if(ecomms != null )
            return new PageInfo<>(ecomms);
        else
            return null;

    }

    /**
     * Author: mokuanyuan 10:46 2019/4/26
     * @param ecomm
     * @return boolean
     * @apiNote: 插入一条电子通信记录
     */
    public int insert(Ecomm ecomm) {
//        return  ecommMapper.insert(ecomm);
        //System.out.println("ecomm==="+ecomm);
         return ecommMapper.insertSelective(ecomm);

    }

    /**
     * Author: mokuanyuan 10:46 2019/4/26
     * @param ecomm
     * @return boolean
     * @apiNote: 以一个新的对象更新一条电子通信记录
     */
    public boolean update(Ecomm ecomm) {
        return ecommMapper.updateByPrimaryKeySelective(ecomm) > 0 ? true : false;
    }

    /**
     * Author: mokuanyuan 10:47 2019/4/26
     * @param id
     * @return boolean
     * @apiNote: 以id删除一条电子通信记录
     */
    public boolean delete(long id) {
        return ecommMapper.deleteByPrimaryKey(id) > 0 ? true : false;
    }

    /**
     * Author: laizhouhao 15:41 2019/5/9
     * @param user_id
     * @return List<Ecomm>
     * @apiNote: 根据用户id返回用户的有效的通信信息
     */
    @Override
    public List<Ecomm> selectValidEcomByUserId(Long user_id) {
        EcommExample ecommExample = new EcommExample();
        ecommExample.setOrderByClause("flag ASC");
        ecommExample.createCriteria().andUserIdEqualTo(user_id)
                .andDeletedEqualTo(false);
        return ecommMapper.selectByExample(ecommExample);
    }

    /**
     * Author: laizhouhao 18:35 2019/5/13
     * @param requestMessage
     * @return boolean
     * @apiNote: 用户点击申请修改通信方式
     */
    @Override
    public boolean clickApplyEcomm(RequestMessage requestMessage) {
        Ecomm ecomm = requestMessage.getEcomm();
        Long byWho = requestMessage.getByWho();
        UserinfoApply userInfo_apply = requestMessage.getUserinfoApply();
        //获取被修改的用户id
        Long user_id = ecomm.getUserId();
        //旧记录id
        Long oldId = ecomm.getId();
//            System.out.println("oldId = "+oldId);
        //将要插入的记录设置为无效
        ecomm.setDeleted(true);
        //将新纪录插入Ecomm表
        ecommMapper.insert(ecomm);
        //新纪录的id
        Long newId = ecomm.getId();
        //向userinfoApply增加审批业务id
        userInfo_apply.setApprovalMainId(approvalMainService.
                selectIdByName(userInfo_apply.getUniversityId(), "审批学生申请修改通信方式"));
        //设置用户信息申请种类
        userInfo_apply.setInfoType(0);
        //设置用户信息申请旧信息记录id
        userInfo_apply.setOldInfoId(oldId);
        //设置用户信息申请新信息记录id
        userInfo_apply.setNewInfoId(newId);
        //设置用户信息申请开始时间
        userInfo_apply.setStartTime(ecomm.getDatetime());
        //设置用户信息创建时间
        userInfo_apply.setDatetime(ecomm.getDatetime());
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
     * Author: mokuanyuan 20:03 2019/5/13
     * @param userInfo
     * @param user_id
     * @apiNote: 根据用户id查询电子通信方式，并把结果赋值到工具类UserInfo的相应属性中
     */
    public void getEcommByUserIdToUserInfo(UserInfo userInfo , Long user_id){

        EcommExample ecommExample = new EcommExample();
        ecommExample.createCriteria().andUserIdEqualTo(user_id).andDeletedEqualTo(false);

        List<Ecomm> ecomms = ecommMapper.selectByExample(ecommExample);

        userInfo.setEcomms(ecomms);
    }

    @Override
    public List<Ecomm> filterEcomm(List<Ecomm> ecomms) {
        List<Ecomm> ecommList = new ArrayList<>();
        int[] type = new int[8];
        for (int i : type) i = 0;
        for(Ecomm ecomm : ecomms)
            if(type[ecomm.getFlag()] == 0){
                type[ecomm.getFlag()] = 1;
                ecommList.add(ecomm);
            }

        return ecommList;
    }

    /**
     * Author: laizhouhao 20:21 2019/6/9
     * @param ecommList
     * @return 用户通信方式
     * @apiNote: 根据用户id获取用户的通信方式
     */
    @Override
    public void getUserEcomm(HashMap<String, Object> map, List<Ecomm> ecommList) {
        //获取用户的各种通信方式，并将放入map中
        for (int i=0; i<ecommList.size(); i++){
            //判断该联系方式是否有效，有效则加入
            if(ecommList.get(i).getDeleted() == false){
                int ecommType = ecommList.get(i).getFlag();
                switch (ecommType){
                    case 0:
                        map.put("QQEcomm", ecommList.get(i));
                        break;
                    case 1:
                        map.put("WeChatEcomm",ecommList.get(i));
                        break;
                    case 2:
                        map.put("EmailEcomm",ecommList.get(i).getId());
                        break;
                    case 3:
                        map.put("MobailPhoneEcomm",ecommList.get(i));
                        break;
                    case 4:
                        map.put("OfficePhoneEcomm",ecommList.get(i));
                        break;
                    case 5:
                        map.put("HomePhoneEcomm",ecommList.get(i));
                        break;
                }
            }
        }
    }
}
