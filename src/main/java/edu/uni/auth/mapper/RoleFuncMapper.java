package edu.uni.auth.mapper;

import edu.uni.auth.bean.RoleFunc;
import edu.uni.auth.bean.RoleFuncExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleFuncMapper {
    int countByExample(RoleFuncExample example);

    int deleteByExample(RoleFuncExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RoleFunc record);

    int insertSelective(RoleFunc record);

    List<RoleFunc> selectByExample(RoleFuncExample example);

    RoleFunc selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RoleFunc record, @Param("example") RoleFuncExample example);

    int updateByExample(@Param("record") RoleFunc record, @Param("example") RoleFuncExample example);

    int updateByPrimaryKeySelective(RoleFunc record);

    int updateByPrimaryKey(RoleFunc record);
}