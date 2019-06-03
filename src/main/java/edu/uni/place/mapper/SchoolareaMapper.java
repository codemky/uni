package edu.uni.place.mapper;

import edu.uni.place.bean.Schoolarea;
import edu.uni.place.bean.SchoolareaExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SchoolareaMapper {
    long countByExample(SchoolareaExample example);

    int deleteByExample(SchoolareaExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Schoolarea record);

    int insertSelective(Schoolarea record);

    List<Schoolarea> selectByExample(SchoolareaExample example);

    Schoolarea selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Schoolarea record, @Param("example") SchoolareaExample example);

    int updateByExample(@Param("record") Schoolarea record, @Param("example") SchoolareaExample example);

    int updateByPrimaryKeySelective(Schoolarea record);

    int updateByPrimaryKey(Schoolarea record);
}