package edu.uni.userBaseInfo1.service.impl;

import edu.uni.administrativestructure.bean.Employ;
import edu.uni.administrativestructure.bean.EmployPosition;
import edu.uni.administrativestructure.bean.EmployPositionExample;
import edu.uni.administrativestructure.bean.Position;
import edu.uni.administrativestructure.mapper.EmployPositionMapper;
import edu.uni.administrativestructure.mapper.PositionMapper;
import edu.uni.administrativestructure.service.EmployPositionService;
import edu.uni.administrativestructure.service.EmployService;
import edu.uni.userBaseInfo1.bean.Employee;
import edu.uni.userBaseInfo1.service.OtherEmployPositionService;
import edu.uni.userBaseInfo1.service.OtherEmployService;
import edu.uni.userBaseInfo1.service.OtherRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ClassName OtherEmployPositionServiceImpl
 * Description TODO
 * Author LonelySeven
 * Date 2019/6/3 23:25
 * Version 1.0
 **/
@Service
public class OtherEmployPositionServiceImpl implements OtherEmployPositionService {

    @Autowired
    private EmployPositionMapper employPositionMapper;
    @Autowired
    private PositionMapper positionMapper;
    @Autowired
    private EmployService employService;
    @Autowired
    private OtherEmployService otherEmployService;
    @Autowired
    private OtherRoleService otherRoleService;

    @Override
    public List<EmployPosition> selectByEmployeeId(Employee employee) {
        Employ employ = otherEmployService.selectEmployByEmployeeId(employee.getId(), employee.getUniversityId());
        if(employ == null)
            return null;
        EmployPositionExample example = new EmployPositionExample();
        EmployPositionExample.Criteria criteria = example.createCriteria();
        criteria.andEmployIdEqualTo(employ.getId());
        criteria.andDeletedEqualTo(false);
        return employPositionMapper.selectByExample(example);

    }

    @Override
    public List<Position> selectPositionsByEmployeeId(Employee employee) {
        List<EmployPosition> employPositions = selectByEmployeeId(employee);
        if( employPositions.size() > 0 ){
            List<Position> positionList = new ArrayList<>();
            for( EmployPosition employPosition : employPositions )
                if( positionMapper.selectByPrimaryKey(employPosition.getPositionId()) != null )
                    positionList.add(positionMapper.selectByPrimaryKey(employPosition.getPositionId()));

            return positionList;
        }
        else
            return null;
    }

    @Override
    public boolean whetherPositionByEmployeeId(Employee employee, String positionName) {
        List<EmployPosition> employPositions = selectByEmployeeId(employee);
        if( employPositions.size() > 0 ){
            for( EmployPosition employPosition : employPositions )
                if( positionMapper.selectByPrimaryKey(employPosition.getPositionId()).getName().equals(positionName) )
                    return true;
            return false;
        }
        else
            return false;

    }

    @Override
    public List<Integer> selectEmployeeRoleByUserId(Employee employee) {
        if(employee == null)
            return null;

        ArrayList<Integer> roleList = new ArrayList<>();
        List<Position> positions = selectPositionsByEmployeeId(employee);
        for( Position position : positions){
            if( position.getName().equals("授课老师") )
                if( otherRoleService.isPlayOneRole(employee.getUserId(),"授课老师") && !roleList.contains(0) )
                    roleList.add(0);
            if( position.getName().equals("班主任") )
                if( otherRoleService.isPlayOneRole(employee.getUserId(),"班主任") && !roleList.contains(1) )
                    roleList.add(1);
            if( position.getName().contains("辅导员") || position.getName().contains("书记") || position.getName().contains("院长")  )
                if( otherRoleService.isPlayDepartmentLeader(employee.getUserId()) && !roleList.contains(2) )
                    roleList.add(2);
            if( position.getName().contains("人事处") )
                if( otherRoleService.isPlaySchoolLeader(employee.getUserId()) && !roleList.contains(3) )
                    roleList.add(3);

            if(roleList.size() == 4)
                break;
        }

        return roleList;
    }
}
