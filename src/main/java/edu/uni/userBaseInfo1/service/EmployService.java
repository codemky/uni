package edu.uni.userBaseInfo1.service;

import edu.uni.userBaseInfo1.bean.Employ;


public interface EmployService {

    /**
     * Author: chenenru 14:00 2019/5/11
     * @param schoolId
     * @param employeeId
     * @return Employ
     * @apiNote: 根据employeeId和学校id查询部门人员
     */
    Employ selectEmployByEmployeeId(Long employeeId, Long schoolId);
}
