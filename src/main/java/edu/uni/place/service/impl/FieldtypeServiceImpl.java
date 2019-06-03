package edu.uni.place.service.impl;


import edu.uni.example.config.ExampleConfig;
import edu.uni.place.bean.Fieldtype;
import edu.uni.place.bean.FieldtypeExample;
import edu.uni.place.mapper.FieldtypeMapper;
import edu.uni.place.service.FieldtypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 潘绍豪
 * @Create 2019/5/5
 * @Description
 * @Since 1.0.0
 */
@Service
public class FieldtypeServiceImpl implements FieldtypeService {
    @Autowired
    private FieldtypeMapper fieldtypeMapper;

    @Autowired
    private ExampleConfig globalConfig;

    /**
     * 新增场地类型
     * @param fieldtype
     * @return
     */
    public boolean insert(Fieldtype fieldtype){
        return fieldtypeMapper.insert(fieldtype) > 0 ? true : false;
    }

    /**
     * 删除场地类型
     * @param id
     * @return
     */
    public boolean delete(Long id){
       Fieldtype fieldtype = new Fieldtype();
       fieldtype.setId((long) id);
       fieldtype.setDeleted(1);
       return fieldtypeMapper.updateByPrimaryKeySelective(fieldtype) > 0;
    }


    /**
     * 修改场地类型
     * @param fieldtype
     * @return
     */
    public boolean update(Fieldtype fieldtype){
        //创建原数据备份
        Fieldtype NewFieldtype = fieldtypeMapper.selectByPrimaryKey(fieldtype.getId());
        NewFieldtype.setDeleted(1);
        fieldtypeMapper.insert(NewFieldtype);
        //修改原数据
        FieldtypeExample example = new FieldtypeExample();
        FieldtypeExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(fieldtype.getId());
        return fieldtypeMapper.updateByExampleSelective(fieldtype,example) > 0 ? true : false;
    }
    /**
     * 查找所有场地类型
     * @return
     */
    @Override
    public List<Fieldtype> selectAll() {
        FieldtypeExample example = new FieldtypeExample();
        example.or().andDeletedEqualTo(0);
        List<Fieldtype> list = fieldtypeMapper.selectByExample(example);
        if(!list.isEmpty())
            return list;
        else
            return null;

    }
}
