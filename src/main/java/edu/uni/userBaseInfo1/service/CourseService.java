/*
author:  邓凯丰
create:  2019.4.20
modified:  2019.5.03
description：创建CourseSerivice接口,提供给操作者使用
*/
package edu.uni.userBaseInfo1.service;

import com.github.pagehelper.PageInfo;
import edu.uni.userBaseInfo1.bean.Course;

import java.util.List;

public interface CourseService {
    /**
     * 查询课程详情
     * @param id
     * @return
     */
    Course select(long id);
}
