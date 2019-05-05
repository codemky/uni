package edu.uni.userBaseInfo1.mapper;

import edu.uni.userBaseInfo1.bean.Address;
import edu.uni.userBaseInfo1.bean.AddressExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * Author: laizhouhao
 * @apiNote:关于Address表操作的api
 */
public interface AddressMapper {
    //按条件计数
    int countByExample(AddressExample example);

    //按条件删除
    int deleteByExample(AddressExample example);

    //按主键删除
    int deleteByPrimaryKey(Long id);

    //插入所有值，包括null值
    int insert(Address record);

    //插入不为null的值
    int insertSelective(Address record);

    //按条件查询出多条数据
    List<Address> selectByExample(AddressExample example);

    //按主键查询单条数据
    Address selectByPrimaryKey(Long id);

    //按用户id查询多条记录
    List<Address> selectByUserId(Long userId);

    //按条件更新插入值不为null的字段，即将实体中不为null的属性更新到查询出的数据
    int updateByExampleSelective(@Param("record") Address record, @Param("example") AddressExample example);

    //更新按条件查询出的数据的全部字段
    int updateByExample(@Param("record") Address record, @Param("example") AddressExample example);


    //按主键更新插入值不为null的字段，即将实体中不为null的属性更新到查询出的数据中
    int updateByPrimaryKeySelective(Address record);

    //更新按主键查询出的数据的全部字段
    int updateByPrimaryKey(Address record);
}