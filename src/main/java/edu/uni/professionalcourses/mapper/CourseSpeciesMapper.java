package edu.uni.professionalcourses.mapper;

import edu.uni.professionalcourses.bean.CourseSpecies;
import edu.uni.professionalcourses.bean.CourseSpeciesExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseSpeciesMapper {
    int countByExample(CourseSpeciesExample example);

    int deleteByExample(CourseSpeciesExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CourseSpecies record);

    int insertSelective(CourseSpecies record);

    List<CourseSpecies> selectByExample(CourseSpeciesExample example);

    CourseSpecies selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CourseSpecies record, @Param("example") CourseSpeciesExample example);

    int updateByExample(@Param("record") CourseSpecies record, @Param("example") CourseSpeciesExample example);

    int updateByPrimaryKeySelective(CourseSpecies record);

    int updateByPrimaryKey(CourseSpecies record);
}