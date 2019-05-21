package edu.uni.userBaseInfo1.service;

import edu.uni.administrativestructure.bean.University;
import edu.uni.administrativestructure.service.UniversityService;

import java.util.List;

/**
 * author：黄育林
 * create: 2019.4.20
 * 功能：学校接口
 */
public interface OtherUniversityService {
    /**
     * Author: laizhouhao 19:11 2019/5/16
     * @return List<University>
     * @apiNote: 获取所有有效的学校信息
     */
    List<University> selectAllValidUniversities();

    /**
     * Author: laizhouhao 20:28 2019/5/19
     * @param university_id
     * @return University
     * @apiNote: 根据学校id获取有效的学校信息
     */
    University selectValidById(Long university_id);
}
