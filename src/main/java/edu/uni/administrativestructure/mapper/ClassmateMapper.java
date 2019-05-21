package edu.uni.administrativestructure.mapper;

import edu.uni.administrativestructure.bean.Classmate;
import edu.uni.administrativestructure.bean.ClassmateExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassmateMapper {
    int countByExample(ClassmateExample example);

    int deleteByExample(ClassmateExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Classmate record);

    int insertSelective(Classmate record);

    List<Classmate> selectByExample(ClassmateExample example);

    Classmate selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Classmate record, @Param("example") ClassmateExample example);

    int updateByExample(@Param("record") Classmate record, @Param("example") ClassmateExample example);

    int updateByPrimaryKeySelective(Classmate record);

    int updateByPrimaryKey(Classmate record);
}