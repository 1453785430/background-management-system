package com.web.filter;

import com.web.component.BgmsConfig;
import com.web.entity.UserInfo;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebFilter(urlPatterns = {"/*"})
public class Filter_B_CheckSession implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //将servletRequest转为HttpServletRequest对象,方便获取session对象
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if ("/bgms/".equals(request.getRequestURI())){
            UserInfo userInfo = (UserInfo) request.getSession().getAttribute(BgmsConfig.SESSION_USER_KEY);
            if (userInfo == null){
                filterChain.doFilter(servletRequest,servletResponse);

            }else {
                response.sendRedirect(request.getContextPath()+"/api/menu/showMenuTree");
            }
        }else {
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }
}
