package edu.uni.administrativestructure.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.administrativestructure.bean.DepartmentSubdepartment;
import edu.uni.administrativestructure.bean.DepartmentSubdepartmentExample;
import edu.uni.administrativestructure.config.administrativeStructureConfig;
import edu.uni.administrativestructure.mapper.DepartmentSubdepartmentMapper;
import edu.uni.administrativestructure.service.DepartmentSubdepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
    author:黄永佳
    create:2019.4.23
    modified:null
    功能:实现DepartmentSubdepartmentService接口
 **/
@Service
public class DepartmentSubdepartmentServiceImpl implements DepartmentSubdepartmentService {
    @Autowired
    private DepartmentSubdepartmentMapper departmentSubdepartmentMapper;

    @Autowired
    private administrativeStructureConfig globalConfig;

    //插入表记录
    @Override
    public boolean insert(DepartmentSubdepartment departmentSubdepartment) {
        return departmentSubdepartmentMapper.insert(departmentSubdepartment) > 0 ? true : false;
    }

    //删除表记录
    @Override
    public boolean delete(long id) {
        return departmentSubdepartmentMapper.deleteByPrimaryKey(id) > 0 ? true : false;
    }

    //修改表记录
    @Override
    public boolean update(DepartmentSubdepartment departmentSubdepartment) {
        return departmentSubdepartmentMapper.updateByPrimaryKeySelective(departmentSubdepartment) > 0 ? true : false;
    }

    //查询表记录
    @Override
    public DepartmentSubdepartment select(long id) {
        return departmentSubdepartmentMapper.selectByPrimaryKey(id);
    }

    //分页查询所有部门
    @Override
    public PageInfo<DepartmentSubdepartment> selectPage(int pageNum) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        List<DepartmentSubdepartment> departmentSubdepartments = departmentSubdepartmentMapper.selectByExample(null);   //  无条件查找部门
        if(departmentSubdepartments != null)
            return new PageInfo<>(departmentSubdepartments);
        else
            return null;
    }

//    根据学校id分页查询一二级部门关系
    @Override
    public PageInfo<DepartmentSubdepartment> selectPageByUniversity(int pageNum, long universityId) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        DepartmentSubdepartmentExample example = new DepartmentSubdepartmentExample();
        DepartmentSubdepartmentExample.Criteria criteria = example.createCriteria();
        criteria.andUniversityIdEqualTo(universityId);
        // 根据条件查询
        List<DepartmentSubdepartment> departmentSubdepartments = departmentSubdepartmentMapper.selectByExample(example);
        if(departmentSubdepartments != null)
            return new PageInfo<>(departmentSubdepartments);
        else
            return null;
    }

//    根据一级部门id分页查询一二级部门关系表
    @Override
    public PageInfo<DepartmentSubdepartment> selectPageByDepartment(int pageNum, long departmentId) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        DepartmentSubdepartmentExample example = new DepartmentSubdepartmentExample();
        DepartmentSubdepartmentExample.Criteria criteria = example.createCriteria();
        criteria.andDepartmentIdEqualTo(departmentId);
        // 根据条件查询
        List<DepartmentSubdepartment> departmentSubdepartments = departmentSubdepartmentMapper.selectByExample(example);
        if(departmentSubdepartments != null)
            return new PageInfo<>(departmentSubdepartments);
        else
            return null;
    }

//    根据二级部门id分页查询一二级部门关系表
    @Override
    public PageInfo<DepartmentSubdepartment> selectPageBySubdepartment(int pageNum, long subdepartmentId) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        DepartmentSubdepartmentExample example = new DepartmentSubdepartmentExample();
        DepartmentSubdepartmentExample.Criteria criteria = example.createCriteria();
        criteria.andSubdepartmentIdEqualTo(subdepartmentId);
        // 根据条件查询
        List<DepartmentSubdepartment> departmentSubdepartments = departmentSubdepartmentMapper.selectByExample(example);
        if(departmentSubdepartments != null)
            return new PageInfo<>(departmentSubdepartments);
        else
            return null;
    }

//    遍历所有一二级部门关系
    @Override
    public List<DepartmentSubdepartment> selectAll() {
        return departmentSubdepartmentMapper.selectByExample(null);
    }
}
