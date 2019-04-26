package edu.uni.userBaseInfo1.mapper;

import edu.uni.userBaseInfo1.bean.PoliticalAffiliation;
import edu.uni.userBaseInfo1.bean.PoliticalAffiliationExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PoliticalAffiliationMapper {
    //按条件计数
    int countByExample(PoliticalAffiliationExample example);

    //按条件删除
    int deleteByExample(PoliticalAffiliationExample example);

    //按主键删除
    int deleteByPrimaryKey(Long id);

    //插入数据（返回值为id)
    int insert(PoliticalAffiliation record);

    //插入数据不为null的字段值
    int insertSelective(PoliticalAffiliation record);

    //按条件查询
    List<PoliticalAffiliation> selectByExample(PoliticalAffiliationExample example);

    //按主键查询
    PoliticalAffiliation selectByPrimaryKey(Long id);

    //按条件更新值不为null的字段
    int updateByExampleSelective(@Param("record") PoliticalAffiliation record, @Param("example") PoliticalAffiliationExample example);

    //按条件更新
    int updateByExample(@Param("record") PoliticalAffiliation record, @Param("example") PoliticalAffiliationExample example);

    //按主键更新不为null的字段
    int updateByPrimaryKeySelective(PoliticalAffiliation record);

    //按主键更新
    int updateByPrimaryKey(PoliticalAffiliation record);
}