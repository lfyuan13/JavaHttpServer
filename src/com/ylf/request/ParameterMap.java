package com.ylf.request;

import java.util.HashMap;
import java.util.Map;

/**
 * 继承HashMap的ParameterMap 保护参数
 * @author Administrator
 *
 */
public class ParameterMap extends HashMap<String, String>{

	private boolean locked;
	
	public ParameterMap(){
		super();
	}
	
	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	// override method
	@Override
	public String get(Object key) {
		return super.get(key);
	}

	@Override
	public boolean isEmpty() {
		return super.isEmpty();
	}

	@Override
	public String put(String key, String value) {
		if(locked){
			throw new IllegalAccessError("Invalid method.");
		}
		return super.put(key, value);
	}

	@Override
	public void putAll(Map<? extends String, ? extends String> arg0) {
		if(locked){
			throw new IllegalAccessError("Invalid method.");
		}
		super.putAll(arg0);
	}

	@Override
	public String remove(Object arg0) {
		if(locked){
			throw new IllegalAccessError("Invalid method.");
		}
		return super.remove(arg0);
	}

	@Override
	public int size() {
		return super.size();
	}
	
}
