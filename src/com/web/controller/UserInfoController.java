package com.web.controller;

import com.web.annotation.Controller;
import com.web.annotation.RequestMapping;
import com.web.annotation.RequestParameter;
import com.web.annotation.ResponseBody;
import com.web.service.UserInfoService;
import com.web.service.impl.UserInfoServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/api/user")
public class UserInfoController {

    private UserInfoService userInfoService = new UserInfoServiceImpl();

    @RequestMapping("/login")
    @ResponseBody
    public String login(HttpServletRequest request, HttpServletResponse response) {
        return userInfoService.login(request);
    }

    @RequestMapping("/userList")
    public String userList(HttpServletRequest request, HttpServletResponse response) {
        userInfoService.getUserInfoAllById(request);
        return "pages/user_list";
    }

    @RequestMapping("/getUser")
    @ResponseBody
    public String getUser(HttpServletRequest request, HttpServletResponse response) {
        return userInfoService.getUserInfoAllId(request);
    }

    @RequestMapping("/editUser")
    @ResponseBody
    public String editUser(HttpServletRequest request, HttpServletResponse response) {
        return userInfoService.editUser(request);
    }

    @RequestMapping("/updateUserStatus")
    @ResponseBody
    public String updateUserStatus(HttpServletRequest request,HttpServletResponse response){
        return userInfoService.updateUserStatus(request);
    }
}
