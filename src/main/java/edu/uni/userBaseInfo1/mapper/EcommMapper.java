package edu.uni.userBaseInfo1.mapper;

import edu.uni.userBaseInfo1.bean.Ecomm;
import edu.uni.userBaseInfo1.bean.EcommExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EcommMapper {
    int countByExample(EcommExample example);

    int deleteByExample(EcommExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Ecomm record);

    int insertSelective(Ecomm record);

    List<Ecomm> selectByExample(EcommExample example);

    Ecomm selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Ecomm record, @Param("example") EcommExample example);

    int updateByExample(@Param("record") Ecomm record, @Param("example") EcommExample example);

    int updateByPrimaryKeySelective(Ecomm record);

    int updateByPrimaryKey(Ecomm record);
}