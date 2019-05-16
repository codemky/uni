package edu.uni.userBaseInfo1.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.userBaseInfo1.bean.*;
import edu.uni.userBaseInfo1.mapper.SubdepartmentMapper;
import edu.uni.userBaseInfo1.service.SubdepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author：黄育林
 * create: 2019.4.20
 * modified: 2019.5.9
 * 功能：三级部门实现类
 */
@Service
public class SubdepartmentServiceImpl implements SubdepartmentService {
    @Autowired
    private SubdepartmentMapper subdepartmentMapper;


    /**
     * Author: laizhouhao 19:21 2019/5/16
     * @param department_id
     * @return List<Department>
     * @apiNote: 根据部门id查询该部门的所有有效科室
     */
    @Override
    public List<Subdepartment> selectValidSubDepartByDepartId(Long department_id) {
        //构造查询条件，条件为部门id、有效
        SubdepartmentExample subdepartmentExample = new SubdepartmentExample();
        subdepartmentExample.createCriteria().andDepartmentIdEqualTo(department_id)
                .andDeletedEqualTo(false);
        //查询满足该条件的所有信息并返回
        return subdepartmentMapper.selectByExample(subdepartmentExample);
    }
}
