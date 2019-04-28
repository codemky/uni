package edu.uni.userBaseInfo1.mapper;

import edu.uni.userBaseInfo1.bean.ApprovalMain;
import edu.uni.userBaseInfo1.bean.ApprovalMainExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApprovalMainMapper {
    int countByExample(ApprovalMainExample example);

    int deleteByExample(ApprovalMainExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ApprovalMain record);

    int insertSelective(ApprovalMain record);

    List<ApprovalMain> selectByExample(ApprovalMainExample example);

    ApprovalMain selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ApprovalMain record, @Param("example") ApprovalMainExample example);

    int updateByExample(@Param("record") ApprovalMain record, @Param("example") ApprovalMainExample example);

    int updateByPrimaryKeySelective(ApprovalMain record);

    int updateByPrimaryKey(ApprovalMain record);
}