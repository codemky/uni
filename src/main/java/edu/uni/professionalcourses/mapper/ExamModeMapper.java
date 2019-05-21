package edu.uni.professionalcourses.mapper;

import edu.uni.professionalcourses.bean.ExamMode;
import edu.uni.professionalcourses.bean.ExamModeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExamModeMapper {
    int countByExample(ExamModeExample example);

    int deleteByExample(ExamModeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ExamMode record);

    int insertSelective(ExamMode record);

    List<ExamMode> selectByExample(ExamModeExample example);

    ExamMode selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ExamMode record, @Param("example") ExamModeExample example);

    int updateByExample(@Param("record") ExamMode record, @Param("example") ExamModeExample example);

    int updateByPrimaryKeySelective(ExamMode record);

    int updateByPrimaryKey(ExamMode record);
}