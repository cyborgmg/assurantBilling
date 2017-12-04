
package br.com.delphos.billing.braspag.pagador.cliente;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AuthorizeTransactionRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AuthorizeTransactionRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{https://www.pagador.com.br/webservice/pagador}AbstractRequest">
 *       &lt;sequence>
 *         &lt;element name="OrderData" type="{https://www.pagador.com.br/webservice/pagador}OrderDataRequest" minOccurs="0"/>
 *         &lt;element name="CustomerData" type="{https://www.pagador.com.br/webservice/pagador}CustomerDataRequest" minOccurs="0"/>
 *         &lt;element name="PaymentDataCollection" type="{https://www.pagador.com.br/webservice/pagador}ArrayOfPaymentDataRequest" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AuthorizeTransactionRequest", propOrder = {
    "orderData",
    "customerData",
    "paymentDataCollection"
})
public class AuthorizeTransactionRequest
    extends AbstractRequest
{

    @XmlElement(name = "OrderData")
    protected OrderDataRequest orderData;
    @XmlElement(name = "CustomerData")
    protected CustomerDataRequest customerData;
    @XmlElement(name = "PaymentDataCollection")
    protected ArrayOfPaymentDataRequest paymentDataCollection;

    /**
     * Gets the value of the orderData property.
     * 
     * @return
     *     possible object is
     *     {@link OrderDataRequest }
     *     
     */
    public OrderDataRequest getOrderData() {
        return orderData;
    }

    /**
     * Sets the value of the orderData property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrderDataRequest }
     *     
     */
    public void setOrderData(OrderDataRequest value) {
        this.orderData = value;
    }

    /**
     * Gets the value of the customerData property.
     * 
     * @return
     *     possible object is
     *     {@link CustomerDataRequest }
     *     
     */
    public CustomerDataRequest getCustomerData() {
        return customerData;
    }

    /**
     * Sets the value of the customerData property.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomerDataRequest }
     *     
     */
    public void setCustomerData(CustomerDataRequest value) {
        this.customerData = value;
    }

    /**
     * Gets the value of the paymentDataCollection property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfPaymentDataRequest }
     *     
     */
    public ArrayOfPaymentDataRequest getPaymentDataCollection() {
        return paymentDataCollection;
    }

    /**
     * Sets the value of the paymentDataCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfPaymentDataRequest }
     *     
     */
    public void setPaymentDataCollection(ArrayOfPaymentDataRequest value) {
        this.paymentDataCollection = value;
    }

}
