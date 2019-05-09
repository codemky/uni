package edu.uni.userBaseInfo1.service;

import com.github.pagehelper.PageInfo;
import edu.uni.userBaseInfo1.bean.User;
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
}
