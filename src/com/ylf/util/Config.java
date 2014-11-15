package com.ylf.util;

import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.ylf.servlet.Servlet;

public class Config {
	
	private static Config instance;
	private Map<String, Project> map = new HashMap<String, Project>();
	
	public static Config getInstance(){
		if(instance == null){
			synchronized (Config.class) {
				if(instance == null){
					instance = new Config();
					instance.parse();
				}
			}
		}
		return instance;
	}
	
	/**
	 * ���������ļ�
	 * 
	 */
	private void parse(){
		SAXReader reader = new SAXReader();
		try{
			
			Element root = reader.read(new FileInputStream("config.xml")).getDocument().getRootElement();
			// for each project
			for(Object o: root.elements()){
				Element e = (Element)o;
				Project prj = new Project();
				for(Object o2 : e.elements()){
					Element e2 = (Element)o2;
					
					if(e2.getName().equals("name")){
						prj.setName(e2.getText());
					}else if(e2.getName().equals("path")){
						prj.setPath(e2.getText());
					}else if(e2.getName().equals("root")){
						prj.setRoot(e2.getText());
					}else if(e2.getName().equals("servlet")){
						String className = null, url = null;
						for(Object o3 : e2.elements()){
							Element e3 = (Element)o3;
							if(e3.getName().equals("class"))
								className = e3.getText();
							else if(e3.getName().equals("url"))
								url = e3.getText();
						}
						prj.addServlet(url, className);
					}
				}
				map.put(prj.getRoot(), prj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private Config(){}
	
	public Servlet getServlet(String fullUrl){
		int index = fullUrl.indexOf("/", 1);
		if(index < 0)
			return null;
		String root = fullUrl.substring(0, index);
		String url = fullUrl.substring(index);
		return map.get(root).getServlet(url);
	}
	
	@Override
	public String toString() {
		return "Config [map=" + map + "]";
	}

	
	/**
	 * ����xml�����ļ�����Ŀ��
	 * @author Administrator
	 *
	 */
	class Project{
		private String name;
		private String path;
		private String root;
		private Map<String, Servlet> urlServlet = new HashMap<String, Servlet>();  // �洢url-servlet��ϵ
		private URL classPath;  // �洢���·����ΪclassLoader׼��
		
		private URLClassLoader loader;
		
		public Project(){
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			try {
				classPath = new URL("file:" + path);  // �����·��
				if(loader == null)
					loader = new URLClassLoader(new URL[]{classPath});
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			this.path = path;
		}

		public String getRoot() {
			return root;
		}

		public void setRoot(String root) {
			this.root = root;
		}
		
		/**
		 * ����Ԥ�ȼ��غ����е�servlet
		 * ��ȻҲ�������ó��õ�ʱ���ڼ��أ��д�ʵ�֣�����getʱnewInstance()
		 * @param url
		 * @param className
		 */
		public void addServlet(String url, String className){
			try{
				Class cls = loader.loadClass(className);
				urlServlet.put(url, (Servlet)cls.newInstance());  // ���servlet url
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		public Servlet getServlet(String url){
			return urlServlet.get(url);
		}

		@Override
		public String toString() {
			return "Project [name=" + name + ", path=" + path + ", root="
					+ root + ", urlServlet=" + urlServlet + ", classPath="
					+ classPath + ", loader=" + loader + "]";
		}
	}
	
}
