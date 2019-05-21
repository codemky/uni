package edu.uni.administrativestructure.service;

import com.github.pagehelper.PageInfo;
import edu.uni.administrativestructure.bean.Department;

import java.util.List;
/**
 author:黄永佳
 create:2019.4.19
 modified:null
 功能:创建DepartmentService接口
 **/
public interface DepartmentService {
    /**
     * 新增部门
     * @param department
     * @return
     */
    boolean insert(Department department);

    /**
     * 删除部门
     * @param id
     * @return
     */
    boolean delete(long id);

    /**
     * 修改部门
     * @param department
     * @return
     */
    boolean update(Department department);

    /**
     * 查询部门详情
     * @param id
     * @return
     */
    Department select(long id);

    /**
     * 分页查询所有部门
     * @param pageNum
     * @return
     */
    PageInfo<Department> selectPage(int pageNum);

    /**
     * 分学校分页查询部门
     * @param pageNum
     * @param universityId
     * @return
     */
    PageInfo<Department> selectPageByUniversity(int pageNum, long universityId);

    /**
     * 查找所有部门
     * @return
     */
    List<Department> selectAll();
}
