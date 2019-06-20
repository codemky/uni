package edu.uni.userBaseInfo1.service.impl;

import edu.uni.administrativestructure.bean.Position;
import edu.uni.administrativestructure.bean.PositionExample;
import edu.uni.administrativestructure.mapper.PositionMapper;
import edu.uni.userBaseInfo1.service.OtherPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author chenenru
 * @ClassName OtherPositionServiceImpl
 * @Description
 * @Date 2019/6/19 23:27
 * @Version 1.0
 **/
@Service
public class OtherPositionServiceImpl implements OtherPositionService {
    @Autowired
    private PositionMapper positionMapper;

    @Override
    public Position selectPositionByPositionName(String PositionName) {
        PositionExample positionExample = new PositionExample();
        PositionExample.Criteria criteria = positionExample.createCriteria();
        criteria.andNameEqualTo(PositionName).andDeletedEqualTo(false);
        List<Position> positions = positionMapper.selectByExample(positionExample);
        return positions.size()>0?positions.get(0):null;
    }
}
