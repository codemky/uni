package edu.uni.userBaseInfo1.service;

import com.github.pagehelper.PageInfo;
import edu.uni.userBaseInfo1.bean.UserinfoApply;
import edu.uni.userBaseInfo1.bean.UserinfoApplyApproval;

import java.util.List;

public interface UserinfoApplyApprovalService {


    /**
     * Author: mokuanyuan 21:03 2019/6/10
     * @apiNote: 创建审批流程记录（由于发出申请时产生的第一条记录）
     */
    public boolean createForApply(UserinfoApplyApproval applyApproval,UserinfoApply userinfoApply);


    /**
     * Author: mokuanyuan 16:54 2019/5/11
     * @param userinfoApplyApproval 申请审批的实体类
     * @param roles 该用户所有扮演的角色
     * @return List<UserinfoApplyApproval>
     * @apiNote: 根据审批结果、审批类型、角色名搜素审批表
     */
    List<UserinfoApplyApproval> selectAllByApprovalAndRole(
            UserinfoApplyApproval userinfoApplyApproval , List<String> roles);

    /**
     * Author: chenenru 23:59 2019/4/29
     * @param
     * @return List<UserinfoApplyApproval>
     * @apiNote: 查询所有的用户信息审批流程
     */
    List<UserinfoApplyApproval> selectAllUserinfoApplyApprovals();

    /**
     * Author: mokuanyuan 19:55 2019/5/16
     * @param applyId
     * @return List<UserinfoApplyApproval>
     * @apiNote: 根据申请表id查询所有的审批流程记录
     */
    public List<UserinfoApplyApproval> selectByApplyId(Long applyId);

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
