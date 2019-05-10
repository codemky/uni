package edu.uni.administrativestructure.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.administrativestructure.bean.Department;
import edu.uni.administrativestructure.bean.DepartmentExample;
import edu.uni.administrativestructure.bean.UniversityDepartment;
import edu.uni.administrativestructure.bean.UniversityDepartmentExample;
import edu.uni.administrativestructure.config.administrativeStructureConfig;
import edu.uni.administrativestructure.mapper.DepartmentMapper;
import edu.uni.administrativestructure.mapper.UniversityDepartmentMapper;
import edu.uni.administrativestructure.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author : 黄永佳
 * create : 2019/4/19 0023 15:34
 * modified :
 * 功能 :一级部门表的具体实现类
 **/
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private UniversityDepartmentMapper universityDepartmentMapper;

    @Autowired
    private administrativeStructureConfig globalConfig;

    //插入department表记录
    @Override
    public boolean insert(Department department) {
        if(departmentMapper.insert(department)<0){
            return false;
        }
        UniversityDepartment universityDepartment=new UniversityDepartment();
        universityDepartment.setUniversityId(department.getUniversityId());
        universityDepartment.setByWho(department.getByWho());
        universityDepartment.setDatetime(department.getDatetime());
        universityDepartment.setDepartmentId(department.getId());
        universityDepartment.setDeleted(department.getDeleted());
        if(universityDepartmentMapper.insert(universityDepartment)<0){
            return false;
        }else return true;
    }

    //删除department表记录
    @Override
    public boolean delete(long id) {

        Department department=departmentMapper.selectByPrimaryKey(id);
        if(departmentMapper.deleteByPrimaryKey(id)>0) {
            department.setDeleted(true);
            departmentMapper.insert(department);
        }else {
            return false;
        }
        return true;
    }

    //修改department表记录
    @Override
    public boolean update(Department department) {
        Department department1=new Department();
        department1=departmentMapper.selectByPrimaryKey(department.getId());
        department1.setDeleted(true);
        if(departmentMapper.updateByPrimaryKeySelective(department)<=0){
            return false;
        }
        if(departmentMapper.insert(department1)<=0){
            return false;
        }

//        判断关系表是否需要修改
        if(department.getUniversityId()!=department1.getUniversityId()){
            UniversityDepartmentExample example = new UniversityDepartmentExample();
            UniversityDepartmentExample.Criteria criteria = example.createCriteria();
            criteria.andDepartmentIdEqualTo(department.getId()).andDeletedEqualTo(false);
            List<UniversityDepartment> universityDepartments=universityDepartmentMapper.selectByExample(example);
            UniversityDepartment universityDepartment= universityDepartments.get(0);
            universityDepartment.setDeleted(true);
            universityDepartmentMapper.insert(universityDepartment);
            universityDepartment.setUniversityId(department.getUniversityId());
            universityDepartment.setByWho(department.getByWho());
            universityDepartment.setDatetime(department.getDatetime());
            universityDepartment.setDeleted(false);
            universityDepartmentMapper.updateByPrimaryKeySelective(universityDepartment);
        }
        return true;
    }

    //查询department表记录
    @Override
    public Department select(long id) {
        return departmentMapper.selectByPrimaryKey(id);
    }

    //分页查询所有部门
    @Override
    public PageInfo<Department> selectPage(int pageNum) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        DepartmentExample example = new DepartmentExample();
        DepartmentExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(false);
        List<Department> departments = departmentMapper.selectByExample(example);   //  查找部门
        if(departments != null)
            return new PageInfo<>(departments);
        else
            return null;
    }

    //根据学校id分页查询部门
    @Override
    public PageInfo<Department> selectPageByUniversity(int pageNum, long universityId) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        DepartmentExample example = new DepartmentExample();
        DepartmentExample.Criteria criteria = example.createCriteria();
        criteria.andUniversityIdEqualTo(universityId).andDeletedEqualTo(false);
        // 根据条件查询
        List<Department> departments = departmentMapper.selectByExample(example);
        if(departments != null)
            return new PageInfo<>(departments);
        else
            return null;
    }

    //遍历所有部门
    @Override
    public List<Department> selectAll() {
        DepartmentExample example = new DepartmentExample();
        DepartmentExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(false);

        return departmentMapper.selectByExample(example);
    }
}
