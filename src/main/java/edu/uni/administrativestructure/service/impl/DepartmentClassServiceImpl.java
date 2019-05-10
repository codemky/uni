package edu.uni.administrativestructure.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.administrativestructure.bean.DepartmentClass;
import edu.uni.administrativestructure.bean.DepartmentClassExample;
import edu.uni.administrativestructure.config.administrativeStructureConfig;
import edu.uni.administrativestructure.mapper.DepartmentClassMapper;
import edu.uni.administrativestructure.service.DepartmentClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author：黄育林
 * create: 2019.4.26
 * modified: 2019.5.10
 * 功能：部门班级关系实现类
 */
@Service
public class DepartmentClassServiceImpl implements DepartmentClassService {
    @Autowired
    private DepartmentClassMapper departmentClassMapper;

    @Autowired
    private administrativeStructureConfig globalConfig;

    @Override
    public boolean insert(DepartmentClass departmentClass) {
        return departmentClassMapper.insert(departmentClass) > 0 ? true : false;
    }

    @Override
    public boolean delete(long id) {
        return departmentClassMapper.deleteByPrimaryKey(id) > 0 ? true : false;
    }

    @Override
    public boolean update(DepartmentClass departmentClass) {
        return departmentClassMapper.updateByPrimaryKeySelective(departmentClass) > 0 ? true : false;
    }

    @Override
    public DepartmentClass select(long id) {
        return departmentClassMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<DepartmentClass> selectPage(int pageNum) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        DepartmentClassExample example = new DepartmentClassExample();
        DepartmentClassExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(false);
        // 根据条件查询
        List<DepartmentClass> departmentClasses = departmentClassMapper.selectByExample(example);
        if(departmentClasses != null)
            return new PageInfo<>(departmentClasses);
        else
            return null;
    }

    @Override
    public PageInfo<DepartmentClass> selectPageByDepartment(int pageNum, long departmentId) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        DepartmentClassExample example = new DepartmentClassExample();
        DepartmentClassExample.Criteria criteria = example.createCriteria();
        criteria.andDepartmentIdEqualTo(departmentId).andDeletedEqualTo(false);
        // 根据条件查询
        List<DepartmentClass> departmentClasses = departmentClassMapper.selectByExample(example);   //  无条件查找部门
        if(departmentClasses != null)
            return new PageInfo<>(departmentClasses);
        else
            return null;
    }

    @Override
    public List<DepartmentClass> selectAll() {
        // 创建查询条件
        DepartmentClassExample example = new DepartmentClassExample();
        DepartmentClassExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(false);
        // 根据条件查询
        return departmentClassMapper.selectByExample(example);
    }

    @Override
    public PageInfo<DepartmentClass> selectPageByUniversity(int pageNum, long universityId) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        DepartmentClassExample example = new DepartmentClassExample();
        DepartmentClassExample.Criteria criteria = example.createCriteria();
        criteria.andUniversityIdEqualTo(universityId).andDeletedEqualTo(false);
        // 根据条件查询
        List<DepartmentClass> departmentClasses = departmentClassMapper.selectByExample(example);   //  无条件查找部门
        if(departmentClasses != null)
            return new PageInfo<>(departmentClasses);
        else
            return null;
    }

    @Override
    public PageInfo<DepartmentClass> selectPageByClass(int pageNum, long classId) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        DepartmentClassExample example = new DepartmentClassExample();
        DepartmentClassExample.Criteria criteria = example.createCriteria();
        criteria.andClassIdEqualTo(classId).andDeletedEqualTo(false);
        // 根据条件查询
        List<DepartmentClass> departmentClasses = departmentClassMapper.selectByExample(example);   //  无条件查找部门
        if(departmentClasses != null)
            return new PageInfo<>(departmentClasses);
        else
            return null;
    }
}
