package com.soapserver.core.processors.impl;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.soapserver.core.processors.AbstractServiceProcessor;
import com.soapserver.entities.CountryRequest;
import com.soapserver.entities.CountryResponse;

public class CountriesProcessor extends AbstractServiceProcessor<CountryRequest, CountryResponse> {
	
	@Override
	protected Marshaller createMarshaller() throws JAXBException {
		return JAXBContext.newInstance(CountryRequest.class).createMarshaller();
	}
	
	@Override
	protected Unmarshaller createUnmarshaller() throws JAXBException {
		return JAXBContext.newInstance(CountryResponse.class).createUnmarshaller();
	}
	
	@Override
	protected CountryResponse castResult(final Object response) {
		return (CountryResponse) response;
	}
	
}
