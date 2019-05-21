package edu.uni.professionalcourses.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.professionalcourses.bean.SimilarSpecialty;
import edu.uni.professionalcourses.bean.SimilarSpecialtyExample;
import edu.uni.professionalcourses.config.ProfessionalCoursesConfig;
import edu.uni.professionalcourses.mapper.SimilarSpecialtyMapper;
import edu.uni.professionalcourses.service.SimilarSpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



/**
 author:  曹中耀
 create:  2019.5.5
 modified:  2019.5.5
 description：对  SimilarSpecialtyService的方法的实现
 */
@Service
public class SimilarSpecialtyServiceImpl implements SimilarSpecialtyService {
    @Autowired
    private SimilarSpecialtyMapper similarSpecialtyMapper;
    @Autowired
    private ProfessionalCoursesConfig globalConfig;
    @Override
    /**
     * 新增SimilarSpecialty信息
     * @param similarSpecialty
     * @return
     */
    public boolean insert(SimilarSpecialty similarSpecialty) {
        return similarSpecialtyMapper.insert(similarSpecialty) > 0 ? true : false;
    }
    /**
     * 删除SimilarSpecialty信息
     * @param id
     * @return
     */
    @Override
    public boolean delete(long id) {

       SimilarSpecialty similarSpecialty1=new SimilarSpecialty();
        similarSpecialty1=similarSpecialtyMapper.selectByPrimaryKey(id);
        similarSpecialty1.setDeleted(true);
        if(similarSpecialtyMapper.updateByPrimaryKeySelective(similarSpecialty1)<=0)
        {
            return false;
        }
        else return true;
    }
    /**
     * 更新SimilarSpecialty信息
     * @param similarSpecialty
     * @return
     */
    @Override
    public boolean update(SimilarSpecialty similarSpecialty) {
        return similarSpecialtyMapper.updateByPrimaryKeySelective(similarSpecialty) > 0 ? true : false;
    }
    /**
     * 查找SimilarSpecialty信息
     * @param id
     * @return
     */
    @Override
    public SimilarSpecialty select(long id) {
        SimilarSpecialty similarSpecialty1= similarSpecialtyMapper.selectByPrimaryKey(id);
//        如果该ID的信息有效;
        if(similarSpecialty1.getDeleted()){
            return null;
        }else return similarSpecialty1;
    }
    /**
     * 分页查询返回所有SimilarSpecialty信息
     * @param pageNum
     * @return
     */
    @Override
    public PageInfo<SimilarSpecialty> selectPage(int pageNum) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        SimilarSpecialtyExample example = new SimilarSpecialtyExample ();
        SimilarSpecialtyExample .Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(false);// 开启分页查询，第一次切仅第一次查询时生效
        List<SimilarSpecialty> similarSpecialties= similarSpecialtyMapper.selectByExample(example);   //  条件查找商品
        if(similarSpecialties  != null)
            return new PageInfo<>(similarSpecialties );
        else
            return null;
    }
    /**
     *  根据SimilarSpecialty表学校id分页查询并返回SimilarSpecialty信息
     * @param pageNum
     * @param university_Id
     * @return
     */
    @Override
    public PageInfo<SimilarSpecialty> selectPageByUniversityID(int pageNum, long university_Id) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        SimilarSpecialtyExample example = new SimilarSpecialtyExample();
        SimilarSpecialtyExample .Criteria criteria = example.createCriteria();
        criteria.andUniversityIdEqualTo(university_Id).andDeletedEqualTo(false);
        // 根据条件查询
        List<SimilarSpecialty> products = similarSpecialtyMapper.selectByExample(example);   //  无条件查找商品
        if(products != null)
            return new PageInfo<>(products);
        else
            return null;
    }
    /**
     *  根据SimilarSpecialty表专业id分页查询并返回SimilarSpecialty信息
     * @param pageNum
     * @param specialty_id
     * @return
     */
    @Override
    public PageInfo<SimilarSpecialty> selectPageBySpecialtyID(int pageNum, long specialty_id) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        SimilarSpecialtyExample example = new SimilarSpecialtyExample();
        SimilarSpecialtyExample .Criteria criteria = example.createCriteria();
        criteria.andSpecialtyIdEqualTo(specialty_id).andDeletedEqualTo(false);;
        // 根据条件查询
        List<SimilarSpecialty> products = similarSpecialtyMapper.selectByExample(example);   //  无条件查找商品
        if(products != null)
            return new PageInfo<>(products);
        else
            return null;
    }
    /**
     *  根据PreCourse表相近专业id分页查询并返回PreCourse信息
     * @param pageNum
     * @param similar_specialty_id
     * @return
     */
    @Override
    public PageInfo<SimilarSpecialty>  selectPageBySimilarSpecialtyID(int pageNum, long similar_specialty_id) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        SimilarSpecialtyExample example = new SimilarSpecialtyExample();
        SimilarSpecialtyExample .Criteria criteria = example.createCriteria();
        criteria.andSimilarSpecialtyIdEqualTo(similar_specialty_id).andDeletedEqualTo(false);;
        // 根据条件查询
        List<SimilarSpecialty> products = similarSpecialtyMapper.selectByExample(example);   //  无条件查找商品
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
    public List<SimilarSpecialty> selectAll() {

        SimilarSpecialtyExample example=new SimilarSpecialtyExample();
        SimilarSpecialtyExample.Criteria criteria=example.createCriteria();
        criteria.andDeletedEqualTo(false);
        return similarSpecialtyMapper.selectByExample(example);
    }
}
