
package br.com.delphos.billing.braspag.pagador.cliente;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CreditCardDataRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CreditCardDataRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{https://www.pagador.com.br/webservice/pagador}PaymentDataRequest">
 *       &lt;sequence>
 *         &lt;element name="ServiceTaxAmount" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="NumberOfPayments" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="PaymentPlan" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
 *         &lt;element name="TransactionType" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
 *         &lt;element name="CardHolder" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CardNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CardSecurityCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CardExpirationDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CreditCardToken" type="{http://microsoft.com/wsdl/types/}guid"/>
 *         &lt;element name="JustClickAlias" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SaveCreditCard" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ExternalAuthentication" type="{https://www.pagador.com.br/webservice/pagador}ExternalAuthentication" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CreditCardDataRequest", propOrder = {
    "serviceTaxAmount",
    "numberOfPayments",
    "paymentPlan",
    "transactionType",
    "cardHolder",
    "cardNumber",
    "cardSecurityCode",
    "cardExpirationDate",
    "creditCardToken",
    "justClickAlias",
    "saveCreditCard",
    "externalAuthentication"
})
public class CreditCardDataRequest
    extends PaymentDataRequest
{

    @XmlElement(name = "ServiceTaxAmount")
    protected long serviceTaxAmount;
    @XmlElement(name = "NumberOfPayments")
    protected short numberOfPayments;
    @XmlElement(name = "PaymentPlan")
    @XmlSchemaType(name = "unsignedByte")
    protected short paymentPlan;
    @XmlElement(name = "TransactionType")
    @XmlSchemaType(name = "unsignedByte")
    protected short transactionType;
    @XmlElement(name = "CardHolder")
    protected String cardHolder;
    @XmlElement(name = "CardNumber")
    protected String cardNumber;
    @XmlElement(name = "CardSecurityCode")
    protected String cardSecurityCode;
    @XmlElement(name = "CardExpirationDate")
    protected String cardExpirationDate;
    @XmlElement(name = "CreditCardToken", required = true, nillable = true)
    protected String creditCardToken;
    @XmlElement(name = "JustClickAlias")
    protected String justClickAlias;
    @XmlElement(name = "SaveCreditCard", required = true, type = Boolean.class, nillable = true)
    protected Boolean saveCreditCard;
    @XmlElement(name = "ExternalAuthentication")
    protected ExternalAuthentication externalAuthentication;

    /**
     * Gets the value of the serviceTaxAmount property.
     * 
     */
    public long getServiceTaxAmount() {
        return serviceTaxAmount;
    }

    /**
     * Sets the value of the serviceTaxAmount property.
     * 
     */
    public void setServiceTaxAmount(long value) {
        this.serviceTaxAmount = value;
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
     * Gets the value of the paymentPlan property.
     * 
     */
    public short getPaymentPlan() {
        return paymentPlan;
    }

    /**
     * Sets the value of the paymentPlan property.
     * 
     */
    public void setPaymentPlan(short value) {
        this.paymentPlan = value;
    }

    /**
     * Gets the value of the transactionType property.
     * 
     */
    public short getTransactionType() {
        return transactionType;
    }

    /**
     * Sets the value of the transactionType property.
     * 
     */
    public void setTransactionType(short value) {
        this.transactionType = value;
    }

    /**
     * Gets the value of the cardHolder property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardHolder() {
        return cardHolder;
    }

    /**
     * Sets the value of the cardHolder property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardHolder(String value) {
        this.cardHolder = value;
    }

    /**
     * Gets the value of the cardNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * Sets the value of the cardNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardNumber(String value) {
        this.cardNumber = value;
    }

    /**
     * Gets the value of the cardSecurityCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardSecurityCode() {
        return cardSecurityCode;
    }

    /**
     * Sets the value of the cardSecurityCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardSecurityCode(String value) {
        this.cardSecurityCode = value;
    }

    /**
     * Gets the value of the cardExpirationDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardExpirationDate() {
        return cardExpirationDate;
    }

    /**
     * Sets the value of the cardExpirationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardExpirationDate(String value) {
        this.cardExpirationDate = value;
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
     * Gets the value of the justClickAlias property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJustClickAlias() {
        return justClickAlias;
    }

    /**
     * Sets the value of the justClickAlias property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJustClickAlias(String value) {
        this.justClickAlias = value;
    }

    /**
     * Gets the value of the saveCreditCard property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSaveCreditCard() {
        return saveCreditCard;
    }

    /**
     * Sets the value of the saveCreditCard property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSaveCreditCard(Boolean value) {
        this.saveCreditCard = value;
    }

    /**
     * Gets the value of the externalAuthentication property.
     * 
     * @return
     *     possible object is
     *     {@link ExternalAuthentication }
     *     
     */
    public ExternalAuthentication getExternalAuthentication() {
        return externalAuthentication;
    }

    /**
     * Sets the value of the externalAuthentication property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExternalAuthentication }
     *     
     */
    public void setExternalAuthentication(ExternalAuthentication value) {
        this.externalAuthentication = value;
    }

}
