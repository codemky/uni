package edu.uni.educateAffair.service;

import edu.uni.bean.Result;
import edu.uni.educateAffair.bean.Canlendar;
import edu.uni.educateAffair.bean.Semester;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
    *@Author:梁俊杰
    *@Description:校历接口
    *@Date:Created in {23:13} {2019/4/28}
*/
public interface CanlendarService {
    /**
        *@Author:梁俊杰
        *@Description:添加校历
        *@Date:Created in {23:14} {2019/4/28}
    */
    boolean insertCanlendar(List<Canlendar> canlendar);
    /**
        *@Author:梁俊杰
        *@Description:修改校历
        *@Date:Created in {23:30} {2019/4/28}
    */
    boolean updateCanlendar(List<Canlendar> c) throws SQLException;
    /**
        *@Author:梁俊杰
        *@Description:注入"星期"的值
        *@Date:Created in {23:53} {2019/5/3}
    */
    String inputWeek(Long id, Date theDate) throws SQLException;
    /**
        *@Author:梁俊杰
        *@Description:注入"星期几"的值
        *@Date:Created in {12:47} {2019/5/4}
    */
    String inputDate(Date theDate);
    /**
        *@Author:梁俊杰
        *@Description:查看所有校历
        *@Date:Created in {14:13} {2019/5/4}
    */
    List<Canlendar> selectAll();
    /**
        *@Author:梁俊杰
        *@Description:根据ID删除校历
        *@Date:Created in {18:50} {2019/5/4}
    */
    boolean delete(Long id);
    /**
        *@Author:梁俊杰
        *@Description:根据学期ID查看校历
        *@Date:Created in {8:46} {2019/5/9}
    */
    List<Canlendar> selectBySemesterId(Long sid);
}
