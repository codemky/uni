package edu.uni.userBaseInfo1.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.example.config.ExampleConfig;
import edu.uni.userBaseInfo1.bean.UserUploadFile;
import edu.uni.userBaseInfo1.mapper.UserUploadFileMapper;
import edu.uni.userBaseInfo1.service.UserUploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author chenenru
 * @ClassName UserUploadFileServiceImpl
 * @Description
 * @Date 2019/4/30 15:14
 * @Version 1.0
 **/
//Service类的注解，标志这是一个服务层接口类，这样才能被Spring”“”“”“”"扫描"到=
@SuppressWarnings("ALL")
@Service
public class UserUploadFileServiceImpl implements UserUploadFileService {
    //持久层接口的对象
    @Autowired
    private UserUploadFileMapper userUploadFileMapper;
    //配置类，规定了上传文件的路径和分页查询每一页的记录数
    @Autowired
    private ExampleConfig config;

    /**
     * Author: chenenru 0:10 2019/4/30
     * @param
     * @return  List<UserUploadFile>
     * @apiNote: 查询所有用户上传文件记录，不分页
     */
    @Override
    public List<UserUploadFile> selectAllUserUploadFiles() {
        return userUploadFileMapper.selectByExample(null);
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param id
     * @return UserUploadFile
     * @apiNote: 通过id查询一个用户上传文件记录
     */
    @Override
    public UserUploadFile selectUserUploadFileById(long id) {
        return userUploadFileMapper.selectByPrimaryKey(id);
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param pageNum
     * @return  PageInfo<UserUploadFile>
     * @apiNote: 分页查询所有用户上传文件记录
     */
    @Override
    public PageInfo<UserUploadFile> selectUserUploadFileByPage(int pageNum) {
        //设置查询的是哪一页和每一页有多少个记录
        PageHelper.offsetPage(pageNum,config.getPageSize());
        //无条件查询（条件对象为null，即无条件），查询所有
        List<UserUploadFile> UserUploadFiles = userUploadFileMapper.selectByExample(null);
        //检验查询的结果
        if(UserUploadFiles !=null){
            return new PageInfo<>(UserUploadFiles);
        }else{
            return null;
        }
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param UserUploadFile
     * @return boolean
     * @apiNote: 插入一条用户上传文件记录
     */
    @Override
    public boolean insertUserUploadFile(UserUploadFile UserUploadFile) {
        return  userUploadFileMapper.insertSelective(UserUploadFile) > 0 ? true : false;
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param UserUploadFile
     * @return boolean
     * @apiNote: 更新一条用户上传文件记录
     */
    @Override
    public boolean updateUserUploadFile(UserUploadFile UserUploadFile) {
        return userUploadFileMapper.updateByPrimaryKeySelective(UserUploadFile) >0 ? true : false;
    }
    /**
     * Author: chenenru 0:10 2019/4/30
     * @param id
     * @return boolean
     * @apiNote:  删除一条用户上传文件记录
     */
    @Override
    public boolean deleteUserUploadFile(long id) {
        return userUploadFileMapper.deleteByPrimaryKey(id) > 0 ? true :false;
    }
}
