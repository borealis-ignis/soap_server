package com.soapserver.core.filters;

import com.soapserver.core.processors.ServiceException;

public interface PostFilter <RQ, RS> {
	
	public boolean isApplicable(RQ request, RS response);
	
	public void postProcess(RQ request, RS response) throws ServiceException;
	
}
