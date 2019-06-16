package edu.uni.userBaseInfo1.service;

import com.github.pagehelper.PageInfo;
import edu.uni.userBaseInfo1.bean.User;
import edu.uni.userBaseInfo1.bean.UserUploadFile;

import java.util.HashMap;
import java.util.List;

public interface UserUploadFileService {

    /**
     * Author: mokuanyuan 16:55 2019/6/13
     * @param map
     * @param userUploadFile
     * @param oldId
     * @param newId
     * @param loginUser
     * @param modifiedUser
     * @return boolean
     * @apiNote: 用户点击申请时进行的一些系列为了创建申请记录所做的准备
     */
    public boolean readyForApply(HashMap<String, Object> map, UserUploadFile userUploadFile, Long oldId,
                                 Long newId, edu.uni.auth.bean.User loginUser, User modifiedUser);

    /**
     * Author: mokuanyuan 14:52 2019/6/12
     * @param oldId
     * @param newId
     * @return boolean 操作结果
     * @apiNote: 当审批的最后一步都通过后进行的操作，把相应的信息记录进行更新操作
     */
    public boolean updateForApply(Long oldId,Long newId);

    /**
     * Author: chenenru 23:59 2019/4/29
     * @param
     * @return List<UserUploadFile>
     * @apiNote: 查询所有的用户上传文件
     */
    List<UserUploadFile> selectAllUserUploadFiles();
    /**
     * Author: chenenru 0:00 2019/4/30
     * @param id
     * @return UserUploadFile
     * @apiNote: 根据id查询用户上传文件
     */
    UserUploadFile selectUserUploadFileById(long id);
    /**
     * Author: chenenru 0:00 2019/4/30
     * @param pageNum
     * @return PageInfo<UserUploadFile>
     * @apiNote: 分页查询用户上传文件
     */
    PageInfo<UserUploadFile> selectUserUploadFileByPage(int pageNum);
    /**
     * Author: chenenru 0:00 2019/4/30
     * @param UserUploadFile
     * @return boolean
     * @apiNote: 用户上传文件添加UserUploadFile表的一条记录
     */
    boolean insertUserUploadFile(UserUploadFile UserUploadFile);
    /**
     * Author: chenenru 0:00 2019/4/30
     * @param UserUploadFile
     * @return boolean
     * @apiNote:  用户更新一个UserUploadFile表的某个记录（传一个新的对象）
     */
    boolean updateUserUploadFile(UserUploadFile UserUploadFile);
    /**
     * Author: chenenru 0:00 2019/4/30
     * @param id
     * @return boolean
     * @apiNote:  用于删除UserUploadFile表的某个记录
     */
    boolean deleteUserUploadFile(long id);
}
