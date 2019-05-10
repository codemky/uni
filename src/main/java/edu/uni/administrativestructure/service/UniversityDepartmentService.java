package edu.uni.administrativestructure.service;

import com.github.pagehelper.PageInfo;
import edu.uni.administrativestructure.bean.UniversityDepartment;

import java.util.List;

/**
 * author：黄育林
 * create: 2019.4.26
 * 功能：学校部门关系接口
 */
public interface UniversityDepartmentService {
    /**
     * 保存学校部门关系
     * @param universityDepartment
     * @return
     */
    boolean insert(UniversityDepartment universityDepartment);

    /**
     * 删除学校部门关系
     * @param id
     * @return
     */
    boolean delete(long id);

    /**
     * 更新学校部门关系
     * @param universityDepartment
     * @return
     */
    boolean update(UniversityDepartment universityDepartment);

    /**
     * 查找学校部门关系
     * @param id
     * @return
     */
    UniversityDepartment select(long id);

    /**
     * 查找所有学校部门关系
     * @return
     */
    List<UniversityDepartment> selectAll();

    /**
     * 分页查询所有学校部门关系
     * @param pageNum
     * @return
     */
    PageInfo<UniversityDepartment> selectPage(int pageNum);

    /**
     * 分部门分页查询学校部门关系
     * @param pageNum
     * @param departmentId
     * @return
     */
    PageInfo<UniversityDepartment> selectPageByDepartment(int pageNum, long departmentId);

    /**
     * 分学校分页查询学校部门关系
     * @param pageNum
     * @param universityId
     * @return
     */
    PageInfo<UniversityDepartment> selectPageByUniversity(int pageNum, long universityId);
}
