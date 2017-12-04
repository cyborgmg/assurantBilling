
package br.com.delphos.billing.braspag.conciliador;

import java.util.Calendar;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for Event complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Event">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="EventId" use="required" type="{http://microsoft.com/wsdl/types/}guid" />
 *       &lt;attribute name="EventDate" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="CategoryId" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" />
 *       &lt;attribute name="Category" use="required" type="{}EventCategory" />
 *       &lt;attribute name="TypeId" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" />
 *       &lt;attribute name="Type" use="required" type="{}EventType" />
 *       &lt;attribute name="AffiliationCode" use="required" type="{http://www.w3.org/2001/XMLSchema}long" />
 *       &lt;attribute name="TransactionInstallment" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" />
 *       &lt;attribute name="GrossAmount" type="{http://www.w3.org/2001/XMLSchema}long" />
 *       &lt;attribute name="NetAmount" type="{http://www.w3.org/2001/XMLSchema}long" />
 *       &lt;attribute name="TaxAmount" type="{http://www.w3.org/2001/XMLSchema}long" />
 *       &lt;attribute name="Bank" use="required" type="{http://www.w3.org/2001/XMLSchema}short" />
 *       &lt;attribute name="Agency" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="Account" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="AcquirerAdjustCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="AcquirerAdjustDescription" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="AnticipationOperationNumber" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="OriginalPaymentDate" type="{http://www.w3.org/2001/XMLSchema}date" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Event")
public class Event {

    @XmlAttribute(name = "EventId", required = true)
    protected String eventId;
    @XmlAttribute(name = "EventDate", required = true)
    @XmlJavaTypeAdapter(Adapter2 .class)
    @XmlSchemaType(name = "date")
    protected Calendar eventDate;
    @XmlAttribute(name = "CategoryId", required = true)
    @XmlSchemaType(name = "unsignedByte")
    protected short categoryId;
    @XmlAttribute(name = "Category", required = true)
    protected EventCategory category;
    @XmlAttribute(name = "TypeId", required = true)
    @XmlSchemaType(name = "unsignedByte")
    protected short typeId;
    @XmlAttribute(name = "Type", required = true)
    protected EventType type;
    @XmlAttribute(name = "AffiliationCode", required = true)
    protected long affiliationCode;
    @XmlAttribute(name = "TransactionInstallment")
    @XmlSchemaType(name = "unsignedByte")
    protected Short transactionInstallment;
    @XmlAttribute(name = "GrossAmount")
    protected Long grossAmount;
    @XmlAttribute(name = "NetAmount")
    protected Long netAmount;
    @XmlAttribute(name = "TaxAmount")
    protected Long taxAmount;
    @XmlAttribute(name = "Bank", required = true)
    protected short bank;
    @XmlAttribute(name = "Agency", required = true)
    protected int agency;
    @XmlAttribute(name = "Account")
    protected String account;
    @XmlAttribute(name = "AcquirerAdjustCode")
    protected String acquirerAdjustCode;
    @XmlAttribute(name = "AcquirerAdjustDescription")
    protected String acquirerAdjustDescription;
    @XmlAttribute(name = "AnticipationOperationNumber")
    protected Integer anticipationOperationNumber;
    @XmlAttribute(name = "OriginalPaymentDate")
    @XmlJavaTypeAdapter(Adapter2 .class)
    @XmlSchemaType(name = "date")
    protected Calendar originalPaymentDate;

    /**
     * Gets the value of the eventId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventId() {
        return eventId;
    }

    /**
     * Sets the value of the eventId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventId(String value) {
        this.eventId = value;
    }

    /**
     * Gets the value of the eventDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getEventDate() {
        return eventDate;
    }

    /**
     * Sets the value of the eventDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventDate(Calendar value) {
        this.eventDate = value;
    }

    /**
     * Gets the value of the categoryId property.
     * 
     */
    public short getCategoryId() {
        return categoryId;
    }

    /**
     * Sets the value of the categoryId property.
     * 
     */
    public void setCategoryId(short value) {
        this.categoryId = value;
    }

    /**
     * Gets the value of the category property.
     * 
     * @return
     *     possible object is
     *     {@link EventCategory }
     *     
     */
    public EventCategory getCategory() {
        return category;
    }

    /**
     * Sets the value of the category property.
     * 
     * @param value
     *     allowed object is
     *     {@link EventCategory }
     *     
     */
    public void setCategory(EventCategory value) {
        this.category = value;
    }

    /**
     * Gets the value of the typeId property.
     * 
     */
    public short getTypeId() {
        return typeId;
    }

    /**
     * Sets the value of the typeId property.
     * 
     */
    public void setTypeId(short value) {
        this.typeId = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link EventType }
     *     
     */
    public EventType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link EventType }
     *     
     */
    public void setType(EventType value) {
        this.type = value;
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
     * Gets the value of the transactionInstallment property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getTransactionInstallment() {
        return transactionInstallment;
    }

    /**
     * Sets the value of the transactionInstallment property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setTransactionInstallment(Short value) {
        this.transactionInstallment = value;
    }

    /**
     * Gets the value of the grossAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getGrossAmount() {
        return grossAmount;
    }

    /**
     * Sets the value of the grossAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setGrossAmount(Long value) {
        this.grossAmount = value;
    }

    /**
     * Gets the value of the netAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getNetAmount() {
        return netAmount;
    }

    /**
     * Sets the value of the netAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setNetAmount(Long value) {
        this.netAmount = value;
    }

    /**
     * Gets the value of the taxAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTaxAmount() {
        return taxAmount;
    }

    /**
     * Sets the value of the taxAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTaxAmount(Long value) {
        this.taxAmount = value;
    }

    /**
     * Gets the value of the bank property.
     * 
     */
    public short getBank() {
        return bank;
    }

    /**
     * Sets the value of the bank property.
     * 
     */
    public void setBank(short value) {
        this.bank = value;
    }

    /**
     * Gets the value of the agency property.
     * 
     */
    public int getAgency() {
        return agency;
    }

    /**
     * Sets the value of the agency property.
     * 
     */
    public void setAgency(int value) {
        this.agency = value;
    }

    /**
     * Gets the value of the account property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccount() {
        return account;
    }

    /**
     * Sets the value of the account property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccount(String value) {
        this.account = value;
    }

    /**
     * Gets the value of the acquirerAdjustCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcquirerAdjustCode() {
        return acquirerAdjustCode;
    }

    /**
     * Sets the value of the acquirerAdjustCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcquirerAdjustCode(String value) {
        this.acquirerAdjustCode = value;
    }

    /**
     * Gets the value of the acquirerAdjustDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcquirerAdjustDescription() {
        return acquirerAdjustDescription;
    }

    /**
     * Sets the value of the acquirerAdjustDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcquirerAdjustDescription(String value) {
        this.acquirerAdjustDescription = value;
    }

    /**
     * Gets the value of the anticipationOperationNumber property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAnticipationOperationNumber() {
        return anticipationOperationNumber;
    }

    /**
     * Sets the value of the anticipationOperationNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAnticipationOperationNumber(Integer value) {
        this.anticipationOperationNumber = value;
    }

    /**
     * Gets the value of the originalPaymentDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getOriginalPaymentDate() {
        return originalPaymentDate;
    }

    /**
     * Sets the value of the originalPaymentDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOriginalPaymentDate(Calendar value) {
        this.originalPaymentDate = value;
    }

}
