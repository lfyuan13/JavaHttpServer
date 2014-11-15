package com.ylf.container;

import com.ylf.servlet.ServletRequest;
import com.ylf.servlet.ServletResponse;

public class UriLogValue implements Value{

	@Override
	public void invoke(ServletRequest req, ServletResponse resp,
			ValueContext context) {
		System.out.println("[UriLogValue] Uri before ");
		context.invokeNext(req, resp);
		System.out.println("[UriLogValue] Uri after ");
	}

}
