package com.ylf.servlet;

import com.test.GoodbyeServlet;
import com.test.HelloServlet;
import com.ylf.util.Config;

/**
 * 我觉得这个类应该就是实现类似 servlet Container容器的功能了
 * @author Administrator
 *
 */
public class ServletProcessor {
	
	private Config config;
	public ServletProcessor(){
		config = Config.getInstance();
	}
	
	/**
	 * 根据request的uri来寻找相应的class进行加载
	 * 在tomcat里，这个规则应该就是web.xml里配置的<url-pattern>
	 * @param req
	 * @param resp
	 */
	public void process(ServletRequest req, ServletResponse resp){
		
		Servlet servlet = config.getServlet(req.getRequestUri());
		if(servlet == null){
			resp.getWriter().println("404!Not found!");
		}else
			servlet.service(req, resp);
		
		
	}
	
	

}
