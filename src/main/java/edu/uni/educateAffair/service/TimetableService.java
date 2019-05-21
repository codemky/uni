package edu.uni.educateAffair.service;

import edu.uni.educateAffair.bean.Timetable;

import java.util.List;

/**
 * @Author:梁俊杰
 * @Description:作息表
 * @Date:Created in 22:20 2019/5/10
 */
public interface TimetableService {
    /**
        *@Author:梁俊杰
        *@Description:查看所有作息表
        *@Date:Created in {22:21} {2019/5/10}
    */
    List<Timetable> selectAll();

}
