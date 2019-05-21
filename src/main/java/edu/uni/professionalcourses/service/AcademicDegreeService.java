package edu.uni.professionalcourses.service;

import com.github.pagehelper.PageInfo;
import edu.uni.professionalcourses.bean.AcademicDegree;

import java.util.List;

/**
 author:  曹中耀
 create:  2019.5.11
 modified:  2019.5.11
 description：创建 AcademicDegree接口
 */
public interface AcademicDegreeService {

    /**
     * 新增  AcademicDegree信息
     * @param academicDegree
     * @return
     */
    boolean insert(AcademicDegree academicDegree);

    /**
     * 删除  AcademicDegree信息
     * @param id
     * @return
     */
    boolean delete(long id);

    /**
     * 更新  AcademicDegree信息
     * @param academicDegree
     * @return
     */
    boolean update(AcademicDegree academicDegree);

    /**
     * 根据  AcademicDegree的id查找  AcademicDegree信息
     * @param id
     * @return
     */
    AcademicDegree select(long id);
    /**
     * 分页查询所有  AcademicDegree息
     * @param pageNum
     * @return
     */
    PageInfo<  AcademicDegree> selectPage(int pageNum);
    /**
     * 查找所有信息
     * @return
     */
    List<  AcademicDegree> selectAll();
}
