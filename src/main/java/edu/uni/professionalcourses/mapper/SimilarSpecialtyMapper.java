package edu.uni.professionalcourses.mapper;

import edu.uni.professionalcourses.bean.SimilarSpecialty;
import edu.uni.professionalcourses.bean.SimilarSpecialtyExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SimilarSpecialtyMapper {
    int countByExample(SimilarSpecialtyExample example);

    int deleteByExample(SimilarSpecialtyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SimilarSpecialty record);

    int insertSelective(SimilarSpecialty record);

    List<SimilarSpecialty> selectByExample(SimilarSpecialtyExample example);

    SimilarSpecialty selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SimilarSpecialty record, @Param("example") SimilarSpecialtyExample example);

    int updateByExample(@Param("record") SimilarSpecialty record, @Param("example") SimilarSpecialtyExample example);

    int updateByPrimaryKeySelective(SimilarSpecialty record);

    int updateByPrimaryKey(SimilarSpecialty record);
}