package edu.uni.userBaseInfo1.mapper;

import edu.uni.userBaseInfo1.bean.SecondLevelDiscipline;
import edu.uni.userBaseInfo1.bean.SecondLevelDisciplineExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SecondLevelDisciplineMapper {
    //按条件计数
    int countByExample(SecondLevelDisciplineExample example);

    //按条件删除
    int deleteByExample(SecondLevelDisciplineExample example);

    //按主键删除
    int deleteByPrimaryKey(Long id);

    //插入数据（返回值为id)
    int insert(SecondLevelDiscipline record);

    //插入数据不为null的字段值
    int insertSelective(SecondLevelDiscipline record);

    //按条件查询
    List<SecondLevelDiscipline> selectByExample(SecondLevelDisciplineExample example);

    //按主键查询
    SecondLevelDiscipline selectByPrimaryKey(Long id);

    //按条件更新值不为null的字段
    int updateByExampleSelective(@Param("record") SecondLevelDiscipline record, @Param("example") SecondLevelDisciplineExample example);

    //按条件更新
    int updateByExample(@Param("record") SecondLevelDiscipline record, @Param("example") SecondLevelDisciplineExample example);

    //按主键更新不为null的字段
    int updateByPrimaryKeySelective(SecondLevelDiscipline record);

    //按主键更新
    int updateByPrimaryKey(SecondLevelDiscipline record);
}