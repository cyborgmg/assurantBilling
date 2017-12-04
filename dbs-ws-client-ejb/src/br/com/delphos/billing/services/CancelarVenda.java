
package br.com.delphos.billing.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de cancelarVenda complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="cancelarVenda">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codigoEmpresa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigoProduto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigoSistema" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="token" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cpf" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigoVenda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigoMotivoCancelamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="valorEstorno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="flagCancelamentoGateway" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cancelarVenda", propOrder = {
    "codigoEmpresa",
    "codigoProduto",
    "codigoSistema",
    "token",
    "cpf",
    "codigoVenda",
    "codigoMotivoCancelamento",
    "valorEstorno",
    "flagCancelamentoGateway"
})
public class CancelarVenda {

    protected String codigoEmpresa;
    protected String codigoProduto;
    protected String codigoSistema;
    protected String token;
    protected String cpf;
    protected String codigoVenda;
    protected String codigoMotivoCancelamento;
    protected String valorEstorno;
    protected String flagCancelamentoGateway;

    /**
     * Obtém o valor da propriedade codigoEmpresa.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoEmpresa() {
        return codigoEmpresa;
    }

    /**
     * Define o valor da propriedade codigoEmpresa.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoEmpresa(String value) {
        this.codigoEmpresa = value;
    }

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
     * Obtém o valor da propriedade codigoSistema.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoSistema() {
        return codigoSistema;
    }

    /**
     * Define o valor da propriedade codigoSistema.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoSistema(String value) {
        this.codigoSistema = value;
    }

    /**
     * Obtém o valor da propriedade token.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToken() {
        return token;
    }

    /**
     * Define o valor da propriedade token.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToken(String value) {
        this.token = value;
    }

    /**
     * Obtém o valor da propriedade cpf.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Define o valor da propriedade cpf.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCpf(String value) {
        this.cpf = value;
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

    /**
     * Obtém o valor da propriedade codigoMotivoCancelamento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoMotivoCancelamento() {
        return codigoMotivoCancelamento;
    }

    /**
     * Define o valor da propriedade codigoMotivoCancelamento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoMotivoCancelamento(String value) {
        this.codigoMotivoCancelamento = value;
    }

    /**
     * Obtém o valor da propriedade valorEstorno.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValorEstorno() {
        return valorEstorno;
    }

    /**
     * Define o valor da propriedade valorEstorno.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValorEstorno(String value) {
        this.valorEstorno = value;
    }

    /**
     * Obtém o valor da propriedade flagCancelamentoGateway.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlagCancelamentoGateway() {
        return flagCancelamentoGateway;
    }

    /**
     * Define o valor da propriedade flagCancelamentoGateway.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlagCancelamentoGateway(String value) {
        this.flagCancelamentoGateway = value;
    }

}
