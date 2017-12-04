
package br.com.delphos.billing.braspag.consulta.cliente;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OrderIdTransactionResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OrderIdTransactionResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{https://www.pagador.com.br/query/pagadorquery}AbstractResponse">
 *       &lt;sequence>
 *         &lt;element name="BraspagOrderId" type="{http://microsoft.com/wsdl/types/}guid"/>
 *         &lt;element name="BraspagTransactionId" type="{https://www.pagador.com.br/query/pagadorquery}ArrayOfGuid" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrderIdTransactionResponse", propOrder = {
    "braspagOrderId",
    "braspagTransactionId"
})
public class OrderIdTransactionResponse
    extends AbstractResponse
{

    @XmlElement(name = "BraspagOrderId", required = true)
    protected String braspagOrderId;
    @XmlElement(name = "BraspagTransactionId")
    protected ArrayOfGuid braspagTransactionId;

    /**
     * Gets the value of the braspagOrderId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBraspagOrderId() {
        return braspagOrderId;
    }

    /**
     * Sets the value of the braspagOrderId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBraspagOrderId(String value) {
        this.braspagOrderId = value;
    }

    /**
     * Gets the value of the braspagTransactionId property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfGuid }
     *     
     */
    public ArrayOfGuid getBraspagTransactionId() {
        return braspagTransactionId;
    }

    /**
     * Sets the value of the braspagTransactionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfGuid }
     *     
     */
    public void setBraspagTransactionId(ArrayOfGuid value) {
        this.braspagTransactionId = value;
    }

}
