package com.soapserver.core.dao;

import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;

public interface XmlAbstractDAO {
	
	@Transactional(
		readOnly = true, 
		rollbackFor = Throwable.class
	)
	Document selectDataFromDB(String selectQuery);
	
}
