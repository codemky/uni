package edu.uni.administrativestructure.service;

import com.github.pagehelper.PageInfo;
import edu.uni.administrativestructure.bean.Employ;

import java.util.List;

/**
 * author：黄育林
 * create: 2019.4.24
 * modified：2019.5.7
 * 功能：部门人员表接口
 */
public interface EmployService {
    /**
     * 保存部门人员记录
     * @param employ
     * @return
     */
    boolean insert(Employ employ);

    /**
     * 删除部门人员记录
     * @param id
     * @return
     */
    boolean delete(long id);

    /**
     * 更新部门人员记录
     * @param employ
     * @return
     */
    boolean update(Employ employ);

    /**
     * 查找部门人员记录
     * @param id
     * @return
     */
    Employ select(long id);

    /**
     * 查找所有部门人员记录
     * @return
     */
    List<Employ> selectAll();

    /**
     * 分页查询所有部门人员记录
     * @param pageNum
     * @return
     */
    PageInfo<Employ> selectPage(int pageNum);

    /**
     * 分部门分页查询部门人员记录
     * @param pageNum
     * @param departmentId
     * @return
     */
    PageInfo<Employ> selectPageByDepartment(int pageNum, long departmentId);

    /**
     * 分学校分页查询部门人员记录
     * @param pageNum
     * @param universityId
     * @return
     */
    PageInfo<Employ> selectPageByUniversity(int pageNum, long universityId);

    /**
     * 分雇员分页查询部门人员记录
     * @param pageNum
     * @param workerId
     * @return
     */
    PageInfo<Employ> selectPageByEmployee(int pageNum, long workerId);
}
