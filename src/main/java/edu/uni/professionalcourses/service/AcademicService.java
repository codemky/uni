package edu.uni.professionalcourses.service;

import com.github.pagehelper.PageInfo;
import edu.uni.professionalcourses.bean.Academic;

import java.util.List;

/**
 author:  曹中耀
 create:  2019.5.11
 modified:  2019.5.11
 description：创建 Academic接口
 */
public interface AcademicService {


    /**
     * 新增 Academic信息
     * @param academic
     * @return
     */
    boolean insert(Academic academic);

    /**
     * 删除 Academic信息
     * @param id
     * @return
     */
    boolean delete(long id);

    /**
     * 更新 Academic信息
     * @param academic
     * @return
     */
    boolean update(Academic academic);

    /**
     * 根据 Academic的id查找 Academic信息
     * @param id
     * @return
     */
    Academic select(long id);
    /**
     * 分页查询所有 Academic信息
     * @param pageNum
     * @return
     */
    PageInfo< Academic> selectPage(int pageNum);
    /**
     * 查找所有信息
     * @return
     */
    List< Academic> selectAll();
}
