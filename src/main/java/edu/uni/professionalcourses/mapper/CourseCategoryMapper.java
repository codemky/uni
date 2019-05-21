package edu.uni.professionalcourses.mapper;

import edu.uni.professionalcourses.bean.CourseCategory;
import edu.uni.professionalcourses.bean.CourseCategoryExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseCategoryMapper {
    int countByExample(CourseCategoryExample example);

    int deleteByExample(CourseCategoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CourseCategory record);

    int insertSelective(CourseCategory record);

    List<CourseCategory> selectByExample(CourseCategoryExample example);

    CourseCategory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CourseCategory record, @Param("example") CourseCategoryExample example);

    int updateByExample(@Param("record") CourseCategory record, @Param("example") CourseCategoryExample example);

    int updateByPrimaryKeySelective(CourseCategory record);

    int updateByPrimaryKey(CourseCategory record);
}