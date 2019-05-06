package edu.uni.userBaseInfo1.service;

import com.github.pagehelper.PageInfo;
import edu.uni.userBaseInfo1.bean.Student;

import java.util.List;

/**
 * @Author laizhouhao
 * @Description Student实体类的Service层接口
 * @Date 10:01 2019/4/30
 **/
public interface StudentService {

        /**
         * Author: laizhouhao 10:04 2019/4/30
         * @return List<Student>
         * @apiNote: 获取所有学生的学生信息
         */
        List<Student> selectAll();

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
}
