package edu.uni.administrativestructure.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.administrativestructure.bean.Employ;
import edu.uni.administrativestructure.bean.EmployExample;
import edu.uni.administrativestructure.config.administrativeStructureConfig;
import edu.uni.administrativestructure.mapper.EmployMapper;
import edu.uni.administrativestructure.service.EmployService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author：黄育林
 * create: 2019.4.24
 * modified:2019.5.10
 * 功能：部门人员实现类
 */
@Service
public class EmployServiceImpl implements EmployService {
    @Autowired
    private EmployMapper employMapper;

    @Autowired
    private administrativeStructureConfig globalConfig;

    @Override
    public boolean insert(Employ employ) {
        return employMapper.insert(employ) > 0 ? true : false;
    }

    @Override
    public boolean delete(long id) {
        return employMapper.deleteByPrimaryKey(id) > 0 ? true : false;
    }

    @Override
    public boolean update(Employ employ) {
        return employMapper.updateByPrimaryKeySelective(employ) > 0 ? true : false;
    }

    @Override
    public Employ select(long id) {
        return employMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<Employ> selectPage(int pageNum) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        EmployExample example = new EmployExample();
        EmployExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(false);
        // 根据条件查询
        List<Employ> employs = employMapper.selectByExample(example);
        if(employs != null)
            return new PageInfo<>(employs);
        else
            return null;
    }

    @Override
    public PageInfo<Employ> selectPageByDepartment(int pageNum, long departmentId) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        EmployExample example = new EmployExample();
        EmployExample.Criteria criteria = example.createCriteria();
        criteria.andDepartmentIdEqualTo(departmentId).andDeletedEqualTo(false);
        // 根据条件查询
        List<Employ> employs = employMapper.selectByExample(example);   //  无条件查找部门
        if(employs != null)
            return new PageInfo<>(employs);
        else
            return null;
    }

    @Override
    public PageInfo<Employ> selectPageByUniversity(int pageNum, long universityId) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        EmployExample example = new EmployExample();
        EmployExample.Criteria criteria = example.createCriteria();
        criteria.andUniversityIdEqualTo(universityId).andDeletedEqualTo(false);
        // 根据条件查询
        List<Employ> employs = employMapper.selectByExample(example);   //  无条件查找部门
        if(employs != null)
            return new PageInfo<>(employs);
        else
            return null;
    }

    @Override
    public PageInfo<Employ> selectPageByEmployee(int pageNum, long workerId) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        EmployExample example = new EmployExample();
        EmployExample.Criteria criteria = example.createCriteria();
        criteria.andWorkerIdEqualTo(workerId).andDeletedEqualTo(false);
        // 根据条件查询
        List<Employ> employs = employMapper.selectByExample(example);   //  无条件查找部门
        if(employs != null)
            return new PageInfo<>(employs);
        else
            return null;
    }

    @Override
    public List<Employ> selectAll() {
        // 创建查询条件
        EmployExample example = new EmployExample();
        EmployExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(false);
        // 根据条件查询
        return employMapper.selectByExample(example);
    }

}
