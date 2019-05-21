package edu.uni.administrativestructure.mapper;

import edu.uni.administrativestructure.bean.Class;
import edu.uni.administrativestructure.bean.ClassExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassMapper {
    int countByExample(ClassExample example);

    int deleteByExample(ClassExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Class record);

    int insertSelective(Class record);

    List<Class> selectByExample(ClassExample example);

    Class selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Class record, @Param("example") ClassExample example);

    int updateByExample(@Param("record") Class record, @Param("example") ClassExample example);

    int updateByPrimaryKeySelective(Class record);

    int updateByPrimaryKey(Class record);
}