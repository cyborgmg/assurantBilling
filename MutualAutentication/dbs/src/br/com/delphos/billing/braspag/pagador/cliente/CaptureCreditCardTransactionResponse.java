
package br.com.delphos.billing.braspag.pagador.cliente;

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
 *         &lt;element name="CaptureCreditCardTransactionResult" type="{https://www.pagador.com.br/webservice/pagador}CaptureCreditCardTransactionResponse" minOccurs="0"/>
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
    "captureCreditCardTransactionResult"
})
@XmlRootElement(name = "CaptureCreditCardTransactionResponse")
public class CaptureCreditCardTransactionResponse {

    @XmlElement(name = "CaptureCreditCardTransactionResult")
    protected CaptureCreditCardTransactionResponse2 captureCreditCardTransactionResult;

    /**
     * Gets the value of the captureCreditCardTransactionResult property.
     * 
     * @return
     *     possible object is
     *     {@link CaptureCreditCardTransactionResponse2 }
     *     
     */
    public CaptureCreditCardTransactionResponse2 getCaptureCreditCardTransactionResult() {
        return captureCreditCardTransactionResult;
    }

    /**
     * Sets the value of the captureCreditCardTransactionResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link CaptureCreditCardTransactionResponse2 }
     *     
     */
    public void setCaptureCreditCardTransactionResult(CaptureCreditCardTransactionResponse2 value) {
        this.captureCreditCardTransactionResult = value;
    }

}
