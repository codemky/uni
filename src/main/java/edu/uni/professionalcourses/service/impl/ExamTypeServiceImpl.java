/*
author:  邓凯丰
create:  2019.5.10
modified:  2019.5.11
*/
package edu.uni.professionalcourses.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.professionalcourses.bean.ExamType;
import edu.uni.professionalcourses.bean.ExamTypeExample;
import edu.uni.professionalcourses.config.ProfessionalCoursesConfig;
import edu.uni.professionalcourses.mapper.ExamTypeMapper;
import edu.uni.professionalcourses.service.ExamTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ExamTypeServiceImpl implements ExamTypeService {
    @Autowired
    private ExamTypeMapper examTypeMapper;
    @Autowired
    private ProfessionalCoursesConfig globalConfig;


    @Override
    public boolean insert(ExamType examType) {
        return examTypeMapper.insert(examType) > 0 ? true : false;
    }

    @Override
    public boolean delete(long id) {
        ExamType examType=examTypeMapper.selectByPrimaryKey(id);
        examType.setDeleted(true);

        return examTypeMapper.updateByPrimaryKeySelective(examType) > 0 ? true : false;
    }

    @Override
    public boolean update(ExamType examType) {
        ExamType examType1=new ExamType();
        examType1=examTypeMapper.selectByPrimaryKey(examType.getId());
        examType1.setDeleted(true);
        examTypeMapper.insert(examType);

        return examTypeMapper.updateByPrimaryKeySelective(examType) > 0 ? true : false;
    }

    @Override
    public ExamType select(long id) {
        ExamType examType=examTypeMapper.selectByPrimaryKey(id);
        if (examType.getDeleted()==false){
            return examType;
        }else {
            return null;
        }
    }

    @Override
    public PageInfo<ExamType> selectPage(int pageNum) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());
        ExamTypeExample example=new ExamTypeExample();
        ExamTypeExample.Criteria criteria=example.createCriteria();
        criteria.andDeletedEqualTo(false);

        List<ExamType> examModes =examTypeMapper.selectByExample(example);
        if(examModes != null)
            return new PageInfo<>(examModes);
        else
            return null;
    }

    @Override
    public PageInfo<ExamType> selectPageByUniversity(int pageNum, long universityId) {
        PageHelper.startPage(pageNum, globalConfig.getPageSize());
        // 创建查询条件
        ExamTypeExample example = new ExamTypeExample();
        ExamTypeExample.Criteria criteria = example.createCriteria();
        criteria.andUniversityIdEqualTo(universityId).andDeletedEqualTo(false);
        // 根据条件查询
        List<ExamType> examModes = examTypeMapper.selectByExample(example);
        if(examModes != null)
            return new PageInfo<>(examModes);
        else
            return null;
    }

    @Override
    public List<ExamType> selectAll() {
        return examTypeMapper.selectByExample(null);
    }
}

