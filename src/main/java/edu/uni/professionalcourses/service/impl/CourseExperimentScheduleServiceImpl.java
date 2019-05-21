/*
author:  邓凯丰
create:  2019.4.20
modified:  2019.5.11
description：实现CourseExperimentScheduleService的接口，供操作者使用的操作
*/
package edu.uni.professionalcourses.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.professionalcourses.bean.CourseExperimentDescriptionExample;
import edu.uni.professionalcourses.bean.CourseExperimentSchedule;
import edu.uni.professionalcourses.bean.CourseExperimentScheduleExample;
import edu.uni.professionalcourses.config.ProfessionalCoursesConfig;
import edu.uni.professionalcourses.mapper.CourseExperimentScheduleMapper;
import edu.uni.professionalcourses.service.CourseExperimentScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseExperimentScheduleServiceImpl implements CourseExperimentScheduleService {
    @Autowired
    private CourseExperimentScheduleMapper courseExperimentScheduleMapper;

    @Autowired
    private ProfessionalCoursesConfig globalConfig;
    @Override
    public boolean insert(CourseExperimentSchedule courseExperimentSchedule) {
        return courseExperimentScheduleMapper.insert(courseExperimentSchedule) > 0 ? true : false;
    }

    @Override
    public boolean delete(long id) {
        CourseExperimentSchedule courseExperimentSchedule=new CourseExperimentSchedule();
        courseExperimentSchedule=courseExperimentScheduleMapper.selectByPrimaryKey(id);
        courseExperimentSchedule.setDeleted(true);


        return courseExperimentScheduleMapper.updateByPrimaryKeySelective(courseExperimentSchedule) > 0 ? true : false;
    }

    @Override
    public boolean update(CourseExperimentSchedule courseExperimentSchedule) {
        CourseExperimentSchedule courseExperimentSchedule1=new CourseExperimentSchedule();
        courseExperimentSchedule1=courseExperimentScheduleMapper.selectByPrimaryKey(courseExperimentSchedule.getId());
        courseExperimentSchedule1.setDeleted(true);
        courseExperimentScheduleMapper.insert(courseExperimentSchedule1);

        return courseExperimentScheduleMapper.updateByPrimaryKeySelective(courseExperimentSchedule) > 0 ? true : false;
    }

    @Override
    public CourseExperimentSchedule select(long id) {
        CourseExperimentSchedule courseExperimentSchedule=new CourseExperimentSchedule();
        courseExperimentSchedule=courseExperimentScheduleMapper.selectByPrimaryKey(id);

        if (courseExperimentSchedule.getDeleted()==false){
            return courseExperimentSchedule;
        }else {
            return null;
        }

    }

    @Override
    public PageInfo<CourseExperimentSchedule> selectPage(int pageNum) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());
        CourseExperimentScheduleExample example=new CourseExperimentScheduleExample();
        CourseExperimentScheduleExample.Criteria criteria=example.createCriteria();
        criteria.andDeletedEqualTo(false);

        List<CourseExperimentSchedule> courseExperimentSchedules = courseExperimentScheduleMapper.selectByExample(example);
        if(courseExperimentSchedules != null)
            return new PageInfo<>(courseExperimentSchedules);
        else
            return null;
    }

    @Override
    public PageInfo<CourseExperimentSchedule> selectPageByUniversity(int pageNum, long universityId) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());
        CourseExperimentScheduleExample example = new CourseExperimentScheduleExample();
        CourseExperimentScheduleExample.Criteria criteria = example.createCriteria();
        criteria.andUniversityIdEqualTo(universityId).andDeletedEqualTo(false);
        List<CourseExperimentSchedule> courseExperimentSchedules = courseExperimentScheduleMapper.selectByExample(example);
        if(courseExperimentSchedules != null)
            return new PageInfo<>(courseExperimentSchedules);
        else
            return null;
    }

    @Override
    public List<CourseExperimentSchedule> selectAll() {
        return courseExperimentScheduleMapper.selectByExample(null);
    }
}

