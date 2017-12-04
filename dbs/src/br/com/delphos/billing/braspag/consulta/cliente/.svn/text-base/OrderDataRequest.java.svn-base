
package br.com.delphos.billing.braspag.consulta.cliente;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OrderDataRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OrderDataRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{https://www.pagador.com.br/query/pagadorquery}AbstractRequest">
 *       &lt;sequence>
 *         &lt;element name="MerchantId" type="{http://microsoft.com/wsdl/types/}guid"/>
 *         &lt;element name="BraspagOrderId" type="{http://microsoft.com/wsdl/types/}guid"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrderDataRequest", propOrder = {
    "merchantId",
    "braspagOrderId"
})
public class OrderDataRequest
    extends AbstractRequest
{

    @XmlElement(name = "MerchantId", required = true)
    protected String merchantId;
    @XmlElement(name = "BraspagOrderId", required = true)
    protected String braspagOrderId;

    /**
     * Gets the value of the merchantId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMerchantId() {
        return merchantId;
    }

    /**
     * Sets the value of the merchantId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMerchantId(String value) {
        this.merchantId = value;
    }

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

}
