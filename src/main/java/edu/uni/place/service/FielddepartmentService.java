package edu.uni.place.service;

import com.github.pagehelper.PageInfo;
import edu.uni.place.bean.Fielddepartment;

public interface FielddepartmentService {
    /**
     * 新增场地主管部门
     * @param fielddepartment
     * @return
     */
    boolean insert(Fielddepartment fielddepartment);

    /**
     * 删除场地主管部门
     * @param id
     * @return
     */
    boolean delete(Long id);

    /**
     * 修改场地主管部门
     * @param fielddepartment
     * @return
     */
    boolean update(Fielddepartment fielddepartment);

    /**
     * 查询场地主管部门详情
     * @param id
     * @return
     */
    Fielddepartment select(Long id);

    /**
     * 分学校、分场地、分部门、分页查询场地主管部门
     * @param pageNum
     * @param universityId
     * @param fieldId
     * @param departmentId
     * @return
     */
    PageInfo<Fielddepartment> selectPageByUIdFIdDId(int pageNum, Long universityId, Long fieldId, Long departmentId);



}
