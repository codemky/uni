package edu.uni.professionalcourses.service;

import com.github.pagehelper.PageInfo;
import edu.uni.professionalcourses.bean.PreCourse;

import java.util.List;

/**
 author:  曹中耀
 create:  2019.5.5
 modified:  2019.5.5
 description：创建 PreCourse接口
 */
public interface PreCourseService {
    /**
     * 新增PreCourse信息
     * @param preCourse
     * @return
     */
    boolean insert(PreCourse preCourse);

    /**
     * 删除PreCourse信息
     * @param id
     * @return
     */
    boolean delete(long id);

    /**
     * 更新PreCourse信息
     * @param preCourse
     * @return
     */
    boolean update(PreCourse preCourse);

    /**
     * 根据PreCourse的id查找PreCourse信息
     * @param id
     * @return
     */
    PreCourse select(long id);

    /**
     * 分页查询所有PreCourse信息
     * @param pageNum
     * @return
     */
    PageInfo<PreCourse> selectPage(int pageNum);

    /**
     * 根据先修课程表学校id分页查询
     * @param pageNum
     * @param university_id
     * @return
     */
    PageInfo<PreCourse> selectPageByUniversityID(int pageNum, long university_id);
    /**
     * 根据先修课程表先修课程id分页查询
     * @param pageNum
     * @param pre_course_id
     * @return
     */
    PageInfo<PreCourse> selectPageByPreCourseID(int pageNum, long pre_course_id);
    /**
     * 根据先修课程表课程id分页查询
     * @param pageNum
     * @param course_id
     * @return
     */
    PageInfo<PreCourse> selectPageByCourseID(int pageNum, long course_id);
    /**
     * 查找所有信息
     * @return
     */
    List<PreCourse> selectAll();
}
