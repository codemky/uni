package edu.uni.userBaseInfo1.service;

import edu.uni.place.bean.Field;

import java.util.List;

/**
 * InterfaceName OtherFieldService
 * Description TODO
 * Author LonelySeven
 * Date 2019/6/7 22:02
 * Version 1.0
 **/
public interface OtherFieldService {

    /**
     * Author: mokuanyuan 21:18 2019/6/9
     * @param schoolId
     * @returns List<Field>
     * @apiNote: 根据学校id查询所有的宿舍类型的场地
     */
    public List<Field> selectAllDormitoriesBySchoolId(Long schoolId);

    /**
     * Author: laizhouhao 19:19 2019/6/10
     * @param id
     * @return
     * @apiNote:
     */
    public Field selectById(Long id);

}
