
package br.com.delphos.billing.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de retornoCancelarVenda complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="retornoCancelarVenda">
 *   &lt;complexContent>
 *     &lt;extension base="{http://servicos.billing.delphos.com.br/}retornoBillingService">
 *       &lt;sequence>
 *         &lt;element name="codigoProduto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigoVenda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "retornoCancelarVenda", propOrder = {
    "codigoProduto",
    "codigoVenda"
})
public class RetornoCancelarVenda
    extends RetornoBillingService
{

    protected String codigoProduto;
    protected String codigoVenda;

    /**
     * Obtém o valor da propriedade codigoProduto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoProduto() {
        return codigoProduto;
    }

    /**
     * Define o valor da propriedade codigoProduto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoProduto(String value) {
        this.codigoProduto = value;
    }

    /**
     * Obtém o valor da propriedade codigoVenda.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoVenda() {
        return codigoVenda;
    }

    /**
     * Define o valor da propriedade codigoVenda.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoVenda(String value) {
        this.codigoVenda = value;
    }

}
