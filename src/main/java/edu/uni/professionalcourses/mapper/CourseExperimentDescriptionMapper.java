package edu.uni.professionalcourses.mapper;

import edu.uni.professionalcourses.bean.CourseExperimentDescription;
import edu.uni.professionalcourses.bean.CourseExperimentDescriptionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseExperimentDescriptionMapper {
    int countByExample(CourseExperimentDescriptionExample example);

    int deleteByExample(CourseExperimentDescriptionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CourseExperimentDescription record);

    int insertSelective(CourseExperimentDescription record);

    List<CourseExperimentDescription> selectByExample(CourseExperimentDescriptionExample example);

    CourseExperimentDescription selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CourseExperimentDescription record, @Param("example") CourseExperimentDescriptionExample example);

    int updateByExample(@Param("record") CourseExperimentDescription record, @Param("example") CourseExperimentDescriptionExample example);

    int updateByPrimaryKeySelective(CourseExperimentDescription record);

    int updateByPrimaryKey(CourseExperimentDescription record);
}