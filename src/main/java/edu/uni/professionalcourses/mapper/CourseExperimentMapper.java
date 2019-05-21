package edu.uni.professionalcourses.mapper;

import edu.uni.professionalcourses.bean.CourseExperiment;
import edu.uni.professionalcourses.bean.CourseExperimentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseExperimentMapper {
    int countByExample(CourseExperimentExample example);

    int deleteByExample(CourseExperimentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CourseExperiment record);

    int insertSelective(CourseExperiment record);

    List<CourseExperiment> selectByExample(CourseExperimentExample example);

    CourseExperiment selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CourseExperiment record, @Param("example") CourseExperimentExample example);

    int updateByExample(@Param("record") CourseExperiment record, @Param("example") CourseExperimentExample example);

    int updateByPrimaryKeySelective(CourseExperiment record);

    int updateByPrimaryKey(CourseExperiment record);
}