
package br.com.delphos.billing.braspag.consulta.cliente;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BoletoDataRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BoletoDataRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{https://www.pagador.com.br/query/pagadorquery}AbstractRequest">
 *       &lt;sequence>
 *         &lt;element name="MerchantId" type="{http://microsoft.com/wsdl/types/}guid"/>
 *         &lt;element name="BraspagTransactionId" type="{http://microsoft.com/wsdl/types/}guid"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BoletoDataRequest", propOrder = {
    "merchantId",
    "braspagTransactionId"
})
public class BoletoDataRequest
    extends AbstractRequest
{

    @XmlElement(name = "MerchantId", required = true)
    protected String merchantId;
    @XmlElement(name = "BraspagTransactionId", required = true)
    protected String braspagTransactionId;

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
     * Gets the value of the braspagTransactionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBraspagTransactionId() {
        return braspagTransactionId;
    }

    /**
     * Sets the value of the braspagTransactionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBraspagTransactionId(String value) {
        this.braspagTransactionId = value;
    }

}
