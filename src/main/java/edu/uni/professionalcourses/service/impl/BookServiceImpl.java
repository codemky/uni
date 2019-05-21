package edu.uni.professionalcourses.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.example.config.ExampleConfig;
import edu.uni.professionalcourses.bean.*;
import edu.uni.professionalcourses.mapper.BookMapper;
import edu.uni.professionalcourses.mapper.CourseBookMapper;
import edu.uni.professionalcourses.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
author:  曹中耀
create:  2019.4.26
modified:  2019.4.26
description：对BookService的方法的实现
*/
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private CourseBookMapper courseBookMapper;
    @Autowired
    private ExampleConfig globalConfig;
    /**
     * 新增Book信息
     * @param book
     * @return
     */
    @Override
    public boolean insert(Book book, Course course) {
        if(bookMapper.insert(book)<=0)
        {
            return false;
        }
        //return bookMapper.insert(book) > 0 ? true : false;
        CourseBook courseBook=new CourseBook();
        courseBook.setBookId(book.getId());
        courseBook.setUniversityId(book.getUniversityId());
        courseBook.setDatetime(book.getDatetime());
        courseBook.setByWho(book.getByWho());
        courseBook.setDeleted(book.getDeleted());
        courseBook.setCourseId(course.getId());
        if(courseBookMapper.insert(courseBook)<=0){
            return false;
        }
        else return true;
       // return bookMapper.insert(book) > 0 ? true : false;
    }

    /**
     * 删除Book信息
     * @param id
     * @return
     */
    @Override
    public boolean delete(long id) {
        Book book1=new Book();
        book1=bookMapper.selectByPrimaryKey(id);
        book1.setDeleted(true);
        if(bookMapper.updateByPrimaryKeySelective(book1)<=0)
        {
            return false;
        }
        // 创建查询条件
        CourseBookExample example = new CourseBookExample();
        CourseBookExample.Criteria criteria = example.createCriteria();
        criteria.andBookIdEqualTo(book1.getId()).andDeletedEqualTo(false);
        List<CourseBook> courseBooks=courseBookMapper.selectByExample(example);
        CourseBook courseBook=courseBooks.get(0);
        courseBookMapper.deleteByPrimaryKey(courseBook.getId());
        /*if(courseBookMapper.updateByPrimaryKeySelective(courseBook)<0)
        {
            return false;
        }else*/  return true;
    }

    /**
     * 更新Book信息
     * @param book
     * @return
     */
    public boolean update(Book book){//新
        //保存旧信息
        Book book1=new Book();
        book1=bookMapper.selectByPrimaryKey(book.getId());
        book1.setDeleted(true);

        //更新信息
        if(bookMapper.updateByPrimaryKeySelective(book)<=0)
        {
            return false;
        }
        if(bookMapper.insert(book1)<0)
        {
            return false;
        }
        return true;
    }

    /**
     * 查找Book信息
     * @param id
     * @return
     */
    @Override
    public Book select(long id) {
        Book book1=bookMapper.selectByPrimaryKey(id);
        //        判断如果输入id查找的信息有效时显示信息，无效显示null
        if(book1.getDeleted())
        {
            return null;
        }
        else return book1;
       // return bookMapper.selectByPrimaryKey(id);
    }

    /**
     * 查找所有Book信息
     * @return
     */
    @Override
    public List<Book> selectAll() {
        BookExample example=new BookExample();
        BookExample.Criteria criteria=example.createCriteria();
        criteria.andDeletedEqualTo(false);
        return bookMapper.selectByExample(example);
    }
    /**
     * 分页查询返回所有Book信息
     * @param pageNum
     * @return
     */
    public PageInfo<Book> selectPage(int pageNum){
        PageHelper.startPage(pageNum, globalConfig.getPageSize());
        BookExample example = new BookExample();
        BookExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(false);// 开启分页查询，第一次切仅第一次查询时生效
        List<Book> books = bookMapper.selectByExample(example);   //  查找有效book信息
        if(books != null)
            return new PageInfo<>(books);
        else
            return null;
    }
    /**
     *  根据书籍、教材、参考书表学校id分页查询并返回Book信息
     * @param pageNum
     * @param university_Id
     * @return
     */
    public PageInfo<Book> selectPageByUniversityID(int pageNum, long university_Id){
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        BookExample example = new BookExample();
        BookExample.Criteria criteria = example.createCriteria();
        criteria.andUniversityIdEqualTo(university_Id).andDeletedEqualTo(false);
        // 根据条件查询
        List<Book> products = bookMapper.selectByExample(example);   //  无条件查找商品
        if(products != null)
            return new PageInfo<>(products);
        else
            return null;
    }
}
