package edu.uni.userBaseInfo1.mapper;

import edu.uni.userBaseInfo1.bean.StudentRelation;
import edu.uni.userBaseInfo1.bean.StudentRelationExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentRelationMapper {
    int countByExample(StudentRelationExample example);

    int deleteByExample(StudentRelationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(StudentRelation record);

    int insertSelective(StudentRelation record);

    List<StudentRelation> selectByExample(StudentRelationExample example);

    StudentRelation selectByPrimaryKey(Long id);

    List<StudentRelation> selectByUserId(Long userId);

    StudentRelation selectByRelaId(Long relaId);

    int updateByExampleSelective(@Param("record") StudentRelation record, @Param("example") StudentRelationExample example);

    int updateByExample(@Param("record") StudentRelation record, @Param("example") StudentRelationExample example);

    int updateByPrimaryKeySelective(StudentRelation record);

    int updateByPrimaryKey(StudentRelation record);
}