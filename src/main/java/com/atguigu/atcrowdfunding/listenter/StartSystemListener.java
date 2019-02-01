/**
 * 
 */
package com.atguigu.atcrowdfunding.listenter;

import com.atguigu.atcrowdfunding.bean.Permission;
import com.atguigu.atcrowdfunding.manager.service.PermissionService;
import com.atguigu.atcrowdfunding.util.Const;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Description: 
 * @Author: chenyihong
 * @Date: 2019年1月10日
 */
public class StartSystemListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext application = sce.getServletContext();
		String contextPath = application.getContextPath();
		application.setAttribute("APP_PATH", contextPath);
		System.out.println("APP_PATH...");

		//加载所有许可路径
		ApplicationContext ioc = WebApplicationContextUtils.getWebApplicationContext(application);
		PermissionService permissionService = ioc.getBean(PermissionService.class);
		List<Permission> queryAllPermission = permissionService.queryAllPermission();

	Set<String> allURIs = new HashSet<String>();
		for (Permission permission : queryAllPermission) {
		allURIs.add("/" + permission.getUrl());
	}
	application.setAttribute(Const.ALL_PERMISSION_URI, allURIs);
}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		}

	

}
