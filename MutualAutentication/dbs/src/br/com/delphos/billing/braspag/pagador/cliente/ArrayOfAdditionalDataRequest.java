
package br.com.delphos.billing.braspag.pagador.cliente;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfAdditionalDataRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfAdditionalDataRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AdditionalDataRequest" type="{https://www.pagador.com.br/webservice/pagador}AdditionalDataRequest" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfAdditionalDataRequest", propOrder = {
    "additionalDataRequest"
})
public class ArrayOfAdditionalDataRequest {

    @XmlElement(name = "AdditionalDataRequest", nillable = true)
    protected List<AdditionalDataRequest> additionalDataRequest;

    /**
     * Gets the value of the additionalDataRequest property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the additionalDataRequest property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAdditionalDataRequest().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AdditionalDataRequest }
     * 
     * 
     */
    public List<AdditionalDataRequest> getAdditionalDataRequest() {
        if (additionalDataRequest == null) {
            additionalDataRequest = new ArrayList<AdditionalDataRequest>();
        }
        return this.additionalDataRequest;
    }

}
