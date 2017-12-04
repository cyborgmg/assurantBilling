
package br.com.delphos.sca.wsclient;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de listarUsuariosPorParteDescricao complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="listarUsuariosPorParteDescricao">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="parteDescricao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "listarUsuariosPorParteDescricao", propOrder = {
    "parteDescricao"
})
public class ListarUsuariosPorParteDescricao {

    protected String parteDescricao;

    /**
     * Obtém o valor da propriedade parteDescricao.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParteDescricao() {
        return parteDescricao;
    }

    /**
     * Define o valor da propriedade parteDescricao.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParteDescricao(String value) {
        this.parteDescricao = value;
    }

}
