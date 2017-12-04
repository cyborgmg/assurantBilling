
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
 *         &lt;element name="additionalDataRequest" type="{https://www.pagador.com.br/query/pagadorquery}AdditionalDataRequest" minOccurs="0"/>
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
    "additionalDataRequest"
})
@XmlRootElement(name = "GetAdditionalData")
public class GetAdditionalData {

    protected AdditionalDataRequest additionalDataRequest;

    /**
     * Gets the value of the additionalDataRequest property.
     * 
     * @return
     *     possible object is
     *     {@link AdditionalDataRequest }
     *     
     */
    public AdditionalDataRequest getAdditionalDataRequest() {
        return additionalDataRequest;
    }

    /**
     * Sets the value of the additionalDataRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link AdditionalDataRequest }
     *     
     */
    public void setAdditionalDataRequest(AdditionalDataRequest value) {
        this.additionalDataRequest = value;
    }

}
