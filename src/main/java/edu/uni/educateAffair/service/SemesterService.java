package edu.uni.educateAffair.service;

import edu.uni.educateAffair.bean.Canlendar;
import edu.uni.educateAffair.bean.Semester;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author:梁俊杰
 * @Description:
 * @Date:Created in 8:23 2019/4/29
 */
public interface SemesterService {

    /**
        *@Author:梁俊杰
        *@Description:新增学期
        *@Date:Created in {8:23} {2019/4/29}
    */
    boolean insert(Semester semester, List<Canlendar> canlendarList) throws SQLException;
    /**
        *@Author:梁俊杰
        *@Description:根据ID查找学期
        *@Date:Created in {13:52} {2019/4/30}
    */
    Semester selectById(Long id);

    /**
        *@Author:梁俊杰
        *@Description:查找所有学期信息
        *@Date:Created in {15:51} {2019/4/30}
    */
    List<Semester> selectAll();

    /**
        *@Author:梁俊杰
        *@Description:根据ID修改学期
        *@Date:Created in {22:00} {2019/4/30}
    */
    boolean update(Semester semester) throws SQLException;

    /**
        *@Author:梁俊杰
        *@Description:根据ID删除学期
        *@Date:Created in {16:29} {2019/5/3}
    */
    boolean delete(Long id) throws SQLException;

    /**
        *@Author:梁俊杰
        *@Description:学期id加个数 该学期加上前几个学期 成绩模块需要
        *@Date:Created in {21:41} {2019/5/10}
    */
    List<Semester> selectBySemesterIdAndCount(Long semesterId, Integer count);
    /**
        *@Author:梁俊杰
        *@Description:根据学期ID制造区间检索在此区间里面的学期ID
        *@Date:Created in {1:59} {2019/5/12}
    */
    List<Long> selectByfromtoEnd(Long from, Long to);
}
