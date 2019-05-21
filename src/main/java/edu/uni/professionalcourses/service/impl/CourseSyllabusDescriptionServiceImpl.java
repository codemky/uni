/*
author:  邓凯丰
create:  2019.4.20
modified:  2019.5.11
description：实现CourseExperimentDescriptionService的接口，供操作者使用的操作
*/
package edu.uni.professionalcourses.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.professionalcourses.bean.CourseSyllabusDescription;
import edu.uni.professionalcourses.bean.CourseSyllabusDescriptionExample;
import edu.uni.professionalcourses.config.ProfessionalCoursesConfig;
import edu.uni.professionalcourses.mapper.CourseSyllabusDescriptionMapper;
import edu.uni.professionalcourses.service.CourseSyllabusDescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseSyllabusDescriptionServiceImpl implements CourseSyllabusDescriptionService {
    @Autowired
    private CourseSyllabusDescriptionMapper courseSyllabusDescriptionMapper;

    @Autowired
    private ProfessionalCoursesConfig globalConfig;
    @Override
    public boolean insert(CourseSyllabusDescription courseSyllabusDescription) {
        return courseSyllabusDescriptionMapper.insert(courseSyllabusDescription) > 0 ? true : false;
    }

    @Override
    public boolean delete(long id) {
        CourseSyllabusDescription courseSyllabusDescription=new CourseSyllabusDescription();
        courseSyllabusDescription=courseSyllabusDescriptionMapper.selectByPrimaryKey(id);
        courseSyllabusDescription.setDeleted(true);

        return courseSyllabusDescriptionMapper.updateByPrimaryKeySelective(courseSyllabusDescription) > 0 ? true : false;
    }

    @Override
    public boolean update(CourseSyllabusDescription courseSyllabusDescription) {
        CourseSyllabusDescription courseSyllabusDescription1=new CourseSyllabusDescription();
        courseSyllabusDescription1=courseSyllabusDescriptionMapper.selectByPrimaryKey(courseSyllabusDescription.getId());
        courseSyllabusDescription1.setDeleted(true);
        courseSyllabusDescriptionMapper.insert(courseSyllabusDescription1);

        return courseSyllabusDescriptionMapper.updateByPrimaryKeySelective(courseSyllabusDescription) > 0 ? true : false;
    }

    @Override
    public CourseSyllabusDescription select(long id) {
        CourseSyllabusDescription courseSyllabusDescription=courseSyllabusDescriptionMapper.selectByPrimaryKey(id);
        if (courseSyllabusDescription.getDeleted()==false){
            return  courseSyllabusDescription;
        }else {
            return null;
        }

    }

    @Override
    public PageInfo<CourseSyllabusDescription> selectPage(int pageNum) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());
        CourseSyllabusDescriptionExample example=new CourseSyllabusDescriptionExample();
        CourseSyllabusDescriptionExample.Criteria criteria=example.createCriteria();
        criteria.andDeletedEqualTo(false);

        List<CourseSyllabusDescription> courseExperimentDescriptions = courseSyllabusDescriptionMapper.selectByExample(example);
        if(courseExperimentDescriptions != null)
            return new PageInfo<>(courseExperimentDescriptions);
        else
            return null;
    }

    @Override
    public PageInfo<CourseSyllabusDescription> selectPageByUniversity(int pageNum, long universityId) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());
        // 创建查询条件
        CourseSyllabusDescriptionExample example = new CourseSyllabusDescriptionExample();
        CourseSyllabusDescriptionExample.Criteria criteria = example.createCriteria();
        criteria.andUniversityIdEqualTo(universityId).andDeletedEqualTo(false);
        // 根据条件查询
        List<CourseSyllabusDescription> courseSyllabusDescriptions = courseSyllabusDescriptionMapper.selectByExample(example);
        if(courseSyllabusDescriptions != null)
            return new PageInfo<>(courseSyllabusDescriptions);
        else
            return null;
    }

    @Override
    public PageInfo<CourseSyllabusDescription> selectPageByCoursesyllabus(int pageNum, long coursesyllabusId) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());
        // 创建查询条件
        CourseSyllabusDescriptionExample example = new CourseSyllabusDescriptionExample();
        CourseSyllabusDescriptionExample.Criteria criteria = example.createCriteria();
        criteria.andCourseSyllabusIdEqualTo(coursesyllabusId).andDeletedEqualTo(false);
        // 根据条件查询
        List<CourseSyllabusDescription> courseSyllabusDescriptions = courseSyllabusDescriptionMapper.selectByExample(example);
        if(courseSyllabusDescriptions != null)
            return new PageInfo<>(courseSyllabusDescriptions);
        else
            return null;
    }

    @Override
    public PageInfo<CourseSyllabusDescription> selectPageByBook(int pageNum, long bookId) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());
        CourseSyllabusDescriptionExample example = new CourseSyllabusDescriptionExample();
        CourseSyllabusDescriptionExample.Criteria criteria = example.createCriteria();
        criteria.andCourseBookIdEqualTo(bookId).andDeletedEqualTo(false);
        List<CourseSyllabusDescription> courseSyllabusDescriptions = courseSyllabusDescriptionMapper.selectByExample(example);
        if(courseSyllabusDescriptions != null)
            return new PageInfo<>(courseSyllabusDescriptions);
        else
            return null;
    }

    @Override
    public List<CourseSyllabusDescription> selectAll() {
        return courseSyllabusDescriptionMapper.selectByExample(null);
    }
}

