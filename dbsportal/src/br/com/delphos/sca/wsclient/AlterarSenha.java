
package br.com.delphos.sca.wsclient;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de alterarSenha complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="alterarSenha">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="uid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="atual" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nova" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "alterarSenha", propOrder = {
    "uid",
    "atual",
    "nova"
})
public class AlterarSenha {

    protected String uid;
    protected String atual;
    protected String nova;

    /**
     * Obtém o valor da propriedade uid.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUid() {
        return uid;
    }

    /**
     * Define o valor da propriedade uid.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUid(String value) {
        this.uid = value;
    }

    /**
     * Obtém o valor da propriedade atual.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAtual() {
        return atual;
    }

    /**
     * Define o valor da propriedade atual.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAtual(String value) {
        this.atual = value;
    }

    /**
     * Obtém o valor da propriedade nova.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNova() {
        return nova;
    }

    /**
     * Define o valor da propriedade nova.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNova(String value) {
        this.nova = value;
    }

}
