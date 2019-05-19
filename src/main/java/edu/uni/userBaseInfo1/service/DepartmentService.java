package edu.uni.userBaseInfo1.service;

import com.github.pagehelper.PageInfo;
import edu.uni.userBaseInfo1.bean.Department;

import java.util.List;

/**
 author:黄永佳
 create:2019.4.19
 modified:null
 功能:创建DepartmentService接口
 **/
public interface DepartmentService {
    /**
     * Author: laizhouhao 19:15 2019/5/16
     * @param university_id
     * @return List<Department></>
     * @apiNote: 根据学校的id查询所有该学校的有效的部门信息
     */
    List<Department> selectAllValidDepartment(Long university_id);

    /**
     * Author: chenenru 13:47 2019/5/18
     * @param depaertmentName
     * @return Department
     * @apiNote: 根据学院名称查询学院
     */
    List<Department> selectDepartmentByName(String depaertmentName);
}
