package edu.uni.professionalcourses.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.professionalcourses.bean.DisciplineCategory;
import edu.uni.professionalcourses.bean.DisciplineCategoryExample;
import edu.uni.professionalcourses.config.ProfessionalCoursesConfig;
import edu.uni.professionalcourses.mapper.DisciplineCategoryMapper;
import edu.uni.professionalcourses.mapper.SpecialtyMapper;
import edu.uni.professionalcourses.service.DisciplineCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 author:  曹中耀
 create:  2019.5.11
 modified:  2019.5.11
 description：对DisciplineCategoryService的方法的实现
 */
@Service
public class DisciplineCategoryServiceImpl implements DisciplineCategoryService {

    @Autowired
    private DisciplineCategoryMapper disciplineCategoryMapper;
    @Autowired
    private SpecialtyMapper specialtyMapper;
    @Autowired
    private ProfessionalCoursesConfig globalConfig;
    @Override
    /**
     * 新增DisciplineCategory信息
     * @param disciplineCategory
     * @return
     */
    public boolean insert(DisciplineCategory disciplineCategory) {
        return disciplineCategoryMapper.insert(disciplineCategory) > 0 ? true : false;
    }

    @Override
    /**
     * 删除DisciplineCategory信息
     * @param disciplineCategory
     * @return
     */
    public boolean delete(long id) {
      /*  DisciplineCategory disciplineCategory1=disciplineCategoryMapper.selectByPrimaryKey(id);
        disciplineCategory1.setDeleted(true);
        if(disciplineCategoryMapper.updateByExampleSelective(disciplineCategory1)<=0)
        {
            return false;
        }
        return true;*/
      return disciplineCategoryMapper.deleteByPrimaryKey(id)>0?true:false;
    }

    @Override
    /**
     * 更新DisciplineCategory信息
     * @param disciplineCategory
     * @return
     */
    public boolean update(DisciplineCategory disciplineCategory) {
        DisciplineCategory disciplineCategory1=disciplineCategoryMapper.selectByPrimaryKey(disciplineCategory.getId());
        disciplineCategory1.setDeleted(true);
        if(disciplineCategoryMapper.updateByPrimaryKeySelective(disciplineCategory)<=0)
        {
            return false;
        }
        if(disciplineCategoryMapper.insert(disciplineCategory1)<=0)
        {
            return  false;
        }
        return true;
    }

    @Override
    /**
     * 查找DisciplineCategory信息
     * @param id
     * @return
     */
    public DisciplineCategory select(long id) {
        DisciplineCategory disciplineCategory1= disciplineCategoryMapper.selectByPrimaryKey(id);
//        如果该ID的信息有效;
        if(disciplineCategory1.getDeleted()){
            return null;
        }else return disciplineCategory1;
    }

    @Override
    /**
     * 分页查询返回所有DisciplineCategory信息
     * @param pageNum
     * @return
     */
    public PageInfo<DisciplineCategory> selectPage(int pageNum) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        DisciplineCategoryExample example = new DisciplineCategoryExample ();
        DisciplineCategoryExample .Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(false);// 开启分页查询，第一次切仅第一次查询时生效
        List<DisciplineCategory> disciplineCategories= disciplineCategoryMapper.selectByExample(example);   //  条件查找商品
        if(disciplineCategories  != null)
            return new PageInfo<>(disciplineCategories );
        else
            return null;
    }

    @Override
    public List<DisciplineCategory> selectAll() {

        DisciplineCategoryExample example=new DisciplineCategoryExample();
        DisciplineCategoryExample.Criteria criteria=example.createCriteria();
        criteria.andDeletedEqualTo(false);
        return disciplineCategoryMapper.selectByExample(example);
    }
}
