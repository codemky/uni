/*
author:  邓凯丰
create:  2019.5.10
modified:  2019.5.11
*/
package edu.uni.professionalcourses.service;

import com.github.pagehelper.PageInfo;
import edu.uni.professionalcourses.bean.CourseSyllabusDescription;

import java.util.List;

public interface CourseSyllabusDescriptionService {
    /**
     * 新增理论教学内容
     * @param
     * @return
     */
    boolean insert(CourseSyllabusDescription courseSyllabusDescription);

    /**
     * 删除理论教学内容
     * @param id
     * @return
     */
    boolean delete(long id);

    /**
     * 修改理论教学内容
     * @param
     * @return
     */
    boolean update(CourseSyllabusDescription courseSyllabusDescription);

    /**
     * 查询理论教学内容
     * @param id
     * @return
     */
    CourseSyllabusDescription select(long id);

    /**
     * 分页查询所有理论教学内容
     * @param pageNum
     * @return
     */
    PageInfo<CourseSyllabusDescription> selectPage(int pageNum);

    /**
     * 分学校分页查询理论教学内容
     * @param pageNum
     * @param universityId
     * @return
     */
    PageInfo<CourseSyllabusDescription> selectPageByUniversity(int pageNum, long universityId);
    /**
     * 分理论教学大纲分页查询理论教学内容
     * @param pageNum
     * @param coursesyllabusId
     * @return
     */
    PageInfo<CourseSyllabusDescription> selectPageByCoursesyllabus(int pageNum, long coursesyllabusId);
    /**
     * 分参考书目分页查询理论教学内容
     * @param pageNum
     * @param bookId
     * @return
     */
    PageInfo<CourseSyllabusDescription> selectPageByBook(int pageNum, long bookId);
    /**
     * 查找所有理论教学内容
     * @return
     */
    List<CourseSyllabusDescription> selectAll();
}

