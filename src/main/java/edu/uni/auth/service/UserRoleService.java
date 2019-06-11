package edu.uni.auth.service;

import edu.uni.auth.bean.UserRole;
import edu.uni.auth.exception.UnivInfoSUPException;
/**
 * @Author 何亮
 */
public interface UserRoleService {
    /**
     * 为用户新增角色
     * @param userRole
     * @return
     */
    boolean insert(UserRole userRole) throws UnivInfoSUPException;


    /**
     * 删除用户角色
     * @param id
     * @return
     */
    boolean deleteByIdAndUniversityId(long id, long universityId);

    /**
     * 更新用户角色状态
     * @param id
     * @param universityId
     * @param status
     * @return
     */
    boolean updateStatusByIdAndUniversityId(long id, long universityId, int status);

    /**
     * 删除某用户与角色的关联
     * @param userId
     * @param roleId
     * @param universityId
     */
    boolean deleteByUserIdAndRoleIdAndUniversityId(Long userId, Long roleId, Long universityId);
}
