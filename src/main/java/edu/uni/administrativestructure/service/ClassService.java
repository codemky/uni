package edu.uni.administrativestructure.service;

import com.github.pagehelper.PageInfo;
import edu.uni.administrativestructure.bean.Class;

import java.util.List;

/**
 * author : 黄永佳
 * create : 2019/4/26 0026 12:27
 * modified :
 * 功能 :创建class表的接口
 **/
public interface ClassService {
    /**
     * 新增班级信息
     * @param class_1
     * @return
     */
    boolean insert(Class class_1);

    /**
     * 删除班级信息
     * @param id
     * @return
     */
    boolean delete(long id);

    /**
     * 修改班级信息
     * @param
     * @return
     */
    boolean update(Class class_1);

    /**
     * 查询一级部门二级部门关系详情
     * @param id
     * @return
     */
    Class select(long id);

    /**
     * 分页查询所有一二级部门关系
     * @param pageNum
     * @return
     */
    PageInfo<Class> selectPage(int pageNum);

    /**
     * 分学校分页查询一二级部门关系
     * @param pageNum
     * @param universityId
     * @return
     */
    PageInfo<Class> selectPageByUniversity(int pageNum, long universityId);

    /**
     * 分一级部门分页查询部门关系
     * @param pageNum
     * @param departmentId
     * @return
     */
    PageInfo<Class> selectPageByDepartment(int pageNum, long departmentId);

    /**
     * 分二级部门分页查询部门关系
     * @param pageNum
     * @param specialtyId
     * @return
     */
    PageInfo<Class> selectPageBySpecialty(int pageNum, long specialtyId);


}
