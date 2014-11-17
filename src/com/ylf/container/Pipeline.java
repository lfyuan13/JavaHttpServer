package com.ylf.container;

import com.ylf.lifecycle.Lifecycle;
import com.ylf.logger.Logger;
import com.ylf.servlet.ServletRequest;
import com.ylf.servlet.ServletResponse;

/**
 * PipeLine should extends Lifecycle
 * @author Administrator
 *
 */
public interface Pipeline extends Lifecycle{
	public void invoke(ServletRequest req, ServletResponse resp);
	public void addValue(Value value);
	public void removeValue(Value value);
	
}
