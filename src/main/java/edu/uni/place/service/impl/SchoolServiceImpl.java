package edu.uni.place.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.example.config.ExampleConfig;
import edu.uni.place.bean.*;
import edu.uni.place.mapper.*;
import edu.uni.place.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class SchoolServiceImpl implements SchoolService {
    @Autowired
    private SchoolMapper schoolMapper;
    @Autowired
    private SchoolareaMapper schoolareaMapper;
    @Autowired
    private FieldMapper fieldMapper;


    @Autowired
    private ExampleConfig globalConfig;

    /**
     * 新增校区
     * @param school
     * @return
     */
    public boolean insert(School school){
        return schoolMapper.insert(school) > 0 ? true : false;
    }
    /**
     * 删除校区
     * @param id
     * @return
     */
    public boolean delete(Long id){
        School school = new School();
        school.setId((long)id);
        school.setDeleted(1);

        Schoolarea schoolarea = new Schoolarea();
        schoolarea.setSchoolId((long)id);
        schoolarea.setDeleted(1);

        Field field = new Field();
        field.setDeleted(1);
        //删除校区
        if(schoolMapper.updateByPrimaryKeySelective(school) > 0){
            //根据校区删除所有功能区
            SchoolareaExample Schoolareaexample = new SchoolareaExample();
            SchoolareaExample.Criteria criteria = Schoolareaexample.createCriteria();
            criteria.andSchoolIdEqualTo((long)id);
            schoolareaMapper.updateByExampleSelective(schoolarea,Schoolareaexample);
            //根据校区删除所有场地
            FieldExample Fieldexample = new FieldExample();
            FieldExample.Criteria criteria1 = Fieldexample.createCriteria();
            criteria1.andSchoolIdEqualTo((long)id);
            fieldMapper.updateByExampleSelective(field,Fieldexample);

            return true;
        }
        return false;
    }

    /**
     * 修改校区
     * @param school
     * @return
     */
    public boolean update(School school){
        //创建原数据备份
        School NewSchool = schoolMapper.selectByPrimaryKey(school.getId());
        NewSchool.setDeleted(1);
        schoolMapper.insert(NewSchool);
        //修改原数据
        SchoolExample example = new SchoolExample();
        SchoolExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(school.getId());
        return schoolMapper.updateByExampleSelective(school,example) > 0 ? true : false;
    }

    /**
     * 查询校区详情
     * @param id
     * @return
     */
    public School select(Long id){
        SchoolExample example = new SchoolExample();
        example.or().andIdEqualTo((long) id).andDeletedEqualTo(0);
        List<School> list = schoolMapper.selectByExample(example);
        if(list.isEmpty())return null;
        return  list.get(0);
    }
    /**
     * @description:根据校区名称模糊查询
     * @author: 潘绍豪
     * @date: {2019/5/9} {19:20}
     */
    @Override
    public PageInfo<School> selectPageLikeName(int pageNum, String name){
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        SchoolExample example = new SchoolExample();
        SchoolExample.Criteria criteria = example.createCriteria();
        criteria.andNameLike("%" + name + "%").andDeletedEqualTo(0);
        // 根据条件查询
        List<School> schools = schoolMapper.selectByExample(example); //  无条件查找
        if(schools != null)
            return new PageInfo<>(schools);
        else
            return null;
    }



    /**
     * 分学校，分页查询所有校区，并返回其他信息
     * @param pageNum
     * @param universityId
     * @return
     */
    public PageInfo<School> selectPageByUId(int pageNum, Long universityId){
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        SchoolExample example = new SchoolExample();
        SchoolExample.Criteria criteria = example.createCriteria();
        criteria.andUniversityIdEqualTo((long) universityId).andDeletedEqualTo(0);
        // 根据条件查询
        List<School> schools = schoolMapper.selectByExample(example); //  无条件查找
        if(schools != null)
            return new PageInfo<>(schools);
        else
            return null;
    }

    /**
     * 分学校查询所有校区，返回校区
     * @param universityId
     * @return
     */
    public List<School> selectByUId(Long universityId) {
        SchoolExample example = new SchoolExample();
        example.or().andUniversityIdEqualTo((long) universityId).andDeletedEqualTo(0);
        return (List<School>) schoolMapper.selectByExample(example);
    }


}
