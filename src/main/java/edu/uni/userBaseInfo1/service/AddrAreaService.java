package edu.uni.userBaseInfo1.service;

import com.github.pagehelper.PageInfo;
import edu.uni.userBaseInfo1.bean.AddrArea;

import java.util.List;

public interface AddrAreaService {
    /**
     * Author: chenenru 11:22 2019/5/1
     * @return List<AddrArea>
     * @apiNote: 查询所有的城区方式
     */
    List<AddrArea> selectAllAddrAreas();
    /**
     * Author: chenenru 11:22 2019/5/1
     * @param id
     * @return AddrArea
     * @apiNote: 根据id查询城区记录
     */
    AddrArea selectAddrAreaById(long id);
    /**
     * Author: chenenru 11:22 2019/5/1
     * @param pageNum
     * @return List<Ecomm>
     * @apiNote: 分页查询城区记录
     */
    PageInfo<AddrArea> selectAddrAreaByPage(int pageNum);
    /**
     * Author: chenenru 11:22 2019/5/1
     * @param addrArea
     * @return boolean
     * @apiNote : 用于增加AddrArea表的一个记录
     */
    boolean insertAddrArea(AddrArea addrArea);
    /**
     * Author: chenenru 11:22 2019/5/1
     * @param addrArea
     * @return boolean
     * @apiNote : 用于更新AddrArea表的某个记录（传一个新的对象）
     */
    boolean updateAddrArea(AddrArea addrArea);
    /**
     * Author: chenenru 11:22 2019/5/1
     * @param id
     * @return boolean
     * @apiNote : 用于删除AddrArea表的某个记录
     */
    boolean deleteAddrArea(long id);

    List<AddrArea> selectAllAddrAreasByCityCode(Long cityCode);
}
