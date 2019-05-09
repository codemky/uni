package edu.uni.userBaseInfo1.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.example.config.ExampleConfig;
import edu.uni.userBaseInfo1.bean.Address;
import edu.uni.userBaseInfo1.bean.AddressExample;
import edu.uni.userBaseInfo1.mapper.AddressMapper;
import edu.uni.userBaseInfo1.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @Author laizhouhao
 * @Description Address实体类的service层接口的实现类
 * @Date 15:49 2019/4/29
 **/
import java.util.List;
//Service类的注解，标志这是一个服务层接口类，这样才能被Spring”“”“”“”"扫描"到
@SuppressWarnings("ALL")
@Service
public class AddressServiceImpl implements AddressService {
    //持久层接口的对象
    @Autowired
    private AddressMapper addressMapper;//爆红的话编译器的原因，不影响运行

    //配置类，规定了上传文件的路径和分页查询每一页的记录数
    @Autowired
    private ExampleConfig config;

    /**
     * Author: laizhouhao 15:17 2019/4/29
     * @return List<Address>
     * @apiNote: 查询所有的地址
     */
    @Override
    public List<Address> selectAll() {
        return addressMapper.selectByExample(null);
    }

    /**
     * Author: laizhouhao 15:39 2019/4/29
     * @param id
     * @return Address
     * @apiNote: 根据id查询出一条地址信息
     */
    @Override
    public Address selectById(Long id) {
        return addressMapper.selectByPrimaryKey(id);
    }

    /**
     * Author: chenenru 1:24 2019/5/5
     * @param userId
     * @return Address
     * @apiNote: 根据用户的id查询出地址信息
     */
    @Override
    public List<Address> selectByUserId(Long userId) {
        return addressMapper.selectByUserId(userId);
    }

    /**
     * Author: laizhouhao 15:41 2019/4/29
     * @param pageNum
     * @return List<Address>
     * @apiNote: 分页查询出所有地址信息
     */
    @Override
    public PageInfo<Address> selectPage(Integer pageNum) {
        //设置查询的是哪一页和每页的记录条数
        PageHelper.offsetPage(pageNum, config.getPageSize());

        //无条件查询,查询所有
        List<Address>addresss =addressMapper.selectByExample(null);

        if (addresss!=null){
            return new PageInfo<>(addresss);
        }
        return null;
    }

    /**
     * Author: laizhouhao 15:44 2019/4/29
     * @param address
     * @return boolean
     * @apiNote: 增加Address表的一个记录
     */
    @Override
    public boolean insert(Address address) {
        return addressMapper.insert(address)>0 ? true : false;
    }

    /**
     * Author: laizhouhao 15:46 2019/4/29
     * @param address
     * @return boolean
     * @apiNote: 用一个新的对象更新Address表的一个记录
     */
    @Override
    public boolean update(Address address) {
        return addressMapper.updateByPrimaryKey(address) > 0 ? true : false;
    }


    /**
     * Author: laizhouhao 15:47 2019/4/29
     * @param id
     * @return boolean
     * @apiNote: 根据id删除Address表的一个记录
     */
    @Override
    public boolean delete(Long id) {
        return addressMapper.deleteByPrimaryKey(id) > 0 ? true : false;
    }

    /**
     * Author: laizhouhao 19:00 2019/5/7
     * @param addressExample
     * @return List<Address>
     * @apiNote: 根据自定义条件查询用户的地址信息
     */
    @Override
    public List<Address> selectByExample(AddressExample addressExample) {
        return addressMapper.selectByExample(addressExample);
    }
}
