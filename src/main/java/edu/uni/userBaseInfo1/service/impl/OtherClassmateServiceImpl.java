package edu.uni.userBaseInfo1.service.impl;

import edu.uni.administrativestructure.bean.Classmate;
import edu.uni.administrativestructure.bean.ClassmateExample;
import edu.uni.administrativestructure.mapper.ClassmateMapper;
import edu.uni.userBaseInfo1.service.OtherClassmateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author：黄育林
 * create: 2019.4.25
 * modified: 2019.5.10
 * 功能：班级人员实现类
 */
@Service
public class OtherClassmateServiceImpl implements OtherClassmateService {
    @Autowired
    private ClassmateMapper classmateMapper;


    @Override
    public List<Classmate> selectByClassId(Long classId) {
        // 创建查询条件
        ClassmateExample example = new ClassmateExample();
        ClassmateExample.Criteria criteria = example.createCriteria();
        criteria.andClassIdEqualTo(classId);
        criteria.andDeletedEqualTo(false);
        return classmateMapper.selectByExample(example);
    }
}
