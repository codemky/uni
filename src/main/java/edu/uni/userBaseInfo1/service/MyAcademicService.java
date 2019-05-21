package edu.uni.userBaseInfo1.service;

import com.github.pagehelper.PageInfo;
import edu.uni.professionalcourses.bean.Academic;

import java.util.List;

/**
 * @Author laizhouhao
 * @Description Academic实体类的Service层接口
 * @Date 14:45 2019/4/30
 **/
public interface MyAcademicService {

        /**
         * Author: laizhouhao 14:47 2019/4/30
         * @return List<Academic>
         * @apiNote: 获取所有的受教育程度信息
         */
        List<Academic> selectAll();

        /**
         * Author: laizhouhao 14:55 2019/4/30
         * @param id
         * @return Academic
         * @apiNote: 根据id获取单条受教育程度的信息
         */
        Academic selectById(long id);

        /**
         * Author: laizhouhao 14:56 2019/4/30
         * @param pageNum
         * @return  List<Academic>
         * @apiNote: 分页查询受教育程度信息
         */
        PageInfo<Academic> selectPage(int pageNum);

        /**
         * Author: laizhouhao 14:57 2019/4/30
         * @param academic
         * @return boolean
         * @apiNote: 增肌Academic表的一个记录
         */
        boolean insert(Academic academic);

        /**
         * Author: laizhouhao 14:58 2019/4/30
         * @param academic
         * @return boolean
         * @apiNote: 更新Academic表的一个记录（传一个新的对象）
         */
        boolean update(Academic academic);

        /**
         * Author: laizhouhao 14:59 2019/4/30
         * @param id
         * @return boolean
         * @apiNote: 删除Academic表的某个记录
         */
        boolean delete(long id);
}
