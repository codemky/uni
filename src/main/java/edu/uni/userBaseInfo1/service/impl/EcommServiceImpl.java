/**
 * @Author Mr.k
 * @Description // Ecomm实体类的service层接口的实现类
 * @Date 17:16 2019/4/24
 **/

package edu.uni.userBaseInfo1.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.example.config.ExampleConfig;
import edu.uni.userBaseInfo1.bean.Ecomm;
import edu.uni.userBaseInfo1.bean.EcommExample;
import edu.uni.userBaseInfo1.mapper.EcommMapper;
import edu.uni.userBaseInfo1.service.EcommService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//Service类的注解，标志这是一个服务层接口类，这样才能被Spring”“”“”“”"扫描"到
@SuppressWarnings("ALL")
@Service
public class EcommServiceImpl implements EcommService {

    //持久层接口的对象
    @Autowired
    private EcommMapper ecommMapper;  //爆红不用管

    //配置类，规定了上传文件的路径和分页查询每一页的记录数
    @Autowired
    private ExampleConfig config;

    /**
     * Author: mokuanyuan 10:44 2019/4/26
     * @return List<Ecomm>
     * @apiNote: 查询所有通信记录，不分页
     */
    public List<Ecomm> selectAll() {
        return ecommMapper.selectByExample(null);
    }

    /**
     * Author: mokuanyuan 10:45 2019/4/26
     * @param id
     * @return Ecomm
     * @apiNote: 通过id查询一个通信记录
     */
    public Ecomm selectById(long id) {
        return ecommMapper.selectByPrimaryKey(id);
    }

    /**
     * Author: chenenru 0:44 2019/5/5
     * @param  userId
     * @return List<Ecomm>
     * @apiNote: 通过用户id查询一个通信记录
     */
    @Override
    public List<Ecomm> selectByUserId(long userId) {
        return ecommMapper.selectByUserId(userId);
    }

    /**
     * Author: mokuanyuan 10:39 2019/4/26
     * @param pageNum
     * @return pageInfo<Ecomm>
     * @apiNote: 分页查询所有通信记录
     */
    public PageInfo<Ecomm> selectPage(int pageNum) {
        //设置查询的是哪一页和每一页有多少个记录
        PageHelper.offsetPage(pageNum,config.getPageSize());

        //无条件查询（条件对象为null，即无条件），查询所有
        List<Ecomm> ecomms = ecommMapper.selectByExample(null);

        //检验查询的结果
        if(ecomms != null )
            return new PageInfo<>(ecomms);
        else
            return null;

    }

    /**
     * Author: mokuanyuan 10:46 2019/4/26
     * @param ecomm
     * @return boolean
     * @apiNote: 插入一条电子通信记录
     */
    public boolean insert(Ecomm ecomm) {
        return ecommMapper.insertSelective(ecomm) > 0 ? true : false;

    }

    /**
     * Author: mokuanyuan 10:46 2019/4/26
     * @param ecomm
     * @return boolean
     * @apiNote: 以一个新的对象更新一条电子通信记录
     */
    public boolean update(Ecomm ecomm) {
        return ecommMapper.updateByPrimaryKeySelective(ecomm) > 0 ? true : false;
    }

    /**
     * Author: mokuanyuan 10:47 2019/4/26
     * @param id
     * @return boolean
     * @apiNote: 以id删除一条电子通信记录
     */
    public boolean delete(long id) {
        return ecommMapper.deleteByPrimaryKey(id) > 0 ? true : false;
    }

    /**
     * Author: laizhouhao 15:41 2019/5/9
     * @param user_id
     * @return List<Ecomm>
     * @apiNote: 根据用户id返回用户的有效的通信信息
     */
    @Override
    public List<Ecomm> selectValidEcomByUserId(Long user_id) {
        EcommExample ecommExample = new EcommExample();
        ecommExample.createCriteria().andUserIdEqualTo(user_id)
                .andDeletedEqualTo(false);
        List<Ecomm> ecommList = ecommMapper.selectByExample(ecommExample);
        return ecommList;
    }
}
