package edu.uni.auth.mapper;

import edu.uni.auth.bean.Func;
import edu.uni.auth.bean.FuncModule;
import edu.uni.auth.bean.FuncModuleExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Author 何亮
 */
@Mapper
public interface FuncModuleMapper {

    @Select("select * from `func_module`" +
            "where id in" +
            "(" +
            "    select DISTINCT module_id from `func` " +
            "    where id in" +
            "    (" +
            "        select DISTINCT func_id from `role_func` " +
            "        where role_id in" +
            "        (" +
            "            select DISTINCT role_id from `user_role` " +
            "            where user_id=#{userId}" +
            "        )" +
            "    )" +
            ");")
    List<FuncModule> selectAllFuncModuleByUserId(Long userId);

    int countByExample(FuncModuleExample example);

    int deleteByExample(FuncModuleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FuncModule record);

    int insertSelective(FuncModule record);

    List<FuncModule> selectByExample(FuncModuleExample example);

    FuncModule selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FuncModule record, @Param("example") FuncModuleExample example);

    int updateByExample(@Param("record") FuncModule record, @Param("example") FuncModuleExample example);

    int updateByPrimaryKeySelective(FuncModule record);

    int updateByPrimaryKey(FuncModule record);
}