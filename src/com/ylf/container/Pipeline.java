package com.ylf.container;

import com.ylf.servlet.ServletRequest;
import com.ylf.servlet.ServletResponse;

public interface Pipeline {
	public void invoke(ServletRequest req, ServletResponse resp);
	public void addValue(Value value);
	public void removeValue(Value value);
}
