
package br.com.delphos.billing.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de iniciarVendaCartaoCreditoResponse complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="iniciarVendaCartaoCreditoResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://servicos.billing.delphos.com.br/}retornoIniciarVendaCartaoCredito" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "iniciarVendaCartaoCreditoResponse", propOrder = {
    "_return"
})
public class IniciarVendaCartaoCreditoResponse {

    @XmlElement(name = "return")
    protected RetornoIniciarVendaCartaoCredito _return;

    /**
     * Obtém o valor da propriedade return.
     * 
     * @return
     *     possible object is
     *     {@link RetornoIniciarVendaCartaoCredito }
     *     
     */
    public RetornoIniciarVendaCartaoCredito getReturn() {
        return _return;
    }

    /**
     * Define o valor da propriedade return.
     * 
     * @param value
     *     allowed object is
     *     {@link RetornoIniciarVendaCartaoCredito }
     *     
     */
    public void setReturn(RetornoIniciarVendaCartaoCredito value) {
        this._return = value;
    }

}
