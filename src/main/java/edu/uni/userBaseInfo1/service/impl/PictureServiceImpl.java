package edu.uni.userBaseInfo1.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.example.config.ExampleConfig;
import edu.uni.userBaseInfo1.bean.Picture;
import edu.uni.userBaseInfo1.mapper.PictureMapper;
import edu.uni.userBaseInfo1.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @Author laizhouhao
 * @Description picture实体类的service层接口的实现类
 * @Date 15:49 2019/4/29
 **/
import java.util.List;
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
}
