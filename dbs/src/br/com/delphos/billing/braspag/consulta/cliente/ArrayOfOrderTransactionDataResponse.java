
package br.com.delphos.billing.braspag.consulta.cliente;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfOrderTransactionDataResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfOrderTransactionDataResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OrderTransactionDataResponse" type="{https://www.pagador.com.br/query/pagadorquery}OrderTransactionDataResponse" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfOrderTransactionDataResponse", propOrder = {
    "orderTransactionDataResponse"
})
public class ArrayOfOrderTransactionDataResponse {

    @XmlElement(name = "OrderTransactionDataResponse", nillable = true)
    protected List<OrderTransactionDataResponse> orderTransactionDataResponse;

    /**
     * Gets the value of the orderTransactionDataResponse property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the orderTransactionDataResponse property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOrderTransactionDataResponse().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OrderTransactionDataResponse }
     * 
     * 
     */
    public List<OrderTransactionDataResponse> getOrderTransactionDataResponse() {
        if (orderTransactionDataResponse == null) {
            orderTransactionDataResponse = new ArrayList<OrderTransactionDataResponse>();
        }
        return this.orderTransactionDataResponse;
    }

}
