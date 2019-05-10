package edu.uni.administrativestructure.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.administrativestructure.bean.University;
import edu.uni.administrativestructure.bean.UniversityExample;
import edu.uni.administrativestructure.config.administrativeStructureConfig;
import edu.uni.administrativestructure.mapper.UniversityMapper;
import edu.uni.administrativestructure.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author：黄育林
 * create: 2019.4.20
 * modified: 2019.5.9
 * 功能：学校接口实现类
 */
@Service
public class UniversityServiceImpl implements UniversityService {
    @Autowired
    private UniversityMapper universityMapper;

    @Autowired
    private administrativeStructureConfig globalConfig;

    /**
     * 保存学校
     * @param university
     * @return
     */
    @Override
    public boolean insert(University university) {
        return universityMapper.insert(university) > 0 ? true : false;
    }

    /**
     * 删除学校
     * @param id
     * @return
     */
    @Override
    public boolean delete(long id) {
        //获取删除前的数据
        University university1 = universityMapper.selectByPrimaryKey(id);
        university1.setDeleted(true);
        //判断能否删除
        if(universityMapper.deleteByPrimaryKey(id)<=0){
            return false;
        }else{
            //删除后插入作废记录
            if(universityMapper.insert(university1)<=0){
                return false;
            }
            else
                return true;
        }
    }

    /**
     * 更新学校
     * @param university
     * @return
     */
    public boolean update(University university){
        //获取修改前的数据
        long id1 = university.getId();
        University university1 = universityMapper.selectByPrimaryKey(id1);
        university1.setDeleted(true);
        //修改数据
        if(universityMapper.updateByPrimaryKeySelective(university)<=0){
            return false;
        }
        //修改后插入修改前的数据
        if(universityMapper.insert(university1)<=0){
            return false;
        }
        else
            return true;
    }

    /**
     * 查找学校
     * @param id
     * @return
     */
    @Override
    public University select(long id) {
        return universityMapper.selectByPrimaryKey(id);
    }

    /**
     * 查找所有学校
     * @return
     */
    @Override
    public List<University> selectAll() {
        //筛选出有效记录
        UniversityExample example = new UniversityExample();
        UniversityExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(false);
        List<University> universities = universityMapper.selectByExample(example);
        return universities;
    }

    /**
     * 分页查找所有学校
     * @return
     */
    @Override
    public PageInfo<University> selectPage(int pageNum) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        //筛选出有效记录
        UniversityExample example = new UniversityExample();
        UniversityExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(false);
        List<University> universities = universityMapper.selectByExample(example);
        if(universities != null)
            return new PageInfo<>(universities);
        else
            return null;
    }

}
