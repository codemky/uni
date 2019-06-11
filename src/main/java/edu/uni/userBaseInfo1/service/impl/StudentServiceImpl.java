package edu.uni.userBaseInfo1.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysql.cj.log.Log;
import edu.uni.administrativestructure.service.UniversityService;
import edu.uni.auth.mapper.RoleMapper;
import edu.uni.example.config.ExampleConfig;
import edu.uni.place.service.FieldService;
import edu.uni.professionalcourses.service.SpecialtyService;
import edu.uni.userBaseInfo1.bean.*;
import edu.uni.userBaseInfo1.mapper.StudentMapper;
import edu.uni.userBaseInfo1.mapper.UserMapper;
import edu.uni.userBaseInfo1.service.*;
import edu.uni.utils.LogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author laizhouhao
 * @Description Student实体类的service层接口的实现类
 * @Date 10:11 2019/4/30
 **/
//Service类的注解，标志这是一个服务层接口类，这样才能被Spring”“”“”“”"扫描"到
@SuppressWarnings("ALL")
@Service
public class StudentServiceImpl implements StudentService {
    private LogUtils logUilts = new LogUtils(this.getClass());

    //持久层接口的对象
    @Autowired
    private StudentMapper studentMapper;  //爆红时由于编译器问题，不影响运行
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private ApprovalStepInchargeService approvalStepInchargeService;
    @Autowired
    private UserinfoApplyApprovalService userinfoApplyApprovalService;
    @Autowired
    private ApprovalMainService approvalMainService;
    @Autowired
    private UniversityService universityService;
    @Autowired
    private UserinfoApplyService userinfoApplyService;
    @Autowired
    private SpecialtyService specialtyService;
    @Autowired
    private PoliticalAffiliationService politicalAffiliationService;
    @Autowired
    private FieldService fieldService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private OtherFieldService otherFieldService;
    @Autowired
    private OtherUniversityService otherUniversityService;
    @Autowired
    private EcommService ecommService;
    @Autowired
    private OtherClassService otherClassService;


    //配置类，规定了上传文件的路径和分页查询每一页的记录数
    @Autowired
    private ExampleConfig config;

    /**
     * Author: mokuanyuan 20:02 2019/6/9
     * @param map
     * @param userId
     * @apiNote: 把student对象里的id信息内容查询出来，并把相应的信息放进map里
     */
    public void selectByUserIdToMap(HashMap map , Student student){
        map.put("student_no",student.getStuNo());
        map.put("begin_learn", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(student.getBeginLearnDate()));
        map.put("grade",student.getGrade());
        map.put("specialty", specialtyService.select(student.getSpecialtyId()).getName());
        map.put("political", politicalAffiliationService.selectPoliticalAffiliationById(student.getPoliticalId()).getPolitical());
        map.put("dormitory", fieldService.select(student.getLiveRoom()).getName());
        map.put("class",otherClassService.select(student.getClassId()).getName());

    }

    /**
     * Author: laizhouhao 10:14 2019/4/30
     * @return List<Student>
     * @apiNote: 查询所有学生记录，不分页
     */
    public List<Student> selectAll() {
        return studentMapper.selectByExample(null);
    }

    /**
     * Author: mokuanyuan 12:55 2019/5/10
     * @param classId
     * @return List<Student>
     * @apiNote: 根据班级id查询所有学生信息
     */
    @Override
    public List<Student> selectByClassId(Long classId) {
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andClassIdEqualTo(classId).andDeletedEqualTo(false);

        return studentMapper.selectByExample(studentExample);
    }

    /**
     * Author: laizhouhao 10:15 2019/4/30
     * @param id
     * @return Student
     * @apiNote: 通过id查询一个学生记录
     */
    public Student selectById(long id) {
        return studentMapper.selectByPrimaryKey(id);
    }

    /**
     * Author: laizhouhao 18:34 2019/5/6
     * @param user_id
     * @return List<Student>
     * @apiNote: 根据用户id查找有效的学生信息
     */
    @Override
    public List<Student> selectByUserId(Long user_id) {
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andUserIdEqualTo(user_id)
                .andDeletedEqualTo(false);
        return studentMapper.selectByExample(studentExample);
    }

    /**
     * Author: laizhouhao 10:16 2019/4/30
     * @param pageNum
     * @return pageInfo<Student>
     * @apiNote: 分页查询所有学生记录
     */
    public PageInfo<Student> selectPage(int pageNum) {
        //设置查询的是哪一页和每一页有多少个记录
        PageHelper.offsetPage(pageNum,config.getPageSize());

        //无条件查询（条件对象为null，即无条件），查询所有
        List<Student> students = studentMapper.selectByExample(null);

        //检验查询的结果
        if(students != null )
            return new PageInfo<>(students);
        else
            return null;

    }

    /**
     * Author: laizhouhao 10:17 2019/4/30
     * @param student
     * @return boolean
     * @apiNote: 插入一条电子学生记录
     */
    public boolean insert(Student student) {
        return studentMapper.insertSelective(student) > 0 ? true : false;
    }

    /**
     * Author: laizhouhao 10:18 2019/4/30
     * @param student
     * @return  boolean
     * @apiNote: 以一个新的对象更新一条学生记录
     */
    public boolean update(Student student) {
        return studentMapper.updateByPrimaryKeySelective(student) > 0 ? true : false;
    }

    /**
     * Author: laizhouhao 10:18 2019/4/30
     * @param id
     * @return boolean
     * @apiNote: 以id删除一条学生记录
     */
    public boolean delete(long id) {
        return studentMapper.deleteByPrimaryKey(id) > 0 ? true : false;
    }

