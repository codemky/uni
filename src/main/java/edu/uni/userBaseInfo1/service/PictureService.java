/**
 * @Author laizhouhao
 * @Description //picture实体类的service层接口
 * @Date 15:11 2019/4/29
 **/
package edu.uni.userBaseInfo1.service;
import com.github.pagehelper.PageInfo;
import edu.uni.userBaseInfo1.bean.Picture;
import edu.uni.userBaseInfo1.bean.PictureExample;
import edu.uni.userBaseInfo1.bean.User;

import java.util.HashMap;
import java.util.List;

public interface PictureService {

    /**
     * Author: mokuanyuan 16:55 2019/6/13
     * @param map
     * @param picture
     * @param idList
     * @param loginUser
     * @param modifiedUser
     * @return boolean
     * @apiNote: 用户点击申请时进行的一些系列为了创建申请记录所做的准备
     */
    public boolean readyForApply(HashMap<String, Object> map, Picture picture, long[] idList, edu.uni.auth.bean.User loginUser, User modifiedUser);

    /**
     * Author: mokuanyuan 14:52 2019/6/12
     * @param oldId
     * @param newId
     * @return boolean 操作结果
     * @apiNote: 当审批的最后一步都通过后进行的操作，把相应的信息记录进行更新操作
     */
    public boolean updateForApply(Long oldId,Long newId);

    /**
     * Author: laizhouhao 15:17 2019/4/29
     * @return List<Picture>
     * @apiNote: 查询所有的照片
     */
    List<Picture> selectAll();

    /**
     * Author: laizhouhao 15:39 2019/4/29
     * @param id
     * @return Picture
     * @apiNote: 根据id查询照片
     */
    Picture selectById(Long id);

    /**
     * Author: chenenru 0:57 2019/5/5
     * @param userId
     * @return  Picture
     * @apiNote: 根据用户的id查询图片
     */
    List<Picture> selectByUserId(Long userId);

    /**
     * Author: laizhouhao 15:41 2019/4/29
     * @param pageNum
     * @return List<Picture>
     * @apiNote: 分页查询照片信息
     */
    PageInfo<Picture> selectPage(Integer pageNum);

    /**
     * Author: laizhouhao 15:44 2019/4/29
     * @param picture
     * @return boolean
     * @apiNote: 用于增加Picture表的一个记录
     */
    boolean insert(Picture picture);

    /**
     * Author: laizhouhao 15:46 2019/4/29
     * @param picture
     * @return boolean
     * @apiNote: 用于更新Picture表的一个记录
     */
    boolean update(Picture picture);

    /**
     * Author: laizhouhao 15:47 2019/4/29
     * @param id
     * @return boolean
     * @apiNote: 用于根据id删除Picture表的一个记录
     */
    boolean delete(Long id);

    /**
     * Author: laizhouhao 18:21 2019/5/7
     * @param pictureExample
     * @return List<Picture>
     * @apiNote: 根据自定义条件查询Picture的一个记录
     */
    List<Picture> selectByExample(PictureExample pictureExample);

    /**
     * Author: laizhouhao 16:37 2019/5/8
     * @param user_id
     * @return Picture
     * @apiNote: 根据用户id查询有效的照片信息
     */
    Picture selectPictureByUserId(Long user_id);

    /**
     * Author: mokuanyuan 15:18 2019/6/24
     * @param userId
     * @return List<Picture>
     * @apiNote 根据用户id查询有效的照片信息
     */
    List<Picture> selectValidByUserId(Long userId);

    /**
     * Author: laizhouhao 21:59 2019/6/9
     * @param pictureList
     * @return 用户的照片信息
     * @apiNote: 根据用户的照片实体获取照片的详情
     */
    void getUserPitutre(HashMap<String,Object>map, List<Picture> pictureList);

}
