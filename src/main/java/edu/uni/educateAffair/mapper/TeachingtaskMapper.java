package edu.uni.educateAffair.mapper;

import edu.uni.educateAffair.bean.Teachingtask;
import edu.uni.educateAffair.bean.TeachingtaskExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeachingtaskMapper {
    long countByExample(TeachingtaskExample example);

    int deleteByExample(TeachingtaskExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Teachingtask record);

    int insertSelective(Teachingtask record);

    List<Teachingtask> selectByExample(TeachingtaskExample example);

    Teachingtask selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Teachingtask record, @Param("example") TeachingtaskExample example);

    int updateByExample(@Param("record") Teachingtask record, @Param("example") TeachingtaskExample example);

    int updateByPrimaryKeySelective(Teachingtask record);

    int updateByPrimaryKey(Teachingtask record);
}