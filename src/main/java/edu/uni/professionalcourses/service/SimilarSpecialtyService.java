package edu.uni.professionalcourses.service;

import com.github.pagehelper.PageInfo;
import edu.uni.professionalcourses.bean.SimilarSpecialty;

import java.util.List;
/**
 author:  曹中耀
 create:  2019.5.5
 modified:  2019.5.5
 description：创建SimilarSpecialty接口
 */
public interface SimilarSpecialtyService {
    /**
     * 新增SimilarSpecialty信息
     * @param similarSpecialty
     * @return
     */
    boolean insert(SimilarSpecialty similarSpecialty);

    /**
     * 删除SimilarSpecialty信息
     * @param id
     * @return
     */
    boolean delete(long id);

    /**
     * 更新SimilarSpecialty信息
     * @param similarSpecialty
     * @return
     */
    boolean update(SimilarSpecialty similarSpecialty);

    /**
     * 根据SimilarSpecialty的id查找SimilarSpecialty信息
     * @param id
     * @return
     */
    SimilarSpecialty select(long id);

    /**
     * 分页查询所有SimilarSpecialty信息
     * @param pageNum
     * @return
     */
    PageInfo<SimilarSpecialty> selectPage(int pageNum);

    /**
     * 根据相近专业表学校id分页查询
     * @param pageNum
     * @param university_id
     * @return
     */
    PageInfo<SimilarSpecialty> selectPageByUniversityID(int pageNum, long university_id);
    /**
     * 根据相近专业表先修专业id分页查询
     * @param pageNum
     * @param specialty_id
     * @return
     */
    PageInfo<SimilarSpecialty> selectPageBySpecialtyID(int pageNum, long specialty_id);
    /**
     * 根据先修课程表相近专业id分页查询
     * @param pageNum
     * @param similar_specialty_id
     * @return
     */
    PageInfo<SimilarSpecialty> selectPageBySimilarSpecialtyID(int pageNum, long similar_specialty_id);
    /**
     * 查找所有信息
     * @return
     */
    List<SimilarSpecialty> selectAll();
}
