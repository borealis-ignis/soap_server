<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:ent="http://soapserver.com/entities"
	xmlns:sutil="http://xml.apache.org/xalan/java/com.soapserver.core.helpers.StringUtil"
	xmlns:queryutil="http://xml.apache.org/xalan/java/com.soapserver.core.helpers.SqlQueriesUtil"
	exclude-result-prefixes="ent sutil queryutil">
	
	<xsl:output method="xml"/>
	
	<xsl:template match="/">
		
		<xsl:variable name="currenciesRS" select="queryutil:subQuery('select * from currencies')"/>
		
		<ent:CountryResponse>
			<xsl:for-each select="Result/Entry">
				<xsl:variable name="cityName" select="city_name"/>
				<xsl:variable name="currencyId" select="currency_id"/>
				<xsl:variable name="currency" select="sutil:upperCase(string($currenciesRS/Entry[id = $currencyId]/code))"/>
				
				<ent:Country>
					<ent:Name>
						<xsl:value-of select="country_name"/>
					</ent:Name>
					<ent:Cities>
						<ent:City Name="{$cityName}" />
					</ent:Cities>
					<ent:Currency>
						<xsl:value-of select="$currency"/>
					</ent:Currency>
				</ent:Country>
			</xsl:for-each>
		</ent:CountryResponse>
	</xsl:template>
	
</xsl:stylesheet>