package edu.uni.educateAffair.service;

import edu.uni.educateAffair.VO.CurriculumWithCondition;
import edu.uni.educateAffair.bean.Curriculum;
import edu.uni.educateAffair.VO.CurriculumVO;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author:梁俊杰
 * @Description:
 * @Date:Created in 17:52 2019/5/4
 */
public interface CurriculumService {
    /**
        *@Author:梁俊杰
        *@Description:新建授课安排表
        *@Date:Created in {17:53} {2019/5/4}
    */
    boolean insertCurriculum(Curriculum curriculum);
    /**
        *@Author:梁俊杰
        *@Description:更改授课安排表
        *@Date:Created in {23:18} {2019/5/4}
    */
    boolean updateCurriculum(Curriculum curriculum);
    /**
        *@Author:梁俊杰
        *@Description:查看全部授课安排表
        *@Date:Created in {23:40} {2019/5/4}
    */
    List<Curriculum> selectAll() throws SQLException;
    /**
        *@Author:梁俊杰
        *@Description:根据ID删除授课安排表
        *@Date:Created in {23:53} {2019/5/4}
    */
    boolean delete(Long id);
    /**
        *@Author:梁俊杰
        *@Description:转换课程表
        *@Date:Created in {22:04} {2019/5/11}
    */
    List<CurriculumVO> Transform(CurriculumWithCondition curriculumWithCondition);
/**
    *@Author:梁俊杰
    *@Description:学期Id集合，教师id集合，课程id集合， 班级id集合
    *@Date:Created in {13:25} {2019/5/17}
*/
    List<Curriculum> selectCurriculumByCondition(List<Long> semesterId, List<Long> employeeId, List<Long> courseId, List<Long> classId);
}
