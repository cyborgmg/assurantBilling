
package br.com.delphos.billing.braspag.pagador.cliente;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DebitCardDataResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DebitCardDataResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{https://www.pagador.com.br/webservice/pagador}PaymentDataResponse">
 *       &lt;sequence>
 *         &lt;element name="AcquirerTransactionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AuthenticationUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ReturnCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ReturnMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
 *         &lt;element name="MaskedCreditCardNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DebitCardDataResponse", propOrder = {
    "acquirerTransactionId",
    "authenticationUrl",
    "returnCode",
    "returnMessage",
    "status",
    "maskedCreditCardNumber"
})
public class DebitCardDataResponse
    extends PaymentDataResponse
{

    @XmlElement(name = "AcquirerTransactionId")
    protected String acquirerTransactionId;
    @XmlElement(name = "AuthenticationUrl")
    protected String authenticationUrl;
    @XmlElement(name = "ReturnCode")
    protected String returnCode;
    @XmlElement(name = "ReturnMessage")
    protected String returnMessage;
    @XmlElement(name = "Status")
    @XmlSchemaType(name = "unsignedByte")
    protected short status;
    @XmlElement(name = "MaskedCreditCardNumber")
    protected String maskedCreditCardNumber;

    /**
     * Gets the value of the acquirerTransactionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcquirerTransactionId() {
        return acquirerTransactionId;
    }

    /**
     * Sets the value of the acquirerTransactionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcquirerTransactionId(String value) {
        this.acquirerTransactionId = value;
    }

    /**
     * Gets the value of the authenticationUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthenticationUrl() {
        return authenticationUrl;
    }

    /**
     * Sets the value of the authenticationUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthenticationUrl(String value) {
        this.authenticationUrl = value;
    }

    /**
     * Gets the value of the returnCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReturnCode() {
        return returnCode;
    }

    /**
     * Sets the value of the returnCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReturnCode(String value) {
        this.returnCode = value;
    }

    /**
     * Gets the value of the returnMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReturnMessage() {
        return returnMessage;
    }

    /**
     * Sets the value of the returnMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReturnMessage(String value) {
        this.returnMessage = value;
    }

    /**
     * Gets the value of the status property.
     * 
     */
    public short getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     */
    public void setStatus(short value) {
        this.status = value;
    }

    /**
     * Gets the value of the maskedCreditCardNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaskedCreditCardNumber() {
        return maskedCreditCardNumber;
    }

    /**
     * Sets the value of the maskedCreditCardNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaskedCreditCardNumber(String value) {
        this.maskedCreditCardNumber = value;
    }

}
