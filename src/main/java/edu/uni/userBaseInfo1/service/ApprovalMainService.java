/**
 * @Author laizhouhao
 * @Description //ApprovalMain实体类的service层接口
 * @Date 15:11 2019/4/29
 **/
package edu.uni.userBaseInfo1.service;
import com.github.pagehelper.PageInfo;
import edu.uni.userBaseInfo1.bean.ApprovalMain;

import java.util.List;

public interface ApprovalMainService {
    /**
     * Author: laizhouhao 15:17 2019/4/29
     * @return List<ApprovalMain>
     * @apiNote: 查询所有的每一种申请的审批步骤数
     */
    List<ApprovalMain> selectAll();

    /**
     * Author: laizhouhao 15:39 2019/4/29
     * @param id
     * @return ApprovalMain
     * @apiNote: 根据id查询每一种申请的审批步骤数
     */
    ApprovalMain selectById(Long id);

    /**
     * Author: laizhouhao 15:41 2019/4/29
     * @param pageNum
     * @return List<ApprovalMain>
     * @apiNote: 分页查询每一种申请的审批步骤数信息
     */
    PageInfo<ApprovalMain> selectPage(Integer pageNum);

    /**
     * Author: laizhouhao 15:44 2019/4/29
     * @param approvalMain
     * @return boolean
     * @apiNote: 用于增加ApprovalMain表的一个记录
     */
    boolean insert(ApprovalMain approvalMain);

    /**
     * Author: laizhouhao 15:46 2019/4/29
     * @param approvalMain
     * @return boolean
     * @apiNote: 用于更新ApprovalMain表的一个记录
     */
    boolean update(ApprovalMain approvalMain);

    /**
     * Author: laizhouhao 15:47 2019/4/29
     * @param id
     * @return boolean
     * @apiNote: 用于根据id删除ApprovalMain表的一个记录
     */
    boolean delete(Long id);
}
