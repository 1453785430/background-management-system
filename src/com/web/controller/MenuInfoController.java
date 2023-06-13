package com.web.controller;

import com.web.annotation.Controller;
import com.web.annotation.RequestMapping;
import com.web.annotation.RequestParameter;
import com.web.annotation.ResponseBody;
import com.web.entity.MenuInfo;
import com.web.service.MenuInfoService;
import com.web.service.impl.MenuInfoServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/api/menu")
public class MenuInfoController {
    private MenuInfoService menuInfoService = new MenuInfoServiceImpl();

    @RequestMapping("/showMenuTree")
    public String showMenuTree(HttpServletRequest request, HttpServletResponse response) {
        return menuInfoService.showMenu(request);
    }

    @RequestMapping("/menuList")
    public String menuList(HttpServletRequest request,HttpServletResponse response) {
        menuInfoService.getMenuList(request);
        return "pages/menu_list";
    }

    @RequestMapping("/getMenu")
    @ResponseBody
    public String getMenu(HttpServletRequest request,HttpServletResponse response) {
        return menuInfoService.getMenu(request);
    }

    @RequestMapping("/edit")
    @ResponseBody
    public String edit(HttpServletRequest request,HttpServletResponse response) {
        return menuInfoService.editMenu(request);
    }

    @RequestMapping("/deleteMenu")
    @ResponseBody
    public String deleteMenu(HttpServletRequest request,HttpServletResponse response) {
        return menuInfoService.deleteMenu(request);
    }

    @RequestMapping("/addMenu")
    @ResponseBody
    public String addMenu(HttpServletRequest request,HttpServletResponse response) {
        return menuInfoService.addMenu(request);
    }
}
