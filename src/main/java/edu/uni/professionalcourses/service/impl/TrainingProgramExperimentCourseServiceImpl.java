package edu.uni.professionalcourses.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.professionalcourses.bean.TrainingProgramExperimentCourse;
import edu.uni.professionalcourses.bean.TrainingProgramExperimentCourseExample;
import edu.uni.professionalcourses.config.ProfessionalCoursesConfig;
import edu.uni.professionalcourses.mapper.TrainingProgramExperimentCourseMapper;
import edu.uni.professionalcourses.service.TrainingProgramExperimentCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 author:  曹中耀
 create:  2019.4.30
 modified:  2019.4.30
 description：对 TrainingProgramExperimentCourseService 的方法的实现
 */
@Service
public class TrainingProgramExperimentCourseServiceImpl implements TrainingProgramExperimentCourseService {
    @Autowired
    private TrainingProgramExperimentCourseMapper trainingProgramExperimentCourseMapper;
    @Autowired
    private ProfessionalCoursesConfig globalConfig;
    @Override
    /**
     * 新增TrainingProgramExperimentCourse信息
     * @param trainingProgramExperimentCourse
     * @return
     */
    public boolean insert(TrainingProgramExperimentCourse trainingProgramExperimentCourse) {
        return trainingProgramExperimentCourseMapper.insert(trainingProgramExperimentCourse) > 0 ? true : false;
    }
    /**
     * 删除TrainingProgramExperimentCourse信息
     * @param id
     * @return
     */
    @Override
    public boolean delete(long id) {

//        TrainingProgramExperimentCourse trainingProgramExperimentCourse1=new TrainingProgramExperimentCourse();
//        trainingProgramExperimentCourse1=trainingProgramExperimentCourseMapper.selectByPrimaryKey(id);
//        trainingProgramExperimentCourse1.setDeleted(true);
//        if(trainingProgramExperimentCourseMapper.updateByPrimaryKeySelective(trainingProgramExperimentCourse1)<=0){
//            return false;
//        }
//
//        TrainingProgramExperimentCourse
       return trainingProgramExperimentCourseMapper.deleteByPrimaryKey(id) > 0 ? true : false;
    }
    /**
     * 更新TrainingProgramExperimentCourse信息
     * @param trainingProgramExperimentCourse
     * @return
     */
    @Override
    public boolean update(TrainingProgramExperimentCourse trainingProgramExperimentCourse) {
/*//        保存旧信息
        TrainingProgramExperimentCourse trainingProgramExperimentCourse1=trainingProgramExperimentCourseMapper.selectByPrimaryKey(trainingProgramExperimentCourse.getId());
//      旧信息设为无效
       trainingProgramExperimentCourse1.setDeleted(true);
//       更新新信息
       if(trainingProgramExperimentCourseMapper.updateByPrimaryKeySelective(trainingProgramExperimentCourse)<=0)
       {
           return false;
       }
//       插入旧信息
       if(trainingProgramExperimentCourseMapper.insert(trainingProgramExperimentCourse1)<=0)
       {
           return false;
       }else return true;*/

        return trainingProgramExperimentCourseMapper.updateByPrimaryKeySelective(trainingProgramExperimentCourse)> 0 ? true : false;
    }
    /**
     * 查找TrainingProgramExperimentCourse信息
     * @param id
     * @return
     */
    @Override
    public TrainingProgramExperimentCourse select(long id) {
        return trainingProgramExperimentCourseMapper.selectByPrimaryKey(id);
    }
    /**
     * 分页查询返回所有TrainingProgramExperimentCourse信息
     * @param pageNum
     * @return
     */
    @Override
    public PageInfo<TrainingProgramExperimentCourse> selectPage(int pageNum) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效

        List<TrainingProgramExperimentCourse> trainingProgramExperimentCourses= trainingProgramExperimentCourseMapper.selectByExample(null);   //  无条件查找商品
        if(trainingProgramExperimentCourses  != null)
            return new PageInfo<>(trainingProgramExperimentCourses );
        else
            return null;
    }
    /**
     *  根据TrainingProgramExperimentCourse表学校id分页查询并返回TrainingProgramExperimentCourse信息
     * @param pageNum
     * @param university_Id
     * @return
     */
    @Override
    public PageInfo<TrainingProgramExperimentCourse> selectPageByUniversityID(int pageNum, long university_Id) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        TrainingProgramExperimentCourseExample example = new TrainingProgramExperimentCourseExample();
        TrainingProgramExperimentCourseExample .Criteria criteria = example.createCriteria();
        criteria.andUniversityIdEqualTo(university_Id).andDeletedEqualTo(false);
        // 根据条件查询
        List<TrainingProgramExperimentCourse> products = trainingProgramExperimentCourseMapper.selectByExample(example);   //  无条件查找商品
        if(products != null)
            return new PageInfo<>(products);
        else
            return null;
    }
    /**
     *  根据TrainingProgramExperimentCourse表培养方案内容id分页查询并返回TrainingProgramExperimentCourse信息
     * @param pageNum
     * @param training_program_Id
     * @return
     */
    @Override
    public PageInfo<TrainingProgramExperimentCourse> selectPageByTrainingProgramID(int pageNum, long training_program_Id) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        TrainingProgramExperimentCourseExample example = new TrainingProgramExperimentCourseExample();
        TrainingProgramExperimentCourseExample .Criteria criteria = example.createCriteria();
        criteria.andTrainingProgramIdEqualTo(training_program_Id).andDeletedEqualTo(false);
        // 根据条件查询
        List<TrainingProgramExperimentCourse> products = trainingProgramExperimentCourseMapper.selectByExample(example);   //  无条件查找商品
        if(products != null)
            return new PageInfo<>(products);
        else
            return null;
    }
    /**
     *  根据TrainingProgramExperimentCourse表课程id分页查询并返回TrainingProgramExperimentCourse信息
     * @param pageNum
     * @param Course_Id
     * @return
     */
    @Override
    public PageInfo<TrainingProgramExperimentCourse>  selectPageByCourseID(int pageNum, long Course_Id) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        TrainingProgramExperimentCourseExample example = new TrainingProgramExperimentCourseExample();
        TrainingProgramExperimentCourseExample .Criteria criteria = example.createCriteria();
        criteria.andCourseIdEqualTo(Course_Id).andDeletedEqualTo(false);
        // 根据条件查询
        List<TrainingProgramExperimentCourse> products = trainingProgramExperimentCourseMapper.selectByExample(example);   //  无条件查找商品
        if(products != null)
            return new PageInfo<>(products);
        else
            return null;
    }
    /**
     * 查找所有TrainingProgramExperimentCourse信息
     * @return
     */
    @Override
    public List<TrainingProgramExperimentCourse> selectAll() {
        TrainingProgramExperimentCourseExample example=new TrainingProgramExperimentCourseExample();
        TrainingProgramExperimentCourseExample.Criteria criteria=example.createCriteria();
        criteria.andDeletedEqualTo(false);
        return trainingProgramExperimentCourseMapper.selectByExample(example);
    }
}
