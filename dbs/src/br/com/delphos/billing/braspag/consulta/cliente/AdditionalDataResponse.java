
package br.com.delphos.billing.braspag.consulta.cliente;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AdditionalDataResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AdditionalDataResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{https://www.pagador.com.br/query/pagadorquery}AbstractResponse">
 *       &lt;sequence>
 *         &lt;element name="AdditionalDataCollection" type="{https://www.pagador.com.br/query/pagadorquery}ArrayOfAdditionalTransactionDataResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AdditionalDataResponse", propOrder = {
    "additionalDataCollection"
})
public class AdditionalDataResponse
    extends AbstractResponse
{

    @XmlElement(name = "AdditionalDataCollection")
    protected ArrayOfAdditionalTransactionDataResponse additionalDataCollection;

    /**
     * Gets the value of the additionalDataCollection property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfAdditionalTransactionDataResponse }
     *     
     */
    public ArrayOfAdditionalTransactionDataResponse getAdditionalDataCollection() {
        return additionalDataCollection;
    }

    /**
     * Sets the value of the additionalDataCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfAdditionalTransactionDataResponse }
     *     
     */
    public void setAdditionalDataCollection(ArrayOfAdditionalTransactionDataResponse value) {
        this.additionalDataCollection = value;
    }

}
