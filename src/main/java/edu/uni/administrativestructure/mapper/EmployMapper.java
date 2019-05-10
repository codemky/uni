package edu.uni.administrativestructure.mapper;

import edu.uni.administrativestructure.bean.Employ;
import edu.uni.administrativestructure.bean.EmployExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployMapper {
    int countByExample(EmployExample example);

    int deleteByExample(EmployExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Employ record);

    int insertSelective(Employ record);

    List<Employ> selectByExample(EmployExample example);

    Employ selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Employ record, @Param("example") EmployExample example);

    int updateByExample(@Param("record") Employ record, @Param("example") EmployExample example);

    int updateByPrimaryKeySelective(Employ record);

    int updateByPrimaryKey(Employ record);
}