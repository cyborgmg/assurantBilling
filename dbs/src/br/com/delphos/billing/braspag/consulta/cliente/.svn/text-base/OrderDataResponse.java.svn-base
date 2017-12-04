
package br.com.delphos.billing.braspag.consulta.cliente;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OrderDataResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OrderDataResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{https://www.pagador.com.br/query/pagadorquery}AbstractResponse">
 *       &lt;sequence>
 *         &lt;element name="TransactionDataCollection" type="{https://www.pagador.com.br/query/pagadorquery}ArrayOfOrderTransactionDataResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrderDataResponse", propOrder = {
    "transactionDataCollection"
})
public class OrderDataResponse
    extends AbstractResponse
{

    @XmlElement(name = "TransactionDataCollection")
    protected ArrayOfOrderTransactionDataResponse transactionDataCollection;

    /**
     * Gets the value of the transactionDataCollection property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfOrderTransactionDataResponse }
     *     
     */
    public ArrayOfOrderTransactionDataResponse getTransactionDataCollection() {
        return transactionDataCollection;
    }

    /**
     * Sets the value of the transactionDataCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfOrderTransactionDataResponse }
     *     
     */
    public void setTransactionDataCollection(ArrayOfOrderTransactionDataResponse value) {
        this.transactionDataCollection = value;
    }

}
