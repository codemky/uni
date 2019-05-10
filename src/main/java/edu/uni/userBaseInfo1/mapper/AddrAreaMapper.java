package edu.uni.userBaseInfo1.mapper;

import edu.uni.userBaseInfo1.bean.AddrArea;
import edu.uni.userBaseInfo1.bean.AddrAreaExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AddrAreaMapper {
    int countByExample(AddrAreaExample example);

    int deleteByExample(AddrAreaExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AddrArea record);

    int insertSelective(AddrArea record);

    List<AddrArea> selectByExample(AddrAreaExample example);

    AddrArea selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AddrArea record, @Param("example") AddrAreaExample example);

    int updateByExample(@Param("record") AddrArea record, @Param("example") AddrAreaExample example);

    int updateByPrimaryKeySelective(AddrArea record);

    int updateByPrimaryKey(AddrArea record);

    List<AddrArea> selectByCityCode(Long cityCode);
}