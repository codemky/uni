package edu.uni.administrativestructure.mapper;

import edu.uni.administrativestructure.bean.DepartmentClass;
import edu.uni.administrativestructure.bean.DepartmentClassExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepartmentClassMapper {
    int countByExample(DepartmentClassExample example);

    int deleteByExample(DepartmentClassExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DepartmentClass record);

    int insertSelective(DepartmentClass record);

    List<DepartmentClass> selectByExample(DepartmentClassExample example);

    DepartmentClass selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DepartmentClass record, @Param("example") DepartmentClassExample example);

    int updateByExample(@Param("record") DepartmentClass record, @Param("example") DepartmentClassExample example);

    int updateByPrimaryKeySelective(DepartmentClass record);

    int updateByPrimaryKey(DepartmentClass record);
}