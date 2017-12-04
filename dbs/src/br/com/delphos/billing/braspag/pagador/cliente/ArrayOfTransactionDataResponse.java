
package br.com.delphos.billing.braspag.pagador.cliente;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfTransactionDataResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfTransactionDataResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TransactionDataResponse" type="{https://www.pagador.com.br/webservice/pagador}TransactionDataResponse" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfTransactionDataResponse", propOrder = {
    "transactionDataResponse"
})
public class ArrayOfTransactionDataResponse {

    @XmlElement(name = "TransactionDataResponse", nillable = true)
    protected List<TransactionDataResponse> transactionDataResponse;

    /**
     * Gets the value of the transactionDataResponse property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the transactionDataResponse property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTransactionDataResponse().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TransactionDataResponse }
     * 
     * 
     */
    public List<TransactionDataResponse> getTransactionDataResponse() {
        if (transactionDataResponse == null) {
            transactionDataResponse = new ArrayList<TransactionDataResponse>();
        }
        return this.transactionDataResponse;
    }

}
