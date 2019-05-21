package edu.uni.professionalcourses.mapper;

import edu.uni.professionalcourses.bean.SyllabusTeachingContent;
import edu.uni.professionalcourses.bean.SyllabusTeachingContentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SyllabusTeachingContentMapper {
    int countByExample(SyllabusTeachingContentExample example);

    int deleteByExample(SyllabusTeachingContentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SyllabusTeachingContent record);

    int insertSelective(SyllabusTeachingContent record);

    List<SyllabusTeachingContent> selectByExample(SyllabusTeachingContentExample example);

    SyllabusTeachingContent selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SyllabusTeachingContent record, @Param("example") SyllabusTeachingContentExample example);

    int updateByExample(@Param("record") SyllabusTeachingContent record, @Param("example") SyllabusTeachingContentExample example);

    int updateByPrimaryKeySelective(SyllabusTeachingContent record);

    int updateByPrimaryKey(SyllabusTeachingContent record);
}