package edu.uni.administrativestructure.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.administrativestructure.bean.Class;
import edu.uni.administrativestructure.bean.ClassExample;
import edu.uni.administrativestructure.bean.DepartmentClass;
import edu.uni.administrativestructure.bean.DepartmentClassExample;
import edu.uni.administrativestructure.config.administrativeStructureConfig;
import edu.uni.administrativestructure.mapper.ClassMapper;
import edu.uni.administrativestructure.mapper.DepartmentClassMapper;
import edu.uni.administrativestructure.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author : 黄永佳
 * create : 2019/4/26 0026 12:48
 * modified :
 * 功能 :实现classService接口
 **/
@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassMapper classMapper;
    @Autowired
    private DepartmentClassMapper departmentClassMapper;
    @Autowired
    private administrativeStructureConfig globalConfig;

// 插入班级信息
    @Override
    public boolean insert(Class class_1) {
        if(classMapper.insert(class_1)<=0){
            return false;
        }
        DepartmentClass departmentClass=new DepartmentClass();
        departmentClass.setByWho(class_1.getByWho());
        departmentClass.setClassId(class_1.getId());
        departmentClass.setDatetime(class_1.getDatetime());
        departmentClass.setDeleted(false);
        departmentClass.setUniversityId(class_1.getUniversityId());
        departmentClass.setDepartmentId(class_1.getDepartmentId());
        if(departmentClassMapper.insert(departmentClass)<=0){
            return false;
        }
        return true;
    }
// 删除班级信息
    @Override
    public boolean delete(long id) {
        Class class_1 = classMapper.selectByPrimaryKey(id);
        //创造查询条件
        DepartmentClassExample example = new DepartmentClassExample();
        DepartmentClassExample.Criteria criteria = example.createCriteria();
        criteria.andClassIdEqualTo(id);
        //  获取关系表内记录
        List<DepartmentClass> departmentClasses = departmentClassMapper.selectByExample(example);
        DepartmentClass departmentClass = departmentClasses.get(0);
        //删除关系表内记录
        departmentClassMapper.deleteByPrimaryKey(departmentClass.getId());
        if (classMapper.deleteByPrimaryKey(id) <= 0) {
            //无法删除，重新插入关系表记录
            departmentClassMapper.insert(departmentClass);
            return false;
        } else {
            class_1.setDeleted(true);
            //删除后插入旧记录，不再插入关系表
            if (classMapper.insert(class_1) <= 0) {
                return false;
            } else {
                return true;
            }
        }
    }
//修改班级信息
    @Override
    public boolean update(Class class_1) {
        Class class_2=new Class();
        class_2=classMapper.selectByPrimaryKey(class_1.getId());
        class_2.setDeleted(true);
        if(classMapper.updateByPrimaryKeySelective(class_1)<=0){
            return false;
        }
        if(classMapper.insert(class_2)<=0){
            return false;
        }
//        判断关系表是否需要修改
        if(class_1.getDepartmentId()!=class_2.getDepartmentId()){
            DepartmentClassExample example = new DepartmentClassExample();
            DepartmentClassExample.Criteria criteria = example.createCriteria();
            criteria.andClassIdEqualTo(class_1.getId()).andDeletedEqualTo(false);
            List<DepartmentClass> departmentClasses=departmentClassMapper.selectByExample(example);
            DepartmentClass departmentClass= departmentClasses.get(0);
            departmentClass.setDeleted(true);
            departmentClassMapper.insert(departmentClass);
            departmentClass.setUniversityId(class_1.getUniversityId());
            departmentClass.setByWho(class_1.getByWho());
            departmentClass.setDatetime(class_1.getDatetime());
            departmentClass.setDeleted(false);
            departmentClass.setDepartmentId(class_1.getDepartmentId());
            departmentClass.setClassId(class_1.getId());
            departmentClassMapper.updateByPrimaryKeySelective(departmentClass);
        }
        return true;
    }
//查询班级信息
    @Override
    public Class select(long id) {
        return classMapper.selectByPrimaryKey(id);
    }
//分页查询所有班级信息
    @Override
    public PageInfo<Class> selectPage(int pageNum) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());
        // 开启分页查询，第一次切仅第一次查询时生效
        ClassExample example = new ClassExample();
        ClassExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(false);
        List<Class> classes = classMapper.selectByExample(example);   //  查找部门
        if(classes != null)
            return new PageInfo<>(classes);
        else
            return null;
    }
//根据学校id分页查询班级信息
    @Override
    public PageInfo<Class> selectPageByUniversity(int pageNum, long universityId) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        ClassExample example = new ClassExample();
        ClassExample.Criteria criteria = example.createCriteria();
        criteria.andUniversityIdEqualTo(universityId).andDeletedEqualTo(false);
        // 根据条件查询
        List<Class> classes = classMapper.selectByExample(example);
        if(classes != null)
            return new PageInfo<>(classes);
        else
            return null;
    }
//根据部门id分页查询班级信息
    @Override
    public PageInfo<Class> selectPageByDepartment(int pageNum, long departmentId) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        ClassExample example = new ClassExample();
        ClassExample.Criteria criteria = example.createCriteria();
        criteria.andDepartmentIdEqualTo(departmentId).andDeletedEqualTo(false);
        // 根据条件查询
        List<Class> classes = classMapper.selectByExample(example);
        if(classes != null)
            return new PageInfo<>(classes);
        else
            return null;
    }
//根据专业id分页查询班级信息
    @Override
    public PageInfo<Class> selectPageBySpecialty(int pageNum, long specialtyId) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        ClassExample example = new ClassExample();
        ClassExample.Criteria criteria = example.createCriteria();
        criteria.andSpecialtyIdEqualTo(specialtyId).andDeletedEqualTo(false);
        // 根据条件查询
        List<Class> classes = classMapper.selectByExample(example);
        if(classes != null)
            return new PageInfo<>(classes);
        else
            return null;
    }
}
