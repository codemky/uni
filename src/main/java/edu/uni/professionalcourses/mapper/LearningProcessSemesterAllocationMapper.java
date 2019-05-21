package edu.uni.professionalcourses.mapper;

import edu.uni.professionalcourses.bean.LearningProcessSemesterAllocation;
import edu.uni.professionalcourses.bean.LearningProcessSemesterAllocationExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LearningProcessSemesterAllocationMapper {
    int countByExample(LearningProcessSemesterAllocationExample example);

    int deleteByExample(LearningProcessSemesterAllocationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(LearningProcessSemesterAllocation record);

    int insertSelective(LearningProcessSemesterAllocation record);

    List<LearningProcessSemesterAllocation> selectByExample(LearningProcessSemesterAllocationExample example);

    LearningProcessSemesterAllocation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") LearningProcessSemesterAllocation record, @Param("example") LearningProcessSemesterAllocationExample example);

    int updateByExample(@Param("record") LearningProcessSemesterAllocation record, @Param("example") LearningProcessSemesterAllocationExample example);

    int updateByPrimaryKeySelective(LearningProcessSemesterAllocation record);

    int updateByPrimaryKey(LearningProcessSemesterAllocation record);
}