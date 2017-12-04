
package br.com.delphos.billing.braspag.consulta.cliente;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OrderIdDataResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OrderIdDataResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{https://www.pagador.com.br/query/pagadorquery}AbstractResponse">
 *       &lt;sequence>
 *         &lt;element name="OrderIdDataCollection" type="{https://www.pagador.com.br/query/pagadorquery}ArrayOfOrderIdTransactionResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrderIdDataResponse", propOrder = {
    "orderIdDataCollection"
})
public class OrderIdDataResponse
    extends AbstractResponse
{

    @XmlElement(name = "OrderIdDataCollection")
    protected ArrayOfOrderIdTransactionResponse orderIdDataCollection;

    /**
     * Gets the value of the orderIdDataCollection property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfOrderIdTransactionResponse }
     *     
     */
    public ArrayOfOrderIdTransactionResponse getOrderIdDataCollection() {
        return orderIdDataCollection;
    }

    /**
     * Sets the value of the orderIdDataCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfOrderIdTransactionResponse }
     *     
     */
    public void setOrderIdDataCollection(ArrayOfOrderIdTransactionResponse value) {
        this.orderIdDataCollection = value;
    }

}
