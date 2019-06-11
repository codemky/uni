package edu.uni.auth.service;

import com.github.pagehelper.PageInfo;
import edu.uni.auth.bean.UnivInfoSUP;
import edu.uni.auth.exception.UnivInfoSUPException;

import java.util.List;

/**
 * @author 何亮
 */
public interface UnivInfoSUPService {

    /**
     * 新增学校信息管理员
     * 前提：
     *     1. 就任用户不能已是其他学校的信息管理员
     *     2. 学校不能已存在学校信息管理员
     * @param uid
     * @param universityId
     * @return
     */
    boolean insert(long uid, long universityId, int status) throws UnivInfoSUPException;

    /**
     * 删除学校信息管理员
     * @param universityId
     * @return
     */
    boolean delete(long universityId);

    /**
     * 更新学校信息管理员状态
     * @param universityId
     * @param status
     * @return
     * @throws UnivInfoSUPException
     */
    boolean updateStatusByUniversityId(Long universityId, Integer status) ;

    /**
     * 获取所有学校的信息管理者
     * @return
     */
    List<UnivInfoSUP> selectPage(int pageNum, int pageSize);

    /**
     * 根据学校的中英文名，分頁模糊搜索符合条件的前十条学校信息管理者信息
     * @param pageNum
     * @param pageSize
     * @param name
     * @param ename
     * @return
     */
    PageInfo selectLikeUnivNameEname(int pageNum, int pageSize, String name, String ename);

    /**
     * 根据信息管理员的用户id，搜索其管理的学校的ID
     * @param supId
     * @return
     */
    Long selectUniversityIdBySupId(long supId) throws UnivInfoSUPException;
}
