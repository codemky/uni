package edu.uni.userBaseInfo1.service;

import com.github.pagehelper.PageInfo;
import edu.uni.userBaseInfo1.bean.UserUploadFile;

import java.util.List;

public interface UserUploadFileService {
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
