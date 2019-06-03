package edu.uni.place.mapper;

import edu.uni.place.bean.Fieldincharge;
import edu.uni.place.bean.FieldinchargeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FieldinchargeMapper {
    long countByExample(FieldinchargeExample example);

    int deleteByExample(FieldinchargeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Fieldincharge record);

    int insertSelective(Fieldincharge record);

    List<Fieldincharge> selectByExample(FieldinchargeExample example);

    Fieldincharge selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Fieldincharge record, @Param("example") FieldinchargeExample example);

    int updateByExample(@Param("record") Fieldincharge record, @Param("example") FieldinchargeExample example);

    int updateByPrimaryKeySelective(Fieldincharge record);

    int updateByPrimaryKey(Fieldincharge record);
}