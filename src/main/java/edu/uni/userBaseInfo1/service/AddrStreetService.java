package edu.uni.userBaseInfo1.service;

import com.github.pagehelper.PageInfo;
import edu.uni.userBaseInfo1.bean.AddrStreet;

import java.util.List;

public interface AddrStreetService {
    /**
     * Author: chenenru 11:22 2019/5/1
     * @return List<AddrStreet>
     * @apiNote: 查询所有的街道方式
     */
    List<AddrStreet> selectAllAddrStreets();
    /**
     * Author: chenenru 11:22 2019/5/1
     * @param id
     * @return AddrStreet
     * @apiNote: 根据id查询街道记录
     */
    AddrStreet selectAddrStreetById(long id);
    /**
     * Author: chenenru 11:22 2019/5/1
     * @param pageNum
     * @return List<Ecomm>
     * @apiNote: 分页查询街道记录
     */
    PageInfo<AddrStreet> selectAddrStreetByPage(int pageNum);
    /**
     * Author: chenenru 11:22 2019/5/1
     * @param AddrStreet
     * @return boolean
     * @apiNote : 用于增加AddrStreet表的一个记录
     */
    boolean insertAddrStreet(AddrStreet AddrStreet);
    /**
     * Author: chenenru 11:22 2019/5/1
     * @param AddrStreet
     * @return boolean
     * @apiNote : 用于更新AddrStreet表的某个记录（传一个新的对象）
     */
    boolean updateAddrStreet(AddrStreet AddrStreet);
    /**
     * Author: chenenru 11:22 2019/5/1
     * @param id
     * @return boolean
     * @apiNote : 用于删除AddrStreet表的某个记录
     */
    boolean deleteAddrStreet(long id);

    /**
     * Author: chenenru 19:48 2019/5/8
     * @param AreaCode
     * @return List<AddrStreet>
     * @apiNote: 根据县/区的编码查询所有的街道
     */
    List<AddrStreet> selectStreetsByAreaCode(Long AreaCode);
}
