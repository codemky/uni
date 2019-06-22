package edu.uni.userBaseInfo1.service;

import com.github.pagehelper.PageInfo;
import edu.uni.userBaseInfo1.bean.*;
import edu.uni.userBaseInfo1.utils.UserInfo;
import org.hibernate.validator.constraints.br.CNPJ;

import java.util.HashMap;
import java.util.List;

/**
 * @Author laizhouhao
 * @Description Student实体类的Service层接口
 * @Date 10:01 2019/4/30
 **/
public interface StudentService {

        /**
         * Author: mokuanyuan 20:02 2019/6/9
         * @param map
         * @param student
         * @apiNote: 把student对象里的id信息内容查询出来，并把相应的信息放进map里
         */
        public void selectByUserIdToMap(HashMap map , Student student);

        /**
         * Author: mokuanyuan 14:52 2019/6/12
         * @param oldId
         * @param newId
         * @return boolean 操作结果
         * @apiNote: 当审批的最后一步都通过后进行的操作，把相应的信息记录进行更新操作
         */
        public boolean updateForApply(Long oldId,Long newId);

        /**
         * Author: mokuanyuan 16:55 2019/6/13
         * @param map
         * @param student
         * @param idList
         * @param loginUser
         * @param modifiedUser
         * @return boolean
         * @apiNote: 用户点击申请时进行的一些系列为了创建申请记录所做的准备
         */
        public boolean readyForApply(HashMap<String, Object> map, Student student, long[] idList,
                                     edu.uni.auth.bean.User loginUser, edu.uni.userBaseInfo1.bean.User modifiedUser);

        /**
         * Author: mokuanyuan 15:42 2019/6/20
         * @param studentUserId
         * @param employeeId
         * @return boolean
         * @apiNote 传入一个学生用户id和职员用户id，判断他们之间是学生与班主任的关系（判断学生所在班级的班主任是不是该职员）
         */
        public boolean isHeadTeacher(Long studentUserId,Long employeeId);

        /**
         * Author: mokuanyuan 15:57 2019/6/20
         * @param RelationUserId
         * @param employeeUserId
         * @return boolean
         * @apiNote 传入一个学生亲属用户id和职员用户id，判断这个亲属的学生和该职员的关系
         */
        public boolean isChildTeacher(Long RelationUserId,Long employeeUserId);

        /**
         * Author: mokuanyuan 20:30 2019/6/20
         * @param studentUserId
         * @param employeeUserId
         * @return boolean
         * @apiNote 传入一个学生用户id和职员用户id，判断这两者是否在同一学院
         */
        public boolean isSameDepartment(Long studentUserId, Long employeeUserId);

        /**
         * Author: mokuanyuan 21:06 2019/6/20
         * @param studentUserId
         * @param loginUserId
         * @return boolean
         * @apiNote 判断登录者是否有权查看该学生用户的信息
         */
        public boolean whetherSeeStudent(Long studentUserId, Long loginUserId);

        /**
         * Author: mokuanyuan 21:09 2019/6/20
         * @param employeeUserId
         * @param loginUserId
         * @return boolean
         * @apiNote
         */
        public boolean whetherSeeEmployee(Long employeeUserId,Long loginUserId);


        public boolean whetherSeeRelation(Long relationUserId,Long loginUserId);

        /**
         * Author: laizhouhao 10:04 2019/4/30
         * @return List<Student>
         * @apiNote: 获取所有学生的学生信息
         */
        List<Student> selectAll();

        /**
         * Author: mokuanyuan 12:55 2019/5/10
         * @param classId
         * @return List<Student>
         * @apiNote: 根据班级id查询所有学生信息
         */
        List<Student> selectByClassId(Long classId);

        /**
         * Author: laizhouhao 10:05 2019/4/30
         * @param id
         * @return  Student
         * @apiNote: 根据id获取单条学生信息
         */
        Student selectById(long id);

        /**
         * Author: laizhouhao 18:34 2019/5/6
         * @param user_id
         * @return List<Student>
         * @apiNote: 根据用户id查找学生信息
         */
        List<Student>selectByUserId(Long user_id);

        /**
         * Author: laizhouhao 10:06 2019/4/30
         * @param pageNum
         * @return List<Student>
         * @apiNote: 分页查询学生信息
         */
        PageInfo<Student> selectPage(int pageNum);

        /**
         * Author: laizhouhao 10:07 2019/4/30
         * @param student
         * @return boolean
         * @apiNote: 增加Student表的一个记录
         */
        boolean insert(Student student);

        /**
         * Author: laizhouhao 10:08 2019/4/30
         * @param student
         * @return boolean
         * @apiNote: 更新Student表的一个记录（传一个新的对象）
         */
        boolean update(Student student);

        /**
         * Author: laizhouhao 10:09 2019/4/30
         * @param id
         * @return boolean
         * @apiNote: 删除Student表的某个记录
         */
        boolean delete(long id);

        /**
         * Author: laizhouhao 18:21 2019/5/7
         * @param studentExample
         * @return List<Student>
         * @apiNote: 根据自定义条件查询Student的一个记录
         */
        List<Student> selectByExample(StudentExample studentExample);

        /**
         * Author: laizhouhao 14:54 2019/5/8
         * @param stu_no
         * @return
         * @apiNote: 根据学号查询学生的用户id
         */
        Long selectByStuNo(String stu_no);

        /**
         * Author: laizhouhao 15:22 2019/5/9
         * @param user_id
         * @return List<Student>
         * @apiNote: 根据用户id查找有效的学生信息
         */
        List<Student> selectValidStudentByUserId(Long user_id);

        /**
         * Author: mokuanyuan 18:33 2019/6/11
         * @param student
         * @param userInfo_apply
         * @return boolean
         * @apiNote: 用户点击申请修改学生主要信息
         */
//        boolean clickApplyStudent(Student student , UserinfoApply userInfo_apply);

        /**
         * Author: chenenru 15:44 2019/5/16
         * @param  student_id
         * @return Student
         * @apiNote: 根据学生id获取学生实体信息
         */
        Student selectValidStudentByStuId(Long student_id);

        /**
         * Author: laizhouhao 21:33 2019/6/2
         * @param stu_no
         * @return 学生实体
         * @apiNote: 根据学号获取学生实体
         */
        Student selectValidStuByStuNo(String stu_no);

        /**
         * Author: laizhouhao 18:55 2019/6/10
         * @param studentList
         * @return 用户的有效的学生信息详情
         * @apiNote: 根据用户的学生实体获取用户的所有有效的学生信息详情
         */
        void getStudent(HashMap<String,Object>map,List<Student>studentList);
        /**
         * Author: chenenru 17:05 2019/6/19
         * @param
         * @return
         * @apiNote: 根据学号和学校的id获取学生实体
         */
        Student selectValidStuByStuNoAndUniId(String stu_no,Long uniId);
        /**
         * Author: chenenru 15:49 2019/6/20
         * @param
         * @return
         * @apiNote: 根据年级查找学生，用来校验年级是否存在
         */
        List<Student> selectByGrade(String grade);
}
