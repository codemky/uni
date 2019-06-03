package edu.uni.place.service;

import com.github.pagehelper.PageInfo;
import edu.uni.place.bean.Schoolarea;

import java.util.List;

public interface SchoolareaService {

    /**
     * 新增学校功能区
     *
     * @param schoolarea
     * @return
     */
    boolean insert(Schoolarea schoolarea);

    /**
     * 删除学校功能区
     *
     * @param id
     * @return
     */
    boolean delete(Long id);

    /**
     * 修改学校功能区
     * @param schoolarea
     * @return
     */
    boolean update(Schoolarea schoolarea);

    /**
     * 查询学校功能区详情
     * @param id
     * @return
     */
    Schoolarea select(Long id);

    /**
     * 分校区分页查询学校功能区
     * @param pageNum
     * @param schoolId
     * @return
     */
    PageInfo<Schoolarea> selectPageByUIdSId(int pageNum, Long schoolId);

    /**
     * 分校区查询功能区
     * @param schoolId
     * @return
     */
    List<Schoolarea> selectByUIdSId(Long schoolId);

    /**
     * @description: 分学校查询功能区
     * @author: 潘绍豪
     * @date: {2019/5/12} {0:56}
     */
    List<Schoolarea>selectByUId(Long universityId);

    /**
     * @description: 根据功能区名称模糊查询
     * @author: 潘绍豪
     * @date: {2019/5/9} {19:48}
     */
    PageInfo<Schoolarea> selectPageLikeName(int pageNum, String name);

    /**
     * @description: 根据学校id或者校区id来查询功能区
     * @author: 潘绍豪
     * @date: {2019/5/16} {16:36}
     */
    List<Schoolarea>selectByUIdorSId(Long universityId, Long schoolId);

}
