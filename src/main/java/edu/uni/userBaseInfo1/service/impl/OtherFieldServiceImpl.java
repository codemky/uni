package edu.uni.userBaseInfo1.service.impl;

import edu.uni.place.bean.Field;
import edu.uni.place.bean.FieldExample;
import edu.uni.place.bean.Fieldtype;
import edu.uni.place.bean.FieldtypeExample;
import edu.uni.place.mapper.FieldMapper;
import edu.uni.place.mapper.FieldtypeMapper;
import edu.uni.userBaseInfo1.service.OtherFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName OtherFieldServiceImpl
 * Description TODO
 * Author LonelySeven
 * Date 2019/6/7 22:14
 * Version 1.0
 **/
@Service
public class OtherFieldServiceImpl implements OtherFieldService {

    @Autowired
    private FieldMapper fieldMapper;

    @Autowired
    private FieldtypeMapper fieldtypeMapper;

    /**
     * Author: mokuanyuan 21:18 2019/6/9
     * @param schoolId
     * @returns List<Field>
     * @apiNote: 根据学校id查询所有的宿舍类型的场地
     */
    @Override
    public List<Field> selectAllDormitoriesBySchoolId(Long schoolId) {
        //首先搜索出“"宿舍"类型的场地类型id ( p_field_type表的id )
        FieldtypeExample fieldtypeExample = new FieldtypeExample();
        fieldtypeExample.createCriteria().andNameEqualTo("宿舍").andDeletedEqualTo(0);  //这个地方写死了"宿舍"！注意了！
        List<Fieldtype> fieldTypeList = fieldtypeMapper.selectByExample(fieldtypeExample);
        if(fieldTypeList.size() > 0){
            FieldExample fieldExample = new FieldExample();
            fieldExample.createCriteria().andUniversityIdEqualTo(schoolId).andTypeIdEqualTo(fieldTypeList.get(0).getId()).andDeletedEqualTo(0);
            return fieldMapper.selectByExample(fieldExample);
        }
        else
            return null;
    }

    /**
     * Author: laizhouhao 19:19 2019/6/10
     * @param id
     * @return
     * @apiNote:
     */
    public Field selectById(Long id){
        return fieldMapper.selectByPrimaryKey(id);
    }
}
