package edu.uni.professionalcourses.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.professionalcourses.bean.TrainingProgramMainCourse;
import edu.uni.professionalcourses.bean.TrainingProgramMainCourseExample;
import edu.uni.professionalcourses.config.ProfessionalCoursesConfig;
import edu.uni.professionalcourses.mapper.TrainingProgramMainCourseMapper;
import edu.uni.professionalcourses.service.TrainingProgramMainCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 author:  曹中耀
 create:  2019.5.5
 modified:  2019.5.5
 description：对 TrainingProgramExperimentCourseService 的方法的实现
 */
@Service
public class TrainingProgramMainCourseServiceImpl implements TrainingProgramMainCourseService {
    @Autowired
    private TrainingProgramMainCourseMapper trainingProgramMainCourseMapper;
    @Autowired
    private ProfessionalCoursesConfig globalConfig;
    @Override
    /**
     * 新增TrainingProgramMainCourse信息
     * @param trainingProgramMainCourse
     * @return
     */
    public boolean insert(TrainingProgramMainCourse trainingProgramMainCourse) {
        return trainingProgramMainCourseMapper.insert(trainingProgramMainCourse) > 0 ? true : false;
    }
    /**
     * 删除TrainingProgramMainCourse信息
     * @param id
     * @return
     */
    @Override
    public boolean delete(long id) {

        return trainingProgramMainCourseMapper.deleteByPrimaryKey(id) > 0 ? true : false;
    }
    /**
     * 更新TrainingProgramMainCourse信息
     * @param trainingProgramMainCourse
     * @return
     */
    @Override
    public boolean update(TrainingProgramMainCourse trainingProgramMainCourse) {
        return trainingProgramMainCourseMapper.updateByPrimaryKeySelective(trainingProgramMainCourse) > 0 ? true : false;
    }
    /**
     * 查找TrainingProgramMainCourse信息
     * @param id
     * @return
     */
    @Override
    public TrainingProgramMainCourse select(long id) {
        return trainingProgramMainCourseMapper.selectByPrimaryKey(id);
    }
    /**
     * 分页查询返回所有TrainingProgramMainCourse信息
     * @param pageNum
     * @return
     */
    @Override
    public PageInfo<TrainingProgramMainCourse> selectPage(int pageNum) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        List<TrainingProgramMainCourse> trainingProgramExperimentCourses= trainingProgramMainCourseMapper.selectByExample(null);   //  无条件查找商品
        if(trainingProgramExperimentCourses  != null)
            return new PageInfo<>(trainingProgramExperimentCourses );
        else
            return null;
    }
    /**
     *  根据TrainingProgramMainCourse表学校id分页查询并返回TrainingProgramMainCourse信息
     * @param pageNum
     * @param university_Id
     * @return
     */
    @Override
    public PageInfo<TrainingProgramMainCourse> selectPageByUniversityID(int pageNum, long university_Id) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        TrainingProgramMainCourseExample example = new TrainingProgramMainCourseExample();
        TrainingProgramMainCourseExample .Criteria criteria = example.createCriteria();
        criteria.andUniversityIdEqualTo(university_Id).andDeletedEqualTo(false);
        // 根据条件查询
        List<TrainingProgramMainCourse> products = trainingProgramMainCourseMapper.selectByExample(example);   //  无条件查找商品
        if(products != null)
            return new PageInfo<>(products);
        else
            return null;
    }
    /**
     *  根据TrainingProgramMainCourse表培养方案内容id分页查询并返回TrainingProgramMainCourse信息
     * @param pageNum
     * @param training_program_Id
     * @return
     */
    @Override
    public PageInfo<TrainingProgramMainCourse> selectPageByTrainingProgramID(int pageNum, long training_program_Id) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        TrainingProgramMainCourseExample example = new TrainingProgramMainCourseExample();
        TrainingProgramMainCourseExample .Criteria criteria = example.createCriteria();
        criteria.andTrainingProgramIdEqualTo(training_program_Id).andDeletedEqualTo(false);
        // 根据条件查询
        List<TrainingProgramMainCourse> products = trainingProgramMainCourseMapper.selectByExample(example);   //  无条件查找商品
        if(products != null)
            return new PageInfo<>(products);
        else
            return null;
    }
    /**
     *  根据TrainingProgramMainCourse表课程id分页查询并返回TrainingProgramMainCourse信息
     * @param pageNum
     * @param Course_Id
     * @return
     */
    @Override
    public PageInfo<TrainingProgramMainCourse>  selectPageByCourseID(int pageNum, long Course_Id) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        TrainingProgramMainCourseExample example = new TrainingProgramMainCourseExample();
        TrainingProgramMainCourseExample .Criteria criteria = example.createCriteria();
        criteria.andCourseIdEqualTo(Course_Id).andDeletedEqualTo(false);
        // 根据条件查询
        List<TrainingProgramMainCourse> products = trainingProgramMainCourseMapper.selectByExample(example);   //  无条件查找商品
        if(products != null)
            return new PageInfo<>(products);
        else
            return null;
    }
    /**
     * 查找所有TrainingProgramMainCourse信息
     * @return
     */
    @Override
    public List<TrainingProgramMainCourse> selectAll() {
        TrainingProgramMainCourseExample example=new TrainingProgramMainCourseExample();
        TrainingProgramMainCourseExample.Criteria criteria=example.createCriteria();
        criteria.andDeletedEqualTo(false);
        return trainingProgramMainCourseMapper.selectByExample(example);
    }
}
