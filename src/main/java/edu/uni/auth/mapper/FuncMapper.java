package edu.uni.auth.mapper;

import edu.uni.auth.bean.Func;
import edu.uni.auth.bean.FuncExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Author 何亮
 */
@Mapper
public interface FuncMapper {

    /**
     * 根据用户id获取用户的所有功能点
     * @param userId
     * @return
     */
    @Select("select * from `func` " +
            "where id in" +
            "(" +
            "    select DISTINCT func_id from `role_func` " +
            "    where role_id in" +
            "    (" +
            "        select DISTINCT role_id from `user_role` " +
            "        where user_id=#{userId}" +
            "    )" +
            ");")
    List<Func> selectFuncByUserId(Long userId);

    int countByExample(FuncExample example);

    int deleteByExample(FuncExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Func record);

    int insertSelective(Func record);

    List<Func> selectByExample(FuncExample example);

    Func selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Func record, @Param("example") FuncExample example);

    int updateByExample(@Param("record") Func record, @Param("example") FuncExample example);

    int updateByPrimaryKeySelective(Func record);

    int updateByPrimaryKey(Func record);
}