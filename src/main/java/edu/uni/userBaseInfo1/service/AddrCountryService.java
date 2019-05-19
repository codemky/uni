package edu.uni.userBaseInfo1.service;

import com.github.pagehelper.PageInfo;
import edu.uni.userBaseInfo1.bean.AddrCountry;

import java.util.List;

public interface AddrCountryService {
    /**
     * Author: chenenru 11:22 2019/5/1
     * @return List<AddrCountry>
     * @apiNote: 查询所有的国家方式
     */
    List<AddrCountry> selectAllAddrCountrys();
    /**
     * Author: chenenru 11:22 2019/5/1
     * @param id
     * @return AddrCountry
     * @apiNote: 根据id查询国家记录
     */
    AddrCountry selectAddrCountryById(long id);
    /**
     * Author: chenenru 11:22 2019/5/1
     * @param pageNum
     * @return List<Ecomm>
     * @apiNote: 分页查询国家记录
     */
    PageInfo<AddrCountry> selectAddrCountryByPage(int pageNum);
    /**
     * Author: chenenru 11:22 2019/5/1
     * @param AddrCountry
     * @return boolean
     * @apiNote : 用于增加AddrCountry表的一个记录
     */
    boolean insertAddrCountry(AddrCountry AddrCountry);
    /**
     * Author: chenenru 11:22 2019/5/1
     * @param AddrCountry
     * @return boolean
     * @apiNote : 用于更新AddrCountry表的某个记录（传一个新的对象）
     */
    boolean updateAddrCountry(AddrCountry AddrCountry);
    /**
     * Author: chenenru 11:22 2019/5/1
     * @param id
     * @return boolean
     * @apiNote : 用于删除AddrCountry表的某个记录
     */
    boolean deleteAddrCountry(long id);
    /**
     * Author: chenenru 18:33 2019/5/17
     * @param countryName
     * @return AddrCountry
     * @apiNote: 根据国家名称查询国家
     */
    List<AddrCountry> selectCountryByName(String countryName);
}
