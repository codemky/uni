package edu.uni.administrativestructure.mapper;

import edu.uni.administrativestructure.bean.EmployPosition;
import edu.uni.administrativestructure.bean.EmployPositionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployPositionMapper {
    int countByExample(EmployPositionExample example);

    int deleteByExample(EmployPositionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EmployPosition record);

    int insertSelective(EmployPosition record);

    List<EmployPosition> selectByExample(EmployPositionExample example);

    EmployPosition selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EmployPosition record, @Param("example") EmployPositionExample example);

    int updateByExample(@Param("record") EmployPosition record, @Param("example") EmployPositionExample example);

    int updateByPrimaryKeySelective(EmployPosition record);

    int updateByPrimaryKey(EmployPosition record);
}