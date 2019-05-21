package edu.uni.professionalcourses.mapper;

import edu.uni.professionalcourses.bean.ExamType;
import edu.uni.professionalcourses.bean.ExamTypeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExamTypeMapper {
    int countByExample(ExamTypeExample example);

    int deleteByExample(ExamTypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ExamType record);

    int insertSelective(ExamType record);

    List<ExamType> selectByExample(ExamTypeExample example);

    ExamType selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ExamType record, @Param("example") ExamTypeExample example);

    int updateByExample(@Param("record") ExamType record, @Param("example") ExamTypeExample example);

    int updateByPrimaryKeySelective(ExamType record);

    int updateByPrimaryKey(ExamType record);
}