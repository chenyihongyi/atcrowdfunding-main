/**
 * 
 */
package com.atguigu.atcrowdfunding.interceptor;

import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.util.Const;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description: 
 * @Author: chenyihong
 * @Date: 2019年1月30日
 */
public class LoginInterceptor extends HandlerInterceptorAdapter{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1.定义哪些路径是不需要拦截(将这些路径称为白名单)
    	Set<String> uri = new HashSet<String>();
		uri.add("/user/reg.do");
		uri.add("/user/reg.htm");
		uri.add("/login.htm");
		uri.add("/doLogin.do");
		uri.add("/logout.do");

        //获取请求路径
        String servletPath = request.getServletPath();
        if (uri.contains(servletPath)) {
            return true;
        }

        //2.判断用户是否登陆, 如果登陆就放行
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Const.LOGIN_USER);

        if (user != null) {
            return true;
        } else {
            response.sendRedirect(request.getContextPath() + "/login.htm");
            return false;
        }

    }
}
