package edu.uni.userBaseInfo1.mapper;

import edu.uni.userBaseInfo1.bean.UserinfoApplyApproval;
import edu.uni.userBaseInfo1.bean.UserinfoApplyApprovalExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserinfoApplyApprovalMapper {
    int countByExample(UserinfoApplyApprovalExample example);

    int deleteByExample(UserinfoApplyApprovalExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserinfoApplyApproval record);

    int insertSelective(UserinfoApplyApproval record);

    List<UserinfoApplyApproval> selectByExample(UserinfoApplyApprovalExample example);

    UserinfoApplyApproval selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserinfoApplyApproval record, @Param("example") UserinfoApplyApprovalExample example);

    int updateByExample(@Param("record") UserinfoApplyApproval record, @Param("example") UserinfoApplyApprovalExample example);

    int updateByPrimaryKeySelective(UserinfoApplyApproval record);

    int updateByPrimaryKey(UserinfoApplyApproval record);
}