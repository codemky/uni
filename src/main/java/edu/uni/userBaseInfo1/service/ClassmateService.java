package edu.uni.userBaseInfo1.service;

import edu.uni.userBaseInfo1.bean.Classmate;

import java.util.List;

public interface ClassmateService {

    /**
     * Author: chenenru 12:35 2019/5/16
     * @param classId
     * @return List<Classmate>
     * @apiNote: 根据班级id查询对应的所有学生
     */
    List<Classmate> selectByClassId(Long classId);

}
