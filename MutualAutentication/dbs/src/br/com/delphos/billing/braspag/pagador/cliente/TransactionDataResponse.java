
package br.com.delphos.billing.braspag.pagador.cliente;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TransactionDataResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TransactionDataResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BraspagTransactionId" type="{http://microsoft.com/wsdl/types/}guid"/>
 *         &lt;element name="AcquirerTransactionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Amount" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="AuthorizationCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ReturnCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ReturnMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
 *         &lt;element name="ProofOfSale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ServiceTaxAmount" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TransactionDataResponse", propOrder = {
    "braspagTransactionId",
    "acquirerTransactionId",
    "amount",
    "authorizationCode",
    "returnCode",
    "returnMessage",
    "status",
    "proofOfSale",
    "serviceTaxAmount"
})
public class TransactionDataResponse {

    @XmlElement(name = "BraspagTransactionId", required = true)
    protected String braspagTransactionId;
    @XmlElement(name = "AcquirerTransactionId")
    protected String acquirerTransactionId;
    @XmlElement(name = "Amount")
    protected long amount;
    @XmlElement(name = "AuthorizationCode")
    protected String authorizationCode;
    @XmlElement(name = "ReturnCode")
    protected String returnCode;
    @XmlElement(name = "ReturnMessage")
    protected String returnMessage;
    @XmlElement(name = "Status")
    @XmlSchemaType(name = "unsignedByte")
    protected short status;
    @XmlElement(name = "ProofOfSale")
    protected String proofOfSale;
    @XmlElement(name = "ServiceTaxAmount")
    protected BigInteger serviceTaxAmount;

    /**
     * Gets the value of the braspagTransactionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBraspagTransactionId() {
        return braspagTransactionId;
    }

    /**
     * Sets the value of the braspagTransactionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBraspagTransactionId(String value) {
        this.braspagTransactionId = value;
    }

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
     * Gets the value of the amount property.
     * 
     */
    public long getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     */
    public void setAmount(long value) {
        this.amount = value;
    }

    /**
     * Gets the value of the authorizationCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthorizationCode() {
        return authorizationCode;
    }

    /**
     * Sets the value of the authorizationCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthorizationCode(String value) {
        this.authorizationCode = value;
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
     * Gets the value of the proofOfSale property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProofOfSale() {
        return proofOfSale;
    }

    /**
     * Sets the value of the proofOfSale property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProofOfSale(String value) {
        this.proofOfSale = value;
    }

    /**
     * Gets the value of the serviceTaxAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getServiceTaxAmount() {
        return serviceTaxAmount;
    }

    /**
     * Sets the value of the serviceTaxAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setServiceTaxAmount(BigInteger value) {
        this.serviceTaxAmount = value;
    }

}
