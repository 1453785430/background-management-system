package com.web.filter;

import com.web.component.BgmsConfig;
import com.web.entity.UserInfo;
import com.web.utils.PropertiesUtil2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/api/*"})
public class Filter_A_CheckSession implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //将servletRequest转为HttpServletRequest对象,方便获取session对象
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (filterRequest(request)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            UserInfo userInfo = (UserInfo) request.getSession().getAttribute(BgmsConfig.SESSION_USER_KEY);
            if (userInfo == null) {
                //用户没有登陆,重定向登录页
                response.sendRedirect("/bgms/");
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
    }

    /**
     * 过滤请求
     *
     * @return 结果为true 就放行, 结果为false就拦截
     */
    private boolean filterRequest(HttpServletRequest request){
        PropertiesUtil2.load("config");
        String excludeUrls = PropertiesUtil2.getValue("exclude.urls");
        String[] urls = excludeUrls.split(",");
        for (String url : urls ){
            if (request.getRequestURI().equals(request.getContextPath()+url)) {
                return true;
            }
        }
        return false;
    }
}
