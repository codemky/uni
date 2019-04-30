package edu.uni.userBaseInfo1.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.example.config.ExampleConfig;
import edu.uni.userBaseInfo1.bean.FirstLevelDiscipline;
import edu.uni.userBaseInfo1.mapper.FirstLevelDisciplineMapper;
import edu.uni.userBaseInfo1.service.FirstLevelDisciplineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author chenenru
 * @ClassName FirstLevelDisciplineServiceImpl
 * @Description
 * @Date 2019/4/30 15:11
 * @Version 1.0
 **/
//Service类的注解，标志这是一个服务层接口类，这样才能被Spring”“”“”“”"扫描"到=
@Service
public class FirstLevelDisciplineServiceImpl implements FirstLevelDisciplineService {
    //持久层接口的对象
    @Autowired
    private FirstLevelDisciplineMapper firstLevelDisciplineMapper;
    //配置类，规定了上传文件的路径和分页查询每一页的记录数
    @Autowired
    private ExampleConfig config;

    /**
     * Author: chenenru 0:10 2019/4/30
     * @param
     * @return  List<FirstLevelDiscipline>
     * @apiNote: 查询所有一级学科记录，不分页
     */
    @Override
    public List<FirstLevelDiscipline> selectAllFirstLevelDisciplines() {
        return firstLevelDisciplineMapper.selectByExample(null);
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param id
     * @return FirstLevelDiscipline
     * @apiNote: 通过id查询一个一级学科记录
     */
    @Override
    public FirstLevelDiscipline selectFirstLevelDisciplineById(long id) {
        return firstLevelDisciplineMapper.selectByPrimaryKey(id);
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param pageNum
     * @return  PageInfo<FirstLevelDiscipline>
     * @apiNote: 分页查询所有一级学科记录
     */
    @Override
    public PageInfo<FirstLevelDiscipline> selectFirstLevelDisciplineByPage(int pageNum) {
        //设置查询的是哪一页和每一页有多少个记录
        PageHelper.offsetPage(pageNum,config.getPageSize());
        //无条件查询（条件对象为null，即无条件），查询所有
        List<FirstLevelDiscipline> FirstLevelDisciplines = firstLevelDisciplineMapper.selectByExample(null);
        //检验查询的结果
        if(FirstLevelDisciplines !=null){
            return new PageInfo<>(FirstLevelDisciplines);
        }else{
            return null;
        }
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param FirstLevelDiscipline
     * @return boolean
     * @apiNote: 插入一条一级学科记录
     */
    @Override
    public boolean insertFirstLevelDiscipline(FirstLevelDiscipline FirstLevelDiscipline) {
        return  firstLevelDisciplineMapper.insertSelective(FirstLevelDiscipline) > 0 ? true : false;
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param FirstLevelDiscipline
     * @return boolean
     * @apiNote: 更新一条一级学科记录
     */
    @Override
    public boolean updateFirstLevelDiscipline(FirstLevelDiscipline FirstLevelDiscipline) {
        return firstLevelDisciplineMapper.updateByPrimaryKeySelective(FirstLevelDiscipline) >0 ? true : false;
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param id
     * @return boolean
     * @apiNote:  删除一条一级学科记录
     */
    @Override
    public boolean deleteFirstLevelDiscipline(long id) {
        return firstLevelDisciplineMapper.deleteByPrimaryKey(id) > 0 ? true :false;
    }
}
