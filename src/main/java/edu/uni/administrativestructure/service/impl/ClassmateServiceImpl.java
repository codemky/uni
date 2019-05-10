package edu.uni.administrativestructure.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.administrativestructure.bean.Classmate;
import edu.uni.administrativestructure.bean.ClassmateExample;
import edu.uni.administrativestructure.bean.ClassmatePosition;
import edu.uni.administrativestructure.bean.ClassmatePositionExample;
import edu.uni.administrativestructure.config.administrativeStructureConfig;
import edu.uni.administrativestructure.mapper.ClassmateMapper;
import edu.uni.administrativestructure.mapper.ClassmatePositionMapper;
import edu.uni.administrativestructure.service.ClassmateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author：黄育林
 * create: 2019.4.25
 * modified: 2019.5.10
 * 功能：班级人员实现类
 */
@Service
public class ClassmateServiceImpl implements ClassmateService {
    @Autowired
    private ClassmateMapper classmateMapper;

    @Autowired
    private ClassmatePositionMapper classmatePositionMapper;

    @Autowired
    private administrativeStructureConfig globalConfig;

    @Override
    public boolean insert(Classmate classmate) {
        if(classmateMapper.insert(classmate)<=0) {
            return false;
        }
        //插入关系表
        ClassmatePosition classmatePosition =new ClassmatePosition();
        classmatePosition.setUniversityId(classmate.getUniversityId());
        classmatePosition.setByWho(classmate.getByWho());
        classmatePosition.setClassmateId(classmate.getId());
        classmatePosition.setDatetime(classmate.getDatetime());
        classmatePosition.setDeleted(classmate.getDeleted());
        if(classmatePositionMapper.insert(classmatePosition)<=0){
            return false;
        } else
            return true;
    }

    @Override
    public boolean delete(long id) {
        //获取旧数据
        Classmate classmate =classmateMapper.selectByPrimaryKey(id);
        classmate.setDeleted(true);
        //删除记录
        if(classmateMapper.deleteByPrimaryKey(id)<=0){
            return false;
        }else{
            //删除成功后插入旧纪录
            if(classmateMapper.insert(classmate)<=0){
                return false;
            }else{
                return true;
            }
        }
    }

    @Override
    public boolean update(Classmate classmate) {
        //获取修改前数据
        long id = classmate.getId();
        Classmate classmate1 = classmateMapper.selectByPrimaryKey(id);
        classmate1.setDeleted(true);
        //根据classmeteId获取classmateposition
        ClassmatePositionExample example = new ClassmatePositionExample();
        ClassmatePositionExample.Criteria criteria = example.createCriteria();
        criteria.andClassmateIdEqualTo(id);
        List<ClassmatePosition> classmatePositions = classmatePositionMapper.selectByExample(example);
        ClassmatePosition classmatePosition = classmatePositions.get(0);
        if(classmateMapper.updateByPrimaryKeySelective(classmate)<=0){
            return false;
        }
        if(classmatePositionMapper.updateByPrimaryKeySelective(classmatePosition)<=0){
            return false;
        }
        if(classmateMapper.insert(classmate1)<=0){
            return false;
        }
        else
            return true;
    }

    @Override
    public Classmate select(long id) {
        return classmateMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<Classmate> selectPage(int pageNum) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        ClassmateExample example = new ClassmateExample();
        ClassmateExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(false);
        List<Classmate> classmates = classmateMapper.selectByExample(example);   //  无条件查找部门
        if(classmates != null)
            return new PageInfo<>(classmates);
        else
            return null;
    }

    @Override
    public PageInfo<Classmate> selectPageByClass(int pageNum, long classId) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        ClassmateExample example = new ClassmateExample();
        ClassmateExample.Criteria criteria = example.createCriteria();
        criteria.andClassIdEqualTo(classId).andDeletedEqualTo(false);
        // 根据条件查询
        List<Classmate> classmates= classmateMapper.selectByExample(example);   //  无条件查找部门
        if(classmates != null)
            return new PageInfo<>(classmates);
        else
            return null;
    }

    @Override
    public PageInfo<Classmate> selectPageByUniversity(int pageNum, long universityId) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        ClassmateExample example = new ClassmateExample();
        ClassmateExample.Criteria criteria = example.createCriteria();
        criteria.andUniversityIdEqualTo(universityId).andDeletedEqualTo(false);
        // 根据条件查询
        List<Classmate> classmates = classmateMapper.selectByExample(example);   //  无条件查找部门
        if(classmates != null)
            return new PageInfo<>(classmates);
        else
            return null;
    }

    @Override
    public PageInfo<Classmate> selectPageByStudent(int pageNum, long studentId) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        ClassmateExample example = new ClassmateExample();
        ClassmateExample.Criteria criteria = example.createCriteria();
        criteria.andStudentIdEqualTo(studentId).andDeletedEqualTo(false);
        // 根据条件查询
        List<Classmate> classmates = classmateMapper.selectByExample(example);   //  无条件查找部门
        if(classmates != null)
            return new PageInfo<>(classmates);
        else
            return null;
    }

    @Override
    public List<Classmate> selectAll() {
        // 创建查询条件
        ClassmateExample example = new ClassmateExample();
        ClassmateExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(false);
        // 根据条件查询
        return classmateMapper.selectByExample(example);
    }
}
