
package br.com.delphos.billing.braspag.pagador.cliente;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AuthorizeTransactionResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AuthorizeTransactionResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{https://www.pagador.com.br/webservice/pagador}AbstractResponse">
 *       &lt;sequence>
 *         &lt;element name="OrderData" type="{https://www.pagador.com.br/webservice/pagador}OrderDataResponse" minOccurs="0"/>
 *         &lt;element name="PaymentDataCollection" type="{https://www.pagador.com.br/webservice/pagador}ArrayOfPaymentDataResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AuthorizeTransactionResponse", propOrder = {
    "orderData",
    "paymentDataCollection"
})
public class AuthorizeTransactionResponse2
    extends AbstractResponse
{

    @XmlElement(name = "OrderData")
    protected OrderDataResponse orderData;
    @XmlElement(name = "PaymentDataCollection")
    protected ArrayOfPaymentDataResponse paymentDataCollection;

    /**
     * Gets the value of the orderData property.
     * 
     * @return
     *     possible object is
     *     {@link OrderDataResponse }
     *     
     */
    public OrderDataResponse getOrderData() {
        return orderData;
    }

    /**
     * Sets the value of the orderData property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrderDataResponse }
     *     
     */
    public void setOrderData(OrderDataResponse value) {
        this.orderData = value;
    }

    /**
     * Gets the value of the paymentDataCollection property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfPaymentDataResponse }
     *     
     */
    public ArrayOfPaymentDataResponse getPaymentDataCollection() {
        return paymentDataCollection;
    }

    /**
     * Sets the value of the paymentDataCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfPaymentDataResponse }
     *     
     */
    public void setPaymentDataCollection(ArrayOfPaymentDataResponse value) {
        this.paymentDataCollection = value;
    }

}
