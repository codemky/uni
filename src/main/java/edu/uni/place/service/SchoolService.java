package edu.uni.place.service;

import com.github.pagehelper.PageInfo;
import edu.uni.place.bean.School;

import java.util.List;

public interface SchoolService {

    /**
     * 新增校区
     * @param school
     * @return
     */
    boolean insert(School school);

    /**
     * 删除校区
     * @param id
     * @return
     */
    boolean delete(Long id);

    /**
     * 修改校区
     * @param school
     * @return
     */
    boolean update(School school);

    /**
     * 查询校区详情
     * @param id
     * @return
     */
    School select(Long id);

    /**
     * 分校分页查询校区
     * @param pageNum
     * @param universityId
     * @return
     */
    PageInfo<School> selectPageByUId(int pageNum, Long universityId);

    /**
     * 分校查询校区
     * @param universityId
     * @return
     */
    List<School> selectByUId(Long universityId);
    /**
     * @description: 根据校区名称模糊查询
     * @author: 潘绍豪
     * @date: {2019/5/9} {19:23}
     */
    PageInfo<School> selectPageLikeName(int pageNum, String name);

}
