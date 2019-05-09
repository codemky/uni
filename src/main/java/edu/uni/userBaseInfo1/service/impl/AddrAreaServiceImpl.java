package edu.uni.userBaseInfo1.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.example.config.ExampleConfig;
import edu.uni.userBaseInfo1.bean.AddrArea;
import edu.uni.userBaseInfo1.mapper.AddrAreaMapper;
import edu.uni.userBaseInfo1.service.AddrAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author chenenru
 * @ClassName AddrAreaServiceImpl
 * @Description
 * @Date 2019/5/2 9:45
 * @Version 1.0
 **/
//Service类的注解，标志这是一个服务层接口类，这样才能被Spring”“”“”“”"扫描"到=
@Service
public class AddrAreaServiceImpl implements AddrAreaService {

    //持久层接口的对象
    @Autowired
    private AddrAreaMapper addrAreaMapper;
    //配置类，规定了上传文件的路径和分页查询每一页的记录数
    @Autowired
    private ExampleConfig config;

    /**
     * Author: chenenru 0:10 2019/4/30
     * @param
     * @return  List<AddrArea>
     * @apiNote: 查询所有城区记录，不分页
     */
    @Override
    public List<AddrArea> selectAllAddrAreas() {
        return addrAreaMapper.selectByExample(null);
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param id
     * @return AddrArea
     * @apiNote: 通过id查询一个城区记录
     */
    @Override
    public AddrArea selectAddrAreaById(long id) {
        return addrAreaMapper.selectByPrimaryKey(id);
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param pageNum
     * @return  PageInfo<AddrArea>
     * @apiNote: 分页查询所有城区记录
     */
    @Override
    public PageInfo<AddrArea> selectAddrAreaByPage(int pageNum) {
        //设置查询的是哪一页和每一页有多少个记录
        PageHelper.offsetPage(pageNum,config.getPageSize());
        //无条件查询（条件对象为null，即无条件），查询所有
        List<AddrArea> AddrAreas = addrAreaMapper.selectByExample(null);
        //检验查询的结果
        if(AddrAreas !=null){
            return new PageInfo<>(AddrAreas);
        }else{
            return null;
        }
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param AddrArea
     * @return boolean
     * @apiNote: 插入一条城区记录
     */
    @Override
    public boolean insertAddrArea(AddrArea AddrArea) {
        return  addrAreaMapper.insertSelective(AddrArea) > 0 ? true : false;
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param AddrArea
     * @return boolean
     * @apiNote: 更新一条城区记录
     */
    @Override
    public boolean updateAddrArea(AddrArea AddrArea) {
        return addrAreaMapper.updateByPrimaryKeySelective(AddrArea) >0 ? true : false;
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param id
     * @return boolean
     * @apiNote:  删除一条城区记录
     */
    @Override
    public boolean deleteAddrArea(long id) {
        return addrAreaMapper.deleteByPrimaryKey(id) > 0 ? true :false;
    }

    /**
     * Author: chenenru 19:39 2019/5/8
     * @param cityCode
     * @return  List<AddrArea>
     * @apiNote: 根据城市的编码查询所有的县/区
     */
    @Override
    public List<AddrArea> selectAllAddrAreasByCityCode(Long cityCode) {
        return addrAreaMapper.selectByCityCode(cityCode);
    }

}
