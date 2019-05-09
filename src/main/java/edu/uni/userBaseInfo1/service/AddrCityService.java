package edu.uni.userBaseInfo1.service;

import com.github.pagehelper.PageInfo;
import edu.uni.userBaseInfo1.bean.AddrCity;

import java.util.List;

public interface AddrCityService {
    /**
     * Author: chenenru 11:22 2019/5/1
     * @return List<AddrCity>
     * @apiNote: 查询所有的城市方式
     */
    List<AddrCity> selectAllAddrCitys();
    /**
     * Author: chenenru 11:22 2019/5/1
     * @param id
     * @return AddrCity
     * @apiNote: 根据id查询城市记录
     */
    AddrCity selectAddrCityById(long id);
    /**
     * Author: chenenru 11:22 2019/5/1
     * @param pageNum
     * @return List<Ecomm>
     * @apiNote: 分页查询城市记录
     */
    PageInfo<AddrCity> selectAddrCityByPage(int pageNum);
    /**
     * Author: chenenru 11:22 2019/5/1
     * @param AddrCity
     * @return boolean
     * @apiNote : 用于增加AddrCity表的一个记录
     */
    boolean insertAddrCity(AddrCity AddrCity);
    /**
     * Author: chenenru 11:22 2019/5/1
     * @param AddrCity
     * @return boolean
     * @apiNote : 用于更新AddrCity表的某个记录（传一个新的对象）
     */
    boolean updateAddrCity(AddrCity AddrCity);
    /**
     * Author: chenenru 11:22 2019/5/1
     * @param id
     * @return boolean
     * @apiNote : 用于删除AddrCity表的某个记录
     */
    boolean deleteAddrCity(long id);

    /**
     * Author: chenenru 19:29 2019/5/8
     * @param stateCode
     * @return List<AddrCity>
     * @apiNote: 根据省份的编码查询对应的所有的城市
     */
    List<AddrCity> selectAllAddrCitysByStateCode(Long stateCode);
}
