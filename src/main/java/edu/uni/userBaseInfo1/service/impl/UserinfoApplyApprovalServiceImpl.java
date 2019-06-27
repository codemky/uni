package edu.uni.userBaseInfo1.service.impl;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.administrativestructure.service.DepartmentService;
import edu.uni.administrativestructure.service.PositionService;
import edu.uni.administrativestructure.service.SubdepartmentService;
import edu.uni.auth.bean.Role;
import edu.uni.auth.mapper.RoleMapper;
import edu.uni.auth.service.AuthService;
import edu.uni.auth.service.RoleService;
import edu.uni.example.config.ExampleConfig;
import edu.uni.place.service.FieldService;
import edu.uni.professionalcourses.service.SpecialtyService;
import edu.uni.userBaseInfo1.alibaba.easyexcel.test.StudentUpload;
import edu.uni.userBaseInfo1.alibaba.easyexcel.test.model.EmployeeModel;
import edu.uni.userBaseInfo1.alibaba.easyexcel.test.model.StudentModel;
import edu.uni.userBaseInfo1.alibaba.easyexcel.test.util.FileUtil;
import edu.uni.userBaseInfo1.bean.*;
import edu.uni.userBaseInfo1.config.userBaseInfo1Config;
import edu.uni.userBaseInfo1.mapper.UserinfoApplyApprovalMapper;
import edu.uni.userBaseInfo1.service.*;
import edu.uni.userBaseInfo1.utils.UserInfoFileUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author chenenru
 * @ClassName UserinfoApplyApprovalServiceImpl
 * @Description
 * @Date 2019/4/30 15:13
 * @Version 1.0
 **/
//Service类的注解，标志这是一个服务层接口类，这样才能被Spring”“”“”“”"扫描"到=
@Service
public class UserinfoApplyApprovalServiceImpl implements UserinfoApplyApprovalService {
    //持久层接口的对象
    @Autowired
    private UserinfoApplyApprovalMapper userinfoApplyApprovalMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private ApprovalStepInchargeService approvalStepInchargeService;
    @Autowired
    private UserinfoApplyApprovalService userinfoApplyApprovalService;
    @Autowired
    private UserinfoApplyService userinfoApplyService;
    @Autowired
    private OtherClassService otherClassService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    OtherEmployService otherEmployService;
    @Autowired  //把Student的Service层接口所有的方法自动装配到该对象中
    private StudentService studentService;
    @Autowired
    private UserService userService;
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
    private ApprovalMainService  approvalMainService;
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
    private AuthService authService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private userBaseInfo1Config userBaseInfo1Config;



    //配置类，规定了上传文件的路径和分页查询每一页的记录数
    @Autowired
    private ExampleConfig config;

