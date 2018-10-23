package com.soapserver.core.processors.xslt;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.URIResolver;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.lang.StringUtils;

public class XslTransformer {
	
	private Templates templates;
	
	
	static {
		System.setProperty("javax.xml.transform.TransformerFactory", "org.apache.xalan.xsltc.trax.TransformerFactoryImpl");
	}
	
	
	public XslTransformer(final URL url) throws IOException, TransformerConfigurationException {
		final String styleSheetBaseURI = StringUtils.substringBeforeLast(url.toExternalForm(), "/");
		final URIResolver customURIResolver = new UriResolverImpl(styleSheetBaseURI);
		
		final TransformerFactory transformerFactory = TransformerFactory.newInstance();
		transformerFactory.setURIResolver(customURIResolver);
		
		final String systemId = url.toString();
        final Source source = new StreamSource(url.openConnection().getInputStream(), systemId);
		templates = transformerFactory.newTemplates(source);
	}
	
	public void transform(final Source xmlSource, final Result outputTarget, final Map<String, Object> params) throws TransformerException {
		final Transformer transformer = templates.newTransformer();
		
		for (final Entry<String, Object> entry : params.entrySet()) {
			transformer.setParameter(entry.getKey(), entry.getValue());
		}
		transformer.setErrorListener(new XslErrorListener());
		
		transformer.transform(xmlSource, outputTarget);
	}
	
}
