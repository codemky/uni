/*
author:  邓凯丰
create:  2019.4.20
modified:  2019.5.11
description：创建CourseExperimentScheduleSerivice接口,提供给操作者使用
*/
package edu.uni.professionalcourses.service;

import com.github.pagehelper.PageInfo;
import edu.uni.professionalcourses.bean.CourseExperimentSchedule;

import java.util.List;

public interface CourseExperimentScheduleService {
    /**
     * 新增课程实验进度
     * @param
     * @return
     */
    boolean insert(CourseExperimentSchedule courseExperimentSchedule);

    /**
     * 删除课程实验进度
     * @param id
     * @return
     */
    boolean delete(long id);

    /**
     * 修改课程实验进度
     * @param
     * @return
     */
    boolean update(CourseExperimentSchedule courseExperimentSchedule);

    /**
     * 查询课程实验进度详情
     * @param id
     * @return
     */
    CourseExperimentSchedule select(long id);

    /**
     * 分页查询所有课程实验进度
     * @param pageNum
     * @return
     */
    PageInfo<CourseExperimentSchedule> selectPage(int pageNum);

    /**
     * 分学校分页查询课程实验进度
     * @param pageNum
     * @param universityId
     * @return
     */
    PageInfo<CourseExperimentSchedule> selectPageByUniversity(int pageNum, long universityId);

    /**
     * 查找所有课程实验进度
     * @return
     */
    List<CourseExperimentSchedule> selectAll();
}
