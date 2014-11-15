package com.ylf.servlet;

import com.test.GoodbyeServlet;
import com.test.HelloServlet;
import com.ylf.util.Config;

/**
 * �Ҿ��������Ӧ�þ���ʵ������ servlet Container�����Ĺ�����
 * @author Administrator
 *
 */
public class ServletProcessor {
	
	private Config config;
	public ServletProcessor(){
		config = Config.getInstance();
	}
	
	/**
	 * ����request��uri��Ѱ����Ӧ��class���м���
	 * ��tomcat��������Ӧ�þ���web.xml�����õ�<url-pattern>
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
