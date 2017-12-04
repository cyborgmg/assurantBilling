
package br.com.delphos.billing.braspag.pagador.cliente;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CaptureCreditCardTransactionResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CaptureCreditCardTransactionResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{https://www.pagador.com.br/webservice/pagador}AbstractResponse">
 *       &lt;sequence>
 *         &lt;element name="TransactionDataCollection" type="{https://www.pagador.com.br/webservice/pagador}ArrayOfTransactionDataResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CaptureCreditCardTransactionResponse", propOrder = {
    "transactionDataCollection"
})
public class CaptureCreditCardTransactionResponse2
    extends AbstractResponse
{

    @XmlElement(name = "TransactionDataCollection")
    protected ArrayOfTransactionDataResponse transactionDataCollection;

    /**
     * Gets the value of the transactionDataCollection property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfTransactionDataResponse }
     *     
     */
    public ArrayOfTransactionDataResponse getTransactionDataCollection() {
        return transactionDataCollection;
    }

    /**
     * Sets the value of the transactionDataCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfTransactionDataResponse }
     *     
     */
    public void setTransactionDataCollection(ArrayOfTransactionDataResponse value) {
        this.transactionDataCollection = value;
    }

}
