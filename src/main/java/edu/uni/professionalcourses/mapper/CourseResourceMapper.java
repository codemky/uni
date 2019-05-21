package edu.uni.professionalcourses.mapper;

import edu.uni.professionalcourses.bean.CourseResource;
import edu.uni.professionalcourses.bean.CourseResourceExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseResourceMapper {
    int countByExample(CourseResourceExample example);

    int deleteByExample(CourseResourceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CourseResource record);

    int insertSelective(CourseResource record);

    List<CourseResource> selectByExample(CourseResourceExample example);

    CourseResource selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CourseResource record, @Param("example") CourseResourceExample example);

    int updateByExample(@Param("record") CourseResource record, @Param("example") CourseResourceExample example);

    int updateByPrimaryKeySelective(CourseResource record);

    int updateByPrimaryKey(CourseResource record);
}