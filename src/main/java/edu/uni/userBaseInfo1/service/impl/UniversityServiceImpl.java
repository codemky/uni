package edu.uni.userBaseInfo1.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.userBaseInfo1.bean.University;
import edu.uni.userBaseInfo1.bean.UniversityExample;
import edu.uni.userBaseInfo1.mapper.UniversityMapper;
import edu.uni.userBaseInfo1.service.UniversityService;
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
public class UniversityServiceImpl implements UniversityService {
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
}
