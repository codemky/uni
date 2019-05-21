package edu.uni.professionalcourses.mapper;

import edu.uni.professionalcourses.bean.SpecialtyCourse;
import edu.uni.professionalcourses.bean.SpecialtyCourseExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SpecialtyCourseMapper {
    int countByExample(SpecialtyCourseExample example);

    int deleteByExample(SpecialtyCourseExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SpecialtyCourse record);

    int insertSelective(SpecialtyCourse record);

    List<SpecialtyCourse> selectByExample(SpecialtyCourseExample example);

    SpecialtyCourse selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SpecialtyCourse record, @Param("example") SpecialtyCourseExample example);

    int updateByExample(@Param("record") SpecialtyCourse record, @Param("example") SpecialtyCourseExample example);

    int updateByPrimaryKeySelective(SpecialtyCourse record);

    int updateByPrimaryKey(SpecialtyCourse record);
}