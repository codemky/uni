package edu.uni.userBaseInfo1.mapper;

import edu.uni.userBaseInfo1.bean.Student;
import edu.uni.userBaseInfo1.bean.StudentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {
    //按条件计数
    int countByExample(StudentExample example);

    //按条件删除
    int deleteByExample(StudentExample example);

    //按主键删除
    int deleteByPrimaryKey(Long id);

    //插入数据（返回值为id)
    int insert(Student record);

    //插入数据不为null的字段值
    int insertSelective(Student record);

    //按条件查询
    List<Student> selectByExample(StudentExample example);

    //按主键查询
    Student selectByPrimaryKey(Long id);

    //按条件更新值不为null的字段
    int updateByExampleSelective(@Param("record") Student record, @Param("example") StudentExample example);

    //按条件更新
    int updateByExample(@Param("record") Student record, @Param("example") StudentExample example);

    //按主键更新不为null的字段
    int updateByPrimaryKeySelective(Student record);

    //按主键更新
    int updateByPrimaryKey(Student record);
}