package edu.uni.userBaseInfo1.mapper;

import edu.uni.userBaseInfo1.bean.Ecomm;
import edu.uni.userBaseInfo1.bean.EcommExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * Author: laizhouhao
 * @apiNote:关于Ecomm表操作的api
 */
public interface EcommMapper {
    //按条件计数
    int countByExample(EcommExample example);

    //按条件删除
    int deleteByExample(EcommExample example);

    //按主键删除
    int deleteByPrimaryKey(Long id);

    //插入所有值，包括null值
    int insert(Ecomm record);

    //插入不为null的值
    int insertSelective(Ecomm record);

    //按条件查询出多条数据
    List<Ecomm> selectByExample(EcommExample example);

    //按主键查询单条数据
    Ecomm selectByPrimaryKey(Long id);

    //按用户的id查询多条记录
    List<Ecomm> selectByUserId(Long userId);

    //按条件更新插入值不为null的字段，即将实体中不为null的属性更新到查询出的数据中
    int updateByExampleSelective(@Param("record") Ecomm record, @Param("example") EcommExample example);

    //更新按条件查询出的数据的全部字段
    int updateByExample(@Param("record") Ecomm record, @Param("example") EcommExample example);

    //按主键更新插入值不为null的字段，即将实体中不为null的属性更新到查询出的数据中
    int updateByPrimaryKeySelective(Ecomm record);

    //更新按主键查询出的数据的全部字段
    int updateByPrimaryKey(Ecomm record);
}