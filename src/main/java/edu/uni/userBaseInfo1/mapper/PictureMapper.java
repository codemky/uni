package edu.uni.userBaseInfo1.mapper;

import edu.uni.userBaseInfo1.bean.Picture;
import edu.uni.userBaseInfo1.bean.PictureExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PictureMapper {
    //按条件计数
    int countByExample(PictureExample example);

    //按条件删除
    int deleteByExample(PictureExample example);

    //按主键删除
    int deleteByPrimaryKey(long id);

    //插入数据（返回值为id)
    int insert(Picture record);

    //插入数据不为null的字段值
    int insertSelective(Picture record);

    //按条件查询
    List<Picture> selectByExample(PictureExample example);

    //按主键查询
    Picture selectByPrimaryKey(Long id);

    //按用户的id查询
    List<Picture> selectByUserId(Long userId);

    //按条件更新值不为null的字段
    int updateByExampleSelective(@Param("record") Picture record, @Param("example") PictureExample example);

    //按条件更新
    int updateByExample(@Param("record") Picture record, @Param("example") PictureExample example);

    //按主键更新不为null的字段
    int updateByPrimaryKeySelective(Picture record);

    //按主键更新
    int updateByPrimaryKey(Picture record);
}