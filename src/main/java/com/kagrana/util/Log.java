package com.kagrana.util;

public class Log {
	private boolean debugEnabled = true;

	public boolean isDebugEnabled() {
		return debugEnabled;
	}

	public void setDebugEnabled(boolean debugEnabled) {
		this.debugEnabled = debugEnabled;
	}
	
	public void write(String message){
		if(isDebugEnabled())
			System.out.println(message);
	}
	public void write(Exception e){
		if(isDebugEnabled()){
			writeError(e.getMessage());
			e.printStackTrace();
		}
	}
	public void writeError(String message){
		if(isDebugEnabled())
			System.err.println(message);
	}

}
