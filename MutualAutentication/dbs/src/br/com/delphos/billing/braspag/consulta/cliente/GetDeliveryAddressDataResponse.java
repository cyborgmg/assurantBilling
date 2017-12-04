
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
 *         &lt;element name="GetDeliveryAddressDataResult" type="{https://www.pagador.com.br/query/pagadorquery}DeliveryAddressDataResponse" minOccurs="0"/>
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
    "getDeliveryAddressDataResult"
})
@XmlRootElement(name = "GetDeliveryAddressDataResponse")
public class GetDeliveryAddressDataResponse {

    @XmlElement(name = "GetDeliveryAddressDataResult")
    protected DeliveryAddressDataResponse getDeliveryAddressDataResult;

    /**
     * Gets the value of the getDeliveryAddressDataResult property.
     * 
     * @return
     *     possible object is
     *     {@link DeliveryAddressDataResponse }
     *     
     */
    public DeliveryAddressDataResponse getGetDeliveryAddressDataResult() {
        return getDeliveryAddressDataResult;
    }

    /**
     * Sets the value of the getDeliveryAddressDataResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeliveryAddressDataResponse }
     *     
     */
    public void setGetDeliveryAddressDataResult(DeliveryAddressDataResponse value) {
        this.getDeliveryAddressDataResult = value;
    }

}
