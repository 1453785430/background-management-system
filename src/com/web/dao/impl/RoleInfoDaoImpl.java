package com.web.dao.impl;

import com.mysql.cj.util.StringUtils;
import com.web.dao.RoleInfoDao;
import com.web.entity.RoleInfo;
import com.web.utils.BaseDao2;
import com.web.utils.BaseResult;

import java.util.List;

public class RoleInfoDaoImpl extends BaseDao2<RoleInfo> implements RoleInfoDao {
    @Override
    public List<RoleInfo> getRoleInfoAllByName(String name) {
        String sql = "select * from t_role_info";
        if (!StringUtils.isNullOrEmpty(name)) {
            sql += " and name like concat('%',?,'%')";
            return selectListForObject(sql, RoleInfo.class, name);
        }
        return selectListForObject(sql, RoleInfo.class);
    }

    @Override
    public RoleInfo getRoleInfoById(Integer id) {
        String sql = "select * from t_role_info where id=?";
        return selectOne(sql, RoleInfo.class, id);
    }

    @Override
    public int addRoleInfo(String name) {
        String sql = "insert into t_role_info values(null,?,now(),default)";
        return executeUpdate(sql, name);
    }

    @Override
    public int updateRoleById(String name, String id) {
        String sql = "update t_role_info set name=? where id=?";
        return executeUpdate(sql, name, id);
    }

    @Override
    public int updateRoleStatus(Integer id) {
        String sql = "update t_role_info set `status`=IF(`status`=1,0,1) where id=?";
        return executeUpdate(sql, id);
    }

    @Override
    public int batchUpdateRoleStatus(String[] ids) {
        String sql = "update t_role_info set `status`=1 where id in(";
        for (int i = 0; i < ids.length; i++) {
            if (i == ids.length - 1) {
                sql += " ?";
            } else {
                sql += " ?,";
            }
        }
        sql += ")";
        return executeUpdate(sql,ids);
    }
}
