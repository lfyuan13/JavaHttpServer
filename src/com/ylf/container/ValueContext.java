package com.ylf.container;

import com.ylf.servlet.ServletRequest;
import com.ylf.servlet.ServletResponse;

/**
 * just like an iterator
 * @author Administrator
 *
 */
public interface ValueContext {
	public void invokeNext(ServletRequest req, ServletResponse resp);
}
