
package br.com.delphos.billing.braspag.consulta.cliente;

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
 *         &lt;element name="GetBoletoDataResult" type="{https://www.pagador.com.br/query/pagadorquery}BoletoDataResponse" minOccurs="0"/>
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
    "getBoletoDataResult"
})
@XmlRootElement(name = "GetBoletoDataResponse")
public class GetBoletoDataResponse {

    @XmlElement(name = "GetBoletoDataResult")
    protected BoletoDataResponse getBoletoDataResult;

    /**
     * Gets the value of the getBoletoDataResult property.
     * 
     * @return
     *     possible object is
     *     {@link BoletoDataResponse }
     *     
     */
    public BoletoDataResponse getGetBoletoDataResult() {
        return getBoletoDataResult;
    }

    /**
     * Sets the value of the getBoletoDataResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link BoletoDataResponse }
     *     
     */
    public void setGetBoletoDataResult(BoletoDataResponse value) {
        this.getBoletoDataResult = value;
    }

}
