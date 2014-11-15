package com.ylf.container;

import com.ylf.servlet.ServletRequest;
import com.ylf.servlet.ServletResponse;

public class HeaderLogValue implements Value{

	@Override
	public void invoke(ServletRequest req, ServletResponse resp,
			ValueContext context) {
		System.out.println("[HeaderLogValue] Header Before ");
		context.invokeNext(req, resp);
		System.out.println("[HeaderLogValue Header After ");
	}

}
