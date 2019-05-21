package edu.uni.professionalcourses.service;

import com.github.pagehelper.PageInfo;
import edu.uni.professionalcourses.bean.SecondLevelDiscipline;

import java.util.List;

/**
 author:  曹中耀
 create:  2019.5.11
 modified:  2019.5.11
 description：创建SecondLevelDisciplineService接口
 */
public interface SecondLevelDisciplineService {
    /**
     * 新增SecondLevelDiscipline信息
     * @param secondLevelDiscipline
     * @return
     */
    boolean insert(SecondLevelDiscipline secondLevelDiscipline);

    /**
     * 删除SecondLevelDiscipline信息
     * @param id
     * @return
     */
    boolean delete(long id);

    /**
     * 更新SecondLevelDiscipline信息
     * @param secondLevelDiscipline
     * @return
     */
    boolean update(SecondLevelDiscipline secondLevelDiscipline);

    /**
     * 根据SecondLevelDiscipline的id查找SecondLevelDiscipline信息
     * @param id
     * @return
     */
    SecondLevelDiscipline select(long id);

    /**
     * 分页查询所有SecondLevelDiscipline信息
     * @param pageNum
     * @return
     */
    PageInfo<SecondLevelDiscipline> selectPage(int pageNum);

    /**
     * 根据二级学科表学科门类id分页查询
     * @param pageNum
     * @param first_level_discipline_id
     * @return
     */
    PageInfo<SecondLevelDiscipline> selectPageByFirstLevelDisciplineID(int pageNum, long first_level_discipline_id);
    /**
     * 查找所有信息
     * @return
     */
    List<SecondLevelDiscipline> selectAll();
}
