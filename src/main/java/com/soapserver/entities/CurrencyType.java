//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.08.20 at 07:32:54 PM MSK 
//


package com.soapserver.entities;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CurrencyType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CurrencyType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="GBP"/>
 *     &lt;enumeration value="EUR"/>
 *     &lt;enumeration value="PLN"/>
 *     &lt;enumeration value="BYN"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CurrencyType")
@XmlEnum
public enum CurrencyType {

    GBP,
    EUR,
    PLN,
    BYN;

    public String value() {
        return name();
    }

    public static CurrencyType fromValue(String v) {
        return valueOf(v);
    }

}
