package edu.uni.professionalcourses.mapper;

import edu.uni.professionalcourses.bean.AcademicDegree;
import edu.uni.professionalcourses.bean.AcademicDegreeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AcademicDegreeMapper {
    int countByExample(AcademicDegreeExample example);

    int deleteByExample(AcademicDegreeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AcademicDegree record);

    int insertSelective(AcademicDegree record);

    List<AcademicDegree> selectByExample(AcademicDegreeExample example);

    AcademicDegree selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AcademicDegree record, @Param("example") AcademicDegreeExample example);

    int updateByExample(@Param("record") AcademicDegree record, @Param("example") AcademicDegreeExample example);

    int updateByPrimaryKeySelective(AcademicDegree record);

    int updateByPrimaryKey(AcademicDegree record);
}