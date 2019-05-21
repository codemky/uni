package edu.uni.administrativestructure.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.administrativestructure.bean.ClassmatePosition;
import edu.uni.administrativestructure.bean.ClassmatePositionExample;
import edu.uni.administrativestructure.config.administrativeStructureConfig;
import edu.uni.administrativestructure.mapper.ClassmatePositionMapper;
import edu.uni.administrativestructure.service.ClassmatePositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author : 黄永佳
 * create : 2019/4/26 0026 18:43
 * modified :
 * 功能 :实现ClassmatePositionService接口
 **/
@Service
public class ClassmatePositionServiceImpl implements ClassmatePositionService {
    @Autowired
    private ClassmatePositionMapper classmatePositionMapper;
    @Autowired
    private administrativeStructureConfig globalConfig;
//插入班级人员岗位信息
    @Override
    public boolean insert(ClassmatePosition classmatePosition) {
        return classmatePositionMapper.insert(classmatePosition) > 0 ? true : false;
    }
//删除班级人员岗位信息
    @Override
    public boolean delete(long id) {
        return classmatePositionMapper.deleteByPrimaryKey(id) > 0 ? true : false;
    }
//修改班级人员岗位信息
    @Override
    public boolean update(ClassmatePosition classmatePosition) {
        return classmatePositionMapper.updateByPrimaryKeySelective(classmatePosition) > 0 ? true : false;
    }
//查询班级人员岗位信息
    @Override
    public ClassmatePosition select(long id) {
            return classmatePositionMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<ClassmatePosition> selectPage(int pageNum) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        List<ClassmatePosition> classmatePositions  = classmatePositionMapper.selectByExample(null);   //  无条件查找部门
        if(classmatePositions != null)
            return new PageInfo<>(classmatePositions);
        else
            return null;
    }

    @Override
    public PageInfo<ClassmatePosition> selectPageByUniversity(int pageNum, long universityId) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        ClassmatePositionExample example = new ClassmatePositionExample();
        ClassmatePositionExample.Criteria criteria = example.createCriteria();
        criteria.andUniversityIdEqualTo(universityId);
        // 根据条件查询
        List<ClassmatePosition> classmatePositions= classmatePositionMapper.selectByExample(example);
        if(classmatePositions != null)
            return new PageInfo<>(classmatePositions);
        else
            return null;
    }

    @Override
    public PageInfo<ClassmatePosition> selectPageByClassmate(int pageNum, long classmateId) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        ClassmatePositionExample example = new ClassmatePositionExample();
        ClassmatePositionExample.Criteria criteria = example.createCriteria();
        criteria.andClassmateIdEqualTo(classmateId);
        // 根据条件查询
        List<ClassmatePosition> classmatePositions= classmatePositionMapper.selectByExample(example);
        if(classmatePositions != null)
            return new PageInfo<>(classmatePositions);
        else
            return null;
    }

    @Override
    public PageInfo<ClassmatePosition> selectPageByPosition(int pageNum, long positionId) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        ClassmatePositionExample example = new ClassmatePositionExample();
        ClassmatePositionExample.Criteria criteria = example.createCriteria();
        criteria.andPositionIdEqualTo(positionId);
        // 根据条件查询
        List<ClassmatePosition> classmatePositions= classmatePositionMapper.selectByExample(example);
        if(classmatePositions != null)
            return new PageInfo<>(classmatePositions);
        else
            return null;
    }
}
