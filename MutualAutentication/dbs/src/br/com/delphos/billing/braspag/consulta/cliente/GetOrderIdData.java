
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
 *         &lt;element name="orderIdDataRequest" type="{https://www.pagador.com.br/query/pagadorquery}OrderIdDataRequest" minOccurs="0"/>
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
    "orderIdDataRequest"
})
@XmlRootElement(name = "GetOrderIdData")
public class GetOrderIdData {

    protected OrderIdDataRequest orderIdDataRequest;

    /**
     * Gets the value of the orderIdDataRequest property.
     * 
     * @return
     *     possible object is
     *     {@link OrderIdDataRequest }
     *     
     */
    public OrderIdDataRequest getOrderIdDataRequest() {
        return orderIdDataRequest;
    }

    /**
     * Sets the value of the orderIdDataRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrderIdDataRequest }
     *     
     */
    public void setOrderIdDataRequest(OrderIdDataRequest value) {
        this.orderIdDataRequest = value;
    }

}
