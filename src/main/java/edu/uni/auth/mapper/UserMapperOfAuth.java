package edu.uni.auth.mapper;

import edu.uni.auth.bean.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author 何亮
 */
@Mapper
public interface UserMapperOfAuth {
    @Insert("INSERT INTO `user`(`name`, `pwd`, `status`, `salt`, `university_id`, `user_type`, `regist`) VALUES(#{name}, #{pwd}, #{status}, #{salt}, #{universityId}, #{user_type}, #{regist});")
    long insert(User user);

    @Select("select * from `user` where `name`=#{name};")
    User selectByName(String name);

    @Select("select * from `user` where `university_id`=#{universityId} and `name` like #{name} and `user_name` like #{userName}")
    List<User> selectLikeByByUniversityIdNameAndUserNameAndUserType(@Param("universityId") Long universityId, @Param("name") String name, @Param("userName") String userName);

    @Select("select * from `user` where id=#{id};")
    User selectByPrimaryKey(Long id);

    @Select("select `name` from `role` where id in" +
            "    (select role_id from `user_role` where status=0 and user_id=#{id})")
    List<String> selectRoles(Long id);

    @Select("select DISTINCT `name` from `permission`" +
            "where id in " +
            "(" +
            "    select `permission_id` from `func_permission`" +
            "    where func_id in" +
            "    (" +
            "        select `func_id` from `role_func` " +
            "        where role_id in" +
            "        (" +
            "            select `role_id` from `user_role` " +
            "            where user_id=#{id} " +
            "            and role_id in" +
            "                (select `id` from `role` where `status`=0)" +
            "        )" +
            "    )" +
            ");")
    List<String> selectPermissions(Long id);

    @Update("update `user` set pwd=#{pwd},salt=#{salt} where id=#{id}")
    int updatePwdAndSalt(Long id, String pwd, String salt);
}
