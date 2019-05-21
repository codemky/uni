package edu.uni.professionalcourses.service;

import com.github.pagehelper.PageInfo;
import edu.uni.professionalcourses.bean.Specialty;

import java.util.List;
/**
 author:  曹中耀
 create:  2019.5.5
 modified:  2019.5.5
 description：创建Specialty接口
 */
public interface SpecialtyService {
    /**
     * 新增Specialty信息
     * @param specialty
     * @return
     */
    boolean insert(Specialty specialty);

    /**
     * 删除Specialty信息
     * @param id
     * @return
     */
    boolean delete(long id);

    /**
     * 更新Specialty信息
     * @param specialty
     * @return
     */
    boolean update(Specialty specialty);

    /**
     * 根据Specialty的id查找Specialty信息
     * @param id
     * @return
     */
    Specialty select(long id);

    /**
     * 分页查询所有Specialty信息
     * @param pageNum
     * @return
     */
    PageInfo<Specialty> selectPage(int pageNum);

    /**
     * 根据专业信息表学校id分页查询
     * @param pageNum
     * @param university_id
     * @return
     */
    PageInfo<Specialty> selectPageByUniversityID(int pageNum, long university_id);
    /**
     * 根据专业信息表部门id分页查询
     * @param pageNum
     * @param department_id
     * @return
     */
    PageInfo<Specialty> selectPageByDepartmentID(int pageNum, long department_id);
    /**
     * 根据专业信息表学科门类id分页查询
     * @param pageNum
     * @param discipline_category_id
     * @return
     */
    PageInfo<Specialty> selectPageByDisciplineCategoryID(int pageNum, long discipline_category_id);
    /**
     * 根据专业信息表一级学科id分页查询
     * @param pageNum
     * @param first_level_discipline_id
     * @return
     */
    PageInfo<Specialty> selectPageByFirstLevelDisciplineID(int pageNum, long first_level_discipline_id);
    /**
     * 根据专业信息表二级学科id分页查询
     * @param pageNum
     * @param second_level_discipline_id
     * @return
     */
    PageInfo<Specialty> selectPageBySecondLevelDisciplineID(int pageNum, long second_level_discipline_id);
    /**
     * 根据专业信息表培养的学历等级，专业可以获得的学位id分页查询
     * @param pageNum
     * @param academic_id
     * @return
     */
    PageInfo<Specialty> selectPageByAcademicID(int pageNum, long academic_id);
    /**
     * 查找所有信息
     * @return
     */
    List<Specialty> selectAll();
}
