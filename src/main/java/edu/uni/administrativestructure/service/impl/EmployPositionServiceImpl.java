package edu.uni.administrativestructure.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.administrativestructure.bean.EmployPosition;
import edu.uni.administrativestructure.bean.EmployPositionExample;
import edu.uni.administrativestructure.config.administrativeStructureConfig;
import edu.uni.administrativestructure.mapper.EmployPositionMapper;
import edu.uni.administrativestructure.service.EmployPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author：黄育林
 * create: 2019.4.27
 * modified: 2019.5.10
 * 功能：雇员岗位关系实现类
 */
@Service
public class EmployPositionServiceImpl implements EmployPositionService {
    @Autowired
    private EmployPositionMapper employPositionMapper;

    @Autowired
    private administrativeStructureConfig globalConfig;

    @Override
    public boolean insert(EmployPosition employPosition) {
        return employPositionMapper.insert(employPosition) > 0 ? true : false;
    }

    @Override
    public boolean delete(long id) {
        return employPositionMapper.deleteByPrimaryKey(id) > 0 ? true : false;
    }

    @Override
    public boolean update(EmployPosition employPosition) {
        return employPositionMapper.updateByPrimaryKeySelective(employPosition) > 0 ? true : false;
    }

    @Override
    public EmployPosition select(long id) {
        return employPositionMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<EmployPosition> selectPage(int pageNum) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        EmployPositionExample example = new EmployPositionExample();
        EmployPositionExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(false);
        // 根据条件查询
        List<EmployPosition> employPositions = employPositionMapper.selectByExample(example);
        if(employPositions != null)
            return new PageInfo<>(employPositions);
        else
            return null;
    }

    @Override
    public PageInfo<EmployPosition> selectPageByEmploy(int pageNum, long employId) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        EmployPositionExample example = new EmployPositionExample();
        EmployPositionExample.Criteria criteria = example.createCriteria();
        criteria.andEmployIdEqualTo(employId).andDeletedEqualTo(false);
        // 根据条件查询
        List<EmployPosition> employPositions = employPositionMapper.selectByExample(example);   //  无条件查找部门
        if(employPositions != null)
            return new PageInfo<>(employPositions);
        else
            return null;
    }

    @Override
    public List<EmployPosition> selectAll() {
        // 创建查询条件
        EmployPositionExample example = new EmployPositionExample();
        EmployPositionExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(false);
        // 根据条件查询
        return employPositionMapper.selectByExample(example);
    }

    @Override
    public PageInfo<EmployPosition> selectPageByUniversity(int pageNum, long universityId) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        EmployPositionExample example = new EmployPositionExample();
        EmployPositionExample.Criteria criteria = example.createCriteria();
        criteria.andUniversityIdEqualTo(universityId).andDeletedEqualTo(false);
        // 根据条件查询
        List<EmployPosition> employPositions = employPositionMapper.selectByExample(example);   //  无条件查找部门
        if(employPositions != null)
            return new PageInfo<>(employPositions);
        else
            return null;
    }

    @Override
    public PageInfo<EmployPosition> selectPageByPosition(int pageNum, long positionId) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        EmployPositionExample example = new EmployPositionExample();
        EmployPositionExample.Criteria criteria = example.createCriteria();
        criteria.andPositionIdEqualTo(positionId).andDeletedEqualTo(false);
        // 根据条件查询
        List<EmployPosition> employPositions = employPositionMapper.selectByExample(example);   //  无条件查找部门
        if(employPositions != null)
            return new PageInfo<>(employPositions);
        else
            return null;
    }
}
