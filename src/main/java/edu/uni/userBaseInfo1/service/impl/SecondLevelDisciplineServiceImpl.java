package edu.uni.userBaseInfo1.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.example.config.ExampleConfig;
import edu.uni.userBaseInfo1.bean.SecondLevelDiscipline;
import edu.uni.userBaseInfo1.mapper.SecondLevelDisciplineMapper;
import edu.uni.userBaseInfo1.service.SecondLevelDisciplineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author chenenru
 * @ClassName SecondLevelDisciplineServiceImpl
 * @Description
 * @Date 2019/4/30 15:12
 * @Version 1.0
 **/
//Service类的注解，标志这是一个服务层接口类，这样才能被Spring”“”“”“”"扫描"到=
@Service
public class SecondLevelDisciplineServiceImpl implements SecondLevelDisciplineService {
    //持久层接口的对象
    @Autowired
    private SecondLevelDisciplineMapper secondLevelDisciplineMapper;
    //配置类，规定了上传文件的路径和分页查询每一页的记录数
    @Autowired
    private ExampleConfig config;

    /**
     * Author: chenenru 0:10 2019/4/30
     * @param
     * @return  List<SecondLevelDiscipline>
     * @apiNote: 查询所有二级学科记录，不分页
     */
    @Override
    public List<SecondLevelDiscipline> selectAllSecondLevelDisciplines() {
        return secondLevelDisciplineMapper.selectByExample(null);
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param id
     * @return SecondLevelDiscipline
     * @apiNote: 通过id查询一个二级学科记录
     */
    @Override
    public SecondLevelDiscipline selectSecondLevelDisciplineById(long id) {
        return secondLevelDisciplineMapper.selectByPrimaryKey(id);
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param pageNum
     * @return  PageInfo<SecondLevelDiscipline>
     * @apiNote: 分页查询所有二级学科记录
     */
    @Override
    public PageInfo<SecondLevelDiscipline> selectSecondLevelDisciplineByPage(int pageNum) {
        //设置查询的是哪一页和每一页有多少个记录
        PageHelper.offsetPage(pageNum,config.getPageSize());
        //无条件查询（条件对象为null，即无条件），查询所有
        List<SecondLevelDiscipline> SecondLevelDisciplines = secondLevelDisciplineMapper.selectByExample(null);
        //检验查询的结果
        if(SecondLevelDisciplines !=null){
            return new PageInfo<>(SecondLevelDisciplines);
        }else{
            return null;
        }
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param SecondLevelDiscipline
     * @return boolean
     * @apiNote: 插入一条二级学科记录
     */
    @Override
    public boolean insertSecondLevelDiscipline(SecondLevelDiscipline SecondLevelDiscipline) {
        return  secondLevelDisciplineMapper.insertSelective(SecondLevelDiscipline) > 0 ? true : false;
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param SecondLevelDiscipline
     * @return boolean
     * @apiNote: 更新一条二级学科记录
     */
    @Override
    public boolean updateSecondLevelDiscipline(SecondLevelDiscipline SecondLevelDiscipline) {
        return secondLevelDisciplineMapper.updateByPrimaryKeySelective(SecondLevelDiscipline) >0 ? true : false;
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param id
     * @return boolean
     * @apiNote:  删除一条二级学科记录
     */
    @Override
    public boolean deleteSecondLevelDiscipline(long id) {
        return secondLevelDisciplineMapper.deleteByPrimaryKey(id) > 0 ? true :false;
    }
}
