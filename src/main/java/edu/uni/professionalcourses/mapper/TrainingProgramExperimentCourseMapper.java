package edu.uni.professionalcourses.mapper;

import edu.uni.professionalcourses.bean.TrainingProgramExperimentCourse;
import edu.uni.professionalcourses.bean.TrainingProgramExperimentCourseExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TrainingProgramExperimentCourseMapper {
    int countByExample(TrainingProgramExperimentCourseExample example);

    int deleteByExample(TrainingProgramExperimentCourseExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TrainingProgramExperimentCourse record);

    int insertSelective(TrainingProgramExperimentCourse record);

    List<TrainingProgramExperimentCourse> selectByExample(TrainingProgramExperimentCourseExample example);

    TrainingProgramExperimentCourse selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TrainingProgramExperimentCourse record, @Param("example") TrainingProgramExperimentCourseExample example);

    int updateByExample(@Param("record") TrainingProgramExperimentCourse record, @Param("example") TrainingProgramExperimentCourseExample example);

    int updateByPrimaryKeySelective(TrainingProgramExperimentCourse record);

    int updateByPrimaryKey(TrainingProgramExperimentCourse record);
}