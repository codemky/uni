package edu.uni.professionalcourses.service;

import com.github.pagehelper.PageInfo;
import edu.uni.professionalcourses.bean.SyllabusTeachingContent;

import java.util.List;

/**
 author:  曹中耀
 create:  2019.4.30
 modified:  2019.4.30
 description：创建SyllabusTeachingContentService接口
 */
public interface SyllabusTeachingContentService {
    /**
     * 新增SyllabusTeachingContent信息
     * @param syllabusTeachingContent
     * @return
     */
    boolean insert(SyllabusTeachingContent syllabusTeachingContent);

    /**
     * 删除SyllabusTeachingContent信息
     * @param id
     * @return
     */
    boolean delete(long id);

    /**
     * 更新SyllabusTeachingContent信息
     * @param syllabusTeachingContent
     * @return
     */
    boolean update(SyllabusTeachingContent syllabusTeachingContent);

    /**
     * 根据SyllabusTeachingContent的id查找SyllabusTeachingContent信息
     * @param id
     * @return
     */
    SyllabusTeachingContent select(long id);

    /**
     * 分页查询所有SyllabusTeachingContent信息
     * @param pageNum
     * @return
     */
    PageInfo<SyllabusTeachingContent> selectPage(int pageNum);

    /**
     * 根据教学内容表学校id分页查询
     * @param pageNum
     * @param university_id
     * @return
     */
    PageInfo<SyllabusTeachingContent> selectPageByUniversityID(int pageNum, long university_id);
    /**
     * 根据教学内容表理论教学内容id分页查询
     * @param pageNum
     * @param course_syllabus_description_id
     * @return
     */
    PageInfo<SyllabusTeachingContent> selectPageByCourseSyllabusDescriptionID(int pageNum, long course_syllabus_description_id);
    /**
     * 查找所有信息
     * @return
     */
    List<SyllabusTeachingContent> selectAll();
}
