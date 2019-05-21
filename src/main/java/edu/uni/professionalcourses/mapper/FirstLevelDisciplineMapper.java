package edu.uni.professionalcourses.mapper;

import edu.uni.professionalcourses.bean.FirstLevelDiscipline;
import edu.uni.professionalcourses.bean.FirstLevelDisciplineExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FirstLevelDisciplineMapper {
    int countByExample(FirstLevelDisciplineExample example);

    int deleteByExample(FirstLevelDisciplineExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FirstLevelDiscipline record);

    int insertSelective(FirstLevelDiscipline record);

    List<FirstLevelDiscipline> selectByExample(FirstLevelDisciplineExample example);

    FirstLevelDiscipline selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FirstLevelDiscipline record, @Param("example") FirstLevelDisciplineExample example);

    int updateByExample(@Param("record") FirstLevelDiscipline record, @Param("example") FirstLevelDisciplineExample example);

    int updateByPrimaryKeySelective(FirstLevelDiscipline record);

    int updateByPrimaryKey(FirstLevelDiscipline record);
}