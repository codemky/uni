package edu.uni.place.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.example.config.ExampleConfig;
import edu.uni.place.bean.*;
import edu.uni.place.mapper.FieldMapper;
import edu.uni.place.mapper.SchoolareaMapper;
import edu.uni.place.service.SchoolareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 潘绍豪
 * @Create 2019/4/29
 * @Description
 * @Since 1.0.0
 */
@Service
public class SchoolareaServiceImpl implements SchoolareaService {
    @Autowired
    private SchoolareaMapper schoolareaMapper;
    @Autowired
    private FieldMapper fieldMapper;

    @Autowired
    private ExampleConfig globalConfig;

    /**
     * 新增学校功能区
     * @param schoolarea
     * @return
     */
    public boolean insert(Schoolarea schoolarea){
        return schoolareaMapper.insert(schoolarea) > 0 ? true : false;
    }

    /**
     * 删除学校功能区
     * @param id
     * @return
     */
    public boolean delete(Long id){
        Schoolarea schoolarea = new Schoolarea();
        schoolarea.setId((long) id);
        schoolarea.setDeleted(1);

        Field field = new Field();
        field.setDeleted(1);
        if(schoolareaMapper.updateByPrimaryKeySelective(schoolarea) > 0){
            //根据功能区删除所有场地
            FieldExample Fieldexample = new FieldExample();
            FieldExample.Criteria criteria = Fieldexample.createCriteria();
            criteria.andAreaIdEqualTo((long)id);
            fieldMapper.updateByExampleSelective(field,Fieldexample);
            return  true;
        }
        return  false;

    }

    /**
     * 修改学校功能区
     * @param schoolarea
     * @return
     */
    public boolean update(Schoolarea schoolarea){
        //创建原数据备份
        Schoolarea NewSchoolarea = schoolareaMapper.selectByPrimaryKey(schoolarea.getId());
        NewSchoolarea.setDeleted(1);
        schoolareaMapper.insert(NewSchoolarea);
        //修改原数据
        SchoolareaExample example = new SchoolareaExample();
        SchoolareaExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(schoolarea.getId());
        return schoolareaMapper.updateByExampleSelective(schoolarea,example) > 0 ? true : false;
    }

    /**
     * 查询学校功能区详情
     * @param id
     * @return
     */
    public Schoolarea select(Long id){
        SchoolareaExample example = new SchoolareaExample();
        example.or().andIdEqualTo((long) id).andDeletedEqualTo(0);
        List<Schoolarea> list = schoolareaMapper.selectByExample(example);
        if (list.isEmpty()) return null;
        return  list.get(0);


    }

    /**
     * 分校区分页查询所有学校功能区，并返回其他信息
     * @param pageNum
     * @param schoolId
     * @return
     */
    public PageInfo<Schoolarea> selectPageByUIdSId(int pageNum, Long schoolId){
        globalConfig.setPageSize(10);
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        SchoolareaExample example = new SchoolareaExample();
        SchoolareaExample.Criteria criteria = example.createCriteria();
        criteria.andSchoolIdEqualTo((long) schoolId).andDeletedEqualTo(0);
        // 根据条件查询
        List<Schoolarea> schoolareas = schoolareaMapper.selectByExample(example); //  无条件查找
        if(schoolareas != null)
            return new PageInfo<>(schoolareas);
        else
            return null;
    }

    /**
     * 分校区查询所有功能区，返回功能区
     * @param schoolId
     * @return
     */
    public List<Schoolarea> selectByUIdSId( Long schoolId) {
        SchoolareaExample example = new SchoolareaExample();
        example.or().andSchoolIdEqualTo((long) schoolId).andDeletedEqualTo(0);
        return schoolareaMapper.selectByExample(example);
    }

    /**
     * @description: 分学校查询所有功能区，返回功能区
     * @author: 潘绍豪
     * @date: {2019/5/12} {0:58}
     */
    public List<Schoolarea> selectByUId(Long universityId) {
        SchoolareaExample example = new SchoolareaExample();
        example.or().andUniversityIdEqualTo(universityId).andDeletedEqualTo(0);
        return schoolareaMapper.selectByExample(example);
    }

    /**
    * @description: 根据功能区名称模糊查询
    * @author: 潘绍豪
    * @date: {2019/5/9} {19:51}
    */
    public PageInfo<Schoolarea> selectPageLikeName(int pageNum, String name) {
        globalConfig.setPageSize(10);
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        SchoolareaExample example = new SchoolareaExample();
        SchoolareaExample.Criteria criteria = example.createCriteria();
        criteria.andNameLike("%" + name + "%").andDeletedEqualTo(0);
        // 根据条件查询
        List<Schoolarea> schoolareas = schoolareaMapper.selectByExample(example); //  无条件查找
        if(schoolareas != null)
            return new PageInfo<>(schoolareas);
        else
            return null;
    }

    /**
     * @description: 根据学校id或者校区id来查询功能区
     * @author: 潘绍豪
     * @date: {2019/5/16} {16:39}
     */
    public List<Schoolarea> selectByUIdorSId(Long universityId, Long schoolId) {

        SchoolareaExample example = new SchoolareaExample();
        SchoolareaExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(0);
        if(schoolId != null){
            criteria.andSchoolIdEqualTo(schoolId);
        }else{
            criteria.andUniversityIdEqualTo(universityId);
        }
        return schoolareaMapper.selectByExample(example);





    }
}
