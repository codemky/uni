package edu.uni.educateAffair.mapper;

import edu.uni.educateAffair.bean.Canlendar;
import edu.uni.educateAffair.bean.CanlendarExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CanlendarMapper {
    long countByExample(CanlendarExample example);

    int deleteByExample(CanlendarExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Canlendar record);

    int insertBatch(List<Canlendar> record);

    int insertSelective(Canlendar record);

    List<Canlendar> selectByExample(CanlendarExample example);

    Canlendar selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Canlendar record, @Param("example") CanlendarExample example);

    int updateByExample(@Param("record") Canlendar record, @Param("example") CanlendarExample example);

    int updateByPrimaryKeySelective(Canlendar record);

    int updateByPrimaryKey(Canlendar record);
}