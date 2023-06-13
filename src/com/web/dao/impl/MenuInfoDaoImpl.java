package com.web.dao.impl;

import com.mysql.cj.util.StringUtils;
import com.web.dao.MenuInfoDao;
import com.web.entity.MenuInfo;
import com.web.entity.vo.MenuInfoListVo;
import com.web.utils.BaseDao2;
import com.web.utils.BaseResult;

import java.util.List;
import java.util.Map;

/**
 * 根据pid查询菜单集合
 */
public class MenuInfoDaoImpl extends BaseDao2<MenuInfo> implements MenuInfoDao {

    @Override
    public List<MenuInfo> getMenuInfoListByPid(Integer userId, Integer pId) {
        //String sql1="select * from t_menu_info where `status`=1 and p_id=?";
        String sql = "select tmi.*\n" +
                "from t_menu_info tmi\n" +
                "left join t_menu_role_info tmri on tmri.menu_id=tmi.id\n" +
                "left join t_user_role_info turi on turi.role_id=tmri.role_id\n" +
                "where turi.user_id=? and tmi.`status`=1 and tmi.p_id=?";
        return selectListForObject(sql, MenuInfo.class, userId, pId);
    }

    @Override
    public List<Map<String, Object>> getMenuAll(String keyword) {
        String sql = "SELECT tmi1.*,tmi2.title as pName\n" +
                "from t_menu_info tmi1\n" +
                "LEFT JOIN t_menu_info tmi2 on tmi2.id=tmi1.p_id";
        if (!StringUtils.isNullOrEmpty(keyword)) {
            sql += " where tmi1.title like concat('%',?,'%')";
            return selectListForMap(sql, keyword);
        }
        return selectListForMap(sql);
    }

    @Override
    public MenuInfo getMenuInfoById(Integer id) {
        String sql = "select * from t_menu_info where id=?";
        return selectOne(sql, MenuInfo.class, id);
    }

    @Override
    public int updateMenuInfoById(Integer id, String title, String icon, String href, Integer pId) {
        String sql = "update t_menu_info set title=?,icon=?,href=?,p_id=? where id =?";
        return executeUpdate(sql, title, icon, href, pId, id);
    }

    @Override
    public int deleteMenuInfoById(Integer id) {
        String sql = "update t_menu_info set status=?,icon=? where id =?";
        return executeUpdate(sql, id);
    }

    @Override
    public List<MenuInfo> getMenuInfoByPid(Integer pId) {
        String sql = "select * from t_menu_info where `status`=1 and p_id=?";
        return selectListForObject(sql, MenuInfo.class, pId);
    }

    @Override
    public int saveMenu(String title, String icon, String href, Integer pId) {
        String sql = "insert into t_menu_info values(null,?,?,?,?,now(),default)";
        return executeUpdate(sql, title, icon, href, pId);
    }
}
