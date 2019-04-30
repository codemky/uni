package edu.uni.userBaseInfo1.service;

import com.github.pagehelper.PageInfo;
import edu.uni.userBaseInfo1.bean.FirstLevelDiscipline;

import java.util.List;

public interface FirstLevelDisciplineService {
    /**
     * Author: chenenru 14:57 2019/4/30
     * @param 
     * @return   List<FirstLevelDiscipline>
     * @apiNote:查询所有一级学科
     */
    List<FirstLevelDiscipline> selectAllFirstLevelDisciplines();
    /**
     * Author: chenenru 14:57 2019/4/30
     * @param id
     * @return FirstLevelDiscipline
     * @apiNote: 根据id查询一级学科
     */
    FirstLevelDiscipline selectFirstLevelDisciplineById(long id);
    /**
     * Author: chenenru 14:57 2019/4/30
     * @param pageNum
     * @return PageInfo<FirstLevelDiscipline>
     * @apiNote: 分页查询一级学科
     */
    PageInfo<FirstLevelDiscipline> selectFirstLevelDisciplineByPage(int pageNum);
    /**
     * Author: chenenru 14:57 2019/4/30
     * @param firstLevelDiscipline
     * @return boolean
     * @apiNote: 职员添加FirstLevelDiscipline表的一条记录
     */
    boolean insertFirstLevelDiscipline(FirstLevelDiscipline firstLevelDiscipline);
    /**
     * Author: chenenru 14:57 2019/4/30
     * @param firstLevelDiscipline
     * @return boolean
     * @apiNote: 用户更新一个FirstLevelDiscipline表的某个记录（传一个新的对象）
     */
    boolean updateFirstLevelDiscipline(FirstLevelDiscipline firstLevelDiscipline);
    /**
     * Author: chenenru 14:57 2019/4/30
     * @param id
     * @return boolean
     * @apiNote: 用于删除FirstLevelDiscipline表的某个记录
     */
    boolean deleteFirstLevelDiscipline(long id);
    
}
