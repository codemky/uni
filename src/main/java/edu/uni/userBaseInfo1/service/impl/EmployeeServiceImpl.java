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
import edu.uni.userBaseInfo1.utils.userinfoTransMapBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
     * Author: mokuanyuan 16:55 2019/6/13
     * @param map
     * @param employee
     * @param oldId
     * @param newId
     * @param loginUser
     * @param modifiedUser
     * @return boolean
     * @apiNote: 用户点击申请时进行的一些系列为了创建申请记录所做的准备
     */
    @Override
    public boolean readyForApply(HashMap<String, Object> map, Employee employee, Long oldId,
                                 Long newId, edu.uni.auth.bean.User loginUser, User modifiedUser) {
        //通过工具类获取在map包装好的对象属性
        userinfoTransMapBean.transMap2Bean((Map) map.get("applyEmployee"),employee);
        //检验是否把该获取的信息都获取到了
        if(!Employee.isValidForApply(employee))
            return false;
        boolean result = false;
        if(employee.getId() != -1){  //不是-1代表原本有旧数据
            Employee oldEmployee = selectEmployeeById(employee.getId());
            Employee.copyPropertiesForApply(employee,oldEmployee);
            employee.setByWho(loginUser.getId());
            oldId = oldEmployee.getId();
            result = insertEmployee(employee) ;
            newId = employee.getId();

        }
        else{
            employee.setUserId(modifiedUser.getId());
            employee.setDatetime(new Date());
            employee.setByWho(loginUser.getId());
            employee.setDeleted(true);
            result = insertEmployee(employee) ;
            newId = employee.getId();
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
        Employee newEmployee = selectEmployeeById(newId);
        if(oldId != null){
            Employee oldEmployee = selectEmployeeById(oldId);
            oldEmployee.setId(newId);
            newEmployee.setId(oldId);
            if( updateEmployee(oldEmployee) && updateEmployee(newEmployee) )
                result = true;
        }else{
            newEmployee.setDeleted(false);
            if( updateEmployee(newEmployee) )
                result = true;
        }
        return result;
    }

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