    /**
     * Author: mokuanyuan 21:53 2019/6/11
     * @param newId
     * @param oldId
     * @param infoType
     * @apiNote: 当审批的最后一步都通过后进行的操作，把相应的信息记录进行更新操作
     */
    public boolean updateDataForApplyPass(Long newId,Long oldId,Integer infoType) throws IOException {
//        Object newInfo = new Object();
//        Object oldInfo = new Object();

        UserInfoFileUtil userInfoFileUtil = new UserInfoFileUtil(userBaseInfo1Config.getAbsoluteExcelDir());
        UserUploadFile userUploadFile = null;
        File file =null;
        StringBuffer stringBuffer = null;
        StudentUpload studentUpload = null;
        List<Object> data = null;


        if( infoType == 9 || infoType == 10 || infoType == 11 || infoType == 12 ){
            userUploadFile = userUploadFileService.selectUserUploadFileById(newId);
            file = new File(userInfoFileUtil.getUploadRootDir() + userUploadFile.getFileName());
            if( !file.exists() )
                System.out.println("最终审批后。。   #########根据路径信息获取的文件为空！");
            stringBuffer = new StringBuffer();
            studentUpload = new StudentUpload();
            data = new ArrayList<>();
        }

//        指定要审核的信息种类	  0:联系方式	  1:地址
//        2：照片  	3：亲属  4	：学历  5	：简历
//        6：学生信息	 7：教职工信息	 8：	用户个人信息
//        9：学生excel表  10：职员excel表
//        10：批量添加学生账号 12：批量添加职员账号
        boolean result = false;
        switch (infoType){
            case 0: // 0为联系方式
                result = ecommService.updateForApply(oldId,newId); break;
            case 1: // 1为地址
                result = addressService.updateForApply(oldId,newId); break;
            case 2: // 2为照片
                result = pictureService.updateForApply(oldId,newId); break;
            case 3: // 3为亲属
                result = studentRelationService.updateForApply(oldId,newId); break;
            case 4: // 4为学历
                result = learningDegreeSerevice.updateForApply(oldId,newId); break;
            case 5: // 5为简历
                result = employeeHistoryService.updateForApply(oldId,newId); break;
            case 6: // 6为学生信息
                result = studentService.updateForApply(oldId,newId); break;
            case 7: // 7为职员信息
                result = employeeService.updateForApply(oldId,newId); break;
            case 8: // 8为用户信息
                break;
            case 9: // 9为批量更新学生信息
                stringBuffer = studentUpload.UpdateStudent(new FileInputStream(file));
                break;
            case 10: // 10为批量更新教职工信息
                stringBuffer = studentUpload.UpdateEmployee(new FileInputStream(file));
                break;
            case 11: // 11为批量添加学生信息
                data = EasyExcelFactory.read(new FileInputStream(file), new Sheet(1, 1, StudentModel.class));
                for (Object o : data)
                    stringBuffer.append(studentUpload.insertStudent(o));
                break;
            case 12: // 12为批量添加教职工信息
                data = EasyExcelFactory.read(new FileInputStream(file), new Sheet(1, 1, EmployeeModel.class));
                for (Object o : data)
                    stringBuffer.append(studentUpload.insertEmployee(o));
        }

        if( infoType == 11 || infoType == 12 )
            if(stringBuffer.toString().contains("插入成功")){
                userUploadFile.setDeleted(false);
                return true;
            }

        if(infoType == 9 || infoType == 10)
            if(stringBuffer.toString().contains("更新成功")){
                userUploadFile.setDeleted(false);
                return true;
            }

        return result;

    }

    /**
     * Author: mokuanyuan 12:39 2019/6/12
     * @param applyApproval
     * @param userinfoApply
     * @return boolean 操作的结果
     * @apiNote: 创建申请审批流程记录（因点击确认申请后产生的记录）
     */
    @Override
    public boolean createForApply(UserinfoApplyApproval applyApproval,UserinfoApply userinfoApply) {
        //设置学校id
        applyApproval.setUniversityId(userinfoApply.getUniversityId());
        //设置申请表id
        applyApproval.setUserinfoApplyId(userinfoApply.getId());
        //设置步骤，初始化为1
        applyApproval.setStep(1);
        //设置时间
        applyApproval.setDatetime(userinfoApply.getStartTime());
        //设置为有效
        applyApproval.setDeleted(false);
        //设置申请信息的种类
        applyApproval.setInfoType(userinfoApply.getInfoType());
        //设置审批的角色名
        int step = applyApproval.getStep();
        Long mainId = userinfoApply.getApprovalMainId();
        Long roleId = approvalStepInchargeService
                .selectRoleIdByStepAppovalId(step,mainId);
        Role role = roleMapper.selectByPrimaryKey(roleId);
        applyApproval.setRoleName(role.getName());
        //设置写入者
        applyApproval.setByWho(userinfoApply.getByWho());
        //设置申请人的用户id
        applyApproval.setApplyUserId(userinfoApply.getByWho());

        return insertUserinfoApplyApproval(applyApproval);
    }

