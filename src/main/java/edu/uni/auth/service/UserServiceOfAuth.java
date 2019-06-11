package edu.uni.auth.service;

import com.github.pagehelper.PageInfo;
import edu.uni.auth.bean.User;

import java.util.List;
/**
 * @Author 何亮
 */
public interface UserServiceOfAuth {
    boolean insert(User user); //创建用户
    User selectByName(String name);// 根据用户名查找用户
    PageInfo selectPageByUniversityIdLikeByNameAndUserName(Long universityId, String name, String userName, Integer pageNum);

    List<String> selectRoles(Long id);

    List<String> selectPermissions(Long id);

    boolean updatePwdAndSalt(Long id, String pwd, String salt);
}
