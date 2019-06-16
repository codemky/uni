package edu.uni.userBaseInfo1.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.administrativestructure.service.DepartmentService;
import edu.uni.administrativestructure.service.PositionService;
import edu.uni.administrativestructure.service.SubdepartmentService;
import edu.uni.auth.bean.User;
import edu.uni.auth.mapper.RoleMapper;
import edu.uni.auth.service.AuthService;
import edu.uni.auth.service.RoleService;
import edu.uni.example.config.ExampleConfig;
import edu.uni.place.service.FieldService;
import edu.uni.professionalcourses.service.SpecialtyService;
import edu.uni.userBaseInfo1.bean.*;
import edu.uni.userBaseInfo1.controller.UserinfoApplyApprovalController;
import edu.uni.userBaseInfo1.mapper.UserinfoApplyApprovalMapper;
import edu.uni.userBaseInfo1.mapper.UserinfoApplyMapper;
import edu.uni.userBaseInfo1.service.*;
import edu.uni.userBaseInfo1.utils.StaticInformation;
import edu.uni.userBaseInfo1.utils.userinfoTransMapBean;
import edu.uni.utils.LogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author chenenru
 * @ClassName UserinfoApplyServiceImpl
 * @Description
 * @Date 2019/4/30 15:13
 * @Version 1.0
 **/
//Service类的注解，标志这是一个服务层接口类，这样才能被Spring”“”“”“”"扫描"到=
@SuppressWarnings("ALL")
@Service
public class UserinfoApplyServiceImpl implements UserinfoApplyService {
    private LogUtils logUilts = new LogUtils(this.getClass());

    //持久层接口的对象
    @Autowired
    private UserinfoApplyMapper userinfoApplyMapper;
    @Autowired
    private ApprovalMainService approvalMainService;
    @Autowired
    private AuthService authService;
    @Autowired
    private UserinfoApplyApprovalMapper userinfoApplyApprovalMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    UserinfoApplyService userinfoApplyService;
    @Autowired
    UserService userService;
    @Autowired
    UserinfoApplyApprovalController userinfoApplyApprovalController;
    @Autowired
    UserinfoApplyApprovalService userinfoApplyApprovalService;
    @Autowired
    private ApprovalStepInchargeService approvalStepInchargeService;
    @Autowired
    private OtherClassService otherClassService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    OtherEmployService otherEmployService;
    @Autowired  //把Student的Service层接口所有的方法自动装配到该对象中
    private StudentService studentService;
    @Autowired
    private StudentRelationService studentRelationService;
    @Autowired
    private EcommService ecommService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private PictureService pictureService;
    @Autowired
    private LearningDegreeSerevice learningDegreeSerevice;
    @Autowired
    private EmployeeHistoryService employeeHistoryService;
    @Autowired
    private UserUploadFileService userUploadFileService;
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
    private OtherUniversityService otherUniversityService;
    @Autowired
    private MyAcademicService myAcademicService;
    @Autowired
    private MyAcademicDegreeService myAcademicDegreeService;
    @Autowired
    private SpecialtyService specialtyService;
    @Autowired
    private FieldService fieldService;
    @Autowired
    private PoliticalAffiliationService politicalAffiliationService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private SubdepartmentService subdepartmentService;
    @Autowired
    private MySecondLevelDisciplineService mySecondLevelDisciplineService;
    @Autowired
    private OtherEmployPositionService otherEmployPositionService;
    @Autowired
    private PositionService positionService;
    @Autowired
    private RoleService roleService;

    //配置类，规定了上传文件的路径和分页查询每一页的记录数
    @Autowired
    private ExampleConfig config;


