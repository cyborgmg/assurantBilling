
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
 *         &lt;element name="RefundCreditCardTransactionResult" type="{https://www.pagador.com.br/webservice/pagador}RefundCreditCardTransactionResponse" minOccurs="0"/>
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
    "refundCreditCardTransactionResult"
})
@XmlRootElement(name = "RefundCreditCardTransactionResponse")
public class RefundCreditCardTransactionResponse {

    @XmlElement(name = "RefundCreditCardTransactionResult")
    protected RefundCreditCardTransactionResponse2 refundCreditCardTransactionResult;

    /**
     * Gets the value of the refundCreditCardTransactionResult property.
     * 
     * @return
     *     possible object is
     *     {@link RefundCreditCardTransactionResponse2 }
     *     
     */
    public RefundCreditCardTransactionResponse2 getRefundCreditCardTransactionResult() {
        return refundCreditCardTransactionResult;
    }

    /**
     * Sets the value of the refundCreditCardTransactionResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link RefundCreditCardTransactionResponse2 }
     *     
     */
    public void setRefundCreditCardTransactionResult(RefundCreditCardTransactionResponse2 value) {
        this.refundCreditCardTransactionResult = value;
    }

}
