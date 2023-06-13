package com.web.service;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户的业务层方法
 */
public interface UserInfoService {
    /**
     * 用户登陆方法
     * @param request http请求,用于获取用户提交的数据
     * @return json字符串
     */
    String login(HttpServletRequest request);

    void getUserInfoAllById(HttpServletRequest request);

    String getUserInfoAllId(HttpServletRequest request);

    String editUser(HttpServletRequest request);

    String updateUserStatus(HttpServletRequest request);
}
