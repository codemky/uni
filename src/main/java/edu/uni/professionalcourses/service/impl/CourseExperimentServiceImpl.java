/*
author:  邓凯丰
create:  2019.4.20
modified:  2019.5.11
description：实现CourseExperimentService的接口，供操作者使用的操作
*/
package edu.uni.professionalcourses.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.professionalcourses.bean.Course;
import edu.uni.professionalcourses.bean.CourseExperiment;
import edu.uni.professionalcourses.bean.CourseExperimentExample;
import edu.uni.professionalcourses.config.ProfessionalCoursesConfig;
import edu.uni.professionalcourses.mapper.CourseExperimentMapper;
import edu.uni.professionalcourses.mapper.CourseMapper;
import edu.uni.professionalcourses.service.CourseExperimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseExperimentServiceImpl implements CourseExperimentService {
    @Autowired
    private CourseExperimentMapper courseExperimentMapper;
    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private ProfessionalCoursesConfig globalConfig;
    @Override
    public boolean insert(CourseExperiment courseExperiment) {

        return courseExperimentMapper.insert(courseExperiment) > 0 ? true : false;
    }

    @Override
    public boolean delete(long id) {
        CourseExperiment courseExperiment1=new CourseExperiment();
        courseExperiment1=courseExperimentMapper.selectByPrimaryKey(id);
        courseExperiment1.setDeleted(true);


        return courseExperimentMapper.updateByPrimaryKeySelective(courseExperiment1) > 0 ? true : false;
    }

    @Override
    public boolean update(CourseExperiment courseExperiment) {
        CourseExperiment courseExperiment1=new CourseExperiment();
        courseExperiment1=courseExperimentMapper.selectByPrimaryKey(courseExperiment.getId());
        courseExperiment1.setDeleted(true);
        courseExperimentMapper.insert(courseExperiment1);

        return courseExperimentMapper.updateByPrimaryKeySelective(courseExperiment) > 0 ? true : false;
    }

    @Override
    public CourseExperiment select(long id) {
        CourseExperiment courseExperiment=courseExperimentMapper.selectByPrimaryKey(id);
        if (courseExperiment.getDeleted()==false){
            return courseExperiment;
        }else {
            return null;
        }

    }

    @Override
    public PageInfo<CourseExperiment> selectPage(int pageNum) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());
        CourseExperimentExample example=new CourseExperimentExample();
        CourseExperimentExample.Criteria criteria=example.createCriteria();
        criteria.andDeletedEqualTo(false);

        List<CourseExperiment> courseExperiments = courseExperimentMapper.selectByExample(example);
        if(courseExperiments != null)
            return new PageInfo<>(courseExperiments);
        else
            return null;
    }

    @Override
    public PageInfo<CourseExperiment> selectPageByUniversity(int pageNum, long universityId) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());
        CourseExperimentExample example = new CourseExperimentExample();
        CourseExperimentExample.Criteria criteria = example.createCriteria();
        criteria.andUniversityIdEqualTo(universityId).andDeletedEqualTo(false);
        List<CourseExperiment> courseExperiments = courseExperimentMapper.selectByExample(example);
        if(courseExperiments != null)
            return new PageInfo<>(courseExperiments);
        else
            return null;
    }
    @Override
    public PageInfo<CourseExperiment> selectPageByCourse(int pageNum, long courseId) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());
        CourseExperimentExample example = new CourseExperimentExample();
        CourseExperimentExample.Criteria criteria = example.createCriteria();
        criteria.andCourseIdEqualTo(courseId).andDeletedEqualTo(false);
        List<CourseExperiment> courseExperiments = courseExperimentMapper.selectByExample(example);
        if(courseExperiments != null)
            return new PageInfo<>(courseExperiments);
        else
            return null;
    }
    @Override
    public List<CourseExperiment> selectAll() {
        return courseExperimentMapper.selectByExample(null);
    }
}

