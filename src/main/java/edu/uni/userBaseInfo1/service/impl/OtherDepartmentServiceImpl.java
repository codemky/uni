package edu.uni.userBaseInfo1.service.impl;

import edu.uni.administrativestructure.bean.Department;
import edu.uni.administrativestructure.bean.DepartmentExample;
import edu.uni.administrativestructure.mapper.DepartmentMapper;
import edu.uni.userBaseInfo1.config.userBaseInfo1Config;
import edu.uni.userBaseInfo1.service.OtherDepartmentService;
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
public class OtherDepartmentServiceImpl implements OtherDepartmentService {
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

    /**
     * Author: laizhouhao 15:28 2019/5/18
     * @param depart_name
     * @return Long
     * @apiNote: 根据部门名查找有效的部门id
     */
    @Override
    public Long selectDepartIdByName(String depart_name) {
        //构造查找条件
        DepartmentExample departmentExample = new DepartmentExample();
        departmentExample.createCriteria().andNameEqualTo(depart_name).andDeletedEqualTo(false);
        //获取学院id
        List<Department> departmentList = departmentMapper.selectByExample(departmentExample);
        return departmentList.size()>=1?departmentList.get(0).getId():null;
    }

    /**
     * Author: chenenru 13:47 2019/5/18
     * @param depaertmentName
     * @return Department
     * @apiNote: 根据学院名称查询学院
     */
    @Override
    public List<Department> selectDepartmentByName(String depaertmentName) {
        //构造查询条件，条件为学校id、有效
        DepartmentExample departmentExample = new DepartmentExample();
        DepartmentExample.Criteria criteria = departmentExample.createCriteria();
        criteria.andNameEqualTo(depaertmentName);
        criteria.andDeletedEqualTo(false);
        return  departmentMapper.selectByExample(departmentExample);
    }

    /**
     * Author: laizhouhao 20:39 2019/5/19
     * @param id
     * @return List<Department></>
     * @apiNote: 根据id获取有效的学院信息
     */
    @Override
    public List<Department> selectValidById(Long id) {
        //构造查询条件
        DepartmentExample departmentExample = new DepartmentExample();
        departmentExample.createCriteria().andIdEqualTo(id).andDeletedEqualTo(false);
        return departmentMapper.selectByExample(departmentExample);
    }
}
