/*
author:  邓凯丰
create:  2019.4.20
modified:  2019.5.11
description：实现CourseSyllabusService的接口，供操作者使用的操作
*/
package edu.uni.professionalcourses.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.professionalcourses.bean.CourseSyllabus;
import edu.uni.professionalcourses.bean.CourseSyllabusExample;
import edu.uni.professionalcourses.config.ProfessionalCoursesConfig;
import edu.uni.professionalcourses.mapper.CourseSyllabusMapper;
import edu.uni.professionalcourses.service.CourseSyllabusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseSyllabusServiceImpl implements CourseSyllabusService {
    @Autowired
    private CourseSyllabusMapper courseSyllabusMapper;

    @Autowired
    private ProfessionalCoursesConfig globalConfig;
    @Override
    public boolean insert(CourseSyllabus courseSyllabus) {
        return courseSyllabusMapper.insert(courseSyllabus) > 0 ? true : false;
    }

    @Override
    public boolean delete(long id) {
        CourseSyllabus courseSyllabus=new CourseSyllabus();
        courseSyllabus=courseSyllabusMapper.selectByPrimaryKey(id);
        courseSyllabus.setDeleted(true);

        return courseSyllabusMapper.updateByPrimaryKeySelective(courseSyllabus) > 0 ? true : false;
    }

    @Override
    public boolean update(CourseSyllabus courseSyllabus) {
        CourseSyllabus courseSyllabus1=new CourseSyllabus();
        courseSyllabus1=courseSyllabusMapper.selectByPrimaryKey(courseSyllabus.getId());
        courseSyllabus1.setDeleted(true);
        courseSyllabusMapper.insert(courseSyllabus1);

        return courseSyllabusMapper.updateByPrimaryKeySelective(courseSyllabus) > 0 ? true : false;
    }

    @Override
    public CourseSyllabus select(long id) {
        CourseSyllabus courseSyllabus=courseSyllabusMapper.selectByPrimaryKey(id);
        if (courseSyllabus.getDeleted()==false){
            return courseSyllabus;
        }else {
            return null;
        }

    }

    @Override
    public PageInfo<CourseSyllabus> selectPage(int pageNum) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());
        CourseSyllabusExample example=new CourseSyllabusExample();
        CourseSyllabusExample.Criteria criteria=example.createCriteria();
        criteria.andDeletedEqualTo(false);

        List<CourseSyllabus> courseSyllabuss = courseSyllabusMapper.selectByExample(example);
        if(courseSyllabuss != null)
            return new PageInfo<>(courseSyllabuss);
        else
            return null;
    }

    @Override
    public PageInfo<CourseSyllabus> selectPageByUniversity(int pageNum, long universityId) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());
        // 创建查询条件
        CourseSyllabusExample example = new CourseSyllabusExample();
        CourseSyllabusExample.Criteria criteria = example.createCriteria();
        criteria.andUniversityIdEqualTo(universityId).andDeletedEqualTo(false);
        // 根据条件查询
        List<CourseSyllabus> courseSyllabuss = courseSyllabusMapper.selectByExample(example);
        if(courseSyllabuss != null)
            return new PageInfo<>(courseSyllabuss);
        else
            return null;
    }
    @Override
    public PageInfo<CourseSyllabus> selectPageByCourse(int pageNum, long courseId) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());
        // 创建查询条件
        CourseSyllabusExample example = new CourseSyllabusExample();
        CourseSyllabusExample.Criteria criteria = example.createCriteria();
        criteria.andCourseIdEqualTo(courseId).andDeletedEqualTo(false);
        // 根据条件查询
        List<CourseSyllabus> courseSyllabuss = courseSyllabusMapper.selectByExample(example);
        if(courseSyllabuss != null)
            return new PageInfo<>(courseSyllabuss);
        else
            return null;
    }

    @Override
    public List<CourseSyllabus> selectAll() {
        return courseSyllabusMapper.selectByExample(null);
    }
}
