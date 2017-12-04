
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
 *         &lt;element name="addressDataRequest" type="{https://www.pagador.com.br/query/pagadorquery}DeliveryAddressDataRequest" minOccurs="0"/>
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
    "addressDataRequest"
})
@XmlRootElement(name = "GetDeliveryAddressData")
public class GetDeliveryAddressData {

    protected DeliveryAddressDataRequest addressDataRequest;

    /**
     * Gets the value of the addressDataRequest property.
     * 
     * @return
     *     possible object is
     *     {@link DeliveryAddressDataRequest }
     *     
     */
    public DeliveryAddressDataRequest getAddressDataRequest() {
        return addressDataRequest;
    }

    /**
     * Sets the value of the addressDataRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeliveryAddressDataRequest }
     *     
     */
    public void setAddressDataRequest(DeliveryAddressDataRequest value) {
        this.addressDataRequest = value;
    }

}
