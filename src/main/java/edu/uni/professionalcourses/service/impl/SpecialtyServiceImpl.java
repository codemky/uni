package edu.uni.professionalcourses.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.professionalcourses.bean.*;
import edu.uni.professionalcourses.config.ProfessionalCoursesConfig;
import edu.uni.professionalcourses.mapper.SimilarSpecialtyMapper;
import edu.uni.professionalcourses.mapper.SpecialtyCourseMapper;
import edu.uni.professionalcourses.mapper.SpecialtyMapper;
import edu.uni.professionalcourses.service.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 author:  曹中耀
 create:  2019.5.5
 modified:  2019.5.11
 description：对SpecialtyService的方法的实现
 */
@Service
public class SpecialtyServiceImpl implements SpecialtyService {
    @Autowired
    private SpecialtyMapper specialtyMapper;
    @Autowired
    private SpecialtyCourseMapper specialtyCourseMapper;
    @Autowired
    private SimilarSpecialtyMapper similarSpecialtyMapper;
    @Autowired
    private ProfessionalCoursesConfig globalConfig;
    @Override
    /**
     * 新增Specialty信息
     * @param specialty
     * @return
     */
    public boolean insert(Specialty specialty) {
        if(specialtyMapper.insert(specialty)<=0)
        {
            return false;
        }

        else return true;
    }
    /**
     * 删除Specialty信息
     * @param id
     * @return
     */
    @Override
    public boolean delete(long id) {
       /* //将专业表中该id信息无效
        Specialty specialty1=specialtyMapper.selectByPrimaryKey(id);
        specialty1.setDeleted(true);
        if(specialtyMapper.updateByPrimaryKeySelective(specialty1)<=0)
        {
            return false;
        }
        //将相近专业表中含有该id的信息无效
        SimilarSpecialtyExample example = new SimilarSpecialtyExample();
        SimilarSpecialtyExample.Criteria criteria1 = example.createCriteria();
        criteria1.andSpecialtyIdEqualTo(specialty1.getId()).andDeletedEqualTo(false);
        List<SimilarSpecialty> similarSpecialties1=similarSpecialtyMapper.selectByExample(example);
        SimilarSpecialty similarSpecialty=similarSpecialties1.get(0);
        similarSpecialtyMapper.deleteByPrimaryKey(similarSpecialty.getId());
        //将*/
        return specialtyMapper.deleteByPrimaryKey(id) > 0 ? true : false;
       // return true;
    }
    /**
     * 更新Specialty信息
     * @param specialty
     * @return
     */
    @Override
    public boolean update(Specialty specialty) {
        Specialty specialty1=specialtyMapper.selectByPrimaryKey(specialty.getId());
        specialty1.setDeleted(true);
        if(specialtyMapper.updateByPrimaryKeySelective(specialty)<=0)
        {
            return false;
        }
        if(specialtyMapper.insert(specialty1)<=0)
        {
            return  false;
        }
    return true;
       // return specialtyMapper.updateByPrimaryKeySelective(specialty) > 0 ? true : false;
    }
    /**
     * 查找Specialty信息
     * @param id
     * @return
     */
    @Override
    public Specialty select(long id) {
        Specialty specialty1=specialtyMapper.selectByPrimaryKey(id);
        //        判断如果输入id查找的信息有效时显示信息，无效显示null
        if(specialty1.getDeleted())
        {
            return null;
        }
        else return specialty1;
       // return specialtyMapper.selectByPrimaryKey(id);
    }
    /**
     * 分页查询返回所有Specialty信息
     * @param pageNum
     * @return
     */
    @Override
    public PageInfo<Specialty> selectPage(int pageNum) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        SpecialtyExample example = new SpecialtyExample();
        SpecialtyExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(false);// 开启分页查询，第一次切仅第一次查询时生效
        List<Specialty> similarSpecialties= specialtyMapper.selectByExample(example);   //  无条件查找商品
        if(similarSpecialties  != null)
            return new PageInfo<>(similarSpecialties );
        else
            return null;
    }
    /**
     *  根据Specialty表学校id分页查询并返回Specialty信息
     * @param pageNum
     * @param university_Id
     * @return
     */
    @Override
    public PageInfo<Specialty> selectPageByUniversityID(int pageNum, long university_Id) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        SpecialtyExample example = new SpecialtyExample();
        SpecialtyExample .Criteria criteria = example.createCriteria();
        criteria.andUniversityIdEqualTo(university_Id).andDeletedEqualTo(false);
        // 根据条件查询
        List<Specialty> products = specialtyMapper.selectByExample(example);   //  无条件查找商品
        if(products != null)
            return new PageInfo<>(products);
        else
            return null;
    }
    /**
     *  根据Specialty表部门id分页查询并返回Specialty信息
     * @param pageNum
     * @param department_id
     * @return
     */
    @Override
    public PageInfo<Specialty> selectPageByDepartmentID(int pageNum, long department_id) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        SpecialtyExample example = new SpecialtyExample();
       SpecialtyExample .Criteria criteria = example.createCriteria();
        criteria.andDepartmentIdEqualTo(department_id).andDeletedEqualTo(false);

        // 根据条件查询
        List<Specialty> products = specialtyMapper.selectByExample(example);   //  无条件查找商品
        if(products != null)
            return new PageInfo<>(products);
        else
            return null;
    }
    /**
     *  根据Specialty表学科门类id分页查询并返回Specialty信息
     * @param pageNum
     * @param discipline_category_id
     * @return
     */
    @Override
    public PageInfo<Specialty> selectPageByDisciplineCategoryID(int pageNum, long discipline_category_id) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        SpecialtyExample example = new SpecialtyExample();
        SpecialtyExample .Criteria criteria = example.createCriteria();
        criteria.andDisciplineCategoryIdEqualTo(discipline_category_id).andDeletedEqualTo(false);
        // 根据条件查询
        List<Specialty> products = specialtyMapper.selectByExample(example);   //  无条件查找商品
        if(products != null)
            return new PageInfo<>(products);
        else
            return null;
    }
    /**
     *  根据Specialty表一级学科id分页查询并返回Specialty信息
     * @param pageNum
     * @param first_level_discipline_id
     * @return
     */
    @Override
    public PageInfo<Specialty> selectPageByFirstLevelDisciplineID(int pageNum, long first_level_discipline_id) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        SpecialtyExample example = new SpecialtyExample();
        SpecialtyExample .Criteria criteria = example.createCriteria();
        criteria.andFirstLevelDisciplineIdEqualTo(first_level_discipline_id).andDeletedEqualTo(false);
        // 根据条件查询
        List<Specialty> products = specialtyMapper.selectByExample(example);   //  无条件查找商品
        if(products != null)
            return new PageInfo<>(products);
        else
            return null;
    }
    /**
     *  根据Specialty表二级学科id分页查询并返回Specialty信息
     * @param pageNum
     * @param second_level_discipline_id
     * @return
     */
    @Override
    public PageInfo<Specialty> selectPageBySecondLevelDisciplineID(int pageNum, long second_level_discipline_id) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        SpecialtyExample example = new SpecialtyExample();
        SpecialtyExample .Criteria criteria = example.createCriteria();
        criteria.andSecondLevelDisciplineIdEqualTo(second_level_discipline_id).andDeletedEqualTo(false);
        // 根据条件查询
        List<Specialty> products = specialtyMapper.selectByExample(example);   //  无条件查找商品
        if(products != null)
            return new PageInfo<>(products);
        else
            return null;
    }
    /**
     *  根据Specialty表培养的学历等级，专业可以获得的学位id分页查询并返回Specialty信息
     * @param pageNum
     * @param academic_id
     * @return
     */
    @Override
    public PageInfo<Specialty> selectPageByAcademicID(int pageNum, long academic_id) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        SpecialtyExample example = new SpecialtyExample();
        SpecialtyExample .Criteria criteria = example.createCriteria();
        criteria.andAcademicIdEqualTo(academic_id).andDeletedEqualTo(false);
        // 根据条件查询
        List<Specialty> products = specialtyMapper.selectByExample(example);   //  无条件查找商品
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
    public List<Specialty> selectAll() {

        SpecialtyExample example=new SpecialtyExample();
        SpecialtyExample.Criteria criteria=example.createCriteria();
        criteria.andDeletedEqualTo(false);
        return specialtyMapper.selectByExample(example);
    }
}
