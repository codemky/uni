package edu.uni.userBaseInfo1.service;

import edu.uni.userBaseInfo1.bean.Class;

import java.util.List;

public interface ClassService {

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


}
