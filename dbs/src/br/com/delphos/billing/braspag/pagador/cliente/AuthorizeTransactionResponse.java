
package br.com.delphos.billing.braspag.pagador.cliente;

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
 *         &lt;element name="AuthorizeTransactionResult" type="{https://www.pagador.com.br/webservice/pagador}AuthorizeTransactionResponse" minOccurs="0"/>
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
    "authorizeTransactionResult"
})
@XmlRootElement(name = "AuthorizeTransactionResponse")
public class AuthorizeTransactionResponse {

    @XmlElement(name = "AuthorizeTransactionResult")
    protected AuthorizeTransactionResponse2 authorizeTransactionResult;

    /**
     * Gets the value of the authorizeTransactionResult property.
     * 
     * @return
     *     possible object is
     *     {@link AuthorizeTransactionResponse2 }
     *     
     */
    public AuthorizeTransactionResponse2 getAuthorizeTransactionResult() {
        return authorizeTransactionResult;
    }

    /**
     * Sets the value of the authorizeTransactionResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link AuthorizeTransactionResponse2 }
     *     
     */
    public void setAuthorizeTransactionResult(AuthorizeTransactionResponse2 value) {
        this.authorizeTransactionResult = value;
    }

}
