package com.soapserver.core.endpoints;

import java.io.IOException;

import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.soapserver.core.processors.ServiceException;
import com.soapserver.core.processors.impl.CountriesProcessor;
import com.soapserver.entities.CountryRequest;
import com.soapserver.entities.CountryResponse;

@Endpoint
public class CountryEndpoint {
	
	private static final String NAMESPACE_URI = "http://soapserver.com/entities";
	
	@Autowired
	private CountriesProcessor countriesProcessor;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "CountryRequest")
	@ResponsePayload
	public CountryResponse getCountry(@RequestPayload CountryRequest request) throws ServiceException {
		CountryResponse response = null;
		try {
			response = countriesProcessor.process(request);
		} catch (JAXBException | IOException | TransformerException e) {
			e.printStackTrace();
		}
		
		return response;
	}
	
}