package com.soapserver.core.filters.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.soapserver.core.filters.PostFilter;
import com.soapserver.entities.CountryRequest;
import com.soapserver.entities.CountryResponse;
import com.soapserver.entities.CountryType;
import com.soapserver.entities.CitiesType;
import com.soapserver.entities.CitiesType.City;

public class GroupCitiesFilter implements PostFilter<CountryRequest, CountryResponse> {

	@Override
	public boolean isApplicable(final CountryRequest request, final CountryResponse response) {
		if (response.getCountry().isEmpty()) {
			return false;
		}
		return true;
	}

	@Override
	public void postProcess(final CountryRequest request, final CountryResponse response) {
		final List<CountryType> countriesDeleteList = new ArrayList<>();
		final Map<String, List<City>> citiesMap = new HashMap<>();
		for (final CountryType country : response.getCountry()) {
			final String countryName = country.getName();
			if (citiesMap.containsKey(countryName)) {
				countriesDeleteList.add(country);
			}
			
			if (country.getCities() == null) {
				continue;
			}
			
			List<City> cities = citiesMap.get(countryName);
			if (cities == null) {
				cities = new ArrayList<>();
				citiesMap.put(countryName, cities);
			}
			cities.addAll(country.getCities().getCity());
		}
		
		response.getCountry().removeAll(countriesDeleteList);
		
		final Iterator<CountryType> countryIterator = response.getCountry().iterator();
		while (countryIterator.hasNext()) {
			final CountryType country = countryIterator.next();
			final CitiesType cities = country.getCities();
			if (cities == null) {
				continue;
			}
			
			cities.getCity().clear();
			
			final List<City> citiesList = citiesMap.get(country.getName());
			if (citiesList != null) {
				cities.getCity().addAll(citiesList);
			}
		}
	}
	
}
