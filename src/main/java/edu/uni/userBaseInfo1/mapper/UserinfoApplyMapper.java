package edu.uni.userBaseInfo1.mapper;

import edu.uni.userBaseInfo1.bean.UserinfoApply;
import edu.uni.userBaseInfo1.bean.UserinfoApplyExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserinfoApplyMapper {
    //按条件计数
    int countByExample(UserinfoApplyExample example);

    //按条件删除
    int deleteByExample(UserinfoApplyExample example);

    //按主键删除
    int deleteByPrimaryKey(Long id);

    //插入数据（返回值为id)
    int insert(UserinfoApply record);

    //插入数据不为null的字段值
    int insertSelective(UserinfoApply record);

    //按条件查询
    List<UserinfoApply> selectByExample(UserinfoApplyExample example);

    //按主键查询
    UserinfoApply selectByPrimaryKey(Long id);

    //按条件更新值不为null的字段
    int updateByExampleSelective(@Param("record") UserinfoApply record, @Param("example") UserinfoApplyExample example);

    //按条件更新
    int updateByExample(@Param("record") UserinfoApply record, @Param("example") UserinfoApplyExample example);

    //按主键更新不为null的字段
    int updateByPrimaryKeySelective(UserinfoApply record);

    //按主键更新
    int updateByPrimaryKey(UserinfoApply record);
}