package edu.uni.userBaseInfo1.mapper;

import edu.uni.userBaseInfo1.bean.Employee;
import edu.uni.userBaseInfo1.bean.EmployeeExample;
import edu.uni.userBaseInfo1.bean.EmployeeHistory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    //按条件计数
    int countByExample(EmployeeExample example);

    //按条件删除
    int deleteByExample(EmployeeExample example);

    //按主键删除
    int deleteByPrimaryKey(Long id);

    //插入数据（返回值为id)
    int insert(Employee record);

    //插入数据不为null的字段值
    int insertSelective(Employee record);

    //按条件查询
    List<Employee> selectByExample(EmployeeExample example);

    //按主键查询
    Employee selectByPrimaryKey(Long id);

    //按用户id查询
    List<Employee>selectByUserId(Long user_id);

    //按条件更新值不为null的字段
    int updateByExampleSelective(@Param("record") Employee record, @Param("example") EmployeeExample example);

    //按条件更新
    int updateByExample(@Param("record") Employee record, @Param("example") EmployeeExample example);

    //按主键更新不为null的字段
    int updateByPrimaryKeySelective(Employee record);

    //按主键更新
    int updateByPrimaryKey(Employee record);
}