package com.moecola.cms.interceptor;

import com.moecola.cms.domain.Account;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("INFO：管理员拦截器执行");
        if (request.getSession().getAttribute("logined")!=null) {
            Account account=(Account)request.getSession().getAttribute("logined");
            if(account.getIsAdmin()!=1){
                System.out.println("INFO：用户非管理员，无权访问此页面");
                response.sendRedirect(request.getContextPath() + "/");
                return false;
            }else return true;
        }
        System.out.println("INFO：游客无权访问此页面");
        response.sendRedirect(request.getContextPath() + "/");
        return false;
    }
}
