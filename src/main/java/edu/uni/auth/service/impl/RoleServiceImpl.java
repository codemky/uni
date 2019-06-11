package edu.uni.auth.service.impl;

import edu.uni.auth.bean.*;
import edu.uni.auth.exception.RoleException;
import edu.uni.auth.exception.UnivInfoSUPException;
import edu.uni.auth.mapper.RoleFuncMapper;
import edu.uni.auth.mapper.RoleMapper;
import edu.uni.auth.mapper.UserRoleMapper;
import edu.uni.auth.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author 何亮
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleFuncMapper roleFuncMapper;

    /**
     * 新增角色
     * @param role
     * @return
     */
    @Override
    public boolean insert(Role role) throws RoleException {
        // 检查角色名是否已存在
        {
            RoleExample roleExample = new RoleExample();
            RoleExample.Criteria criteria = roleExample.createCriteria();
            criteria.andUniversityIdEqualTo(role.getUniversityId());
            criteria.andNameEqualTo(role.getName());
            int count = roleMapper.countByExample(roleExample);
            if(count > 0){
                throw new RoleException("角色已存在，添加失败");
            }
        }
        return roleMapper.insert(role) > 0 ? true : false;
    }

    /**
     * 删除某学校某角色
     * @param id
     * @param universityId
     * @return
     */
    @Override
    @Transactional(isolation= Isolation.READ_COMMITTED, propagation= Propagation.REQUIRED)
    public void deleteByIdAndUniversityId(long id, long universityId) throws SQLException {
        UserRoleExample userRoleExample = new UserRoleExample();{
            UserRoleExample.Criteria criteria = userRoleExample.createCriteria();
            criteria.andRoleIdEqualTo(id);
        }
        userRoleMapper.deleteByExample(userRoleExample);

        RoleFuncExample roleFuncExample = new RoleFuncExample();{
            RoleFuncExample.Criteria criteria = roleFuncExample.createCriteria();
            criteria.andRoleIdEqualTo(id);
        }
        roleFuncMapper.deleteByExample(roleFuncExample);

        RoleExample roleExample = new RoleExample();
        {
            RoleExample.Criteria criteria = roleExample.createCriteria();
            criteria.andIdEqualTo(id);
            criteria.andUniversityIdEqualTo(universityId);
        }
        if(roleMapper.deleteByExample(roleExample) < 1){
            throw new SQLException("刪除role記錄失敗");
        }
    }

    /**
     * 修改角色
     * @return
     */
    @Override
    public boolean updateSelective(Role role) throws RoleException {
        // 若修改角色名，查看该角色是否已存在
        if(role.getName() != null){
            RoleExample roleExample = new RoleExample();{
                RoleExample.Criteria criteria = roleExample.createCriteria();
                criteria.andNameEqualTo(role.getName());
                criteria.andUniversityIdEqualTo(role.getUniversityId());
            }
            List<Role> roles = roleMapper.selectByExample(roleExample);
            if(roles.size() > 0){
                throw new RoleException("角色" + role.getName() + "在你们学校已存在");
            }
        }
        return roleMapper.updateByPrimaryKeySelective(role) > 0 ? true : false;
    }

    /**
     * 根据学校id获取该学校所有的角色
     * @param universityId
     * @return
     */
    @Override
    public List<Role> selectAllByUniversityId(long universityId) {
        RoleExample roleExample = new RoleExample();
        RoleExample.Criteria criteria = roleExample.createCriteria();
        criteria.andUniversityIdEqualTo(universityId);

        return roleMapper.selectByExample(roleExample);
    }

    /**
     * 获取某用户在某学校中所包含的所有角色
     * @param uid
     * @param universityId
     * @return
     */
    public List<Role> selectByUidAndUniversityId(long uid, long universityId){
        UserRoleExample userRoleExample = new UserRoleExample();
        UserRoleExample.Criteria criteria = userRoleExample.createCriteria();
        criteria.andUserIdEqualTo(uid);
        criteria.andUniversityIdEqualTo(universityId);
        List<UserRole> userRoles = userRoleMapper.selectByExample(userRoleExample);

        List<Role> roles = new LinkedList<>();
        userRoles.forEach(userRole -> {
            Long roleId = userRole.getRoleId();
            Role role = roleMapper.selectByPrimaryKey(roleId);
            roles.add(role);
        });
        return roles;
    }


    /**
     * 更新某学校中某用户的角色集合
     * @param roleIds
     * @param userId
     * @param universityId
     * @return
     */
    @Override
    @Transactional(isolation= Isolation.READ_COMMITTED, propagation= Propagation.REQUIRED)
    public void updateUserRoleSet(Long universityId, Long userId, List<Long> roleIds) throws SQLException {
        // 获取用户在某学校的角色id集合
        List<Long> ids = roleMapper.selectRoleSetByUserIdAndUniversityId(userId, universityId);
        // 更新角色集合
        if(roleIds != null){
            for (int i=0 ; i<roleIds.size() ; ++i){
                // 如果原角色集不包括这个新的角色，那么新增该角色与用户的关联
                if(ids == null || !ids.contains(roleIds.get(i))){
                    UserRole userRole = new UserRole();
                    userRole.setUserId(userId);
                    userRole.setRoleId(roleIds.get(i));
                    userRole.setUniversityId(universityId);
                    userRole.setStatus(0);

                    int insert = userRoleMapper.insert(userRole);
                    if(insert < 1){
                        throw new SQLException("新增用户与角色的关联失败：userId=" + userId + ",roleId=" + roleIds.get(i));
                    }
                }
            }
        }
        if(ids != null){
            for(int i=0 ; i<ids.size() ; i++){
                // 如果新的角色集合中不包含某个原先的角色，那么移除这个角色与用户的关联
                if(roleIds == null ||  !roleIds.contains(ids.get(i))){
                    UserRoleExample example = new UserRoleExample();{
                        UserRoleExample.Criteria criteria = example.createCriteria();
                        criteria.andUserIdEqualTo(userId);
                        criteria.andRoleIdEqualTo(ids.get(i));
                    }
                    int delete = userRoleMapper.deleteByExample(example);
                    if(delete < 1){
                        throw new SQLException("删除用户与角色的关联失败：userId=" + userId + ",roleId=" + ids.get(i));
                    }
                }
            }
        }
    }

    /**
     * 更新某个角色的功能点集合
     * @param roleId
     * @param funcIds
     */
    @Override
    @Transactional(isolation= Isolation.READ_COMMITTED, propagation= Propagation.REQUIRED)
    public void updateRoleFuncSet(Long universityId, Long roleId, List<Long> funcIds) throws SQLException {
        List<Long> ids =  roleMapper.selectFuncSetByRoleId(roleId);
        // 更新角色的功能点集合
        if(funcIds != null){
            for (int i=0 ; i<funcIds.size() ; ++i){
                // 如果原功能点集合不包括这个新的功能点，那么新增角色与功能点的关联
                if(ids == null || !ids.contains(funcIds.get(i))){
                    RoleFunc roleFunc = new RoleFunc();
                    roleFunc.setRoleId(roleId);
                    roleFunc.setFuncId(funcIds.get(i));
                    roleFunc.setUniversityId(universityId);
                    roleFunc.setStatus(0);

                    int insert = roleFuncMapper.insert(roleFunc);
                    if(insert < 1){
                        throw new SQLException("新增角色与功能点的关联失败：roleId=" + roleId + ",funcId=" + funcIds.get(i));
                    }
                }
            }
        }

        if(ids != null){
            for(int i=0 ; i<ids.size() ; i++){
                // 如果新的角色集合中不包含某个原先的角色，那么移除这个角色与用户的关联
                if(funcIds == null ||  !funcIds.contains(ids.get(i))){
                    RoleFuncExample example = new RoleFuncExample();{
                        RoleFuncExample.Criteria criteria = example.createCriteria();
                        criteria.andRoleIdEqualTo(roleId);
                        criteria.andFuncIdEqualTo(ids.get(i));
                    }

                    int delete = roleFuncMapper.deleteByExample(example);
                    if(delete < 1){
                        throw new SQLException("删除角色与功能点的关联失败：roleId=" + roleId + ",funcId=" + ids.get(i));
                    }
                }
            }
        }
    }

}
