package edu.uni.userBaseInfo1.service;

import edu.uni.userBaseInfo1.bean.Specialty;

import java.util.List;

public interface SpecialtyService {
    /**
     * Author: chenenru 9:59 2019/5/19
     * @param
     * @return
     * @apiNote: 根据学校id，学院id和专业名称查询对应的专业
     */
    List<Specialty> seclectByDIdAndSName(Long uId,Long DId, String SName);
}
