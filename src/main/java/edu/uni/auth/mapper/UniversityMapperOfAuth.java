package edu.uni.auth.mapper;

import edu.uni.auth.bean.University;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UniversityMapperOfAuth {
    @Select("select * from `university` where `name` like #{name} and `ename` like #{ename}")
    List<University> selectByLikeNameAndEname(@Param("name") String name, @Param("ename") String ename);

    @Select("select * from `university` where id=#{id}")
    University selectByPrimaryKey(long id);

    @Select("select * from `university`")
    List<University> selectAll();
}
