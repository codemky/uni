package edu.uni.professionalcourses.mapper;

import edu.uni.professionalcourses.bean.Academic;
import edu.uni.professionalcourses.bean.AcademicExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AcademicMapper {
    int countByExample(AcademicExample example);

    int deleteByExample(AcademicExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Academic record);

    int insertSelective(Academic record);

    List<Academic> selectByExample(AcademicExample example);

    Academic selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Academic record, @Param("example") AcademicExample example);

    int updateByExample(@Param("record") Academic record, @Param("example") AcademicExample example);

    int updateByPrimaryKeySelective(Academic record);

    int updateByPrimaryKey(Academic record);
}