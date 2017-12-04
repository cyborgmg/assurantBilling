
package br.com.delphos.billing.braspag.pagador.cliente;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PaymentDataResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PaymentDataResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BraspagTransactionId" type="{http://microsoft.com/wsdl/types/}guid"/>
 *         &lt;element name="PaymentMethod" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="Amount" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PaymentDataResponse", propOrder = {
    "braspagTransactionId",
    "paymentMethod",
    "amount"
})
@XmlSeeAlso({
    DebitCardDataResponse.class,
    OnlineDebitDataResponse.class,
    BoletoDataResponse.class,
    NiquelDataResponse.class,
    OneBuyDataResponse.class,
    CreditCardDataResponse.class,
    MobileDataResponse.class
})
public abstract class PaymentDataResponse {

    @XmlElement(name = "BraspagTransactionId", required = true)
    protected String braspagTransactionId;
    @XmlElement(name = "PaymentMethod")
    protected short paymentMethod;
    @XmlElement(name = "Amount")
    protected long amount;

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

}
