
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
 *         &lt;element name="GetOrderIdDataResult" type="{https://www.pagador.com.br/query/pagadorquery}OrderIdDataResponse" minOccurs="0"/>
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
    "getOrderIdDataResult"
})
@XmlRootElement(name = "GetOrderIdDataResponse")
public class GetOrderIdDataResponse {

    @XmlElement(name = "GetOrderIdDataResult")
    protected OrderIdDataResponse getOrderIdDataResult;

    /**
     * Gets the value of the getOrderIdDataResult property.
     * 
     * @return
     *     possible object is
     *     {@link OrderIdDataResponse }
     *     
     */
    public OrderIdDataResponse getGetOrderIdDataResult() {
        return getOrderIdDataResult;
    }

    /**
     * Sets the value of the getOrderIdDataResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrderIdDataResponse }
     *     
     */
    public void setGetOrderIdDataResult(OrderIdDataResponse value) {
        this.getOrderIdDataResult = value;
    }

}
