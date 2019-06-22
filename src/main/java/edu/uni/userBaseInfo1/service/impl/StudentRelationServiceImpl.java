package edu.uni.userBaseInfo1.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.auth.bean.Role;
import edu.uni.auth.mapper.RoleMapper;
import edu.uni.example.config.ExampleConfig;
import edu.uni.userBaseInfo1.bean.*;
import edu.uni.userBaseInfo1.mapper.StudentRelationMapper;
import edu.uni.userBaseInfo1.service.*;
import edu.uni.userBaseInfo1.utils.userinfoTransMapBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    @Autowired
    private UserService userService;


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
     * @return List<StudentRelation>
     * @apiNote: 根据user_id查询所有的有效的学生亲属信息集合
     */
    @Override
    public List<StudentRelation> selectByUserId(Long userId) {
        StudentRelationExample studentRelationExample = new StudentRelationExample();
        studentRelationExample.createCriteria().andUserIdEqualTo(userId).andDeletedEqualTo(false);
        return studentRelationMapper.selectByExample(studentRelationExample);
    }

    @Override
    public List<StudentRelation> selectByRelationId(Long relationId) {
        StudentRelationExample studentRelationExample = new StudentRelationExample();
        studentRelationExample.createCriteria().andRelaIdEqualTo(relationId).andDeletedEqualTo(false);
        return studentRelationMapper.selectByExample(studentRelationExample);

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
    public StudentRelation selectUserIdByRelaId(Long relaId) {
        StudentRelationExample studentRelationExample = new StudentRelationExample();
        studentRelationExample.createCriteria().andUserIdEqualTo(relaId).andDeletedEqualTo(false);

        List<StudentRelation> studentRelations = studentRelationMapper.selectByExample(studentRelationExample);

        return studentRelations.size() > 0 ? studentRelations.get(0) : null;
    }


    /**
     * Author: mokuanyuan 16:55 2019/6/13
     * @param map
     * @param studentRelation
     * @param idList
     * @param loginUser
     * @param modifiedUser
     * @return boolean
     * @apiNote: 用户点击申请时进行的一些系列为了创建申请记录所做的准备
     */
    @Override
    public boolean readyForApply(HashMap<String, Object> map, StudentRelation studentRelation,long[] idList,
                                 edu.uni.auth.bean.User loginUser, User modifiedUser) {
        //通过工具类获取在map包装好的对象属性
        userinfoTransMapBean.transMap2Bean((Map) map.get("applyStudentRelation"),studentRelation);
        //检验是否把该获取的信息都获取到了
        if(!StudentRelation.isValidForApply(studentRelation))
            return false;
        boolean result = false;
        if(studentRelation.getId() != -1){  //不是-1代表原本有旧数据
            StudentRelation oldStudentRelation = selectById(studentRelation.getId());
            StudentRelation.copyPropertiesForApply(studentRelation,oldStudentRelation);
            studentRelation.setByWho(loginUser.getId());
            idList[0] = oldStudentRelation.getId();
            result = insert(studentRelation) ;
            idList[1] = studentRelation.getId();

        }
        else{
            studentRelation.setUserId(loginUser.getId());
            studentRelation.setDatetime(new Date());
            studentRelation.setByWho(loginUser.getId());
            studentRelation.setDeleted(true);
            result = insert(studentRelation) ;
            idList[1] = studentRelation.getId();
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
        StudentRelation newStudentRelation = selectById(newId);
        if(oldId != null){
            StudentRelation oldStudentRelation = selectById(oldId);
            oldStudentRelation.setId(newId);
            newStudentRelation.setId(oldId);
            oldStudentRelation.setDeleted(true);
            newStudentRelation.setDeleted(false);
            if( update(oldStudentRelation) && update(newStudentRelation) )
                result = true;
        }else{
            newStudentRelation.setDeleted(false);
            if( update(newStudentRelation) )
                result = true;
        }
        return result;
    }

    /**
     * Author: laizhouhao 15:58 2019/6/10
     * @param studentRelationList
     * @return 用户的亲属信息
     * @apiNote: 根据用户的亲属实体获取用户所有亲属的详细信息
     */
    @Override
    public void getStuRelationInfo(HashMap<String, Object> map, List<StudentRelation> studentRelationList) {
        //获取用户的所有亲属信息，并将放入map中
        if(studentRelationList.size() > 0){
            ArrayList<HashMap<String, Object>> itemList = new ArrayList<>();
            for (int i=0; i<studentRelationList.size(); i++){
                //判断该亲属信息是否有效，有效则加入
                if(studentRelationList.get(i).getDeleted() == false){
                    Long relationUserId = studentRelationList.get(i).getRelaId();
                    User relationUser = userService.selectUserById(relationUserId);
                    HashMap<String, Object> relationMap = new HashMap<>();
                    //写入亲属的user表id
                    relationMap.put("relationUserId",relationUserId);

                    relationMap.put("relationName",relationUser.getUserName());
                    //写入亲属人性别
                    String relationSex = "不详";
                    if(relationUser.getUserType() == 0)
                        relationSex = "女";
                    if(relationUser.getUserType() == 1)
                        relationSex = "男";
                    relationMap.put("relationSex",relationSex);

                    //写入亲属关系类型 关系0：母  1：父 2：兄  3：弟 4：姐  5：妹6: 其他
                    String relationType = "其他";
                    switch (studentRelationList.get(i).getRelationship()){
                        case 0 : relationType = "母";break;
                        case 1 : relationType = "父";break;
                        case 2 : relationType = "兄";break;
                        case 3 : relationType = "弟";break;
                        case 4 : relationType = "姐";break;
                        case 5 : relationType = "妹";
                    }
                    relationMap.put("relationType",relationType);

                    itemList.add(relationMap);

//                    int stuRelationType = studentRelationList.get(i).getRelationship();
//                    switch (stuRelationType){
//                        case 0:
//                            map.put("MotherName",studentRelationList.get(i).getRelaName());
//                            map.put("MotherRelation", "母亲");
//                            break;
//                        case 1:
//                            map.put("FatherName",studentRelationList.get(i).getRelaName());
//                            map.put("FatherRelation", "父亲");
//                            break;
//                        case 2:
//                            map.put("OldBrotherName",studentRelationList.get(i).getRelaName());
//                            map.put("OldBrotherRelation", "哥哥");
//                            break;
//                        case 3:
//                            map.put("LitBrotherName",studentRelationList.get(i).getRelaName());
//                            map.put("LitBrotherRelation", "弟弟");
//                            break;
//                        case 4:
//                            map.put("OldSisterName",studentRelationList.get(i).getRelaName());
//                            map.put("OldSisterRelation", "姐姐");
//                            break;
//                        case 5:
//                            map.put("LitSisterName",studentRelationList.get(i).getRelaName());
//                            map.put("LitSisterRelation", "妹妹");
//                            break;
//                        case 6:
//                            map.put("OtherName",studentRelationList.get(i).getRelaName());
//                            map.put("OtherRelation", "其他");
//                            break;
//                    }
                }
            }

            map.put("relationList",itemList);
        }
    }


    /**
     //     * Author: laizhouhao 15:18 2019/5/14
     //     * @param requestMessage
     //     * @return boolean
     //     * @apiNote: 用户点击申请修改学生亲属信息
     //     */
//    @Override
//    public boolean clickApplyStudentRelation(RequestMessage requestMessage) {
//        StudentRelation studentRelation = requestMessage.getStudentRelation();
//        Long byWho = requestMessage.getByWho();
//        UserinfoApply userInfo_apply = requestMessage.getUserinfoApply();
//        //获取被修改的用户id
//        Long user_id = studentRelation.getUserId();
//        //旧记录id
//        Long oldId = studentRelation.getId();
////            System.out.println("oldId = "+oldId);
//        //将要插入的记录设置为无效
//        studentRelation.setDeleted(true);
//        //将新纪录插入StudentRelation表
//        studentRelationMapper.insert(studentRelation);
//        //新纪录的id
//        Long newId = studentRelation.getId();
//        //向userinfoApply增加审批业务id
//        userInfo_apply.setApprovalMainId(approvalMainService.
//                selectIdByName(userInfo_apply.getUniversityId(), "审批学生申请修改亲属信息"));
//        //设置用户信息申请种类
//        userInfo_apply.setInfoType(1);
//        //设置用户信息申请旧信息记录id
//        userInfo_apply.setOldInfoId(oldId);
//        //设置用户信息申请新信息记录id
//        userInfo_apply.setNewInfoId(newId);
//        //设置用户信息申请开始时间
//        userInfo_apply.setStartTime(studentRelation.getDatetime());
//        //设置用户信息创建时间
//        userInfo_apply.setDatetime(studentRelation.getDatetime());
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
//        return successInfoApply && successApplyApproval;
//    }

}
