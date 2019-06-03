package edu.uni.place.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.example.config.ExampleConfig;
import edu.uni.place.bean.Field;
import edu.uni.place.bean.FieldExample;
import edu.uni.place.bean.FieldtypeExample;
import edu.uni.place.bean.Schoolarea;
import edu.uni.place.mapper.FieldMapper;
import edu.uni.place.service.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 潘绍豪
 * @Create 2019/4/30
 * @Description
 * @Since 1.0.0
 */
@Service
public class FieldServiceImpl implements FieldService {
    @Autowired
    private FieldMapper fieldMapper;

    @Autowired
    private ExampleConfig globalConfig;


    /**
     * 新增场地信息
     * @param field
     * @return
     */
    public boolean insert(Field field){
        return fieldMapper.insert(field) > 0 ? true : false;
    }


    /**
     * 删除场地信息
     * @param id
     * @return
     */
    public boolean delete(Long id){
        Field field = new Field();
        field.setId((Long) id);
        field.setDeleted(1);


        return  fieldMapper.updateByPrimaryKeySelective(field) > 0;

    }


    /**
     * 修改场地信息
     * @param field
     * @return
     */
    public boolean update(Field field){
        //创建原数据备份
        Field Newfield = fieldMapper.selectByPrimaryKey(field.getId());
        Newfield.setDeleted(1);
        fieldMapper.insert(Newfield);
        //修改原数据
        FieldExample example = new FieldExample();
        FieldExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(field.getId());
        return fieldMapper.updateByExampleSelective(field,example) > 0 ? true : false;
    }

    /**
     * 查询场地信息详情
     * @param id
     * @return
     */
    public Field select(Long id){
        FieldExample example = new FieldExample();
        example.or().andIdEqualTo((Long) id).andDeletedEqualTo(0);
        List<Field> list = fieldMapper.selectByExample(example);
        if(list.isEmpty()) return null;
        return list.get(0);

    }


    /**
     * 分学校功能区、分页查询场地
     * @param pageNum
     * @param areaId
     * @return
     */
    public PageInfo<Field> selectPageByUIdSIdAId(int pageNum, Long areaId){
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        FieldExample example = new FieldExample();
        FieldExample.Criteria criteria = example.createCriteria();
        criteria.andAreaIdEqualTo((Long) areaId).andDeletedEqualTo(0);
        // 根据条件查询
        List<Field> fields = fieldMapper.selectByExample(example); //  无条件查找
        if(fields != null)
            return new PageInfo<>(fields);
        else
            return null;
    }

    /**
     * @description: 分学校、分页查询场地
     * @author: 潘绍豪
     * @date: {2019/5/10} {17:56}
     */
    public PageInfo<Field> selectPageByUId(int pageNum, Long universityId){
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        FieldExample example = new FieldExample();
        FieldExample.Criteria criteria = example.createCriteria();
        criteria.andUniversityIdEqualTo((Long) universityId).andDeletedEqualTo(0);
        List<Field> fields = fieldMapper.selectByExample(example); //  无条件查找
        if(fields != null)
            return new PageInfo<>(fields);
        else
            return null;
    }

    /**
     * @description: 分校区、分页查询场地
     * @author: 潘绍豪
     * @date: {2019/5/10} {18:16}
     */
    public PageInfo<Field> selectPageByUIdSId(int pageNum, Long schoolId){
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        FieldExample example = new FieldExample();
        FieldExample.Criteria criteria = example.createCriteria();
        criteria.andSchoolIdEqualTo((Long) schoolId).andDeletedEqualTo(0);
        List<Field> fields = fieldMapper.selectByExample(example); //  无条件查找
        if(fields != null)
            return new PageInfo<>(fields);
        else
            return null;
    }


    /**
     * 分学校功能区查询学校功能区
     * @param areaId
     * @return
     */
    public List<Field> seleByUIdSIdAId( Long areaId) {
       FieldExample example = new FieldExample();
       example.or().andAreaIdEqualTo((Long) areaId).andDeletedEqualTo(0);
       return  fieldMapper.selectByExample(example);
    }
    /**
     * @description: 根据功能区id和场地名称模糊查询
     * @author: 潘绍豪
     * @date: {2019/5/9} {20:19}
     */
    public PageInfo<Field> selectPageLikeName(int pageNum, String name,Long areaId) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        FieldExample example = new FieldExample();
        FieldExample.Criteria criteria = example.createCriteria();
        criteria.andAreaIdEqualTo((Long) areaId).andNameLike("%" + name + "%").andDeletedEqualTo(0);
        List<Field> fields = fieldMapper.selectByExample(example); //  无条件查找
        if(fields != null)
            return new PageInfo<>(fields);
        else
            return null;
    }
}
