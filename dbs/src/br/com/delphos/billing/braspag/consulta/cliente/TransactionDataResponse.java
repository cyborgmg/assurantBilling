
package br.com.delphos.billing.braspag.consulta.cliente;

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
 *     &lt;extension base="{https://www.pagador.com.br/query/pagadorquery}AbstractResponse">
 *       &lt;sequence>
 *         &lt;element name="BraspagTransactionId" type="{http://microsoft.com/wsdl/types/}guid"/>
 *         &lt;element name="OrderId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AcquirerTransactionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PaymentMethod" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="PaymentMethodName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ErrorCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ErrorMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Amount" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="AuthorizationCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumberOfPayments" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="Currency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Country" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TransactionType" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
 *         &lt;element name="ReceivedDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CapturedDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="VoidedDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CreditCardToken" type="{http://microsoft.com/wsdl/types/}guid"/>
 *         &lt;element name="ProofOfSale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MaskedCardNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TransactionDataResponse", propOrder = {
    "braspagTransactionId",
    "orderId",
    "acquirerTransactionId",
    "paymentMethod",
    "paymentMethodName",
    "errorCode",
    "errorMessage",
    "amount",
    "authorizationCode",
    "numberOfPayments",
    "currency",
    "country",
    "transactionType",
    "status",
    "receivedDate",
    "capturedDate",
    "voidedDate",
    "creditCardToken",
    "proofOfSale",
    "maskedCardNumber"
})
public class TransactionDataResponse
    extends AbstractResponse
{

    @XmlElement(name = "BraspagTransactionId", required = true)
    protected String braspagTransactionId;
    @XmlElement(name = "OrderId")
    protected String orderId;
    @XmlElement(name = "AcquirerTransactionId")
    protected String acquirerTransactionId;
    @XmlElement(name = "PaymentMethod")
    protected short paymentMethod;
    @XmlElement(name = "PaymentMethodName")
    protected String paymentMethodName;
    @XmlElement(name = "ErrorCode")
    protected String errorCode;
    @XmlElement(name = "ErrorMessage")
    protected String errorMessage;
    @XmlElement(name = "Amount")
    protected long amount;
    @XmlElement(name = "AuthorizationCode")
    protected String authorizationCode;
    @XmlElement(name = "NumberOfPayments")
    protected short numberOfPayments;
    @XmlElement(name = "Currency")
    protected String currency;
    @XmlElement(name = "Country")
    protected String country;
    @XmlElement(name = "TransactionType", required = true, type = Short.class, nillable = true)
    @XmlSchemaType(name = "unsignedByte")
    protected Short transactionType;
    @XmlElement(name = "Status")
    @XmlSchemaType(name = "unsignedByte")
    protected short status;
    @XmlElement(name = "ReceivedDate")
    protected String receivedDate;
    @XmlElement(name = "CapturedDate")
    protected String capturedDate;
    @XmlElement(name = "VoidedDate")
    protected String voidedDate;
    @XmlElement(name = "CreditCardToken", required = true, nillable = true)
    protected String creditCardToken;
    @XmlElement(name = "ProofOfSale")
    protected String proofOfSale;
    @XmlElement(name = "MaskedCardNumber")
    protected String maskedCardNumber;

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
     * Gets the value of the orderId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * Sets the value of the orderId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderId(String value) {
        this.orderId = value;
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
     * Gets the value of the paymentMethod property.
     * 
     */
    public short getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * Sets the value of the paymentMethod property.
     * 
     */
    public void setPaymentMethod(short value) {
        this.paymentMethod = value;
    }

    /**
     * Gets the value of the paymentMethodName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentMethodName() {
        return paymentMethodName;
    }

    /**
     * Sets the value of the paymentMethodName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentMethodName(String value) {
        this.paymentMethodName = value;
    }

    /**
     * Gets the value of the errorCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Sets the value of the errorCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorCode(String value) {
        this.errorCode = value;
    }

    /**
     * Gets the value of the errorMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets the value of the errorMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorMessage(String value) {
        this.errorMessage = value;
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
     * Gets the value of the numberOfPayments property.
     * 
     */
    public short getNumberOfPayments() {
        return numberOfPayments;
    }

    /**
     * Sets the value of the numberOfPayments property.
     * 
     */
    public void setNumberOfPayments(short value) {
        this.numberOfPayments = value;
    }

    /**
     * Gets the value of the currency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Sets the value of the currency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrency(String value) {
        this.currency = value;
    }

    /**
     * Gets the value of the country property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the value of the country property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountry(String value) {
        this.country = value;
    }

    /**
     * Gets the value of the transactionType property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getTransactionType() {
        return transactionType;
    }

    /**
     * Sets the value of the transactionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setTransactionType(Short value) {
        this.transactionType = value;
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
     * Gets the value of the receivedDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceivedDate() {
        return receivedDate;
    }

    /**
     * Sets the value of the receivedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceivedDate(String value) {
        this.receivedDate = value;
    }

    /**
     * Gets the value of the capturedDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCapturedDate() {
        return capturedDate;
    }

    /**
     * Sets the value of the capturedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCapturedDate(String value) {
        this.capturedDate = value;
    }

    /**
     * Gets the value of the voidedDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVoidedDate() {
        return voidedDate;
    }

    /**
     * Sets the value of the voidedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVoidedDate(String value) {
        this.voidedDate = value;
    }

    /**
     * Gets the value of the creditCardToken property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditCardToken() {
        return creditCardToken;
    }

    /**
     * Sets the value of the creditCardToken property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditCardToken(String value) {
        this.creditCardToken = value;
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
     * Gets the value of the maskedCardNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaskedCardNumber() {
        return maskedCardNumber;
    }

    /**
     * Sets the value of the maskedCardNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaskedCardNumber(String value) {
        this.maskedCardNumber = value;
    }

}
