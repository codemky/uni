/**
 * @Author laizhouhao
 * @Description //EmployeeHistory实体类的service层接口
 * @Date 15:11 2019/4/29
 **/
package edu.uni.userBaseInfo1.service;
import com.github.pagehelper.PageInfo;
import edu.uni.userBaseInfo1.bean.EmployeeHistory;
import edu.uni.userBaseInfo1.bean.RequestMessage;

import java.util.List;

public interface EmployeeHistoryService {
    /**
     * Author: laizhouhao 15:17 2019/4/29
     * @return List<EmployeeHistory>
     * @apiNote: 查询所有的简历信息
     */
    List<EmployeeHistory> selectAll();

    /**
     * Author: laizhouhao 15:39 2019/4/29
     * @param id
     * @return EmployeeHistory
     * @apiNote: 根据id查询简历信息
     */
    EmployeeHistory selectById(Long id);

    /**
     * Author: laizhouhao 15:41 2019/4/29
     * @param pageNum
     * @return List<EmployeeHistory>
     * @apiNote: 分页查询简历信息
     */
    PageInfo<EmployeeHistory> selectPage(Integer pageNum);

    /**
     * Author: laizhouhao 9:21 2019/5/6
     * @param user_id
     * @return List<EmployeeHistory>
     * @apiNote: 根据user_id查询简历信息
     */
    List<EmployeeHistory>selectByUserId(Long user_id);

    /**
     * Author: laizhouhao 15:44 2019/4/29e
     * @param employeeHistory
     * @return boolean
     * @apiNote: 用于增加EmployeeHistory表的一个记录
     */
    boolean insert(EmployeeHistory employeeHistory);

    /**
     * Author: laizhouhao 15:46 2019/4/29
     * @param employeeHistory
     * @return boolean
     * @apiNote: 用于更新EmployeeHistory表的一个记录
     */
    boolean update(EmployeeHistory employeeHistory);

    /**
     * Author: laizhouhao 15:47 2019/4/29
     * @param id
     * @return boolean
     * @apiNote: 用于根据id删除EmployeeHistory表的一个记录
     */
    boolean delete(Long id);

    /**
     * Author: laizhouhao 18:26 2019/5/10
     * @param user_id
     * @return List<EmployeeHistory>
     * @apiNote: 根据用户id查询有效的雇佣历史信息
     */
    List<EmployeeHistory> seleValidEmpHisByUserId(Long user_id);
    /**
     * Author: chenenru 20:05 2019/5/13
     * @param
     * @return
     * @apiNote: 用户点击申请修改简历
     */
    boolean clickApplyEmployeeHistory(RequestMessage requestMessage);
}
