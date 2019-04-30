package edu.uni.userBaseInfo1.service;

import com.github.pagehelper.PageInfo;
import edu.uni.userBaseInfo1.bean.LearningDegree;

import java.util.List;

public interface LearningDegreeSerevice {
    /**
     * Author: chenenru 23:59 2019/4/29
     * @param
     * @return List<LearningDegree>
     * @apiNote: 查询所有的学历
     */
    List<LearningDegree> selectAllLearningDegrees();
    /**
     * Author: chenenru 0:00 2019/4/30
     * @param id
     * @return LearningDegree
     * @apiNote: 根据id查询学历
     */
    LearningDegree selectLearningDegreeById(long id);
    /**
     * Author: chenenru 0:00 2019/4/30
     * @param pageNum
     * @return PageInfo<LearningDegree>
     * @apiNote: 分页查询学历
     */
    PageInfo<LearningDegree> selectLearningDegreeByPage(int pageNum);
    /**
     * Author: chenenru 0:00 2019/4/30
     * @param LearningDegree
     * @return boolean
     * @apiNote: 学历添加LearningDegree表的一条记录
     */
    boolean insertLearningDegree(LearningDegree LearningDegree);
    /**
     * Author: chenenru 0:00 2019/4/30
     * @param LearningDegree
     * @return boolean
     * @apiNote:  用户更新一个LearningDegree表的某个记录（传一个新的对象）
     */
    boolean updateLearningDegree(LearningDegree LearningDegree);
    /**
     * Author: chenenru 0:00 2019/4/30
     * @param id
     * @return boolean
     * @apiNote:  用于删除LearningDegree表的某个记录
     */
    boolean deleteLearningDegree(long id);
}
