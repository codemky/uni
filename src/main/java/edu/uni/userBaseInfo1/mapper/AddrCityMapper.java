package edu.uni.userBaseInfo1.mapper;

import edu.uni.userBaseInfo1.bean.AddrCity;
import edu.uni.userBaseInfo1.bean.AddrCityExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AddrCityMapper {
    int countByExample(AddrCityExample example);

    int deleteByExample(AddrCityExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AddrCity record);

    int insertSelective(AddrCity record);

    List<AddrCity> selectByExample(AddrCityExample example);

    AddrCity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AddrCity record, @Param("example") AddrCityExample example);

    int updateByExample(@Param("record") AddrCity record, @Param("example") AddrCityExample example);

    int updateByPrimaryKeySelective(AddrCity record);

    int updateByPrimaryKey(AddrCity record);

    List<AddrCity> selectByStateCode(Long stateCode);
}