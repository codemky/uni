package edu.uni.professionalcourses.mapper;

import edu.uni.professionalcourses.bean.CourseSyllabus;
import edu.uni.professionalcourses.bean.CourseSyllabusExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseSyllabusMapper {
    int countByExample(CourseSyllabusExample example);

    int deleteByExample(CourseSyllabusExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CourseSyllabus record);

    int insertSelective(CourseSyllabus record);

    List<CourseSyllabus> selectByExample(CourseSyllabusExample example);

    CourseSyllabus selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CourseSyllabus record, @Param("example") CourseSyllabusExample example);

    int updateByExample(@Param("record") CourseSyllabus record, @Param("example") CourseSyllabusExample example);

    int updateByPrimaryKeySelective(CourseSyllabus record);

    int updateByPrimaryKey(CourseSyllabus record);
}