package edu.uni.example.service;

import com.github.pagehelper.PageInfo;
import edu.uni.example.bean.Product;

public interface ProductService {
    /**
     * 新增产品
     * @param product
     * @return
     */
    boolean insert(Product product);

    /**
     * 删除产品
     * @param id
     * @return
     */
    boolean delete(int id);

    /**
     * 修改商品
     * @param product
     * @return
     */
    boolean update(Product product);

    /**
     * 查询商品详情
     * @param id
     * @return
     */
    Product select(int id);

    /**
     * 分页查询所有商品
     * @param pageNum
     * @return
     */
    PageInfo<Product> selectPage(int pageNum);

    /**
     * 分类分页查询商品
     * @param pageNum
     * @param categoryId
     * @return
     */
    PageInfo<Product> selectPageByCategory(int pageNum, int categoryId);
}
