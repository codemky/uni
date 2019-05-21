package edu.uni.professionalcourses.mapper;

import edu.uni.professionalcourses.bean.SecondLevelDiscipline;
import edu.uni.professionalcourses.bean.SecondLevelDisciplineExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SecondLevelDisciplineMapper {
    int countByExample(SecondLevelDisciplineExample example);

    int deleteByExample(SecondLevelDisciplineExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SecondLevelDiscipline record);

    int insertSelective(SecondLevelDiscipline record);

    List<SecondLevelDiscipline> selectByExample(SecondLevelDisciplineExample example);

    SecondLevelDiscipline selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SecondLevelDiscipline record, @Param("example") SecondLevelDisciplineExample example);

    int updateByExample(@Param("record") SecondLevelDiscipline record, @Param("example") SecondLevelDisciplineExample example);

    int updateByPrimaryKeySelective(SecondLevelDiscipline record);

    int updateByPrimaryKey(SecondLevelDiscipline record);
}