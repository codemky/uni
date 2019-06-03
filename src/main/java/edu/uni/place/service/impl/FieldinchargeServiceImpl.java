package edu.uni.place.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.example.config.ExampleConfig;
import edu.uni.place.bean.Fieldincharge;
import edu.uni.place.bean.FieldinchargeExample;
import edu.uni.place.mapper.FieldinchargeMapper;
import edu.uni.place.service.FieldinchargeService;
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
public class FieldinchargeServiceImpl implements FieldinchargeService {
    @Autowired
    private FieldinchargeMapper fieldinchargeMapper;

    @Autowired
    private ExampleConfig globalConfig;

    /**
     * 新增场地管理员
     * @param fieldincharge
     * @return
     */
    public boolean insert(Fieldincharge fieldincharge){
        return fieldinchargeMapper.insert(fieldincharge) > 0 ? true : false;
    }

    /**
     * 删除场地管理员
     * @param id
     * @return
     */
    public boolean delete(Long id){
        Fieldincharge fieldincharge = new Fieldincharge();
        fieldincharge.setId((long) id);
        fieldincharge.setDeleted(1);
        return fieldinchargeMapper.updateByPrimaryKeySelective(fieldincharge) > 0;

    }

    /**
     * 修改场地管理员
     * @param fieldincharge
     * @return
     */
    public boolean update(Fieldincharge fieldincharge){
        //创建原数据备份
        Fieldincharge NewFieldincharge = fieldinchargeMapper.selectByPrimaryKey(fieldincharge.getId());
        NewFieldincharge.setDeleted(1);
        fieldinchargeMapper.insert(NewFieldincharge);
        //修改原数据
        FieldinchargeExample example = new FieldinchargeExample();
        FieldinchargeExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(fieldincharge.getId());
        return fieldinchargeMapper.updateByExampleSelective(fieldincharge,example) > 0 ? true : false;
    }

    /**
     * 查询场地管理员
     * @param id
     * @return
     */
    public Fieldincharge select(Long id){
        FieldinchargeExample example = new FieldinchargeExample();
        example.or().andIdEqualTo((long) id).andDeletedEqualTo(0);
        List<Fieldincharge> list = fieldinchargeMapper.selectByExample(example);
        if(list.isEmpty()) return null;
        return list.get(0);
    }

    /**
     * 分学校、分场地、分用户、分页查询场地管理员
     * @param pageNum
     * @param universityId
     * @param fieldId
     * @param userId
     * @return
     */
    public PageInfo<Fieldincharge> selectPageByUIdFIdUId(int pageNum, Long universityId, Long fieldId, Long userId){
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        FieldinchargeExample example = new FieldinchargeExample();
        FieldinchargeExample.Criteria criteria = example.createCriteria();
        criteria.andUniversityIdEqualTo((long) universityId).andFieldIdEqualTo((long) fieldId).andUserIdEqualTo((long) userId).andDeletedEqualTo(0);
        // 根据条件查询
        List<Fieldincharge> fieldincharges = fieldinchargeMapper.selectByExample(example); //  无条件查找
        if(fieldincharges != null)
            return new PageInfo<>(fieldincharges);
        else
            return null;
    }
}
