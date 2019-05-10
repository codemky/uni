package edu.uni.userBaseInfo1.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.example.config.ExampleConfig;
import edu.uni.userBaseInfo1.bean.Student;
import edu.uni.userBaseInfo1.bean.StudentExample;
import edu.uni.userBaseInfo1.mapper.StudentMapper;
import edu.uni.userBaseInfo1.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author laizhouhao
 * @Description Student实体类的service层接口的实现类
 * @Date 10:11 2019/4/30
 **/
//Service类的注解，标志这是一个服务层接口类，这样才能被Spring”“”“”“”"扫描"到
@SuppressWarnings("ALL")
@Service
public class StudentServiceImpl implements StudentService {
    //持久层接口的对象
    @Autowired
    private StudentMapper studentMapper;  //爆红时由于编译器问题，不影响运行

    //配置类，规定了上传文件的路径和分页查询每一页的记录数
    @Autowired
    private ExampleConfig config;

    /**
     * Author: laizhouhao 10:14 2019/4/30
     * @return List<Student>
     * @apiNote: 查询所有学生记录，不分页
     */
    public List<Student> selectAll() {
        return studentMapper.selectByExample(null);
    }

    /**
     * Author: mokuanyuan 12:55 2019/5/10
     * @param classId
     * @return List<Student>
     * @apiNote: 根据班级id查询所有学生信息
     */
    @Override
    public List<Student> selectByClassId(Long classId) {
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andUserIdEqualTo(classId).andDeletedEqualTo(false);

        return studentMapper.selectByExample(studentExample);
    }

    /**
     * Author: laizhouhao 10:15 2019/4/30
     * @param id
     * @return Student
     * @apiNote: 通过id查询一个学生记录
     */
    public Student selectById(long id) {
        return studentMapper.selectByPrimaryKey(id);
    }

    /**
     * Author: laizhouhao 18:34 2019/5/6
     * @param user_id
     * @return List<Student>
     * @apiNote: 根据用户id查找学生信息
     */
    @Override
    public List<Student> selectByUserId(Long user_id) {
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andUserIdEqualTo(user_id).andDeletedEqualTo(false);

        return studentMapper.selectByExample(studentExample);

    }

    /**
     * Author: laizhouhao 10:16 2019/4/30
     * @param pageNum
     * @return pageInfo<Student>
     * @apiNote: 分页查询所有学生记录
     */
    public PageInfo<Student> selectPage(int pageNum) {
        //设置查询的是哪一页和每一页有多少个记录
        PageHelper.offsetPage(pageNum,config.getPageSize());

        //无条件查询（条件对象为null，即无条件），查询所有
        List<Student> students = studentMapper.selectByExample(null);

        //检验查询的结果
        if(students != null )
            return new PageInfo<>(students);
        else
            return null;

    }

    /**
     * Author: laizhouhao 10:17 2019/4/30
     * @param student
     * @return boolean
     * @apiNote: 插入一条电子学生记录
     */
    public boolean insert(Student student) {
        return studentMapper.insertSelective(student) > 0 ? true : false;
    }

    /**
     * Author: laizhouhao 10:18 2019/4/30
     * @param student
     * @return  boolean
     * @apiNote: 以一个新的对象更新一条学生记录
     */
    public boolean update(Student student) {
        return studentMapper.updateByPrimaryKeySelective(student) > 0 ? true : false;
    }

    /**
     * Author: laizhouhao 10:18 2019/4/30
     * @param id
     * @return boolean
     * @apiNote: 以id删除一条学生记录
     */
    public boolean delete(long id) {
        return studentMapper.deleteByPrimaryKey(id) > 0 ? true : false;
    }

    /**
     * Author: laizhouhao 19:29 2019/5/7
     * @param studentExample
     * @return List<Student>
     * @apiNote: 根据自定义条件查询学生记录
     */
    @Override
    public List<Student> selectByExample(StudentExample studentExample) {
        return studentMapper.selectByExample(studentExample);
    }

    /**
     * Author: laizhouhao 14:59 2019/5/8
     * @param stu_no
     * @return Long
     * @apiNote: 根据学号查询用户id
     */
    @Override
    public Long selectByStuNo(String stu_no) {
        //根据学号查询学生
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andStuNoEqualTo(stu_no).andDeletedEqualTo(false);
        List<Student> students = studentMapper.selectByExample(studentExample);
        Long user_id = students.get(0).getUserId();
        return user_id;
    }

    /**
     * Author: laizhouhao 15:22 2019/5/9
     * @param user_id
     * @return List<Student>
     * @apiNote: 根据用户id查找有效的学生信息
     */
    @Override
    public List<Student> selectValidStudentByUserId(Long user_id) {
        //构造查询条件
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andUserIdEqualTo(user_id)
                .andDeletedEqualTo(false);
        return studentMapper.selectByExample(studentExample);
    }
}
