
package br.com.delphos.billing.braspag.conciliador;

import java.util.Calendar;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for AcquirerTransactionData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AcquirerTransactionData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="TransactionId" use="required" type="{http://microsoft.com/wsdl/types/}guid" />
 *       &lt;attribute name="AffiliationCode" use="required" type="{http://www.w3.org/2001/XMLSchema}long" />
 *       &lt;attribute name="SummaryNumber" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="CardNumber" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="SaleDate" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="TransactionGrossAmount" use="required" type="{http://www.w3.org/2001/XMLSchema}long" />
 *       &lt;attribute name="TransactionTaxAmount" type="{http://www.w3.org/2001/XMLSchema}long" />
 *       &lt;attribute name="TransactionNetAmount" use="required" type="{http://www.w3.org/2001/XMLSchema}long" />
 *       &lt;attribute name="RoundingInstallmentGrossAmount" type="{http://www.w3.org/2001/XMLSchema}long" />
 *       &lt;attribute name="RoundingInstallmentTaxAmount" type="{http://www.w3.org/2001/XMLSchema}long" />
 *       &lt;attribute name="RoundingInstallmentNetAmount" type="{http://www.w3.org/2001/XMLSchema}long" />
 *       &lt;attribute name="InstallmentsGrossAmount" type="{http://www.w3.org/2001/XMLSchema}long" />
 *       &lt;attribute name="InstallmentsTaxAmount" type="{http://www.w3.org/2001/XMLSchema}long" />
 *       &lt;attribute name="InstallmentsNetAmount" type="{http://www.w3.org/2001/XMLSchema}long" />
 *       &lt;attribute name="Tax" type="{http://www.w3.org/2001/XMLSchema}short" />
 *       &lt;attribute name="Tid" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Nsu" use="required" type="{http://www.w3.org/2001/XMLSchema}long" />
 *       &lt;attribute name="IataAmount" type="{http://www.w3.org/2001/XMLSchema}long" />
 *       &lt;attribute name="OrderId" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="TerminalLogicNumber" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="CaptureDate" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="SummaryIdentifierNumber" type="{http://www.w3.org/2001/XMLSchema}long" />
 *       &lt;attribute name="InstallmentCount" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" />
 *       &lt;attribute name="AuthorizationCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="CaptureMethodId" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" />
 *       &lt;attribute name="CaptureMethodDescription" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="CardTypeId" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" />
 *       &lt;attribute name="CardType" type="{}CardType" />
 *       &lt;attribute name="ProductIdentifierCode" type="{http://www.w3.org/2001/XMLSchema}short" />
 *       &lt;attribute name="ProductIdentifierDescription" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AcquirerTransactionData")
public class AcquirerTransactionData {

    @XmlAttribute(name = "TransactionId", required = true)
    protected String transactionId;
    @XmlAttribute(name = "AffiliationCode", required = true)
    protected long affiliationCode;
    @XmlAttribute(name = "SummaryNumber")
    protected Integer summaryNumber;
    @XmlAttribute(name = "CardNumber")
    protected String cardNumber;
    @XmlAttribute(name = "SaleDate")
    @XmlJavaTypeAdapter(Adapter2 .class)
    @XmlSchemaType(name = "date")
    protected Calendar saleDate;
    @XmlAttribute(name = "TransactionGrossAmount", required = true)
    protected long transactionGrossAmount;
    @XmlAttribute(name = "TransactionTaxAmount")
    protected Long transactionTaxAmount;
    @XmlAttribute(name = "TransactionNetAmount", required = true)
    protected long transactionNetAmount;
    @XmlAttribute(name = "RoundingInstallmentGrossAmount")
    protected Long roundingInstallmentGrossAmount;
    @XmlAttribute(name = "RoundingInstallmentTaxAmount")
    protected Long roundingInstallmentTaxAmount;
    @XmlAttribute(name = "RoundingInstallmentNetAmount")
    protected Long roundingInstallmentNetAmount;
    @XmlAttribute(name = "InstallmentsGrossAmount")
    protected Long installmentsGrossAmount;
    @XmlAttribute(name = "InstallmentsTaxAmount")
    protected Long installmentsTaxAmount;
    @XmlAttribute(name = "InstallmentsNetAmount")
    protected Long installmentsNetAmount;
    @XmlAttribute(name = "Tax")
    protected Short tax;
    @XmlAttribute(name = "Tid")
    protected String tid;
    @XmlAttribute(name = "Nsu", required = true)
    protected long nsu;
    @XmlAttribute(name = "IataAmount")
    protected Long iataAmount;
    @XmlAttribute(name = "OrderId")
    protected String orderId;
    @XmlAttribute(name = "TerminalLogicNumber")
    protected String terminalLogicNumber;
    @XmlAttribute(name = "CaptureDate", required = true)
    @XmlJavaTypeAdapter(Adapter2 .class)
    @XmlSchemaType(name = "date")
    protected Calendar captureDate;
    @XmlAttribute(name = "SummaryIdentifierNumber")
    protected Long summaryIdentifierNumber;
    @XmlAttribute(name = "InstallmentCount", required = true)
    @XmlSchemaType(name = "unsignedByte")
    protected short installmentCount;
    @XmlAttribute(name = "AuthorizationCode")
    protected String authorizationCode;
    @XmlAttribute(name = "CaptureMethodId")
    @XmlSchemaType(name = "unsignedByte")
    protected Short captureMethodId;
    @XmlAttribute(name = "CaptureMethodDescription")
    protected String captureMethodDescription;
    @XmlAttribute(name = "CardTypeId")
    @XmlSchemaType(name = "unsignedByte")
    protected Short cardTypeId;
    @XmlAttribute(name = "CardType")
    protected CardType cardType;
    @XmlAttribute(name = "ProductIdentifierCode")
    protected Short productIdentifierCode;
    @XmlAttribute(name = "ProductIdentifierDescription")
    protected String productIdentifierDescription;

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
     * Gets the value of the summaryNumber property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSummaryNumber() {
        return summaryNumber;
    }

    /**
     * Sets the value of the summaryNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSummaryNumber(Integer value) {
        this.summaryNumber = value;
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
     * Gets the value of the transactionGrossAmount property.
     * 
     */
    public long getTransactionGrossAmount() {
        return transactionGrossAmount;
    }

    /**
     * Sets the value of the transactionGrossAmount property.
     * 
     */
    public void setTransactionGrossAmount(long value) {
        this.transactionGrossAmount = value;
    }

    /**
     * Gets the value of the transactionTaxAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTransactionTaxAmount() {
        return transactionTaxAmount;
    }

    /**
     * Sets the value of the transactionTaxAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTransactionTaxAmount(Long value) {
        this.transactionTaxAmount = value;
    }

    /**
     * Gets the value of the transactionNetAmount property.
     * 
     */
    public long getTransactionNetAmount() {
        return transactionNetAmount;
    }

    /**
     * Sets the value of the transactionNetAmount property.
     * 
     */
    public void setTransactionNetAmount(long value) {
        this.transactionNetAmount = value;
    }

    /**
     * Gets the value of the roundingInstallmentGrossAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRoundingInstallmentGrossAmount() {
        return roundingInstallmentGrossAmount;
    }

    /**
     * Sets the value of the roundingInstallmentGrossAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRoundingInstallmentGrossAmount(Long value) {
        this.roundingInstallmentGrossAmount = value;
    }

    /**
     * Gets the value of the roundingInstallmentTaxAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRoundingInstallmentTaxAmount() {
        return roundingInstallmentTaxAmount;
    }

    /**
     * Sets the value of the roundingInstallmentTaxAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRoundingInstallmentTaxAmount(Long value) {
        this.roundingInstallmentTaxAmount = value;
    }

    /**
     * Gets the value of the roundingInstallmentNetAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRoundingInstallmentNetAmount() {
        return roundingInstallmentNetAmount;
    }

    /**
     * Sets the value of the roundingInstallmentNetAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRoundingInstallmentNetAmount(Long value) {
        this.roundingInstallmentNetAmount = value;
    }

    /**
     * Gets the value of the installmentsGrossAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getInstallmentsGrossAmount() {
        return installmentsGrossAmount;
    }

    /**
     * Sets the value of the installmentsGrossAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setInstallmentsGrossAmount(Long value) {
        this.installmentsGrossAmount = value;
    }

    /**
     * Gets the value of the installmentsTaxAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getInstallmentsTaxAmount() {
        return installmentsTaxAmount;
    }

    /**
     * Sets the value of the installmentsTaxAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setInstallmentsTaxAmount(Long value) {
        this.installmentsTaxAmount = value;
    }

    /**
     * Gets the value of the installmentsNetAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getInstallmentsNetAmount() {
        return installmentsNetAmount;
    }

    /**
     * Sets the value of the installmentsNetAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setInstallmentsNetAmount(Long value) {
        this.installmentsNetAmount = value;
    }

    /**
     * Gets the value of the tax property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getTax() {
        return tax;
    }

    /**
     * Sets the value of the tax property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setTax(Short value) {
        this.tax = value;
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
     */
    public long getNsu() {
        return nsu;
    }

    /**
     * Sets the value of the nsu property.
     * 
     */
    public void setNsu(long value) {
        this.nsu = value;
    }

    /**
     * Gets the value of the iataAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIataAmount() {
        return iataAmount;
    }

    /**
     * Sets the value of the iataAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIataAmount(Long value) {
        this.iataAmount = value;
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
     * Gets the value of the terminalLogicNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTerminalLogicNumber() {
        return terminalLogicNumber;
    }

    /**
     * Sets the value of the terminalLogicNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTerminalLogicNumber(String value) {
        this.terminalLogicNumber = value;
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
     * Gets the value of the summaryIdentifierNumber property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSummaryIdentifierNumber() {
        return summaryIdentifierNumber;
    }

    /**
     * Sets the value of the summaryIdentifierNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSummaryIdentifierNumber(Long value) {
        this.summaryIdentifierNumber = value;
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
     * Gets the value of the captureMethodId property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getCaptureMethodId() {
        return captureMethodId;
    }

    /**
     * Sets the value of the captureMethodId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setCaptureMethodId(Short value) {
        this.captureMethodId = value;
    }

    /**
     * Gets the value of the captureMethodDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCaptureMethodDescription() {
        return captureMethodDescription;
    }

    /**
     * Sets the value of the captureMethodDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCaptureMethodDescription(String value) {
        this.captureMethodDescription = value;
    }

    /**
     * Gets the value of the cardTypeId property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getCardTypeId() {
        return cardTypeId;
    }

    /**
     * Sets the value of the cardTypeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setCardTypeId(Short value) {
        this.cardTypeId = value;
    }

    /**
     * Gets the value of the cardType property.
     * 
     * @return
     *     possible object is
     *     {@link CardType }
     *     
     */
    public CardType getCardType() {
        return cardType;
    }

    /**
     * Sets the value of the cardType property.
     * 
     * @param value
     *     allowed object is
     *     {@link CardType }
     *     
     */
    public void setCardType(CardType value) {
        this.cardType = value;
    }

    /**
     * Gets the value of the productIdentifierCode property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getProductIdentifierCode() {
        return productIdentifierCode;
    }

    /**
     * Sets the value of the productIdentifierCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setProductIdentifierCode(Short value) {
        this.productIdentifierCode = value;
    }

    /**
     * Gets the value of the productIdentifierDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductIdentifierDescription() {
        return productIdentifierDescription;
    }

    /**
     * Sets the value of the productIdentifierDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductIdentifierDescription(String value) {
        this.productIdentifierDescription = value;
    }

}
