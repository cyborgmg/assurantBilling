
package br.com.delphos.billing.braspag.conciliador.cliente;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfErrorReport complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfErrorReport">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ErrorReport" type="{https://reconciliation.braspag.com.br}ErrorReport" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfErrorReport", propOrder = {
    "errorReport"
})
public class ArrayOfErrorReport {

    @XmlElement(name = "ErrorReport", nillable = true)
    protected List<ErrorReport> errorReport;

    /**
     * Gets the value of the errorReport property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the errorReport property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getErrorReport().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ErrorReport }
     * 
     * 
     */
    public List<ErrorReport> getErrorReport() {
        if (errorReport == null) {
            errorReport = new ArrayList<ErrorReport>();
        }
        return this.errorReport;
    }

}
