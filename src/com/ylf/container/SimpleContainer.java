package com.ylf.container;

import com.ylf.servlet.ServletRequest;
import com.ylf.servlet.ServletResponse;

public class SimpleContainer implements Container{

	Pipeline pipe;
	
	public SimpleContainer(Pipeline pipe){
		this.pipe = pipe;
	}
	
	@Override
	public void invoke(ServletRequest req, ServletResponse resp) {
		pipe.invoke(req, resp);
	}


}
