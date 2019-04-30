package edu.uni.userBaseInfo1.service;

import com.github.pagehelper.PageInfo;
import edu.uni.userBaseInfo1.bean.UserinfoApplyApproval;

import java.util.List;

public interface UserinfoApplyApprovalService {
    /**
     * Author: chenenru 23:59 2019/4/29
     * @param
     * @return List<UserinfoApplyApproval>
     * @apiNote: 查询所有的用户信息审批流程
     */
    List<UserinfoApplyApproval> selectAllUserinfoApplyApprovals();
    /**
     * Author: chenenru 0:00 2019/4/30
     * @param id
     * @return UserinfoApplyApproval
     * @apiNote: 根据id查询用户信息审批流程
     */
    UserinfoApplyApproval selectUserinfoApplyApprovalById(long id);
    /**
     * Author: chenenru 0:00 2019/4/30
     * @param pageNum
     * @return PageInfo<UserinfoApplyApproval>
     * @apiNote: 分页查询用户信息审批流程
     */
    PageInfo<UserinfoApplyApproval> selectUserinfoApplyApprovalByPage(int pageNum);
    /**
     * Author: chenenru 0:00 2019/4/30
     * @param UserinfoApplyApproval
     * @return boolean
     * @apiNote: 用户信息审批流程添加UserinfoApplyApproval表的一条记录
     */
    boolean insertUserinfoApplyApproval(UserinfoApplyApproval UserinfoApplyApproval);
    /**
     * Author: chenenru 0:00 2019/4/30
     * @param UserinfoApplyApproval
     * @return boolean
     * @apiNote:  用户更新一个UserinfoApplyApproval表的某个记录（传一个新的对象）
     */
    boolean updateUserinfoApplyApproval(UserinfoApplyApproval UserinfoApplyApproval);
    /**
     * Author: chenenru 0:00 2019/4/30
     * @param id
     * @return boolean
     * @apiNote:  用于删除UserinfoApplyApproval表的某个记录
     */
    boolean deleteUserinfoApplyApproval(long id);
}
