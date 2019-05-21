package edu.uni.educateAffair.mapper;




import edu.uni.educateAffair.bean.Semester;
import edu.uni.educateAffair.bean.SemesterExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface SemesterMapper {
    long countByExample(SemesterExample example);

    int deleteByExample(SemesterExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Semester record);

    int insertSelective(Semester record);

    List<Semester> selectByExample(SemesterExample example);

    Semester selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Semester record, @Param("example") SemesterExample example);

    int updateByExample(@Param("record") Semester record, @Param("example") SemesterExample example);

    int updateByPrimaryKeySelective(Semester record);

    int updateByPrimaryKey(Semester record);
}