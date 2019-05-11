package edu.uni.userBaseInfo1.service.impl;

import edu.uni.userBaseInfo1.bean.Role;
import edu.uni.userBaseInfo1.mapper.RoleMapper;
import edu.uni.userBaseInfo1.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author laizhouhao
 * @Description Role实体类的Service层实现类
 * @Date 13:54 2019/5/11
 **/
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleMapper roleMapper;
    /**
     * Author: laizhouhao 13:53 2019/5/11
     * @param
     * @return
     * @apiNote:
     */
    @Override
    public Role selectById(Long id) {
        return roleMapper.selectByPrimaryKey(id);
    }
}
