package edu.uni.userBaseInfo1.service;

import edu.uni.administrativestructure.bean.EmployPosition;

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
     * @param employeeId
     * @return List<EmployPosition>
     * @apiNote: 根据职员Id查询所有的职员职位记录
     */
    public List<EmployPosition> selectByEmployeeId(Long employeeId);
}
