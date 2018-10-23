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
import com.soapserver.core.processors.impl.HotelsProcessor;
import com.soapserver.entities.CountryRequest;
import com.soapserver.entities.CountryResponse;
import com.soapserver.entities.FaultMessage;
import com.soapserver.entities.HotelsRequest;
import com.soapserver.entities.HotelsResponse;
import com.soapserver.entities.ServicePort;

@Endpoint
public class ServiceEndpoint implements ServicePort {
	
	private static final String NAMESPACE_URI = "http://soapserver.com/entities";
	
	@Autowired
	private CountriesProcessor countriesProcessor;
	
	@Autowired
	private HotelsProcessor hotelsProcessor;
	
	@Override
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "CountryRequest")
	@ResponsePayload
	public CountryResponse processCountryRequest(@RequestPayload CountryRequest countryRequest) throws FaultMessage {
		CountryResponse response = null;
		try {
			response = countriesProcessor.process(countryRequest);
		} catch (JAXBException | IOException | TransformerException e) {
			throw new FaultMessage("Unknown error: " + e.getMessage());
		} catch (ServiceException e) {
			throw new FaultMessage("Service error: " + e.getMessage());
		}
		
		return response;
	}

	/*@Override
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "HotelsRequest")
	@ResponsePayload
	public HotelsResponse processHotelsRequest(@RequestPayload HotelsRequest hotelsRequest) throws FaultMessage {
		HotelsResponse response = null;
		try {
			response = hotelsProcessor.process(hotelsRequest);
		} catch (JAXBException | IOException | TransformerException e) {
			throw new FaultMessage("Unknown error: " + e.getMessage());
		} catch (ServiceException e) {
			throw new FaultMessage("Service error: " + e.getMessage());
		}
		
		return response;
	}*/
}