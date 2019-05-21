package edu.uni.userBaseInfo1.service.impl;

import edu.uni.administrativestructure.bean.University;
import edu.uni.administrativestructure.bean.UniversityExample;
import edu.uni.administrativestructure.mapper.UniversityMapper;
import edu.uni.userBaseInfo1.service.OtherUniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author：黄育林
 * create: 2019.4.20
 * modified: 2019.5.9
 * 功能：学校接口实现类
 */
@Service
public class OtherUniversityServiceImpl implements OtherUniversityService {
    @Autowired
    private UniversityMapper universityMapper;


    /**
     * Author: laizhouhao 19:11 2019/5/16
     * @return List<University>
     * @apiNote: 获取所有有效的学校信息
     */
    @Override
    public List<University> selectAllValidUniversities() {
        //构造查询条件，false表示有效的学校信息
        UniversityExample universityExample = new UniversityExample();
        universityExample.createCriteria().andDeletedEqualTo(false);
        //查询所有符合该条件的信息并返回
        return universityMapper.selectByExample(universityExample);
    }

    /**
     * Author: laizhouhao 20:28 2019/5/19
     * @param university_id
     * @return University
     * @apiNote: 根据学校id获取有效的学校信息
     */
    @Override
    public University selectValidById(Long university_id) {
        //构造查询条件
        UniversityExample universityExample = new UniversityExample();
        UniversityExample.Criteria criteria = universityExample.createCriteria();
        criteria.andIdEqualTo(university_id).andDeletedEqualTo(false);
        //查询所有的满足条件的学校
        List<University> universityList = universityMapper.selectByExample(universityExample);
        return universityList.size()>=1?universityList.get(0):null;
    }
}
