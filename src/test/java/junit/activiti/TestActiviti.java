/**
 * 
 */
package junit.activiti;

import org.activiti.engine.ProcessEngine;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;




/**
 * @Description: 
 * @Author: chenyihong
 * @Date: 2019年2月3日
 */
public class TestActiviti {

	ApplicationContext ioc = new ClassPathXmlApplicationContext("spring/spring-*.xml");
	
	@Test
	public void test01(){
		ProcessEngine processEngine = (ProcessEngine)ioc.getBean("processEngine");
		
		System.out.println("processEngine"+processEngine);
		
	}
}
