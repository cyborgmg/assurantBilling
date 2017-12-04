
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
 *         &lt;element name="GetCreditCardDataResult" type="{https://www.pagador.com.br/query/pagadorquery}CreditCardDataResponse" minOccurs="0"/>
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
    "getCreditCardDataResult"
})
@XmlRootElement(name = "GetCreditCardDataResponse")
public class GetCreditCardDataResponse {

    @XmlElement(name = "GetCreditCardDataResult")
    protected CreditCardDataResponse getCreditCardDataResult;

    /**
     * Gets the value of the getCreditCardDataResult property.
     * 
     * @return
     *     possible object is
     *     {@link CreditCardDataResponse }
     *     
     */
    public CreditCardDataResponse getGetCreditCardDataResult() {
        return getCreditCardDataResult;
    }

    /**
     * Sets the value of the getCreditCardDataResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreditCardDataResponse }
     *     
     */
    public void setGetCreditCardDataResult(CreditCardDataResponse value) {
        this.getCreditCardDataResult = value;
    }

}
