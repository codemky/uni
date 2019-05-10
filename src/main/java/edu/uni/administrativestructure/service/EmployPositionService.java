package edu.uni.administrativestructure.service;

import com.github.pagehelper.PageInfo;
import edu.uni.administrativestructure.bean.EmployPosition;

import java.util.List;

/**
 * author：黄育林
 * create: 2019.4.27
 * 功能：雇员岗位关系接口
 */
public interface EmployPositionService {
    /**
     * 保存雇员岗位关系
     * @param employPosition
     * @return
     */
    boolean insert(EmployPosition employPosition);

    /**
     * 删除雇员岗位关系
     * @param id
     * @return
     */
    boolean delete(long id);

    /**
     * 更新雇员岗位关系
     * @param employPosition
     * @return
     */
    boolean update(EmployPosition employPosition);

    /**
     * 查找雇员岗位关系
     * @param id
     * @return
     */
    EmployPosition select(long id);

    /**
     * 查找所有雇员岗位关系
     * @return
     */
    List<EmployPosition> selectAll();

    /**
     * 分页查询所有雇员岗位关系
     * @param pageNum
     * @return
     */
    PageInfo<EmployPosition> selectPage(int pageNum);

    /**
     * 分雇员分页查询雇员岗位关系
     * @param pageNum
     * @param employId
     * @return
     */
    PageInfo<EmployPosition> selectPageByEmploy(int pageNum, long employId);

    /**
     * 分学校分页查询雇员岗位关系
     * @param pageNum
     * @param universityId
     * @return
     */
    PageInfo<EmployPosition> selectPageByUniversity(int pageNum, long universityId);

    /**
     * 分学校分页查询雇员岗位关系
     * @param pageNum
     * @param positionId
     * @return
     */
    PageInfo<EmployPosition> selectPageByPosition(int pageNum, long positionId);
}
