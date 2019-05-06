package edu.uni.userBaseInfo1.mapper;

import edu.uni.userBaseInfo1.bean.LearningDegree;
import edu.uni.userBaseInfo1.bean.LearningDegreeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LearningDegreeMapper {
    //按条件计数
    int countByExample(LearningDegreeExample example);

    //按条件删除
    int deleteByExample(LearningDegreeExample example);

    //按主键删除
    int deleteByPrimaryKey(Long id);

    //插入数据（返回值为id)
    int insert(LearningDegree record);

    //插入数据不为null的字段值
    int insertSelective(LearningDegree record);

    //按条件查询
    List<LearningDegree> selectByExample(LearningDegreeExample example);

    //按主键查询
    LearningDegree selectByPrimaryKey(Long id);

    //根据user_id查询学历
    List<LearningDegree>selectByUserId(Long user_id);

    //按条件更新值不为null的字段
    int updateByExampleSelective(@Param("record") LearningDegree record, @Param("example") LearningDegreeExample example);

    //按条件更新
    int updateByExample(@Param("record") LearningDegree record, @Param("example") LearningDegreeExample example);

    //按主键更新不为null的字段
    int updateByPrimaryKeySelective(LearningDegree record);

    //按主键更新
    int updateByPrimaryKey(LearningDegree record);
}