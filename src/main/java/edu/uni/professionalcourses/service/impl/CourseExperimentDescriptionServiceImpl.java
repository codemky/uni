/*
author:  邓凯丰
create:  2019.4.20
modified:  2019.5.11
description：实现CourseExperimentDescriptionService的接口，供操作者使用的操作
*/
package edu.uni.professionalcourses.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.professionalcourses.bean.CourseExperimentDescription;
import edu.uni.professionalcourses.bean.CourseExperimentDescriptionExample;
import edu.uni.professionalcourses.config.ProfessionalCoursesConfig;
import edu.uni.professionalcourses.mapper.CourseExperimentDescriptionMapper;
import edu.uni.professionalcourses.service.CourseExperimentDescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseExperimentDescriptionServiceImpl implements CourseExperimentDescriptionService {
    @Autowired
    private CourseExperimentDescriptionMapper courseExperimentDescriptionMapper;

    @Autowired
    private ProfessionalCoursesConfig globalConfig;
    @Override
    public boolean insert(CourseExperimentDescription courseExperimentDescription) {
        return courseExperimentDescriptionMapper.insert(courseExperimentDescription) > 0 ? true : false;
    }

    @Override
    public boolean delete(long id) {
        CourseExperimentDescription courseExperimentDescription=new CourseExperimentDescription();
        courseExperimentDescription=courseExperimentDescriptionMapper.selectByPrimaryKey(id);
        courseExperimentDescription.setDeleted(true);

        return courseExperimentDescriptionMapper.updateByPrimaryKeySelective(courseExperimentDescription) > 0 ? true : false;
    }

    @Override
    public boolean update(CourseExperimentDescription courseExperimentDescription) {
        CourseExperimentDescription courseExperimentDescription1=new CourseExperimentDescription();
        courseExperimentDescription1=courseExperimentDescriptionMapper.selectByPrimaryKey(courseExperimentDescription.getId());
        courseExperimentDescription1.setDeleted(true);
        courseExperimentDescriptionMapper.insert(courseExperimentDescription1);

        return courseExperimentDescriptionMapper.updateByPrimaryKeySelective(courseExperimentDescription) > 0 ? true : false;
    }

    @Override
    public CourseExperimentDescription select(long id) {
        CourseExperimentDescription courseExperimentDescription=new CourseExperimentDescription();
        courseExperimentDescription=courseExperimentDescriptionMapper.selectByPrimaryKey(id);
        if (courseExperimentDescription.getDeleted()==false){
            return courseExperimentDescription;
        }else {
            return null;
        }
    }

    @Override
    public PageInfo<CourseExperimentDescription> selectPage(int pageNum) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());
        CourseExperimentDescriptionExample example=new CourseExperimentDescriptionExample();
        CourseExperimentDescriptionExample.Criteria criteria=example.createCriteria();
        criteria.andDeletedEqualTo(false);

        List<CourseExperimentDescription> courseExperimentDescriptions = courseExperimentDescriptionMapper.selectByExample(example);
        if(courseExperimentDescriptions != null)
            return new PageInfo<>(courseExperimentDescriptions);
        else
            return null;
    }

    @Override
    public PageInfo<CourseExperimentDescription> selectPageByUniversity(int pageNum, long universityId) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());
        // 创建查询条件
        CourseExperimentDescriptionExample example = new CourseExperimentDescriptionExample();
        CourseExperimentDescriptionExample.Criteria criteria = example.createCriteria();
        criteria.andUniversityIdEqualTo(universityId).andDeletedEqualTo(false);
        // 根据条件查询
        List<CourseExperimentDescription> courseExperimentDescriptions = courseExperimentDescriptionMapper.selectByExample(example);
        if(courseExperimentDescriptions != null)
            return new PageInfo<>(courseExperimentDescriptions);
        else
            return null;
    }

    @Override
    public PageInfo<CourseExperimentDescription> selectPageByCourseexperiment(int pageNum, long courseexperimentId) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());
        // 创建查询条件
        CourseExperimentDescriptionExample example = new CourseExperimentDescriptionExample();
        CourseExperimentDescriptionExample.Criteria criteria = example.createCriteria();
        criteria.andCourseExperimentIdEqualTo(courseexperimentId).andDeletedEqualTo(false);
        // 根据条件查询
        List<CourseExperimentDescription> courseExperimentDescriptions = courseExperimentDescriptionMapper.selectByExample(example);
        if(courseExperimentDescriptions != null)
            return new PageInfo<>(courseExperimentDescriptions);
        else
            return null;
    }

    @Override
    public PageInfo<CourseExperimentDescription> selectPageByCoursebook(int pageNum, long coursebookId) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());
        CourseExperimentDescriptionExample example = new CourseExperimentDescriptionExample();
        CourseExperimentDescriptionExample.Criteria criteria = example.createCriteria();
        criteria.andCourseBookIdEqualTo(coursebookId).andDeletedEqualTo(false);
        List<CourseExperimentDescription> courseExperimentDescriptions = courseExperimentDescriptionMapper.selectByExample(example);
        if(courseExperimentDescriptions != null)
            return new PageInfo<>(courseExperimentDescriptions);
        else
            return null;
    }

    @Override
    public List<CourseExperimentDescription> selectAll() {
        return courseExperimentDescriptionMapper.selectByExample(null);
    }
}

