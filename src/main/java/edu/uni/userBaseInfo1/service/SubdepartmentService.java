package edu.uni.userBaseInfo1.service;

import com.github.pagehelper.PageInfo;
import edu.uni.userBaseInfo1.bean.Department;
import edu.uni.userBaseInfo1.bean.Subdepartment;

import java.util.List;

/**
 * author：黄育林
 * create: 2019.4.20
 * modified: 2019.4.24
 * 功能：三级部门接口
 */
public interface SubdepartmentService {
    /**
     * Author: laizhouhao 19:21 2019/5/16
     * @param department_id
     * @return List<Department>
     * @apiNote: 根据部门id查询该部门的所有有效科室
     */
    List<Subdepartment> selectValidSubDepartByDepartId(Long department_id);
}
