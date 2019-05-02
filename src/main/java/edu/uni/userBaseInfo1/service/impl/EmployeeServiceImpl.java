package edu.uni.userBaseInfo1.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.example.config.ExampleConfig;
import edu.uni.userBaseInfo1.bean.Employee;
import edu.uni.userBaseInfo1.mapper.EmployeeMapper;
import edu.uni.userBaseInfo1.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author chenenru
 * @ClassName EmployeeServiceImpl
 * @Description
 * @Date 2019/4/30 0:04
 * @Version 1.0
 **/
//Service类的注解，标志这是一个服务层接口类，这样才能被Spring”“”“”“”"扫描"到=
@Service
public class EmployeeServiceImpl implements EmployeeService {
    //持久层接口的对象
    @Autowired
    private EmployeeMapper employeeMapper;
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
}