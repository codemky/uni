package edu.uni.professionalcourses.mapper;

import edu.uni.professionalcourses.bean.LearningProcess;
import edu.uni.professionalcourses.bean.LearningProcessExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LearningProcessMapper {
    int countByExample(LearningProcessExample example);

    int deleteByExample(LearningProcessExample example);

    int deleteByPrimaryKey(Long id);

    int insert(LearningProcess record);

    int insertSelective(LearningProcess record);

    List<LearningProcess> selectByExample(LearningProcessExample example);

    LearningProcess selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") LearningProcess record, @Param("example") LearningProcessExample example);

    int updateByExample(@Param("record") LearningProcess record, @Param("example") LearningProcessExample example);

    int updateByPrimaryKeySelective(LearningProcess record);

    int updateByPrimaryKey(LearningProcess record);
}