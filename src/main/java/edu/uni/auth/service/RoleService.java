package edu.uni.auth.service;


import edu.uni.auth.bean.Role;
import edu.uni.auth.exception.RoleException;
import edu.uni.auth.exception.UnivInfoSUPException;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author 何亮
 */
public interface RoleService {
    /**
     * 信息主管新增学校中某部门角色，并建立学校信息主管与该角色的从属关系
     * @param role
     * @return
     */
    boolean insert(Role role) throws RoleException;   // 创建角色

    /**
     * 删除角色，并删除该角色从属关系
     * @param id
     * @return
     */
    void deleteByIdAndUniversityId(long id, long universityId) throws SQLException;  //删除角色

    /**
     * 修改角色
     * @return
     */
    boolean updateSelective(Role role) throws UnivInfoSUPException, RoleException;

    /**
     * 根据学校id获取该学校所有的角色
     * @param universityId
     * @return
     */
    List<Role> selectAllByUniversityId(long universityId);

    /**
     * 获取某用户在某学校中所包含的所有角色
     * @param uid
     * @param universityId
     * @return
     */
    List<Role> selectByUidAndUniversityId(long uid, long universityId);

    /**
     * 更新某学校中某用户的角色集合
     * @param roleIds
     * @param userId
     * @param universityId
     * @return
     */
    void updateUserRoleSet(Long universityId, Long userId, List<Long> roleIds) throws SQLException;

    /**
     * 更新某个角色的功能点集合
     * @param roleId
     * @param universityId
     * @param funcIds
     */
    void updateRoleFuncSet(Long universityId, Long roleId, List<Long> funcIds) throws SQLException;
}
