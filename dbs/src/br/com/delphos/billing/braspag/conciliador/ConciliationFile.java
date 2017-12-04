
package br.com.delphos.billing.braspag.conciliador;

import java.util.Calendar;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for ConciliationFile complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConciliationFile">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ConciliatedTransactions" type="{}ArrayOfConciliatedTransaction" minOccurs="0"/>
 *         &lt;element name="AffiliationAccountingEvents" type="{}ArrayOfEvent" minOccurs="0"/>
 *         &lt;element name="AffiliationInformationalEvents" type="{}ArrayOfEvent" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="MerchantId" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="AcquirerId" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" />
 *       &lt;attribute name="GenerationDateTime" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="StartPeriod" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="EndPeriod" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="SequentialNumber" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="ProcessingTypeId" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="ProcessingType" use="required" type="{}ProcessingType" />
 *       &lt;attribute name="Version" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConciliationFile", propOrder = {
    "conciliatedTransactions",
    "affiliationAccountingEvents",
    "affiliationInformationalEvents"
})
public class ConciliationFile {

    @XmlElement(name = "ConciliatedTransactions")
    protected ArrayOfConciliatedTransaction conciliatedTransactions;
    @XmlElement(name = "AffiliationAccountingEvents")
    protected ArrayOfEvent affiliationAccountingEvents;
    @XmlElement(name = "AffiliationInformationalEvents")
    protected ArrayOfEvent affiliationInformationalEvents;
    @XmlAttribute(name = "MerchantId", required = true)
    protected int merchantId;
    @XmlAttribute(name = "AcquirerId", required = true)
    @XmlSchemaType(name = "unsignedByte")
    protected short acquirerId;
    @XmlAttribute(name = "GenerationDateTime", required = true)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar generationDateTime;
    @XmlAttribute(name = "StartPeriod", required = true)
    @XmlJavaTypeAdapter(Adapter2 .class)
    @XmlSchemaType(name = "date")
    protected Calendar startPeriod;
    @XmlAttribute(name = "EndPeriod", required = true)
    @XmlJavaTypeAdapter(Adapter2 .class)
    @XmlSchemaType(name = "date")
    protected Calendar endPeriod;
    @XmlAttribute(name = "SequentialNumber")
    protected Integer sequentialNumber;
    @XmlAttribute(name = "ProcessingTypeId", required = true)
    protected int processingTypeId;
    @XmlAttribute(name = "ProcessingType", required = true)
    protected ProcessingType processingType;
    @XmlAttribute(name = "Version")
    protected String version;

    /**
     * Gets the value of the conciliatedTransactions property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfConciliatedTransaction }
     *     
     */
    public ArrayOfConciliatedTransaction getConciliatedTransactions() {
        return conciliatedTransactions;
    }

    /**
     * Sets the value of the conciliatedTransactions property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfConciliatedTransaction }
     *     
     */
    public void setConciliatedTransactions(ArrayOfConciliatedTransaction value) {
        this.conciliatedTransactions = value;
    }

    /**
     * Gets the value of the affiliationAccountingEvents property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfEvent }
     *     
     */
    public ArrayOfEvent getAffiliationAccountingEvents() {
        return affiliationAccountingEvents;
    }

    /**
     * Sets the value of the affiliationAccountingEvents property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfEvent }
     *     
     */
    public void setAffiliationAccountingEvents(ArrayOfEvent value) {
        this.affiliationAccountingEvents = value;
    }

    /**
     * Gets the value of the affiliationInformationalEvents property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfEvent }
     *     
     */
    public ArrayOfEvent getAffiliationInformationalEvents() {
        return affiliationInformationalEvents;
    }

    /**
     * Sets the value of the affiliationInformationalEvents property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfEvent }
     *     
     */
    public void setAffiliationInformationalEvents(ArrayOfEvent value) {
        this.affiliationInformationalEvents = value;
    }

    /**
     * Gets the value of the merchantId property.
     * 
     */
    public int getMerchantId() {
        return merchantId;
    }

    /**
     * Sets the value of the merchantId property.
     * 
     */
    public void setMerchantId(int value) {
        this.merchantId = value;
    }

    /**
     * Gets the value of the acquirerId property.
     * 
     */
    public short getAcquirerId() {
        return acquirerId;
    }

    /**
     * Sets the value of the acquirerId property.
     * 
     */
    public void setAcquirerId(short value) {
        this.acquirerId = value;
    }

    /**
     * Gets the value of the generationDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getGenerationDateTime() {
        return generationDateTime;
    }

    /**
     * Sets the value of the generationDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGenerationDateTime(Calendar value) {
        this.generationDateTime = value;
    }

    /**
     * Gets the value of the startPeriod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getStartPeriod() {
        return startPeriod;
    }

    /**
     * Sets the value of the startPeriod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartPeriod(Calendar value) {
        this.startPeriod = value;
    }

    /**
     * Gets the value of the endPeriod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getEndPeriod() {
        return endPeriod;
    }

    /**
     * Sets the value of the endPeriod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndPeriod(Calendar value) {
        this.endPeriod = value;
    }

    /**
     * Gets the value of the sequentialNumber property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSequentialNumber() {
        return sequentialNumber;
    }

    /**
     * Sets the value of the sequentialNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSequentialNumber(Integer value) {
        this.sequentialNumber = value;
    }

    /**
     * Gets the value of the processingTypeId property.
     * 
     */
    public int getProcessingTypeId() {
        return processingTypeId;
    }

    /**
     * Sets the value of the processingTypeId property.
     * 
     */
    public void setProcessingTypeId(int value) {
        this.processingTypeId = value;
    }

    /**
     * Gets the value of the processingType property.
     * 
     * @return
     *     possible object is
     *     {@link ProcessingType }
     *     
     */
    public ProcessingType getProcessingType() {
        return processingType;
    }

    /**
     * Sets the value of the processingType property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProcessingType }
     *     
     */
    public void setProcessingType(ProcessingType value) {
        this.processingType = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

}
