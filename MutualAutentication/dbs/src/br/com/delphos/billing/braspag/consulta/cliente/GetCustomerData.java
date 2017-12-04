
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
 *         &lt;element name="customerDataRequest" type="{https://www.pagador.com.br/query/pagadorquery}CustomerDataRequest" minOccurs="0"/>
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
    "customerDataRequest"
})
@XmlRootElement(name = "GetCustomerData")
public class GetCustomerData {

    protected CustomerDataRequest customerDataRequest;

    /**
     * Gets the value of the customerDataRequest property.
     * 
     * @return
     *     possible object is
     *     {@link CustomerDataRequest }
     *     
     */
    public CustomerDataRequest getCustomerDataRequest() {
        return customerDataRequest;
    }

    /**
     * Sets the value of the customerDataRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomerDataRequest }
     *     
     */
    public void setCustomerDataRequest(CustomerDataRequest value) {
        this.customerDataRequest = value;
    }

}
