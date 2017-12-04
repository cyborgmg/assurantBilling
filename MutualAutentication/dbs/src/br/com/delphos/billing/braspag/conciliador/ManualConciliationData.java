
package br.com.delphos.billing.braspag.conciliador;

import java.util.Calendar;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for ManualConciliationData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ManualConciliationData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="ConciliationUserName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ConciliationDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ManualConciliationData")
public class ManualConciliationData {

    @XmlAttribute(name = "ConciliationUserName")
    protected String conciliationUserName;
    @XmlAttribute(name = "ConciliationDateTime")
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar conciliationDateTime;

    /**
     * Gets the value of the conciliationUserName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConciliationUserName() {
        return conciliationUserName;
    }

    /**
     * Sets the value of the conciliationUserName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConciliationUserName(String value) {
        this.conciliationUserName = value;
    }

    /**
     * Gets the value of the conciliationDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getConciliationDateTime() {
        return conciliationDateTime;
    }

    /**
     * Sets the value of the conciliationDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConciliationDateTime(Calendar value) {
        this.conciliationDateTime = value;
    }

}
