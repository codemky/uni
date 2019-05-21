/*
author:  邓凯丰
create:  2019.4.20
modified:  2019.5.11
description：创建CourseHasResourceSerivice接口,提供给操作者使用
*/
package edu.uni.professionalcourses.service;

import com.github.pagehelper.PageInfo;
import edu.uni.professionalcourses.bean.CourseHasResource;

import java.util.List;

public interface CourseHasResourceService {
    /**
     * 新增课程课程-资源对应表
     * @param
     * @return
     */
    boolean insert(CourseHasResource courseHasResource);

    /**
     * 删除课程-资源对应表
     * @param id
     * @return
     */
    boolean delete(long id);


    boolean update(CourseHasResource courseHasResource);

    /**
     * 查询课程-资源对应表
     * @param id
     * @return
     */
    CourseHasResource select(long id);

    /**
     * 分页查询所有课程-资源对应表
     * @param pageNum
     * @return
     */
    PageInfo<CourseHasResource> selectPage(int pageNum);

    /**
     * 分学校分页查询课程-资源对应表
     * @param pageNum
     * @param universityId
     * @return
     */
    PageInfo<CourseHasResource> selectPageByUniversity(int pageNum, long universityId);
    /**
     * 分课程分页查询课程-资源对应表
     * @param pageNum
     * @param courseId
     * @return
     */
    PageInfo<CourseHasResource> selectPageByCourse(int pageNum, long courseId);
    /**
     * 分学校分页查询课程-资源对应表
     * @param pageNum
     * @param courseresourceId
     * @return
     */
    PageInfo<CourseHasResource> selectPageByCourseresource(int pageNum, long courseresourceId);

    /**
     * 查找所有课程
     * @return
     */
    List<CourseHasResource> selectAll();
}

