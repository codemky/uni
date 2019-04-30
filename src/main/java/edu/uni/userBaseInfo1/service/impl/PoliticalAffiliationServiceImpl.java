package edu.uni.userBaseInfo1.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.example.config.ExampleConfig;
import edu.uni.userBaseInfo1.bean.PoliticalAffiliation;
import edu.uni.userBaseInfo1.mapper.PoliticalAffiliationMapper;
import edu.uni.userBaseInfo1.service.PoliticalAffiliationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author chenenru
 * @ClassName PoliticalAffiliationServiceImpl
 * @Description
 * @Date 2019/4/30 15:12
 * @Version 1.0
 **/
//Service类的注解，标志这是一个服务层接口类，这样才能被Spring”“”“”“”"扫描"到=
@Service
public class PoliticalAffiliationServiceImpl implements PoliticalAffiliationService {
    //持久层接口的对象
    @Autowired
    private PoliticalAffiliationMapper politicalAffiliationMapper;
    //配置类，规定了上传文件的路径和分页查询每一页的记录数
    @Autowired
    private ExampleConfig config;

    /**
     * Author: chenenru 0:10 2019/4/30
     * @param
     * @return  List<PoliticalAffiliation>
     * @apiNote: 查询所有政治面貌记录，不分页
     */
    @Override
    public List<PoliticalAffiliation> selectAllPoliticalAffiliations() {
        return politicalAffiliationMapper.selectByExample(null);
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param id
     * @return PoliticalAffiliation
     * @apiNote: 通过id查询一个政治面貌记录
     */
    @Override
    public PoliticalAffiliation selectPoliticalAffiliationById(long id) {
        return politicalAffiliationMapper.selectByPrimaryKey(id);
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param pageNum
     * @return  PageInfo<PoliticalAffiliation>
     * @apiNote: 分页查询所有政治面貌记录
     */
    @Override
    public PageInfo<PoliticalAffiliation> selectPoliticalAffiliationByPage(int pageNum) {
        //设置查询的是哪一页和每一页有多少个记录
        PageHelper.offsetPage(pageNum,config.getPageSize());
        //无条件查询（条件对象为null，即无条件），查询所有
        List<PoliticalAffiliation> PoliticalAffiliations = politicalAffiliationMapper.selectByExample(null);
        //检验查询的结果
        if(PoliticalAffiliations !=null){
            return new PageInfo<>(PoliticalAffiliations);
        }else{
            return null;
        }
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param PoliticalAffiliation
     * @return boolean
     * @apiNote: 插入一条政治面貌记录
     */
    @Override
    public boolean insertPoliticalAffiliation(PoliticalAffiliation PoliticalAffiliation) {
        return  politicalAffiliationMapper.insertSelective(PoliticalAffiliation) > 0 ? true : false;
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param PoliticalAffiliation
     * @return boolean
     * @apiNote: 更新一条政治面貌记录
     */
    @Override
    public boolean updatePoliticalAffiliation(PoliticalAffiliation PoliticalAffiliation) {
        return politicalAffiliationMapper.updateByPrimaryKeySelective(PoliticalAffiliation) >0 ? true : false;
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param id
     * @return boolean
     * @apiNote:  删除一条政治面貌记录
     */
    @Override
    public boolean deletePoliticalAffiliation(long id) {
        return politicalAffiliationMapper.deleteByPrimaryKey(id) > 0 ? true :false;
    }
}
