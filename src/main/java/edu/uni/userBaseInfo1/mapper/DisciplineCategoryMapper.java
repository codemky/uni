package edu.uni.userBaseInfo1.mapper;

import edu.uni.userBaseInfo1.bean.DisciplineCategory;
import edu.uni.userBaseInfo1.bean.DisciplineCategoryExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * Author: laizhouhao
 * @apiNote:关于DisciplineCategory表操作的api
 */
public interface DisciplineCategoryMapper {
    //按条件计数
    int countByExample(DisciplineCategoryExample example);

    //按条件删除
    int deleteByExample(DisciplineCategoryExample example);

    //按主键删除
    int deleteByPrimaryKey(Long id);

    //插入所有值，包括null值
    int insert(DisciplineCategory record);

    //插入不为null的值
    int insertSelective(DisciplineCategory record);

    //按条件查询出多条数据
    List<DisciplineCategory> selectByExample(DisciplineCategoryExample example);

    //按主键查询单条数据
    DisciplineCategory selectByPrimaryKey(Long id);

    //按条件更新插入值不为null的字段，即将实体中不为null的属性更新到查询出的数据中
    int updateByExampleSelective(@Param("record") DisciplineCategory record, @Param("example") DisciplineCategoryExample example);

    //更新按条件查询出的数据的全部字段
    int updateByExample(@Param("record") DisciplineCategory record, @Param("example") DisciplineCategoryExample example);

    //按主键更新插入值不为null的字段，即将实体中不为null的属性更新到查询出的数据中
    int updateByPrimaryKeySelective(DisciplineCategory record);

    //更新按主键查询出的数据的全部字段
    int updateByPrimaryKey(DisciplineCategory record);
}