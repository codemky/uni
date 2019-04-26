package edu.uni.userBaseInfo1.mapper;

import edu.uni.userBaseInfo1.bean.AcademicDegree;
import edu.uni.userBaseInfo1.bean.AcademicDegreeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * Author: laizhouhao 19:55 2019/4/26
 * @apiNote: 关于AcademicDegree表的操作的api
 */
public interface AcademicDegreeMapper {
    //按条件计数
    int countByExample(AcademicDegreeExample example);

    //按条件删除
    int deleteByExample(AcademicDegreeExample example);

    //按主键删除
    int deleteByPrimaryKey(Long id);

    //向AcademicDegree表插入数据
    int insert(AcademicDegree record);

    //向AcademicDegree表插入数据不为null的值
    int insertSelective(AcademicDegree record);

    //返回按条件查询出的实体集合
    List<AcademicDegree> selectByExample(AcademicDegreeExample example);

    //按主键查找AcademicDegree实体
    AcademicDegree selectByPrimaryKey(Long id);

    //按条件更新插入值不为null的字段，即将实体中不为null的属性更新到查询出的数据中
    int updateByExampleSelective(@Param("record") AcademicDegree record, @Param("example") AcademicDegreeExample example);

    //更新按条件查询出的数据的全部字段
    int updateByExample(@Param("record") AcademicDegree record, @Param("example") AcademicDegreeExample example);

    //按主键更新插入值不为null的字段，即将实体中不为null的属性更新到查询出的数据中
    int updateByPrimaryKeySelective(AcademicDegree record);

    //更新按主键查询出的数据的全部字段
    int updateByPrimaryKey(AcademicDegree record);
}