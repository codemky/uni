package edu.uni.userBaseInfo1.service.impl;

import edu.uni.administrativestructure.bean.Subdepartment;
import edu.uni.administrativestructure.bean.SubdepartmentExample;
import edu.uni.administrativestructure.mapper.SubdepartmentMapper;
import edu.uni.userBaseInfo1.service.OtherSubdepartmentService;
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
public class OtherSubdepartmentServiceImpl implements OtherSubdepartmentService {
    @Autowired
    private SubdepartmentMapper subdepartmentMapper;


    /**
     * Author: laizhouhao 19:21 2019/5/16
     * @param department_id
     * @return List<Subdepartment>
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

    /**
     * Author: laizhouhao 15:36 2019/5/18
     * @param subdepart_name
     * @return Long
     * @apiNote: 根据科室名查找有效的部门的id
     */
    @Override
    public Long selectIdBySubdepartName(String subdepart_name) {
        //构造查询条件
        SubdepartmentExample subdepartmentExample = new SubdepartmentExample();
        subdepartmentExample.createCriteria().andNameEqualTo(subdepart_name).andDeletedEqualTo(false);
        //查找部门id
        List<Subdepartment> subdepartmentList = subdepartmentMapper.selectByExample(subdepartmentExample);
        //判断是否查找到该部门，有的话返回id
        return subdepartmentList.size()>=1?subdepartmentList.get(0).getId():null;
    }
}
