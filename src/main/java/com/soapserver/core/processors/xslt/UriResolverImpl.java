package com.soapserver.core.processors.xslt;

import java.net.URL;

import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.URIResolver;
import javax.xml.transform.stream.StreamSource;

public class UriResolverImpl implements URIResolver {
	
	private final String baseUri;
	
	
	public UriResolverImpl(final String baseUri) {
		this.baseUri = baseUri;
	}
	
	public Source resolve(final String href, final String systemId) throws TransformerException {
		try {
			final URL newUrl;
			if (href.startsWith("/")) { // case when e.g. we open xml-file in xslt using document()
				newUrl = new URL("file:" + href);
			} else {
				newUrl = new URL(baseUri + "/" + href);
			}
			final Source source = new StreamSource(newUrl.openConnection().getInputStream(), newUrl.toExternalForm());
			
			return source;
		} catch (final Exception e) {
			throw new TransformerException(e);
		}
	}

}
