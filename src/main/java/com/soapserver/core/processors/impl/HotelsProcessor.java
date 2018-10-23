package com.soapserver.core.processors.impl;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.soapserver.core.processors.AbstractServiceProcessor;
import com.soapserver.entities.HotelsRequest;
import com.soapserver.entities.HotelsResponse;

public class HotelsProcessor extends AbstractServiceProcessor<HotelsRequest, HotelsResponse> {
	
	@Override
	protected Marshaller createMarshaller() throws JAXBException {
		return JAXBContext.newInstance(HotelsRequest.class).createMarshaller();
	}
	
	@Override
	protected Unmarshaller createUnmarshaller() throws JAXBException {
		return JAXBContext.newInstance(HotelsResponse.class).createUnmarshaller();
	}
	
	@Override
	protected HotelsResponse castResult(final Object response) {
		return (HotelsResponse) response;
	}
	
}
