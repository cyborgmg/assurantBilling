
package br.com.delphos.billing.braspag.conciliador;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfConciliatedTransaction complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfConciliatedTransaction">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ConciliatedTransaction" type="{}ConciliatedTransaction" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfConciliatedTransaction", propOrder = {
    "conciliatedTransaction"
})
public class ArrayOfConciliatedTransaction {

    @XmlElement(name = "ConciliatedTransaction", nillable = true)
    protected List<ConciliatedTransaction> conciliatedTransaction;

    /**
     * Gets the value of the conciliatedTransaction property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the conciliatedTransaction property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConciliatedTransaction().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ConciliatedTransaction }
     * 
     * 
     */
    public List<ConciliatedTransaction> getConciliatedTransaction() {
        if (conciliatedTransaction == null) {
            conciliatedTransaction = new ArrayList<ConciliatedTransaction>();
        }
        return this.conciliatedTransaction;
    }

}
