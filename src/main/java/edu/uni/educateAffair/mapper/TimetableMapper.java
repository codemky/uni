package edu.uni.educateAffair.mapper;

import edu.uni.educateAffair.bean.Timetable;
import edu.uni.educateAffair.bean.TimetableExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TimetableMapper {
    long countByExample(TimetableExample example);

    int deleteByExample(TimetableExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Timetable record);

    int insertSelective(Timetable record);

    List<Timetable> selectByExample(TimetableExample example);

    Timetable selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Timetable record, @Param("example") TimetableExample example);

    int updateByExample(@Param("record") Timetable record, @Param("example") TimetableExample example);

    int updateByPrimaryKeySelective(Timetable record);

    int updateByPrimaryKey(Timetable record);
}