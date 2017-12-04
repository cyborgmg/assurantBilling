
package br.com.delphos.billing.braspag.conciliador.cliente;

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
 *         &lt;element name="SendTransactionFileResult" type="{https://reconciliation.braspag.com.br}StoreTransactionFileResponse" minOccurs="0"/>
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
    "sendTransactionFileResult"
})
@XmlRootElement(name = "SendTransactionFileResponse")
public class SendTransactionFileResponse {

    @XmlElement(name = "SendTransactionFileResult")
    protected StoreTransactionFileResponse sendTransactionFileResult;

    /**
     * Gets the value of the sendTransactionFileResult property.
     * 
     * @return
     *     possible object is
     *     {@link StoreTransactionFileResponse }
     *     
     */
    public StoreTransactionFileResponse getSendTransactionFileResult() {
        return sendTransactionFileResult;
    }

    /**
     * Sets the value of the sendTransactionFileResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link StoreTransactionFileResponse }
     *     
     */
    public void setSendTransactionFileResult(StoreTransactionFileResponse value) {
        this.sendTransactionFileResult = value;
    }

}
