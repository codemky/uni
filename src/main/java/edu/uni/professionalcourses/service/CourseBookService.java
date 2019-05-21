package edu.uni.professionalcourses.service;

import com.github.pagehelper.PageInfo;
import edu.uni.professionalcourses.bean.CourseBook;

import java.util.List;
/**
author:  曹中耀
create:  2019.4.24
modified:  2019.4.26
description：创建CourseBookSerivice接口
*/
public interface CourseBookService {
    /**
     * 新增CourseBook信息
     * @param courseBook
     * @return
     */
    boolean insert(CourseBook courseBook);

    /**
     * 删除CourseBook信息
     * @param id
     * @return
     */
    boolean delete(long id);

    /**
     * 更新CourseBook信息
     * @param courseBook
     * @return
     */
    boolean update(CourseBook courseBook);

    /**
     * 根据CourseBook的id查找CourseBook信息
     * @param id
     * @return
     */
    CourseBook select(long id);

    /**
     * 分页查询所有CourseBook信息
     * @param pageNum
     * @return
     */
    PageInfo<CourseBook> selectPage(int pageNum);


    /**
     * 根据课程参考书表学校id分页查询
     * @param pageNum
     * @param university_id
     * @return
     */
    PageInfo<CourseBook> selectPageByUniversityID(int pageNum, long university_id);
    /**
     * 根据课程参考书表课程id分页查询
     * @param pageNum
     * @param course_id
     * @return
     */
    PageInfo<CourseBook> selectPageByCourseID(int pageNum, long course_id);
    /**
     * 根据课程参考书表课本id分页查询
     * @param pageNum
     * @param book_id
     * @return
     */
    PageInfo<CourseBook> selectPageByBookID(int pageNum, long book_id);
    /**
     * 查找所有信息
     * @return
     */
    List<CourseBook> selectAll();
}
