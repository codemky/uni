package edu.uni.professionalcourses.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.professionalcourses.bean.AcademicDegree;
import edu.uni.professionalcourses.bean.AcademicDegreeExample;
import edu.uni.professionalcourses.config.ProfessionalCoursesConfig;
import edu.uni.professionalcourses.mapper.AcademicDegreeMapper;
import edu.uni.professionalcourses.service.AcademicDegreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 author:  曹中耀
 create:  2019.5.11
 modified:  2019.5.11
 description：对  AcademicDegreeService的方法的实现
 */
@Service
public class AcademicDegreeServiceImpl implements AcademicDegreeService {
    @Autowired
    private AcademicDegreeMapper academicDegreecMapper;
    @Autowired
    private ProfessionalCoursesConfig globalConfig;
    @Override
    /**
     * 新增AcademicDegree信息
     * @param  academicDegree
     * @return
     */
    public boolean insert(AcademicDegree academicDegree) {

        return academicDegreecMapper.insert(academicDegree) > 0 ? true : false;
    }
    /**
     * 删除AcademicDegree信息
     * @param id
     * @return
     */
    @Override
    public boolean delete(long id) {
        AcademicDegree academicDegree1=new AcademicDegree();
        academicDegree1=academicDegreecMapper.selectByPrimaryKey(id);
        academicDegree1.setDeleted(true);
        if(academicDegreecMapper.updateByPrimaryKeySelective(academicDegree1)<0)
        {
            return false;
        }
        else return true;
//        return preCourseMapper.deleteByPrimaryKey(id) > 0 ? true : false;
    }
    /**
     * 更新AcademicDegree信息
     * @param academicDegree
     * @return
     */
    @Override
    public boolean update(AcademicDegree academicDegree) {
//        保存旧信息
        AcademicDegree academicDegree1=new AcademicDegree();
        academicDegree1=academicDegreecMapper.selectByPrimaryKey(academicDegree.getId());
        academicDegree1.setDeleted(true);
//        更新新信息
        if(academicDegreecMapper.updateByPrimaryKeySelective(academicDegree)<0)
        {
            return false;

        }
        if(academicDegreecMapper.insert(academicDegree1)<0)
        {
            return false;

        }
        else return true;

//        return preCourseMapper.updateByPrimaryKeySelective(preCourse) > 0 ? true : false;
    }
    /**
     * 查找AcademicDegree信息
     * @param id
     * @return
     */
    @Override
    public AcademicDegree select(long id) {
        AcademicDegree academicDegree1=academicDegreecMapper.selectByPrimaryKey(id);
//        判断如果输入id查找的信息有效时显示信息，无效显示null
        if(academicDegree1.getDeleted())
        {
            return null;
        }
        else return academicDegree1;
        //return preCourseMapper.selectByPrimaryKey(id);
    }
    /**
     * 分页查询返回所有AcademicDegree信息
     * @param pageNum
     * @return
     */
    @Override
    public PageInfo<AcademicDegree> selectPage(int pageNum) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        AcademicDegreeExample example = new AcademicDegreeExample();
        AcademicDegreeExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(false);// 开启分页查询，第一次切仅第一次查询时生效
        List<AcademicDegree> academicDegrees= academicDegreecMapper.selectByExample(example);   //  无条件查找商品
        if(academicDegrees  != null)
            return new PageInfo<>(academicDegrees );
        else
            return null;
    }

    /**
     * 查找所有AcademicDegree信息
     * @return
     */
    @Override
    public List<AcademicDegree> selectAll() {
        AcademicDegreeExample example=new AcademicDegreeExample();
        AcademicDegreeExample.Criteria criteria=example.createCriteria();
        criteria.andDeletedEqualTo(false);
        return academicDegreecMapper.selectByExample(example);
    }
}
