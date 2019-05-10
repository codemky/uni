package edu.uni.administrativestructure.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.administrativestructure.bean.UniversityDepartment;
import edu.uni.administrativestructure.bean.UniversityDepartmentExample;
import edu.uni.administrativestructure.config.administrativeStructureConfig;
import edu.uni.administrativestructure.mapper.UniversityDepartmentMapper;
import edu.uni.administrativestructure.service.UniversityDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author：黄育林
 * create: 2019.4.20
 * modified: 2019.5.9
 * 功能：学校部门关系实现类
 */
@Service
public class UniversityDepartmentServiceImpl implements UniversityDepartmentService {
    @Autowired
    private UniversityDepartmentMapper universityDepartmentMapper;

    @Autowired
    private administrativeStructureConfig globalConfig;

    @Override
    public boolean insert(UniversityDepartment universityDepartment) {
        return universityDepartmentMapper.insert(universityDepartment) > 0 ? true : false;
    }

    @Override
    public boolean delete(long id) {
        return universityDepartmentMapper.deleteByPrimaryKey(id) > 0 ? true : false;
    }

    @Override
    public boolean update(UniversityDepartment universityDepartment) {
        return universityDepartmentMapper.updateByPrimaryKeySelective(universityDepartment) > 0 ? true : false;
    }

    @Override
    public UniversityDepartment select(long id) {
        return universityDepartmentMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<UniversityDepartment> selectPage(int pageNum) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        //筛选有效记录
        UniversityDepartmentExample example = new UniversityDepartmentExample();
        UniversityDepartmentExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(false);
        List<UniversityDepartment> universityDepartments = universityDepartmentMapper.selectByExample(example);
        if(universityDepartments != null)
            return new PageInfo<>(universityDepartments);
        else
            return null;
    }

    @Override
    public PageInfo<UniversityDepartment> selectPageByDepartment(int pageNum, long departmentId) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        UniversityDepartmentExample example = new UniversityDepartmentExample();
        UniversityDepartmentExample.Criteria criteria = example.createCriteria();
        criteria.andDepartmentIdEqualTo(departmentId).andDeletedEqualTo(false);
        // 根据条件查询
        List<UniversityDepartment> universityDepartments = universityDepartmentMapper.selectByExample(example);
        if(universityDepartments != null)
            return new PageInfo<>(universityDepartments);
        else
            return null;
    }

    @Override
    public List<UniversityDepartment> selectAll() {
        // 创建查询条件
        UniversityDepartmentExample example = new UniversityDepartmentExample();
        UniversityDepartmentExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(false);
        // 根据条件查询
        List<UniversityDepartment> universityDepartments = universityDepartmentMapper.selectByExample(example);
        return universityDepartments;
    }

    @Override
    public PageInfo<UniversityDepartment> selectPageByUniversity(int pageNum, long universityId) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        UniversityDepartmentExample example = new UniversityDepartmentExample();
        UniversityDepartmentExample.Criteria criteria = example.createCriteria();
        criteria.andUniversityIdEqualTo(universityId).andDeletedEqualTo(false);
        // 根据条件查询
        List<UniversityDepartment> universityDepartments = universityDepartmentMapper.selectByExample(example);   //  无条件查找部门
        if(universityDepartments != null)
            return new PageInfo<>(universityDepartments);
        else
            return null;
    }
}
