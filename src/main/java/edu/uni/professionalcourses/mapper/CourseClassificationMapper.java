package edu.uni.professionalcourses.mapper;

import edu.uni.professionalcourses.bean.CourseClassification;
import edu.uni.professionalcourses.bean.CourseClassificationExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseClassificationMapper {
    int countByExample(CourseClassificationExample example);

    int deleteByExample(CourseClassificationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CourseClassification record);

    int insertSelective(CourseClassification record);

    List<CourseClassification> selectByExample(CourseClassificationExample example);

    CourseClassification selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CourseClassification record, @Param("example") CourseClassificationExample example);

    int updateByExample(@Param("record") CourseClassification record, @Param("example") CourseClassificationExample example);

    int updateByPrimaryKeySelective(CourseClassification record);

    int updateByPrimaryKey(CourseClassification record);
}