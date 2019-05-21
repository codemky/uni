/*
author:  邓凯丰
create:  2019.5.10
modified:  2019.5.11
*/
package edu.uni.professionalcourses.service;

import com.github.pagehelper.PageInfo;
import edu.uni.professionalcourses.bean.ExamType;

import java.util.List;

public interface ExamTypeService {

    boolean insert(ExamType examType);


    boolean delete(long id);


    boolean update(ExamType examType);


    ExamType select(long id);


    PageInfo<ExamType> selectPage(int pageNum);


    PageInfo<ExamType> selectPageByUniversity(int pageNum, long universityId);

    List<ExamType> selectAll();


}


