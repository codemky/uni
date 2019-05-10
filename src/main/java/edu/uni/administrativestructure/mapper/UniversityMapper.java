package edu.uni.administrativestructure.mapper;

import edu.uni.administrativestructure.bean.University;
import edu.uni.administrativestructure.bean.UniversityExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UniversityMapper {
    int countByExample(UniversityExample example);

    int deleteByExample(UniversityExample example);

    int deleteByPrimaryKey(Long id);

    int insert(University record);

    int insertSelective(University record);

    List<University> selectByExample(UniversityExample example);

    University selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") University record, @Param("example") UniversityExample example);

    int updateByExample(@Param("record") University record, @Param("example") UniversityExample example);

    int updateByPrimaryKeySelective(University record);

    int updateByPrimaryKey(University record);
}