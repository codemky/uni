package edu.uni.userBaseInfo1.mapper;

import edu.uni.userBaseInfo1.bean.AddrState;
import edu.uni.userBaseInfo1.bean.AddrStateExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AddrStateMapper {
    int countByExample(AddrStateExample example);

    int deleteByExample(AddrStateExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AddrState record);

    int insertSelective(AddrState record);

    List<AddrState> selectByExample(AddrStateExample example);

    AddrState selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AddrState record, @Param("example") AddrStateExample example);

    int updateByExample(@Param("record") AddrState record, @Param("example") AddrStateExample example);

    int updateByPrimaryKeySelective(AddrState record);

    int updateByPrimaryKey(AddrState record);

    List<AddrState> selectByCountryCode(Long countryCode);
}