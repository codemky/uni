package edu.uni.userBaseInfo1.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.example.config.ExampleConfig;
import edu.uni.userBaseInfo1.bean.LearningDegree;
import edu.uni.userBaseInfo1.bean.LearningDegree;
import edu.uni.userBaseInfo1.bean.LearningDegreeExample;
import edu.uni.userBaseInfo1.mapper.AcademicDegreeMapper;
import edu.uni.userBaseInfo1.mapper.AcademicMapper;
import edu.uni.userBaseInfo1.mapper.LearningDegreeMapper;
import edu.uni.userBaseInfo1.service.LearningDegreeSerevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author chenenru
 * @ClassName LearningDegreeSereviceImpl
 * @Description
 * @Date 2019/4/30 15:11
 * @Version 1.0
 **/
//Service类的注解，标志这是一个服务层接口类，这样才能被Spring”“”“”“”"扫描"到=
@SuppressWarnings("ALL")
@Service
public class LearningDegreeSereviceImpl implements LearningDegreeSerevice {
    //持久层接口的对象
    @Autowired
    private LearningDegreeMapper learningDegreeMapper;
    /*@Autowired
    private AcademicMapper academicMapper;
    @Autowired
    private AcademicDegreeMapper academicDegreeMapper;*/
    //配置类，规定了上传文件的路径和分页查询每一页的记录数
    @Autowired
    private ExampleConfig config;

    /**
     * Author: chenenru 0:10 2019/4/30
     * @param
     * @return  List<LearningDegree>
     * @apiNote: 查询所有学历记录，不分页
     */
    @Override
    public List<LearningDegree> selectAllLearningDegrees() {
        return learningDegreeMapper.selectByExample(null);
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param id
     * @return LearningDegree
     * @apiNote: 通过id查询一个学历记录
     */
    @Override
    public LearningDegree selectLearningDegreeById(long id) {
        LearningDegreeExample example = new LearningDegreeExample();
        LearningDegreeExample.Criteria criteria = example.createCriteria();
        criteria.andDegreeIdEqualTo(id);
        //return learningDegreeMapper.selectByPrimaryKey(id);
        return  learningDegreeMapper.selectByExample(example).get(0);
    }

    /**
     * Author: laizhouhao 8:30 2019/5/6
     * @param user_id
     * @return List<LearningDegree>
     * @apiNote:
     */
    @Override
    public List<LearningDegree> selectByUserId(Long user_id) {
        LearningDegreeExample example = new LearningDegreeExample();
        LearningDegreeExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(user_id);
        criteria.andDeletedEqualTo(false);
        //return learningDegreeMapper.selectByUserId(user_id);
        return  learningDegreeMapper.selectByExample(example);
    }

    /**
     * Author: chenenru 0:10 2019/4/30
     * @param pageNum
     * @return  PageInfo<LearningDegree>
     * @apiNote: 分页查询所有学历记录
     */
    @Override
    public PageInfo<LearningDegree> selectLearningDegreeByPage(int pageNum) {
        //设置查询的是哪一页和每一页有多少个记录
        PageHelper.offsetPage(pageNum,config.getPageSize());
        //无条件查询（条件对象为null，即无条件），查询所有
        List<LearningDegree> LearningDegrees = learningDegreeMapper.selectByExample(null);
        //检验查询的结果
        if(LearningDegrees !=null){
            return new PageInfo<>(LearningDegrees);
        }else{
            return null;
        }
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param LearningDegree
     * @return boolean
     * @apiNote: 插入一条学历记录
     */
    @Override
    public boolean insertLearningDegree(LearningDegree LearningDegree) {
        return  learningDegreeMapper.insertSelective(LearningDegree) > 0 ? true : false;
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param LearningDegree
     * @return boolean
     * @apiNote: 更新一条学历记录
     */
    @Override
    public boolean updateLearningDegree(LearningDegree LearningDegree) {
        return learningDegreeMapper.updateByPrimaryKeySelective(LearningDegree) >0 ? true : false;
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param id
     * @return boolean
     * @apiNote:  删除一条学历记录
     */
    @Override
    public boolean deleteLearningDegree(long id) {
        return learningDegreeMapper.deleteByPrimaryKey(id) > 0 ? true :false;
    }

}
