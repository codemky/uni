package edu.uni.administrativestructure.service;

import com.github.pagehelper.PageInfo;
import edu.uni.administrativestructure.bean.Subdepartment;

import java.util.List;

/**
 * author：黄育林
 * create: 2019.4.20
 * modified: 2019.4.24
 * 功能：三级部门接口
 */
public interface SubdepartmentService {
    /**
     * 保存三级部门
     * @param subdepartment
     * @return
     */
    boolean insert(Subdepartment subdepartment);

    /**
     * 删除三级部门
     * @param id
     * @return
     */
    boolean delete(long id);

    /**
     * 更新三级部门
     * @param subdepartment
     * @return
     */
    boolean update(Subdepartment subdepartment);

    /**
     * 查找三级部门
     * @param id
     * @return
     */
    Subdepartment select(long id);

    /**
     * 查找所有三级部门
     * @return
     */
    List<Subdepartment> selectAll();

    /**
     * 分页查询所有三级部门
     * @param pageNum
     * @return
     */
    PageInfo<Subdepartment> selectPage(int pageNum);

    /**
     * 分部门分页查询三级部门
     * @param pageNum
     * @param departmentId
     * @return
     */
    PageInfo<Subdepartment> selectPageByDepartment(int pageNum, long departmentId);

    /**
     * 分学校分页查询三级部门
     * @param pageNum
     * @param universityId
     * @return
     */
    PageInfo<Subdepartment> selectPageByUniversity(int pageNum, long universityId);

}
