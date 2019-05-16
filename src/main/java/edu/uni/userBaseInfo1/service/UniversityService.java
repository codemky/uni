package edu.uni.userBaseInfo1.service;

import com.github.pagehelper.PageInfo;
import edu.uni.userBaseInfo1.bean.University;

import java.util.List;

/**
 * author：黄育林
 * create: 2019.4.20
 * 功能：学校接口
 */
public interface UniversityService {
    /**
     * Author: laizhouhao 19:11 2019/5/16
     * @return List<University>
     * @apiNote: 获取所有有效的学校信息
     */
    List<University> selectAllValidUniversities();
}
