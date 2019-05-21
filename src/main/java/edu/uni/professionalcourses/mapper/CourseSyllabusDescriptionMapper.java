package edu.uni.professionalcourses.mapper;

import edu.uni.professionalcourses.bean.CourseSyllabusDescription;
import edu.uni.professionalcourses.bean.CourseSyllabusDescriptionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseSyllabusDescriptionMapper {
    int countByExample(CourseSyllabusDescriptionExample example);

    int deleteByExample(CourseSyllabusDescriptionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CourseSyllabusDescription record);

    int insertSelective(CourseSyllabusDescription record);

    List<CourseSyllabusDescription> selectByExample(CourseSyllabusDescriptionExample example);

    CourseSyllabusDescription selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CourseSyllabusDescription record, @Param("example") CourseSyllabusDescriptionExample example);

    int updateByExample(@Param("record") CourseSyllabusDescription record, @Param("example") CourseSyllabusDescriptionExample example);

    int updateByPrimaryKeySelective(CourseSyllabusDescription record);

    int updateByPrimaryKey(CourseSyllabusDescription record);
}