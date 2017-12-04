
package br.com.delphos.billing.braspag.consulta.cliente;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="orderDataRequest" type="{https://www.pagador.com.br/query/pagadorquery}OrderDataRequest" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "orderDataRequest"
})
@XmlRootElement(name = "GetOrderData")
public class GetOrderData {

    protected OrderDataRequest orderDataRequest;

    /**
     * Gets the value of the orderDataRequest property.
     * 
     * @return
     *     possible object is
     *     {@link OrderDataRequest }
     *     
     */
    public OrderDataRequest getOrderDataRequest() {
        return orderDataRequest;
    }

    /**
     * Sets the value of the orderDataRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrderDataRequest }
     *     
     */
    public void setOrderDataRequest(OrderDataRequest value) {
        this.orderDataRequest = value;
    }

}
