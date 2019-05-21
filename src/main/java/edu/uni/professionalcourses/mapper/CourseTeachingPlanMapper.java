package edu.uni.professionalcourses.mapper;

import edu.uni.professionalcourses.bean.CourseTeachingPlan;
import edu.uni.professionalcourses.bean.CourseTeachingPlanExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseTeachingPlanMapper {
    int countByExample(CourseTeachingPlanExample example);

    int deleteByExample(CourseTeachingPlanExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CourseTeachingPlan record);

    int insertSelective(CourseTeachingPlan record);

    List<CourseTeachingPlan> selectByExample(CourseTeachingPlanExample example);

    CourseTeachingPlan selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CourseTeachingPlan record, @Param("example") CourseTeachingPlanExample example);

    int updateByExample(@Param("record") CourseTeachingPlan record, @Param("example") CourseTeachingPlanExample example);

    int updateByPrimaryKeySelective(CourseTeachingPlan record);

    int updateByPrimaryKey(CourseTeachingPlan record);
}