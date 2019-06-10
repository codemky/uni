package edu.uni.userBaseInfo1.service.impl;

import edu.uni.administrativestructure.bean.Class;
import edu.uni.administrativestructure.bean.ClassExample;
import edu.uni.administrativestructure.mapper.ClassMapper;
import edu.uni.userBaseInfo1.service.OtherClassService;
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
public class OtherClassServiceImpl implements OtherClassService {

    @Autowired
    private ClassMapper classMapper;

    //查询班级信息
    @Override
    public Class select(Long id) {
        return classMapper.selectByPrimaryKey(id);
    }

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

    @Override
    public List<Class> selectAllClassByDepartmentId(Long depaermentId) {
        ClassExample classExample = new ClassExample();
        ClassExample.Criteria criteria = classExample.createCriteria();
        criteria.andDepartmentIdEqualTo(depaermentId);
        criteria.andDeletedEqualTo(false);
        return  classMapper.selectByExample(classExample);
    }

    @Override
    public Class selectClassByName(String Name) {
        ClassExample classExample = new ClassExample();
        ClassExample.Criteria criteria = classExample.createCriteria();
        criteria.andNameEqualTo(Name);
        criteria.andDeletedEqualTo(false);
        List<Class> classes = classMapper.selectByExample(classExample);
        return classes.size()<=0?null:classes.get(0);
    }

    @Override
    public List<Class> selectClassByyear(Integer year) {
        ClassExample classExample = new ClassExample();
        ClassExample.Criteria criteria = classExample.createCriteria();
        criteria.andCyearEqualTo(year);
        return classMapper.selectByExample(classExample);
    }

    @Override
    public Class selectClassByClassCode(String classCode) {
        ClassExample classExample = new ClassExample();
        ClassExample.Criteria criteria = classExample.createCriteria();
        criteria.andCodeEqualTo(classCode);
        criteria.andDeletedEqualTo(false);
        List<Class> classes = classMapper.selectByExample(classExample);
        return classes.size()<=0?null:classes.get(0);
    }

    /**
     * Author: mokuanyuan 17:48 2019/6/7
     * @param grade
     * @param SpecialtyId
     * @return List<Class>
     * @apiNote: 根据年级和专业id查询相应的班级
     */
    @Override
    public List<Class> selectClassByGradeAndSpecialtyId(Integer grade, Long SpecialtyId) {
        ClassExample example = new ClassExample();
        ClassExample.Criteria criteria = example.createCriteria();
        criteria.andSpecialtyIdEqualTo(SpecialtyId);
        criteria.andCyearEqualTo(grade);
        criteria.andDeletedEqualTo(false);

        //注意！！！该方法没有加学校id就进行搜索时因为是先选择了相应的专业后再调用这个方法的（搜索专业时有加学校条件搜索）
        //既然都已经选了某个学校的专业了，那对应的班级应该也是同一个学校的才对
        return classMapper.selectByExample(example);

    }
}
