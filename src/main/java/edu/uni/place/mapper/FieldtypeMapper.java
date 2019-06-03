package edu.uni.place.mapper;

import edu.uni.place.bean.Fieldtype;
import edu.uni.place.bean.FieldtypeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FieldtypeMapper {
    long countByExample(FieldtypeExample example);

    int deleteByExample(FieldtypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Fieldtype record);

    int insertSelective(Fieldtype record);

    List<Fieldtype> selectByExample(FieldtypeExample example);

    Fieldtype selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Fieldtype record, @Param("example") FieldtypeExample example);

    int updateByExample(@Param("record") Fieldtype record, @Param("example") FieldtypeExample example);

    int updateByPrimaryKeySelective(Fieldtype record);

    int updateByPrimaryKey(Fieldtype record);
}