    /**
     * Author: laizhouhao 19:29 2019/5/7
     * @param studentExample
     * @return List<Student>
     * @apiNote: 根据自定义条件查询学生记录
     */
    @Override
    public List<Student> selectByExample(StudentExample studentExample) {
        return studentMapper.selectByExample(studentExample);
    }

    /**
     * Author: laizhouhao 14:59 2019/5/8
     * @param stu_no
     * @return Long
     * @apiNote: 根据学号查询用户id
     */
    @Override
    public Long selectByStuNo(String stu_no) {
        //根据学号查询学生
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andStuNoEqualTo(stu_no).andDeletedEqualTo(false);
        List<Student> students = studentMapper.selectByExample(studentExample);
        //查找出有结果则返回userId，没有则返回null
        return students.size()>=1?students.get(0).getUserId():null;
    }

    /**
     * Author: laizhouhao 15:22 2019/5/9
     * @param user_id
     * @return List<Student>
     * @apiNote: 根据用户id查找有效的学生信息
     */
    @Override
    public List<Student> selectValidStudentByUserId(Long user_id) {
        //构造查询条件
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andUserIdEqualTo(user_id)
                .andDeletedEqualTo(false);
        return studentMapper.selectByExample(studentExample);
    }


    /**
     * Author: mokuanyuan 18:33 2019/6/11
     * @param student
     * @param userInfo_apply
     * @return boolean
     * @apiNote: 用户点击申请修改学生主要信息
     */
    @Override
    public boolean clickApplyStudent(Student student , UserinfoApply userInfo_apply) {
        //获取被修改的用户id
        Long user_id = student.getUserId();
        //旧记录id
        Long oldId = student.getId();
        //将要插入的记录设置为无效
        student.setDeleted(true);
        //将新纪录插入student表
        studentMapper.insert(student);
        //设置用户信息申请种类  学生信息的信息种类为6
        userInfo_apply.setInfoType(6);
        //设置新旧记录的记录id
        userInfo_apply.setOldInfoId(oldId);
        userInfo_apply.setNewInfoId(student.getId());

        boolean createApply = userinfoApplyService.createForApply(userInfo_apply, 0);

        //向审批流程表插入一条数据
        UserinfoApplyApproval applyApproval = new UserinfoApplyApproval();
        boolean createApplyApproval = userinfoApplyApprovalService.createForApply(applyApproval, userInfo_apply);

        if(createApply == false)
            logUilts.warn("创建申请表记录失败");
        if(createApplyApproval == false)
            logUilts.warn("创建申请流程表记录失败");

        System.out.println(userInfo_apply + "\n" + applyApproval);

        return createApply && createApplyApproval ;
    }

    /**
     * Author: chenenru 15:44 2019/5/16
     * @param  student_id
     * @return Student
     * @apiNote: 根据学生id获取学生实体信息
     */
    @Override
    public Student selectValidStudentByStuId(Long student_id) {
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andIdEqualTo(student_id).andDeletedEqualTo(false);
        List<Student> studentList = new ArrayList<>();
        studentList = studentMapper.selectByExample(studentExample);
        return studentList==null?null:studentList.get(0);
    }

    /**
     * Author: laizhouhao 21:33 2019/6/2
     * @param stu_no
     * @return 学生实体
     * @apiNote: 根据学号获取学生实体
     */
    @Override
    public Student selectValidStuByStuNo(String stu_no) {
        //构造查询条件
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andStuNoEqualTo(stu_no).andDeletedEqualTo(false);
        List<Student> studentList = studentMapper.selectByExample(studentExample);
        return studentList.size()>=1?studentList.get(0):null;
    }

    /**
     * Author: laizhouhao 18:55 2019/6/10
     * @param studentList
     * @return 用户的有效的学生信息详情
     * @apiNote: 根据用户的学生实体获取用户的所有有效的学生信息详情
     */
    @Override
    public void getStudent(HashMap<String, Object> map, List<Student> studentList) {
        //获取用户的学生信息详情，并将放入map中
        for (int i=0; i<studentList.size(); i++){
            //判断该学生信息是否有效，有效则加入
            if(studentList.get(i).getDeleted() == false){
                map.put("id", studentList.get(i).getId());
                map.put("University", otherUniversityService.selectValidById(studentList.get(i).getUniversityId()).getName());
                map.put("StudentNo", studentList.get(i).getStuNo());
                map.put("BeginLearnDate",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                        .format(studentList.get(i).getBeginLearnDate()));
                map.put("Grade", studentList.get(i).getGrade());
                map.put("Sprcialty", specialtyService.selectValidSpeciatyById(studentList.get(i).getSpecialtyId()).get(0).getName());
                map.put("Class", otherClassService.selectClassByClassId(studentList.get(i).getClassId()).getName());
                map.put("Politicial", politicalAffiliationService
                        .selectPoliticalAffiliationById(studentList.get(i).getPoliticalId()).getPolitical());
                map.put("Field", otherFieldService.selectById(studentList.get(i).getLiveRoom()).getName());
                //查找该用户的详细地址
                List<Address> addressList = new ArrayList<>();
                addressList.add(addressService.selectById(studentList.get(i).getHomeAddressId()));
                //存放地址
                HashMap<String, Object> addrMap = new HashMap<>();
                addressService.getAddress(addrMap, addressList);
                map.put("Address",addrMap.get("CurAddr"));

                map.put("Ecomm", ecommService.selectById(studentList.get(i).getPhoneEcommId()).getContent());
            }
        }
    }

}
