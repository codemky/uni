package edu.uni.userBaseInfo1.service;

import com.github.pagehelper.PageInfo;
import edu.uni.userBaseInfo1.bean.Student;
import edu.uni.userBaseInfo1.bean.StudentExample;
import edu.uni.userBaseInfo1.bean.StudentRelation;
import edu.uni.userBaseInfo1.bean.StudentRelationExample;

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
         * @apiNote: 根据用户id查询所有的亲属信息
         */
        List<StudentRelation> selectRelaByUserId(Long user_id);

}
