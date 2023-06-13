package com.web.dao;

import com.web.entity.UserInfo;

import java.util.List;

/**
 * 根据用户名 查询用户信息
 * userName 用户名
 * password 密码
 * return 用户对象
 */
public interface UserInfoDao {
    UserInfo getUserInfoByUserNameAndPassword(String userName,String password);

    List<UserInfo> getUserInfoById(String userName);

    UserInfo getUserInfoAllId(Integer id);

    int addUserInfo(String userName,String password,String nickName,String tel,String address,String sex);

    int updateUserInfoById(String userName,String nickName, String tel, String address, String sex, String id);


    int updateUserStatus(Integer id);
}
