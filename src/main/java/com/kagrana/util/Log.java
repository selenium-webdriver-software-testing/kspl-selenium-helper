package com.kagrana.util;

import com.kagrana.reporting.ReportingType;

public class Log {
	private boolean debugEnabled = true;
	private ReportingType reportingType;
	public Log(){
		
	}
	public Log(ReportingType reportingType){
		this.reportingType = reportingType;
	}

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
