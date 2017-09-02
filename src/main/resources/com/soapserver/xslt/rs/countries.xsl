<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:ent="http://soapserver.com/entities"
	exclude-result-prefixes="ent">
	
	<xsl:output method="xml"/>
	
	<xsl:template match="/">
		<ent:CountryResponse>
			<xsl:for-each select="Result/Entry">
				<xsl:variable name="cityName" select="city_name"/>
				
				<ent:Country>
					<ent:Name>
						<xsl:value-of select="country_name"/>
					</ent:Name>
					<ent:Cities>
						<ent:City Name="{$cityName}" />
					</ent:Cities>
					<ent:Currency>
						<xsl:value-of select="currency"/>
					</ent:Currency>
				</ent:Country>
			</xsl:for-each>
		</ent:CountryResponse>
	</xsl:template>
	
</xsl:stylesheet>