package edu.uni.userBaseInfo1.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.example.config.ExampleConfig;
import edu.uni.userBaseInfo1.bean.AddrState;
import edu.uni.userBaseInfo1.mapper.AddrStateMapper;
import edu.uni.userBaseInfo1.service.AddrStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author chenenru
 * @ClassName AddrStateServiceImpl
 * @Description
 * @Date 2019/5/2 9:46
 * @Version 1.0
 **/
//Service类的注解，标志这是一个服务层接口类，这样才能被Spring”“”“”“”"扫描"到=
@SuppressWarnings("ALL")
@Service
public class AddrStateServiceImpl implements AddrStateService {

    //持久层接口的对象
    @Autowired
    private AddrStateMapper addrStateMapper;
    //配置类，规定了上传文件的路径和分页查询每一页的记录数
    @Autowired
    private ExampleConfig config;

    /**
     * Author: chenenru 0:10 2019/4/30
     * @param
     * @return  List<AddrState>
     * @apiNote: 查询所有省份记录，不分页
     */
    @Override
    public List<AddrState> selectAllAddrStates() {
        return addrStateMapper.selectByExample(null);
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param id
     * @return AddrState
     * @apiNote: 通过id查询一个省份记录
     */
    @Override
    public AddrState selectAddrStateById(long id) {
        return addrStateMapper.selectByPrimaryKey(id);
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param pageNum
     * @return  PageInfo<AddrState>
     * @apiNote: 分页查询所有省份记录
     */
    @Override
    public PageInfo<AddrState> selectAddrStateByPage(int pageNum) {
        //设置查询的是哪一页和每一页有多少个记录
        PageHelper.offsetPage(pageNum,config.getPageSize());
        //无条件查询（条件对象为null，即无条件），查询所有
        List<AddrState> AddrStates = addrStateMapper.selectByExample(null);
        //检验查询的结果
        if(AddrStates !=null){
            return new PageInfo<>(AddrStates);
        }else{
            return null;
        }
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param AddrState
     * @return boolean
     * @apiNote: 插入一条省份记录
     */
    @Override
    public boolean insertAddrState(AddrState AddrState) {
        return  addrStateMapper.insertSelective(AddrState) > 0 ? true : false;
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param AddrState
     * @return boolean
     * @apiNote: 更新一条省份记录
     */
    @Override
    public boolean updateAddrState(AddrState AddrState) {
        return addrStateMapper.updateByPrimaryKeySelective(AddrState) >0 ? true : false;
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param id
     * @return boolean
     * @apiNote:  删除一条省份记录
     */
    @Override
    public boolean deleteAddrState(long id) {
        return addrStateMapper.deleteByPrimaryKey(id) > 0 ? true :false;
    }

    /**
     * Author: chenenru 19:23 2019/5/8
     * @param countryCode
     * @return List<AddrState>
     * @apiNote: 根据国家的编码查询对应的所有省份
     */
    @Override
    public List<AddrState> selectAllAddrStatesByCountryCode(Long countryCode) {
        return addrStateMapper.selectByCountryCode(countryCode);
    }
}
