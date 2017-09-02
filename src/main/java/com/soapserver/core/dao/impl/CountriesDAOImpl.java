package com.soapserver.core.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.soapserver.core.dao.CountriesDAO;
import com.soapserver.core.dao.JdbcExt;

public class CountriesDAOImpl extends JdbcExt implements CountriesDAO {
	
	private static final String COUNTRIES_NAMES = "select name from countries";
	
	@Override
	public List<String> getCountriesNames() {
		return getJdbcTemplate().query(COUNTRIES_NAMES, new RowMapper<String>() {
			
			@Override
			public String mapRow(ResultSet rs, int index) throws SQLException {
				return rs.getString("name");
			}
			
		});
	}
}
