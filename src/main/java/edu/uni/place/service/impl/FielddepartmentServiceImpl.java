package edu.uni.place.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.example.config.ExampleConfig;
import edu.uni.place.bean.FieldExample;
import edu.uni.place.bean.Fielddepartment;
import edu.uni.place.bean.FielddepartmentExample;
import edu.uni.place.mapper.FielddepartmentMapper;
import edu.uni.place.service.FielddepartmentService;
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
public class FielddepartmentServiceImpl implements FielddepartmentService {
    @Autowired
    private FielddepartmentMapper fielddepartmentMapper;

    @Autowired
    private ExampleConfig globalConfig;

    /**
     * 新增场地主管部门
     * @param fielddepartment
     * @return
     */
    public boolean insert(Fielddepartment fielddepartment){
        return fielddepartmentMapper.insert(fielddepartment) > 0 ? true : false;
    }

    /**
     * 删除场地主管部门
     * @param id
     * @return
     */
    public boolean delete(Long id){
        Fielddepartment fielddepartment = new Fielddepartment();
        fielddepartment.setId((long) id);
        fielddepartment.setDeleted(1);
        return fielddepartmentMapper.updateByPrimaryKeySelective(fielddepartment) > 0;

    }


    /**
     * 修改场地主管部门
     * @param fielddepartment
     * @return
     */
    public boolean update(Fielddepartment fielddepartment){
        //创建原数据备份
        Fielddepartment NewFielddepartment = fielddepartmentMapper.selectByPrimaryKey(fielddepartment.getId());
        NewFielddepartment.setDeleted(1);
        fielddepartmentMapper.insert(NewFielddepartment);
        //修改原数据
        FielddepartmentExample example = new FielddepartmentExample();
        FielddepartmentExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(fielddepartment.getId());
        return fielddepartmentMapper.updateByExampleSelective(fielddepartment, example) > 0 ? true : false;
    }

    /**
     * 查询场地主管部门
     * @param id
     * @return
     */
    public Fielddepartment select(Long id){
        FielddepartmentExample example = new FielddepartmentExample();
        example.or().andIdEqualTo((long) id).andDeletedEqualTo(0);
        List<Fielddepartment> list = fielddepartmentMapper.selectByExample(example);
        if(list.isEmpty()) return null;
        return list.get(0);

    }


    /**
     * 分学校、分场地、分部门、分页查询场地主管部门
     * @param pageNum
     * @param universityId
     * @param fieldId
     * @param departmentId
     * @return
     */
    public PageInfo<Fielddepartment> selectPageByUIdFIdDId(int pageNum, Long universityId, Long fieldId, Long departmentId){
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        FielddepartmentExample example = new FielddepartmentExample();
        FielddepartmentExample.Criteria criteria = example.createCriteria();
        criteria.andUniversityIdEqualTo((long) universityId).andFieldIdEqualTo((long) fieldId).andDepartmentIdEqualTo((long) departmentId).andDeletedEqualTo(0);
        // 根据条件查询
        List<Fielddepartment> fielddepartments = fielddepartmentMapper.selectByExample(example); //  无条件查找
        if(fielddepartments != null)
            return new PageInfo<>(fielddepartments);
        else
            return null;
    }
}
