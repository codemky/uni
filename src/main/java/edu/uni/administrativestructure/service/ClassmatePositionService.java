package edu.uni.administrativestructure.service;

import com.github.pagehelper.PageInfo;
import edu.uni.administrativestructure.bean.ClassmatePosition;

/**
 * author : 黄永佳
 * create : 2019/4/26 0026 18:07
 * modified :
 * 功能 :创建classmate_position表的接口
 **/
public interface ClassmatePositionService {
    /**
     * 新增班级人员岗位信息
     * @param classmatePosition
     * @return
     */
    boolean insert(ClassmatePosition classmatePosition);
    /**
     * 删除班级人员岗位信息
     * @param id
     * @return
     */
    boolean delete(long id);
    /**
     * 修改班级人员岗位信息
     * @param
     * @return
     */
    boolean update(ClassmatePosition classmatePosition);
    /**
     * 查询班级人员岗位信息
     * @param id
     * @return
     */
    ClassmatePosition select(long id);
    /**
     * 分页查询所有班级人员岗位信息
     * @param pageNum
     * @return
     */
    PageInfo<ClassmatePosition> selectPage(int pageNum);
    /**
     * 分学校分页查询班级人员岗位信息
     * @param pageNum
     * @param universityId
     * @return
     */
    PageInfo<ClassmatePosition> selectPageByUniversity(int pageNum, long universityId);

    /**
     * 分班级人员id分页查询班级人员岗位信息
     * @param pageNum
     * @param classmateId
     * @return
     */
    PageInfo<ClassmatePosition> selectPageByClassmate(int pageNum, long classmateId);

    /**
     * 分岗位id分页查询班级人员岗位信息
     * @param pageNum
     * @param positionId
     * @return
     */
    PageInfo<ClassmatePosition> selectPageByPosition(int pageNum, long positionId);

}
