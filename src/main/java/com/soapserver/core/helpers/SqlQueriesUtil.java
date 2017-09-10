package com.soapserver.core.helpers;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.soapserver.core.dao.XmlAbstractDAO;

public class SqlQueriesUtil {
	
	private static XmlAbstractDAO xmlAbstractDAO;
	
	public static NodeList subQuery(final String query) {
		final Document doc = xmlAbstractDAO.selectDataFromDB(query);
		
		return doc.getChildNodes();
	}
	
	public void setXmlAbstractDAO(final XmlAbstractDAO xmlAbstractDAO) {
		SqlQueriesUtil.xmlAbstractDAO = xmlAbstractDAO;
	}
	
}
