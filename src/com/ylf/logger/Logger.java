package com.ylf.logger;

public interface Logger {
	public static int EMPTY = -1;
	public static int FATAL = 0;
	public static int ERROR = 1;
	public static int WARN = 2;
	public static int INFO = 3;
	public static int DEBUG = 4;
	
	public void setLevel(int level);
	public int getLevel();
	
	public void log(String msg);
	public void log(String msg, int level);

}
