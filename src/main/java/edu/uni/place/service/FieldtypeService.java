package edu.uni.place.service;

import edu.uni.place.bean.Fieldtype;

import java.util.List;

public interface FieldtypeService {
    /**
     * 新增场地类型
     * @param fieldtype
     * @return
     */
    boolean insert(Fieldtype fieldtype);

    /**
     * 删除场地类型
     * @param id
     * @return
     */
    boolean delete(Long id);

    /**
     * 修改场地类型
     * @param fieldtype
     * @return
     */
    boolean update(Fieldtype fieldtype);

    /**
     * 查询所有场地类型
     * @return
     */
    List<Fieldtype> selectAll();
}
