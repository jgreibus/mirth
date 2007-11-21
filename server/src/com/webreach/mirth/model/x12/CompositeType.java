//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1.2-b01-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2007.02.26 at 11:38:19 PM PST 
//


package com.webreach.mirth.model.x12;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for CompositeType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CompositeType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="data_ele" type="{http://www.w3.org/2001/XMLSchema}normalizedString"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}normalizedString"/>
 *         &lt;element name="usage" type="{http://www.w3.org/2001/XMLSchema}normalizedString"/>
 *         &lt;element name="seq" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger"/>
 *         &lt;element name="refdes" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="element" type="{}ElementType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CompositeType", propOrder = {
    "dataEle",
    "name",
    "usage",
    "seq",
    "refdes",
    "element"
})
public class CompositeType {

    @XmlElement(name = "data_ele", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String dataEle;
    @XmlElement(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String name;
    @XmlElement(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String usage;
    @XmlElement(required = true)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger seq;
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String refdes;
    protected List<ElementType> element;

    /**
     * Gets the value of the dataEle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataEle() {
        return dataEle;
    }

    /**
     * Sets the value of the dataEle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataEle(String value) {
        this.dataEle = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the usage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsage() {
        return usage;
    }

    /**
     * Sets the value of the usage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsage(String value) {
        this.usage = value;
    }

    /**
     * Gets the value of the seq property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSeq() {
        return seq;
    }

    /**
     * Sets the value of the seq property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSeq(BigInteger value) {
        this.seq = value;
    }

    /**
     * Gets the value of the refdes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRefdes() {
        return refdes;
    }

    /**
     * Sets the value of the refdes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefdes(String value) {
        this.refdes = value;
    }

    /**
     * Gets the value of the element property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the element property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getElement().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ElementType }
     * 
     * 
     */
    public List<ElementType> getElement() {
        if (element == null) {
            element = new ArrayList<ElementType>();
        }
        return this.element;
    }

}
