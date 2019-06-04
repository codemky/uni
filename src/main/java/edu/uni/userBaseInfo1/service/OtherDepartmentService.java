package edu.uni.userBaseInfo1.service;

import edu.uni.administrativestructure.bean.Department;
import edu.uni.administrativestructure.service.DepartmentService;

import java.util.List;

/**
 author:黄永佳
 create:2019.4.19
 modified:null
 功能:创建DepartmentService接口
 **/
public interface OtherDepartmentService  {
    /**
     * Author: laizhouhao 19:15 2019/5/16
     * @param university_id
     * @return List<Department></>
     * @apiNote: 根据学校的id查询所有该学校的有效的部门信息
     */
    List<Department> selectAllValidDepartment(Long university_id);

    /**
     * Author: laizhouhao 15:28 2019/5/18
     * @param depart_name
     * @return Long
     * @apiNote: 根据部门名查找有效的部门id
     */
    Long selectDepartIdByName(String depart_name);

    /**
     * Author: chenenru 13:47 2019/5/18
     * @param depaertmentName
     * @return Department
     * @apiNote: 根据学院名称查询学院
     */
    List<Department> selectDepartmentByName(String depaertmentName);

    /**
     * Author: laizhouhao 20:39 2019/5/19
     * @param id
     * @return List<Department></>
     * @apiNote: 根据id获取有效的学院信息
     */
    List<Department> selectValidById(Long id);
}
