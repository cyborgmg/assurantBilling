
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
 *         &lt;element name="creditCardDataRequest" type="{https://www.pagador.com.br/query/pagadorquery}CreditCardDataRequest" minOccurs="0"/>
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
    "creditCardDataRequest"
})
@XmlRootElement(name = "GetCreditCardData")
public class GetCreditCardData {

    protected CreditCardDataRequest creditCardDataRequest;

    /**
     * Gets the value of the creditCardDataRequest property.
     * 
     * @return
     *     possible object is
     *     {@link CreditCardDataRequest }
     *     
     */
    public CreditCardDataRequest getCreditCardDataRequest() {
        return creditCardDataRequest;
    }

    /**
     * Sets the value of the creditCardDataRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreditCardDataRequest }
     *     
     */
    public void setCreditCardDataRequest(CreditCardDataRequest value) {
        this.creditCardDataRequest = value;
    }

}
