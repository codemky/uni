package edu.uni.userBaseInfo1.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.example.config.ExampleConfig;
import edu.uni.userBaseInfo1.bean.*;
import edu.uni.userBaseInfo1.mapper.*;
import edu.uni.userBaseInfo1.service.*;
import edu.uni.userBaseInfo1.utils.GetAddrDetail;
import edu.uni.userBaseInfo1.utils.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author chenenru
 * @ClassName UserServiceImpl
 * @Description
 * @Date 2019/4/29 23:22
 * @Version 1.0
 **/
//Service类的注解，标志这是一个服务层接口类，这样才能被Spring”“”“”“”"扫描"到=
@Service
public class UserServiceImpl implements UserService {
    //持久层接口的对象
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private EcommMapper ecommMapper;
    @Autowired
    private StudentRelationMapper studentRelationMapper;
    @Autowired
    private LearningDegreeMapper learningDegreeMapper;
    @Autowired
    private EmployeeHistoryMapper employeeHistoryMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private PictureMapper pictureMapper;
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private UserinfoApplyMapper userinfoApplyMapper;
    @Autowired
    private ApprovalMainMapper approvalMainMapper;
    @Autowired
    private ApprovalStepInchargeMapper approvalStepInchargeMapper;
    @Autowired
    private UserinfoApplyApprovalMapper userinfoApplyApprovalMapper;
    @Autowired
    private SecondLevelDisciplineService secondLevelDisciplineService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private PoliticalAffiliationMapper politicalAffiliationMapper;
    @Autowired
    private AddressService addressService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private StudentRelationService studentRelationService;
    @Autowired
    private EmployeeHistoryService employeeHistoryService;
    //配置类，规定了上传文件的路径和分页查询每一页的记录数
    @Autowired
    private ExampleConfig config;
    /**
     * Author: chenenru 23:25 2019/4/29
     * @param 
     * @return List<User>
     * @apiNote: 查询所有用户记录，不分页
     */
    @Override
    public List<User> selectAllUsers() {
        return userMapper.selectByExample(null);
    }
    /**
     * Author: chenenru 23:26 2019/4/29
     * @param id
     * @return User
     * @apiNote: 通过id查询一个用户记录
     */
    @Override
    public User selectUserById(long id) {
        return userMapper.selectByPrimaryKey(id);
    }
    /**
     * Author: chenenru 23:27 2019/4/29
     * @param pageNum
     * @return PageInfo<User>
     * @apiNote: 分页查询所有用户记录
     */
    @Override
    public PageInfo<User> selectUserByPage(int pageNum) {
        //设置查询的是哪一页和每一页有多少个记录
        PageHelper.offsetPage(pageNum,config.getPageSize());
        //无条件查询（条件对象为null，即无条件），查询所有
        List<User> users = userMapper.selectByExample(null);
        //检验查询的结果
        if(users !=null){
            return new PageInfo<>(users);
        }else{
            return null;
        }
    }
    /**
     * Author: chenenru 23:27 2019/4/29
     * @param user
     * @return boolean
     * @apiNote: 插入一条用户记录
     */
    @Override
    public boolean insertUser(User user) {
        return userMapper.insertSelective(user) > 0 ? true : false;
    }
    /**
     * Author: chenenru 23:28 2019/4/29
     * @param user
     * @return  boolean
     * @apiNote: 更新一条用户记录
     */
    @Override
    public boolean updateUser(User user) {
        return userMapper.updateByPrimaryKeySelective(user) >0 ? true : false;
    }
    /**
     * Author: chenenru 23:29 2019/4/29
     * @param id
     * @return boolean
     * @apiNote: 删除一条用户记录
     */
    @Override
    public boolean deleteUser(long id) {
        return userMapper.deleteByPrimaryKey(id) > 0 ? true :false;
    }

    /**
     * Author: laizhouhao 14:47 2019/5/9
     * @param user_id
     * @return UserInfo
     * @apiNote: 根据用户id查找用户的照片、详细地址
     */
    @Override
    public UserInfo selectPictureAddrByUserId(Long user_id) {
        UserInfo userInfo = new UserInfo();
        //获取亲属的有效地址信息
        AddressExample addressExample = new AddressExample();
        addressExample.createCriteria().andDeletedEqualTo(false)
                .andUserIdEqualTo(user_id);
        List<Address> addresses = addressMapper.selectByExample(addressExample);
        //获取地址详细信息
        userInfo = new GetAddrDetail().reviceAddrDetail(addresses);
        userInfo.setAddresses(addresses);
        //获取亲属的有效照片
        PictureExample pictureExample = new PictureExample();
        pictureExample.createCriteria().andUserIdEqualTo(user_id)
                .andDeletedEqualTo(false);
        userInfo.setPictures(pictureMapper.selectByExample(pictureExample));
        return userInfo;
    }

