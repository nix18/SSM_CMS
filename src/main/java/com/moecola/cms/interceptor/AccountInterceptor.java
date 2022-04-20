package com.moecola.cms.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccountInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object arg2) throws Exception {
        System.out.println("INFO：登录拦截器执行");
        if(request.getSession().getAttribute("logined")==null){
            System.out.println("INFO：用户没有登录，需要跳转");
            response.sendRedirect(request.getContextPath() + "/account/relogin");
            return false;
        }
        return true;
    }
}
