package edu.uni.professionalcourses.mapper;

import edu.uni.professionalcourses.bean.ExperimentSettingContent;
import edu.uni.professionalcourses.bean.ExperimentSettingContentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExperimentSettingContentMapper {
    int countByExample(ExperimentSettingContentExample example);

    int deleteByExample(ExperimentSettingContentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ExperimentSettingContent record);

    int insertSelective(ExperimentSettingContent record);

    List<ExperimentSettingContent> selectByExample(ExperimentSettingContentExample example);

    ExperimentSettingContent selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ExperimentSettingContent record, @Param("example") ExperimentSettingContentExample example);

    int updateByExample(@Param("record") ExperimentSettingContent record, @Param("example") ExperimentSettingContentExample example);

    int updateByPrimaryKeySelective(ExperimentSettingContent record);

    int updateByPrimaryKey(ExperimentSettingContent record);
}