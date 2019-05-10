package edu.uni.userBaseInfo1.service;

import com.github.pagehelper.PageInfo;
import edu.uni.userBaseInfo1.bean.AddrState;

import java.util.List;

public interface AddrStateService {
    /**
     * Author: chenenru 11:22 2019/5/1
     * @return List<AddrState>
     * @apiNote: 查询所有的省份方式
     */
    List<AddrState> selectAllAddrStates();
    /**
     * Author: chenenru 11:22 2019/5/1
     * @param id
     * @return AddrState
     * @apiNote: 根据id查询省份记录
     */
    AddrState selectAddrStateById(long id);
    /**
     * Author: chenenru 11:22 2019/5/1
     * @param pageNum
     * @return List<Ecomm>
     * @apiNote: 分页查询省份记录
     */
    PageInfo<AddrState> selectAddrStateByPage(int pageNum);
    /**
     * Author: chenenru 11:22 2019/5/1
     * @param AddrState
     * @return boolean
     * @apiNote : 用于增加AddrState表的一个记录
     */
    boolean insertAddrState(AddrState AddrState);
    /**
     * Author: chenenru 11:22 2019/5/1
     * @param AddrState
     * @return boolean
     * @apiNote : 用于更新AddrState表的某个记录（传一个新的对象）
     */
    boolean updateAddrState(AddrState AddrState);
    /**
     * Author: chenenru 11:22 2019/5/1
     * @param id
     * @return boolean
     * @apiNote : 用于删除AddrState表的某个记录
     */
    boolean deleteAddrState(long id);
    /**
     * Author: chenenru 19:19 2019/5/8
     * @param countryCode
     * @return List<AddrState>
     * @apiNote: 根据国家的编码查询该国家的所有省份
     */
    List<AddrState> selectAllAddrStatesByCountryCode(Long countryCode);
}
