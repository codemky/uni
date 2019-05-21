/*
author:  邓凯丰
create:  2019.4.20
modified:  2019.5.11
description：实现CourseCategoryService的接口，供操作者使用的操作
*/
package edu.uni.professionalcourses.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.professionalcourses.bean.CourseCategory;
import edu.uni.professionalcourses.bean.CourseCategoryExample;
import edu.uni.professionalcourses.config.ProfessionalCoursesConfig;
import edu.uni.professionalcourses.mapper.CourseCategoryMapper;
import edu.uni.professionalcourses.service.CourseCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseCategoryServiceImpl implements CourseCategoryService {
    @Autowired
    private CourseCategoryMapper courseCategoryMapper;

    @Autowired
    private ProfessionalCoursesConfig globalConfig;
    @Override
    public boolean insert(CourseCategory courseCategory) {

        return courseCategoryMapper.insert(courseCategory) > 0 ? true : false;
    }

    @Override
    public boolean delete(long id) {
        CourseCategory courseCategory=new CourseCategory();
        courseCategory=courseCategoryMapper.selectByPrimaryKey(id);
        courseCategory.setDeleted(true);

        return courseCategoryMapper.updateByPrimaryKeySelective(courseCategory) > 0 ? true : false;
    }

    @Override
    public boolean update(CourseCategory courseCategory) {
        CourseCategory courseCategory1=new CourseCategory();
        courseCategory1=courseCategoryMapper.selectByPrimaryKey(courseCategory.getId());
        courseCategory1.setDeleted(true);
        courseCategoryMapper.insert(courseCategory1);


        return courseCategoryMapper.updateByPrimaryKeySelective(courseCategory) > 0 ? true : false;
    }

    @Override
    public CourseCategory select(long id) {
        CourseCategory courseCategory=new CourseCategory();
        courseCategory=courseCategoryMapper.selectByPrimaryKey(id);

        if (courseCategory.getDeleted()==false){
            return courseCategory;
        }else {
            return null;
        }
    }

    @Override
    public PageInfo<CourseCategory> selectPage(int pageNum) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());
        CourseCategoryExample example=new CourseCategoryExample();
        CourseCategoryExample.Criteria criteria=example.createCriteria();
        criteria.andDeletedEqualTo(false);

        List<CourseCategory> courseCategorys = courseCategoryMapper.selectByExample(example);
        if(courseCategorys != null)
            return new PageInfo<>(courseCategorys);
        else
            return null;
    }

    @Override
    public PageInfo<CourseCategory> selectPageByUniversity(int pageNum, long universityId) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());
        CourseCategoryExample example = new CourseCategoryExample();
        CourseCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andUniversityIdEqualTo(universityId).andDeletedEqualTo(false);
        List<CourseCategory> courseCategorys = courseCategoryMapper.selectByExample(example);
        if(courseCategorys != null)
            return new PageInfo<>(courseCategorys);
        else
            return null;
    }


}