package edu.uni.userBaseInfo1.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.example.config.ExampleConfig;
import edu.uni.userBaseInfo1.bean.StudentRelation;
import edu.uni.userBaseInfo1.bean.StudentRelationExample;
import edu.uni.userBaseInfo1.mapper.StudentRelationMapper;
import edu.uni.userBaseInfo1.service.StudentRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author chenenru
 * @Description StudentRelation实体类的service层接口的实现类
 * @Date 15:49 2019/4/29
 **/

//Service类的注解，标志这是一个服务层接口类，这样才能被Spring”“”“”“”"扫描"到
@SuppressWarnings("ALL")
@Service
public class StudentRelationServiceImpl implements StudentRelationService {
    //持久层接口的对象
    @Autowired
    private StudentRelationMapper studentRelationMapper;//爆红的话编译器的原因，不影响运行

    //配置类，规定了上传文件的路径和分页查询每一页的记录数
    @Autowired
    private ExampleConfig config;

    /**
     * Author: chenenru 15:17 2019/4/29
     * @return List<StudentRelation>
     * @apiNote: 查询所有的地址
     */
    @Override
    public List<StudentRelation> selectAll() {
        return studentRelationMapper.selectByExample(null);
    }

    /**
     * Author: chenenru 15:39 2019/4/29
     * @param id
     * @return StudentRelation
     * @apiNote: 根据id查询出一条地址信息
     */
    @Override
    public StudentRelation selectById(long id) {
        return studentRelationMapper.selectByPrimaryKey(id);
    }

    /**
     * Author: chenenru 1:24 2019/5/5
     * @param userId
     * @return StudentRelation
     * @apiNote: 根据用户的id查询出一条地址信息
     */
    @Override
    public List<StudentRelation> selectByUserId(Long userId) {
        return studentRelationMapper.selectByUserId(userId);
    }

    /**
     * Author: chenenru 15:41 2019/4/29
     * @param pageNum
     * @return List<StudentRelation>
     * @apiNote: 分页查询出所有地址信息
     */
    @Override
    public PageInfo<StudentRelation> selectPage(int pageNum) {
        //设置查询的是哪一页和每页的记录条数
        PageHelper.offsetPage(pageNum, config.getPageSize());

        //无条件查询,查询所有
        List<StudentRelation>StudentRelations =studentRelationMapper.selectByExample(null);

        if (StudentRelations!=null){
            return new PageInfo<>(StudentRelations);
        }
        return null;
    }

    /**
     * Author: chenenru 15:44 2019/4/29
     * @param StudentRelation
     * @return boolean
     * @apiNote: 增加StudentRelation表的一个记录
     */
    @Override
    public boolean insert(StudentRelation StudentRelation) {
        return studentRelationMapper.insert(StudentRelation)>0 ? true : false;
    }

    /**
     * Author: chenenru 15:46 2019/4/29
     * @param StudentRelation
     * @return boolean
     * @apiNote: 用一个新的对象更新StudentRelation表的一个记录
     */
    @Override
    public boolean update(StudentRelation StudentRelation) {
        return studentRelationMapper.updateByPrimaryKey(StudentRelation) > 0 ? true : false;
    }


    /**
     * Author: chenenru 15:47 2019/4/29
     * @param id
     * @return boolean
     * @apiNote: 根据id删除StudentRelation表的一个记录
     */
    @Override
    public boolean delete(long id) {
        return studentRelationMapper.deleteByPrimaryKey(id) > 0 ? true : false;
    }

    /**
     * Author: laizhouhao 19:29 2019/5/7
     * @param studentRelationExample
     * @return List<StudentRelation>
     * @apiNote: 根据自定义条件查询学生亲属记录
     */
    @Override
    public List<StudentRelation> selectByExample(StudentRelationExample studentRelationExample) {
        return studentRelationMapper.selectByExample(studentRelationExample);
    }

    /**
     * Author: laizhouhao 15:13 2019/5/8
     * @param user_id
     * @return List<Long>
     * @apiNote: 根据用户id查询亲属在本系统的id
     */
    @Override
    public List<StudentRelation> selectRelaByUserId(Long user_id) {
        //构造查询条件
        StudentRelationExample studentRelationExample = new StudentRelationExample();
        studentRelationExample.createCriteria().andUserIdEqualTo(user_id).andDeletedEqualTo(false);
        //查找所有的亲属信息
        List<StudentRelation> studentRelations
                = studentRelationMapper.selectByExample(studentRelationExample);
        return studentRelations;
    }

}
