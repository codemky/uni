package edu.uni.educateAffair.service.impl;

import com.sun.rowset.CachedRowSetImpl;
import edu.uni.bean.Result;
import edu.uni.educateAffair.bean.Canlendar;
import edu.uni.educateAffair.bean.CanlendarExample;
import edu.uni.educateAffair.bean.Semester;
import edu.uni.educateAffair.bean.SemesterExample;
import edu.uni.educateAffair.mapper.CanlendarMapper;
import edu.uni.educateAffair.mapper.SemesterMapper;
import edu.uni.educateAffair.service.CanlendarService;
import edu.uni.utils.DayInterval;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springfox.documentation.swagger2.mappers.SecurityMapper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class CanlendarServiceImpl implements CanlendarService {

    private final int isUse = 0;
    private final int notUse = 1;

    @Autowired
    private CanlendarMapper canlendarMapper;
    @Autowired
    private SemesterMapper semesterMapper;

    @Override
    public boolean insertCanlendar(List<Canlendar> canlendar) {
        Integer id = 0;
        for (Canlendar c : canlendar){
           id = canlendarMapper.insert(c);
        }
        {
            return id > 0 ? true : false;
        }
    }

    @Override
    public String inputWeek(Long id, Date theDate) throws SQLException{

        Date start = null;
        String week = null;

        SemesterExample example = new SemesterExample();
        SemesterExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        criteria.andDeletedEqualTo(isUse);
        List<Semester> semester = semesterMapper.selectByExample(example);
        if(semester.size() > 0){
            start = semester.get(0).getStart();
            DayInterval dayInterval = new DayInterval(start,theDate);
            week = String.valueOf(dayInterval.DayLimit()/7 + 1);
        }else{
            throw new SQLException("学期不存在");
        }
        return week;
    }

    @Override
    public String inputDate(Date theDate) {
        String sweek = null;
        if(theDate != null){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(theDate);
            int iweek = calendar.get(Calendar.DAY_OF_WEEK);
            switch (iweek){
                case 1:
                    sweek="日";
                    break;
                case 2:
                    sweek="一";
                    break;
                case 3:
                    sweek="二";
                    break;
                case 4:
                    sweek="三";
                    break;
                case 5:
                    sweek="四";
                    break;
                case 6:
                    sweek="五";
                    break;
                case 7:
                    sweek="六";
                    break;
            }
        }
        return sweek;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    //c为校历ID + 是否为假期holiday + 描述describe
    public boolean updateCanlendar(List<Canlendar> c) throws SQLException {
/*        for (Canlendar canlendar : c){
        CanlendarExample example = new CanlendarExample();
        CanlendarExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(canlendar.getId());
        Canlendar DeleteCanlendar = new Canlendar();
        ////将delete字段改为1

        DeleteCanlendar.setDeleted(notUse);
        if (canlendarMapper.updateByExampleSelective(DeleteCanlendar,example) <= 0){
            throw new SQLException();
        }
        //新建一条记录
        if(canlendarMapper.insert(canlendar) <= 0){
            throw new SQLException();
        }
        }
        return true;*/
        List<Canlendar> NewCanlendarList = new ArrayList<Canlendar>();
        //创建数据副本并将delete设为notUse
        for(Canlendar canlendar : c){
            List<Canlendar> c1 = new ArrayList<Canlendar>();
            CanlendarExample example = new CanlendarExample();
            CanlendarExample.Criteria criteria = example.createCriteria();
            criteria.andIdEqualTo(canlendar.getId());
            criteria.andDeletedEqualTo(isUse);
            c1 = canlendarMapper.selectByExample(example);
            c1.get(0).setDeleted(notUse);
            System.out.println(c1.get(0));
            NewCanlendarList.add(c1.get(0));
        }
        if(canlendarMapper.insertBatch(NewCanlendarList) <= 0){
            throw new SQLException();
        }
        //修改原数据
        for(Canlendar canlendar : c){
            CanlendarExample example = new CanlendarExample();
            CanlendarExample.Criteria criteria = example.createCriteria();
            criteria.andIdEqualTo(canlendar.getId());
            if(canlendarMapper.updateByExampleSelective(canlendar,example) <= 0){
                throw new SQLException();
            }
        }
        return true;
    }

    @Override
    public List<Canlendar> selectAll() {
        CanlendarExample example = new CanlendarExample();
        CanlendarExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(isUse);
        List<Canlendar> AllCanlendar = canlendarMapper.selectByExample(example);
        return AllCanlendar;
    }

    @Override
    public List<Canlendar> selectBySemesterId(Long sid) {
        CanlendarExample example = new CanlendarExample();
        CanlendarExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(isUse);
        criteria.andSemesterIdEqualTo(sid);
        List<Canlendar> canlendarList = canlendarMapper.selectByExample(example);

        return canlendarList;
    }

    @Override
    public boolean delete(Long id) {
        boolean success;
        CanlendarExample example = new CanlendarExample();
        CanlendarExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);

        Canlendar DeleteCanlendar = new Canlendar();
        DeleteCanlendar.setDeleted(notUse);

        if(canlendarMapper.updateByExampleSelective(DeleteCanlendar,example) > 0){
            success = true;
        }else{
            success = false;
        }
        return success;
    }
}

