package edu.uni.userBaseInfo1.service;

import com.github.pagehelper.PageInfo;
import edu.uni.userBaseInfo1.bean.Employee;

import java.util.List;

public interface EmployeeService {
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
}
