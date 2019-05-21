package edu.uni.professionalcourses.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.professionalcourses.bean.PreCourse;
import edu.uni.professionalcourses.bean.PreCourseExample;
import edu.uni.professionalcourses.config.ProfessionalCoursesConfig;
import edu.uni.professionalcourses.mapper.PreCourseMapper;
import edu.uni.professionalcourses.service.PreCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 author:  曹中耀
 create:  2019.5.5
 modified:  2019.5.11
 description：对 PreCourseService 的方法的实现
 */
@Service
public class PreCourseImpl implements PreCourseService {
    @Autowired
    private PreCourseMapper preCourseMapper;
    @Autowired
    private ProfessionalCoursesConfig globalConfig;
    @Override
    /**
     * 新增PreCourse信息
     * @param preCourse
     * @return
     */
    public boolean insert(PreCourse preCourse) {

        return preCourseMapper.insert(preCourse) > 0 ? true : false;
    }
    /**
     * 删除PreCourse信息
     * @param id
     * @return
     */
    @Override
    public boolean delete(long id) {
        PreCourse preCourse1=new PreCourse();
        preCourse1=preCourseMapper.selectByPrimaryKey(id);
        preCourse1.setDeleted(true);
        if(preCourseMapper.updateByPrimaryKeySelective(preCourse1)<0)
        {
            return false;
        }
        else return true;
//        return preCourseMapper.deleteByPrimaryKey(id) > 0 ? true : false;
    }
    /**
     * 更新PreCourse信息
     * @param preCourse
     * @return
     */
    @Override
    public boolean update(PreCourse preCourse) {
//        保存旧信息
        PreCourse preCourse1=new PreCourse();
        preCourse1=preCourseMapper.selectByPrimaryKey(preCourse.getId());
        preCourse1.setDeleted(true);
//        更新新信息
        if(preCourseMapper.updateByPrimaryKeySelective(preCourse)<0)
        {
            return false;

        }
        if(preCourseMapper.insert(preCourse1)<0)
        {
            return false;

        }
        else return true;

//        return preCourseMapper.updateByPrimaryKeySelective(preCourse) > 0 ? true : false;
    }
    /**
     * 查找PreCourse信息
     * @param id
     * @return
     */
    @Override
    public PreCourse select(long id) {
        PreCourse preCourse1=preCourseMapper.selectByPrimaryKey(id);
//        判断如果输入id查找的信息有效时显示信息，无效显示null
        if(preCourse1.getDeleted())
        {
            return null;
        }
        else return preCourse1;
        //return preCourseMapper.selectByPrimaryKey(id);
    }
    /**
     * 分页查询返回所有PreCourse信息
     * @param pageNum
     * @return
     */
    @Override
    public PageInfo<PreCourse> selectPage(int pageNum) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        PreCourseExample example = new PreCourseExample();
        PreCourseExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(false);// 开启分页查询，第一次切仅第一次查询时生效
        List<PreCourse> preCourses= preCourseMapper.selectByExample(example);   //  无条件查找商品
        if(preCourses  != null)
            return new PageInfo<>(preCourses );
        else
            return null;
    }
    /**
     *  根据PreCourse表学校id分页查询并返回PreCourse信息
     * @param pageNum
     * @param university_Id
     * @return
     */
    @Override
    public PageInfo<PreCourse> selectPageByUniversityID(int pageNum, long university_Id) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        PreCourseExample example = new PreCourseExample();
        PreCourseExample .Criteria criteria = example.createCriteria();
        criteria.andUniversityIdEqualTo(university_Id).andDeletedEqualTo(false);
        // 根据条件查询
        List<PreCourse> products = preCourseMapper.selectByExample(example);   //  无条件查找商品
        if(products != null)
            return new PageInfo<>(products);
        else
            return null;
    }
    /**
     *  根据PreCourse表先修课程id分页查询并返回PreCourse信息
     * @param pageNum
     * @param pre_course_id
     * @return
     */
    @Override
    public PageInfo<PreCourse> selectPageByPreCourseID(int pageNum, long pre_course_id) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        PreCourseExample example = new PreCourseExample();
        PreCourseExample .Criteria criteria = example.createCriteria();
        criteria.andPreCourseIdEqualTo(pre_course_id).andDeletedEqualTo(false);
        // 根据条件查询
        List<PreCourse> products = preCourseMapper.selectByExample(example);   //  无条件查找商品
        if(products != null)
            return new PageInfo<>(products);
        else
            return null;
    }
    /**
     *  根据PreCourse表课程id分页查询并返回PreCourse信息
     * @param pageNum
     * @param Course_Id
     * @return
     */
    @Override
    public PageInfo<PreCourse>  selectPageByCourseID(int pageNum, long Course_Id) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        PreCourseExample example = new PreCourseExample();
        PreCourseExample .Criteria criteria = example.createCriteria();
        criteria.andCourseIdEqualTo(Course_Id).andDeletedEqualTo(false);
        // 根据条件查询
        List<PreCourse> products = preCourseMapper.selectByExample(example);   //  无条件查找商品
        if(products != null)
            return new PageInfo<>(products);
        else
            return null;
    }
    /**
     * 查找所有PreCourse信息
     * @return
     */
    @Override
    public List<PreCourse> selectAll() {
        PreCourseExample example=new PreCourseExample();
        PreCourseExample.Criteria criteria=example.createCriteria();
        criteria.andDeletedEqualTo(false);
        return preCourseMapper.selectByExample(example);
    }
}
