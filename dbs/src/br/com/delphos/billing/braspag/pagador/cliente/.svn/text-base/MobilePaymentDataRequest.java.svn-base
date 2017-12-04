
package br.com.delphos.billing.braspag.pagador.cliente;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MobilePaymentDataRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MobilePaymentDataRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{https://www.pagador.com.br/webservice/pagador}PaymentDataRequest">
 *       &lt;sequence>
 *         &lt;element name="AreaCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PhoneNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PaymentPlan" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
 *         &lt;element name="TransactionType" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
 *         &lt;element name="NumberOfPayments" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MobilePaymentDataRequest", propOrder = {
    "areaCode",
    "phoneNumber",
    "paymentPlan",
    "transactionType",
    "numberOfPayments"
})
public class MobilePaymentDataRequest
    extends PaymentDataRequest
{

    @XmlElement(name = "AreaCode")
    protected String areaCode;
    @XmlElement(name = "PhoneNumber")
    protected String phoneNumber;
    @XmlElement(name = "PaymentPlan")
    @XmlSchemaType(name = "unsignedByte")
    protected short paymentPlan;
    @XmlElement(name = "TransactionType")
    @XmlSchemaType(name = "unsignedByte")
    protected short transactionType;
    @XmlElement(name = "NumberOfPayments")
    @XmlSchemaType(name = "unsignedByte")
    protected short numberOfPayments;

    /**
     * Gets the value of the areaCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * Sets the value of the areaCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAreaCode(String value) {
        this.areaCode = value;
    }

    /**
     * Gets the value of the phoneNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the value of the phoneNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhoneNumber(String value) {
        this.phoneNumber = value;
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

}
