package edu.uni.professionalcourses.mapper;

import edu.uni.professionalcourses.bean.PreCourse;
import edu.uni.professionalcourses.bean.PreCourseExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PreCourseMapper {
    int countByExample(PreCourseExample example);

    int deleteByExample(PreCourseExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PreCourse record);

    int insertSelective(PreCourse record);

    List<PreCourse> selectByExample(PreCourseExample example);

    PreCourse selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PreCourse record, @Param("example") PreCourseExample example);

    int updateByExample(@Param("record") PreCourse record, @Param("example") PreCourseExample example);

    int updateByPrimaryKeySelective(PreCourse record);

    int updateByPrimaryKey(PreCourse record);
}