package edu.uni.professionalcourses.service;

import com.github.pagehelper.PageInfo;
import edu.uni.professionalcourses.bean.TrainingProgramMainCourse;

import java.util.List;
/**
 author:  曹中耀
 create:  2019.5.1
 modified:  2019.5.1
 description：创建TrainingProgramMainCourseCourse接口
 */
public interface TrainingProgramMainCourseService {
    /**
     * 新增TrainingProgramMainCourse信息
     * @param trainingProgramMainCourse
     * @return
     */
    boolean insert(TrainingProgramMainCourse trainingProgramMainCourse);

    /**
     * 删除TrainingProgramMainCourse信息
     * @param id
     * @return
     */
    boolean delete(long id);

    /**
     * 更新TrainingProgramMainCourse信息
     * @param trainingProgramMainCourse
     * @return
     */
    boolean update(TrainingProgramMainCourse trainingProgramMainCourse);

    /**
     * 根据TrainingProgramMainCourse的id查找TrainingProgramMainCourse信息
     * @param id
     * @return
     */
    TrainingProgramMainCourse select(long id);

    /**
     * 分页查询所有TrainingProgramMainCourse信息
     * @param pageNum
     * @return
     */
    PageInfo<TrainingProgramMainCourse> selectPage(int pageNum);

    /**
     * 根据专业理论课程培养方案内容表学校id分页查询
     * @param pageNum
     * @param university_id
     * @return
     */
    PageInfo<TrainingProgramMainCourse> selectPageByUniversityID(int pageNum, long university_id);
    /**
     * 根据专业理论课程培养方案内容表培养方案内容id分页查询
     * @param pageNum
     * @param training_program_id
     * @return
     */
    PageInfo<TrainingProgramMainCourse> selectPageByTrainingProgramID(int pageNum, long training_program_id);
    /**
     * 根据专业理论课程培养方案内容表课程id分页查询
     * @param pageNum
     * @param course_id
     * @return
     */
    PageInfo<TrainingProgramMainCourse> selectPageByCourseID(int pageNum, long course_id);
    /**
     * 查找所有信息
     * @return
     */
    List<TrainingProgramMainCourse> selectAll();
}
