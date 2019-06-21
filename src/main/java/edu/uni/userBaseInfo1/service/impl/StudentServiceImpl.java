package edu.uni.userBaseInfo1.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysql.cj.log.Log;
import edu.uni.administrativestructure.bean.Class;
import edu.uni.administrativestructure.service.EmployService;
import edu.uni.administrativestructure.service.UniversityService;
import edu.uni.auth.mapper.RoleMapper;
import edu.uni.example.config.ExampleConfig;
import edu.uni.place.service.FieldService;
import edu.uni.professionalcourses.service.SpecialtyService;
import edu.uni.userBaseInfo1.bean.*;
import edu.uni.userBaseInfo1.mapper.StudentMapper;
import edu.uni.userBaseInfo1.mapper.UserMapper;
import edu.uni.userBaseInfo1.service.*;
import edu.uni.userBaseInfo1.utils.userinfoTransMapBean;
import edu.uni.utils.LogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private StudentService studentService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private StudentRelationService studentRelationService;
    @Autowired
    private UserService userService;
    @Autowired
    private OtherEmployPositionService otherEmployPositionService;



    //配置类，规定了上传文件的路径和分页查询每一页的记录数
    @Autowired
    private ExampleConfig config;

    /**
     * Author: mokuanyuan 20:02 2019/6/9
     * @param map
     * @param student
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
     * Author: mokuanyuan 14:52 2019/6/12
     * @param oldId
     * @param newId
     * @return boolean 操作结果
     * @apiNote: 当审批的最后一步都通过后进行的操作，把相应的信息记录进行更新操作
     */
    public boolean updateForApply(Long oldId,Long newId){
        boolean result = false;
        Student newStudent = selectById(newId);
        if(oldId != null){
            Student oldStudent = selectById(oldId);
            oldStudent.setId(newId);
            newStudent.setId(oldId);
            if( update(oldStudent) && update(newStudent) )
                result = true;
        }else{
            newStudent.setDeleted(false);
            if( update(newStudent) )
                result = true;
        }
        return result;
    }

    /**
     * Author: mokuanyuan 16:55 2019/6/13
     * @param map
     * @param student
     * @param oldId
     * @param newId
     * @param loginUser
     * @param modifiedUser
     * @return boolean
     * @apiNote: 用户点击申请时进行的一些系列为了创建申请记录所做的准备
     */
    @Override
    public boolean readyForApply(HashMap<String, Object> map, Student student, Long oldId, Long newId, edu.uni.auth.bean.User loginUser, User modifiedUser) {
        userinfoTransMapBean.transMap2Bean((Map) map.get("applyStudent"),student);
        //检验是否把该获取的信息都获取到了
        if(Student.isValidForApply(student) == false )
            return false;
        boolean result = false;
        if(student.getId() != -1){  //不是-1代表原本有旧数据
            Student oldStudent = selectById(student.getId());
            Student.copyPropertiesForApply(student,oldStudent);
            student.setByWho(loginUser.getId()); //写入者为申请修改者
            oldId = oldStudent.getId();
            result = insert(student);
            newId = student.getId();

        }
        else{ //一般来说不会单独添加学生信息的，就算是添加一个学生信息也只能通过批量增加学生的方法添加
//                    student.setUserId(modifiedUser.getId());
//                    student.setUniversityId(modifiedUser.getUniversityId());
//                    student.setDatetime(new Date());
//                    student.setByWho(loginUser.getId());
//                    student.setDeleted(true);
//                    studentService.insert(student);
//                    newId = student.getId();
//                    flag = 1;
            return false;
        }
        return result;

    }




    @Override //判断是否是班主任
    public boolean isHeadTeacher(Long studentUserId, Long employeeId) {
        List<Student> students = studentService.selectByUserId(studentUserId);
        if(students.size() == 0)
            return false;
        List<Class> classes = otherClassService.selectByHeadTeacherId(employeeId);
        for( Class cclass : classes){
            if(cclass.getId() == students.get(0).getClassId())
                return true;
        }

        return false;
    }

    @Override
    public boolean isChildTeacher(Long relationUserId, Long employeeUserId) {
        List<StudentRelation> studentRelations = studentRelationService.selectByRelationId(relationUserId);
        if(studentRelations.size() == 0)
            return false;

        for( StudentRelation studentRelation : studentRelations )
            if( isSameDepartment(studentRelation.getUserId(),employeeUserId) )
                return true;

        return false;
    }

    @Override
    public boolean isSameDepartment(Long studentUserId, Long employeeUserId) {
        List<Student> students = studentService.selectByUserId(studentUserId);
        List<Employee> employees = employeeService.selectByUserId(employeeUserId);
        if( students.size() == 0 || employees.size() == 0 )
            return false;

        Class studentClass = otherClassService.select(students.get(0).getClassId());
        if(studentClass == null)
            return false;

        if(studentClass.getDepartmentId() == employees.get(0).getDepartmentId())
            return true;

        return false;
    }

    @Override
    public boolean whetherSeeStudent(Long studentUserId, Long loginUserId) {
        List<Student> students = studentService.selectByUserId(studentUserId);
        if(students.size() == 0)
            return false;
        Class studnetClass = otherClassService.select(students.get(0).getClassId());
        if(studnetClass == null)
            return false;

        //判断是否是该学生的学院领导
        List<Employee> employees = employeeService.selectByUserId(loginUserId);
        if(employees.size() > 0){
            List<Integer> roles = otherEmployPositionService.selectEmployeeRoleByUserId(employees.get(0));
            if( employees.get(0).getDepartmentId() == studnetClass.getDepartmentId() )
                if( roles.contains(1) || roles.contains(2) )
                    return true;
        }

        //因为看学生信息的情况还有一种是学生自己亲属看的情况
        List<StudentRelation> studentRelations = studentRelationService.selectByRelationId(loginUserId);
        if(studentRelations.size() > 0){
            for( StudentRelation studentRelation : studentRelations ){
                if( studentRelation.getUserId() == students.get(0).getId() )
                    return true;
            }
        }

        return false;
    }

    @Override
    public boolean whetherSeeEmployee(Long employeeUserId, Long loginUserId) {
        User user = userService.selectUserById(employeeUserId);
        if( user == null )
            return false;

        //查看职员信息除了自己也就只有学校的人事处的工作人员了
        List<Employee> employees = employeeService.selectByUserId(loginUserId);
        if(employees.size() > 0){
            List<Integer> roles = otherEmployPositionService.selectEmployeeRoleByUserId(employees.get(0));
            if( user.getUniversityId() == employees.get(0).getUniversityId() )
                if( roles.contains(3) )
                    return true;
        }

        return false;
    }

    @Override
    public boolean whetherSeeRelation(Long relationUserId, Long loginUserId) {
        User user = userService.selectUserById(relationUserId);
        if( user == null )
            return false;

        //查看亲属信息的情况可以是自己的孩子看或者自己的孩子的班主任或学院领导看
        List<StudentRelation> studentRelations = studentRelationService.selectByRelationId(relationUserId);
        for( StudentRelation studentRelation : studentRelations ){
            List<Student> students = studentService.selectByUserId(studentRelation.getUserId());
            if( students.size() > 0 ){
                //判断是否是自己孩子
                if(students.get(0).getId() == loginUserId)
                    return true;
                Class studnetClass = otherClassService.select(students.get(0).getClassId());
                if(studnetClass != null){
                    List<Employee> employees = employeeService.selectByUserId(loginUserId);
                    if( employees.size() > 0 ){
                        List<Integer> roles = otherEmployPositionService.selectEmployeeRoleByUserId(employees.get(0));
                        //判断班主任
                        if(roles.contains(1) && studnetClass.getHeadteacher() == employees.get(0).getId())
                            return true;
                        //判断学院领导
                        if(roles.contains(2) && studnetClass.getDepartmentId() == employees.get(0).getDepartmentId())
                            return true;

                    }

                }

            }
        }

        return false;
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

    @Override
    public Student selectValidStuByStuNoAndUniId(String stu_no, Long uniId) {
        //构造查询条件
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andStuNoEqualTo(stu_no).andDeletedEqualTo(false);
        studentExample.createCriteria().andUniversityIdEqualTo(uniId);
        List<Student> studentList = studentMapper.selectByExample(studentExample);
        return studentList.size()>=1?studentList.get(0):null;
    }

    @Override
    public List<Student> selectByGrade(String grade) {
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andGradeEqualTo(grade).andDeletedEqualTo(false);
        return studentMapper.selectByExample(studentExample);
    }

}
