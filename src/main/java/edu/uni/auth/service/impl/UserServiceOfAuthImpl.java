package edu.uni.auth.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.auth.bean.User;
import edu.uni.auth.mapper.UserMapperOfAuth;
import edu.uni.auth.service.UserServiceOfAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 何亮
 */
@Service
public class UserServiceOfAuthImpl implements UserServiceOfAuth {
    @Autowired
    private UserMapperOfAuth userMapperOfAuth;

    @Override
    public boolean insert(User user) {
        return userMapperOfAuth.insert(user) > 0 ? true : false;
    }

    @Override
    public User selectByName(String name) {
        return userMapperOfAuth.selectByName(name);
    }

    @Override
    public PageInfo selectPageByUniversityIdLikeByNameAndUserName(Long universityId, String name, String userName, Integer pageNum) {
        PageHelper.startPage(pageNum, 10);
        List<User> users = userMapperOfAuth.selectLikeByByUniversityIdNameAndUserNameAndUserType(universityId, "%" + name + "%", "%" + userName + "%");
        return new PageInfo<>(users);
    }

    @Override
    public List<String> selectRoles(Long id) {
        return userMapperOfAuth.selectRoles(id);
    }

    @Override
    public List<String> selectPermissions(Long id) {
        return userMapperOfAuth.selectPermissions(id);
    }

    @Override
    public boolean updatePwdAndSalt(Long id, String pwd, String salt) {
        return userMapperOfAuth.updatePwdAndSalt(id, pwd, salt) > 0 ? true : false;
    }
}
