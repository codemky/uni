package edu.uni.auth.service;

import edu.uni.auth.bean.University;

import java.util.List;

public interface UniversityServiceOfAuth {
    List<University> selectPageByNameAndEname(int pageSize, int pageNum, String name, String ename);

    List<University> selectAll();
}
