package edu.uni.professionalcourses.mapper;

import edu.uni.professionalcourses.bean.TrainingProgramMainCourse;
import edu.uni.professionalcourses.bean.TrainingProgramMainCourseExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TrainingProgramMainCourseMapper {
    int countByExample(TrainingProgramMainCourseExample example);

    int deleteByExample(TrainingProgramMainCourseExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TrainingProgramMainCourse record);

    int insertSelective(TrainingProgramMainCourse record);

    List<TrainingProgramMainCourse> selectByExample(TrainingProgramMainCourseExample example);

    TrainingProgramMainCourse selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TrainingProgramMainCourse record, @Param("example") TrainingProgramMainCourseExample example);

    int updateByExample(@Param("record") TrainingProgramMainCourse record, @Param("example") TrainingProgramMainCourseExample example);

    int updateByPrimaryKeySelective(TrainingProgramMainCourse record);

    int updateByPrimaryKey(TrainingProgramMainCourse record);
}