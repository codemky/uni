/*
author:  邓凯丰
create:  2019.4.20
modified:  2019.5.11
description：实现CourseResourceService的接口，供操作者使用的操作
*/
package edu.uni.professionalcourses.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.professionalcourses.bean.CourseHasResource;
import edu.uni.professionalcourses.bean.CourseResource;
import edu.uni.professionalcourses.bean.CourseResourceExample;
import edu.uni.professionalcourses.config.ProfessionalCoursesConfig;
import edu.uni.professionalcourses.mapper.CourseHasResourceMapper;
import edu.uni.professionalcourses.mapper.CourseResourceMapper;
import edu.uni.professionalcourses.service.CourseResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseResourceServiceImpl implements CourseResourceService {
    @Autowired
    private CourseResourceMapper courseResourceMapper;
    @Autowired
    private CourseHasResourceMapper courseHasResourceMapper;


    @Autowired
    private ProfessionalCoursesConfig globalConfig;
    @Override
    public boolean insert(CourseResource courseResource) {

        return courseResourceMapper.insert(courseResource) > 0 ? true : false;
    }

    @Override
    public boolean delete(long id) {
        CourseResource courseResource=new CourseResource();
        courseResource=courseResourceMapper.selectByPrimaryKey(id);
        courseResource.setDeleted(true);
        return courseResourceMapper.updateByPrimaryKeySelective(courseResource) > 0 ? true : false;
    }

    @Override
    public boolean update(CourseResource courseResource) {
        CourseResource courseResource1=new CourseResource();
        courseResource1=courseResourceMapper.selectByPrimaryKey(courseResource.getId());
        courseResource1.setDeleted(true);
        courseResourceMapper.insert(courseResource1);


        return courseResourceMapper.updateByPrimaryKeySelective(courseResource) > 0 ? true : false;
    }

    @Override
    public CourseResource select(long id) {
        CourseResource courseResource=courseResourceMapper.selectByPrimaryKey(id);
        if (courseResource.getDeleted()==false){
            return courseResource;
        }else {
            return null;
        }

    }

    @Override
    public PageInfo<CourseResource> selectPage(int pageNum) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());
        CourseResourceExample example=new CourseResourceExample();
        CourseResourceExample.Criteria criteria=example.createCriteria();
        criteria.andDeletedEqualTo(false);
        List<CourseResource> courseResources = courseResourceMapper.selectByExample(example);
        if(courseResources != null)
            return new PageInfo<>(courseResources);
        else
            return null;
    }

    @Override
    public PageInfo<CourseResource> selectPageByUniversity(int pageNum, long universityId) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());
        // 创建查询条件
        CourseResourceExample example = new CourseResourceExample();
        CourseResourceExample.Criteria criteria = example.createCriteria();
        criteria.andUniversityIdEqualTo(universityId).andDeletedEqualTo(false);
        // 根据条件查询
        List<CourseResource> courseResources = courseResourceMapper.selectByExample(example);
        if(courseResources != null)
            return new PageInfo<>(courseResources);
        else
            return null;
    }

    @Override
    public List<CourseResource> selectAll() {
        return courseResourceMapper.selectByExample(null);
    }
}
