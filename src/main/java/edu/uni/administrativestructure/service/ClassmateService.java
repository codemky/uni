package edu.uni.administrativestructure.service;

import com.github.pagehelper.PageInfo;
import edu.uni.administrativestructure.bean.Classmate;

import java.util.List;

/**
 * author：黄育林
 * create: 2019.4.25
 * modified: 2019.5.9
 * 功能：班级人员表接口
 */

public interface ClassmateService {
    /**
     * 保存班级人员记录
     * @param classmate
     * @return
     */
    boolean insert(Classmate classmate);

    /**
     * 删除班级人员记录
     * @param id
     * @return
     */
    boolean delete(long id);

    /**
     * 更新班级人员记录
     * @param classmate
     * @return
     */
    boolean update(Classmate classmate);

    /**
     * 查找班级人员记录
     * @param id
     * @return
     */
    Classmate select(long id);

    /**
     * 查找所有班级人员记录
     * @return
     */
    List<Classmate> selectAll();

    /**
     * 分页查询所有班级人员记录
     * @param pageNum
     * @return
     */
    PageInfo<Classmate> selectPage(int pageNum);

    /**
     * 分班级分页查询部门人员记录
     * @param pageNum
     * @param classId
     * @return
     */
    PageInfo<Classmate> selectPageByClass(int pageNum, long classId);

    /**
     * 分学校分页查询班级人员记录
     * @param pageNum
     * @param universityId
     * @return
     */
    PageInfo<Classmate> selectPageByUniversity(int pageNum, long universityId);

    /**
     * 分学生分页查询班级人员记录
     * @param pageNum
     * @param studentId
     * @return
     */
    PageInfo<Classmate> selectPageByStudent(int pageNum, long studentId);

}
