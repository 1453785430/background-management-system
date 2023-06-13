package com.web.service.impl;

import com.google.gson.Gson;
import com.web.component.BgmsConfig;
import com.web.dao.MenuInfoDao;
import com.web.dao.impl.MenuInfoDaoImpl;
import com.web.entity.MenuInfo;
import com.web.entity.UserInfo;
import com.web.entity.vo.MenuInfoVo;
import com.web.service.MenuInfoService;
import com.web.utils.BaseResult;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class MenuInfoServiceImpl implements MenuInfoService {
    private MenuInfoDao menuInfoDao = new MenuInfoDaoImpl();
    @Override
    public String showMenu(HttpServletRequest request) {
        //获取session中的用户信息
        UserInfo userinfo = (UserInfo) request.getSession().getAttribute(BgmsConfig.SESSION_USER_KEY);
        //组装显示的菜单树
        List<MenuInfoVo> menuInfoVos = assemblyMenuTree(userinfo.getId());
        request.setAttribute(BgmsConfig.CACHE_MENU_LIST,menuInfoVos);
        request.getSession().setAttribute(BgmsConfig.CACHE_MENU_LIST,menuInfoVos);
        return "pages/home";
    }

    @Override
    public void getMenuList(HttpServletRequest request) {
        String keyword = request.getParameter("keyword");

        request.setAttribute(BgmsConfig.MENU_LIST,menuInfoDao.getMenuAll(keyword));
        request.setAttribute(BgmsConfig.FIRST_MENU,menuInfoDao.getMenuInfoByPid(-1));
        request.setAttribute(BgmsConfig.KEYWORD,keyword);
    }

    @Override
    public String getMenu(HttpServletRequest request) {
        //获取用户提交过来的Menuid
        Integer id = Integer.parseInt(request.getParameter("id"));
        if (id == null){
            return BaseResult.error(20001,"请求数据异常");
        }
        MenuInfo menuInfo=menuInfoDao.getMenuInfoById(id);
        return BaseResult.success(menuInfo);
    }

    @Override
    public String editMenu(HttpServletRequest request) {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Integer pId = Integer.parseInt(request.getParameter("pId"));
        String title = request.getParameter("title");
        String icon = request.getParameter("icon");
        String href = request.getParameter("href");
        // 参数校验 如果非空字段为空 给出错误提示
        int rows = menuInfoDao.updateMenuInfoById(id,title,icon,href,pId);
        if (rows>0){
            return BaseResult.success();
        }else {
            return BaseResult.error(20002,"修改菜单失败");
        }
    }

    @Override
    public String deleteMenu(HttpServletRequest request) {
        Integer id = Integer.parseInt( request.getParameter("id"));
        //判空
        int rows = menuInfoDao.deleteMenuInfoById(id);
        if (rows>0){
            return BaseResult.success();
        }else {
            return BaseResult.error(20002,"菜单删除失败");
        }
    }

    @Override
    public String addMenu(HttpServletRequest request) {
        Integer pId = Integer.parseInt(request.getParameter("pId"));
        String title = request.getParameter("title");
        String icon = request.getParameter("icon");
        String href = request.getParameter("href");
        int rows = menuInfoDao.saveMenu(title, icon, href, pId);
        if (rows>0) {
            return BaseResult.success();
        }
            return BaseResult.error(20003,"菜单新增失败");
    }

    /**
     * 组装菜单树
     * @return
     */
    private List<MenuInfoVo> assemblyMenuTree(Integer userId){
        List<MenuInfo> oneMenuList = menuInfoDao.getMenuInfoListByPid(userId,-1);
        List<MenuInfoVo> menuList = new ArrayList<>();
        //循环遍历一级菜单
        oneMenuList.forEach(menuInfo -> {
            MenuInfoVo menuInfoVo = MenuInfoConvertMenuInfoVo(menuInfo);
            menuInfoVo.setList(menuInfoDao.getMenuInfoListByPid(userId,menuInfoVo.getId()));
            menuList.add(menuInfoVo);
        });
        return menuList;
    }

    /**
     * 将MenuInfo转换为MenuInfoVo
     * @param menuInfo
     * @return
     */
    private MenuInfoVo MenuInfoConvertMenuInfoVo(MenuInfo menuInfo){
        MenuInfoVo menuInfoVo = new MenuInfoVo();
        menuInfoVo.setId(menuInfo.getId());
        menuInfoVo.setTitle(menuInfo.getTitle());
        menuInfoVo.setIcon(menuInfo.getIcon());
        menuInfoVo.setHref(menuInfo.getHref());
        menuInfoVo.setPId(menuInfo.getPId());
        menuInfoVo.setCreateTime(menuInfo.getCreateTime());
        menuInfoVo.setStatus(menuInfo.getStatus());

        return menuInfoVo;

    }
}
