package edu.uni.place.mapper;

import edu.uni.place.bean.Fieldapplyapproval;
import edu.uni.place.bean.FieldapplyapprovalExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FieldapplyapprovalMapper {
    long countByExample(FieldapplyapprovalExample example);

    int deleteByExample(FieldapplyapprovalExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Fieldapplyapproval record);

    int insertSelective(Fieldapplyapproval record);

    List<Fieldapplyapproval> selectByExample(FieldapplyapprovalExample example);

    Fieldapplyapproval selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Fieldapplyapproval record, @Param("example") FieldapplyapprovalExample example);

    int updateByExample(@Param("record") Fieldapplyapproval record, @Param("example") FieldapplyapprovalExample example);

    int updateByPrimaryKeySelective(Fieldapplyapproval record);

    int updateByPrimaryKey(Fieldapplyapproval record);
}