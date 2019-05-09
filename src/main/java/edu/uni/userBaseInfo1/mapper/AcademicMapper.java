package edu.uni.userBaseInfo1.mapper;

import edu.uni.userBaseInfo1.bean.Academic;
import edu.uni.userBaseInfo1.bean.AcademicExample;
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