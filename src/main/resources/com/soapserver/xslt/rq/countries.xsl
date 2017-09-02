<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:ent="http://soapserver.com/entities"
	exclude-result-prefixes="ent">
	
	<xsl:output method="text"/>
	
	<xsl:template match="/">
		<xsl:variable name="countryName" select="ent:CountryRequest/ent:Name"/>
		
		<xsl:text>select co.name as country_name, co.currency, ci.name as city_name, ci.active from countries co join cities ci on co.country_id=ci.country_id</xsl:text>
		<xsl:if test="string-length($countryName) &gt; 0">
			<xsl:text> where co.name="</xsl:text>
			<xsl:value-of select="$countryName"/>
			<xsl:text>"</xsl:text>
		</xsl:if>
	</xsl:template>
	
</xsl:stylesheet>