package com.web.service;

import com.web.entity.RoleInfo;
import com.web.entity.UserInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface RoleInfoService {
    /**
     * 根据rolename查询所有角色 支持模糊查询
     */
    void getRoleInfoAllByName1(HttpServletRequest request);

    /**
     * 根据角色ID查询角色对象
     * @param request
     * @return
     */

    String getRoleInfoById(HttpServletRequest request);

    /**
     * 新增角色和修改角色 根据id来判断 如果id能取到值 则为修改反之为新增
     * @param request
     * @return
     */
    String editRole(HttpServletRequest request);

    /**
     * 根据角色id删除角色
     * @param request
     * @return
     */

    String updateRoleStatus(HttpServletRequest request);

    /**
     * 批量启用
     * @param request
     * @return
     */
    String enableRoles(HttpServletRequest request);
}
