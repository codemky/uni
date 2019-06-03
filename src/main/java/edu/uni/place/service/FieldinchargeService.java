package edu.uni.place.service;

import com.github.pagehelper.PageInfo;
import edu.uni.place.bean.Fieldincharge;

public interface FieldinchargeService {
    /**
     * 新增场地管理员
     * @param fieldincharge
     * @return
     */
    boolean insert(Fieldincharge fieldincharge);

    /**
     * 删除场地管理员
     * @param id
     * @return
     */
    boolean delete(Long id);

    /**
     * 修改场地管理员
     * @param fieldincharge
     * @return
     */
    boolean update(Fieldincharge fieldincharge);

    /**
     * 查询场地管理员详情
     * @param id
     * @return
     */
    Fieldincharge select(Long id);

    /**
     * 分学校、分场地、分用户、分页查询场地管理员
     * @param pageNum
     * @param universityId
     * @param fieldId
     * @param userId
     * @return
     */
    PageInfo<Fieldincharge> selectPageByUIdFIdUId(int pageNum, Long universityId, Long fieldId, Long userId);


}
