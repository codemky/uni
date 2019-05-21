package edu.uni.educateAffair.service.impl;

import edu.uni.educateAffair.bean.Canlendar;
import edu.uni.educateAffair.bean.Semester;
import edu.uni.educateAffair.bean.SemesterExample;
import edu.uni.educateAffair.mapper.CanlendarMapper;
import edu.uni.educateAffair.mapper.SemesterMapper;
import edu.uni.educateAffair.service.CanlendarService;
import edu.uni.educateAffair.service.SemesterService;
import edu.uni.utils.DayInterval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Author:梁俊杰
 * @Description:学期表操作
 * @Date:Created in 8:24 2019/4/29
 */
@Service
public class SemesterServiceImpl implements SemesterService {

    private final int isUse = 0;
    private final int notUse = 1;

    @Autowired
    private SemesterMapper semesterMapper;
    @Autowired
    private CanlendarMapper canlendarMapper;

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,rollbackFor = Exception.class)
    @Override
    public boolean insert(Semester semester , List<Canlendar> canlendarList) throws SQLException {
        //新建学期表
        boolean success1 = (semesterMapper.insert(semester) > 0);
        //初始化日历类
        Calendar calendar = Calendar.getInstance();
        Calendar calendar1 = Calendar.getInstance();
        //设置学期开始时间
        calendar.setTime(semester.getStart());
        int iweek = 0;
        if (calendar.get(Calendar.DAY_OF_WEEK) >2 && calendar.get(Calendar.DAY_OF_WEEK) < 7){
            iweek = 1;
        }
        int interval = (int) new DayInterval(semester.getStart(),semester.getEnd()).DayLimit();
        for (int i=0; i<=interval; i++){
            Canlendar canlendar = new Canlendar();
            canlendar.setUniversityId((long) 1);
            canlendar.setSemesterId(semester.getId());
            canlendar.setTheDate(new Date(calendar.get(Calendar.YEAR)-1900, calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE)+i));
            calendar1.setTime(canlendar.getTheDate());
            //设置周次
            if (calendar1.get(Calendar.DAY_OF_WEEK)==2){
                iweek++;
            }
            canlendar.setWeek(String.valueOf(iweek));
            //设置星期
            int iday = calendar1.get(Calendar.DAY_OF_WEEK);
            String sday= "";
            switch (iday){
                case 1:
                    sday="日";
                    break;
                case 2:
                    sday="一";
                    break;
                case 3:
                    sday="二";
                    break;
                case 4:
                    sday="三";
                    break;
                case 5:
                    sday="四";
                    break;
                case 6:
                    sday="五";
                    break;
                case 7:
                    sday="六";
                    break;
            }
            canlendar.setDay(sday);
            if(iday==7||iday==1){
                canlendar.setHoliday(1);
                canlendar.setDescribe("周末");
            }else{
                canlendar.setHoliday(0);
                canlendar.setDescribe("");
            }
            canlendar.setDatetime(Calendar.getInstance().getTime());
            canlendar.setByWho((long)1);
            canlendar.setDeleted(isUse);
            canlendarList.add(canlendar);
        }
        boolean success = (canlendarMapper.insertBatch(canlendarList) > 0 );
        for(Canlendar c : canlendarList){
            System.out.println(c.toString());
        }
        if(success == true && success1 == true){
            return true;
        }else{
            throw new SQLException();
        }
    }

    @Override
    public Semester selectById(Long id) {
        {return semesterMapper.selectByPrimaryKey(id);}
    }

    @Override
    public List<Semester> selectAll() {
        SemesterExample example = new SemesterExample();
        SemesterExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(isUse);
        List<Semester> AllSemester = semesterMapper.selectByExample(example);
        return AllSemester;
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,rollbackFor = Exception.class)
    @Override
    public boolean update(Semester semester) throws SQLException {
        SemesterExample example = new SemesterExample();
        SemesterExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(semester.getId());
        criteria.andDeletedEqualTo(isUse);

        //将delete字段改为1
        Semester DeletedSemester = new Semester();
        DeletedSemester.setDeleted(notUse);
        if(semesterMapper.updateByExampleSelective(DeletedSemester,example) < 0){
            throw new SQLException();
        }

        //新建一条记录
        if(semesterMapper.insert(semester) < 0){
            throw new SQLException();
        }
        return true;
    }

    /**
        *@Author:梁俊杰
        *@Description:根据ID删除学期
        *@Date:Created in {16:30} {2019/5/3}
    */
    @Override
    public boolean delete(Long id) throws SQLException {
        Semester semester = new Semester();
        semester.setDeleted(notUse);
        SemesterExample example = new SemesterExample();
        SemesterExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);


        if(selectById(id).getDeleted() != isUse){
            throw new SQLException("源信息不存在或已被修改");
        }
        if (semesterMapper.updateByExampleSelective(semester,example) <= 0){
            throw new SQLException("删除失败");
        }
        return true;
    }

    //
    // todo 学期id集合 学期区间前端
    @Override
    public List<Long> selectByfromtoEnd(Long from, Long to) {
        //设置起始时间和结束时间区间
        Date startDate = semesterMapper.selectByPrimaryKey(from).getStart();
        Date endDate = semesterMapper.selectByPrimaryKey(to).getEnd();
        //搜索所有学期实体
        SemesterExample example = new SemesterExample();
        SemesterExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(isUse);
        List<Semester> AllSemester = semesterMapper.selectByExample(example);
        //添加符合区间条件的学期ID集合
        List<Long> semesterIdList = new ArrayList<Long>();
        for (Semester s : AllSemester){
            if(startDate.before(s.getStart()) && endDate.after(s.getEnd())){
                semesterIdList.add(s.getId());
            }
        }
        return semesterIdList;
    }

    // todo 学期id加个数 该学期加上前几个学年的学期 成绩模块需要
    @Override
    public List<Semester> selectBySemesterIdAndCount(Long semesterId, Integer count) {
        Semester semester = semesterMapper.selectByPrimaryKey(semesterId);
        String semesterName = semester.getName();
        //截取年份字符型
        String startStr = semesterName.substring(0,semesterName.indexOf("-"));
        String endStr = semesterName.substring(semesterName.indexOf("-")+1,semesterName.indexOf("第"));
        //转换年份为Int
        int startInt = Integer.parseInt(startStr);
        int endInt = Integer.parseInt(endStr);
        //创建用于返回的Semester列表
        List<Semester> ReturnSemester = new ArrayList<Semester>();
        //循环合成前几个学年学期的名称
        for (int i = 0 ; i <= count ; i++){
            //用于模糊匹配的学期名“xxxx-xxxx”
            String sName = startInt + "-" + endInt;
            System.out.println(sName);
            //开始模糊匹配学期名
            SemesterExample example = new SemesterExample();
            SemesterExample.Criteria criteria = example.createCriteria();
            criteria.andNameLike(sName + "%");
            //接收学年返回的学期
            List<Semester> ReceiveSemester = semesterMapper.selectByExample(example);
            //放进用于返回的Semester列表
            for(Semester s : ReceiveSemester){
                ReturnSemester.add(s);
            }
            startInt--;
            endInt--;
        }
        return ReturnSemester;
    }
}
