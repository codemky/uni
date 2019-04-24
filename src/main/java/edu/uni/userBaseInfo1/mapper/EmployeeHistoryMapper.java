package edu.uni.userBaseInfo1.mapper;

import edu.uni.userBaseInfo1.bean.EmployeeHistory;
import edu.uni.userBaseInfo1.bean.EmployeeHistoryExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeHistoryMapper {
    int countByExample(EmployeeHistoryExample example);

    int deleteByExample(EmployeeHistoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EmployeeHistory record);

    int insertSelective(EmployeeHistory record);

    List<EmployeeHistory> selectByExample(EmployeeHistoryExample example);

    EmployeeHistory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EmployeeHistory record, @Param("example") EmployeeHistoryExample example);

    int updateByExample(@Param("record") EmployeeHistory record, @Param("example") EmployeeHistoryExample example);

    int updateByPrimaryKeySelective(EmployeeHistory record);

    int updateByPrimaryKey(EmployeeHistory record);
}