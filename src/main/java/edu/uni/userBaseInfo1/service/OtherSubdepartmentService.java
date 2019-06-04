package edu.uni.userBaseInfo1.service;

import edu.uni.administrativestructure.bean.Subdepartment;
import edu.uni.administrativestructure.service.SubdepartmentService;

import java.util.List;

/**
 * author：黄育林
 * create: 2019.4.20
 * modified: 2019.4.24
 * 功能：三级部门接口
 */
public interface OtherSubdepartmentService {
    /**
     * Author: laizhouhao 19:21 2019/5/16
     * @param department_id
     * @return List<Department>
     * @apiNote: 根据部门id查询该部门的所有有效科室
     */
    List<Subdepartment> selectValidSubDepartByDepartId(Long department_id);

    /**
     * Author: laizhouhao 15:36 2019/5/18
     * @param subdepart_name
     * @return Long
     * @apiNote: 根据科室名查找有效的部门的id
     */
    Long selectIdBySubdepartName(String subdepart_name);

}
