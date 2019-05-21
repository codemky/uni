package edu.uni.userBaseInfo1.service.impl;

import edu.uni.administrativestructure.bean.Employ;
import edu.uni.administrativestructure.bean.EmployExample;
import edu.uni.administrativestructure.mapper.EmployMapper;
import edu.uni.userBaseInfo1.service.OtherEmployService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * author：黄育林
 * create: 2019.4.24
 * modified:2019.5.10
 * 功能：部门人员实现类
 */
@Service
public class OtherEmployServiceImpl implements OtherEmployService {
    @Autowired
    private EmployMapper employMapper;


    @Override
    public Employ selectEmployByEmployeeId(Long employeeId, Long schoolId) {
        EmployExample employExample = new EmployExample();
        EmployExample.Criteria criteria = employExample.createCriteria();
        criteria.andWorkerIdEqualTo(employeeId);
        criteria.andDeletedEqualTo(false);
        return employMapper.selectByExample(employExample).get(0);
    }

}
