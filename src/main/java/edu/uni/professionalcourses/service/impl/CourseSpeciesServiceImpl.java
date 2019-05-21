/*
author:  邓凯丰
create:  2019.4.20
modified:  2019.5.11
description：实现CourseSpeciesService的接口，供操作者使用的操作
*/
package edu.uni.professionalcourses.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.professionalcourses.bean.CourseSpecies;
import edu.uni.professionalcourses.bean.CourseSpeciesExample;
import edu.uni.professionalcourses.config.ProfessionalCoursesConfig;
import edu.uni.professionalcourses.mapper.CourseSpeciesMapper;
import edu.uni.professionalcourses.service.CourseSpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseSpeciesServiceImpl implements CourseSpeciesService {
    @Autowired
    private CourseSpeciesMapper courseSpeciesMapper;

    @Autowired
    private ProfessionalCoursesConfig globalConfig;
    @Override
    public boolean insert(CourseSpecies courseSpecies) {
        return courseSpeciesMapper.insert(courseSpecies) > 0 ? true : false;
    }

    @Override
    public boolean delete(long id) {
        CourseSpecies courseSpecies=new CourseSpecies();
        courseSpecies=courseSpeciesMapper.selectByPrimaryKey(id);
        courseSpecies.setDeleted(true);

        return courseSpeciesMapper.updateByPrimaryKeySelective(courseSpecies) > 0 ? true : false;
    }

    @Override
    public boolean update(CourseSpecies courseSpecies) {
        CourseSpecies courseSpecies1=new CourseSpecies();
        courseSpecies1=courseSpeciesMapper.selectByPrimaryKey(courseSpecies.getId());
        courseSpecies1.setDeleted(true);
        courseSpeciesMapper.insert(courseSpecies1);

        return courseSpeciesMapper.updateByPrimaryKeySelective(courseSpecies) > 0 ? true : false;
    }

    @Override
    public CourseSpecies select(long id) {
        CourseSpecies courseSpecies=courseSpeciesMapper.selectByPrimaryKey(id);
        if(courseSpecies.getDeleted()==false){
            return courseSpecies;
        }else {
            return null;
        }

    }

    @Override
    public PageInfo<CourseSpecies> selectPage(int pageNum) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());
        CourseSpeciesExample example=new CourseSpeciesExample();
        CourseSpeciesExample.Criteria criteria=example.createCriteria();
        criteria.andDeletedEqualTo(false);

        List<CourseSpecies> courseSpeciess = courseSpeciesMapper.selectByExample(example);
        if(courseSpeciess != null)
            return new PageInfo<>(courseSpeciess);
        else
            return null;
    }

    @Override
    public PageInfo<CourseSpecies> selectPageByUniversity(int pageNum, long universityId) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());
        // 创建查询条件
        CourseSpeciesExample example = new CourseSpeciesExample();
        CourseSpeciesExample.Criteria criteria = example.createCriteria();
        criteria.andUniversityIdEqualTo(universityId).andDeletedEqualTo(false);
        // 根据条件查询
        List<CourseSpecies> courses = courseSpeciesMapper.selectByExample(example);
        if(courses != null)
            return new PageInfo<>(courses);
        else
            return null;
    }

    @Override
    public List<CourseSpecies> selectAll() {
        return courseSpeciesMapper.selectByExample(null);
    }
}