    /**
     * Author: laizhouhao 19:15 2019/5/9
     * @param record,example
     * @return int
     * @apiNote: 根据条件修改
     */
    @Override
    public int updateByExample(User record, UserExample example) {
        return userMapper.updateByExampleSelective(record, example);
    }

    /**
     * Author: laizhouhao 16:14 2019/5/11
     * @param now_step, userinfo_apply_id
     * @return boolean
     * @apiNote: 判断该步骤是否是最后一步
     */
    @Override
    public boolean isLastStep(int now_step, Long userinfo_apply_id) {
        UserinfoApply userinfoApply = userinfoApplyMapper.selectByPrimaryKey(userinfo_apply_id);
        ApprovalMain approvalMain = approvalMainMapper.selectByPrimaryKey(userinfoApply.getApprovalMainId());
        return approvalMain.getStepCnt() == now_step;
    }

    /**
     * Author: laizhouhao 16:29 2019/5/11
     * @param userinfoApplyApproval, user_id
     * @return boolean
     * @apiNote: 通过申请并且该步骤是最后一步
     */
    @Override
    public boolean endForPass(UserinfoApplyApproval userinfoApplyApproval, Long user_id) {
        //更新用户信息审批流程表
        userinfoApplyApproval.setResult(true);
        userinfoApplyApproval.setCheckWho(user_id);
        Date checkTime = new Date();
        userinfoApplyApproval.setCheckTime(checkTime);
        int firstSuccess = userinfoApplyApprovalMapper.updateByPrimaryKeySelective(userinfoApplyApproval);
        //更新用户申请表
        UserinfoApply userinfoApply = userinfoApplyMapper.selectByPrimaryKey(userinfoApplyApproval.getUserinfoApplyId());
        userinfoApply.setEndTime(checkTime);
        userinfoApply.setApplyResult(true);
        System.out.println("时间"+userinfoApply.getEndTime()+"结果"+userinfoApply.getApplyResult());
        int secondSuccess = userinfoApplyMapper.updateByPrimaryKeySelective(userinfoApply);
        if(firstSuccess == 1 && secondSuccess == 1){
            return true;
        }
        return false;
    }

    /**
     * Author: laizhouhao 16:50 2019/5/11
     * @param userinfoApplyApproval, user_id
     * @return boolean
     * @apiNote: 通过申请并且该步骤不是最后一步
     */
    @Override
    public boolean createForPass(UserinfoApplyApproval userinfoApplyApproval, Long user_id) {
        //审批结果为通过
        userinfoApplyApproval.setResult(true);
        //审批人为该用户
        userinfoApplyApproval.setCheckWho(user_id);
        //审批时间
        userinfoApplyApproval.setCheckTime(new Date());
        //更新审批流程表中该条信息
        int firstSuccess = userinfoApplyApprovalMapper.updateByPrimaryKeySelective(userinfoApplyApproval);
        //在UserinfoApplyApproval表新增一条记录，用来记录下一步的审批信息
        UserinfoApplyApproval applyApproval = new UserinfoApplyApproval();
        //设置学校id
        applyApproval.setUniversityId(userinfoApplyApproval.getUniversityId());
        //设置审批表id
        applyApproval.setUserinfoApplyId(userinfoApplyApproval.getUserinfoApplyId());
        //设置该审批记录的创建时间
        applyApproval.setDatetime(new Date());
        //设置审批记录的写入者
        applyApproval.setByWho(user_id);
        //设置该记录为有效
        applyApproval.setDeleted(false);
        //设置步骤编号
        applyApproval.setStep(userinfoApplyApproval.getStep()+1);
        System.out.println("creat5");
        //设置下一步审核的角色名
        UserinfoApply userinfoApply = new UserinfoApply();
        //根据id查找出UserinfoApply的一条记录
        userinfoApply = userinfoApplyMapper.selectByPrimaryKey(userinfoApplyApproval.getUserinfoApplyId());
        //设置查找审批人id的条件
        ApprovalStepInchargeExample approvalStepInchargeExample = new ApprovalStepInchargeExample();
        approvalStepInchargeExample.createCriteria().andApprovalMainIdEqualTo(userinfoApply.getApprovalMainId())
                .andStepEqualTo(applyApproval.getStep()).andDeletedEqualTo(false);
        System.out.println("步数"+applyApproval.getStep());
        //审批人条件
        List<ApprovalStepIncharge> approvalStepInchargeList = approvalStepInchargeMapper
                .selectByExample(approvalStepInchargeExample);
        //判断是否有这个审批人
        if(approvalStepInchargeList.size()>=1) {
            Long roleId = approvalStepInchargeList.get(0).getRoleId();
            //审批人姓名
            String roleName = roleMapper.selectByPrimaryKey(roleId).getName();
            applyApproval.setRoleName(roleName);
        }
        //设置申请信息类型
        applyApproval.setInfoType(userinfoApplyApproval.getInfoType());
        //设置申请人用户id
        applyApproval.setApplyUserId(userinfoApplyApproval.getApplyUserId());
        int secondSuccess = userinfoApplyApprovalMapper.insertSelective(applyApproval);
        return firstSuccess == 1 && secondSuccess == 1;
    }

