package edu.uni.userBaseInfo1.service.impl;

import edu.uni.userBaseInfo1.bean.Role;
import edu.uni.userBaseInfo1.bean.RoleExample;
import edu.uni.userBaseInfo1.mapper.RoleMapper;
import edu.uni.userBaseInfo1.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
