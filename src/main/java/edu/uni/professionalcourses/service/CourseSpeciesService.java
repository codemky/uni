/*
author:  邓凯丰
create:  2019.4.20
modified:  2019.5.11
description：创建CourseSpeciesSerivice接口,提供给操作者使用
*/
package edu.uni.professionalcourses.service;

import com.github.pagehelper.PageInfo;
import edu.uni.professionalcourses.bean.CourseSpecies;

import java.util.List;

public interface CourseSpeciesService {
    /**
     * 新增课程种类
     * @param
     * @return
     */
    boolean insert(CourseSpecies courseSpecies);

    /**
     * 删除课程种类
     * @param id
     * @return
     */
    boolean delete(long id);

    /**
     * 修改课程种类
     * @param
     * @return
     */
    boolean update(CourseSpecies courseSpecies);

    /**
     * 查询课程详情
     * @param id
     * @return
     */
    CourseSpecies select(long id);

    /**
     * 分页查询所有课程
     * @param pageNum
     * @return
     */
    PageInfo<CourseSpecies> selectPage(int pageNum);

    /**
     * 分学校分页查询课程种类
     * @param pageNum
     * @param universityId
     * @return
     */
    PageInfo<CourseSpecies> selectPageByUniversity(int pageNum, long universityId);

    /**
     * 查找所有课程种类
     * @return
     */
    List<CourseSpecies> selectAll();
}
