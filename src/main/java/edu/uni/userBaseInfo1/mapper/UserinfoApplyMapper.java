package edu.uni.userBaseInfo1.mapper;

import edu.uni.userBaseInfo1.bean.UserinfoApply;
import edu.uni.userBaseInfo1.bean.UserinfoApplyExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserinfoApplyMapper {
    int countByExample(UserinfoApplyExample example);

    int deleteByExample(UserinfoApplyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserinfoApply record);

    int insertSelective(UserinfoApply record);

    List<UserinfoApply> selectByExample(UserinfoApplyExample example);

    UserinfoApply selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserinfoApply record, @Param("example") UserinfoApplyExample example);

    int updateByExample(@Param("record") UserinfoApply record, @Param("example") UserinfoApplyExample example);

    int updateByPrimaryKeySelective(UserinfoApply record);

    int updateByPrimaryKey(UserinfoApply record);
}