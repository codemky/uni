package edu.uni.place.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.example.config.ExampleConfig;
import edu.uni.place.bean.Fieldapply;
import edu.uni.place.bean.FieldapplyExample;
import edu.uni.place.bean.Fieldincharge;
import edu.uni.place.mapper.FieldapplyMapper;
import edu.uni.place.service.FieldapplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 潘绍豪
 * @Create 2019/5/7
 * @Description
 * @Since 1.0.0
 */
@Service
public class FieldapplyServiceImpl implements FieldapplyService {
    @Autowired
    private FieldapplyMapper fieldapplyMapper;

    @Autowired
    private ExampleConfig globalConfig;


    /**
     * 新增场地使用申请
     * @param fieldapply
     * @return
     */
    public boolean insert(Fieldapply fieldapply){
        return fieldapplyMapper.insert(fieldapply) > 0 ? true : false;
    }


    /**
     * 删除场地使用申请
     * @param id
     * @return
     */
    public boolean delete(Long id){
        Fieldapply fieldapply = new Fieldapply();
        fieldapply.setId((long) id);
        fieldapply.setDeleted(1);
        return fieldapplyMapper.updateByPrimaryKeySelective(fieldapply) > 0;
    }

    /**
     * 修改场地使用申请
     * @param fieldapply
     * @return
     */
    public boolean update(Fieldapply fieldapply){
        return fieldapplyMapper.updateByPrimaryKeySelective(fieldapply) > 0 ? true : false;
    }

    /**
     * 查询场地使用申请
     * @param id
     * @return
     */
    public Fieldapply select(Long id){
        FieldapplyExample example = new FieldapplyExample();
        example.or().andIdEqualTo((long) id).andDeletedEqualTo(0);
        List<Fieldapply> list = fieldapplyMapper.selectByExample(example);
        if(list.isEmpty()) return null;
        return list.get(0);
    }

    /**
     * 分学校、分场地、分用户、分校历、分页查询场地使用申请
     * @param pageNum
     * @param universityId
     * @param fieldId
     * @param userId
     * @return
     */
    public PageInfo<Fieldapply> selectPageByUIdFIdUIdCId(int pageNum, Long universityId, Long fieldId, Long userId){
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        FieldapplyExample example = new FieldapplyExample();
        FieldapplyExample.Criteria criteria = example.createCriteria();
        criteria.andUniversityIdEqualTo((long) universityId).andFieldIdEqualTo((long) fieldId).andUserIdEqualTo((long) userId).andDeletedEqualTo(0);
        // 根据条件查询
        List<Fieldapply> fieldapplies = fieldapplyMapper.selectByExample(example); //  无条件查找
        if(fieldapplies != null)
            return new PageInfo<>(fieldapplies);
        else
            return null;
    }
}
