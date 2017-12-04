
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
 *         &lt;element name="transactionDataRequest" type="{https://www.pagador.com.br/query/pagadorquery}TransactionDataRequest" minOccurs="0"/>
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
    "transactionDataRequest"
})
@XmlRootElement(name = "GetTransactionData")
public class GetTransactionData {

    protected TransactionDataRequest transactionDataRequest;

    /**
     * Gets the value of the transactionDataRequest property.
     * 
     * @return
     *     possible object is
     *     {@link TransactionDataRequest }
     *     
     */
    public TransactionDataRequest getTransactionDataRequest() {
        return transactionDataRequest;
    }

    /**
     * Sets the value of the transactionDataRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link TransactionDataRequest }
     *     
     */
    public void setTransactionDataRequest(TransactionDataRequest value) {
        this.transactionDataRequest = value;
    }

}
