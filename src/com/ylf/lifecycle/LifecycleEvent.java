package com.ylf.lifecycle;

public class LifecycleEvent {
	
	private Lifecycle source;
	private int type;
	private Object arg;
	
	public LifecycleEvent(Lifecycle source, int type, Object arg){
		this.source = source;
		this.type = type;
		this.arg = arg;
	}

	public Lifecycle getSource() {
		return source;
	}

	public void setSource(Lifecycle source) {
		this.source = source;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Object getArg() {
		return arg;
	}

	public void setArg(Object arg) {
		this.arg = arg;
	}
	
	
}