    /**
     * Author: mokuanyuan 18:33 2019/6/11
     * @param map
     * @param type
     * @param loginUser
     * @param modifiedUser
     * @return boolean
     * @apiNote: 在任何申请页面点击确认申请时
     */
    @Override
    public boolean clickApply(HashMap<String,Object> map, Integer type, User loginUser, edu.uni.userBaseInfo1.bean.User modifiedUser) {

        //获取申请理由
        String reason = (String) map.get("reason");

        Long newId = null;
        Long oldId = null;
        Long userId = null ;
        Integer flag = 0;  //0：更新      1：批量更新  默认值为0，因为只有类型9或10时才为1

        //各类信息对象
        Ecomm ecomm = null;
        Address address = null;
        Picture  picture = null;
        StudentRelation studentRelation = null;
        LearningDegree learningDegree = null;
        EmployeeHistory employeeHistory = null;
        Student student = null;
        Employee employee = null;
        edu.uni.userBaseInfo1.bean.User user = null;
        UserUploadFile userUploadFile = null;


//      信息种类	  0:联系方式	  1:地址
//        2：照片  	3：亲属  4	：学历  5	：简历
//        6：学生信息	 7：教职工信息	 8：	用户个人信息
//        9：学生excel	表  10：	职员excel	表
        switch (type){
            case 0:  //0为联系方式类信息
                //为创建申请记录作相应的处理
                ecommService.readyForApply(map,ecomm, oldId, newId, loginUser, modifiedUser); break;
            case 1: //1为地址类型信息

                break;
            case 2: //2为照片类型信息

                break;
            case 3: //3为亲属类型信息

                break;
            case 4: //4为学历类型信息

                break;
            case 5: //5为简历类型信息

                break;
            case 6: //6为学生类型信息
                studentService.readyForApply(map,student, oldId, newId, loginUser, modifiedUser); break;
            case 7: //7为职员类型信息

                break;
            case 8: //8为用户类型信息
                // 额。。关于用户的信息更新不打算做，因为user表都没有 Deleted字段。。
                break;
            case 9: //9代表批量更新学生信息，通过上传文件的方式
                userinfoTransMapBean.transMap2Bean((Map) map.get("applyUserUploadFile"),userUploadFile);
                //检验是否把该获取的信息都获取到了
//                UserUploadFile
//                if(!Student.isValidForApply(student))
//                    return false;
                if(student.getId() != -1){  //不是-1代表原本有旧数据
//                    Student oldStudent = studentService.selectById(student.getId());
//                    Student.copyPropertiesForApply(student,oldStudent);
//                    oldId = student.getId();
//                    studentService.insert(student);
//                    newId = student.getId();
//                    flag = 0;
                    //作上传文件的操作。。。

                    flag = 1; //type为9或者10的时候为批量更新
                }


                break;
            case 10: //10代表批量更新职员信息，通过上传文件的方式

                break;


        }

        UserinfoApply userinfoApply = new UserinfoApply();
        userinfoApply.setApplyReason(reason);
        userinfoApply.setUniversityId(loginUser.getUniversityId());
        //设置用户信息申请写入者
        userinfoApply.setByWho(loginUser.getId());
        //设置申请的信息类型种类
        userinfoApply.setInfoType(type);
        //设置新旧记录的记录id
        userinfoApply.setOldInfoId(oldId);
        userinfoApply.setNewInfoId(newId);

        boolean createApply = userinfoApplyService.createForApply(userinfoApply, flag);

        //向审批流程表插入一条数据
        UserinfoApplyApproval applyApproval = new UserinfoApplyApproval();
        boolean createApplyApproval = userinfoApplyApprovalService.createForApply(applyApproval, userinfoApply);

        if(createApply == false)
            logUilts.info("创建申请表记录失败");
        if(createApplyApproval == false)
            logUilts.info("创建申请流程表记录失败");

        System.out.println(userinfoApply + "\n" + applyApproval);

        return createApply && createApplyApproval ;
    }


