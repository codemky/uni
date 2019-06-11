package edu.uni.auth.mapper;

import edu.uni.auth.bean.FuncPermission;
import edu.uni.auth.bean.FuncPermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FuncPermissionMapper {
    int countByExample(FuncPermissionExample example);

    int deleteByExample(FuncPermissionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FuncPermission record);

    int insertSelective(FuncPermission record);

    List<FuncPermission> selectByExample(FuncPermissionExample example);

    FuncPermission selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FuncPermission record, @Param("example") FuncPermissionExample example);

    int updateByExample(@Param("record") FuncPermission record, @Param("example") FuncPermissionExample example);

    int updateByPrimaryKeySelective(FuncPermission record);

    int updateByPrimaryKey(FuncPermission record);
}