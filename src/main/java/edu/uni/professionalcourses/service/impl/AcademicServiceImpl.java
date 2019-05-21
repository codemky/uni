package edu.uni.professionalcourses.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.professionalcourses.bean.Academic;
import edu.uni.professionalcourses.bean.AcademicExample;
import edu.uni.professionalcourses.config.ProfessionalCoursesConfig;
import edu.uni.professionalcourses.mapper.AcademicMapper;
import edu.uni.professionalcourses.service.AcademicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 author:  曹中耀
 create:  2019.5.11
 modified:  2019.5.11
 description：对  AcademicService的方法的实现
 */
@Service
public class AcademicServiceImpl implements AcademicService {
    @Autowired
    private AcademicMapper academicMapper;
    @Autowired
    private ProfessionalCoursesConfig globalConfig;
    @Override
    /**
     * 新增Academic信息
     * @param academic
     * @return
     */
    public boolean insert(Academic academic) {

        return academicMapper.insert(academic) > 0 ? true : false;
    }
    /**
     * 删除Academic信息
     * @param id
     * @return
     */
    @Override
    public boolean delete(long id) {
        Academic academic1=new Academic();
        academic1=academicMapper.selectByPrimaryKey(id);
        academic1.setDeleted(true);
        if(academicMapper.updateByPrimaryKeySelective(academic1)<0)
        {
            return false;
        }
        else return true;
//        return preCourseMapper.deleteByPrimaryKey(id) > 0 ? true : false;
    }
    /**
     * 更新Academic信息
     * @param academic
     * @return
     */
    @Override
    public boolean update(Academic academic) {
//        保存旧信息
        Academic academic1=new Academic();
        academic1=academicMapper.selectByPrimaryKey(academic.getId());
        academic1.setDeleted(true);
//        更新新信息
        if(academicMapper.updateByPrimaryKeySelective(academic)<0)
        {
            return false;

        }
        if(academicMapper.insert(academic1)<0)
        {
            return false;

        }
        else return true;

//        return preCourseMapper.updateByPrimaryKeySelective(preCourse) > 0 ? true : false;
    }
    /**
     * 查找Academic信息
     * @param id
     * @return
     */
    @Override
    public Academic select(long id) {
        Academic academic1=academicMapper.selectByPrimaryKey(id);
//        判断如果输入id查找的信息有效时显示信息，无效显示null
        if(academic1.getDeleted())
        {
            return null;
        }
        else return academic1;
        //return preCourseMapper.selectByPrimaryKey(id);
    }
    /**
     * 分页查询返回所有Academic信息
     * @param pageNum
     * @return
     */
    @Override
    public PageInfo<Academic> selectPage(int pageNum) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        AcademicExample example = new AcademicExample();
        AcademicExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(false);// 开启分页查询，第一次切仅第一次查询时生效
        List<Academic> academics= academicMapper.selectByExample(example);   //  无条件查找商品
        if(academics  != null)
            return new PageInfo<>(academics );
        else
            return null;
    }

    /**
     * 查找所有Academic信息
     * @return
     */
    @Override
    public List<Academic> selectAll() {
        AcademicExample example=new AcademicExample();
        AcademicExample.Criteria criteria=example.createCriteria();
        criteria.andDeletedEqualTo(false);
        return academicMapper.selectByExample(example);
    }
}
