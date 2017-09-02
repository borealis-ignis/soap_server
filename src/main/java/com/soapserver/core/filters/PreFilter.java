package com.soapserver.core.filters;

import com.soapserver.core.processors.ServiceException;

public interface PreFilter <RQ> {
	
	public boolean isApplicable(RQ request);
	
	public void preProcess(RQ request) throws ServiceException;
	
}
