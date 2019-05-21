/*
author:  邓凯丰
create:  2019.4.20
modified:  2019.5.11
description：实现CourseService的接口，供操作者使用的操作
*/
package edu.uni.professionalcourses.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.professionalcourses.bean.*;
import edu.uni.professionalcourses.config.ProfessionalCoursesConfig;
import edu.uni.professionalcourses.mapper.CourseMapper;
import edu.uni.professionalcourses.service.CourseService;
import edu.uni.professionalcourses.mapper.SpecialtyCourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private SpecialtyCourseMapper specialtyCourseMapper;

    @Autowired
    private ProfessionalCoursesConfig globalConfig;
    @Override
    public boolean insert(Course course) {


        SpecialtyCourse specialtyCourse=new SpecialtyCourse();
        specialtyCourse.setCourseId(course.getId());
        specialtyCourse.setSpecialtyId(course.getSpeciesId());
        specialtyCourse.setUniversityId(course.getUniversityId());
        specialtyCourse.setByWho(course.getByWho());
        specialtyCourse.setDatetime(course.getDatetime());
        specialtyCourse.setDeleted(course.getDeleted());

        specialtyCourseMapper.insert(specialtyCourse);

        return courseMapper.insert(course) > 0 ? true : false;
    }

    @Override
    public boolean delete(long id) {

        Course course1=new Course();
        course1=courseMapper.selectByPrimaryKey(id);
        course1.setDeleted(true);
        courseMapper.updateByPrimaryKeySelective(course1);


        SpecialtyCourseExample example = new SpecialtyCourseExample();
        SpecialtyCourseExample.Criteria criteria = example.createCriteria();
        criteria.andCourseIdEqualTo(course1.getId());
        List<SpecialtyCourse> specialtyCourses = specialtyCourseMapper.selectByExample(example);

        SpecialtyCourse specialtyCourse1=specialtyCourses.get(0);

        specialtyCourse1.setDeleted(true);
        return specialtyCourseMapper.updateByPrimaryKeySelective(specialtyCourse1) > 0 ? true : false;
    }

    @Override
    public boolean update(Course course) {
        Course course1=new Course();
        course1=courseMapper.selectByPrimaryKey(course.getId());
        course1.setDeleted(true);
       courseMapper.insert(course1);


        return courseMapper.updateByPrimaryKeySelective(course) > 0 ? true : false;
    }

    @Override
    public Course select(long id) {
        Course course=new Course();
        course=courseMapper.selectByPrimaryKey(id);
        if (course.getDeleted()==false){
            return course;
        }else {
            return  null;
        }
    }

    @Override
    public PageInfo<Course> selectPage(int pageNum) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());

        CourseExample example=new CourseExample();
        CourseExample.Criteria criteria=example.createCriteria();
        criteria.andDeletedEqualTo(false);
        List<Course> courses = courseMapper.selectByExample(example);
        if(courses != null)
            return new PageInfo<>(courses);
        else
            return null;
    }

    @Override
    public PageInfo<Course> selectPageByUniversity(int pageNum, long universityId) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());
        // 创建查询条件
        CourseExample example = new CourseExample();
        CourseExample.Criteria criteria = example.createCriteria();
        criteria.andUniversityIdEqualTo(universityId).andDeletedEqualTo(false);
        // 根据条件查询
        List<Course> courses = courseMapper.selectByExample(example);
        if(courses != null)
            return new PageInfo<>(courses);
        else
            return null;
    }

    @Override
    public Course selectBynumber(String number){
       CourseExample example=new CourseExample();
       CourseExample.Criteria  criteria= example.createCriteria();
       criteria.andNumberEqualTo(number);

       List<Course> courses=courseMapper.selectByExample(example);
       Course course=courses.get(0);
       return course;
    }

    @Override
    public List<Course> selectAll(){
        CourseExample example=new CourseExample();
        CourseExample.Criteria criteria=example.createCriteria();
        criteria.andDeletedEqualTo(false);
        List<Course> courses=courseMapper.selectByExample(example);
        return  courses;
    }

}
