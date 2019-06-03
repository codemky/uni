/**
 * @Author laizhouhao
 * @Description //Address实体类的service层接口
 * @Date 15:11 2019/4/29
 **/
package edu.uni.userBaseInfo1.service;
import com.github.pagehelper.PageInfo;
import edu.uni.userBaseInfo1.bean.*;

import java.util.HashMap;
import java.util.List;

public interface AddressService {
    /**
     * Author: laizhouhao 15:17 2019/4/29
     * @return List<Address>
     * @apiNote: 查询所有的地址
     */
    List<Address> selectAll();

    /**
     * Author: laizhouhao 15:39 2019/4/29
     * @param id
     * @return Address
     * @apiNote: 根据id查询地址
     */
    Address selectById(Long id);

    /**
     * Author: chenenru 1:23 2019/5/5
     * @param userId
     * @return  Address
     * @apiNote: 根据用户的id查询地址
     */
    List<Address> selectByUserId(Long userId);

    /**
     * Author: laizhouhao 15:41 2019/4/29
     * @param pageNum
     * @return List<Address>
     * @apiNote: 分页查询地址信息
     */
    PageInfo<Address> selectPage(Integer pageNum);

    /**
     * Author: laizhouhao 15:44 2019/4/29
     * @param Address
     * @return boolean
     * @apiNote: 用于增加Address表的一个记录
     */
    boolean insert(Address Address);

    /**
     * Author: laizhouhao 15:46 2019/4/29
     * @param Address
     * @return boolean
     * @apiNote: 用于更新Address表的一个记录
     */
    boolean update(Address Address);

    /**
     * Author: laizhouhao 15:47 2019/4/29
     * @param id
     * @return boolean
     * @apiNote: 用于根据id删除Address表的一个记录
     */
    boolean delete(Long id);

    /**
     * Author: laizhouhao 18:21 2019/5/7
     * @param addressExample
     * @return List<Address>
     * @apiNote: 根据自定义条件查询Address的一个记录
     */
    List<Address> selectByExample(AddressExample addressExample);

    /**
     * Author: laizhouhao 15:18 2019/5/14
     * @param requestMessage
     * @return boolean
     * @apiNote: 用户点击申请修改地址信息
     */
    boolean clickApplyAddress(RequestMessage requestMessage);

    /**
     * Author: laizhouhao 20:06 2019/5/15
     * @param user_id
     * @return Address
     * @apiNote: 根据用户id获取有效的用户地址信息
     */
    List<Address> selectValidAddressByUserId(Long user_id);

    /**
     * Author: laizhouhao 20:22 2019/5/15
     * @param id
     * @return Address
     * @apiNote: 根据用户id获取有效的地址信息
     */
    Address selectValidAddressById(Long id);

    /**
     * Author: mokuanyuan 20:14 2019/6/2
     * @param map
     * @param address
     * @apiNote: 传入一个HashMap和Address对象，把Address里的id字段对应的信息内容放入到map里
     */
    public void selectAllInfoToMap(HashMap map, Address address);
}
