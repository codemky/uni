package edu.uni.userBaseInfo1.mapper;

import edu.uni.userBaseInfo1.bean.UserUploadFile;
import edu.uni.userBaseInfo1.bean.UserUploadFileExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserUploadFileMapper {
    int countByExample(UserUploadFileExample example);

    int deleteByExample(UserUploadFileExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserUploadFile record);

    int insertSelective(UserUploadFile record);

    List<UserUploadFile> selectByExample(UserUploadFileExample example);

    UserUploadFile selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserUploadFile record, @Param("example") UserUploadFileExample example);

    int updateByExample(@Param("record") UserUploadFile record, @Param("example") UserUploadFileExample example);

    int updateByPrimaryKeySelective(UserUploadFile record);

    int updateByPrimaryKey(UserUploadFile record);
}