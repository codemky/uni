package edu.uni.place.service;


import com.github.pagehelper.PageInfo;
import edu.uni.place.dto.Fielddto;

public interface FielddtoService {

    PageInfo<Fielddto> selectFileddtosPageByIds(
            Long universityId, Long schoolId, Long areaId, String name, int pageNum
    );
}
