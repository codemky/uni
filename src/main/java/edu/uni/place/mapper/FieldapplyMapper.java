package edu.uni.place.mapper;

import edu.uni.place.bean.Fieldapply;
import edu.uni.place.bean.FieldapplyExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FieldapplyMapper {
    long countByExample(FieldapplyExample example);

    int deleteByExample(FieldapplyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Fieldapply record);

    int insertSelective(Fieldapply record);

    List<Fieldapply> selectByExample(FieldapplyExample example);

    Fieldapply selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Fieldapply record, @Param("example") FieldapplyExample example);

    int updateByExample(@Param("record") Fieldapply record, @Param("example") FieldapplyExample example);

    int updateByPrimaryKeySelective(Fieldapply record);

    int updateByPrimaryKey(Fieldapply record);
}