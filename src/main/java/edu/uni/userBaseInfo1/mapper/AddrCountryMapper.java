package edu.uni.userBaseInfo1.mapper;

import edu.uni.userBaseInfo1.bean.AddrCountry;
import edu.uni.userBaseInfo1.bean.AddrCountryExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AddrCountryMapper {
    int countByExample(AddrCountryExample example);

    int deleteByExample(AddrCountryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AddrCountry record);

    int insertSelective(AddrCountry record);

    List<AddrCountry> selectByExample(AddrCountryExample example);

    AddrCountry selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AddrCountry record, @Param("example") AddrCountryExample example);

    int updateByExample(@Param("record") AddrCountry record, @Param("example") AddrCountryExample example);

    int updateByPrimaryKeySelective(AddrCountry record);

    int updateByPrimaryKey(AddrCountry record);
}