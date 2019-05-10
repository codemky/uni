package edu.uni.administrativestructure.mapper;

import edu.uni.administrativestructure.bean.ClassmatePosition;
import edu.uni.administrativestructure.bean.ClassmatePositionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassmatePositionMapper {
    int countByExample(ClassmatePositionExample example);

    int deleteByExample(ClassmatePositionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ClassmatePosition record);

    int insertSelective(ClassmatePosition record);

    List<ClassmatePosition> selectByExample(ClassmatePositionExample example);

    ClassmatePosition selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ClassmatePosition record, @Param("example") ClassmatePositionExample example);

    int updateByExample(@Param("record") ClassmatePosition record, @Param("example") ClassmatePositionExample example);

    int updateByPrimaryKeySelective(ClassmatePosition record);

    int updateByPrimaryKey(ClassmatePosition record);
}