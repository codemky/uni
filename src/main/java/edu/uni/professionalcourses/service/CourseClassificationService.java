/*
author:  邓凯丰
create:  2019.4.20
modified:  2019.5.11
description：创建CourseClassificationSerivice接口,提供给操作者使用
*/
package edu.uni.professionalcourses.service;

import com.github.pagehelper.PageInfo;
import edu.uni.professionalcourses.bean.CourseClassification;

import java.util.List;

public interface CourseClassificationService {
    /**
     * 新增课程类别
     * @param CourseCategory
     * @return
     */
    boolean insert(CourseClassification courseClassification);

    /**
     * 删除课程类别
     * @param id
     * @return
     */
    boolean delete(long id);

    /**
     * 修改课程类别
     * @param CourseCategory
     * @return
     */
    boolean update(CourseClassification courseClassification);

    /**
     * 查询课程类别详情
     * @param id
     * @return
     */
    CourseClassification select(long id);

    /**
     * 分页查询所有课程类别
     * @param pageNum
     * @return
     */
    PageInfo<CourseClassification> selectPage(int pageNum);

    /**
     * 分学校分页查询课程类别
     * @param pageNum
     * @param universityId
     * @return
     */
    PageInfo<CourseClassification> selectPageByUniversity(int pageNum, long universityId);

    /**
     * 分课程分页查询课程类别
     * @param pageNum
     * @param courseId
     * @return
     */

    List<CourseClassification> selectAll();
}

