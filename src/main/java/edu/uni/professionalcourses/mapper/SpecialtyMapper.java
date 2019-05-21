package edu.uni.professionalcourses.mapper;

import edu.uni.professionalcourses.bean.Specialty;
import edu.uni.professionalcourses.bean.SpecialtyExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SpecialtyMapper {
    int countByExample(SpecialtyExample example);

    int deleteByExample(SpecialtyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Specialty record);

    int insertSelective(Specialty record);

    List<Specialty> selectByExample(SpecialtyExample example);

    Specialty selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Specialty record, @Param("example") SpecialtyExample example);

    int updateByExample(@Param("record") Specialty record, @Param("example") SpecialtyExample example);

    int updateByPrimaryKeySelective(Specialty record);

    int updateByPrimaryKey(Specialty record);
}