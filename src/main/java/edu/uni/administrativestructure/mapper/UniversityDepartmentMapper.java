package edu.uni.administrativestructure.mapper;

import edu.uni.administrativestructure.bean.UniversityDepartment;
import edu.uni.administrativestructure.bean.UniversityDepartmentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UniversityDepartmentMapper {
    int countByExample(UniversityDepartmentExample example);

    int deleteByExample(UniversityDepartmentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UniversityDepartment record);

    int insertSelective(UniversityDepartment record);

    List<UniversityDepartment> selectByExample(UniversityDepartmentExample example);

    UniversityDepartment selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UniversityDepartment record, @Param("example") UniversityDepartmentExample example);

    int updateByExample(@Param("record") UniversityDepartment record, @Param("example") UniversityDepartmentExample example);

    int updateByPrimaryKeySelective(UniversityDepartment record);

    int updateByPrimaryKey(UniversityDepartment record);
}