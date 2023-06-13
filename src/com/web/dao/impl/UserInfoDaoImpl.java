package com.web.dao.impl;

import com.mysql.cj.util.StringUtils;
import com.web.dao.UserInfoDao;
import com.web.entity.UserInfo;
import com.web.utils.BaseDao2;

import java.util.List;

public class UserInfoDaoImpl extends BaseDao2<UserInfo> implements UserInfoDao {
    @Override
    public UserInfo getUserInfoByUserNameAndPassword(String userName, String password) {
        String sql = "select * from t_user_info where user_name=? and `password`=?";
        return selectOne(sql,UserInfo.class,userName,password);
    }

    @Override
    public List<UserInfo> getUserInfoById(String userName) {
        String sql = "select * from t_user_info where `status`=1";
//        String sql = "select * from t_user_info";
        if (!StringUtils.isNullOrEmpty(userName)){
            sql += " and user_name like concat('%',?,'%')";
            return selectListForObject(sql, UserInfo.class,userName);
        }
        return selectListForObject(sql,UserInfo.class);
    }

    @Override
    public UserInfo getUserInfoAllId(Integer id) {
        String sql = "select * from t_user_info where id=?";
        return selectOne(sql, UserInfo.class,id);
    }

    @Override
    public int addUserInfo(String userName, String password,String nickName,String tel,String address,String sex) {
        String sql = "insert into t_user_info values(null,?,?,?,?,?,?,default,now(),default)";
        return executeUpdate(sql,userName,password,nickName,tel,address,sex);
    }

    @Override
    public int updateUserInfoById(String userName, String nickName, String tel, String address, String sex, String id) {
        String sql = "update t_user_info set user_name=?,nick_name=?,tel=?,address=?,sex=? where id=?";
        return executeUpdate(sql,userName,nickName,tel,address,sex,id);
    }

    @Override
    public int updateUserStatus(Integer id) {
        String sql = "update t_user_info set `status`=IF(`status`=1,0,1) where id=?";
        return executeUpdate(sql,id);
    }
}
