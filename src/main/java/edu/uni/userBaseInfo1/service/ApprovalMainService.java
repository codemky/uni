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
     * @apiNote: 查询所有的审批规定
     */
    List<ApprovalMain> selectAll();

    /**
     * Author: laizhouhao 15:39 2019/4/29
     * @param id
     * @return ApprovalMain
     * @apiNote: 根据id查询审批规定
     */
    ApprovalMain selectById(Long id);

    /**
     * Author: laizhouhao 15:41 2019/4/29
     * @param pageNum
     * @return List<ApprovalMain>
     * @apiNote: 分页查询审批规定信息
     */
    PageInfo<ApprovalMain> selectPage(Integer pageNum);

    /**
     * Author: laizhouhao 15:44 2019/4/29
     * @param approvalMain
     * @return Integer
     * @apiNote: 用于增加ApprovalMain表的一个记录
     */
    Integer insert(ApprovalMain approvalMain);

    /**
     * Author: laizhouhao 15:46 2019/4/29
     * @param approvalMain
     * @return boolean
     * @apiNote: 用于更新ApprovalMain表的一个记录
     */
    boolean update(ApprovalMain approvalMain);

    /**
     * Author: mokuanyuan 20:12 2019/5/10
     * @param approvalMain
     * @return boolean
     * @apiNote: 更新审批规定记录（因为对审批步骤详情表进行了某些操作而导致的更新）
     */
    boolean updateForStepIncharge(ApprovalMain approvalMain);

    /**
     * Author: laizhouhao 15:47 2019/4/29
     * @param id
     * @return boolean
     * @apiNote: 用于根据id删除ApprovalMain表的一个记录
     */
    boolean delete(Long id);

    /**
     * Author: mokuanyuan 20:23 2019/5/7
     * @param schoolId
     * @param name
     * @return id
     * @apiNote: 根据审批业务名称查询审批业务记录id
     */
    long selectIdByName(Long schoolId, String name);

    /**
     * Author: mokuanyuan 21:34 2019/5/7
     * @param id
     * @return 总步数
     * @apiNote: 通过审批规定表记录id获取该审批申请的总步数（step_cnt字段值）
     */
    Integer selectStepCntById(Long id);

    /**
     * Author: mokuanyuan 15:08 2019/5/8
     * @param schoolId
     * @param name
     * @return boolean
     * @apiNote: 检验某个学校是否已经存在同一个审批业务（同一个学校并且同一个业务名）
     */
    boolean isAlreadyExist(Long schoolId, String name);

    /**
     * Author: mokuanyuan 16:36 2019/5/8
     * @param id
     * @return boolean
     * @apiNote: 把某个审批业务规定逻辑删除，并且把与之相关的每一步规定的记录（approval_step_incharge表）也逻辑删除
     */
    boolean updateToInvalidById(Long id);

    /**
     * Author: mokuanyuan 18:50 2019/5/8
     * @param schoolId
     * @return List<ApprovalMain>
     * @apiNote: 根据学校id查询所有的审批业务规定
     */
    List<ApprovalMain> selectBySchoolId(Long schoolId);

    /**
     * Author: mokuanyuan 18:52 2019/5/8
     * @param schoolId
     * @param name
     * @param type
     * @return List<ApprovalMain>
     * @apiNote: 根据学校id、业务名称，业务类型查询所有的审批业务规定（其中业务名称是模糊搜索）
     */
    List<ApprovalMain> selectBySchoolIdAndNameAndType(Long schoolId,String name,String type);


}
