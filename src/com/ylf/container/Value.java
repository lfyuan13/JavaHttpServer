package com.ylf.container;

import com.ylf.servlet.ServletRequest;
import com.ylf.servlet.ServletResponse;

public interface Value {
	public void invoke(ServletRequest req, ServletResponse resp, ValueContext context);

}
