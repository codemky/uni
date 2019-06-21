package edu.uni.userBaseInfo1.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.auth.bean.Role;
import edu.uni.auth.mapper.RoleMapper;
import edu.uni.example.config.ExampleConfig;
import edu.uni.userBaseInfo1.bean.*;
import edu.uni.userBaseInfo1.mapper.EmployeeHistoryMapper;
import edu.uni.userBaseInfo1.service.*;
import edu.uni.userBaseInfo1.utils.userinfoTransMapBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @Author laizhouhao
 * @Description employeeHistory实体类的service层接口的实现类
 * @Date 15:49 2019/4/29
 **/
import java.text.SimpleDateFormat;
import java.util.*;

//Service类的注解，标志这是一个服务层接口类，这样才能被Spring”“”“”“”"扫描"到
@SuppressWarnings("ALL")
@Service
public class EmployeeHistoryServiceImpl implements EmployeeHistoryService {
    //持久层接口的对象
    @Autowired
    private EmployeeHistoryMapper employeeHistoryMapper;//爆红的话编译器的原因，不影响运行
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
     * Author: laizhouhao 15:17 2019/4/29
     * @return List<EmployeeHistory>
     * @apiNote: 查询所有的简历信息
     */
    @Override
    public List<EmployeeHistory> selectAll() {
        return employeeHistoryMapper.selectByExample(null);
    }

    /**
     * Author: laizhouhao 15:39 2019/4/29
     * @param id
     * @return EmployeeHistory
     * @apiNote: 根据id查询出一条简历信息
     */
    @Override
    public EmployeeHistory selectById(Long id) {
        return employeeHistoryMapper.selectByPrimaryKey(id);
    }

    /**
     * Author: laizhouhao 15:41 2019/4/29
     * @param pageNum
     * @return List<EmployeeHistory>
     * @apiNote: 分页查询出所有简历信息
     */
    @Override
    public PageInfo<EmployeeHistory> selectPage(Integer pageNum) {
        //设置查询的是哪一页和每页的记录条数
        PageHelper.offsetPage(pageNum, config.getPageSize());

        //无条件查询,查询所有
        List<EmployeeHistory> employeeHistorys = employeeHistoryMapper.selectByExample(null);

        if (employeeHistorys!=null){
            return new PageInfo<>(employeeHistorys);
        }
        return null;
    }

    /**
     * Author: laizhouhao 18:21 2019/5/6
     * @param user_id
     * @return List<EmployeeHistory>
     * @apiNote: 根据用户id查询简历信息
     */
    @Override
    public List<EmployeeHistory> selectByUserId(Long user_id) {
        return employeeHistoryMapper.selectByUserId(user_id);
    }

    /**
     * Author: laizhouhao 15:44 2019/4/29
     * @param employeeHistory
     * @return boolean
     * @apiNote: 增加EmployeeHistory表的一个记录
     */
    @Override
    public boolean insert(EmployeeHistory employeeHistory) {
        return employeeHistoryMapper.insert(employeeHistory)>0 ? true : false;
    }

    /**
     * Author: laizhouhao 15:46 2019/4/29
     * @param employeeHistory
     * @return boolean
     * @apiNote: 用一个新的对象更新EmployeeHistory表的一个记录
     */
    @Override
    public boolean update(EmployeeHistory employeeHistory) {
        return employeeHistoryMapper.updateByPrimaryKey(employeeHistory) > 0 ? true : false;
    }


    /**
     * Author: laizhouhao 15:47 2019/4/29
     * @param id
     * @return boolean
     * @apiNote: 根据id删除EmployeeHistory表的一个记录
     */
    @Override
    public boolean delete(Long id) {
        return employeeHistoryMapper.deleteByPrimaryKey(id) > 0 ? true : false;
    }

    /**
     * Author: laizhouhao 18:26 2019/5/10
     * @param user_id
     * @return List<EmployeeHistory>
     * @apiNote: 根据用户id查询有效的雇佣历史信息
     */
    @Override
    public List<EmployeeHistory> seleValidEmpHisByUserId(Long user_id) {
        EmployeeHistoryExample employeeHistoryExample = new EmployeeHistoryExample();
        employeeHistoryExample.createCriteria().andUserIdEqualTo(user_id).andDeletedEqualTo(false);
        List<EmployeeHistory> employeeHistoryList = new ArrayList<>();
        employeeHistoryList = employeeHistoryMapper.selectByExample(employeeHistoryExample);
        return employeeHistoryList;
    }

