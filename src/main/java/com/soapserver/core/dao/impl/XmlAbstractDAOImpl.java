package com.soapserver.core.dao.impl;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import com.sun.org.apache.xerces.internal.dom.DocumentImpl;

import com.soapserver.core.dao.XmlAbstractDAO;
import com.soapserver.core.dao.JdbcExt;

public class XmlAbstractDAOImpl extends JdbcExt implements XmlAbstractDAO {
	
	private final static String ROOT_NODE = "Result";
	
	private final static String ENTRY_NODE = "Entry";
	
	
	public Document selectDataFromDB(final String selectQuery) {
		final List<String> columnsList = new ArrayList<>();
		
		final Document xmlDoc = new DocumentImpl();
		final Element root = xmlDoc.createElement(ROOT_NODE);
		xmlDoc.appendChild(root);
		
		getJdbcTemplate().query(selectQuery, new RowMapper<Object>() {
			@Override
			public Object mapRow(ResultSet rs, int index) throws SQLException {
				if (columnsList.isEmpty()) {
					columnsList.addAll(getColumns(rs));
				}
				
				final Node entry = xmlDoc.createElement(ENTRY_NODE);
				root.appendChild(entry);
				
				for (final String columnName : columnsList) {
					final Node columnElement = xmlDoc.createElement(columnName);
					final Object value = rs.getObject(columnName);
					columnElement.appendChild(xmlDoc.createTextNode(((value == null)? "" : value.toString())));
					entry.appendChild(columnElement);
				}
				
				return null;
			}
		});
		
		return xmlDoc;
	}
	
	private List<String> getColumns(final ResultSet rs) throws SQLException {
		final List<String> columns = new ArrayList<>();
		final ResultSetMetaData meta = rs.getMetaData();
		for (int i = 1; i <= meta.getColumnCount(); i++) {
			columns.add(meta.getColumnLabel(i));
		}
		return columns;
	}
	
}
