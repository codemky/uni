package edu.uni.userBaseInfo1.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.example.config.ExampleConfig;
import edu.uni.professionalcourses.bean.AcademicDegree;
import edu.uni.professionalcourses.mapper.AcademicDegreeMapper;
import edu.uni.userBaseInfo1.service.MyAcademicDegreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @Author laizhouhao
 * @Description
 * @Date 15:42 2019/4/30
 **/
import java.util.List;
//Service类的注解，标志这是一个服务层接口类，这样才能被Spring”“”“”“”"扫描"到
@SuppressWarnings("ALL")
@Service
public class MyAcademicDegreeServiceImpl implements MyAcademicDegreeService {

    //持久层接口的对象
    @Autowired
    private AcademicDegreeMapper academicDegreeMapper;  //爆红时由于编译器问题，不影响运行

    //配置类，规定了上传文件的路径和分页查询每一页的记录数
    @Autowired
    private ExampleConfig config;

    /**
     * Author: laizhouhao 15:04 2019/4/30
     * @return List<AcademicDegree>
     * @apiNote: 查询所有的学位信息，不分页
     */
    public List<AcademicDegree> selectAll() {
        return academicDegreeMapper.selectByExample(null);
    }

    /**
     * Author: laizhouhao 15:05 2019/4/30
     * @param id
     * @return AcademicDegree
     * @apiNote: 通过id查找一个学位信息
     */
    public AcademicDegree selectById(long id) {
        return academicDegreeMapper.selectByPrimaryKey(id);
    }

    /**
     * Author: laizhouhao 15:07 2019/4/30
     * @param pageNum
     * @return PageInfo<AcademicDegree>
     * @apiNote: 分页查询所有的学位信息
     */
    public PageInfo<AcademicDegree> selectPage(int pageNum) {
        //设置查询的是哪一页和每一页有多少个记录
        PageHelper.offsetPage(pageNum,config.getPageSize());

        //无条件查询（条件对象为null，即无条件），查询所有
        List<AcademicDegree> academicDegree = academicDegreeMapper.selectByExample(null);

        //检验查询的结果
        if(academicDegree != null )
            return new PageInfo<>(academicDegree);
        else
            return null;

    }

    /**
     * Author: laizhouhao 15:09 2019/4/30
     * @param AcademicDegree
     * @return boolean
     * @apiNote: 插入一条学位信息
     */
    public boolean insert(AcademicDegree AcademicDegree) {
        return academicDegreeMapper.insertSelective(AcademicDegree) > 0 ? true : false;
    }

    /**
     * Author: laizhouhao 15:10 2019/4/30
     * @param AcademicDegree
     * @return boolean
     * @apiNote: 以一个新的对象更新一条学位信息记录
     */
    public boolean update(AcademicDegree AcademicDegree) {
        return academicDegreeMapper.updateByPrimaryKeySelective(AcademicDegree) > 0 ? true : false;
    }

    /**
     * Author: laizhouhao 15:10 2019/4/30
     * @param id
     * @return boolean
     * @apiNote: 以id删除一条学位信息记录
     */
    public boolean delete(long id) {
        return academicDegreeMapper.deleteByPrimaryKey(id) > 0 ? true : false;
    }
}

