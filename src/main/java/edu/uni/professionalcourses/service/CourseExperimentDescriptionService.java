/*
author:  邓凯丰
create:  2019.4.20
modified:  2019.5.11
description：创建CourseExperimentDescriptionSerivice接口,提供给操作者使用
*/
package edu.uni.professionalcourses.service;

import com.github.pagehelper.PageInfo;
import edu.uni.professionalcourses.bean.CourseExperimentDescription;

import java.util.List;

public interface CourseExperimentDescriptionService {
    /**
     * 新增实验教学内容
     * @param CourseExperimentDescription
     * @return
     */
    boolean insert(CourseExperimentDescription courseExperimentDescription);

    /**
     * 删除实验教学内容
     * @param id
     * @return
     */
    boolean delete(long id);


    boolean update(CourseExperimentDescription courseExperimentDescription);

    /**
     * 查询实验教学内容
     * @param id
     * @return
     */
    CourseExperimentDescription select(long id);

    /**
     * 分页查询所有实验教学内容
     * @param pageNum
     * @return
     */
    PageInfo<CourseExperimentDescription> selectPage(int pageNum);

    /**
     * 分学校分页查询实验教学内容
     * @param pageNum
     * @param universityId
     * @return
     */
    PageInfo<CourseExperimentDescription> selectPageByUniversity(int pageNum, long universityId);
    /**
     * 分课程分页查询实验教学内容
     * @param pageNum
     * @param courseID
     * @return
     */
    PageInfo<CourseExperimentDescription> selectPageByCourseexperiment(int pageNum, long courseexperimentId);
    /**
     * 分课程书籍分页查询实验教学内容
     * @param pageNum
     * @param universityId
     * @return
     */
    PageInfo<CourseExperimentDescription> selectPageByCoursebook(int pageNum, long coursebookId);

    /**
     * 查找所有实验教学内容
     * @return
     */
    List<CourseExperimentDescription> selectAll();
}

