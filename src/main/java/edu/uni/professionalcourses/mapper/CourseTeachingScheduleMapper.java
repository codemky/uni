package edu.uni.professionalcourses.mapper;

import edu.uni.professionalcourses.bean.CourseTeachingSchedule;
import edu.uni.professionalcourses.bean.CourseTeachingScheduleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseTeachingScheduleMapper {
    int countByExample(CourseTeachingScheduleExample example);

    int deleteByExample(CourseTeachingScheduleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CourseTeachingSchedule record);

    int insertSelective(CourseTeachingSchedule record);

    List<CourseTeachingSchedule> selectByExample(CourseTeachingScheduleExample example);

    CourseTeachingSchedule selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CourseTeachingSchedule record, @Param("example") CourseTeachingScheduleExample example);

    int updateByExample(@Param("record") CourseTeachingSchedule record, @Param("example") CourseTeachingScheduleExample example);

    int updateByPrimaryKeySelective(CourseTeachingSchedule record);

    int updateByPrimaryKey(CourseTeachingSchedule record);
}