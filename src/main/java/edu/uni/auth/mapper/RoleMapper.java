package edu.uni.auth.mapper;

import edu.uni.auth.bean.Role;
import edu.uni.auth.bean.RoleExample;
import java.util.List;

import edu.uni.auth.bean.Url;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Author 何亮
 */
@Mapper
public interface RoleMapper {

    @Select("select `func_id` from `role_func` where `role_id`=#{roleId}")
    List<Long> selectFuncSetByRoleId(Long roleId);

    @Select("select role_id as id from `user_role` " +
            "where user_id=#{userId} and role_id in" +
            "(select id from `role` where university_id=#{universityId})")
    List<Long> selectRoleSetByUserIdAndUniversityId(@Param("userId") Long userId, @Param("universityId") Long universityId);

    @Select("select temp.url,temp.method,r.`name` from `role` as r" +
            "    inner join" +
            "    (" +
            "        select fp.url,fp.method,rf.role_id from `role_func` as rf" +
            "        inner join" +
            "        (" +
            "            select permission.url,permission.method,func_permission.func_id from `permission`" +
            "            inner join `func_permission`" +
            "            where func_permission.`status`=0 and permission.`id`=func_permission.`permission_id`" +
            "        )as fp" +
            "        where rf.`status`=0 and rf.`func_id`=fp.`func_id`" +
            "    )as temp" +
            "    where r.id=temp.role_id and r.`status`=0;")
    List<Url> selectUrl();

    @Select("select `name` from `role` where id in" +
            "    (select role_id from `user_role` where status=0 and user_id=#{userId})")
    List<String> selectNameByUid(long uid);

    int countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);



}