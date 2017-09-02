package com.soapserver.core.filters.impl;

import java.util.List;

import com.soapserver.core.dao.CountriesDAO;
import com.soapserver.core.filters.PreFilter;
import com.soapserver.core.processors.ServiceException;
import com.soapserver.entities.CountryRequest;

public class CheckCountryFilter implements PreFilter<CountryRequest> {
	
	private CountriesDAO countriesDAO;
	
	@Override
	public boolean isApplicable(final CountryRequest request) {
		return true;
	}

	@Override
	public void preProcess(final CountryRequest request) throws ServiceException {
		if (request.getName() != null) {
			final List<String> countryNames = countriesDAO.getCountriesNames();
			if (!countryNames.contains(request.getName())) {
				throw new ServiceException("Requested country name is incorrect");
			}
		}
	}
	
	public void setCountriesDAO(CountriesDAO countriesDAO) {
		this.countriesDAO = countriesDAO;
	}
	
}
