package edu.uni.professionalcourses.service;

import com.github.pagehelper.PageInfo;
import edu.uni.professionalcourses.bean.FirstLevelDiscipline;

import java.util.List;
/**
 author:  曹中耀
 create:  2019.5.11
 modified:  2019.5.11
 description：创建FirstLevelDisciplineService接口
 */
public interface FirstLevelDisciplineService {
    /**
     * 新增FirstLevelDiscipline信息
     * @param firstLevelDiscipline
     * @return
     */
    boolean insert(FirstLevelDiscipline firstLevelDiscipline);

    /**
     * 删除FirstLevelDiscipline信息
     * @param id
     * @return
     */
    boolean delete(long id);

    /**
     * 更新FirstLevelDiscipline信息
     * @param firstLevelDiscipline
     * @return
     */
    boolean update(FirstLevelDiscipline firstLevelDiscipline);

    /**
     * 根据FirstLevelDiscipline的id查找FirstLevelDiscipline信息
     * @param id
     * @return
     */
    FirstLevelDiscipline select(long id);

    /**
     * 分页查询所有FirstLevelDiscipline信息
     * @param pageNum
     * @return
     */
    PageInfo<FirstLevelDiscipline> selectPage(int pageNum);

    /**
     * 根据一级学科表学科门类id分页查询
     * @param pageNum
     * @param discipline_category_id
     * @return
     */
    PageInfo<FirstLevelDiscipline> selectPageByDisciplineCategoryID(int pageNum, long discipline_category_id);
    /**
     * 查找所有信息
     * @return
     */
    List<FirstLevelDiscipline> selectAll();
}
