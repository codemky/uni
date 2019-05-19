package edu.uni.userBaseInfo1.mapper;

import edu.uni.userBaseInfo1.VO.CurriculumVO;
import edu.uni.userBaseInfo1.VO.CurriculumWithCondition;
import edu.uni.userBaseInfo1.bean.Curriculum;
import edu.uni.userBaseInfo1.bean.CurriculumExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CurriculumMapper {
    long countByExample(CurriculumExample example);

    int deleteByExample(CurriculumExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Curriculum record);

    int insertSelective(Curriculum record);

    List<Curriculum> selectByExample(CurriculumExample example);

    Curriculum selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Curriculum record, @Param("example") CurriculumExample example);

    int updateByExample(@Param("record") Curriculum record, @Param("example") CurriculumExample example);

    int updateByPrimaryKeySelective(Curriculum record);

    int updateByPrimaryKey(Curriculum record);

    List<CurriculumVO> selectCurriculumNameByCondition(CurriculumWithCondition curriculumWithCondition);

    List<Curriculum> getCurriculumMsgList(Map<String,List<Long>> curriculumMap);
}