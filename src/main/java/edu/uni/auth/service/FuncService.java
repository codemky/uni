package edu.uni.auth.service;

import edu.uni.auth.bean.Node;
import edu.uni.auth.bean.User;

import java.util.List;

/**
 * @Author 何亮
 */
public interface FuncService {
    /**
     * 获取用户的功能菜单
     * @param user
     * @return
     */
    List<Node> getUserMenu(User user);

    /**
     * 获取角色功能点菜单
     * @param roleId
     * @return
     */
    List<Node> getRoleFuncMenu(long roleId);
}
