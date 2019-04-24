package edu.uni.example.service.impl;

import edu.uni.example.service.CategoryService;
import edu.uni.example.bean.Category;
import edu.uni.example.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;    // 爆红不管

    /**
     * 保存新类别
     * @param category
     * @return
     */
    @Override
    public boolean insert(Category category) {
        return categoryMapper.insert(category) > 0 ? true : false;
    }

    /**
     * 删除类别
     * @param id
     * @return
     */
    @Override
    public boolean delete(int id) {
        return categoryMapper.deleteByPrimaryKey(id) > 0 ? true : false;
    }

    /**
     * 更新类别
     * @param category
     * @return
     */
    public boolean update(Category category){
        return categoryMapper.updateByPrimaryKeySelective(category) > 0 ? true : false;
    }

    /**
     * 查找类别
     * @param id
     * @return
     */
    @Override
    public Category select(int id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

    /**
     * 查找所有类别
     * @return
     */
    @Override
    public List<Category> selectAll() {
        return categoryMapper.selectByExample(null);
    }
}
