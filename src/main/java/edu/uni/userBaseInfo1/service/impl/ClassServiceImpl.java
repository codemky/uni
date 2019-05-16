package edu.uni.userBaseInfo1.service.impl;

import edu.uni.userBaseInfo1.bean.Class;
import edu.uni.userBaseInfo1.bean.ClassExample;
import edu.uni.userBaseInfo1.mapper.ClassMapper;
import edu.uni.userBaseInfo1.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author : 黄永佳
 * create : 2019/4/26 0026 12:48
 * modified :
 * 功能 :实现classService接口
 **/
@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassMapper classMapper;

    @Override
    public Class selectClassByClassId(Long classId) {
        ClassExample classExample = new ClassExample();
        ClassExample.Criteria criteria = classExample.createCriteria();
        criteria.andIdEqualTo(classId);
        return classMapper.selectByExample(classExample).get(0);
    }

    @Override
    public List<Class> selectClassesByEmployeeId(Long employeeId, Integer year, String className) {
        ClassExample classExample = new ClassExample();
        ClassExample.Criteria criteria = classExample.createCriteria();
        if(year!=null){
            criteria.andCyearEqualTo(year);
        }
        if (className!=null){
            criteria.andNameEqualTo(className);
        }
        criteria.andHeadteacherEqualTo(employeeId);
        return  classMapper.selectByExample(classExample);
    }
}
