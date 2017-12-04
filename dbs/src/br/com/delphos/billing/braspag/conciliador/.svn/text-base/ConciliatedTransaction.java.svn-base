
package br.com.delphos.billing.braspag.conciliador;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ConciliatedTransaction complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConciliatedTransaction">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ManualConciliationData" type="{}ManualConciliationData" minOccurs="0"/>
 *         &lt;element name="SaleData" type="{}SaleTransactionData" minOccurs="0"/>
 *         &lt;element name="AcquirerData" type="{}AcquirerTransactionData" minOccurs="0"/>
 *         &lt;element name="AccountingEvents" type="{}ArrayOfEvent" minOccurs="0"/>
 *         &lt;element name="InformationalEvents" type="{}ArrayOfEvent" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ConciliationTypeId" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" />
 *       &lt;attribute name="ConciliationType" use="required" type="{}ConciliationType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConciliatedTransaction", propOrder = {
    "manualConciliationData",
    "saleData",
    "acquirerData",
    "accountingEvents",
    "informationalEvents"
})
public class ConciliatedTransaction {

    @XmlElement(name = "ManualConciliationData")
    protected ManualConciliationData manualConciliationData;
    @XmlElement(name = "SaleData")
    protected SaleTransactionData saleData;
    @XmlElement(name = "AcquirerData")
    protected AcquirerTransactionData acquirerData;
    @XmlElement(name = "AccountingEvents")
    protected ArrayOfEvent accountingEvents;
    @XmlElement(name = "InformationalEvents")
    protected ArrayOfEvent informationalEvents;
    @XmlAttribute(name = "ConciliationTypeId", required = true)
    @XmlSchemaType(name = "unsignedByte")
    protected short conciliationTypeId;
    @XmlAttribute(name = "ConciliationType", required = true)
    protected ConciliationType conciliationType;

    /**
     * Gets the value of the manualConciliationData property.
     * 
     * @return
     *     possible object is
     *     {@link ManualConciliationData }
     *     
     */
    public ManualConciliationData getManualConciliationData() {
        return manualConciliationData;
    }

    /**
     * Sets the value of the manualConciliationData property.
     * 
     * @param value
     *     allowed object is
     *     {@link ManualConciliationData }
     *     
     */
    public void setManualConciliationData(ManualConciliationData value) {
        this.manualConciliationData = value;
    }

    /**
     * Gets the value of the saleData property.
     * 
     * @return
     *     possible object is
     *     {@link SaleTransactionData }
     *     
     */
    public SaleTransactionData getSaleData() {
        return saleData;
    }

    /**
     * Sets the value of the saleData property.
     * 
     * @param value
     *     allowed object is
     *     {@link SaleTransactionData }
     *     
     */
    public void setSaleData(SaleTransactionData value) {
        this.saleData = value;
    }

    /**
     * Gets the value of the acquirerData property.
     * 
     * @return
     *     possible object is
     *     {@link AcquirerTransactionData }
     *     
     */
    public AcquirerTransactionData getAcquirerData() {
        return acquirerData;
    }

    /**
     * Sets the value of the acquirerData property.
     * 
     * @param value
     *     allowed object is
     *     {@link AcquirerTransactionData }
     *     
     */
    public void setAcquirerData(AcquirerTransactionData value) {
        this.acquirerData = value;
    }

    /**
     * Gets the value of the accountingEvents property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfEvent }
     *     
     */
    public ArrayOfEvent getAccountingEvents() {
        return accountingEvents;
    }

    /**
     * Sets the value of the accountingEvents property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfEvent }
     *     
     */
    public void setAccountingEvents(ArrayOfEvent value) {
        this.accountingEvents = value;
    }

    /**
     * Gets the value of the informationalEvents property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfEvent }
     *     
     */
    public ArrayOfEvent getInformationalEvents() {
        return informationalEvents;
    }

    /**
     * Sets the value of the informationalEvents property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfEvent }
     *     
     */
    public void setInformationalEvents(ArrayOfEvent value) {
        this.informationalEvents = value;
    }

    /**
     * Gets the value of the conciliationTypeId property.
     * 
     */
    public short getConciliationTypeId() {
        return conciliationTypeId;
    }

    /**
     * Sets the value of the conciliationTypeId property.
     * 
     */
    public void setConciliationTypeId(short value) {
        this.conciliationTypeId = value;
    }

    /**
     * Gets the value of the conciliationType property.
     * 
     * @return
     *     possible object is
     *     {@link ConciliationType }
     *     
     */
    public ConciliationType getConciliationType() {
        return conciliationType;
    }

    /**
     * Sets the value of the conciliationType property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConciliationType }
     *     
     */
    public void setConciliationType(ConciliationType value) {
        this.conciliationType = value;
    }

}