    /**
     * Author: laizhouhao 18:33 2019/6/10
     * @param employeeHistory
     * @apiNote: 根据职员用户id获取职员用户的职员简历信息
     */
    @Override
    public void getEmployHistory(HashMap<String, Object> map, EmployeeHistory employeeHistory) {

        map.put("BeginTime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(employeeHistory.getBeginTime()));
        map.put("EndTime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(employeeHistory.getEndTime()));
        map.put("Discript", employeeHistory.getDescript());


    }


    /**
     * Author: mokuanyuan 16:55 2019/6/13
     * @param map
     * @param employeeHistory
     * @param oldId
     * @param newId
     * @param loginUser
     * @param modifiedUser
     * @return boolean
     * @apiNote: 用户点击申请时进行的一些系列为了创建申请记录所做的准备
     */
    @Override
    public boolean readyForApply(HashMap<String, Object> map, EmployeeHistory employeeHistory, Long oldId,
                                 Long newId, edu.uni.auth.bean.User loginUser, User modifiedUser) {
        //通过工具类获取在map包装好的对象属性
        userinfoTransMapBean.transMap2Bean((Map) map.get("applyEmployeeHistory"),employeeHistory);
        //检验是否把该获取的信息都获取到了
        if(!EmployeeHistory.isValidForApply(employeeHistory))
            return false;
        boolean result = false;
        if(employeeHistory.getId() != -1){  //不是-1代表原本有旧数据
            EmployeeHistory oldEmployeeHistory = selectById(employeeHistory.getId());
            EmployeeHistory.copyPropertiesForApply(employeeHistory,oldEmployeeHistory);
            employeeHistory.setByWho(loginUser.getId());
            oldId = oldEmployeeHistory.getId();
            result = insert(employeeHistory) ;
            newId = employeeHistory.getId();

        }
        else{
            employeeHistory.setUserId(modifiedUser.getId());
            employeeHistory.setDatetime(new Date());
            employeeHistory.setByWho(loginUser.getId());
            employeeHistory.setDeleted(true);
            result = insert(employeeHistory) ;
            newId = employeeHistory.getId();
        }
        return result;

    }

    /**
     * Author: mokuanyuan 14:52 2019/6/12
     * @param oldId
     * @param newId
     * @return boolean 操作结果
     * @apiNote: 当审批的最后一步都通过后进行的操作，把相应的信息记录进行更新操作
     */
    public boolean updateForApply(Long oldId,Long newId){
        boolean result = false;
        EmployeeHistory newEmployeeHistory = selectById(newId);
        if(oldId != null){
            EmployeeHistory oldEmployeeHistory = selectById(oldId);
            oldEmployeeHistory.setId(newId);
            newEmployeeHistory.setId(oldId);
            if( update(oldEmployeeHistory) && update(newEmployeeHistory) )
                result = true;
        }else{
            newEmployeeHistory.setDeleted(false);
            if( update(newEmployeeHistory) )
                result = true;
        }
        return result;
    }


//    /**
//     * Author: chenenru 20:07 2019/5/13
//     * @param requestMessage
//     * @return boolean
//     * @apiNote: 用户点击修改简历
//     */
//    @Override
//    public boolean clickApplyEmployeeHistory(RequestMessage requestMessage) {
//        EmployeeHistory employeeHistory = requestMessage.getEmployeeHistory();
//        Long byWho = requestMessage.getByWho();
//        UserinfoApply userInfo_apply = requestMessage.getUserinfoApply();
//        //获取被修改的用户id
//        Long user_id = employeeHistory.getUserId();
//        //旧记录id
//        Long oldId = employeeHistory.getId();
////            System.out.println("oldId = "+oldId);
//        //将要插入的记录设置为无效
//        employeeHistory.setDeleted(true);
//        //将新纪录插入Ecomm表
//        employeeHistoryMapper.insert(employeeHistory);
//        //新纪录的id
//        Long newId = employeeHistory.getId();
//        //向userinfoApply增加审批业务id
//        userInfo_apply.setApprovalMainId(approvalMainService.
//                selectIdByName(userInfo_apply.getUniversityId(), "审批学生申请修改学历"));
//        //设置用户信息申请种类
//        userInfo_apply.setInfoType(0);
//        //设置用户信息申请旧信息记录id
//        userInfo_apply.setOldInfoId(oldId);
//        //设置用户信息申请新信息记录id
//        userInfo_apply.setNewInfoId(newId);
//        //设置用户信息申请开始时间
//        userInfo_apply.setStartTime(employeeHistory.getDatetime());
//        //设置用户信息创建时间
//        userInfo_apply.setDatetime(employeeHistory.getDatetime());
//        //设置用户信息申请写入者
//        userInfo_apply.setByWho(byWho);
//        //设置用户信息申请为有效
//        userInfo_apply.setDeleted(false);
//        //插入新的userinfoApply记录
//        boolean successInfoApply = userinfoApplyService.insertUserinfoApply(userInfo_apply);
//        //向审批流程表插入一条数据
//        UserinfoApplyApproval applyApproval = new UserinfoApplyApproval();
//        //设置学校id
//        applyApproval.setUniversityId(userInfo_apply.getUniversityId());
//        //设置申请表id
//        applyApproval.setUserinfoApplyId(userInfo_apply.getId());
//        //设置步骤，初始化为1
//        applyApproval.setStep(1);
//        //设置时间
//        applyApproval.setDatetime(userInfo_apply.getStartTime());
//        //设置写入者
//        applyApproval.setByWho(byWho);
//        //设置为有效
//        applyApproval.setDeleted(false);
//        //设置申请信息的种类
//        applyApproval.setInfoType(userInfo_apply.getInfoType());
//        //设置审批的角色名
//        int st = applyApproval.getStep();
//        Long mainId = userInfo_apply.getApprovalMainId();
//        Long roleId = approvalStepInchargeService
//                .selectRoleIdByStepAppovalId(st,mainId);
//        Role role = roleMapper.selectByPrimaryKey(roleId);
//        //设置申请人的用户id
//        applyApproval.setApplyUserId(byWho);
//        boolean successApplyApproval = userinfoApplyApprovalService.insertUserinfoApplyApproval(applyApproval);
//        System.out.println("aaa="+applyApproval);
//        return successInfoApply && successApplyApproval;
//    }



}
