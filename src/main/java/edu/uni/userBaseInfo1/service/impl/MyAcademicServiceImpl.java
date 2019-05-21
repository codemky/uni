package edu.uni.userBaseInfo1.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.example.config.ExampleConfig;
import edu.uni.professionalcourses.bean.Academic;
import edu.uni.professionalcourses.mapper.AcademicMapper;
import edu.uni.userBaseInfo1.service.MyAcademicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author laizhouhao
 * @Description Academic实体类的service层接口的实现类
 * @Date 15:01 2019/4/30
 **/

//Service类的注解，标志这是一个服务层接口类，这样才能被Spring”“”“”“”"扫描"到
@SuppressWarnings("ALL")
@Service
public class MyAcademicServiceImpl implements MyAcademicService {

    //持久层接口的对象
    @Autowired
    private AcademicMapper academicMapper;  //爆红时由于编译器问题，不影响运行

    //配置类，规定了上传文件的路径和分页查询每一页的记录数
    @Autowired
    private ExampleConfig config;

    /**
     * Author: laizhouhao 15:04 2019/4/30
     * @return List<Academic>
     * @apiNote: 查询所有的受教育程度信息，不分页
     */
    public List<Academic> selectAll() {
        return academicMapper.selectByExample(null);
    }

    /**
     * Author: laizhouhao 15:05 2019/4/30
     * @param id
     * @return Academic
     * @apiNote: 通过id查找一个受教育程度信息
     */
    public Academic selectById(long id) {
        return academicMapper.selectByPrimaryKey(id);
    }

    /**
     * Author: laizhouhao 15:07 2019/4/30
     * @param pageNum
     * @return PageInfo<Academic>
     * @apiNote: 分页查询所有的受教育程度信息
     */
    public PageInfo<Academic> selectPage(int pageNum) {
        //设置查询的是哪一页和每一页有多少个记录
        PageHelper.offsetPage(pageNum,config.getPageSize());

        //无条件查询（条件对象为null，即无条件），查询所有
        List<Academic> academic = academicMapper.selectByExample(null);

        //检验查询的结果
        if(academic != null )
            return new PageInfo<>(academic);
        else
            return null;

    }

    /**
     * Author: laizhouhao 15:09 2019/4/30
     * @param academic
     * @return boolean
     * @apiNote: 插入一条受教育程度信息
     */
    public boolean insert(Academic academic) {
        return academicMapper.insertSelective(academic) > 0 ? true : false;
    }

    /**
     * Author: laizhouhao 15:10 2019/4/30
     * @param academic
     * @return boolean
     * @apiNote: 以一个新的对象更新一条受教育程度信息记录
     */
    public boolean update(Academic academic) {
        return academicMapper.updateByPrimaryKeySelective(academic) > 0 ? true : false;
    }

    /**
     * Author: laizhouhao 15:10 2019/4/30
     * @param id
     * @return boolean
     * @apiNote: 以id删除一条受教育程度信息记录
     */
    public boolean delete(long id) {
        return academicMapper.deleteByPrimaryKey(id) > 0 ? true : false;
    }
}
