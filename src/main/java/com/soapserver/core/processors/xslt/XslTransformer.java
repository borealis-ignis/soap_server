package com.soapserver.core.processors.xslt;

import java.io.IOException;

import javax.xml.transform.ErrorListener;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

import org.springframework.core.io.ClassPathResource;

public class XslTransformer {
	
	private Transformer transformer;
	
	public XslTransformer(final String xslPath) throws IOException, TransformerConfigurationException {
		final ClassPathResource xslResource = new ClassPathResource(xslPath);
		final Source xsl = new StreamSource(xslResource.getInputStream());
		
		final TransformerFactory factory = TransformerFactory.newInstance();
		transformer = factory.newTransformer(xsl);
		transformer.setErrorListener(new XslErrorListener());
	}
	
	public void transform(Source xmlSource, Result outputTarget) throws TransformerException {
		transformer.transform(xmlSource, outputTarget);
	}
	
	private class XslErrorListener implements ErrorListener {

		@Override
		public void warning(TransformerException exception) throws TransformerException {
			printMessage(exception.getMessage());
		}

		@Override
		public void error(TransformerException exception) throws TransformerException {
			printMessage(exception.getMessage());
		}

		@Override
		public void fatalError(TransformerException exception) throws TransformerException {
			printMessage(exception.getMessage());
		}
		
		private void printMessage(String message) {
			System.out.println(message);
		}
		
	}
	
}
