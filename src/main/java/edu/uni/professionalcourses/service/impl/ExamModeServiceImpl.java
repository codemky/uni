/*
author:  邓凯丰
create:  2019.5.10
modified:  2019.5.11
*/
package edu.uni.professionalcourses.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.professionalcourses.bean.ExamMode;
import edu.uni.professionalcourses.bean.ExamModeExample;
import edu.uni.professionalcourses.config.ProfessionalCoursesConfig;
import edu.uni.professionalcourses.mapper.ExamModeMapper;
import edu.uni.professionalcourses.service.ExamModeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ExamModeServiceImpl implements ExamModeService {
    @Autowired
    private ExamModeMapper examModeMapper;
    @Autowired
    private ProfessionalCoursesConfig globalConfig;


    @Override
    public boolean insert(ExamMode examMode) {
        return examModeMapper.insert(examMode) > 0 ? true : false;
    }

    @Override
    public boolean delete(long id) {
        ExamMode examMode=examModeMapper.selectByPrimaryKey(id);
        examMode.setDeleted(true);

        return examModeMapper.updateByPrimaryKeySelective(examMode) > 0 ? true : false;
    }

    @Override
    public boolean update(ExamMode examMode) {
        ExamMode examMode1=new ExamMode();
        examMode1=examModeMapper.selectByPrimaryKey(examMode.getId());
        examMode1.setDeleted(true);
        examModeMapper.insert(examMode);

        return examModeMapper.updateByPrimaryKeySelective(examMode) > 0 ? true : false;
    }

    @Override
    public ExamMode select(long id) {
        ExamMode examMode=examModeMapper.selectByPrimaryKey(id);
        if (examMode.getDeleted()==false){
            return examMode;
        }else {
            return null;
        }
    }

    @Override
    public PageInfo<ExamMode> selectPage(int pageNum) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());
        ExamModeExample example=new ExamModeExample();
        ExamModeExample.Criteria criteria=example.createCriteria();
        criteria.andDeletedEqualTo(false);

        List<ExamMode> examModes =examModeMapper.selectByExample(example);
        if(examModes != null)
            return new PageInfo<>(examModes);
        else
            return null;
    }

    @Override
    public PageInfo<ExamMode> selectPageByUniversity(int pageNum, long universityId) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());
        // 创建查询条件
        ExamModeExample example = new ExamModeExample();
        ExamModeExample.Criteria criteria = example.createCriteria();
        criteria.andUniversityIdEqualTo(universityId).andDeletedEqualTo(false);
        // 根据条件查询
        List<ExamMode> examModes = examModeMapper.selectByExample(example);
        if(examModes != null)
            return new PageInfo<>(examModes);
        else
            return null;
    }

    @Override
    public List<ExamMode> selectAll() {
        return examModeMapper.selectByExample(null);
    }
}
