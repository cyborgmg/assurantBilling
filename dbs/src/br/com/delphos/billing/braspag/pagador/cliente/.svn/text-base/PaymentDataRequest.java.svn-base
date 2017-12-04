
package br.com.delphos.billing.braspag.pagador.cliente;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PaymentDataRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PaymentDataRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PaymentMethod" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="Amount" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="Currency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Country" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AdditionalDataCollection" type="{https://www.pagador.com.br/webservice/pagador}ArrayOfAdditionalDataRequest" minOccurs="0"/>
 *         &lt;element name="AffiliationData" type="{https://www.pagador.com.br/webservice/pagador}AffiliationDataRequest" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PaymentDataRequest", propOrder = {
    "paymentMethod",
    "amount",
    "currency",
    "country",
    "additionalDataCollection",
    "affiliationData"
})
@XmlSeeAlso({
    CreditCardDataRequest.class,
    BoletoDataRequest.class,
    OneBuyDataRequest.class,
    OnlineDebitDataRequest.class,
    DebitCardDataRequest.class,
    MobilePaymentDataRequest.class,
    NiquelDataRequest.class
})
public class PaymentDataRequest {

    @XmlElement(name = "PaymentMethod")
    protected short paymentMethod;
    @XmlElement(name = "Amount")
    protected long amount;
    @XmlElement(name = "Currency")
    protected String currency;
    @XmlElement(name = "Country")
    protected String country;
    @XmlElement(name = "AdditionalDataCollection")
    protected ArrayOfAdditionalDataRequest additionalDataCollection;
    @XmlElement(name = "AffiliationData")
    protected AffiliationDataRequest affiliationData;

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
     * Gets the value of the additionalDataCollection property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfAdditionalDataRequest }
     *     
     */
    public ArrayOfAdditionalDataRequest getAdditionalDataCollection() {
        return additionalDataCollection;
    }

    /**
     * Sets the value of the additionalDataCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfAdditionalDataRequest }
     *     
     */
    public void setAdditionalDataCollection(ArrayOfAdditionalDataRequest value) {
        this.additionalDataCollection = value;
    }

    /**
     * Gets the value of the affiliationData property.
     * 
     * @return
     *     possible object is
     *     {@link AffiliationDataRequest }
     *     
     */
    public AffiliationDataRequest getAffiliationData() {
        return affiliationData;
    }

    /**
     * Sets the value of the affiliationData property.
     * 
     * @param value
     *     allowed object is
     *     {@link AffiliationDataRequest }
     *     
     */
    public void setAffiliationData(AffiliationDataRequest value) {
        this.affiliationData = value;
    }

}
