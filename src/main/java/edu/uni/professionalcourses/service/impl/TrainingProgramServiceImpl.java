package edu.uni.professionalcourses.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.professionalcourses.bean.*;
import edu.uni.professionalcourses.config.ProfessionalCoursesConfig;
import edu.uni.professionalcourses.mapper.TrainingProgramExperimentCourseMapper;
import edu.uni.professionalcourses.mapper.TrainingProgramMainCourseMapper;
import edu.uni.professionalcourses.mapper.TrainingProgramMapper;
import edu.uni.professionalcourses.service.TrainingProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 author:  曹中耀
 create:  2019.4.30
 modified:  2019.4.30
 description：对 TrainingProgramService 的方法的实现
 */
@Service
public class TrainingProgramServiceImpl implements TrainingProgramService {
    @Autowired
    private TrainingProgramMapper trainingProgramMapper;
    @Autowired
    private TrainingProgramExperimentCourseMapper trainingProgramExperimentCourseMapper;
    @Autowired
    private TrainingProgramMainCourseMapper trainingProgramMainCourseMapper;
    @Autowired
    private ProfessionalCoursesConfig globalConfig;
    @Override
    /**
     * 新增TrainingProgram信息
     * @param trainingProgram
     * @return
     */
    public boolean insert(TrainingProgram trainingProgram) {
        return trainingProgramMapper.insert(trainingProgram) > 0 ? true : false;
    }
    /**
     * 删除TrainingProgram信息,还要删除TrainingProgramMainCourse和TrainingProgramExperimentCourse中相应的关系
     * @param id
     * @return
     */
    @Override
    public boolean delete(long id) {
        //获取修改前数据
        TrainingProgram trainingProgram1=trainingProgramMapper.selectByPrimaryKey(id);
        trainingProgram1.setDeleted(true);
        //将本表记录置为无效
        if(trainingProgramMapper.updateByPrimaryKeySelective(trainingProgram1)<=0)
        {
            return false;
        }
        //查找表 TrainingProgramExperimentCourse 中training_program_id与id相同的信息
        TrainingProgramExperimentCourseExample example1 = new TrainingProgramExperimentCourseExample();
        TrainingProgramExperimentCourseExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andTrainingProgramIdEqualTo(trainingProgram1.getId()).andDeletedEqualTo(false);
        List<TrainingProgramExperimentCourse> trainingProgramExperimentCourses=trainingProgramExperimentCourseMapper.selectByExample(example1);
        TrainingProgramExperimentCourse trainingProgramExperimentCourse=trainingProgramExperimentCourses.get(0);
        //将TrainingProgramExperimentCourse表中信息置为无效
        trainingProgramExperimentCourse.setDeleted(true);
        if(trainingProgramExperimentCourseMapper.updateByPrimaryKeySelective(trainingProgramExperimentCourse)<=0){
            return false;
        }
        //查找表TrainingProgramMainCourse中training_program_id与id相同的信息
        TrainingProgramMainCourseExample example2 = new TrainingProgramMainCourseExample();
        TrainingProgramMainCourseExample.Criteria criteria2 = example2.createCriteria();
        criteria2.andTrainingProgramIdEqualTo(trainingProgram1.getId()).andDeletedEqualTo(false);
        List<TrainingProgramMainCourse> trainingProgramMainCourses=trainingProgramMainCourseMapper.selectByExample(example2);
        TrainingProgramMainCourse trainingProgramMainCourse=trainingProgramMainCourses.get(0);
        //将TrainingProgramMainCourse表中信息置为无效
        trainingProgramMainCourse.setDeleted(false);
        if(trainingProgramMainCourseMapper.updateByPrimaryKeySelective(trainingProgramMainCourse)<=0){
            return false;
        }
        else
            return true;
    }
    /**
     * 更新TrainingProgram信息
     * @param trainingProgram
     * @return
     */
    @Override
    public boolean update(TrainingProgram trainingProgram) {
//        保存旧信息
        TrainingProgram trainingProgram1=trainingProgramMapper.selectByPrimaryKey(trainingProgram.getId());
//      旧信息设为无效
        trainingProgram1.setDeleted(true);
//       更新新信息
        if(trainingProgramMapper.updateByPrimaryKeySelective(trainingProgram)<=0)
        {
            return false;
        }
//       插入旧信息
        if(trainingProgramMapper.insert(trainingProgram1)<=0)
        {
            return false;
        }else return true;

    }
    /**
     * 查找TrainingProgram信息
     * @param id
     * @return
     */
    @Override
    public TrainingProgram select(long id) {
        TrainingProgram trainingProgram1=trainingProgramMapper.selectByPrimaryKey(id);
//        判断如果输入id查找的信息有效时显示信息，无效显示null
        if(trainingProgram1.getDeleted())
        {
            return null;
        }
        else return trainingProgram1;

    }
    /**
     * 分页查询返回所有TrainingProgram信息
     * @param pageNum
     * @return
     */
    @Override
    public PageInfo<TrainingProgram> selectPage(int pageNum) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        TrainingProgramExample example = new TrainingProgramExample();
        TrainingProgramExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(false);// 开启分页查询，第一次切仅第一次查询时生效
        List<TrainingProgram> trainingPrograms= trainingProgramMapper.selectByExample(null);   //  查找有效信息
        if(trainingPrograms  != null)
            return new PageInfo<>(trainingPrograms );
        else
            return null;
    }
    /**
     *  根据专业培养方案内容表学校id分页查询并返回TrainingProgram信息
     * @param pageNum
     * @param university_Id
     * @return
     */
    @Override
    public PageInfo<TrainingProgram> selectPageByUniversityID(int pageNum, long university_Id) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        TrainingProgramExample example = new TrainingProgramExample();
        TrainingProgramExample .Criteria criteria = example.createCriteria();
        criteria.andUniversityIdEqualTo(university_Id).andDeletedEqualTo(false);
        // 根据条件查询
        List<TrainingProgram> products = trainingProgramMapper.selectByExample(example);   //  无条件查找商品
        if(products != null)
            return new PageInfo<>(products);
        else
            return null;
    }
    /**
     *  根据专业培养方案内容表专业id分页查询并返回TrainingProgram信息
     * @param pageNum
     * @param specialty_Id
     * @return
     */
    @Override
    public PageInfo<TrainingProgram> selectPageBySpecialtyID(int pageNum, long specialty_Id) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        TrainingProgramExample example = new TrainingProgramExample();
        TrainingProgramExample .Criteria criteria = example.createCriteria();
        criteria.andSpecialtyIdEqualTo(specialty_Id).andDeletedEqualTo(false);
        // 根据条件查询
        List<TrainingProgram> products = trainingProgramMapper.selectByExample(example);   //  无条件查找商品
        if(products != null)
            return new PageInfo<>(products);
        else
            return null;
    }
    /**
     * 查找所有TrainingProgram信息
     * @return
     */
    @Override
    public List<TrainingProgram> selectAll() {
        TrainingProgramExample example=new TrainingProgramExample();
        TrainingProgramExample.Criteria criteria=example.createCriteria();
        criteria.andDeletedEqualTo(false);
        return trainingProgramMapper.selectByExample(example);
    }
}
