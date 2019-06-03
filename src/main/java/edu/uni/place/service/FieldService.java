package edu.uni.place.service;

import com.github.pagehelper.PageInfo;
import edu.uni.place.bean.Field;

import java.util.List;

public interface FieldService {

    /**
     * 新增场地信息
     * @param field
     * @return
     */
    boolean insert(Field field);

    /**
     * 删除场地信息
     * @param id
     * @return
     */
    boolean delete(Long id);

    /**
     * 修改场地信息
     * @param field
     * @return
     */
    boolean update(Field field);

    /**
     * 查询场地信息详情
     * @param id
     * @return
     */
    Field select(Long id);

    /**
     * 分学校功能区、分页查询场地
     * @param pageNum
     * @param areaId
     * @return
     */
    PageInfo<Field> selectPageByUIdSIdAId(int pageNum, Long areaId);

    /**
     * 分功能区查询场地
     * @param areaId
     * @return
     */
    List<Field> seleByUIdSIdAId(Long areaId);
    
    /**
     * @description: 根据功能区id和场地名称模糊查询
     * @author: 潘绍豪
     * @date: {2019/5/9} {20:18}
     */
    PageInfo<Field> selectPageLikeName(int pageNum, String name, Long areaId);

    /**
     * @description: 分学校、分页查询场地
     * @author: 潘绍豪
     * @date: {2019/5/10} {17:55}
     */
    PageInfo<Field> selectPageByUId(int pageNum, Long universityId);

    /**
     * @description: 分校区、分页查询场地
     * @author: 潘绍豪
     * @date: {2019/5/10} {18:22}
     */
    PageInfo<Field> selectPageByUIdSId(int pageNum, Long schoolId);

}
