package edu.uni.example.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.example.config.ExampleConfig;
import edu.uni.example.service.ProductService;
import edu.uni.example.bean.Product;
import edu.uni.example.bean.ProductExample;
import edu.uni.example.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;    // 爆红不管

    @Autowired
    private ExampleConfig globalConfig;

    /**
     * 新增产品
     * @param product
     * @return
     */
    public boolean insert(Product product){
        return productMapper.insert(product) > 0 ? true : false;
    }

    /**
     * 删除产品
     * @param id
     * @return
     */
    public boolean delete(int id){
        return productMapper.deleteByPrimaryKey(id) > 0 ? true : false;
    }

    /**
     * 修改商品
     * @param product
     * @return
     */
    public boolean update(Product product){
        return productMapper.updateByPrimaryKeySelective(product) > 0 ? true : false;
    }

    /**
     * 查询商品详情
     * @param id
     * @return
     */
    public Product select(int id){
        return productMapper.selectByPrimaryKey(id);
    }

    /**
     * 分页查询所有商品,并返回其他信息
     * @param pageNum
     * @return
     */
    public PageInfo<Product> selectPage(int pageNum){
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        List<Product> products = productMapper.selectByExample(null);   //  无条件查找商品
        if(products != null)
            return new PageInfo<>(products);
        else
            return null;
    }

    /**
     * 分类分页查询所有商品，并返回其他信息
     * @param pageNum
     * @param categoryId
     * @return
     */
    public PageInfo<Product> selectPageByCategory(int pageNum, int categoryId){
        PageHelper.startPage(pageNum, globalConfig.getPageSize());    // 开启分页查询，第一次切仅第一次查询时生效
        // 创建查询条件
        ProductExample example = new ProductExample();
        ProductExample.Criteria criteria = example.createCriteria();
        criteria.andCidEqualTo(categoryId);
        // 根据条件查询
        List<Product> products = productMapper.selectByExample(example);   //  无条件查找商品
        if(products != null)
            return new PageInfo<>(products);
        else
            return null;
    }
}
