package edu.uni.userBaseInfo1.mapper;

import edu.uni.userBaseInfo1.bean.EmployeeHistory;
import edu.uni.userBaseInfo1.bean.EmployeeHistoryExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeHistoryMapper {
    //按条件计数
    int countByExample(EmployeeHistoryExample example);

    //按条件删除
    int deleteByExample(EmployeeHistoryExample example);

    //按主键删除
    int deleteByPrimaryKey(Long id);

    //插入数据（返回值为id)
    int insert(EmployeeHistory record);

    //插入数据不为null的字段值
    int insertSelective(EmployeeHistory record);

    //按条件查询
    List<EmployeeHistory> selectByExample(EmployeeHistoryExample example);

    //按主键查询
    EmployeeHistory selectByPrimaryKey(Long id);

    //按用户id查询
    List<EmployeeHistory>selectByUserId(Long user_id);

    //按条件更新值不为null的字段
    int updateByExampleSelective(@Param("record") EmployeeHistory record, @Param("example") EmployeeHistoryExample example);

    //按条件更新
    int updateByExample(@Param("record") EmployeeHistory record, @Param("example") EmployeeHistoryExample example);

    //按主键更新不为null的字段
    int updateByPrimaryKeySelective(EmployeeHistory record);

    //按主键更新
    int updateByPrimaryKey(EmployeeHistory record);
}