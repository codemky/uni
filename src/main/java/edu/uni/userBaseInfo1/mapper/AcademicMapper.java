package edu.uni.userBaseInfo1.mapper;

import edu.uni.userBaseInfo1.bean.Academic;
import edu.uni.userBaseInfo1.bean.AcademicExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * Author: laizhouhao
 * @apiNote:关于Academic表操作的api
 */
public interface AcademicMapper {
    //按条件计数
    int countByExample(AcademicExample example);

    //按条件删除
    int deleteByExample(AcademicExample example);

    //按主键删除
    int deleteByPrimaryKey(Long id);

    //插入所有值，包括null值
    int insert(Academic record);

    //插入不为null的值
    int insertSelective(Academic record);

    //按条件查询出多条数据
    List<Academic> selectByExample(AcademicExample example);

    //按主键查询单条数据
    Academic selectByPrimaryKey(Long id);

    //按条件更新插入值不为null的字段，即将实体中不为null的属性更新到查询出的数据中
    int updateByExampleSelective(@Param("record") Academic record, @Param("example") AcademicExample example);

    //更新按条件查询出的数据的全部字段
    int updateByExample(@Param("record") Academic record, @Param("example") AcademicExample example);

    //按主键更新插入值不为null的字段，即将实体中不为null的属性更新到查询出的数据中
    int updateByPrimaryKeySelective(Academic record);

    //更新按主键查询出的数据的全部字段
    int updateByPrimaryKey(Academic record);
}