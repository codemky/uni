package edu.uni.userBaseInfo1.service;


import edu.uni.administrativestructure.bean.Employ;
import edu.uni.administrativestructure.service.EmployService;

public interface OtherEmployService {

    /**
     * Author: chenenru 14:00 2019/5/11
     * @param schoolId
     * @param employeeId
     * @return Employ
     * @apiNote: 根据employeeId和学校id查询部门人员
     */
    Employ selectEmployByEmployeeId(Long employeeId, Long schoolId);
}
