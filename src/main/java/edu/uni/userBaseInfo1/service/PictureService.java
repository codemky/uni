/**
 * @Author laizhouhao
 * @Description //picture实体类的service层接口
 * @Date 15:11 2019/4/29
 **/
package edu.uni.userBaseInfo1.service;
import com.github.pagehelper.PageInfo;
import edu.uni.userBaseInfo1.bean.Picture;
import edu.uni.userBaseInfo1.bean.PictureExample;

import java.util.List;

public interface PictureService {
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
}
