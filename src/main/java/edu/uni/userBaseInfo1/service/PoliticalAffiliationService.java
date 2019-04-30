package edu.uni.userBaseInfo1.service;

import com.github.pagehelper.PageInfo;
import edu.uni.userBaseInfo1.bean.PoliticalAffiliation;

import java.util.List;

public interface PoliticalAffiliationService {
    /**
     * Author: chenenru 23:59 2019/4/29
     * @param
     * @return List<PoliticalAffiliation>
     * @apiNote: 查询所有的政治面貌
     */
    List<PoliticalAffiliation> selectAllPoliticalAffiliations();
    /**
     * Author: chenenru 0:00 2019/4/30
     * @param id
     * @return PoliticalAffiliation
     * @apiNote: 根据id查询政治面貌
     */
    PoliticalAffiliation selectPoliticalAffiliationById(long id);
    /**
     * Author: chenenru 0:00 2019/4/30
     * @param pageNum
     * @return PageInfo<PoliticalAffiliation>
     * @apiNote: 分页查询政治面貌
     */
    PageInfo<PoliticalAffiliation> selectPoliticalAffiliationByPage(int pageNum);
    /**
     * Author: chenenru 0:00 2019/4/30
     * @param PoliticalAffiliation
     * @return boolean
     * @apiNote: 政治面貌添加PoliticalAffiliation表的一条记录
     */
    boolean insertPoliticalAffiliation(PoliticalAffiliation PoliticalAffiliation);
    /**
     * Author: chenenru 0:00 2019/4/30
     * @param PoliticalAffiliation
     * @return boolean
     * @apiNote:  用户更新一个PoliticalAffiliation表的某个记录（传一个新的对象）
     */
    boolean updatePoliticalAffiliation(PoliticalAffiliation PoliticalAffiliation);
    /**
     * Author: chenenru 0:00 2019/4/30
     * @param id
     * @return boolean
     * @apiNote:  用于删除PoliticalAffiliation表的某个记录
     */
    boolean deletePoliticalAffiliation(long id);
}
