/*
author:  邓凯丰
create:  2019.4.20
modified:  2019.5.11
description：创建CourseResourceSerivice接口,提供给操作者使用
*/
package edu.uni.professionalcourses.service;

import com.github.pagehelper.PageInfo;
import edu.uni.professionalcourses.bean.CourseResource;

import java.util.List;

public interface CourseResourceService {
    /**
     * 新增课程资源
     * @param
     * @return
     */
    boolean insert(CourseResource courseResource);

    /**
     * 删除课程资源
     * @param id
     * @return
     */
    boolean delete(long id);

    /**
     * 修改课程资源
     * @param department
     * @return
     */
    boolean update(CourseResource courseResource);

    /**
     * 查询课程资源详情
     * @param id
     * @return
     */
    CourseResource select(long id);

    /**
     * 分页查询所有课程资源
     * @param pageNum
     * @return
     */
    PageInfo<CourseResource> selectPage(int pageNum);

    /**
     * 分学校分页查询课程资源
     * @param pageNum
     * @param universityId
     * @return
     */
    PageInfo<CourseResource> selectPageByUniversity(int pageNum, long universityId);

    /**
     * 查找所有课程资源
     * @return
     */
    List<CourseResource> selectAll();
}

