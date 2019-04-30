package edu.uni.userBaseInfo1.service;

import com.github.pagehelper.PageInfo;
import edu.uni.userBaseInfo1.bean.AcademicDegree;
import java.util.List;

/**
 * @Author laizhouhao
 * @Description AcademicDegree实体类的Service层接口
 * @Date 14:45 2019/4/30
 **/
public interface AcademicDegreeService {

    /**
     * Author: laizhouhao 14:47 2019/4/30
     * @return List<AcademicDegree>
     * @apiNote: 获取所有的学位信息信息
     */
    List<AcademicDegree> selectAll();

    /**
     * Author: laizhouhao 14:55 2019/4/30
     * @param id
     * @return AcademicDegree
     * @apiNote: 根据id获取单条学位信息的信息
     */
    AcademicDegree selectById(long id);

    /**
     * Author: laizhouhao 14:56 2019/4/30
     * @param pageNum
     * @return  List<AcademicDegree>
     * @apiNote: 分页查询学位信息信息
     */
    PageInfo<AcademicDegree> selectPage(int pageNum);

    /**
     * Author: laizhouhao 14:57 2019/4/30
     * @param academicDegree
     * @return boolean
     * @apiNote: 增加AcademicDegree表的一个记录
     */
    boolean insert(AcademicDegree academicDegree);

    /**
     * Author: laizhouhao 14:58 2019/4/30
     * @param academicDegree
     * @return boolean
     * @apiNote: 更新AcademicDegree表的一个记录（传一个新的对象）
     */
    boolean update(AcademicDegree academicDegree);

    /**
     * Author: laizhouhao 14:59 2019/4/30
     * @param id
     * @return boolean
     * @apiNote: 删除AcademicDegree表的某个记录
     */
    boolean delete(long id);
}
