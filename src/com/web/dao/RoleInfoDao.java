package com.web.dao;

import com.web.entity.RoleInfo;

import java.util.List;

public interface RoleInfoDao {
    /**
     * 根据角色名称 模糊查询角色列表
     * @param name 角色名字
     * @return 角色集合
     */
    List<RoleInfo> getRoleInfoAllByName(String name);

    /**
     * 根据角色ID查询角色对象
     * @param id 角色ID
     * @return
     */

    RoleInfo getRoleInfoById(Integer id);

    int addRoleInfo(String name);

    int updateRoleById(String name, String id);

    int updateRoleStatus(Integer id);

    int batchUpdateRoleStatus(String[] ids);
}
