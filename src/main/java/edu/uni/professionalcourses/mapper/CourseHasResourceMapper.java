package edu.uni.professionalcourses.mapper;

import edu.uni.professionalcourses.bean.CourseHasResource;
import edu.uni.professionalcourses.bean.CourseHasResourceExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseHasResourceMapper {
    int countByExample(CourseHasResourceExample example);

    int deleteByExample(CourseHasResourceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CourseHasResource record);

    int insertSelective(CourseHasResource record);

    List<CourseHasResource> selectByExample(CourseHasResourceExample example);

    CourseHasResource selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CourseHasResource record, @Param("example") CourseHasResourceExample example);

    int updateByExample(@Param("record") CourseHasResource record, @Param("example") CourseHasResourceExample example);

    int updateByPrimaryKeySelective(CourseHasResource record);

    int updateByPrimaryKey(CourseHasResource record);
}