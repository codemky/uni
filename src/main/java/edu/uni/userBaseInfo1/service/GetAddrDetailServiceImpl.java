package edu.uni.userBaseInfo1.service;

import edu.uni.userBaseInfo1.bean.Address;
import edu.uni.userBaseInfo1.utils.UserInfo;

import java.util.List;

/**
 * @Author laizhouhao
 * @Description
 * @Date 20:00 2019/5/8
 **/
public interface GetAddrDetailServiceImpl{
    public UserInfo reviceAddrDetail(List<Address> addresses);
}
