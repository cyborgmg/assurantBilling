
package br.com.delphos.billing.braspag.pagador.cliente;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CustomerDataRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CustomerDataRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CustomerIdentity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CustomerIdentityType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CustomerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CustomerEmail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BirthDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CustomerAddressData" type="{https://www.pagador.com.br/webservice/pagador}AddressDataRequest" minOccurs="0"/>
 *         &lt;element name="DeliveryAddressData" type="{https://www.pagador.com.br/webservice/pagador}AddressDataRequest" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustomerDataRequest", propOrder = {
    "customerIdentity",
    "customerIdentityType",
    "customerName",
    "customerEmail",
    "birthDate",
    "customerAddressData",
    "deliveryAddressData"
})
public class CustomerDataRequest {

    @XmlElement(name = "CustomerIdentity")
    protected String customerIdentity;
    @XmlElement(name = "CustomerIdentityType")
    protected String customerIdentityType;
    @XmlElement(name = "CustomerName")
    protected String customerName;
    @XmlElement(name = "CustomerEmail")
    protected String customerEmail;
    @XmlElement(name = "BirthDate")
    protected String birthDate;
    @XmlElement(name = "CustomerAddressData")
    protected AddressDataRequest customerAddressData;
    @XmlElement(name = "DeliveryAddressData")
    protected AddressDataRequest deliveryAddressData;

    /**
     * Gets the value of the customerIdentity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerIdentity() {
        return customerIdentity;
    }

    /**
     * Sets the value of the customerIdentity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerIdentity(String value) {
        this.customerIdentity = value;
    }

    /**
     * Gets the value of the customerIdentityType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerIdentityType() {
        return customerIdentityType;
    }

    /**
     * Sets the value of the customerIdentityType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerIdentityType(String value) {
        this.customerIdentityType = value;
    }

    /**
     * Gets the value of the customerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Sets the value of the customerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerName(String value) {
        this.customerName = value;
    }

    /**
     * Gets the value of the customerEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerEmail() {
        return customerEmail;
    }

    /**
     * Sets the value of the customerEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerEmail(String value) {
        this.customerEmail = value;
    }

    /**
     * Gets the value of the birthDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBirthDate() {
        return birthDate;
    }

    /**
     * Sets the value of the birthDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBirthDate(String value) {
        this.birthDate = value;
    }

    /**
     * Gets the value of the customerAddressData property.
     * 
     * @return
     *     possible object is
     *     {@link AddressDataRequest }
     *     
     */
    public AddressDataRequest getCustomerAddressData() {
        return customerAddressData;
    }

    /**
     * Sets the value of the customerAddressData property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressDataRequest }
     *     
     */
    public void setCustomerAddressData(AddressDataRequest value) {
        this.customerAddressData = value;
    }

    /**
     * Gets the value of the deliveryAddressData property.
     * 
     * @return
     *     possible object is
     *     {@link AddressDataRequest }
     *     
     */
    public AddressDataRequest getDeliveryAddressData() {
        return deliveryAddressData;
    }

    /**
     * Sets the value of the deliveryAddressData property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressDataRequest }
     *     
     */
    public void setDeliveryAddressData(AddressDataRequest value) {
        this.deliveryAddressData = value;
    }

}
