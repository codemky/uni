package edu.uni.userBaseInfo1.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.administrativestructure.bean.Position;
import edu.uni.administrativestructure.bean.PositionExample;
import edu.uni.administrativestructure.mapper.PositionMapper;
import edu.uni.auth.bean.Role;
import edu.uni.auth.mapper.RoleMapper;
import edu.uni.example.config.ExampleConfig;
import edu.uni.userBaseInfo1.bean.*;
import edu.uni.userBaseInfo1.mapper.EmployeeMapper;
import edu.uni.userBaseInfo1.mapper.UserMapper;
import edu.uni.userBaseInfo1.service.*;
import edu.uni.userBaseInfo1.utils.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author chenenru
 * @ClassName EmployeeServiceImpl
 * @Description
 * @Date 2019/4/30 0:04
 * @Version 1.0
 **/
//Service类的注解，标志这是一个服务层接口类，这样才能被Spring”“”“”“”"扫描"到=
@SuppressWarnings("ALL")
@Service
public class EmployeeServiceImpl implements EmployeeService {
    //持久层接口的对象
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PositionMapper positionMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ApprovalStepInchargeService approvalStepInchargeService;
    @Autowired
    private UserinfoApplyApprovalService userinfoApplyApprovalService;
    @Autowired
    private ApprovalMainService approvalMainService;
    @Autowired
    private OtherDepartmentService otherDepartmentService;
    @Autowired
    private UserinfoApplyService userinfoApplyService;
    @Autowired
    private OtherSubdepartmentService otherSubdepartmentService;
    @Autowired
    private UserService userService;
    //配置类，规定了上传文件的路径和分页查询每一页的记录数
    @Autowired
    private ExampleConfig config;
    
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param 
     * @return  List<Employee>
     * @apiNote: 查询所有职员记录，不分页
     */
    @Override
    public List<Employee> selectAllEmployees() {
        return employeeMapper.selectByExample(null);
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param id
     * @return Employee
     * @apiNote: 通过id查询一个职员记录
     */
    @Override
    public Employee selectEmployeeById(long id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

    /**
     * Author: laizhouhao 18:50 2019/5/6
     * @param user_id
     * @return List<Employee>
     * @apiNote: 根据用户id查询职工主要信息
     */
    @Override
    public List<Employee> selectByUserId(Long user_id) {
        EmployeeExample employeeExample = new EmployeeExample();
        employeeExample.createCriteria().andUserIdEqualTo(user_id)
                .andDeletedEqualTo(false);

        return employeeMapper.selectByExample(employeeExample);
    }

    /**
     * Author: chenenru 0:10 2019/4/30
     * @param pageNum
     * @return  PageInfo<Employee>
     * @apiNote: 分页查询所有职员记录
     */
    @Override
    public PageInfo<Employee> selectEmployeeByPage(int pageNum) {
        //设置查询的是哪一页和每一页有多少个记录
        PageHelper.offsetPage(pageNum,config.getPageSize());
        //无条件查询（条件对象为null，即无条件），查询所有
        List<Employee> employees = employeeMapper.selectByExample(null);
        //检验查询的结果
        if(employees !=null){
            return new PageInfo<>(employees);
        }else{
            return null;
        }
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param employee
     * @return boolean
     * @apiNote: 插入一条职员记录
     */
    @Override
    public boolean insertEmployee(Employee employee) {
        return  employeeMapper.insertSelective(employee) > 0 ? true : false;
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param employee
     * @return boolean
     * @apiNote: 更新一条职员记录
     */
    @Override
    public boolean updateEmployee(Employee employee) {
        return employeeMapper.updateByPrimaryKeySelective(employee) >0 ? true : false;
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param id
     * @return boolean
     * @apiNote:  删除一条职员记录
     */
    @Override
    public boolean deleteEmployee(long id) {
        return employeeMapper.deleteByPrimaryKey(id) > 0 ? true :false;
    }

    /**
     * Author: laizhouhao 8:31 2019/5/9
     * @param emp_no
     * @return Employee
     * @apiNote: 根据员工编号查询未离职员工的主要信息
     */
    @Override
    public Employee selectEmployeeByEmpNo(String emp_no) {
        //构造查询条件
        EmployeeExample employeeExample = new EmployeeExample();
        employeeExample.createCriteria().andEmpNoEqualTo(emp_no)
                .andDeletedEqualTo(false);
        Employee employee = new Employee();
        List<Employee> employeeList = employeeMapper.selectByExample(employeeExample);
        //判断是否有结果，无结果则返回null
        return employeeList.size()>=1?employeeList.get(0):null;
    }

    /**
     * Author: chenenru 11:31 2019/5/14
     * @param user_id
     * @return List<Employee>
     * @apiNote: 根据用户id返回用户的有效的职员信息
     */
    @Override
    public List<Employee> selectValidEmployeeByUserId(Long user_id) {
        EmployeeExample employeeExample = new EmployeeExample();
        EmployeeExample.Criteria criteria = employeeExample.createCriteria();
        criteria.andUserIdEqualTo(user_id);
        criteria.andDeletedEqualTo(false);
        return employeeMapper.selectByExample(employeeExample);
    }

    /**
     * Author: chenenru 11:31 2019/5/14
     * @param requestMessage
     * @return boolean
     * @apiNote:  用户点击申请修改职员主要信息
     */
    @Override
    public boolean clickApplyEmployee(RequestMessage requestMessage) {
        Employee employee = requestMessage.getEmployee();
        Long byWho = requestMessage.getByWho();
        UserinfoApply userInfo_apply = requestMessage.getUserinfoApply();
        //获取被修改的用户id
        Long user_id = employee.getUserId();
        //旧记录id
        Long oldId = employee.getId();
//            System.out.println("oldId = "+oldId);
        //将要插入的记录设置为无效
        employee.setDeleted(true);
        //将新纪录插入Employee表
        employeeMapper.insert(employee);
        //新纪录的id
        Long newId = employee.getId();
        //向userinfoApply增加审批业务id
        userInfo_apply.setApprovalMainId(approvalMainService.
                selectIdByName(userInfo_apply.getUniversityId(), "审批学生申请修职员主要信息"));
        //设置用户信息申请种类
        userInfo_apply.setInfoType(0);
        //设置用户信息申请旧信息记录id
        userInfo_apply.setOldInfoId(oldId);
        //设置用户信息申请新信息记录id
        userInfo_apply.setNewInfoId(newId);
        //设置用户信息申请开始时间
        userInfo_apply.setStartTime(employee.getDatetime());
        //设置用户信息创建时间
        userInfo_apply.setDatetime(employee.getDatetime());
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
     * Author: laizhouhao 20:43 2019/5/15
     * @param user_id
     * @return Employee
     * @apiNote: 根据用户id获取有效的职员信息
     */
    @Override
    public List<Employee> selectValidByUserId(Long user_id) {
        EmployeeExample employeeExample = new EmployeeExample();
        employeeExample.createCriteria().andUserIdEqualTo(user_id).andDeletedEqualTo(false);
        return employeeMapper.selectByExample(employeeExample);
    }

    /**
     * Author: laizhouhao 19:18 2019/5/17
     * @param position_id
     * @return boolean
     * @apiNote: 判断该用户是否为某某部门在职的工作人员
     */
    @Override
    public boolean checkEmployee(Long position_id, String position) {
        //构造查询条件，部门为position、有效的信息
        PositionExample positionExample = new PositionExample();
        positionExample.createCriteria().andIdEqualTo(position_id)
                .andNameLike("%"+position+"%").andDeletedEqualTo(false);
        List<Position> positionList = positionMapper.selectByExample(positionExample);
        //判断是否存在该部门职员
        return positionList.size()>=1?true:false;
    }

    /**
     * Author: laizhouhao 20:51 2019/5/17
     * @param university_id
     * @return List<Employee></Employee>
     * @apiNote: 根据学校id获取该校所有的职员的信息
     */
    @Override
    public List<Employee> selectValidEmployeeByUniId(Long university_id) {
        //构造查询条件
        EmployeeExample employeeExample = new EmployeeExample();
        employeeExample.createCriteria().andUniversityIdEqualTo(university_id).andDeletedEqualTo(false);
        return employeeMapper.selectByExample(employeeExample);
    }

    /**
     * Author: laizhouhao 20:21 2019/5/17
     * @param depart_name, subdepart_name, emp_name, emp_no
     * @return List<UserInfo>
     * @apiNote: 根据部门名、科室名、员工名、员工编号获取所有员工信息
     */
    @Override
    public UserInfo selectEmployeeByFourPosition(String depart_name,String subdepart_name,String emp_name,String emp_no) {
        UserInfo userInfo = new UserInfo();
        Long depart_id = null;
        Long subdepart_id = null;
        List<Long> user_idList = new ArrayList<>();
        //如果部门名不为空,查出该部门id
        if(depart_name != null){
            depart_id = otherDepartmentService.selectDepartIdByName(depart_name);
        }
        System.out.println(depart_id);
        //如果科室名不为空，查出该科室id
        if(subdepart_name != null){
            subdepart_id = otherSubdepartmentService.selectIdBySubdepartName(subdepart_name);
        }
        //如果姓名不为空，查出该姓名的用户id
        if(emp_name != null){
            List<User> userList = new ArrayList<>();
            userList = userService.selectIdByUserName(emp_name);
            for(int i=0; i<userList.size(); i++){
                user_idList.add(userList.get(i).getId());
            }
        }
        //根据部门名、科室名、员工名、员工编号获取所有员工信息
        EmployeeExample employeeExample = new EmployeeExample();
        EmployeeExample.Criteria criteria = employeeExample.createCriteria();
        List<Employee> employeeList = new ArrayList<>();
        if(user_idList.size() >= 1){
            for(int i=0; i<user_idList.size(); i++){
                //depart_id不为空时添加查询条件
                if(depart_id != null){
                    criteria.andDepartmentIdEqualTo(depart_id);
                }
                //subdepart_id不为空时添加查询条件
                if(subdepart_id != null){
                    criteria.andSubdepartmentIdEqualTo(subdepart_id);
                }
                criteria.andUserIdEqualTo(user_idList.get(i))
                        .andEmpNoEqualTo(emp_no).andDeletedEqualTo(false);
                //判断是否查找出满足该条件的雇员信息
                List<Employee> employees = employeeMapper.selectByExample(employeeExample);
                if(employees.size() >= 1){
                    employeeList.add(employees.get(0));
                }
            }
        }else{
            //判断depart_id是否为空
            if(depart_id != null){
                //构造查询条件
                criteria.andDepartmentIdEqualTo(depart_id);
            }
            //判断subdepart_id是否为空
            if(subdepart_id != null){
                //构造查询条件
                criteria.andSubdepartmentIdEqualTo(subdepart_id);
            }
            criteria.andEmpNoEqualTo(emp_no).andDeletedEqualTo(false);
            //判断查找的符合条件的employee是否为空
            List<Employee> employees = employeeMapper.selectByExample(employeeExample);
            if(employees.size() >= 1){
                employeeList.add(employees.get(0));
            }
        }
        userInfo.setEmployees(employeeList);
        return userInfo;
    }
}
