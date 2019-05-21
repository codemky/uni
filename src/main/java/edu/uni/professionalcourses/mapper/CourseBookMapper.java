package edu.uni.professionalcourses.mapper;

import edu.uni.professionalcourses.bean.CourseBook;
import edu.uni.professionalcourses.bean.CourseBookExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseBookMapper {
    int countByExample(CourseBookExample example);

    int deleteByExample(CourseBookExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CourseBook record);

    int insertSelective(CourseBook record);

    List<CourseBook> selectByExample(CourseBookExample example);

    CourseBook selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CourseBook record, @Param("example") CourseBookExample example);

    int updateByExample(@Param("record") CourseBook record, @Param("example") CourseBookExample example);

    int updateByPrimaryKeySelective(CourseBook record);

    int updateByPrimaryKey(CourseBook record);
}