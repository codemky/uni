/**
 * @Author laizhouhao
 * @Description //approvalStepIncharge实体类的service层接口
 * @Date 15:11 2019/4/29
 **/
package edu.uni.userBaseInfo1.service;
import com.github.pagehelper.PageInfo;
import edu.uni.userBaseInfo1.bean.ApprovalStepIncharge;

import java.util.List;

public interface ApprovalStepInchargeService {
    /**
     * Author: laizhouhao 15:17 2019/4/29
     * @return List<ApprovalStepIncharge>
     * @apiNote: 查询所有的每一种申请的每一个审批步骤的角色
     */
    List<ApprovalStepIncharge> selectAll();

    /**
     * Author: laizhouhao 15:39 2019/4/29
     * @param id
     * @return ApprovalStepIncharge
     * @apiNote: 根据id查询每一种申请的每一个审批步骤的角色
     */
    ApprovalStepIncharge selectById(Long id);

    /**
     * Author: laizhouhao 15:41 2019/4/29
     * @param pageNum
     * @return List<ApprovalStepIncharge>
     * @apiNote: 分页查询每一种申请的每一个审批步骤的角色信息
     */
    PageInfo<ApprovalStepIncharge> selectPage(Integer pageNum);

    /**
     * Author: laizhouhao 15:44 2019/4/29
     * @param approvalStepIncharge
     * @return boolean
     * @apiNote: 用于增加ApprovalStepIncharge表的一个记录
     */
    boolean insert(ApprovalStepIncharge approvalStepIncharge);

    /**
     * Author: laizhouhao 15:46 2019/4/29
     * @param approvalStepIncharge
     * @return boolean
     * @apiNote: 用于更新ApprovalStepIncharge表的一个记录
     */
    boolean update(ApprovalStepIncharge approvalStepIncharge);

    /**
     * Author: laizhouhao 15:47 2019/4/29
     * @param id
     * @return boolean
     * @apiNote: 用于根据id删除ApprovalStepIncharge表的一个记录
     */
    boolean delete(Long id);
}
