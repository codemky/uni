package edu.uni.userBaseInfo1.service.impl;

import edu.uni.userBaseInfo1.bean.Department;
import edu.uni.userBaseInfo1.bean.DepartmentExample;
import edu.uni.userBaseInfo1.config.userBaseInfo1Config;
import edu.uni.userBaseInfo1.mapper.DepartmentMapper;
import edu.uni.userBaseInfo1.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author : 黄永佳
 * create : 2019/4/19 0023 15:34
 * modified :
 * 功能 :一级部门表的具体实现类
 **/
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private userBaseInfo1Config globalConfig;


    /**
     * Author: laizhouhao 19:15 2019/5/16
     * @param university_id
     * @return List<Department></>
     * @apiNote: 根据学校的id查询所有该学校的有效的部门信息
     */
    @Override
    public List<Department> selectAllValidDepartment(Long university_id) {
        //构造查询条件，条件为学校id、有效
        DepartmentExample departmentExample = new DepartmentExample();
        departmentExample.createCriteria().andUniversityIdEqualTo(university_id)
                .andDeletedEqualTo(false);
        //查询所有满足该条件的部门信息并返回
        return departmentMapper.selectByExample(departmentExample);
    }

    @Override
    public List<Department> selectDepartmentByName(String depaertmentName) {
        //构造查询条件，条件为学校id、有效
        DepartmentExample departmentExample = new DepartmentExample();
        DepartmentExample.Criteria criteria = departmentExample.createCriteria();
        criteria.andNameEqualTo(depaertmentName);
        criteria.andDeletedEqualTo(false);
        return  departmentMapper.selectByExample(departmentExample);
    }
}
