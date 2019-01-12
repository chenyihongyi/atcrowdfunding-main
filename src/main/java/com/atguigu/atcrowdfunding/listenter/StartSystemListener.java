/**
 * 
 */
package com.atguigu.atcrowdfunding.listenter;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

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
	}
	
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		}

	

}
