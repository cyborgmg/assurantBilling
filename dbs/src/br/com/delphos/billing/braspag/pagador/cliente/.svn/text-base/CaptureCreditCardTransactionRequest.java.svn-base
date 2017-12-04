
package br.com.delphos.billing.braspag.pagador.cliente;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CaptureCreditCardTransactionRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CaptureCreditCardTransactionRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{https://www.pagador.com.br/webservice/pagador}AbstractRequest">
 *       &lt;sequence>
 *         &lt;element name="MerchantId" type="{http://microsoft.com/wsdl/types/}guid"/>
 *         &lt;element name="TransactionDataCollection" type="{https://www.pagador.com.br/webservice/pagador}ArrayOfTransactionDataRequest" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CaptureCreditCardTransactionRequest", propOrder = {
    "merchantId",
    "transactionDataCollection"
})
public class CaptureCreditCardTransactionRequest
    extends AbstractRequest
{

    @XmlElement(name = "MerchantId", required = true)
    protected String merchantId;
    @XmlElement(name = "TransactionDataCollection")
    protected ArrayOfTransactionDataRequest transactionDataCollection;

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
     * Gets the value of the transactionDataCollection property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfTransactionDataRequest }
     *     
     */
    public ArrayOfTransactionDataRequest getTransactionDataCollection() {
        return transactionDataCollection;
    }

    /**
     * Sets the value of the transactionDataCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfTransactionDataRequest }
     *     
     */
    public void setTransactionDataCollection(ArrayOfTransactionDataRequest value) {
        this.transactionDataCollection = value;
    }

}
