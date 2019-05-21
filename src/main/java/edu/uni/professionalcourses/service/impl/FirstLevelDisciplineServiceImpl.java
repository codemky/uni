package edu.uni.professionalcourses.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.example.config.ExampleConfig;
import edu.uni.professionalcourses.bean.FirstLevelDiscipline;
import edu.uni.professionalcourses.bean.FirstLevelDisciplineExample;
import edu.uni.professionalcourses.mapper.FirstLevelDisciplineMapper;
import edu.uni.professionalcourses.service.FirstLevelDisciplineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author:  曹中耀
 * create:  2019.5.11
 * modified:  2019.5.11
 * description：对 FirstLevelDisciplineService的方法的实现
 */
@Service
public class FirstLevelDisciplineServiceImpl implements FirstLevelDisciplineService {
    @Autowired
    private FirstLevelDisciplineMapper firstLevelDisciplineMapper;
//    @Autowired
//    private DisciplineCategoryMapper disciplineCategoryMapper;
    @Autowired
    private ExampleConfig globalConfig;
    /**
     * 新增FirstLevelDiscipline信息
     * @param firstLevelDiscipline
     * @return
     */
    @Override
    public boolean insert(FirstLevelDiscipline firstLevelDiscipline) {
        if(firstLevelDisciplineMapper.insert(firstLevelDiscipline)<=0)
        {
            return false;
        }

        else return true;
        // return bookMapper.insert(book) > 0 ? true : false;
    }

    /**
     * 删除FirstLevelDiscipline信息
     * @param id
     * @return
     */
    @Override
    public boolean delete(long id) {
        FirstLevelDiscipline firstLevelDiscipline1=new FirstLevelDiscipline();
        firstLevelDiscipline1=firstLevelDisciplineMapper.selectByPrimaryKey(id);
        firstLevelDiscipline1.setDeleted(true);
        if(firstLevelDisciplineMapper.updateByPrimaryKeySelective(firstLevelDiscipline1)<=0)
        {
            return false;
        }
          return true;
    }

    /**
     * 更新FirstLevelDiscipline信息
     * @param firstLevelDiscipline
     * @return
     */
    public boolean update(FirstLevelDiscipline firstLevelDiscipline){//新
        //保存旧信息
        FirstLevelDiscipline firstLevelDiscipline1=new FirstLevelDiscipline();
        firstLevelDiscipline1=firstLevelDisciplineMapper.selectByPrimaryKey(firstLevelDiscipline.getId());
        firstLevelDiscipline1.setDeleted(true);

        //更新信息
        if(firstLevelDisciplineMapper.updateByPrimaryKeySelective(firstLevelDiscipline)<=0)
        {
            return false;
        }
        if(firstLevelDisciplineMapper.insert(firstLevelDiscipline1)<0)
        {
            return false;
        }
        return true;
    }

    /**
     * 查找FirstLevelDiscipline信息
     * @param id
     * @return
     */
    @Override
    public FirstLevelDiscipline select(long id) {
        FirstLevelDiscipline firstLevelDiscipline1=firstLevelDisciplineMapper.selectByPrimaryKey(id);
        //        判断如果输入id查找的信息有效时显示信息，无效显示null
        if(firstLevelDiscipline1.getDeleted())
        {
            return null;
        }
        else return firstLevelDiscipline1;
        // return bookMapper.selectByPrimaryKey(id);
    }

    /**
     * 查找所有FirstLevelDiscipline信息
     * @return
     */
    @Override
    public List<FirstLevelDiscipline> selectAll() {
        FirstLevelDisciplineExample example=new FirstLevelDisciplineExample();
        FirstLevelDisciplineExample.Criteria criteria=example.createCriteria();
        criteria.andDeletedEqualTo(false);
        return firstLevelDisciplineMapper.selectByExample(example);
    }
    /**
     * 分页查询返回所有FirstLevelDiscipline信息
     * @param pageNum
     * @return
     */
    public PageInfo<FirstLevelDiscipline> selectPage(int pageNum){
        PageHelper.startPage(pageNum, globalConfig.getPageSize());
        FirstLevelDisciplineExample example = new FirstLevelDisciplineExample();
        FirstLevelDisciplineExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(false);// 开启分页查询，第一次切仅第一次查询时生效
        List<FirstLevelDiscipline> books = firstLevelDisciplineMapper.selectByExample(example);   //  查找有效book信息
        if(books != null)
            return new PageInfo<>(books);
        else
            return null;
    }
    /**
     *  根据一级学科表学科门类id分页查询并返回FirstLevelDiscipline信息
     * @param pageNum
     * @param discipline_category_id
     * @return
     */
    public PageInfo<FirstLevelDiscipline> selectPageByDisciplineCategoryID(int pageNum, long discipline_category_id){
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        FirstLevelDisciplineExample example = new FirstLevelDisciplineExample();
        FirstLevelDisciplineExample.Criteria criteria = example.createCriteria();
        criteria.andDisciplineCategoryIdEqualTo(discipline_category_id).andDeletedEqualTo(false);
        // 根据条件查询
        List<FirstLevelDiscipline> products = firstLevelDisciplineMapper.selectByExample(example);   //  无条件查找商品
        if(products != null)
            return new PageInfo<>(products);
        else
            return null;
    }
}