    /**
     * Author: laizhouhao 20:57 2019/5/11
     * @param userinfoApplyApproval, user_id
     * @return boolean
     * @apiNote: 不通过申请
     */
    @Override
    public boolean endForRefuse(UserinfoApplyApproval userinfoApplyApproval, Long user_id) {
        //修改用户流程审批表的审批结果
        userinfoApplyApproval.setResult(false);
        //设置审批人
        userinfoApplyApproval.setCheckWho(user_id);
        //设置审批时间
        userinfoApplyApproval.setCheckTime(new Date());
        //更新用户流程审批表的该条信息
        int firstSuccess = userinfoApplyApprovalMapper.updateByPrimaryKeySelective(userinfoApplyApproval);

        //查找申请表
        UserinfoApply userinfoApply = userinfoApplyMapper.selectByPrimaryKey(userinfoApplyApproval.getUserinfoApplyId());
        //将申请表结果改为不通过
        userinfoApply.setApplyResult(false);
        //填写申请表的结束时间
        userinfoApply.setEndTime(new Date());
        //更新申请表该条信息
        int secondSuccess = userinfoApplyMapper.updateByPrimaryKeySelective(userinfoApply);
        //如果两个都更新成功则返回操作成功true
        return firstSuccess==1 && secondSuccess==1;
    }

