package edu.uni.userBaseInfo1.service;

import edu.uni.administrativestructure.bean.EmployPosition;
import edu.uni.administrativestructure.bean.Position;
import edu.uni.userBaseInfo1.bean.Employee;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * InterfaceName OtherEmployPositionService
 * Description TODO
 * Author LonelySeven
 * Date 2019/6/3 23:22
 * Version 1.0
 **/
public interface OtherEmployPositionService {

    /**
     * Author: mokuanyuan 23:23 2019/6/3
     * @param employee
     * @return List<EmployPosition>
     * @apiNote: 根据职员Id查询所有的职员职位记录
     */
    public List<EmployPosition> selectByEmployeeId(Employee employee);

    /**
     * Author: mokuanyuan 19:25 2019/6/18
     * @param  employee
     * @return List<Position>
     * @apiNote: 根据职员Id查询其所有的岗位
     */
    public List<Position> selectPositionsByEmployeeId(Employee employee);

    /**
     * Author: mokuanyuan 19:34 2019/6/19
     * @param employee
     * @return List<Integer>
     * @apiNote: 根据职员用户id获取该职员所有拥有的权限（0:代表教师权限，1：班主任，2：学院领导(辅导员及以上职位)）
     */
    public List<Integer> selectEmployeeRoleByUserId(Employee employee);

}
