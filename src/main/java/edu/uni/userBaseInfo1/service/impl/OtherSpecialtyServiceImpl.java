package edu.uni.userBaseInfo1.service.impl;

import edu.uni.professionalcourses.bean.Specialty;
import edu.uni.professionalcourses.bean.SpecialtyExample;
import edu.uni.professionalcourses.mapper.SpecialtyMapper;
import edu.uni.userBaseInfo1.service.OtherSpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author chenenru
 * @ClassName OtherSpecialtyServiceImpl
 * @Description
 * @Date 2019/5/19 10:01
 * @Version 1.0
 **/
@Service
public class OtherSpecialtyServiceImpl implements OtherSpecialtyService {
    @Autowired
    private SpecialtyMapper specialtyMapper;
    @Override
    public List<Specialty> seclectByDIdAndSName(Long uId, Long DId, String SName) {
        SpecialtyExample example = new SpecialtyExample();
        SpecialtyExample.Criteria criteria = example.createCriteria();
        criteria.andDepartmentIdEqualTo(DId);
        criteria.andNameEqualTo(SName);
        criteria.andUniversityIdEqualTo(uId);
        criteria.andDeletedEqualTo(false);
        return specialtyMapper.selectByExample(example);
    }
}