    /**
     * Author: laizhouhao 16:03 2019/5/14
     * @param userinfoApplyApproval
     * @return boolean
     * @apiNote: 申请修改的信息通过后将新信息置为有效，旧信息置为无效
     */
    @Override
    public boolean updateNewAndOldMessage(UserinfoApplyApproval userinfoApplyApproval) {
        UserinfoApply userinfoApply = userinfoApplyMapper
                .selectByPrimaryKey(userinfoApplyApproval.getUserinfoApplyId());
        //旧信息id
        Long oldId = userinfoApply.getOldInfoId();
        //新信息id
        Long newId = userinfoApply.getNewInfoId();
        //判断申请的信息的类型，从而更新不同的表的信息
        int infoType = userinfoApply.getInfoType();
        //判断是否修改成功,默认为0
        int oldScuuess = 0;
        int newSuccess = 0;
        //修改的是联系方式
        if(infoType == 0){
            //获取新旧记录
            Ecomm oldEcomm = ecommMapper.selectByPrimaryKey(oldId);
            Ecomm newEcomm = ecommMapper.selectByPrimaryKey(newId);
            //旧记录置为无效，新纪录置为有效
            oldEcomm.setDeleted(true);
            newEcomm.setDeleted(false);
            //更新新旧记录
            oldScuuess = ecommMapper.updateByPrimaryKeySelective(oldEcomm);
            newSuccess = ecommMapper.updateByPrimaryKeySelective(newEcomm);
        }else if(infoType == 1){ //修改的是地址信息
            //获取新旧记录
            Address oldAddress = addressMapper.selectByPrimaryKey(oldId);
            Address newAddress = addressMapper.selectByPrimaryKey(newId);
            //旧记录置为无效，新纪录置为有效
            oldAddress.setDeleted(true);
            newAddress.setDeleted(false);
            //更新新旧记录
            oldScuuess = addressMapper.updateByPrimaryKeySelective(oldAddress);
            newSuccess = addressMapper.updateByPrimaryKeySelective(newAddress);
        }else if(infoType == 2){ //修改的是照片
            //获取新旧记录
            Picture oldPicture = pictureMapper.selectByPrimaryKey(oldId);
            Picture newPicture = pictureMapper.selectByPrimaryKey(newId);
            //旧记录置为无效，新纪录置为有效
            oldPicture.setDeleted(true);
            newPicture.setDeleted(false);
            //更新新旧记录
            oldScuuess = pictureMapper.updateByPrimaryKeySelective(oldPicture);
            newSuccess = pictureMapper.updateByPrimaryKeySelective(newPicture);
        }else if(infoType == 3){ //修改的是亲属信息
            //获取新旧记录
            StudentRelation oldStudentRelation = studentRelationMapper.selectByPrimaryKey(oldId);
            StudentRelation newStudentRelation = studentRelationMapper.selectByPrimaryKey(newId);
            //旧记录置为无效，新纪录置为有效
            oldStudentRelation.setDeleted(true);
            newStudentRelation.setDeleted(false);
            //更新新旧记录
            oldScuuess = studentRelationMapper.updateByPrimaryKeySelective(oldStudentRelation);
            newSuccess = studentRelationMapper.updateByPrimaryKeySelective(newStudentRelation);
        }else if(infoType == 4){ //修改的是学历信息
            //获取新旧记录
            LearningDegree oldLearningDegree =learningDegreeMapper.selectByPrimaryKey(oldId);
            LearningDegree newLearningDegree = learningDegreeMapper.selectByPrimaryKey(newId);
            //旧记录置为无效，新纪录置为有效
            oldLearningDegree.setDeleted(true);
            newLearningDegree.setDeleted(false);
            //更新新旧记录
            oldScuuess = learningDegreeMapper.updateByPrimaryKeySelective(oldLearningDegree);
            newSuccess = learningDegreeMapper.updateByPrimaryKeySelective(newLearningDegree);
        }else if(infoType == 5){ //修改的是简历信息
            //获取新旧记录
            EmployeeHistory oldEmployeeHistory =employeeHistoryMapper.selectByPrimaryKey(oldId);
            EmployeeHistory newEmployeeHistory = employeeHistoryMapper.selectByPrimaryKey(newId);
            //旧记录置为无效，新纪录置为有效
            oldEmployeeHistory.setDeleted(true);
            newEmployeeHistory.setDeleted(false);
            //更新新旧记录
            oldScuuess = employeeHistoryMapper.updateByPrimaryKeySelective(oldEmployeeHistory);
            newSuccess = employeeHistoryMapper.updateByPrimaryKeySelective(newEmployeeHistory);
        }else if(infoType == 6){ //修改的是学生信息
            //获取新旧记录
            Student oldStudent =studentMapper.selectByPrimaryKey(oldId);
            Student newStudent = studentMapper.selectByPrimaryKey(newId);
            //旧记录置为无效，新纪录置为有效
            oldStudent.setDeleted(true);
            newStudent.setDeleted(false);
            //更新新旧记录
            oldScuuess = studentMapper.updateByPrimaryKeySelective(oldStudent);
            newSuccess = studentMapper.updateByPrimaryKeySelective(newStudent);
        }else if(infoType == 7){ //修改的是教职工信息
            //获取新旧记录
            Employee oldEmployee = employeeMapper.selectByPrimaryKey(oldId);
            Employee newEmployee = employeeMapper.selectByPrimaryKey(newId);
            //旧记录置为无效，新纪录置为有效
            oldEmployee.setDeleted(true);
            newEmployee.setDeleted(false);
            //更新新旧记录
            oldScuuess = employeeMapper.updateByPrimaryKeySelective(oldEmployee);
            newSuccess = employeeMapper.updateByPrimaryKeySelective(newEmployee);
        }
        return oldScuuess==1 && newSuccess==1;
    }

