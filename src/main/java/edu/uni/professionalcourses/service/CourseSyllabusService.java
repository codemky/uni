/*
author:  邓凯丰
create:  2019.5.10
modified:  2019.5.11
*/
package edu.uni.professionalcourses.service;

import com.github.pagehelper.PageInfo;
import edu.uni.professionalcourses.bean.CourseSyllabus;

import java.util.List;

public interface CourseSyllabusService {
    /**
     * 新增理论教学大纲
     * @param
     * @return
     */
    boolean insert(CourseSyllabus courseSyllabus);

    /**
     * 删除理论教学大纲
     * @param id
     * @return
     */
    boolean delete(long id);

    /**
     * 修改理论教学大纲
     * @param
     * @return
     */
    boolean update(CourseSyllabus courseSyllabus);

    /**
     * 查询理论教学大纲详情
     * @param id
     * @return
     */
    CourseSyllabus select(long id);

    /**
     * 分页查询所有理论教学大纲
     * @param pageNum
     * @return
     */
    PageInfo<CourseSyllabus> selectPage(int pageNum);

    /**
     * 分学校分页查询理论教学大纲
     * @param pageNum
     * @param universityId
     * @return
     */
    PageInfo<CourseSyllabus> selectPageByUniversity(int pageNum, long universityId);
    /**
     * 分课程分页查询理论教学大纲
     * @param pageNum
     * @param universityId
     * @return
     */
    PageInfo<CourseSyllabus> selectPageByCourse(int pageNum, long courseId);

    /**
     * 查找所有理论教学大纲
     * @return
     */
    List<CourseSyllabus> selectAll();
}

