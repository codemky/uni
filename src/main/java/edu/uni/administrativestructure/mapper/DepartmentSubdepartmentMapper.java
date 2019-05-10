package edu.uni.administrativestructure.mapper;

import edu.uni.administrativestructure.bean.DepartmentSubdepartment;
import edu.uni.administrativestructure.bean.DepartmentSubdepartmentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepartmentSubdepartmentMapper {
    int countByExample(DepartmentSubdepartmentExample example);

    int deleteByExample(DepartmentSubdepartmentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DepartmentSubdepartment record);

    int insertSelective(DepartmentSubdepartment record);

    List<DepartmentSubdepartment> selectByExample(DepartmentSubdepartmentExample example);

    DepartmentSubdepartment selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DepartmentSubdepartment record, @Param("example") DepartmentSubdepartmentExample example);

    int updateByExample(@Param("record") DepartmentSubdepartment record, @Param("example") DepartmentSubdepartmentExample example);

    int updateByPrimaryKeySelective(DepartmentSubdepartment record);

    int updateByPrimaryKey(DepartmentSubdepartment record);
}