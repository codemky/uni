package edu.uni.userBaseInfo1.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.example.config.ExampleConfig;
import edu.uni.userBaseInfo1.bean.User;
import edu.uni.userBaseInfo1.mapper.UserMapper;
import edu.uni.userBaseInfo1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author chenenru
 * @ClassName UserServiceImpl
 * @Description
 * @Date 2019/4/29 23:22
 * @Version 1.0
 **/
//Service类的注解，标志这是一个服务层接口类，这样才能被Spring”“”“”“”"扫描"到=
@Service
public class UserServiceImpl implements UserService {
    //持久层接口的对象
    @Autowired
    private UserMapper userMapper;
    //配置类，规定了上传文件的路径和分页查询每一页的记录数
    @Autowired
    private ExampleConfig config;
    /**
     * Author: chenenru 23:25 2019/4/29
     * @param 
     * @return List<User>
     * @apiNote: 查询所有通信记录，不分页
     */
    @Override
    public List<User> selectAllUsers() {
        return userMapper.selectByExample(null);
    }
    /**
     * Author: chenenru 23:26 2019/4/29
     * @param id
     * @return User
     * @apiNote: 通过id查询一个用户记录
     */
    @Override
    public User selectUserById(long id) {
        return userMapper.selectByPrimaryKey(id);
    }
    /**
     * Author: chenenru 23:27 2019/4/29
     * @param pageNum
     * @return PageInfo<User>
     * @apiNote: 分页查询所有用户记录
     */
    @Override
    public PageInfo<User> selectUserByPage(int pageNum) {
        //设置查询的是哪一页和每一页有多少个记录
        PageHelper.offsetPage(pageNum,config.getPageSize());
        //无条件查询（条件对象为null，即无条件），查询所有
        List<User> users = userMapper.selectByExample(null);
        //检验查询的结果
        if(users !=null){
            return new PageInfo<>(users);
        }else{
            return null;
        }
    }
    /**
     * Author: chenenru 23:27 2019/4/29
     * @param user
     * @return boolean
     * @apiNote: 插入一条用户记录
     */
    @Override
    public boolean insertUser(User user) {
        return userMapper.insertSelective(user) > 0 ? true : false;
    }
    /**
     * Author: chenenru 23:28 2019/4/29
     * @param user
     * @return  boolean
     * @apiNote: 更新一条用户记录
     */
    @Override
    public boolean updateUser(User user) {
        return userMapper.updateByPrimaryKeySelective(user) >0 ? true : false;
    }
    /**
     * Author: chenenru 23:29 2019/4/29
     * @param id
     * @return boolean
     * @apiNote: 删除一条用户记录
     */
    @Override
    public boolean deleteUser(long id) {
        return userMapper.deleteByPrimaryKey(id) > 0 ? true :false;
    }
}
