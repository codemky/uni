package edu.uni.userBaseInfo1.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.example.config.ExampleConfig;
import edu.uni.userBaseInfo1.bean.AddrCountry;
import edu.uni.userBaseInfo1.mapper.AddrCountryMapper;
import edu.uni.userBaseInfo1.service.AddrCountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author chenenru
 * @ClassName AddrCountryServiceImpl
 * @Description
 * @Date 2019/5/2 9:46
 * @Version 1.0
 **/
//Service类的注解，标志这是一个服务层接口类，这样才能被Spring”“”“”“”"扫描"到=
@Service
public class AddrCountryServiceImpl implements AddrCountryService {

    //持久层接口的对象
    @Autowired
    private AddrCountryMapper addrCountryMapper;
    //配置类，规定了上传文件的路径和分页查询每一页的记录数
    @Autowired
    private ExampleConfig config;

    /**
     * Author: chenenru 0:10 2019/4/30
     * @param
     * @return  List<AddrCountry>
     * @apiNote: 查询所有国家记录，不分页
     */
    @Override
    public List<AddrCountry> selectAllAddrCountrys() {
        return addrCountryMapper.selectByExample(null);
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param id
     * @return AddrCountry
     * @apiNote: 通过id查询一个国家记录
     */
    @Override
    public AddrCountry selectAddrCountryById(long id) {
        return addrCountryMapper.selectByPrimaryKey(id);
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param pageNum
     * @return  PageInfo<AddrCountry>
     * @apiNote: 分页查询所有国家记录
     */
    @Override
    public PageInfo<AddrCountry> selectAddrCountryByPage(int pageNum) {
        //设置查询的是哪一页和每一页有多少个记录
        PageHelper.offsetPage(pageNum,config.getPageSize());
        //无条件查询（条件对象为null，即无条件），查询所有
        List<AddrCountry> AddrCountrys = addrCountryMapper.selectByExample(null);
        //检验查询的结果
        if(AddrCountrys !=null){
            return new PageInfo<>(AddrCountrys);
        }else{
            return null;
        }
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param AddrCountry
     * @return boolean
     * @apiNote: 插入一条国家记录
     */
    @Override
    public boolean insertAddrCountry(AddrCountry AddrCountry) {
        return  addrCountryMapper.insertSelective(AddrCountry) > 0 ? true : false;
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param AddrCountry
     * @return boolean
     * @apiNote: 更新一条国家记录
     */
    @Override
    public boolean updateAddrCountry(AddrCountry AddrCountry) {
        return addrCountryMapper.updateByPrimaryKeySelective(AddrCountry) >0 ? true : false;
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param id
     * @return boolean
     * @apiNote:  删除一条国家记录
     */
    @Override
    public boolean deleteAddrCountry(long id) {
        return addrCountryMapper.deleteByPrimaryKey(id) > 0 ? true :false;
    }
}
