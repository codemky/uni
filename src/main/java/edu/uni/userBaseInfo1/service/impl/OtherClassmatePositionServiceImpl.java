package edu.uni.userBaseInfo1.service.impl;

import edu.uni.administrativestructure.bean.ClassmatePosition;
import edu.uni.administrativestructure.bean.ClassmatePositionExample;
import edu.uni.administrativestructure.mapper.ClassmatePositionMapper;
import edu.uni.userBaseInfo1.service.OtherClassmatePositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author chenenru
 * @ClassName OtherClassmatePositionServiceImpl
 * @Description
 * @Date 2019/6/3 20:45
 * @Version 1.0
 **/
@Service
public class OtherClassmatePositionServiceImpl implements OtherClassmatePositionService {
    @Autowired
    private ClassmatePositionMapper classmatePositionMapper;
    @Override
    public ClassmatePosition selectclassmatePositionByClassmateIdAndPositionId(Long classMateId,Long positionId) {
        ClassmatePositionExample example = new ClassmatePositionExample();
        ClassmatePositionExample.Criteria criteria = example.createCriteria();
        criteria.andClassmateIdEqualTo(classMateId);
        criteria.andDeletedEqualTo(false);
        if (positionId!=null){
            criteria.andPositionIdEqualTo(positionId);
        }
        List<ClassmatePosition> classmatePositions = classmatePositionMapper.selectByExample(example);
        return classmatePositions.size()<=0?null:classmatePositions.get(0);
    }

    @Override
    public List<ClassmatePosition> selectclassmatePositionByClassmateId(Long classMateId) {
        ClassmatePositionExample example = new ClassmatePositionExample();
        ClassmatePositionExample.Criteria criteria = example.createCriteria();
        criteria.andClassmateIdEqualTo(classMateId);
        criteria.andDeletedEqualTo(false);
        List<ClassmatePosition> classmatePositions = classmatePositionMapper.selectByExample(example);
        return classmatePositions.size()<=0?null:classmatePositions;
    }
}
