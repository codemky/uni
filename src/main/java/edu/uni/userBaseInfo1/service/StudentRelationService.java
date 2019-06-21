package edu.uni.userBaseInfo1.service;

import com.github.pagehelper.PageInfo;
import edu.uni.userBaseInfo1.bean.*;

import java.util.HashMap;
import java.util.List;

/**
 * @Author chenenru
 * @Description studentRelation实体类的Service层接口
 * @Date 10:01 2019/4/30
 **/
public interface StudentRelationService {

        /**
         * Author: chenenru 10:04 2019/4/30
         * @return List<StudentRelation>
         * @apiNote: 获取所有学生亲属的学生亲属信息
         */
        List<StudentRelation> selectAll();

        /**
         * Author: chenenru 10:05 2019/4/30
         * @param id
         * @return  StudentRelation
         * @apiNote: 根据id获取单条学生亲属信息
         */
        StudentRelation selectById(long id);

        /**
         * Author: chenenru 15:26 2019/5/5
         * @param userId
         * @return  List<StudentRelation>
         * @apiNote: 根据学生的id查询多条学生亲属记录
         */
        List<StudentRelation> selectByUserId(Long userId);

        /**
         * Author: mokuanyuan 20:47 2019/6/20
         * @param relationId
         * @return  List<StudentRelation>
         * @apiNote 根据学生亲属用户id查询多条学生亲属记录
         */
        List<StudentRelation> selectByRelationId(Long relationId);

        /**
         * Author: chenenru 10:06 2019/4/30
         * @param pageNum
         * @return List<StudentRelation>
         * @apiNote: 分页查询学生亲属信息
         */
        PageInfo<StudentRelation> selectPage(int pageNum);

        /**
         * Author: chenenru 10:07 2019/4/30
         * @param studentRelation
         * @return boolean
         * @apiNote: 增加StudentRelation表的一个记录
         */
        boolean insert(StudentRelation studentRelation);

        /**
         * Author: chenenru 10:08 2019/4/30
         * @param studentRelation
         * @return boolean
         * @apiNote: 更新StudentRelation表的一个记录（传一个新的对象）
         */
        boolean update(StudentRelation studentRelation);

        /**
         * Author: chenenru 10:09 2019/4/30
         * @param id
         * @return boolean
         * @apiNote: 删除StudentRelation表的某个记录
         */
        boolean delete(long id);

        /**
         * Author: laizhouhao 18:21 2019/5/7
         * @param studentRelationExample
         * @return List<StudentRelation>
         * @apiNote: 根据自定义条件查询StudentRelation表的一个记录
         */
        List<StudentRelation> selectByExample(StudentRelationExample studentRelationExample);

        /**
         * Author: laizhouhao 15:07 2019/5/8
         * @param user_id
         * @return List<StudentRelation>
         * @apiNote: 根据用户id查询所有有效的亲属信息
         */
        List<StudentRelation> selectValidRelaByUserId(Long user_id);

        /**
         * Author: chenenru 16:21 2019/5/10
         * @param relaId
         * @return user_id
         * @apiNote: 根据亲属用户id查询孩子用户id
         */
        StudentRelation selectUserIdByRelaId(Long relaId);

//        /**
//         * Author: laizhouhao 15:18 2019/5/14
//         * @param requestMessage
//         * @return boolean
//         * @apiNote: 用户点击申请修改亲属信息
//         */
//        boolean clickApplyStudentRelation(RequestMessage requestMessage);

        /**
         * Author: mokuanyuan 16:55 2019/6/13
         * @param map
         * @param studentRelation
         * @param oldId
         * @param newId
         * @param loginUser
         * @param modifiedUser
         * @return boolean
         * @apiNote: 用户点击申请时进行的一些系列为了创建申请记录所做的准备
         */
        public boolean readyForApply(HashMap<String, Object> map, StudentRelation studentRelation, Long oldId,
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
         * Author: laizhouhao 15:58 2019/6/10
         * @param studentRelationList
         * @return 用户的亲属信息
         * @apiNote: 根据用户的亲属实体获取用户所有亲属的详细信息
         */
        void getStuRelationInfo(HashMap<String, Object>map, List<StudentRelation>studentRelationList);
}
