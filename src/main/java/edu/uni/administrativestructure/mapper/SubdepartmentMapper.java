package edu.uni.administrativestructure.mapper;

import edu.uni.administrativestructure.bean.Subdepartment;
import edu.uni.administrativestructure.bean.SubdepartmentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SubdepartmentMapper {
    int countByExample(SubdepartmentExample example);

    int deleteByExample(SubdepartmentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Subdepartment record);

    int insertSelective(Subdepartment record);

    List<Subdepartment> selectByExample(SubdepartmentExample example);

    Subdepartment selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Subdepartment record, @Param("example") SubdepartmentExample example);

    int updateByExample(@Param("record") Subdepartment record, @Param("example") SubdepartmentExample example);

    int updateByPrimaryKeySelective(Subdepartment record);

    int updateByPrimaryKey(Subdepartment record);
}