package edu.uni.professionalcourses.service;

import com.github.pagehelper.PageInfo;
import edu.uni.professionalcourses.bean.TrainingProgram;

import java.util.List;

/**
 author:  曹中耀
 create:  2019.4.30
 modified:  2019.4.30
 description：创建TrainingProgramService接口
 */
public interface TrainingProgramService {
    /**
     * 新增TrainingProgram信息
     * @param trainingProgram
     * @return
     */
    boolean insert(TrainingProgram trainingProgram);

    /**
     * 删除TrainingProgram信息
     * @param id
     * @return
     */
    boolean delete(long id);

    /**
     * 更新TrainingProgram信息
     * @param trainingProgram
     * @return
     */
    boolean update(TrainingProgram trainingProgram);

    /**
     * 根据TrainingProgram的id查找TrainingProgram信息
     * @param id
     * @return
     */
    TrainingProgram select(long id);

    /**
     * 分页查询所有TrainingProgram信息
     * @param pageNum
     * @return
     */
    PageInfo<TrainingProgram> selectPage(int pageNum);

    /**
     * 根据专业培养方案内容表学校id分页查询
     * @param pageNum
     * @param university_id
     * @return
     */
    PageInfo<TrainingProgram> selectPageByUniversityID(int pageNum, long university_id);
    /**
     * 根据专业培养方案内容表专业id分页查询
     * @param pageNum
     * @param specialty_id
     * @return
     */
    PageInfo<TrainingProgram> selectPageBySpecialtyID(int pageNum, long specialty_id);
    /**
     * 查找所有信息
     * @return
     */
    List<TrainingProgram> selectAll();
}
