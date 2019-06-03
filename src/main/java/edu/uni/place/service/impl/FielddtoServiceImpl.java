package edu.uni.place.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uni.example.config.ExampleConfig;
import edu.uni.place.bean.Field;
import edu.uni.place.bean.FieldExample;
import edu.uni.place.bean.School;
import edu.uni.place.bean.Schoolarea;
import edu.uni.place.dto.Fielddto;
import edu.uni.place.mapper.FieldMapper;
import edu.uni.place.mapper.SchoolMapper;
import edu.uni.place.mapper.SchoolareaMapper;
import edu.uni.place.service.FielddtoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author 潘绍豪
 * @Create 2019/5/11
 * @Description
 * @Since 1.0.0
 */
@Service
public class FielddtoServiceImpl implements  FielddtoService {

    @Autowired
    private FieldMapper fieldMapper;
    @Autowired
    private SchoolMapper schoolMapper;
    @Autowired
    private SchoolareaMapper schoolareaMapper;

    @Autowired
    private ExampleConfig globalConfig;


    /**
     * @description: 根据校区id查校区名、功能区查功能区名、场地名模糊查询。
     * @author: 潘绍豪
     * @date: {2019/5/11} {15:19}
     */
    public PageInfo<Fielddto> selectFileddtosPageByIds(Long universityId, Long schoolId, Long areaId, String name, int pageNum) {

        FieldExample example = new FieldExample();
        FieldExample.Criteria criteria = example.createCriteria().andUniversityIdEqualTo(universityId).andDeletedEqualTo(0);
        // 分类判断
        if (schoolId != null) {
            criteria.andSchoolIdEqualTo(schoolId);
        }
        if (areaId != null) {
            criteria.andAreaIdEqualTo(areaId);
        }
        if(name != null){
            criteria.andNameLike("%" + name + "%");
        }

        PageHelper.startPage(pageNum, globalConfig.getPageSize());
        List<Field> fields =
                fieldMapper.selectByExample(example);
        PageInfo<Field> pageInfo = new PageInfo<>(fields);

        List<Fielddto> fielddtos = fields.stream().map(
                field -> {
                    Schoolarea schoolarea = schoolareaMapper.selectByPrimaryKey(field.getAreaId());
                    School school = schoolMapper.selectByPrimaryKey(field.getSchoolId());

                    // 复制到fielddto
                    Fielddto fielddto = new Fielddto();
                    BeanUtils.copyProperties(field, fielddto);
                    fielddto.setAreaname(schoolarea.getName());
                    fielddto.setSchoolname(school.getName());
                    return fielddto;
                }
        ).collect(Collectors.toList());
        PageInfo<Fielddto> fielddtoPageInfo = new PageInfo<>(fielddtos);

       //整合分页与数据
        PageInfo<Fielddto> resPageInfo = new PageInfo<>();
        pageInfo.setList(null);
        BeanUtils.copyProperties(pageInfo, resPageInfo);
        resPageInfo.setList(fielddtoPageInfo.getList());

        return resPageInfo;
    }
}
