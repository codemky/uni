package edu.uni.userBaseInfo1.service.impl;

import edu.uni.administrativestructure.bean.EmployPosition;
import edu.uni.administrativestructure.bean.EmployPositionExample;
import edu.uni.administrativestructure.mapper.EmployPositionMapper;
import edu.uni.administrativestructure.service.EmployPositionService;
import edu.uni.userBaseInfo1.service.OtherEmployPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public List<EmployPosition> selectByEmployeeId(Long employeeId) {
        EmployPositionExample example = new EmployPositionExample();
        EmployPositionExample.Criteria criteria = example.createCriteria();
        criteria.andEmployIdEqualTo(employeeId);
        criteria.andDeletedEqualTo(false);
        return employPositionMapper.selectByExample(example);

    }
}
