package edu.uni.educateAffair.service.impl;

import edu.uni.educateAffair.bean.Timetable;
import edu.uni.educateAffair.bean.TimetableExample;
import edu.uni.educateAffair.mapper.TimetableMapper;
import edu.uni.educateAffair.service.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:梁俊杰
 * @Description:
 * @Date:Created in 22:19 2019/5/10
 */
@Service
public class TimetableServiceImpl implements TimetableService {

    private final int isUse = 0;
    private final int notUse = 1;

    @Autowired
    private TimetableMapper timetableMapper;

    @Override
    public List<Timetable> selectAll() {
        TimetableExample example = new TimetableExample();
        TimetableExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(isUse);
        {return timetableMapper.selectByExample(example);}
    }
}
