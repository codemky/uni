package edu.uni.professionalcourses.service;

import com.github.pagehelper.PageInfo;
import edu.uni.professionalcourses.bean.TrainingProgramExperimentCourse;

import java.util.List;

/**
 author:  曹中耀
 create:  2019.5.1
 modified:  2019.5.1
 description：创建TrainingProgramExperimentCourse接口
 */
public interface TrainingProgramExperimentCourseService {
    /**
     * 新增TrainingProgramExperimentCourse信息
     * @param trainingProgramExperimentCourse
     * @return
     */
    boolean insert(TrainingProgramExperimentCourse trainingProgramExperimentCourse);

    /**
     * 删除TrainingProgramExperimentCourse信息
     * @param id
     * @return
     */
    boolean delete(long id);

    /**
     * 更新TrainingProgramExperimentCourse信息
     * @param trainingProgramExperimentCourse
     * @return
     */
    boolean update(TrainingProgramExperimentCourse trainingProgramExperimentCourse);

    /**
     * 根据TrainingProgramExperimentCourse的id查找TrainingProgramExperimentCourse信息
     * @param id
     * @return
     */
    TrainingProgramExperimentCourse select(long id);

    /**
     * 分页查询所有TrainingProgramExperimentCourse信息
     * @param pageNum
     * @return
     */
    PageInfo<TrainingProgramExperimentCourse> selectPage(int pageNum);

    /**
     * 根据专业实验课程培养方案内容表学校id分页查询
     * @param pageNum
     * @param university_id
     * @return
     */
    PageInfo<TrainingProgramExperimentCourse> selectPageByUniversityID(int pageNum, long university_id);
    /**
     * 根据专业实验课程培养方案内容表培养方案内容id分页查询
     * @param pageNum
     * @param training_program_id
     * @return
     */
    PageInfo<TrainingProgramExperimentCourse> selectPageByTrainingProgramID(int pageNum, long training_program_id);
    /**
     * 根据专业实验课程培养方案内容表课程id分页查询
     * @param pageNum
     * @param course_id
     * @return
     */
    PageInfo<TrainingProgramExperimentCourse> selectPageByCourseID(int pageNum, long course_id);
    /**
     * 查找所有信息
     * @return
     */
    List<TrainingProgramExperimentCourse> selectAll();
}
