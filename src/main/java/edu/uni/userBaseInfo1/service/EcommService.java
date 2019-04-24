/**
 * @Author Mr.k
 * @Description // Ecomm实体类的service层接口
 * @Date 17:16 2019/4/24
 **/

package edu.uni.userBaseInfo1.service;

import edu.uni.userBaseInfo1.bean.Ecomm;

import java.util.List;

public interface EcommService {

    /**
     * @Author Mr.k
     * @Description //搜索所有的Ecomm记录
     * @Date 17:31 2019/4/24
     * @Param null
     * @return 所有的Ecomm记录，List类型
     **/
    List<Ecomm> selectAll();

    /**
     * @Author Mr.k
     * @Description //以id查询单个Ecomm记录
     * @Date 17:44 2019/4/24
     * @Param id
     * @return Ecomm对象
     **/
    Ecomm selectById(long id);

    /**
     * @Author Mr.k
     * @Description //在Ecomm表里插入一条数据
     * @Date 17:33 2019/4/24
     * @Param Ecomm对象
     * @return boolean
     **/
    boolean insert(Ecomm ecomm);

    /**
     * @Author Mr.k
     * @Description //更新Ecomm表里更新一条数据
     * @Date 17:36 2019/4/24
     * @Param Ecomm对象
     * @return boolean
     **/
    boolean update(Ecomm ecomm);

    /**
     * Author: mokuanyuan 21:08 2019/4/24
     * @param id
     * @return boolean
     * @apiNote : 用于删除Ecomm表的某个记录
     */
    boolean delete(long id);






}
