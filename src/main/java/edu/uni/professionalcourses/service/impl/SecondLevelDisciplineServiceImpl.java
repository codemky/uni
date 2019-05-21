package edu.uni.professionalcourses.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.example.config.ExampleConfig;
import edu.uni.professionalcourses.bean.SecondLevelDiscipline;
import edu.uni.professionalcourses.bean.SecondLevelDisciplineExample;
import edu.uni.professionalcourses.mapper.SecondLevelDisciplineMapper;
import edu.uni.professionalcourses.service.SecondLevelDisciplineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author:  曹中耀
 * create:  2019.5.11
 * modified:  2019.5.11
 * description：对 SecondLevelDisciplineService的方法的实现
 */
@Service
public class SecondLevelDisciplineServiceImpl implements SecondLevelDisciplineService {
    @Autowired
    private SecondLevelDisciplineMapper secondLevelDisciplineMapper;
    //    @Autowired
//    private DisciplineCategoryMapper disciplineCategoryMapper;
    @Autowired
    private ExampleConfig globalConfig;
    /**
     * 新增SecondLevelDiscipline信息
     * @param secondLevelDiscipline
     * @return
     */
    @Override
    public boolean insert(SecondLevelDiscipline secondLevelDiscipline) {
        if(secondLevelDisciplineMapper.insert(secondLevelDiscipline)<=0)
        {
            return false;
        }

        else return true;
        // return bookMapper.insert(book) > 0 ? true : false;
    }

    /**
     * 删除SecondLevelDiscipline信息
     * @param id
     * @return
     */
    @Override
    public boolean delete(long id) {
        SecondLevelDiscipline secondLevelDiscipline1=new SecondLevelDiscipline();
        secondLevelDiscipline1=secondLevelDisciplineMapper.selectByPrimaryKey(id);
        secondLevelDiscipline1.setDeleted(true);
        if(secondLevelDisciplineMapper.updateByPrimaryKeySelective(secondLevelDiscipline1)<=0)
        {
            return false;
        }
        return true;
    }

    /**
     * 更新SecondLevelDiscipline信息
     * @param secondLevelDiscipline
     * @return
     */
    public boolean update(SecondLevelDiscipline secondLevelDiscipline){//新
        //保存旧信息
        SecondLevelDiscipline secondLevelDiscipline1=new SecondLevelDiscipline();
        secondLevelDiscipline1=secondLevelDisciplineMapper.selectByPrimaryKey(secondLevelDiscipline.getId());
        secondLevelDiscipline1.setDeleted(true);

        //更新信息
        if(secondLevelDisciplineMapper.updateByPrimaryKeySelective(secondLevelDiscipline)<=0)
        {
            return false;
        }
        if(secondLevelDisciplineMapper.insert(secondLevelDiscipline1)<0)
        {
            return false;
        }
        return true;
    }

    /**
     * 查找SecondLevelDiscipline信息
     * @param id
     * @return
     */
    @Override
    public SecondLevelDiscipline select(long id) {
        SecondLevelDiscipline secondLevelDiscipline1=secondLevelDisciplineMapper.selectByPrimaryKey(id);
        //        判断如果输入id查找的信息有效时显示信息，无效显示null
        if(secondLevelDiscipline1.getDeleted())
        {
            return null;
        }
        else return secondLevelDiscipline1;
        // return bookMapper.selectByPrimaryKey(id);
    }

    /**
     * 查找所有SecondLevelDiscipline信息
     * @return
     */
    @Override
    public List<SecondLevelDiscipline> selectAll() {
        SecondLevelDisciplineExample example=new SecondLevelDisciplineExample();
        SecondLevelDisciplineExample.Criteria criteria=example.createCriteria();
        criteria.andDeletedEqualTo(false);
        return secondLevelDisciplineMapper.selectByExample(example);
    }
    /**
     * 分页查询返回所有SecondLevelDiscipline信息
     * @param pageNum
     * @return
     */
    public PageInfo<SecondLevelDiscipline> selectPage(int pageNum){
        PageHelper.startPage(pageNum, globalConfig.getPageSize());
        SecondLevelDisciplineExample example = new SecondLevelDisciplineExample();
        SecondLevelDisciplineExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(false);// 开启分页查询，第一次切仅第一次查询时生效
        List<SecondLevelDiscipline> books = secondLevelDisciplineMapper.selectByExample(example);   //  查找有效book信息
        if(books != null)
            return new PageInfo<>(books);
        else
            return null;
    }
    /**
     *  根据二级学科表一级学科id分页查询并返回SecondLevelDiscipline信息
     * @param pageNum
     * @param first_level_discipline_id
     * @return
     */
    public PageInfo<SecondLevelDiscipline> selectPageByFirstLevelDisciplineID(int pageNum, long first_level_discipline_id){
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        SecondLevelDisciplineExample example = new SecondLevelDisciplineExample();
        SecondLevelDisciplineExample.Criteria criteria = example.createCriteria();
        criteria.andFirstLevelDisciplineIdEqualTo(first_level_discipline_id).andDeletedEqualTo(false);
        // 根据条件查询
        List<SecondLevelDiscipline> products = secondLevelDisciplineMapper.selectByExample(example);   //  无条件查找商品
        if(products != null)
            return new PageInfo<>(products);
        else
            return null;
    }
}
