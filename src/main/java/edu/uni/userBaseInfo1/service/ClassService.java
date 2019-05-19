package edu.uni.userBaseInfo1.service;

import edu.uni.userBaseInfo1.bean.Class;

import java.util.List;

public interface ClassService {

    /**
     * 查询一级部门二级部门关系详情
     * @param id
     * @return
     */
    Class select(long id);

    /**
     * Author: chenenru 13:14 2019/5/11
     * @param classId
     * @return Class
     * @apiNote: 根据用户的class_id查询对应的班级
     */
    Class selectClassByClassId(Long classId);

    /**
     * Author: chenenru 11:38 2019/5/16
     * @param employeeId
     * @return  List<Class>
     * @apiNote: 根据employeeId查询某教师教的所有班级
     */
    List<Class> selectClassesByEmployeeId(Long employeeId, Integer year, String className);

    /**
     * Author: chenenru 18:00 2019/5/17
     * @param depaermentId
     * @return  List<Class>
     * @apiNote: 某学院的所有班级
     */
    List<Class> selectAllClassByDepartmentId(Long depaermentId);

    /**
     * Author: chenenru 15:29 2019/5/18
     * @param Name
     * @return Class
     * @apiNote: 根据班级的名称查询某班级
     */
    List<Class> selectClassByName(String Name);
    /**
     * Author: chenenru 15:55 2019/5/18
     * @param year
     * @return  List<Class>
     * @apiNote: 根据年级查询班级
     */
    List<Class> selectClassByyear(Integer year);


}
