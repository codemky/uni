package edu.uni.place.service;

import com.github.pagehelper.PageInfo;
import edu.uni.place.bean.Fieldapply;

public interface FieldapplyService {
    /**
     * 新增场地使用申请
     * @param fieldapply
     * @return
     */
    boolean insert(Fieldapply fieldapply);

    /**
     * 删除场地使用申请
     * @param id
     * @return
     */
    boolean delete(Long id);

    /**
     * 修改场地使用申请
     * @param fieldapply
     * @return
     */
    boolean update(Fieldapply fieldapply);

    /**
     * 查询场地使用申请详情
     * @param id
     * @return
     */
    Fieldapply select(Long id);

    /**
     * 分学校、分场地、分用户、分校历、分页查询场地使用申请
     * @param pageNum
     * @param universityId
     * @param fieldId
     * @param userId
     * @return
     */
    PageInfo<Fieldapply> selectPageByUIdFIdUIdCId(int pageNum, Long universityId, Long fieldId, Long userId);

}
