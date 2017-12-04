
package br.com.delphos.billing.braspag.pagador.cliente;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OneBuyDataRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OneBuyDataRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{https://www.pagador.com.br/webservice/pagador}PaymentDataRequest">
 *       &lt;sequence>
 *         &lt;element name="TokenOneBuy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumberOfPayments" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="PaymentPlan" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
 *         &lt;element name="NumeroCheckout" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OneBuyDataRequest", propOrder = {
    "tokenOneBuy",
    "numberOfPayments",
    "paymentPlan",
    "numeroCheckout"
})
public class OneBuyDataRequest
    extends PaymentDataRequest
{

    @XmlElement(name = "TokenOneBuy")
    protected String tokenOneBuy;
    @XmlElement(name = "NumberOfPayments")
    protected short numberOfPayments;
    @XmlElement(name = "PaymentPlan")
    @XmlSchemaType(name = "unsignedByte")
    protected short paymentPlan;
    @XmlElement(name = "NumeroCheckout")
    protected String numeroCheckout;

    /**
     * Gets the value of the tokenOneBuy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTokenOneBuy() {
        return tokenOneBuy;
    }

    /**
     * Sets the value of the tokenOneBuy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTokenOneBuy(String value) {
        this.tokenOneBuy = value;
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
     * Gets the value of the numeroCheckout property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroCheckout() {
        return numeroCheckout;
    }

    /**
     * Sets the value of the numeroCheckout property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroCheckout(String value) {
        this.numeroCheckout = value;
    }

}
