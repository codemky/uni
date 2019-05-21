/*
author:  邓凯丰
create:  2019.4.20
modified:  2019.5.11
description：创建CourseCategorySerivice接口,提供给操作者使用
*/
package edu.uni.professionalcourses.service;

import com.github.pagehelper.PageInfo;
import edu.uni.professionalcourses.bean.CourseCategory;

import java.util.List;

public interface CourseCategoryService {
    /**
     * 新增课程类别
     * @param CourseCategory
     * @return
     */
    boolean insert(CourseCategory courseCategory);

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
    boolean update(CourseCategory courseCategory);

    /**
     * 查询课程类别详情
     * @param id
     * @return
     */
    CourseCategory select(long id);

    /**
     * 分页查询所有课程类别
     * @param pageNum
     * @return
     */
    PageInfo<CourseCategory> selectPage(int pageNum);

    /**
     * 分学校分页查询课程类别
     * @param pageNum
     * @param universityId
     * @return
     */
    PageInfo<CourseCategory> selectPageByUniversity(int pageNum, long universityId);


}
