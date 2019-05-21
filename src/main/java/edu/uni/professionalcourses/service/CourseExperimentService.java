/*
author:  邓凯丰
create:  2019.4.20
modified:  2019.5.11
description：创建CourseExperimentSerivice接口,提供给操作者使用
*/
package edu.uni.professionalcourses.service;

import com.github.pagehelper.PageInfo;
import edu.uni.professionalcourses.bean.CourseExperiment;

import java.util.List;

public interface CourseExperimentService {
    /**
     * 新增实验教学大纲
     * @param
     * @return
     */
    boolean insert(CourseExperiment courseExperiment);

    /**
     * 删除实验教学大纲
     * @param id
     * @return
     */
    boolean delete(long id);

    /**
     * 修改实验教学大纲
     * @param
     * @return
     */
    boolean update(CourseExperiment courseExperiment);

    /**
     * 查询课程实验教学大纲
     * @param id
     * @return
     */
    CourseExperiment select(long id);

    /**
     * 分页查询所有实验教学大纲
     * @param pageNum
     * @return
     */
    PageInfo<CourseExperiment> selectPage(int pageNum);

    /**
     * 分学校分页查询实验教学大纲
     * @param pageNum
     * @param universityId
     * @return
     */
    PageInfo<CourseExperiment> selectPageByUniversity(int pageNum, long universityId);
    /**
     * 分课程分页查询实验教学大纲
     * @param pageNum
     * @param courseId
     * @return
     */

    PageInfo<CourseExperiment> selectPageByCourse(int pageNum, long CourseId);

    /**
     * 查找所有实验教学大纲
     * @return
     */
    List<CourseExperiment> selectAll();
}

