package edu.uni.userBaseInfo1.service;

import com.github.pagehelper.PageInfo;
import edu.uni.userBaseInfo1.PageBean.ClassmateBean;
import edu.uni.userBaseInfo1.PageBean.EmployeeBean;
import edu.uni.userBaseInfo1.bean.Employee;
import edu.uni.userBaseInfo1.bean.RequestMessage;
import edu.uni.userBaseInfo1.bean.User;
import edu.uni.userBaseInfo1.utils.UserInfo;

import java.util.HashMap;
import java.util.List;

public interface EmployeeService {

    /**
     * Author: mokuanyuan 16:55 2019/6/13
     * @param map
     * @param employee
     * @param idList
     * @param loginUser
     * @param modifiedUser
     * @return boolean
     * @apiNote: 用户点击申请时进行的一些系列为了创建申请记录所做的准备
     */
    public boolean readyForApply(HashMap<String, Object> map, Employee employee,long[] idList, edu.uni.auth.bean.User loginUser, User modifiedUser);

    /**
     * Author: mokuanyuan 14:52 2019/6/12
     * @param oldId
     * @param newId
     * @return boolean 操作结果
     * @apiNote: 当审批的最后一步都通过后进行的操作，把相应的信息记录进行更新操作
     */
    public boolean updateForApply(Long oldId,Long newId);


    /**
     * Author: mokuanyuan 20:02 2019/6/9
     * @param map
     * @param employee
     * @apiNote: 把employee对象里的id信息内容查询出来，并把相应的信息放进map里
     */
    public void selectByUserIdToMap(HashMap map , Employee employee);


    /**
     * Author: chenenru 23:59 2019/4/29
     * @param
     * @return List<Employee>
     * @apiNote: 查询所有的职员
     */
    List<Employee> selectAllEmployees();
    /**
     * Author: chenenru 0:00 2019/4/30
     * @param id
     * @return Employee
     * @apiNote: 根据id查询职员
     */
    Employee selectEmployeeById(long id);

    /**
     * Author: laizhouhao 18:47 2019/5/6
     * @param user_id
     * @return List<Employee>
     * @apiNote: 根据用户id查询职工主要信息
     */
    List<Employee> selectByUserId(Long user_id);

    /**
     * Author: chenenru 0:00 2019/4/30
     * @param pageNum
     * @return PageInfo<Employee>
     * @apiNote: 分页查询职员
     */
    PageInfo<Employee> selectEmployeeByPage(int pageNum);
    /**
     * Author: chenenru 0:00 2019/4/30
     * @param employee
     * @return boolean
     * @apiNote: 职员添加Employee表的一条记录
     */
    boolean insertEmployee(Employee employee);
    /**
     * Author: chenenru 0:00 2019/4/30
     * @param employee
     * @return boolean
     * @apiNote:  用户更新一个Employee表的某个记录（传一个新的对象）
     */
    boolean updateEmployee(Employee employee);
    /**
     * Author: chenenru 0:00 2019/4/30
     * @param id
     * @return boolean
     * @apiNote:  用于删除Employee表的某个记录
     */
    boolean deleteEmployee(long id);

    /**
     * Author: laizhouhao 8:31 2019/5/9
     * @param emp_no
     * @return Employee
     * @apiNote: 根据员工编号查询未离职员工的主要信息
     */
    Employee selectEmployeeByEmpNo(String emp_no);

    /**
     * Author: chenenru 15:41 2019/5/9
     * @param user_id
     * @return List<Employee>
     * @apiNote: 根据用户id返回用户的有效的职员信息
     */
    List<Employee> selectValidEmployeeByUserId(Long user_id);

    /**
     * Author: laizhouhao 20:43 2019/5/15
     * @param user_id
     * @return Employee
     * @apiNote: 根据用户id获取有效的职员信息
     */
    List<Employee> selectValidByUserId(Long user_id);

    /**
     * Author: laizhouhao 19:18 2019/5/17
     * @param position_id
     * @return boolean
     * @apiNote: 判断该用户是否为某部门的职员
     */
    boolean checkEmployee(Long position_id, String position);

    /**
     * Author: laizhouhao 20:51 2019/5/17
     * @param university_id
     * @return List<Employee></Employee>
     * @apiNote: 根据学校id获取该校所有的职员的信息
     */
    List<Employee> selectValidEmployeeByUniId(Long university_id);

    /**
     * Author: chenenru 16:51 2019/6/20
     * @param
     * @return
     * @apiNote: 根据教工号和学校的id查询
     */
    Employee selectValidEmployeeByEmpNoAndUniId(String EmpNo,Long university_id);

    /**
     * Author: laizhouhao 20:21 2019/5/17
     * @param depart_name, subdepart_name, emp_name, emp_no
     * @return List<UserInfo>
     * @apiNote: 根据部门名、科室名、员工名、员工编号获取所有员工信息
     */
    UserInfo selectEmployeeByFourPosition(String depart_name,String subdepart_name,String emp_name,String emp_no);

//    /**
//     * Author: chenenru 18:35 2019/5/13
//     * @param requestMessage
//     * @return boolean
//     * @apiNote: 用户点击申请修改职员
//     */
//    boolean clickApplyEmployee(RequestMessage requestMessage);
    /**
     * Author: chenenru 16:45 2019/6/22
     * @param
     * @return
     * @apiNote: 领导查询班级学生
     */
    List<ClassmateBean> selecClassMateBeantByUserId(Long userId);
    /**
     * Author: chenenru 20:51 2019/6/22
     * @param
     * @return
     * @apiNote: 领导查询职员
     */
    List<EmployeeBean> selectEmployeeBeanByUniId(Long uniId);

}