    /**
     * Author: laizhouhao 18:06 2019/5/14
     * @param identification
     * @return  所搜索的游客信息
     * @apiNote: 根据用户身份证查询游客信息，身份证为空时默认查询所有游客信息
     */
    @Override
    public List<User> selectTouristByIdentification(String identification) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdentificationEqualTo(identification).andUserTypeEqualTo(0);
        List<User> userList = new ArrayList<>();
        userList = userMapper.selectByExample(userExample);
        return userList;
    }


    /**
     * Author: laizhouhao 19:24 2019/5/15
     * @param user_id
     * @return UserInfo
     * @apiNote: 根据用户id获取该用户的所有信息
     */
    @Override
    public UserInfo selectUserInfoAllByUserId(Long user_id) {
        UserInfo userInfo = new UserInfo();
        UserInfo info = new UserInfo();
        //根据id获取用户在user表的主要信息
        User user = userMapper.selectByPrimaryKey(user_id);
        //将用户主要信息加入UserInfo这个信息集合里面
        List<User> userList = new ArrayList<>();
        userList.add(user);
        userInfo.setUsers(userList);
        //将该用户的学校信息加入UserInfo这个信息集合里面,
//        userInfo.setUniversity(universityMapper.selectUniversityByid(user.getUniversityId()));
        //判断用户的类型
        int type = user.getUserType();
        if(type == 0|| type == 3 || type == 4 || type == 5 || type == 6){
            return userInfo;
        }else if(type == 1 ){
            //将该用户的学生主要信息加入UserInfo这个信息集合里面
            List<Student> studentList = studentService.selectValidStudentByUserId(user_id);
            userInfo.setStudents(studentList);
            //将该学生的主修专业加入集合
            SecondLevelDiscipline secondLevelDiscipline = secondLevelDisciplineService
                    .selectValidSecondLevelDisciplineById(userInfo.getStudents().get(0).getSpecialtyId());
            List<SecondLevelDiscipline> secondLevelDisciplineList = new ArrayList<>();
            secondLevelDisciplineList.add(secondLevelDiscipline);
            userInfo.setSecondLevelDisciplines(secondLevelDisciplineList);
            //将该学生的政治面貌加入集合
            List<PoliticalAffiliation> politicalAffiliationList = new ArrayList<>();
            politicalAffiliationList.add(politicalAffiliationMapper
                    .selectByPrimaryKey(userInfo.getStudents().get(0).getPoliticalId()));
            userInfo.setPoliticalAffiliations(politicalAffiliationList);
            //将该学生的宿舍加入集合

            //将该学生的当前住址、通信地址地址加入集合
            List<Address> addresses = new ArrayList<>();
            addresses.add(addressService.selectValidAddressById(studentList.get(0).getHomeAddressId()));
            addresses.add(addressService.selectValidAddressById(studentList.get(0).getPhoneEcommId()));
            userInfo.setAddresses(addresses);
            //将该学生的地址的国家、省份、市、区、街道加入集合
            info = new GetAddrDetail().reviceAddrDetail(addresses);
            userInfo.setAddrCountries(info.getAddrCountries());
            userInfo.setAddrStates(info.getAddrStates());
            userInfo.setAddrCities(info.getAddrCities());
            userInfo.setAddrAreas(info.getAddrAreas());
            userInfo.setAddrStreets(info.getAddrStreets());
            //将该学生的亲属信息加入集合
            userInfo.setStudentRelations(studentRelationService.selectValidRelaByUserId(user_id));
            //将学生的班级信息加入集合
        }else if(type == 2){
            //根据用户id查找有效的职员信息，加入信息集合中
            List<Employee> employees = employeeService.selectValidByUserId(user_id);
            userInfo.setEmployees(employees);
            //将所属学院信息加入信息集合

            //将当前所在科室加入信息集合

            //将雇员简历加入信息集合
            userInfo.setEmployeeHistories(employeeHistoryService.seleValidEmpHisByUserId(user_id));
            //将主修专业加入信息集合
            List<SecondLevelDiscipline> secondLevelDisciplineList = new ArrayList<>();
            secondLevelDisciplineList.add(secondLevelDisciplineService
                    .selectValidSecondLevelDisciplineById(employees.get(0).getDisciplineId()));
            userInfo.setSecondLevelDisciplines(secondLevelDisciplineList);
            //将政治面貌加入信息集合
            List<PoliticalAffiliation> politicalAffiliationList = new ArrayList<>();
            politicalAffiliationList.add(politicalAffiliationMapper
                    .selectByPrimaryKey(userInfo.getStudents().get(0).getPoliticalId()));
            userInfo.setPoliticalAffiliations(politicalAffiliationList);
            //将当前岗位加入信息集合

            //将教职工的地址加入信息集合
            List<Address> addresses = new ArrayList<>();
            addresses.add(addressService.selectValidAddressById(employees.get(0).getHomeAddressId()));
            addresses.add(addressService.selectValidAddressById(employees.get(0).getPhoneEcommId()));
            userInfo.setAddresses(addresses);
            //将该教职工的地址的国家、省份、市、区、街道加入集合
            info = new GetAddrDetail().reviceAddrDetail(addresses);
            userInfo.setAddrCountries(info.getAddrCountries());
            userInfo.setAddrStates(info.getAddrStates());
            userInfo.setAddrCities(info.getAddrCities());
            userInfo.setAddrAreas(info.getAddrAreas());
            userInfo.setAddrStreets(info.getAddrStreets());
        }
        return userInfo;
    }

    /**
     * Author: laizhouhao 15:47 2019/5/18
     * @param user_name
     * @return Long
     * @apiNote: 根据用户名获取有效的用户id
     */
    @Override
    public List<User> selectIdByUserName(String user_name) {
        //构造查询条件
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserNameEqualTo(user_name);
        //查找id
        List<User> userList = new ArrayList<>();
        userList = userMapper.selectByExample(userExample);
        return userList;
    }
}
