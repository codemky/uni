package edu.uni.userBaseInfo1.mapper;

import edu.uni.userBaseInfo1.bean.AcademicDegree;
import edu.uni.userBaseInfo1.bean.AcademicDegreeExample;
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