package edu.uni.userBaseInfo1.service;

import com.github.pagehelper.PageInfo;
import edu.uni.userBaseInfo1.bean.UserinfoApply;

import java.util.List;

public interface UserinfoApplyService {
    /**
     * Author: chenenru 23:59 2019/4/29
     * @param
     * @return List<UserinfoApply>
     * @apiNote: 查询所有的用户信息申请
     */
    List<UserinfoApply> selectAllUserinfoApplys();
    /**
     * Author: chenenru 0:00 2019/4/30
     * @param id
     * @return UserinfoApply
     * @apiNote: 根据id查询用户信息申请
     */
    UserinfoApply selectUserinfoApplyById(long id);
    /**
     * Author: chenenru 0:00 2019/4/30
     * @param pageNum
     * @return PageInfo<UserinfoApply>
     * @apiNote: 分页查询用户信息申请
     */
    PageInfo<UserinfoApply> selectUserinfoApplyByPage(int pageNum);
    /**
     * Author: chenenru 0:00 2019/4/30
     * @param UserinfoApply
     * @return boolean
     * @apiNote: 用户信息申请添加UserinfoApply表的一条记录
     */
    boolean insertUserinfoApply(UserinfoApply UserinfoApply);
    /**
     * Author: chenenru 0:00 2019/4/30
     * @param UserinfoApply
     * @return boolean
     * @apiNote:  用户更新一个UserinfoApply表的某个记录（传一个新的对象）
     */
    boolean updateUserinfoApply(UserinfoApply UserinfoApply);
    /**
     * Author: chenenru 0:00 2019/4/30
     * @param id
     * @return boolean
     * @apiNote:  用于删除UserinfoApply表的某个记录
     */
    boolean deleteUserinfoApply(long id);
}
