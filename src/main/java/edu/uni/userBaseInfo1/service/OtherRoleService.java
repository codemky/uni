package edu.uni.userBaseInfo1.service;

import edu.uni.auth.bean.Role;
import edu.uni.userBaseInfo1.bean.User;

import java.util.List;

/**
 * @Author laizhouhao
 * @Description Role实体的Service层接口
 * @Date 13:53 2019/5/11
 **/
public interface OtherRoleService {
    /**
     * Author: laizhouhao 13:53 2019/5/11
     * @param id
     * @return Role
     * @apiNote: 根据id获取角色详情
     */
    Role selectById(Long id);

    /**
     * Author: mokuanyuan 11:45 2019/6/4
     * @return List<Role>
     * @apiNote: 获取所有角色详情
     */
    List<Role> selectAll();

    /**
     * Author: mokuanyuan 15:30 2019/6/13
     * @param userId
     * @param roleName
     * @return boolean
     * @apiNote: 根据用户id和角色名判断这个用户是否有扮演这个角色
     */
    boolean isPlayOneRole(Long userId, String roleName);


}



