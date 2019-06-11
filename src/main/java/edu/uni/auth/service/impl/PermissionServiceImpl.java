package edu.uni.auth.service.impl;

import edu.uni.auth.bean.*;
import edu.uni.auth.mapper.FuncPermissionMapper;
import edu.uni.auth.mapper.PermissionMapper;
import edu.uni.auth.mapper.RoleFuncMapper;
import edu.uni.auth.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
/**
 * @Author 何亮
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> selectAll() {
        return permissionMapper.selectByExample(null);
    }

    @Override
    public List<Permission> selectByRoleId(long roleId) {
        return permissionMapper.selectByRoleId(roleId);
    }
}
