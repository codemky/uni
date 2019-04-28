package edu.uni.userBaseInfo1.mapper;

import edu.uni.userBaseInfo1.bean.ApprovalStepIncharge;
import edu.uni.userBaseInfo1.bean.ApprovalStepInchargeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApprovalStepInchargeMapper {
    int countByExample(ApprovalStepInchargeExample example);

    int deleteByExample(ApprovalStepInchargeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ApprovalStepIncharge record);

    int insertSelective(ApprovalStepIncharge record);

    List<ApprovalStepIncharge> selectByExample(ApprovalStepInchargeExample example);

    ApprovalStepIncharge selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ApprovalStepIncharge record, @Param("example") ApprovalStepInchargeExample example);

    int updateByExample(@Param("record") ApprovalStepIncharge record, @Param("example") ApprovalStepInchargeExample example);

    int updateByPrimaryKeySelective(ApprovalStepIncharge record);

    int updateByPrimaryKey(ApprovalStepIncharge record);
}