package edu.uni.professionalcourses.mapper;

import edu.uni.professionalcourses.bean.TrainingProgram;
import edu.uni.professionalcourses.bean.TrainingProgramExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TrainingProgramMapper {
    int countByExample(TrainingProgramExample example);

    int deleteByExample(TrainingProgramExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TrainingProgram record);

    int insertSelective(TrainingProgram record);

    List<TrainingProgram> selectByExample(TrainingProgramExample example);

    TrainingProgram selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TrainingProgram record, @Param("example") TrainingProgramExample example);

    int updateByExample(@Param("record") TrainingProgram record, @Param("example") TrainingProgramExample example);

    int updateByPrimaryKeySelective(TrainingProgram record);

    int updateByPrimaryKey(TrainingProgram record);
}