package com.soapserver.core.processors.xslt;

import javax.xml.transform.ErrorListener;
import javax.xml.transform.TransformerException;

public class XslErrorListener implements ErrorListener {
	
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
