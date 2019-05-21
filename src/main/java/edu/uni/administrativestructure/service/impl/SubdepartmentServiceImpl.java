package edu.uni.administrativestructure.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.administrativestructure.bean.DepartmentSubdepartment;
import edu.uni.administrativestructure.bean.DepartmentSubdepartmentExample;
import edu.uni.administrativestructure.bean.Subdepartment;
import edu.uni.administrativestructure.bean.SubdepartmentExample;
import edu.uni.administrativestructure.config.administrativeStructureConfig;
import edu.uni.administrativestructure.mapper.DepartmentSubdepartmentMapper;
import edu.uni.administrativestructure.mapper.SubdepartmentMapper;
import edu.uni.administrativestructure.service.SubdepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author：黄育林
 * create: 2019.4.20
 * modified: 2019.5.9
 * 功能：三级部门实现类
 */
@Service
public class SubdepartmentServiceImpl implements SubdepartmentService {
    @Autowired
    private SubdepartmentMapper subdepartmentMapper;

    @Autowired
    private DepartmentSubdepartmentMapper departmentSubdepartmentMapper;

    @Autowired
    private administrativeStructureConfig globalConfig;

    @Override
    public boolean insert(Subdepartment subdepartment) {
        if(subdepartmentMapper.insert(subdepartment)<=0){
            return false;
        }
        //将记录插入关系表
        DepartmentSubdepartment departmentSubdepartment = new DepartmentSubdepartment();
        departmentSubdepartment.setUniversityId(subdepartment.getUniversityId());
        departmentSubdepartment.setByWho(subdepartment.getByWho());
        departmentSubdepartment.setDatetime(subdepartment.getDatetime());
        departmentSubdepartment.setDeleted(subdepartment.getDeleted());
        departmentSubdepartment.setDepartmentId(subdepartment.getDepartmentId());
        departmentSubdepartment.setSubdepartmentId(subdepartment.getId());
        if(departmentSubdepartmentMapper.insert(departmentSubdepartment)<=0){
            return false;
        }
        else
            return true;
    }

    @Override
    public boolean delete(long id) {
        Subdepartment subdepartment = subdepartmentMapper.selectByPrimaryKey(id);
        //创造查询条件
        DepartmentSubdepartmentExample example = new DepartmentSubdepartmentExample();
        DepartmentSubdepartmentExample.Criteria criteria = example.createCriteria();
        criteria.andSubdepartmentIdEqualTo(id);
        //获取关系表内记录
        List<DepartmentSubdepartment> departmentSubdepartments = departmentSubdepartmentMapper.selectByExample(example);
        DepartmentSubdepartment departmentSubdepartment = departmentSubdepartments.get(0);
        long id1 = departmentSubdepartment.getId();
        //删除关系表内记录
        departmentSubdepartmentMapper.deleteByPrimaryKey(id1);
        if(subdepartmentMapper.deleteByPrimaryKey(id)<=0){
            //无法删除，重新插入关系表记录
            departmentSubdepartmentMapper.insert(departmentSubdepartment);
            return false;
        }else{
            subdepartment.setDeleted(true);
            //删除后插入旧记录，不再插入关系表
            if(subdepartmentMapper.insert(subdepartment)<=0){
                return false;
            }else{
                return true;
            }
        }
    }

    @Override
    public boolean update(Subdepartment subdepartment) {
        //获取旧数据
        long id = subdepartment.getId();
        Subdepartment subdepartment1 = subdepartmentMapper.selectByPrimaryKey(id);
        subdepartment1.setDeleted(true);
        //更新数据
        if(subdepartmentMapper.updateByPrimaryKeySelective(subdepartment)<=0){
            return false;
        }
        //插入旧数据
        if(subdepartmentMapper.insert(subdepartment1)<=0){
            return false;
        }
        //判断对应关系表内容是否发生变化
        if(subdepartment.getDepartmentId()!=subdepartment1.getDepartmentId()){
            //获取关系表原本数据
            long id1 = subdepartment.getId();
            DepartmentSubdepartmentExample example = new DepartmentSubdepartmentExample();
            DepartmentSubdepartmentExample.Criteria criteria = example.createCriteria();
            criteria.andSubdepartmentIdEqualTo(id1);
            List<DepartmentSubdepartment> departmentSubdepartments = departmentSubdepartmentMapper.selectByExample(example);
            DepartmentSubdepartment departmentSubdepartment = departmentSubdepartments.get(0);
            departmentSubdepartment.setDepartmentId(subdepartment.getDepartmentId());
            //更新关系表内数据
            if(departmentSubdepartmentMapper.updateByPrimaryKeySelective(departmentSubdepartment)<=0){
                return false;
            }
            else
                return true;
        }
        else
            return true;
    }

    @Override
    public Subdepartment select(long id) {
        return subdepartmentMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<Subdepartment> selectPage(int pageNum) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        //筛选条件
        SubdepartmentExample example = new SubdepartmentExample();
        SubdepartmentExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(false);
        //筛选有效记录
        List<Subdepartment> subdepartments = subdepartmentMapper.selectByExample(example);
        if(subdepartments != null)
            return new PageInfo<>(subdepartments);
        else
            return null;
    }

    @Override
    public PageInfo<Subdepartment> selectPageByDepartment(int pageNum, long departmentId) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        SubdepartmentExample example = new SubdepartmentExample();
        SubdepartmentExample.Criteria criteria = example.createCriteria();
        criteria.andDepartmentIdEqualTo(departmentId).andDeletedEqualTo(false);
        // 根据条件查询
        List<Subdepartment> subdepartments = subdepartmentMapper.selectByExample(example);
        if(subdepartments != null)
            return new PageInfo<>(subdepartments);
        else
            return null;
    }

    @Override
    public List<Subdepartment> selectAll() {
        //筛选有效记录
        SubdepartmentExample example = new SubdepartmentExample();
        SubdepartmentExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(false);
        // 根据条件查询
        List<Subdepartment> subdepartments = subdepartmentMapper.selectByExample(example);
        return subdepartments;
    }

    @Override
    public PageInfo<Subdepartment> selectPageByUniversity(int pageNum, long universityId) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        SubdepartmentExample example = new SubdepartmentExample();
        SubdepartmentExample.Criteria criteria = example.createCriteria();
        criteria.andUniversityIdEqualTo(universityId).andDeletedEqualTo(false);
        // 根据条件查询
        List<Subdepartment> subdepartments = subdepartmentMapper.selectByExample(example);
        if(subdepartments != null)
            return new PageInfo<>(subdepartments);
        else
            return null;
    }
}
