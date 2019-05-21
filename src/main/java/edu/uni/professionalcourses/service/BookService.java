package edu.uni.professionalcourses.service;

import com.github.pagehelper.PageInfo;
import edu.uni.professionalcourses.bean.Book;
import edu.uni.professionalcourses.bean.Course;

import java.util.List;

/**
author:  曹中耀
create:  2019.4.26
modified:  2019.5.11
description：创建BookSerivice接口
*/
public interface BookService {
    /**
     * 新增Book信息
     * @param book
     * @return
     */
    boolean insert(Book book, Course course);

    /**
     * 删除Book信息
     * @param id
     * @return
     */
    boolean delete(long id);

    /**
     * 更新Book信息
     * @param book
     * @return
     */
    boolean update(Book book);

    /**
     * 根据Book的id查找Book信息
     * @param id
     * @return
     */
    Book select(long id);

    /**
     * 分页查询所有Book信息
     * @param pageNum
     * @return
     */
    PageInfo<Book> selectPage(int pageNum);

    /**
     * 根据课程参考书表学校id分页查询
     * @param pageNum
     * @param university_id
     * @return
     */
    PageInfo<Book> selectPageByUniversityID(int pageNum, long university_id);
    /**
     * 查找所有信息
     * @return
     */
    List<Book> selectAll();
}
