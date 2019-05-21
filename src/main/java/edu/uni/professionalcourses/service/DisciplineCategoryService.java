package edu.uni.professionalcourses.service;

import com.github.pagehelper.PageInfo;
import edu.uni.professionalcourses.bean.DisciplineCategory;

import java.util.List;

/**
 author:  曹中耀
 create:  2019.5.11
 modified:  2019.5.11
 description：创建DisciplineCategoryService接口
 */
public interface DisciplineCategoryService {
    /**
     * 新增Book信息
     * @param disciplineCategory
     * @return
     */
    boolean insert(DisciplineCategory disciplineCategory);

    /**
     * 删除DisciplineCategory信息
     * @param id
     * @return
     */
    boolean delete(long id);

    /**
     * 更新DisciplineCategory信息
     * @param disciplineCategory
     * @return
     */
    boolean update(DisciplineCategory disciplineCategory);

    /**
     * 根据DisciplineCategory的id查找DisciplineCategory信息
     * @param id
     * @return
     */
    DisciplineCategory select(long id);

    /**
     * 分页查询所有DisciplineCategory信息
     * @param pageNum
     * @return
     */
    PageInfo<DisciplineCategory> selectPage(int pageNum);

   /* /**
     * 根据学科门类表学校id分页查询
     * @param pageNum
     * @param university_id
     * @return
     */
//    PageInfo<Book> selectPageByUniversityID(int pageNum, long university_id);
    /**
     * 查找所有信息
     * @return
     */
    List<DisciplineCategory> selectAll();
}
