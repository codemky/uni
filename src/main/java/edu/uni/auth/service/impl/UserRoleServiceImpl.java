package edu.uni.auth.service.impl;

import edu.uni.auth.bean.UserRole;
import edu.uni.auth.bean.UserRoleExample;
import edu.uni.auth.mapper.UserRoleMapper;
import edu.uni.auth.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author 何亮
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleMapper userRoleMapper;
    /**
     * 为用户新增角色
     * @param userRole
     * @return
     */
    @Override
    public boolean insert(UserRole userRole) {
        return userRoleMapper.insert(userRole) > 0 ? true: false;
    }

    /**
     * 删除用户角色
     * @param id
     * @return
     */
    @Override
    public boolean deleteByIdAndUniversityId(long id, long universityId){
        UserRoleExample userRoleExample = new UserRoleExample();
        UserRoleExample.Criteria criteria = userRoleExample.createCriteria();
        criteria.andIdEqualTo(id);
        criteria.andUniversityIdEqualTo(universityId);
        return userRoleMapper.deleteByExample(userRoleExample) > 0 ? true : false;
    }

    /**
     * 更新用户角色状态
     * @param id
     * @param universityId
     * @param status
     * @return
     */
    @Override
    public boolean updateStatusByIdAndUniversityId(long id, long universityId, int status) {
        UserRole userRole = new UserRole();
        userRole.setStatus(status);

        UserRoleExample userRoleExample = new UserRoleExample();
        UserRoleExample.Criteria criteria = userRoleExample.createCriteria();
        criteria.andIdEqualTo(id);
        criteria.andUniversityIdEqualTo(universityId);

        return userRoleMapper.updateByExampleSelective(userRole, userRoleExample) > 0 ? true : false;
    }

    /**
     * 删除某用户与角色的关联
     * @param userId
     * @param roleId
     * @param universityId
     */
    @Override
    public boolean deleteByUserIdAndRoleIdAndUniversityId(Long userId, Long roleId, Long universityId) {
        UserRoleExample example = new UserRoleExample();
        UserRoleExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andRoleIdEqualTo(roleId);
        criteria.andUniversityIdEqualTo(universityId);

        return userRoleMapper.deleteByExample(example) > 0 ? true : false;
    }


}
