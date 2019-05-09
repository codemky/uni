/**
 * @Author Mr.k
 * @Description // Ecomm实体类的service层接口
 * @Date 17:16 2019/4/24
 **/

package edu.uni.userBaseInfo1.service;

import com.github.pagehelper.PageInfo;
import edu.uni.userBaseInfo1.bean.Ecomm;

import java.util.List;

public interface EcommService {

    /**
     * Author: mokuanyuan 10:13 2019/4/26
     * @return List<Ecomm>
     * @apiNote: 查询所有的电子通讯方式
     */
    List<Ecomm> selectAll();

    /**
     * Author: mokuanyuan 10:15 2019/4/26
     * @param id
     * @return Ecomm
     * @apiNote: 根据id查询电子通信记录
     */
    Ecomm selectById(long id);

    /**
     * Author: chenenru 0:42 2019/5/5
     * @param  userId
     * @return  List<Ecomm>
     * @apiNote: 根据用户的id查询电子通信记录
     */
    List<Ecomm> selectByUserId(long userId);

    /**
     * Author: mokuanyuan 10:18 2019/4/26
     * @param pageNum
     * @return List<Ecomm>
     * @apiNote: 分页查询电子通信记录
     */
    PageInfo<Ecomm> selectPage(int pageNum);

    /**
     * Author: mokuanyuan 21:08 2019/4/24
     * @param ecomm
     * @return boolean
     * @apiNote : 用于增加Ecomm表的一个记录
     */
    boolean insert(Ecomm ecomm);

    /**
     * Author: mokuanyuan 21:08 2019/4/24
     * @param ecomm
     * @return boolean
     * @apiNote : 用于更新Ecomm表的某个记录（传一个新的对象）
     */
    boolean update(Ecomm ecomm);

    /**
     * Author: mokuanyuan 21:08 2019/4/24
     * @param id
     * @return boolean
     * @apiNote : 用于删除Ecomm表的某个记录
     */
    boolean delete(long id);

    /**
     * Author: laizhouhao 15:41 2019/5/9
     * @param user_id
     * @return List<Ecomm>
     * @apiNote: 根据用户id返回用户的有效的通信信息
     */
    List<Ecomm> selectValidEcomByUserId(Long user_id);


}
