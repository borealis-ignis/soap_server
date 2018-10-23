package com.soapserver.core.helpers;

import com.soapserver.core.processors.Session;

public class RequestUtil {
	
	public static void overrideResponse() {
		final Session session = Session.getSession();
		session.setRequestOk(false);
	}
	
}
