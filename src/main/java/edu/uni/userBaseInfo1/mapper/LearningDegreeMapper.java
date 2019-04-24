package edu.uni.userBaseInfo1.mapper;

import edu.uni.userBaseInfo1.bean.LearningDegree;
import edu.uni.userBaseInfo1.bean.LearningDegreeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LearningDegreeMapper {
    int countByExample(LearningDegreeExample example);

    int deleteByExample(LearningDegreeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(LearningDegree record);

    int insertSelective(LearningDegree record);

    List<LearningDegree> selectByExample(LearningDegreeExample example);

    LearningDegree selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") LearningDegree record, @Param("example") LearningDegreeExample example);

    int updateByExample(@Param("record") LearningDegree record, @Param("example") LearningDegreeExample example);

    int updateByPrimaryKeySelective(LearningDegree record);

    int updateByPrimaryKey(LearningDegree record);
}