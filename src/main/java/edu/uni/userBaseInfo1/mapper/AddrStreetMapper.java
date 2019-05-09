package edu.uni.userBaseInfo1.mapper;

import edu.uni.userBaseInfo1.bean.AddrStreet;
import edu.uni.userBaseInfo1.bean.AddrStreetExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AddrStreetMapper {
    int countByExample(AddrStreetExample example);

    int deleteByExample(AddrStreetExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AddrStreet record);

    int insertSelective(AddrStreet record);

    List<AddrStreet> selectByExample(AddrStreetExample example);

    AddrStreet selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AddrStreet record, @Param("example") AddrStreetExample example);

    int updateByExample(@Param("record") AddrStreet record, @Param("example") AddrStreetExample example);

    int updateByPrimaryKeySelective(AddrStreet record);

    int updateByPrimaryKey(AddrStreet record);

    List<AddrStreet> selectByAreaCode(Long AreaCode);
}