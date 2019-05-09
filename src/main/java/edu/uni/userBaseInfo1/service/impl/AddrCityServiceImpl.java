package edu.uni.userBaseInfo1.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.example.config.ExampleConfig;
import edu.uni.userBaseInfo1.bean.AddrCity;
import edu.uni.userBaseInfo1.mapper.AddrCityMapper;
import edu.uni.userBaseInfo1.service.AddrCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author chenenru
 * @ClassName AddrCityServiceImpl
 * @Description
 * @Date 2019/5/2 9:46
 * @Version 1.0
 **/
//Service类的注解，标志这是一个服务层接口类，这样才能被Spring”“”“”“”"扫描"到=
@SuppressWarnings("ALL")
@Service
public class AddrCityServiceImpl implements AddrCityService {
    //持久层接口的对象
    @Autowired
    private AddrCityMapper addrCityMapper;
    //配置类，规定了上传文件的路径和分页查询每一页的记录数
    @Autowired
    private ExampleConfig config;

    /**
     * Author: chenenru 0:10 2019/4/30
     * @param
     * @return  List<AddrCity>
     * @apiNote: 查询所有城市记录，不分页
     */
    @Override
    public List<AddrCity> selectAllAddrCitys() {
        return addrCityMapper.selectByExample(null);
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param id
     * @return AddrCity
     * @apiNote: 通过id查询一个城市记录
     */
    @Override
    public AddrCity selectAddrCityById(long id) {
        return addrCityMapper.selectByPrimaryKey(id);
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param pageNum
     * @return  PageInfo<AddrCity>
     * @apiNote: 分页查询所有城市记录
     */
    @Override
    public PageInfo<AddrCity> selectAddrCityByPage(int pageNum) {
        //设置查询的是哪一页和每一页有多少个记录
        PageHelper.offsetPage(pageNum,config.getPageSize());
        //无条件查询（条件对象为null，即无条件），查询所有
        List<AddrCity> AddrCitys = addrCityMapper.selectByExample(null);
        //检验查询的结果
        if(AddrCitys !=null){
            return new PageInfo<>(AddrCitys);
        }else{
            return null;
        }
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param AddrCity
     * @return boolean
     * @apiNote: 插入一条城市记录
     */
    @Override
    public boolean insertAddrCity(AddrCity AddrCity) {
        return  addrCityMapper.insertSelective(AddrCity) > 0 ? true : false;
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param AddrCity
     * @return boolean
     * @apiNote: 更新一条城市记录
     */
    @Override
    public boolean updateAddrCity(AddrCity AddrCity) {
        return addrCityMapper.updateByPrimaryKeySelective(AddrCity) >0 ? true : false;
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param id
     * @return boolean
     * @apiNote:  删除一条城市记录
     */
    @Override
    public boolean deleteAddrCity(long id) {
        return addrCityMapper.deleteByPrimaryKey(id) > 0 ? true :false;
    }

    /**
     * Author: chenenru 19:30 2019/5/8
     * @param stateCode
     * @return List<AddrCity>
     * @apiNote: 根据省份的编码查询所有的城市
     */
    @Override
    public List<AddrCity> selectAllAddrCitysByStateCode(Long stateCode) {
        return addrCityMapper.selectByStateCode(stateCode);
    }
}
