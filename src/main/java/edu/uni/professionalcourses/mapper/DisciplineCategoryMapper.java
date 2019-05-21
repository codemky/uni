package edu.uni.professionalcourses.mapper;

import edu.uni.professionalcourses.bean.DisciplineCategory;
import edu.uni.professionalcourses.bean.DisciplineCategoryExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DisciplineCategoryMapper {
    int countByExample(DisciplineCategoryExample example);

    int deleteByExample(DisciplineCategoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DisciplineCategory record);

    int insertSelective(DisciplineCategory record);

    List<DisciplineCategory> selectByExample(DisciplineCategoryExample example);

    DisciplineCategory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DisciplineCategory record, @Param("example") DisciplineCategoryExample example);

    int updateByExample(@Param("record") DisciplineCategory record, @Param("example") DisciplineCategoryExample example);

    int updateByPrimaryKeySelective(DisciplineCategory record);

    int updateByPrimaryKey(DisciplineCategory record);
}