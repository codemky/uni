package edu.uni.example.service;


import edu.uni.example.bean.Category;

import java.util.List;

public interface CategoryService {
    /**
     * 保存新类别
     * @param category
     * @return
     */
    boolean insert(Category category);

    /**
     * 删除类别
     * @param id
     * @return
     */
    boolean delete(int id);

    /**
     * 更新类别
     * @param category
     * @return
     */
    boolean update(Category category);

    /**
     * 查找类别
     * @param id
     * @return
     */
    Category select(int id);

    /**
     * 查找所有类别
     * @return
     */
    List<Category> selectAll();
}
