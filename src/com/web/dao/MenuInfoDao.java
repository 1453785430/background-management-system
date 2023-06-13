package com.web.dao;

import com.web.entity.MenuInfo;
import com.web.entity.vo.MenuInfoListVo;

import java.util.List;
import java.util.Map;

public interface MenuInfoDao {
    /**
     * 根据用户ID和菜单Pid查询菜单集合
     *
     * @param userId 用户ID
     * @param pId    父级id
     * @return
     */
    List<MenuInfo> getMenuInfoListByPid(Integer userId, Integer pId);

    List<Map<String, Object>> getMenuAll(String keyword);

    MenuInfo getMenuInfoById(Integer id);

    int updateMenuInfoById(Integer id, String title, String icon, String href, Integer pId);

    int deleteMenuInfoById(Integer id);

    List<MenuInfo> getMenuInfoByPid(Integer pId);

    int saveMenu(String title, String icon, String href, Integer pId);
}
