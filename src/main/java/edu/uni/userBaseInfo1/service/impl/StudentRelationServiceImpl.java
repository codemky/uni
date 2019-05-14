package edu.uni.userBaseInfo1.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.example.config.ExampleConfig;
import edu.uni.userBaseInfo1.bean.*;
import edu.uni.userBaseInfo1.mapper.RoleMapper;
import edu.uni.userBaseInfo1.mapper.StudentRelationMapper;
import edu.uni.userBaseInfo1.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author chenenru
 * @Description StudentRelation实体类的service层接口的实现类
 * @Date 15:49 2019/4/29
 **/

//Service类的注解，标志这是一个服务层接口类，这样才能被Spring”“”“”“”"扫描"到
@SuppressWarnings("ALL")
@Service
public class StudentRelationServiceImpl implements StudentRelationService {
    //持久层接口的对象
    @Autowired
    private StudentRelationMapper studentRelationMapper;//爆红的话编译器的原因，不影响运行
    @Autowired
    private ApprovalMainService approvalMainService;
    @Autowired
    private UserinfoApplyService userinfoApplyService;
    @Autowired
    private ApprovalStepInchargeService approvalStepInchargeService;
    @Autowired
    private UserinfoApplyApprovalService userinfoApplyApprovalService;
    @Autowired
    private RoleMapper roleMapper;


    //配置类，规定了上传文件的路径和分页查询每一页的记录数
    @Autowired
    private ExampleConfig config;

    /**
     * Author: chenenru 15:17 2019/4/29
     * @return List<StudentRelation>
     * @apiNote: 查询所有的地址
     */
    @Override
    public List<StudentRelation> selectAll() {
        return studentRelationMapper.selectByExample(null);
    }

    /**
     * Author: chenenru 15:39 2019/4/29
     * @param id
     * @return StudentRelation
     * @apiNote: 根据id查询出一条地址信息
     */
    @Override
    public StudentRelation selectById(long id) {
        return studentRelationMapper.selectByPrimaryKey(id);
    }

    /**
     * Author: chenenru 1:24 2019/5/5
     * @param userId
     * @return StudentRelation
     * @apiNote: 根据用户的id查询出一条地址信息
     */
    @Override
    public List<StudentRelation> selectByUserId(Long userId) {
        return studentRelationMapper.selectByUserId(userId);
    }

    /**
     * Author: chenenru 15:41 2019/4/29
     * @param pageNum
     * @return List<StudentRelation>
     * @apiNote: 分页查询出所有地址信息
     */
    @Override
    public PageInfo<StudentRelation> selectPage(int pageNum) {
        //设置查询的是哪一页和每页的记录条数
        PageHelper.offsetPage(pageNum, config.getPageSize());

        //无条件查询,查询所有
        List<StudentRelation>StudentRelations =studentRelationMapper.selectByExample(null);

        if (StudentRelations!=null){
            return new PageInfo<>(StudentRelations);
        }
        return null;
    }

    /**
     * Author: chenenru 15:44 2019/4/29
     * @param StudentRelation
     * @return boolean
     * @apiNote: 增加StudentRelation表的一个记录
     */
    @Override
    public boolean insert(StudentRelation StudentRelation) {
        return studentRelationMapper.insert(StudentRelation)>0 ? true : false;
    }

    /**
     * Author: chenenru 15:46 2019/4/29
     * @param StudentRelation
     * @return boolean
     * @apiNote: 用一个新的对象更新StudentRelation表的一个记录
     */
    @Override
    public boolean update(StudentRelation StudentRelation) {
        return studentRelationMapper.updateByPrimaryKey(StudentRelation) > 0 ? true : false;
    }


    /**
     * Author: chenenru 15:47 2019/4/29
     * @param id
     * @return boolean
     * @apiNote: 根据id删除StudentRelation表的一个记录
     */
    @Override
    public boolean delete(long id) {
        return studentRelationMapper.deleteByPrimaryKey(id) > 0 ? true : false;
    }

    /**
     * Author: laizhouhao 19:29 2019/5/7
     * @param studentRelationExample
     * @return List<StudentRelation>
     * @apiNote: 根据自定义条件查询学生亲属记录
     */
    @Override
    public List<StudentRelation> selectByExample(StudentRelationExample studentRelationExample) {
        return studentRelationMapper.selectByExample(studentRelationExample);
    }

    /**
     * Author: laizhouhao 15:13 2019/5/8
     * @param user_id
     * @return List<Long>
     * @apiNote: 根据用户id查询亲属在本系统的id
     */
    @Override
    public List<StudentRelation> selectValidRelaByUserId(Long user_id) {
        //构造查询条件,根据user_id的有效的信息记录
        StudentRelationExample studentRelationExample = new StudentRelationExample();
        studentRelationExample.createCriteria().andUserIdEqualTo(user_id).andDeletedEqualTo(false);
        //查找所有的符合条件的亲属信息
        List<StudentRelation> studentRelations
                = studentRelationMapper.selectByExample(studentRelationExample);
        return studentRelations;
    }

    /**
     *
     * @param relaId
     * @return StudentRelation
     * @apiNote: 根据亲属在用户表的id查亲属的信息
     */
    @Override
    public StudentRelation selectRelaByRelaId(Long relaId) {
        return studentRelationMapper.selectByRelaId(relaId);
    }

    /**
     * Author: laizhouhao 15:18 2019/5/14
     * @param requestMessage
     * @return boolean
     * @apiNote: 用户点击申请修改学生亲属信息
     */
    @Override
    public boolean clickApplyStudentRelation(RequestMessage requestMessage) {
        StudentRelation studentRelation = requestMessage.getStudentRelation();
        Long byWho = requestMessage.getByWho();
        UserinfoApply userInfo_apply = requestMessage.getUserinfoApply();
        //获取被修改的用户id
        Long user_id = studentRelation.getUserId();
        //旧记录id
        Long oldId = studentRelation.getId();
//            System.out.println("oldId = "+oldId);
        //将要插入的记录设置为无效
        studentRelation.setDeleted(true);
        //将新纪录插入StudentRelation表
        studentRelationMapper.insert(studentRelation);
        //新纪录的id
        Long newId = studentRelation.getId();
        //向userinfoApply增加审批业务id
        userInfo_apply.setApprovalMainId(approvalMainService.
                selectIdByName(userInfo_apply.getUniversityId(), "审批学生申请修改亲属信息"));
        //设置用户信息申请种类
        userInfo_apply.setInfoType(1);
        //设置用户信息申请旧信息记录id
        userInfo_apply.setOldInfoId(oldId);
        //设置用户信息申请新信息记录id
        userInfo_apply.setNewInfoId(newId);
        //设置用户信息申请开始时间
        userInfo_apply.setStartTime(studentRelation.getDatetime());
        //设置用户信息创建时间
        userInfo_apply.setDatetime(studentRelation.getDatetime());
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
        return successInfoApply && successApplyApproval;
    }

}
