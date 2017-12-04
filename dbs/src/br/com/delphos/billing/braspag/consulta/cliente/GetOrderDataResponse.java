
package br.com.delphos.billing.braspag.consulta.cliente;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element name="GetOrderDataResult" type="{https://www.pagador.com.br/query/pagadorquery}OrderDataResponse" minOccurs="0"/>
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
    "getOrderDataResult"
})
@XmlRootElement(name = "GetOrderDataResponse")
public class GetOrderDataResponse {

    @XmlElement(name = "GetOrderDataResult")
    protected OrderDataResponse getOrderDataResult;

    /**
     * Gets the value of the getOrderDataResult property.
     * 
     * @return
     *     possible object is
     *     {@link OrderDataResponse }
     *     
     */
    public OrderDataResponse getGetOrderDataResult() {
        return getOrderDataResult;
    }

    /**
     * Sets the value of the getOrderDataResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrderDataResponse }
     *     
     */
    public void setGetOrderDataResult(OrderDataResponse value) {
        this.getOrderDataResult = value;
    }

}
