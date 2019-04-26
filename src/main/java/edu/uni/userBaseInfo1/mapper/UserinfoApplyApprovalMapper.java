package edu.uni.userBaseInfo1.mapper;

import edu.uni.userBaseInfo1.bean.UserinfoApplyApproval;
import edu.uni.userBaseInfo1.bean.UserinfoApplyApprovalExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserinfoApplyApprovalMapper {
    //按条件计数
    int countByExample(UserinfoApplyApprovalExample example);

    //按条件删除
    int deleteByExample(UserinfoApplyApprovalExample example);

    //按主键删除
    int deleteByPrimaryKey(Long id);

    //插入数据（返回值为id)
    int insert(UserinfoApplyApproval record);

    //插入数据不为null的字段值
    int insertSelective(UserinfoApplyApproval record);

    //按条件查询
    List<UserinfoApplyApproval> selectByExample(UserinfoApplyApprovalExample example);

    //按主键查询
    UserinfoApplyApproval selectByPrimaryKey(Long id);

    //按条件更新值不为null的字段
    int updateByExampleSelective(@Param("record") UserinfoApplyApproval record, @Param("example") UserinfoApplyApprovalExample example);

    //按条件更新
    int updateByExample(@Param("record") UserinfoApplyApproval record, @Param("example") UserinfoApplyApprovalExample example);

    //按主键更新不为null的字段
    int updateByPrimaryKeySelective(UserinfoApplyApproval record);

    //按主键更新
    int updateByPrimaryKey(UserinfoApplyApproval record);
}