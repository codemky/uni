package edu.uni.userBaseInfo1.service;

import edu.uni.userBaseInfo1.bean.Role;

/**
 * @Author laizhouhao
 * @Description Role实体的Service层接口
 * @Date 13:53 2019/5/11
 **/
public interface RoleService {
    /**
     * Author: laizhouhao 13:53 2019/5/11
     * @param
     * @return
     * @apiNote:
     */
    Role selectById(Long id);
}
