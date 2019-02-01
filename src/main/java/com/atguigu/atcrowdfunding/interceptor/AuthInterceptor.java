package com.atguigu.atcrowdfunding.interceptor;

import com.atguigu.atcrowdfunding.manager.service.PermissionService;
import com.atguigu.atcrowdfunding.util.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * @Author Elvis Chen
 * @Date 2019/2/1 19:50
 * @Version 1.0
 **/
public class AuthInterceptor extends HandlerInterceptorAdapter{


    @Autowired
    private PermissionService permissionService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //1.查询所有许可
/*        List<Permission> queryAllPermission = permissionService.queryAllPermission();

        Set<String> allURIs = new HashSet<String>();
         for(Permission permission : queryAllPermission){
             allURIs.add("/"+permission.getUrl());
        }*/

        Set<String> allURIs = (Set<String>) request.getSession().getServletContext().getAttribute(Const.ALL_PERMISSION_URI);

        //2.判断请求路径是否在所有许可范围内
        String servletPath = request.getServletPath();
        if (allURIs.contains(servletPath)) {

            //3.判断请求路径是否在用户所拥有的权限内
            Set<String> myURIs =(Set < String >)request.getSession().getAttribute(Const.MY_URIS);
            if (myURIs.contains(servletPath)) {
                return true;
            } else {
                response.sendRedirect(request.getContextPath() + "/login.htm");
                return false;
            }
        }
        return true;

    }
}
