package com.soapserver.core.processors;

public class Session {
	
	private static ThreadLocal<Session> threadLocal = new ThreadLocal<>();
	
	private boolean isRequestOk = true;
	
	public static void initSession() {
		threadLocal.set(new Session());
	}
	
	public static Session getSession() {
		return threadLocal.get();
	}
	
	public boolean isRequestOk() {
		return isRequestOk;
	}
	
	public void setRequestOk(boolean isRequestOk) {
		this.isRequestOk = isRequestOk;
	}
	
}
