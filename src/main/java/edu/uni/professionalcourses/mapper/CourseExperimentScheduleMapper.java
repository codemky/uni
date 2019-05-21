package edu.uni.professionalcourses.mapper;

import edu.uni.professionalcourses.bean.CourseExperimentSchedule;
import edu.uni.professionalcourses.bean.CourseExperimentScheduleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseExperimentScheduleMapper {
    int countByExample(CourseExperimentScheduleExample example);

    int deleteByExample(CourseExperimentScheduleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CourseExperimentSchedule record);

    int insertSelective(CourseExperimentSchedule record);

    List<CourseExperimentSchedule> selectByExample(CourseExperimentScheduleExample example);

    CourseExperimentSchedule selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CourseExperimentSchedule record, @Param("example") CourseExperimentScheduleExample example);

    int updateByExample(@Param("record") CourseExperimentSchedule record, @Param("example") CourseExperimentScheduleExample example);

    int updateByPrimaryKeySelective(CourseExperimentSchedule record);

    int updateByPrimaryKey(CourseExperimentSchedule record);
}