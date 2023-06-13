package com.web.controller;

import com.web.annotation.Controller;
import com.web.annotation.RequestMapping;
import com.web.annotation.ResponseBody;
import com.web.service.RoleInfoService;
import com.web.service.impl.RoleInfoServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/api/role")
public class RoleInfoController {
    private RoleInfoService roleInfoService = new RoleInfoServiceImpl();
    @RequestMapping("/roleList")
    public String roleList(HttpServletRequest request, HttpServletResponse response){
        roleInfoService.getRoleInfoAllByName1(request);
        return "pages/role_list";
    }
    @RequestMapping("/getRole")
    @ResponseBody
    public String getRoleById(HttpServletRequest request,HttpServletResponse response){
        return roleInfoService.getRoleInfoById(request);
    }
    @RequestMapping("/editRole")
    @ResponseBody
    public String editRole(HttpServletRequest request,HttpServletResponse response){
        return roleInfoService.editRole(request);
    }

    @RequestMapping("/updateRoleStatus")
    @ResponseBody
    public String updateRoleStatus(HttpServletRequest request,HttpServletResponse response){
        return roleInfoService.updateRoleStatus(request);
    }

    @RequestMapping("/enableRoles")
    @ResponseBody
    public String enableRoles(HttpServletRequest request,HttpServletResponse response){
        return roleInfoService.enableRoles(request);
    }
}
