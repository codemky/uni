package edu.uni.userBaseInfo1.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.example.config.ExampleConfig;
import edu.uni.userBaseInfo1.bean.Address;
import edu.uni.userBaseInfo1.bean.Picture;
import edu.uni.userBaseInfo1.bean.PictureExample;
import edu.uni.userBaseInfo1.bean.User;
import edu.uni.userBaseInfo1.mapper.PictureMapper;
import edu.uni.userBaseInfo1.service.PictureService;
import edu.uni.userBaseInfo1.utils.userinfoTransMapBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @Author laizhouhao
 * @Description picture实体类的service层接口的实现类
 * @Date 15:49 2019/4/29
 **/
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Service类的注解，标志这是一个服务层接口类，这样才能被Spring”“”“”“”"扫描"到
@SuppressWarnings("ALL")
@Service
public class PictureServiceImpl implements PictureService {
    //持久层接口的对象
    @Autowired
    private PictureMapper pictureMapper;//爆红的话编译器的原因，不影响运行

    //配置类，规定了上传文件的路径和分页查询每一页的记录数
    @Autowired
    private ExampleConfig config;


    /**
     * Author: mokuanyuan 16:55 2019/6/13
     * @param map
     * @param picture
     * @param oldId
     * @param newId
     * @param loginUser
     * @param modifiedUser
     * @return boolean
     * @apiNote: 用户点击申请时进行的一些系列为了创建申请记录所做的准备
     */
    @Override
    public boolean readyForApply(HashMap<String, Object> map, Picture picture, Long oldId,
                                 Long newId, edu.uni.auth.bean.User loginUser, User modifiedUser) {
        //通过工具类获取在map包装好的对象属性
        userinfoTransMapBean.transMap2Bean((Map) map.get("applyPicture"),picture);
        //检验是否把该获取的信息都获取到了
        if(!Picture.isValidForApply(picture))
            return false;
        boolean result = false;
        if(picture.getId() != -1){  //不是-1代表原本有旧数据
            Picture oldPicture = selectById(picture.getId());
            Picture.copyPropertiesForApply(picture,oldPicture);
            picture.setByWho(loginUser.getId());
            oldId = oldPicture.getId();
            result = insert(picture) ;
            newId = picture.getId();

        }
        else{
            picture.setUserId(modifiedUser.getId());
            picture.setDatetime(new Date());
            picture.setByWho(loginUser.getId());
            picture.setDeleted(true);
            result = insert(picture) ;
            newId = picture.getId();
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
        Picture newPicture = selectById(newId);
        if(oldId != null){
            Picture oldPicture = selectById(oldId);
            oldPicture.setId(newId);
            newPicture.setId(oldId);
            if( update(oldPicture) && update(newPicture) )
                result = true;
        }else{
            newPicture.setDeleted(false);
            if( update(newPicture) )
                result = true;
        }
        return result;
    }

    /**
     * Author: laizhouhao 15:17 2019/4/29
     * @return List<Picture>
     * @apiNote: 查询所有的照片
     */
    @Override
    public List<Picture> selectAll() {
        return pictureMapper.selectByExample(null);
    }

    /**
     * Author: laizhouhao 15:39 2019/4/29
     * @param id
     * @return Picture
     * @apiNote: 根据id查询出一条照片信息
     */
    @Override
    public Picture selectById(Long id) {
        return pictureMapper.selectByPrimaryKey(id);
    }

    /**
     * Author: chenenru 0:58 2019/5/5
     * @param userId
     * @return  Picture
     * @apiNote: 根据用户的id查询出一条照片信息
     */
    @Override
    public List<Picture> selectByUserId(Long userId) {
        return pictureMapper.selectByUserId(userId);
    }

    /**
     * Author: laizhouhao 15:41 2019/4/29
     * @param pageNum
     * @return List<Picture>
     * @apiNote: 分页查询出所有照片信息
     */
    @Override
    public PageInfo<Picture> selectPage(Integer pageNum) {
        //设置查询的是哪一页和每页的记录条数
        PageHelper.offsetPage(pageNum, config.getPageSize());

        //无条件查询,查询所有
        List<Picture> pictures = pictureMapper.selectByExample(null);

        if (pictures!=null){
            return new PageInfo<>(pictures);
        }
        return null;
    }

    /**
     * Author: laizhouhao 15:44 2019/4/29
     * @param picture
     * @return boolean
     * @apiNote: 增加Picture表的一个记录
     */
    @Override
    public boolean insert(Picture picture) {
        return pictureMapper.insert(picture)>0 ? true : false;
    }

    /**
     * Author: laizhouhao 15:46 2019/4/29
     * @param picture
     * @return boolean
     * @apiNote: 用一个新的对象更新Picture表的一个记录
     */
    @Override
    public boolean update(Picture picture) {
        return pictureMapper.updateByPrimaryKey(picture) > 0 ? true : false;
    }


    /**
     * Author: laizhouhao 15:47 2019/4/29
     * @param id
     * @return boolean
     * @apiNote: 根据id删除Picture表的一个记录
     */
    @Override
    public boolean delete(Long id) {
        return pictureMapper.deleteByPrimaryKey(id) > 0 ? true : false;
    }


    /**
     * Author: laizhouhao 19:00 2019/5/7
     * @param pictureExample
     * @return List<Picture>
     * @apiNote: 根据自定义条件查询用户的照片信息
     */
    @Override
    public List<Picture> selectByExample(PictureExample pictureExample) {
        return pictureMapper.selectByExample(pictureExample);
    }

    /**
     * Author: laizhouhao 16:37 2019/5/8
     * @param user_id
     * @return List<Picture>
     * @apiNote: 根据用户id查询有效的照片信息
     */
    @Override
    public Picture selectPictureByUserId(Long user_id) {
        //构造查询照片的条件
        PictureExample pictureExample = new PictureExample();
        pictureExample.createCriteria().andUserIdEqualTo(user_id)
                .andDeletedEqualTo(false);
        //根据照片查询
        List<Picture> pictures = pictureMapper.selectByExample(pictureExample);
        return pictures.size()>=1?pictures.get(0):null;
    }

    /**
     * Author: laizhouhao 21:59 2019/6/9
     * @param pictureList
     * @return 用户的照片信息
     * @apiNote: 根据用户的照片实体获取照片的详情
     */
    @Override
    public void getUserPitutre(HashMap<String, Object> map, List<Picture> pictureList) {
        //获取用户的各种照片信息，并将放入map中
        for (int i=0; i<pictureList.size(); i++){
            //判断该联系方式是否有效，有效则加入
            if(pictureList.get(i).getDeleted() == false){
                boolean pictureType = pictureList.get(i).getFlag();
                if(pictureType == false){
                    map.put("PaperPicture", pictureList.get(i).getPictureName());
                }else{
                    map.put("LifePicture", pictureList.get(i).getPictureName());
                }
            }
        }
    }
}
