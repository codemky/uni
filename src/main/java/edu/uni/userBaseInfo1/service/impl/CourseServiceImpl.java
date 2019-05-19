/*
author:  邓凯丰
create:  2019.4.20
modified:  2019.5.03
description：实现CourseService的接口，供操作者使用的操作
*/
package edu.uni.userBaseInfo1.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.userBaseInfo1.bean.Course;
import edu.uni.userBaseInfo1.mapper.CourseMapper;
import edu.uni.userBaseInfo1.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;

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



}