    /**
     * Author: mokuanyuan 21:21 2019/6/10
     * @param userInfo_apply
     * @param flag 说明是修改还是增加
     * @apiNote: 创建申请记录（由于发出申请时产生）
     */
    @Override
    public boolean createForApply(UserinfoApply userInfo_apply , Integer flag ) {
        edu.uni.auth.bean.User user = authService.getUser();
        if(user == null)
            return false;

        //根据flag（说明是修改还是增加还是批量增加）、登录用户的用户类型、申请的信息类型获取审批业务的名称
        String approvalName = StaticInformation.getApprovalString(user.getUserType(),
                userInfo_apply.getInfoType(),flag);

        System.out.println("生成的审批业务名称为:" +approvalName);
        logUilts.info("生成的审批业务名称为:" +approvalName);

        //向userinfoApply增加审批业务id
        userInfo_apply.setApprovalMainId(approvalMainService.
                selectIdByName(userInfo_apply.getUniversityId(), approvalName));
        //设置用户信息申请开始时间
        userInfo_apply.setStartTime(new Date());
        //设置用户信息创建时间
        userInfo_apply.setDatetime(new Date());
        //设置用户信息申请为有效
        userInfo_apply.setDeleted(false);
        //插入新的userinfoApply记录
        return insertUserinfoApply(userInfo_apply);

    }

    /**
     * Author: mokuanyuan 10:17 2019/5/17
     * @param userinfoApply
     * @param userId
     * @return List<UserinfoApply>
     * @apiNote: 根据信息类型，申请结果和用户id查询该用户的所有申请信息
     */
    @Override
    public List<UserinfoApply> selectByTypeAndResultAndUserId( UserinfoApply userinfoApply , Long userId ) {
        UserinfoApplyExample example = new UserinfoApplyExample();
        UserinfoApplyExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause("start_time ASC");
        if(userinfoApply.getInfoType() != null)
            criteria.andInfoTypeEqualTo(userinfoApply.getInfoType());

        //筛选审批结果的记录
        if(userinfoApply.getId() == null){
            if(userinfoApply.getApplyResult() != null)
                criteria.andApplyResultEqualTo(userinfoApply.getApplyResult());
            if(userinfoApply.getApplyResult() == null)
                criteria.andApplyResultIsNull();
        }

        criteria.andByWhoEqualTo(userId).andDeletedEqualTo(false);

        return userinfoApplyMapper.selectByExample(example);
    }

    /**
     * Author: chenenru 0:10 2019/4/30
     * @param
     * @return  List<UserinfoApply>
     * @apiNote: 查询所有用户信息申请记录，不分页
     */
    @Override
    public List<UserinfoApply> selectAllUserinfoApplys() {
        return userinfoApplyMapper.selectByExample(null);
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param id
     * @return UserinfoApply
     * @apiNote: 通过id查询一个用户信息申请记录
     */
    @Override
    public UserinfoApply selectUserinfoApplyById(long id) {
        return userinfoApplyMapper.selectByPrimaryKey(id);
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param pageNum
     * @return  PageInfo<UserinfoApply>
     * @apiNote: 分页查询所有用户信息申请记录
     */
    @Override
    public PageInfo<UserinfoApply> selectUserinfoApplyByPage(int pageNum) {
        //设置查询的是哪一页和每一页有多少个记录
        PageHelper.offsetPage(pageNum,config.getPageSize());
        //无条件查询（条件对象为null，即无条件），查询所有
        List<UserinfoApply> UserinfoApplys = userinfoApplyMapper.selectByExample(null);
        //检验查询的结果
        if(UserinfoApplys !=null){
            return new PageInfo<>(UserinfoApplys);
        }else{
            return null;
        }
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param UserinfoApply
     * @return boolean
     * @apiNote: 插入一条用户信息申请记录
     */
    @Override
    public boolean insertUserinfoApply(UserinfoApply UserinfoApply) {
        return  userinfoApplyMapper.insertSelective(UserinfoApply) > 0 ? true : false;
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param UserinfoApply
     * @return boolean
     * @apiNote: 更新一条用户信息申请记录
     */
    @Override
    public boolean updateUserinfoApply(UserinfoApply UserinfoApply) {
        return userinfoApplyMapper.updateByPrimaryKeySelective(UserinfoApply) >0 ? true : false;
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param id
     * @return boolean
     * @apiNote:  删除一条用户信息申请记录
     */
    @Override
    public boolean deleteUserinfoApply(long id) {
        return userinfoApplyMapper.deleteByPrimaryKey(id) > 0 ? true :false;
    }
}
