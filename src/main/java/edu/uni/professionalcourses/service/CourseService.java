/*
author:  邓凯丰
create:  2019.4.20
modified:  2019.5.11
description：创建CourseSerivice接口,提供给操作者使用
*/
package edu.uni.professionalcourses.service;

import com.github.pagehelper.PageInfo;
import edu.uni.professionalcourses.bean.Course;

import java.util.List;

public interface CourseService {
    /**
     * 新增课程
     * @param department
     * @return
     */
    boolean insert(Course course);

    /**
     * 删除课程
     * @param id
     * @return
     */
    boolean delete(long id);

    /**
     * 修改课程
     * @param department
     * @return
     */
    boolean update(Course course);

    /**
     * 查询课程详情
     * @param id
     * @return
     */
    Course select(long id);

    /**
     * 分页查询所有课程
     * @param pageNum
     * @return
     */
    PageInfo<Course> selectPage(int pageNum);

    /**
     * 分学校分页查询课程
     * @param pageNum
     * @param universityId
     * @return
     */
    PageInfo<Course> selectPageByUniversity(int pageNum, long universityId);

    Course selectBynumber(String number);

    List<Course> selectAll();
}
