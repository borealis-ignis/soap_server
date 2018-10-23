package com.soapserver.core.processors;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.dom.DOMSource;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.w3c.dom.Document;

import com.soapserver.core.dao.XmlAbstractDAO;
import com.soapserver.core.filters.PostFilter;
import com.soapserver.core.filters.PreFilter;
import com.soapserver.core.processors.xslt.XslTransformer;

public abstract class AbstractServiceProcessor <RQ, RS> {
	
	private final static ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
	
	private XmlAbstractDAO abstractDAO;
	
	private List<PreFilter<RQ>> preFilters = new ArrayList<>();
	
	private List<PostFilter<RQ, RS>> postFilters = new ArrayList<>();
	
	private String xsl = "";
	
	
	protected abstract RS castResult(final Object response);
	
	protected abstract Marshaller createMarshaller() throws JAXBException;
	
	protected abstract Unmarshaller createUnmarshaller() throws JAXBException;
	
	
	public RS process(final RQ request) throws JAXBException, IOException, TransformerException, ServiceException {
		Session.initSession();
		for (final PreFilter<RQ> preFilter : preFilters) {
			if (preFilter.isApplicable(request)) {
				preFilter.preProcess(request);
			}
		}
		
		final RS response = castResult(xslProcessing(request));
		
		for (final PostFilter<RQ, RS> postFilter : postFilters) {
			if (postFilter.isApplicable(request, response)) {
				postFilter.postProcess(request, response);
			}
		}
		return response;
	}
	
	private Object xslProcessing(final RQ request) throws JAXBException, IOException, TransformerException, ServiceException {
		final String sqlQuery = processRequest(request);
		
		final Session session = Session.getSession();
		if (!session.isRequestOk()) {
			throw new ServiceException(sqlQuery);
		}
		
		final Document xmlDoc = getAbstractDAO().selectDataFromDB(sqlQuery);
		
		return createResponse(xmlDoc);
	}
	
	private String processRequest(final RQ request) throws JAXBException, IOException, TransformerException {
		final Marshaller jaxbMarshaller = createMarshaller();
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		jaxbMarshaller.marshal(request, baos);
		final Source xml = new StreamSource(new ByteArrayInputStream(baos.toByteArray()));
		
		final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		final StreamResult result = new StreamResult(outStream);
		
		final Resource xslResource = resolver.getResource("classpath:/com/soapserver/xslt/rq/" + xsl);
		final XslTransformer transformer = new XslTransformer(xslResource.getURL());
		transformer.transform(xml, result, Collections.<String, Object>emptyMap());
		
		return new String(outStream.toByteArray());
	}
	
	private Object createResponse(final Document xmlDoc) throws IOException, TransformerException, JAXBException {
		final Source xml = new DOMSource(xmlDoc);
		
		
		final Resource xslResource = resolver.getResource("classpath:/com/soapserver/xslt/rs/" + xsl);
		final XslTransformer transformer = new XslTransformer(xslResource.getURL());
		
		final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		final StreamResult result = new StreamResult(outStream);
		
		transformer.transform(xml, result, Collections.<String, Object>emptyMap());
		
		final Unmarshaller jaxbUnmarshaller = createUnmarshaller();
		return jaxbUnmarshaller.unmarshal(new StreamSource(new ByteArrayInputStream(outStream.toByteArray())));
	}
	
	public void setPreFilters(final List<PreFilter<RQ>> preFilters) {
		this.preFilters = preFilters;
	}
	
	public void setPostFilters(final List<PostFilter<RQ,RS>> postFilters) {
		this.postFilters = postFilters;
	}
	
	public void setXsl(String xsl) {
		this.xsl = xsl;
	}
	
	protected XmlAbstractDAO getAbstractDAO() {
		return abstractDAO;
	}
	
	public void setAbstractDAO(XmlAbstractDAO abstractDAO) {
		this.abstractDAO = abstractDAO;
	}
}
