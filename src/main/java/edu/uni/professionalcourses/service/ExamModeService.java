/*
author:  邓凯丰
create:  2019.5.10
modified:  2019.5.11
*/
package edu.uni.professionalcourses.service;

import com.github.pagehelper.PageInfo;
import edu.uni.professionalcourses.bean.ExamMode;

import java.util.List;

public interface ExamModeService {

    boolean insert(ExamMode examMode);


    boolean delete(long id);


    boolean update(ExamMode examMode);


    ExamMode select(long id);


    PageInfo<ExamMode> selectPage(int pageNum);


    PageInfo<ExamMode> selectPageByUniversity(int pageNum, long universityId);

    List<ExamMode> selectAll();


}

