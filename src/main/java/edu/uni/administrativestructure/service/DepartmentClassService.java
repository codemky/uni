package edu.uni.administrativestructure.service;

import com.github.pagehelper.PageInfo;
import edu.uni.administrativestructure.bean.DepartmentClass;

import java.util.List;

/**
 * author：黄育林
 * create: 2019.4.26
 * modified: 2019.4.30
 * 功能：部门班级关系接口
 */
public interface DepartmentClassService {
    /**
     * 保存部门班级关系
     * @param departmentClass
     * @return
     */
    boolean insert(DepartmentClass departmentClass);

    /**
     * 删除部门班级关系
     * @param id
     * @return
     */
    boolean delete(long id);

    /**
     * 更新部门班级关系
     * @param departmentClass
     * @return
     */
    boolean update(DepartmentClass departmentClass);

    /**
     * 查找部门班级关系
     * @param id
     * @return
     */
    DepartmentClass select(long id);

    /**
     * 查找所有部门班级关系
     * @return
     */
    List<DepartmentClass> selectAll();

    /**
     * 分页查询所有部门班级关系
     * @param pageNum
     * @return
     */
    PageInfo<DepartmentClass> selectPage(int pageNum);

    /**
     * 分部门分页查询部门班级关系
     * @param pageNum
     * @param departmentId
     * @return
     */
    PageInfo<DepartmentClass> selectPageByDepartment(int pageNum, long departmentId);

    /**
     * 分部门分页查询部门班级关系
     * @param pageNum
     * @param classId
     * @return
     */
    PageInfo<DepartmentClass> selectPageByClass(int pageNum, long classId);

    /**
     * 分学校分页查询部门班级关系
     * @param pageNum
     * @param universityId
     * @return
     */
    PageInfo<DepartmentClass> selectPageByUniversity(int pageNum, long universityId);
}
