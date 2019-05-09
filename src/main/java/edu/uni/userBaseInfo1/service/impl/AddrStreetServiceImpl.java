package edu.uni.userBaseInfo1.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.example.config.ExampleConfig;
import edu.uni.userBaseInfo1.bean.AddrStreet;
import edu.uni.userBaseInfo1.mapper.AddrStreetMapper;
import edu.uni.userBaseInfo1.service.AddrStreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author chenenru
 * @ClassName AddrStreetServiceImpl
 * @Description
 * @Date 2019/5/2 9:47
 * @Version 1.0
 **/
//Service类的注解，标志这是一个服务层接口类，这样才能被Spring”“”“”“”"扫描"到=
@Service
public class AddrStreetServiceImpl implements AddrStreetService {

    //持久层接口的对象
    @Autowired
    private AddrStreetMapper addrStreetMapper;
    //配置类，规定了上传文件的路径和分页查询每一页的记录数
    @Autowired
    private ExampleConfig config;


    /**
     * Author: chenenru 0:10 2019/4/30
     * @param
     * @return  List<AddrStreet>
     * @apiNote: 查询所有街道记录，不分页
     */
    @Override
    public List<AddrStreet> selectAllAddrStreets() {
        return addrStreetMapper.selectByExample(null);
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param id
     * @return AddrStreet
     * @apiNote: 通过id查询一个街道记录
     */
    @Override
    public AddrStreet selectAddrStreetById(long id) {
        return addrStreetMapper.selectByPrimaryKey(id);
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param pageNum
     * @return  PageInfo<AddrStreet>
     * @apiNote: 分页查询所有街道记录
     */
    @Override
    public PageInfo<AddrStreet> selectAddrStreetByPage(int pageNum) {
        //设置查询的是哪一页和每一页有多少个记录
        PageHelper.offsetPage(pageNum,config.getPageSize());
        //无条件查询（条件对象为null，即无条件），查询所有
        List<AddrStreet> AddrStreets = addrStreetMapper.selectByExample(null);
        //检验查询的结果
        if(AddrStreets !=null){
            return new PageInfo<>(AddrStreets);
        }else{
            return null;
        }
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param AddrStreet
     * @return boolean
     * @apiNote: 插入一条街道记录
     */
    @Override
    public boolean insertAddrStreet(AddrStreet AddrStreet) {
        return  addrStreetMapper.insertSelective(AddrStreet) > 0 ? true : false;
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param AddrStreet
     * @return boolean
     * @apiNote: 更新一条街道记录
     */
    @Override
    public boolean updateAddrStreet(AddrStreet AddrStreet) {
        return addrStreetMapper.updateByPrimaryKeySelective(AddrStreet) >0 ? true : false;
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param id
     * @return boolean
     * @apiNote:  删除一条街道记录
     */
    @Override
    public boolean deleteAddrStreet(long id) {
        return addrStreetMapper.deleteByPrimaryKey(id) > 0 ? true :false;
    }

    @Override
    public List<AddrStreet> selectStreetsByAreaCode(Long areaCode) {
        return addrStreetMapper.selectByAreaCode(areaCode);
    }
}
