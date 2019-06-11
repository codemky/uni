package edu.uni.auth.service.impl;

import com.github.pagehelper.PageHelper;
import edu.uni.auth.bean.University;
import edu.uni.auth.mapper.UniversityMapperOfAuth;
import edu.uni.auth.service.UniversityServiceOfAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversityServiceOfAuthImpl implements UniversityServiceOfAuth {
    @Autowired
    private UniversityMapperOfAuth universityMapper;

    @Override
    public List<University> selectPageByNameAndEname(int pageSize, int pageNum, String name, String ename) {
        PageHelper.startPage(pageNum, pageSize);
        return universityMapper.selectByLikeNameAndEname("%" + name + "%", "%" + ename + "%");
    }

    @Override
    public List<University> selectAll() {
        return universityMapper.selectAll();
    }
}
