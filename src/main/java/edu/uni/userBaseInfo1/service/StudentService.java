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
         * Author: chenenru 18:35 2019/5/13
         * @param requestMessage
         * @return boolean
         * @apiNote: 用户点击申请修改学生主要信息
         */
        boolean clickApplyStudent(RequestMessage requestMessage);

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
}
