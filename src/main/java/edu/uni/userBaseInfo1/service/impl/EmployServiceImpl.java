package edu.uni.userBaseInfo1.service.impl;

import edu.uni.userBaseInfo1.bean.Employ;
import edu.uni.userBaseInfo1.bean.EmployExample;
import edu.uni.userBaseInfo1.mapper.EmployMapper;
import edu.uni.userBaseInfo1.service.EmployService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * author：黄育林
 * create: 2019.4.24
 * modified:2019.5.10
 * 功能：部门人员实现类
 */
@Service
public class EmployServiceImpl implements EmployService {
    @Autowired
    private EmployMapper employMapper;


    @Override
    public Employ selectEmployByEmployeeId(Long employeeId,Long schoolId) {
        EmployExample employExample = new EmployExample();
        EmployExample.Criteria criteria = employExample.createCriteria();
        criteria.andWorkerIdEqualTo(employeeId);
        criteria.andDeletedEqualTo(false);
        return employMapper.selectByExample(employExample).get(0);
    }

}
