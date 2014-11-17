package com.ylf.logger;

import com.ylf.util.TimeUtil;

public class SystemOutLogger extends BaseLogger{

	public SystemOutLogger(){
		super();
	}
	
	public SystemOutLogger(int level){
		super(level);
	}
	@Override
	public void log(String msg) {
		System.out.println(TimeUtil.getFormatTime() + " " + msg);
	}

}
