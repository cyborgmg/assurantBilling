
package br.com.delphos.billing.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de iniciarVendaCartaoCredito complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conte�do esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="iniciarVendaCartaoCredito">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="token" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigoEmpresa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigoProduto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigoSistema" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cpf" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dddCelular" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="telefoneCelular" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dddContato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="telefoneContato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigoVenda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataVenda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bandeiraCartao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cpfPortadorCartao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nomePortadorCartao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroCartao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vencimentoCartao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cvvCartao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoCobranca" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="quantidadeParcelas" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="valorCobranca" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataPrimeiraCobranca" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataFimVigencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "iniciarVendaCartaoCredito", propOrder = {
    "token",
    "codigoEmpresa",
    "codigoProduto",
    "codigoSistema",
    "cpf",
    "nome",
    "email",
    "dddCelular",
    "telefoneCelular",
    "dddContato",
    "telefoneContato",
    "codigoVenda",
    "dataVenda",
    "bandeiraCartao",
    "cpfPortadorCartao",
    "nomePortadorCartao",
    "numeroCartao",
    "vencimentoCartao",
    "cvvCartao",
    "tipoCobranca",
    "quantidadeParcelas",
    "valorCobranca",
    "dataPrimeiraCobranca",
    "dataFimVigencia"
})
public class IniciarVendaCartaoCredito {

    protected String token;
    protected String codigoEmpresa;
    protected String codigoProduto;
    protected String codigoSistema;
    protected String cpf;
    protected String nome;
    protected String email;
    protected String dddCelular;
    protected String telefoneCelular;
    protected String dddContato;
    protected String telefoneContato;
    protected String codigoVenda;
    protected String dataVenda;
    protected String bandeiraCartao;
    protected String cpfPortadorCartao;
    protected String nomePortadorCartao;
    protected String numeroCartao;
    protected String vencimentoCartao;
    protected String cvvCartao;
    protected String tipoCobranca;
    protected String quantidadeParcelas;
    protected String valorCobranca;
    protected String dataPrimeiraCobranca;
    protected String dataFimVigencia;

    /**
     * Obt�m o valor da propriedade token.
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
     * Obt�m o valor da propriedade codigoEmpresa.
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
     * Obt�m o valor da propriedade codigoProduto.
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
     * Obt�m o valor da propriedade codigoSistema.
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
     * Obt�m o valor da propriedade cpf.
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
     * Obt�m o valor da propriedade nome.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o valor da propriedade nome.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNome(String value) {
        this.nome = value;
    }

    /**
     * Obt�m o valor da propriedade email.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define o valor da propriedade email.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Obt�m o valor da propriedade dddCelular.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDddCelular() {
        return dddCelular;
    }

    /**
     * Define o valor da propriedade dddCelular.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDddCelular(String value) {
        this.dddCelular = value;
    }

    /**
     * Obt�m o valor da propriedade telefoneCelular.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelefoneCelular() {
        return telefoneCelular;
    }

    /**
     * Define o valor da propriedade telefoneCelular.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelefoneCelular(String value) {
        this.telefoneCelular = value;
    }

    /**
     * Obt�m o valor da propriedade dddContato.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDddContato() {
        return dddContato;
    }

    /**
     * Define o valor da propriedade dddContato.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDddContato(String value) {
        this.dddContato = value;
    }

    /**
     * Obt�m o valor da propriedade telefoneContato.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelefoneContato() {
        return telefoneContato;
    }

    /**
     * Define o valor da propriedade telefoneContato.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelefoneContato(String value) {
        this.telefoneContato = value;
    }

    /**
     * Obt�m o valor da propriedade codigoVenda.
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
     * Obt�m o valor da propriedade dataVenda.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataVenda() {
        return dataVenda;
    }

    /**
     * Define o valor da propriedade dataVenda.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataVenda(String value) {
        this.dataVenda = value;
    }

    /**
     * Obt�m o valor da propriedade bandeiraCartao.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBandeiraCartao() {
        return bandeiraCartao;
    }

    /**
     * Define o valor da propriedade bandeiraCartao.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBandeiraCartao(String value) {
        this.bandeiraCartao = value;
    }

    /**
     * Obt�m o valor da propriedade cpfPortadorCartao.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCpfPortadorCartao() {
        return cpfPortadorCartao;
    }

    /**
     * Define o valor da propriedade cpfPortadorCartao.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCpfPortadorCartao(String value) {
        this.cpfPortadorCartao = value;
    }

    /**
     * Obt�m o valor da propriedade nomePortadorCartao.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomePortadorCartao() {
        return nomePortadorCartao;
    }

    /**
     * Define o valor da propriedade nomePortadorCartao.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomePortadorCartao(String value) {
        this.nomePortadorCartao = value;
    }

    /**
     * Obt�m o valor da propriedade numeroCartao.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroCartao() {
        return numeroCartao;
    }

    /**
     * Define o valor da propriedade numeroCartao.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroCartao(String value) {
        this.numeroCartao = value;
    }

    /**
     * Obt�m o valor da propriedade vencimentoCartao.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVencimentoCartao() {
        return vencimentoCartao;
    }

    /**
     * Define o valor da propriedade vencimentoCartao.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVencimentoCartao(String value) {
        this.vencimentoCartao = value;
    }

    /**
     * Obt�m o valor da propriedade cvvCartao.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCvvCartao() {
        return cvvCartao;
    }

    /**
     * Define o valor da propriedade cvvCartao.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCvvCartao(String value) {
        this.cvvCartao = value;
    }

    /**
     * Obt�m o valor da propriedade tipoCobranca.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoCobranca() {
        return tipoCobranca;
    }

    /**
     * Define o valor da propriedade tipoCobranca.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoCobranca(String value) {
        this.tipoCobranca = value;
    }

    /**
     * Obt�m o valor da propriedade quantidadeParcelas.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuantidadeParcelas() {
        return quantidadeParcelas;
    }

    /**
     * Define o valor da propriedade quantidadeParcelas.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuantidadeParcelas(String value) {
        this.quantidadeParcelas = value;
    }

    /**
     * Obt�m o valor da propriedade valorCobranca.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValorCobranca() {
        return valorCobranca;
    }

    /**
     * Define o valor da propriedade valorCobranca.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValorCobranca(String value) {
        this.valorCobranca = value;
    }

    /**
     * Obt�m o valor da propriedade dataPrimeiraCobranca.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataPrimeiraCobranca() {
        return dataPrimeiraCobranca;
    }

    /**
     * Define o valor da propriedade dataPrimeiraCobranca.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataPrimeiraCobranca(String value) {
        this.dataPrimeiraCobranca = value;
    }

    /**
     * Obt�m o valor da propriedade dataFimVigencia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataFimVigencia() {
        return dataFimVigencia;
    }

    /**
     * Define o valor da propriedade dataFimVigencia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataFimVigencia(String value) {
        this.dataFimVigencia = value;
    }

}
