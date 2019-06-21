package edu.uni.userBaseInfo1.service.impl;

import edu.uni.auth.bean.Role;
import edu.uni.auth.bean.RoleExample;
import edu.uni.auth.bean.UserRoleExample;
import edu.uni.auth.mapper.RoleMapper;
import edu.uni.auth.mapper.UserRoleMapper;
import edu.uni.auth.service.RoleService;
import edu.uni.auth.service.UserRoleService;
import edu.uni.userBaseInfo1.bean.User;
import edu.uni.userBaseInfo1.service.OtherRoleService;
import edu.uni.userBaseInfo1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author laizhouhao
 * @Description Role实体类的Service层实现类
 * @Date 13:54 2019/5/11
 **/
@Service
public class OtherRoleServiceImpl implements OtherRoleService {
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    UserRoleMapper userRoleMapper;
    @Autowired
    RoleService roleService;
    @Autowired
    UserService userService;
    /**
     * Author: laizhouhao 13:53 2019/5/11
     * @param id
     * @return Role
     * @apiNote: 根据id获取角色详情
     */
    @Override
    public Role selectById(Long id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    /**
     * Author: mokuanyuan 11:45 2019/6/4
     * @return List<Role>
     * @apiNote: 获取所有角色详情
     */
    @Override
    public List<Role> selectAll() {
        RoleExample roleExample = new RoleExample();
        RoleExample.Criteria criteria = roleExample.createCriteria();
        criteria.andStatusEqualTo(0);
        return roleMapper.selectByExample(roleExample);
    }

    /**
     * Author: mokuanyuan 15:30 2019/6/13
     * @param userId
     * @param roleName
     * @return boolean
     * @apiNote: 根据用户id和角色名判断这个用户是否有扮演这个角色
     */
    @Override
    public boolean isPlayOneRole(Long userId, String roleName) {
        User user = userService.selectUserById(userId);
        if( user == null )
            return false;
        List<Role> roles = roleService.selectByUidAndUniversityId(user.getId(), user.getUniversityId());
        for( Role role : roles )
            if( role.getName().equals(roleName) )
                return true;

        return false;
    }


    @Override
    public boolean isPlayDepartmentLeader(Long userId) {
        return isPlayOneRole(userId, "班主任“") || isPlayOneRole(userId, "辅导员") ||
                isPlayOneRole(userId, "副书记") || isPlayOneRole(userId, "书记") ||
                isPlayOneRole(userId, "副院长") || isPlayOneRole(userId, "院长");
    }

    @Override
    public boolean isPlaySchoolLeader(Long userId) {
        return isPlayOneRole(userId, "人事处工作人员") || isPlayOneRole(userId, "人事处副处长") ||
                isPlayOneRole(userId, "人事处处长") ;
    }
}
