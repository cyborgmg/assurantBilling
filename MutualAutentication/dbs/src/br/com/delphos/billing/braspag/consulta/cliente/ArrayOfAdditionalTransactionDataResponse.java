
package br.com.delphos.billing.braspag.consulta.cliente;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfAdditionalTransactionDataResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfAdditionalTransactionDataResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AdditionalTransactionDataResponse" type="{https://www.pagador.com.br/query/pagadorquery}AdditionalTransactionDataResponse" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfAdditionalTransactionDataResponse", propOrder = {
    "additionalTransactionDataResponse"
})
public class ArrayOfAdditionalTransactionDataResponse {

    @XmlElement(name = "AdditionalTransactionDataResponse", nillable = true)
    protected List<AdditionalTransactionDataResponse> additionalTransactionDataResponse;

    /**
     * Gets the value of the additionalTransactionDataResponse property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the additionalTransactionDataResponse property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAdditionalTransactionDataResponse().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AdditionalTransactionDataResponse }
     * 
     * 
     */
    public List<AdditionalTransactionDataResponse> getAdditionalTransactionDataResponse() {
        if (additionalTransactionDataResponse == null) {
            additionalTransactionDataResponse = new ArrayList<AdditionalTransactionDataResponse>();
        }
        return this.additionalTransactionDataResponse;
    }

}
