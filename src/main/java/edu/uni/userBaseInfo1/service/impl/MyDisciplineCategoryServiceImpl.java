package edu.uni.userBaseInfo1.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.example.config.ExampleConfig;
import edu.uni.professionalcourses.bean.DisciplineCategory;
import edu.uni.professionalcourses.mapper.DisciplineCategoryMapper;
import edu.uni.userBaseInfo1.service.MyDisciplineCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @Author laizhouhao
 * @Description disciplineCategory实体类的service层接口的实现类
 * @Date 15:49 2019/4/29
 **/
import java.util.List;
//Service类的注解，标志这是一个服务层接口类，这样才能被Spring”“”“”“”"扫描"到
@SuppressWarnings("ALL")
@Service
public class MyDisciplineCategoryServiceImpl implements MyDisciplineCategoryService {
    //持久层接口的对象
    @Autowired
    private DisciplineCategoryMapper disciplineCategoryMapper;//爆红的话编译器的原因，不影响运行

    //配置类，规定了上传文件的路径和分页查询每一页的记录数
    @Autowired
    private ExampleConfig config;

    /**
     * Author: laizhouhao 15:17 2019/4/29
     * @return List<DisciplineCategory>
     * @apiNote: 查询所有的学科类别信息
     */
    @Override
    public List<DisciplineCategory> selectAll() {
        return disciplineCategoryMapper.selectByExample(null);
    }

    /**
     * Author: laizhouhao 15:39 2019/4/29
     * @param id
     * @return DisciplineCategory
     * @apiNote: 根据id查询出一条学科类别信息信息
     */
    @Override
    public DisciplineCategory selectById(Long id) {
        return disciplineCategoryMapper.selectByPrimaryKey(id);
    }

    /**
     * Author: laizhouhao 15:41 2019/4/29
     * @param pageNum
     * @return List<DisciplineCategory>
     * @apiNote: 分页查询出所有学科类别信息信息
     */
    @Override
    public PageInfo<DisciplineCategory> selectPage(Integer pageNum) {
        //设置查询的是哪一页和每页的记录条数
        PageHelper.offsetPage(pageNum, config.getPageSize());

        //无条件查询,查询所有
        List<DisciplineCategory> disciplineCategorys = disciplineCategoryMapper.selectByExample(null);

        if (disciplineCategorys!=null){
            return new PageInfo<>(disciplineCategorys);
        }
        return null;
    }

    /**
     * Author: laizhouhao 15:44 2019/4/29
     * @param disciplineCategory
     * @return boolean
     * @apiNote: 增加DisciplineCategory表的一个记录
     */
    @Override
    public boolean insert(DisciplineCategory disciplineCategory) {
        return disciplineCategoryMapper.insert(disciplineCategory)>0 ? true : false;
    }

    /**
     * Author: laizhouhao 15:46 2019/4/29
     * @param disciplineCategory
     * @return boolean
     * @apiNote: 用一个新的对象更新DisciplineCategory表的一个记录
     */
    @Override
    public boolean update(DisciplineCategory disciplineCategory) {
        return disciplineCategoryMapper.updateByPrimaryKey(disciplineCategory) > 0 ? true : false;
    }


    /**
     * Author: laizhouhao 15:47 2019/4/29
     * @param id
     * @return boolean
     * @apiNote: 根据id删除DisciplineCategory表的一个记录
     */
    @Override
    public boolean delete(Long id) {
        return disciplineCategoryMapper.deleteByPrimaryKey(id) > 0 ? true : false;
    }
}
