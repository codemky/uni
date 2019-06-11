package edu.uni.auth.mapper;

import edu.uni.auth.bean.UnivInfoSUP;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
/**
 * @author 何亮
 */
@Mapper
public interface UnivInfoSUPMapper {

    @Select("select d.`name` as universityName,d.ename as universityEname,d.telephone as universityTelephone,c.user_name, c.identification as userIdentification,c.university_id,c.`status` from " +
            "(" +
            "select a.user_name,a.identification,a.user_sex,b.university_id,b.`status` from `user` as a " +
            "inner join (select university_id,user_id,`status` from `user_role` where role_id=2) as b " +
            "where b.user_id = a.id " +
            ") as c " +
            "inner join `university` as d "  +
            "where d.id = c.university_id " +
            "and d.`name` like #{name} " +
            "and d.ename like #{ename} ")
    List<UnivInfoSUP> selectAllByUnivNameAndEname(@Param("name") String name, @Param("ename") String ename);
}
