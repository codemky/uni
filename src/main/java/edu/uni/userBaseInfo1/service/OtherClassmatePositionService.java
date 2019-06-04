package edu.uni.userBaseInfo1.service;

import edu.uni.administrativestructure.bean.ClassmatePosition;

public interface OtherClassmatePositionService {
    /**
     * Author: chenenru 20:45 2019/6/3
     * @param classMateId,positionId
     * @return ClassmatePosition
     * @apiNote: 根据班级成员的id和岗位的id查询有效的学生的岗位
     */
    ClassmatePosition selectclassmatePositionByClassmateIdAndPositionId(Long classMateId,Long positionId);

    /**
     * Author: chenenru 10:55 2019/6/4
     * @param classMateId
     * @return ClassmatePosition
     * @apiNote: 根据班级成员的id查询有效的学生的岗位
     */
    ClassmatePosition selectclassmatePositionByClassmateId(Long classMateId);


}
