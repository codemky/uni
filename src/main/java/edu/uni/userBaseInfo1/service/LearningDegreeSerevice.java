package edu.uni.userBaseInfo1.service;

import com.github.pagehelper.PageInfo;
import edu.uni.userBaseInfo1.bean.EmployeeHistory;
import edu.uni.userBaseInfo1.bean.LearningDegree;
import edu.uni.userBaseInfo1.bean.RequestMessage;
import edu.uni.userBaseInfo1.bean.User;

import java.util.HashMap;
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
     * Author: laizhouhao 8:29 2019/5/6
     * @param user_id
     * @return List<LearningDegree>
     * @apiNote: 根据user_id查询学历
     */
    List<LearningDegree>selectByUserId(Long user_id);

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
    /**
     * Author: chenenru 21:48 2019/5/8
     * @param userId
     * @return LearningDegree
     * @apiNote: 根据职员的id查询有效的学历记录
     */
   /* LearningDegree selectLearingDegreeByEmployeeId(long userId);*/

    /**
     * Author: laizhouhao 18:13 2019/5/10
     * @param user_id
     * @return List<LearningDegree>
     * @apiNote: 根据用户id获取有效的学历信息
     */
    List<LearningDegree> selectValidLeaDeByUserId(Long user_id);
    /**
     * Author: chenenru 20:05 2019/5/13
     * @param 
     * @return 
     * @apiNote: 用户点击申请修改学历
     */
    boolean clickApplyLearningDegree(RequestMessage requestMessage);

    /**
     * Author: mokuanyuan 20:27 2019/6/2
     * @param map
     * @param learningDegree
     * @apiNote: 传入一个HashMap和LearningDegree对象，把LearningDegree里的id字段对应的信息内容放入到map里
     */
    public void selectAllInfoToMap(HashMap map,LearningDegree learningDegree);

    /**
     * Author: mokuanyuan 16:55 2019/6/13
     * @param map
     * @param learningDegree
     * @param oldId
     * @param newId
     * @param loginUser
     * @param modifiedUser
     * @return boolean
     * @apiNote: 用户点击申请时进行的一些系列为了创建申请记录所做的准备
     */
    public boolean readyForApply(HashMap<String, Object> map, LearningDegree learningDegree, Long oldId,
                                 Long newId, edu.uni.auth.bean.User loginUser, User modifiedUser);

    /**
     * Author: mokuanyuan 14:52 2019/6/12
     * @param oldId
     * @param newId
     * @return boolean 操作结果
     * @apiNote: 当审批的最后一步都通过后进行的操作，把相应的信息记录进行更新操作
     */
    public boolean updateForApply(Long oldId,Long newId);

    /**
     * Author: laizhouhao 16:28 2019/6/10
     * @param learningDgree
     * @param map
     * @return 用户的学历信息
     * @apiNote: 根据职员用户用户id获取职员用户的学历
     */
    public void getLearningDegree(HashMap<String, Object> map, LearningDegree learningDgree);
}
