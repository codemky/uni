package edu.uni.professionalcourses.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.professionalcourses.bean.SyllabusTeachingContent;
import edu.uni.professionalcourses.bean.SyllabusTeachingContentExample;
import edu.uni.professionalcourses.config.ProfessionalCoursesConfig;
import edu.uni.professionalcourses.mapper.SyllabusTeachingContentMapper;
import edu.uni.professionalcourses.service.SyllabusTeachingContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 author:  曹中耀
 create:  2019.4.30
 modified:  2019.4.30
 description：对 SyllabusTeachingContentService 的方法的实现
 */
@Service
public class SyllabusTeachingContentServiceImpl implements SyllabusTeachingContentService {
    @Autowired
    private SyllabusTeachingContentMapper syllabusTeachingContentMapper;
    @Autowired
    private ProfessionalCoursesConfig globalConfig;
    @Override
    /**
     * 新增SyllabusTeachingContent信息
     * @param syllabusTeachingContent
     * @return
     */
    public boolean insert(SyllabusTeachingContent syllabusTeachingContent) {


        return syllabusTeachingContentMapper.insert(syllabusTeachingContent) > 0 ? true : false;
    }
    /**
     * 删除SyllabusTeachingContent信息
     * @param id
     * @return
     */
    @Override
    public boolean delete(long id) {
        SyllabusTeachingContent syllabusTeachingContent1=new SyllabusTeachingContent();
        syllabusTeachingContent1=syllabusTeachingContentMapper.selectByPrimaryKey(id);
        syllabusTeachingContent1.setDeleted(true);
        if(syllabusTeachingContentMapper.updateByPrimaryKeySelective(syllabusTeachingContent1)<=0)
        {
            return false;
        }
        else return true;
//        return syllabusTeachingContentMapper.deleteByPrimaryKey(id) > 0 ? true : false;
    }
    /**
     * 更新SyllabusTeachingContent信息
     * @param syllabusTeachingContent
     * @return
     */
    @Override
    public boolean update(SyllabusTeachingContent syllabusTeachingContent) {
//        存旧信息
        SyllabusTeachingContent syllabusTeachingContent1=new SyllabusTeachingContent();
        syllabusTeachingContent1=syllabusTeachingContentMapper.selectByPrimaryKey(syllabusTeachingContent.getId());
        syllabusTeachingContent1.setDeleted(true);
//        更新新信息
        if(syllabusTeachingContentMapper.updateByPrimaryKeySelective(syllabusTeachingContent)<=0)
        {
            return false;
        }
//        插入旧信息
        if(syllabusTeachingContentMapper.insert(syllabusTeachingContent1)<=0)
        {
            return false;
        }else return true;


    }
    /**
     * 查找SyllabusTeachingContent信息
     * @param id
     * @return
     */
    @Override
    public SyllabusTeachingContent select(long id) {
        SyllabusTeachingContent syllabusTeachingContent1= syllabusTeachingContentMapper.selectByPrimaryKey(id);
//        如果该ID的信息有效;
        if(syllabusTeachingContent1.getDeleted()){
            return null;
        }else return syllabusTeachingContent1;
    }
    /**
     * 分页查询返回所有SyllabusTeachingContent信息
     * @param pageNum
     * @return
     */
    @Override
    public PageInfo<SyllabusTeachingContent> selectPage(int pageNum) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        SyllabusTeachingContentExample example = new SyllabusTeachingContentExample ();
        SyllabusTeachingContentExample .Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(false);// 开启分页查询，第一次切仅第一次查询时生效
        List<SyllabusTeachingContent> syllabusTeachingContents = syllabusTeachingContentMapper.selectByExample(example);   //  条件查找商品
        if(syllabusTeachingContents  != null)
            return new PageInfo<>(syllabusTeachingContents );
        else
            return null;
    }
    /**
     *  根据教学内容表学校id分页查询并返回SyllabusTeachingContent信息
     * @param pageNum
     * @param university_Id
     * @return
     */
    @Override
    public PageInfo<SyllabusTeachingContent> selectPageByUniversityID(int pageNum, long university_Id) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        SyllabusTeachingContentExample example = new SyllabusTeachingContentExample();
        SyllabusTeachingContentExample .Criteria criteria = example.createCriteria();
        criteria.andUniversityIdEqualTo(university_Id).andDeletedEqualTo(false);
        // 根据条件查询
        List<SyllabusTeachingContent> products = syllabusTeachingContentMapper.selectByExample(example);   //  无条件查找商品
        if(products != null)
            return new PageInfo<>(products);
        else
            return null;
    }
    /**
     *  根据教学内容表理论教学内容id分页查询并返回SyllabusTeachingContent信息
     * @param pageNum
     * @param course_syllabus_description_Id
     * @return
     */
    @Override
    public PageInfo<SyllabusTeachingContent> selectPageByCourseSyllabusDescriptionID(int pageNum, long course_syllabus_description_Id) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        SyllabusTeachingContentExample example = new SyllabusTeachingContentExample();
        SyllabusTeachingContentExample .Criteria criteria = example.createCriteria();
        criteria.andCourseSyllabusDescriptionIdEqualTo(course_syllabus_description_Id).andDeletedEqualTo(false);
        // 根据条件查询
        List<SyllabusTeachingContent> products = syllabusTeachingContentMapper.selectByExample(example);   //  无条件查找商品
        if(products != null)
            return new PageInfo<>(products);
        else
            return null;
    }
    /**
     * 查找所有SyllabusTeachingContent信息
     * @return
     */
    @Override
    public List<SyllabusTeachingContent> selectAll() {
        SyllabusTeachingContentExample example=new SyllabusTeachingContentExample();
        SyllabusTeachingContentExample.Criteria criteria=example.createCriteria();
        criteria.andDeletedEqualTo(false);
        return syllabusTeachingContentMapper.selectByExample(example);
    }
}
