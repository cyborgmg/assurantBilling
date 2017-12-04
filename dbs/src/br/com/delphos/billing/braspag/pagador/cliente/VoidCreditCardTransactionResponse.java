
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
 *         &lt;element name="VoidCreditCardTransactionResult" type="{https://www.pagador.com.br/webservice/pagador}VoidCreditCardTransactionResponse" minOccurs="0"/>
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
    "voidCreditCardTransactionResult"
})
@XmlRootElement(name = "VoidCreditCardTransactionResponse")
public class VoidCreditCardTransactionResponse {

    @XmlElement(name = "VoidCreditCardTransactionResult")
    protected VoidCreditCardTransactionResponse2 voidCreditCardTransactionResult;

    /**
     * Gets the value of the voidCreditCardTransactionResult property.
     * 
     * @return
     *     possible object is
     *     {@link VoidCreditCardTransactionResponse2 }
     *     
     */
    public VoidCreditCardTransactionResponse2 getVoidCreditCardTransactionResult() {
        return voidCreditCardTransactionResult;
    }

    /**
     * Sets the value of the voidCreditCardTransactionResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link VoidCreditCardTransactionResponse2 }
     *     
     */
    public void setVoidCreditCardTransactionResult(VoidCreditCardTransactionResponse2 value) {
        this.voidCreditCardTransactionResult = value;
    }

}
