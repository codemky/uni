package edu.uni.userBaseInfo1.mapper;

import edu.uni.userBaseInfo1.bean.PoliticalAffiliation;
import edu.uni.userBaseInfo1.bean.PoliticalAffiliationExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PoliticalAffiliationMapper {
    int countByExample(PoliticalAffiliationExample example);

    int deleteByExample(PoliticalAffiliationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PoliticalAffiliation record);

    int insertSelective(PoliticalAffiliation record);

    List<PoliticalAffiliation> selectByExample(PoliticalAffiliationExample example);

    PoliticalAffiliation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PoliticalAffiliation record, @Param("example") PoliticalAffiliationExample example);

    int updateByExample(@Param("record") PoliticalAffiliation record, @Param("example") PoliticalAffiliationExample example);

    int updateByPrimaryKeySelective(PoliticalAffiliation record);

    int updateByPrimaryKey(PoliticalAffiliation record);
}