package edu.uni.userBaseInfo1.service;

import com.github.pagehelper.PageInfo;
import edu.uni.userBaseInfo1.bean.User;
import edu.uni.userBaseInfo1.bean.UserExample;
import edu.uni.userBaseInfo1.bean.UserinfoApplyApproval;
import edu.uni.userBaseInfo1.utils.UserInfo;

import java.util.List;

public interface UserService {
    /**
     * Author: chenenru 23:15 2019/4/29
     * @param
     * @return List<User>
     * @apiNote: 查询所有的用户
     */
    List<User> selectAllUsers();
    /**
     * Author: chenenru 23:16 2019/4/29
     * @param id
     * @return User
     * @apiNote: 根据id查询用户
     */
    User selectUserById(long id);
    /**
     * Author: chenenru 23:18 2019/4/29
     * @param pageNum
     * @return List<User>
     * @apiNote: 分页查询用户
     */
    PageInfo<User> selectUserByPage(int pageNum);
    /**
     * Author: chenenru 23:20 2019/4/29
     * @param user
     * @return boolean
     * @apiNote: 用户增加User表的一个记录
     */
    boolean insertUser(User user);
    /**
     * Author: chenenru 23:21 2019/4/29
     * @param user
     * @return boolean
     * @apiNote: 用户更新一个User表的某个记录（传一个新的对象）
     */
    boolean updateUser(User user);
    /**
     * Author: chenenru 23:22 2019/4/29
     * @param id
     * @return boolean
     * @apiNote: 用于删除User表的某个记录
     */
    boolean deleteUser(long id);

    /**
     * Author: laizhouhao 14:47 2019/5/9
     * @param user_id
     * @return UserInfo
     * @apiNote: 根据用户id查找用户的照片、详细地址
     */
    UserInfo selectPictureAddrByUserId(Long user_id);

    /**
     * Author: laizhouhao 19:15 2019/5/9
     * @param record,example
     * @return int
     * @apiNote: 根据条件修改
     */
    int updateByExample(User record, UserExample example);

    /**
     * Author: laizhouhao 16:14 2019/5/11
     * @param now_step, userinfo_apply_id
     * @return boolean
     * @apiNote: 判断该步骤是否是最后一步
     */
    boolean isLastStep(int now_step,Long userinfo_apply_id );

    /**
     * Author: laizhouhao 16:29 2019/5/11
     * @param userinfoApplyApproval, user_id
     * @return boolean
     * @apiNote: 通过申请并且该步骤是最后一步
     */
    boolean endForPass(UserinfoApplyApproval userinfoApplyApproval, Long user_id);

    /**
     * Author: laizhouhao 16:50 2019/5/11
     * @param userinfoApplyApproval, user_id
     * @return boolean
     * @apiNote: 通过申请并且该步骤不是最后一步
     */
    boolean createForPass(UserinfoApplyApproval userinfoApplyApproval, Long user_id);

    /**
     * Author: laizhouhao 20:57 2019/5/11
     * @param userinfoApplyApproval, user_id
     * @return boolean
     * @apiNote: 不通过申请
     */
    boolean endForRefuse(UserinfoApplyApproval userinfoApplyApproval, Long user_id);

    /**
     * Author: laizhouhao 16:03 2019/5/14
     * @param userinfoApplyApproval
     * @return boolean
     * @apiNote: 申请修改的信息通过后将新信息置为有效，旧信息置为无效
     */
    boolean updateNewAndOldMessage(UserinfoApplyApproval userinfoApplyApproval);

    /**
     * Author: laizhouhao 18:06 2019/5/14
     * @param identification
     * @return  所搜索的游客信息
     * @apiNote: 根据用户身份证查询游客信息，身份证为空时默认查询所有游客信息
     */
    List<User> selectTouristByIdentification(String identification);

    /**
     * Author: laizhouhao 19:24 2019/5/15
     * @param user_id
     * @return UserInfo
     * @apiNote: 根据用户id获取该用户的所有信息
     */
    UserInfo selectUserInfoAllByUserId(Long user_id);

    /**
     * Author: laizhouhao 15:47 2019/5/18
     * @param user_name
     * @return Long
     * @apiNote: 根据用户名获取有效的用户id
     */
    List<User> selectIdByUserName(String user_name);
}