    /**
     * Author: mokuanyuan 16:54 2019/5/11
     * @param userinfoApplyApproval 申请审批的实体类
     * @param roles 该用户所有扮演的角色
     * @return List<UserinfoApplyApproval>
     * @apiNote: 根据审批结果、审批类型、角色名搜素审批表
     */
    @Override
    public List<UserinfoApplyApproval> selectAllByApprovalAndRole(
            UserinfoApplyApproval userinfoApplyApproval, List<String> roles) {

        UserinfoApplyApprovalExample example = new UserinfoApplyApprovalExample();
        UserinfoApplyApprovalExample.Criteria criteria = example.createCriteria();

        //判断审批结果
        if(userinfoApplyApproval.getResult() != null)
            criteria.andResultEqualTo(userinfoApplyApproval.getResult());
        else
            criteria.andResultIsNull();
        //获取类型
        if(userinfoApplyApproval.getInfoType() != null)
            criteria.andInfoTypeEqualTo(userinfoApplyApproval.getInfoType());

        if(roles != null && roles.size() >0 )
            criteria.andRoleNameIn(roles);

        List<UserinfoApplyApproval> userinfoApplyApprovals =
                userinfoApplyApprovalMapper.selectByExample(example);

        return userinfoApplyApprovals;

    }





    /**
     * Author: mokuanyuan 19:55 2019/5/16
     * @param applyId
     * @return List<UserinfoApplyApproval>
     * @apiNote: 根据申请表id查询所有的审批流程记录
     */
    public List<UserinfoApplyApproval> selectByApplyId(Long applyId){
        UserinfoApplyApprovalExample example = new UserinfoApplyApprovalExample();
        UserinfoApplyApprovalExample.Criteria criteria = example.createCriteria();

        example.setOrderByClause("step ASC");
        criteria.andUserinfoApplyIdEqualTo(applyId);
        criteria.andDeletedEqualTo(false);
        criteria.andResultIsNotNull();

        return userinfoApplyApprovalMapper.selectByExample(example);

    }

    /**
     * Author: chenenru 0:10 2019/4/30
     * @param
     * @return  List<UserinfoApplyApproval>
     * @apiNote: 查询所有用户信息审批流程记录，不分页
     */
    @Override
    public List<UserinfoApplyApproval> selectAllUserinfoApplyApprovals() {
        return userinfoApplyApprovalMapper.selectByExample(null);
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param id
     * @return UserinfoApplyApproval
     * @apiNote: 通过id查询一个用户信息审批流程记录
     */
    @Override
    public UserinfoApplyApproval selectUserinfoApplyApprovalById(long id) {
        return userinfoApplyApprovalMapper.selectByPrimaryKey(id);
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param pageNum
     * @return  PageInfo<UserinfoApplyApproval>
     * @apiNote: 分页查询所有用户信息审批流程记录
     */
    @Override
    public PageInfo<UserinfoApplyApproval> selectUserinfoApplyApprovalByPage(int pageNum) {
        //设置查询的是哪一页和每一页有多少个记录
        PageHelper.offsetPage(pageNum,config.getPageSize());
        //无条件查询（条件对象为null，即无条件），查询所有
        List<UserinfoApplyApproval> UserinfoApplyApprovals = userinfoApplyApprovalMapper.selectByExample(null);
        //检验查询的结果
        if(UserinfoApplyApprovals !=null){
            return new PageInfo<>(UserinfoApplyApprovals);
        }else{
            return null;
        }
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param UserinfoApplyApproval
     * @return boolean
     * @apiNote: 插入一条用户信息审批流程记录
     */
    @Override
    public boolean insertUserinfoApplyApproval(UserinfoApplyApproval UserinfoApplyApproval) {
        return  userinfoApplyApprovalMapper.insertSelective(UserinfoApplyApproval) > 0 ? true : false;
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param UserinfoApplyApproval
     * @return boolean
     * @apiNote: 更新一条用户信息审批流程记录
     */
    @Override
    public boolean updateUserinfoApplyApproval(UserinfoApplyApproval UserinfoApplyApproval) {
        return userinfoApplyApprovalMapper.updateByPrimaryKeySelective(UserinfoApplyApproval) >0 ? true : false;
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param id
     * @return boolean
     * @apiNote:  删除一条用户信息审批流程记录
     */
    @Override
    public boolean deleteUserinfoApplyApproval(long id) {
        return userinfoApplyApprovalMapper.deleteByPrimaryKey(id) > 0 ? true :false;
    }
}
