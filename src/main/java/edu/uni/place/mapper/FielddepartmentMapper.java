package edu.uni.place.mapper;

import edu.uni.place.bean.Fielddepartment;
import edu.uni.place.bean.FielddepartmentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FielddepartmentMapper {
    long countByExample(FielddepartmentExample example);

    int deleteByExample(FielddepartmentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Fielddepartment record);

    int insertSelective(Fielddepartment record);

    List<Fielddepartment> selectByExample(FielddepartmentExample example);

    Fielddepartment selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Fielddepartment record, @Param("example") FielddepartmentExample example);

    int updateByExample(@Param("record") Fielddepartment record, @Param("example") FielddepartmentExample example);

    int updateByPrimaryKeySelective(Fielddepartment record);

    int updateByPrimaryKey(Fielddepartment record);
}