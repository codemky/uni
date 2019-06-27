package edu.uni.userBaseInfo1.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.example.config.ExampleConfig;
import edu.uni.userBaseInfo1.bean.Address;
import edu.uni.userBaseInfo1.bean.Employee;
import edu.uni.userBaseInfo1.bean.User;
import edu.uni.userBaseInfo1.bean.UserUploadFile;
import edu.uni.userBaseInfo1.mapper.UserUploadFileMapper;
import edu.uni.userBaseInfo1.service.UserUploadFileService;
import edu.uni.userBaseInfo1.utils.userinfoTransMapBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public boolean readyForApply(HashMap<String, Object> map, UserUploadFile userUploadFile, long[] idList,
                                 edu.uni.auth.bean.User loginUser, User modifiedUser) {
        //通过工具类获取在map包装好的对象属性
        userinfoTransMapBean.transMap2Bean((Map) map.get("applyExcel"),userUploadFile);
        //检验是否把该获取的信息都获取到了
        if(!UserUploadFile.isValidForApply(userUploadFile))
            return false;
        boolean result = false;
        if(userUploadFile.getId() != -1){  //上传文件不存在有旧纪录的情况
//            UserUploadFile oldUserUploadFile = selectUserUploadFileById(userUploadFile.getId());
//            UserUploadFile.copyPropertiesForApply(userUploadFile,oldUserUploadFile);
//            userUploadFile.setByWho(loginUser.getId());
//            idList[0] = oldUserUploadFile.getId();
//            result = insertUserUploadFile(userUploadFile) ;
//            newId = userUploadFile.getId();
            return  false;

        }
        else{
            userUploadFile.setDatetime(new Date());
            userUploadFile.setByWho(loginUser.getId());
            userUploadFile.setDeleted(true);
            result = insertUserUploadFile(userUploadFile) ;
            idList[1] = userUploadFile.getId();
        }
        return result;

    }


    /**
     * Author: mokuanyuan 14:52 2019/6/12
     * @param oldId
     * @param newId
     * @return boolean 操作结果
     * @apiNote: 当审批的最后一步都通过后进行的操作，把相应的信息记录进行更新操作
     */
    public boolean updateForApply(Long oldId,Long newId){
        boolean result = false;
        UserUploadFile newUserUploadFile = selectUserUploadFileById(newId);
        if(oldId != null){
            UserUploadFile oldUserUploadFile = selectUserUploadFileById(oldId);
            oldUserUploadFile.setId(newId);
            newUserUploadFile.setId(oldId);
            if( updateUserUploadFile(oldUserUploadFile) && updateUserUploadFile(newUserUploadFile) )
                result = true;
        }else{
            newUserUploadFile.setDeleted(false);
            if( updateUserUploadFile(newUserUploadFile) )
                result = true;
        }
        return result;
    }

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
        return  userUploadFileMapper.insert(UserUploadFile) > 0 ? true : false;
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
