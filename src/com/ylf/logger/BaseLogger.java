package com.ylf.logger;

public abstract class BaseLogger implements Logger {

	protected int level;
	
	public BaseLogger(){
		this(Logger.DEBUG);  // default only debug
	}
	
	public BaseLogger(int level){
		this.level = level;
	}
	
	@Override
	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public int getLevel() {
		return level;
	}

	public String getLevelStr(int level){
		switch(level){
		case Logger.FATAL: return "FATAL";
		case Logger.ERROR: return "ERROR";
		case Logger.WARN: return "WARN";
		case Logger.INFO: return "INFO";
		case Logger.DEBUG: return "DEBUG";
		default: return "Unknown";
		}
	}
	
	@Override
	public void log(String msg, int level) {
		if(level <= this.level)
			log("[" + getLevelStr(level) + "] " + msg);
	}

}
