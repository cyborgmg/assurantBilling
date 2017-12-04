
package br.com.delphos.billing.braspag.conciliador;

import java.util.Calendar;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for SaleTransactionData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SaleTransactionData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="TransactionId" use="required" type="{http://microsoft.com/wsdl/types/}guid" />
 *       &lt;attribute name="ExternalId" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="BranchId" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="AffiliationCode" use="required" type="{http://www.w3.org/2001/XMLSchema}long" />
 *       &lt;attribute name="OrderId" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="AuthorizationCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="SaleDate" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="CaptureDate" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="TransactionAmount" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="InstallmentCount" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" />
 *       &lt;attribute name="CustomerName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="CustomerDocument" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="CustomerEmail" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="CardNumber" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Tid" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Nsu" type="{http://www.w3.org/2001/XMLSchema}long" />
 *       &lt;attribute name="IataAmount" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="PaymentMethodName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SaleTransactionData")
public class SaleTransactionData {

    @XmlAttribute(name = "TransactionId", required = true)
    protected String transactionId;
    @XmlAttribute(name = "ExternalId")
    protected String externalId;
    @XmlAttribute(name = "BranchId")
    protected String branchId;
    @XmlAttribute(name = "AffiliationCode", required = true)
    protected long affiliationCode;
    @XmlAttribute(name = "OrderId")
    protected String orderId;
    @XmlAttribute(name = "AuthorizationCode")
    protected String authorizationCode;
    @XmlAttribute(name = "SaleDate")
    @XmlJavaTypeAdapter(Adapter2 .class)
    @XmlSchemaType(name = "date")
    protected Calendar saleDate;
    @XmlAttribute(name = "CaptureDate")
    @XmlJavaTypeAdapter(Adapter2 .class)
    @XmlSchemaType(name = "date")
    protected Calendar captureDate;
    @XmlAttribute(name = "TransactionAmount", required = true)
    protected int transactionAmount;
    @XmlAttribute(name = "InstallmentCount", required = true)
    @XmlSchemaType(name = "unsignedByte")
    protected short installmentCount;
    @XmlAttribute(name = "CustomerName")
    protected String customerName;
    @XmlAttribute(name = "CustomerDocument")
    protected String customerDocument;
    @XmlAttribute(name = "CustomerEmail")
    protected String customerEmail;
    @XmlAttribute(name = "CardNumber")
    protected String cardNumber;
    @XmlAttribute(name = "Tid")
    protected String tid;
    @XmlAttribute(name = "Nsu")
    protected Long nsu;
    @XmlAttribute(name = "IataAmount")
    protected Integer iataAmount;
    @XmlAttribute(name = "PaymentMethodName")
    protected String paymentMethodName;

    /**
     * Gets the value of the transactionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * Sets the value of the transactionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionId(String value) {
        this.transactionId = value;
    }

    /**
     * Gets the value of the externalId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalId() {
        return externalId;
    }

    /**
     * Sets the value of the externalId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalId(String value) {
        this.externalId = value;
    }

    /**
     * Gets the value of the branchId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBranchId() {
        return branchId;
    }

    /**
     * Sets the value of the branchId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBranchId(String value) {
        this.branchId = value;
    }

    /**
     * Gets the value of the affiliationCode property.
     * 
     */
    public long getAffiliationCode() {
        return affiliationCode;
    }

    /**
     * Sets the value of the affiliationCode property.
     * 
     */
    public void setAffiliationCode(long value) {
        this.affiliationCode = value;
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
     * Gets the value of the saleDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getSaleDate() {
        return saleDate;
    }

    /**
     * Sets the value of the saleDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSaleDate(Calendar value) {
        this.saleDate = value;
    }

    /**
     * Gets the value of the captureDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getCaptureDate() {
        return captureDate;
    }

    /**
     * Sets the value of the captureDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCaptureDate(Calendar value) {
        this.captureDate = value;
    }

    /**
     * Gets the value of the transactionAmount property.
     * 
     */
    public int getTransactionAmount() {
        return transactionAmount;
    }

    /**
     * Sets the value of the transactionAmount property.
     * 
     */
    public void setTransactionAmount(int value) {
        this.transactionAmount = value;
    }

    /**
     * Gets the value of the installmentCount property.
     * 
     */
    public short getInstallmentCount() {
        return installmentCount;
    }

    /**
     * Sets the value of the installmentCount property.
     * 
     */
    public void setInstallmentCount(short value) {
        this.installmentCount = value;
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
     * Gets the value of the customerDocument property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerDocument() {
        return customerDocument;
    }

    /**
     * Sets the value of the customerDocument property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerDocument(String value) {
        this.customerDocument = value;
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
     * Gets the value of the tid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTid() {
        return tid;
    }

    /**
     * Sets the value of the tid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTid(String value) {
        this.tid = value;
    }

    /**
     * Gets the value of the nsu property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getNsu() {
        return nsu;
    }

    /**
     * Sets the value of the nsu property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setNsu(Long value) {
        this.nsu = value;
    }

    /**
     * Gets the value of the iataAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIataAmount() {
        return iataAmount;
    }

    /**
     * Sets the value of the iataAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIataAmount(Integer value) {
        this.iataAmount = value;
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

}
