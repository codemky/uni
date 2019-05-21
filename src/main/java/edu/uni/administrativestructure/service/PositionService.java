package edu.uni.administrativestructure.service;

import com.github.pagehelper.PageInfo;
import edu.uni.administrativestructure.bean.Position;

import java.util.List;

/**
 * author：黄育林
 * create: 2019.4.20
 * 功能：岗位信息接口
 */
public interface PositionService {    /**
 * 保存岗位信息
 * @param position
 * @return
 */
boolean insert(Position position);

    /**
     * 删除岗位信息
     * @param id
     * @return
     */
    boolean delete(long id);

    /**
     * 更新岗位信息
     * @param position
     * @return
     */
    boolean update(Position position);

    /**
     * 查找岗位信息
     * @param id
     * @return
     */
    Position select(long id);

    /**
     * 查找所有岗位信息
     * @return
     */
    List<Position> selectAll();

    /**
     * 分页查询所有岗位信息
     * @param pageNum
     * @return
     */
    PageInfo<Position> selectPage(int pageNum);

    /**
     * 分学校分页查询岗位信息
     * @param pageNum
     * @param universityId
     * @return
     */
    PageInfo<Position> selectPageByUniversity(int pageNum, long universityId);

}
