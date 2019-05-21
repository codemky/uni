/**
 * @Author laizhouhao
 * @Description //DisciplineCategory实体类的service层接口
 * @Date 15:11 2019/4/29
 **/
package edu.uni.userBaseInfo1.service;
import com.github.pagehelper.PageInfo;
import edu.uni.professionalcourses.bean.DisciplineCategory;

import java.util.List;

public interface MyDisciplineCategoryService {
    /**
     * Author: laizhouhao 15:17 2019/4/29
     * @return List<DisciplineCategory>
     * @apiNote: 查询所有的学科类别信息
     */
    List<DisciplineCategory> selectAll();

    /**
     * Author: laizhouhao 15:39 2019/4/29
     * @param id
     * @return DisciplineCategory
     * @apiNote: 根据id查询学科类别信息
     */
    DisciplineCategory selectById(Long id);

    /**
     * Author: laizhouhao 15:41 2019/4/29
     * @param pageNum
     * @return List<DisciplineCategory>
     * @apiNote: 分页查询学科类别信息信息
     */
    PageInfo<DisciplineCategory> selectPage(Integer pageNum);

    /**
     * Author: laizhouhao 15:44 2019/4/29
     * @param disciplineCategory
     * @return boolean
     * @apiNote: 用于增加DisciplineCategory表的一个记录
     */
    boolean insert(DisciplineCategory disciplineCategory);

    /**
     * Author: laizhouhao 15:46 2019/4/29
     * @param disciplineCategory
     * @return boolean
     * @apiNote: 用于更新DisciplineCategory表的一个记录
     */
    boolean update(DisciplineCategory disciplineCategory);

    /**
     * Author: laizhouhao 15:47 2019/4/29
     * @param id
     * @return boolean
     * @apiNote: 用于根据id删除DisciplineCategory表的一个记录
     */
    boolean delete(Long id);
}
