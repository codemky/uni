/*
author:  邓凯丰
create:  2019.4.20
modified:  2019.5.11
description：实现CourseClassificationService的接口，供操作者使用的操作
*/
package edu.uni.professionalcourses.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.professionalcourses.bean.CourseClassification;
import edu.uni.professionalcourses.bean.CourseClassificationExample;
import edu.uni.professionalcourses.config.ProfessionalCoursesConfig;
import edu.uni.professionalcourses.mapper.CourseClassificationMapper;
import edu.uni.professionalcourses.service.CourseClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseClassificationServiceImpl implements CourseClassificationService {
    @Autowired
    private CourseClassificationMapper courseClassificationMapper;

    @Autowired
    private ProfessionalCoursesConfig globalConfig;
    @Override
    public boolean insert(CourseClassification courseClassification) {
        return courseClassificationMapper.insert(courseClassification) > 0 ? true : false;
    }

    @Override
    public boolean delete(long id) {
        CourseClassification courseClassification=new CourseClassification();
        courseClassification=courseClassificationMapper.selectByPrimaryKey(id);
        courseClassification.setDeleted(true);

        return courseClassificationMapper.updateByPrimaryKeySelective(courseClassification) > 0 ? true : false;
    }

    @Override
    public boolean update(CourseClassification courseClassification) {
        CourseClassification courseClassification1=new CourseClassification();
        courseClassification1=courseClassificationMapper.selectByPrimaryKey(courseClassification.getId());
        courseClassification1.setDeleted(true);
        courseClassificationMapper.insert(courseClassification1);

        return courseClassificationMapper.updateByPrimaryKeySelective(courseClassification) > 0 ? true : false;
    }

    @Override
    public CourseClassification select(long id) {
        CourseClassification courseClassification=new CourseClassification();
        courseClassification=courseClassificationMapper.selectByPrimaryKey(id);

        if(courseClassification.getDeleted()==false){
            return courseClassification;
        }else {
            return null;
        }

    }

    @Override
    public PageInfo<CourseClassification> selectPage(int pageNum) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());
        CourseClassificationExample example=new CourseClassificationExample();
        CourseClassificationExample.Criteria criteria=example.createCriteria();
        criteria.andDeletedEqualTo(false);
        List<CourseClassification> courseClassifications = courseClassificationMapper.selectByExample(example);
        if(courseClassifications != null)
            return new PageInfo<>(courseClassifications);
        else
            return null;
    }

    @Override
    public PageInfo<CourseClassification> selectPageByUniversity(int pageNum, long universityId) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());
        CourseClassificationExample example = new CourseClassificationExample();
        CourseClassificationExample.Criteria criteria = example.createCriteria();
        criteria.andUniversityIdEqualTo(universityId).andDeletedEqualTo(false);
        List<CourseClassification> courseClassifications = courseClassificationMapper.selectByExample(example);
        if(courseClassifications != null)
            return new PageInfo<>(courseClassifications);
        else
            return null;
    }



    @Override
    public List<CourseClassification> selectAll() {
        return courseClassificationMapper.selectByExample(null);
    }
}

