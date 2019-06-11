package edu.uni.auth.service;


import edu.uni.auth.bean.Permission;

import java.util.List;

/**
 * @author 何亮
 */
public interface PermissionService {
    /**
     * 获取所有权限
     * @return
     */
    List<Permission> selectAll();

    /**
     * 获取某学校某角色的所有权限
     * @param roleId
     * @return
     */
    List<Permission> selectByRoleId(long roleId);

}
