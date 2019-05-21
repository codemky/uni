package edu.uni.administrativestructure.service;

import com.github.pagehelper.PageInfo;
import edu.uni.administrativestructure.bean.DepartmentSubdepartment;

import java.util.List;
/**
 author:黄永佳
 create:2019.4.25
 modified:null
 功能:创建DepartmentService接口
 **/
public interface DepartmentSubdepartmentService {
    /**
     * 新增一级部门二级部门关系
     * @param departmentSubdepartment
     * @return
     */
    boolean insert(DepartmentSubdepartment departmentSubdepartment);

    /**
     * 删除一级部门二级部门关系
     * @param id
     * @return
     */
    boolean delete(long id);

    /**
     * 修改一级部门二级部门关系
     * @param
     * @return
     */
    boolean update(DepartmentSubdepartment departmentSubdepartment);

    /**
     * 查询一级部门二级部门关系详情
     * @param id
     * @return
     */
    DepartmentSubdepartment select(long id);

    /**
     * 分页查询所有一二级部门关系
     * @param pageNum
     * @return
     */
    PageInfo<DepartmentSubdepartment> selectPage(int pageNum);

    /**
     * 分学校分页查询一二级部门关系
     * @param pageNum
     * @param universityId
     * @return
     */
    PageInfo<DepartmentSubdepartment> selectPageByUniversity(int pageNum, long universityId);

    /**
     * 分一级部门分页查询部门关系
     * @param pageNum
     * @param departmentId
     * @return
     */
    PageInfo<DepartmentSubdepartment> selectPageByDepartment(int pageNum, long departmentId);

    /**
     * 分二级部门分页查询部门关系
     * @param pageNum
     * @param subdepartmentId
     * @return
     */
    PageInfo<DepartmentSubdepartment> selectPageBySubdepartment(int pageNum, long subdepartmentId);

    /**
     * 查找所有部门关系
     * @return
     */
    List<DepartmentSubdepartment> selectAll();
}
