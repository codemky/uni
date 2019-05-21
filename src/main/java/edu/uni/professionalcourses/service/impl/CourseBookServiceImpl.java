package edu.uni.professionalcourses.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.example.config.ExampleConfig;
import edu.uni.professionalcourses.bean.CourseBook;
import edu.uni.professionalcourses.bean.CourseBookExample;
import edu.uni.professionalcourses.mapper.CourseBookMapper;
import edu.uni.professionalcourses.service.CourseBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
/**
author:  曹中耀
create:  2019.4.24
modified:  2019.4.26
description：对CourseBookService的方法的实现
*/
public class CourseBookServiceImpl implements CourseBookService {
    @Autowired
    private CourseBookMapper courseBookMapper;
    @Autowired
    private ExampleConfig globalConfig;
    /**
     * 新增CourseBook信息
     * @param courseBook
     * @return
     */
    @Override
    public boolean insert(CourseBook courseBook) {

        return courseBookMapper.insert(courseBook) > 0 ? true : false;
    }

    /**
     * 删除CourseBook信息
     * @param id
     * @return
     */
    @Override
    public boolean delete(long id) {

        return courseBookMapper.deleteByPrimaryKey(id) > 0 ? true : false;
    }

    /**
     * 更新CourseBook信息
     * @param courseBook
     * @return
     */
    public boolean update(CourseBook courseBook){
        return courseBookMapper.updateByPrimaryKeySelective(courseBook) > 0 ? true : false;
    }

    /**
     * 查找CourseBook信息
     * @param id
     * @return
     */
    @Override
    public CourseBook select(long id) {

        return courseBookMapper.selectByPrimaryKey(id);
    }

    /**
     * 查找所有CourseBook信息
     * @return
     */
    @Override
    public List<CourseBook> selectAll() {

        return courseBookMapper.selectByExample(null);
    }
    /**
     * 分页查询返回所有CourseBook信息
     * @param pageNum
     * @return
     */
    public PageInfo<CourseBook> selectPage(int pageNum){
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        List<CourseBook> courseBooks = courseBookMapper.selectByExample(null);   //  无条件查找商品
        if(courseBooks != null)
            return new PageInfo<>(courseBooks);
        else
            return null;
    }


    /**
     *  根据课程参考书表学校id分页查询并返回CourseBook信息
     * @param pageNum
     * @param university_Id
     * @return
     */
    public PageInfo<CourseBook> selectPageByUniversityID(int pageNum, long university_Id){
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        CourseBookExample example = new CourseBookExample();
        CourseBookExample.Criteria criteria = example.createCriteria();
        criteria.andUniversityIdEqualTo(university_Id).andDeletedEqualTo(false);
        // 根据条件查询
        List<CourseBook> products = courseBookMapper.selectByExample(example);   //  无条件查找商品
        if(products != null)
            return new PageInfo<>(products);
        else
            return null;
    }
    /**
     *  根据课程参考书表课程id分页查询并返回CourseBook信息
     * @param pageNum
     * @param course_Id
     * @return
     */
    public PageInfo<CourseBook> selectPageByCourseID(int pageNum, long course_Id){
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        CourseBookExample example = new CourseBookExample();
        CourseBookExample.Criteria criteria = example.createCriteria();
        criteria.andCourseIdEqualTo(course_Id).andDeletedEqualTo(false);
        // 根据条件查询
        List<CourseBook> products = courseBookMapper.selectByExample(example);   //  无条件查找商品
        if(products != null)
            return new PageInfo<>(products);
        else
            return null;
    }
    /**
     *  根据课程参考书表课本id分页查询并返回CourseBook信息
     * @param pageNum
     * @param book_Id
     * @return
     */
    public PageInfo<CourseBook> selectPageByBookID(int pageNum, long book_Id){
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        CourseBookExample example = new CourseBookExample();
        CourseBookExample.Criteria criteria = example.createCriteria();
        criteria.andBookIdEqualTo(book_Id).andDeletedEqualTo(false);
        // 根据条件查询
        List<CourseBook> products = courseBookMapper.selectByExample(example);   //  无条件查找商品
        if(products != null)
            return new PageInfo<>(products);
        else
            return null;
    }
}
