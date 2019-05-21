/*
author:  邓凯丰
create:  2019.4.20
modified:  2019.5.11
description：实现HasResourceService的接口，供操作者使用的操作
*/
package edu.uni.professionalcourses.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.professionalcourses.bean.CourseHasResource;
import edu.uni.professionalcourses.bean.CourseHasResourceExample;
import edu.uni.professionalcourses.config.ProfessionalCoursesConfig;
import edu.uni.professionalcourses.mapper.CourseHasResourceMapper;
import edu.uni.professionalcourses.service.CourseHasResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseHasResourceServiceImpl implements CourseHasResourceService {
    @Autowired
    private CourseHasResourceMapper courseHasResourceMapper;

    @Autowired
    private ProfessionalCoursesConfig globalConfig;
    @Override
    public boolean insert(CourseHasResource courseHasResource) {
        return courseHasResourceMapper.insert(courseHasResource) > 0 ? true : false;
    }

    @Override
    public boolean delete(long id) {
        return courseHasResourceMapper.deleteByPrimaryKey(id) > 0 ? true : false;
    }

    @Override
    public boolean update(CourseHasResource courseHasResource) {
        return courseHasResourceMapper.updateByPrimaryKeySelective(courseHasResource) > 0 ? true : false;
    }

    @Override
    public CourseHasResource select(long id) {
        return courseHasResourceMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<CourseHasResource> selectPage(int pageNum) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());
        List<CourseHasResource> courseHasResources = courseHasResourceMapper.selectByExample(null);
        if(courseHasResources != null)
            return new PageInfo<>(courseHasResources);
        else
            return null;
    }

    @Override
    public PageInfo<CourseHasResource> selectPageByUniversity(int pageNum, long universityId) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());
        CourseHasResourceExample example = new CourseHasResourceExample();
        CourseHasResourceExample.Criteria criteria = example.createCriteria();
        criteria.andUniversityIdEqualTo(universityId);
        List<CourseHasResource> courseHasResources = courseHasResourceMapper.selectByExample(example);
        if(courseHasResources != null)
            return new PageInfo<>(courseHasResources);
        else
            return null;
    }

    @Override
    public PageInfo<CourseHasResource> selectPageByCourse(int pageNum, long courseId) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());
        CourseHasResourceExample example = new CourseHasResourceExample();
        CourseHasResourceExample.Criteria criteria = example.createCriteria();
        criteria.andCourseIdEqualTo(courseId);
        List<CourseHasResource> courseHasResources = courseHasResourceMapper.selectByExample(example);
        if(courseHasResources != null)
            return new PageInfo<>(courseHasResources);
        else
            return null;
    }

    @Override
    public PageInfo<CourseHasResource> selectPageByCourseresource(int pageNum, long courseresourceId) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());
        CourseHasResourceExample example = new CourseHasResourceExample();
        CourseHasResourceExample.Criteria criteria = example.createCriteria();
        criteria.andCourseResourceIdEqualTo(courseresourceId);
        List<CourseHasResource> courseHasResources = courseHasResourceMapper.selectByExample(example);
        if(courseHasResources != null)
            return new PageInfo<>(courseHasResources);
        else
            return null;
    }

    @Override
    public List<CourseHasResource> selectAll() {
        return courseHasResourceMapper.selectByExample(null);
    }
}

