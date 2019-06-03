package edu.uni.place.mapper;

import edu.uni.place.bean.Approvalstep;
import edu.uni.place.bean.ApprovalstepExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApprovalstepMapper {
    long countByExample(ApprovalstepExample example);

    int deleteByExample(ApprovalstepExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Approvalstep record);

    int insertSelective(Approvalstep record);

    List<Approvalstep> selectByExample(ApprovalstepExample example);

    Approvalstep selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Approvalstep record, @Param("example") ApprovalstepExample example);

    int updateByExample(@Param("record") Approvalstep record, @Param("example") ApprovalstepExample example);

    int updateByPrimaryKeySelective(Approvalstep record);

    int updateByPrimaryKey(Approvalstep record);
}