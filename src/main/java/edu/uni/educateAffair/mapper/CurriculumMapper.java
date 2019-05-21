package edu.uni.educateAffair.mapper;

import edu.uni.educateAffair.VO.CurriculumWithCondition;
import edu.uni.educateAffair.bean.Curriculum;
import edu.uni.educateAffair.bean.CurriculumExample;
import edu.uni.educateAffair.VO.CurriculumVO;
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

    /*CurriculumVO selectCurriculumByDateAndTeacherId(Curriculum curriculum);*/
    List<CurriculumVO> selectCurriculumNameByCondition(CurriculumWithCondition curriculumWithCondition);

    List<Curriculum> getCurriculumMsgList(Map<String, List<Long>> curriculumMap);
}