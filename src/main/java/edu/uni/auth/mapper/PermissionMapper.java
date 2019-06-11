package edu.uni.auth.mapper;

import edu.uni.auth.bean.Permission;
import edu.uni.auth.bean.PermissionExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
/**
 * @Author 何亮
 */
@Mapper
public interface PermissionMapper {
    @Select("select * from permission where id in " +
            "(" +
            "   select DISTINCT permission_id " +
            "   from func_permission " +
            "   where func_id in" +
            "   (" +
            "       select DISTINCT func_id " +
            "       from role_func " +
            "       where role_id=#{roleId}" +
            "   )" +
            ");")
    List<Permission> selectByRoleId(long roleId);

    @Select("select `name` from `permission` where id in" +
            "    (" +
            "        select permission_id from `func_permission` where status=0 and func_id in" +
            "        (" +
            "            select func_id from `role_func` where status=0 and role_id in" +
            "            (select role_id from `user_role` where status=0 and user_id=#{uid})" +
            "        )" +
            "    );")
    List<String> selectNameByUid(long uid);

    int countByExample(PermissionExample example);

    int deleteByExample(PermissionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    int insertSelective(Permission record);

    List<Permission> selectByExample(PermissionExample example);

    Permission selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Permission record, @Param("example") PermissionExample example);

    int updateByExample(@Param("record") Permission record, @Param("example") PermissionExample example);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);


}